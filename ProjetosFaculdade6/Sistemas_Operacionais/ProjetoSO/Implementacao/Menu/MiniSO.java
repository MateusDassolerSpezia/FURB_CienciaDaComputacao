package miniso.menu;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import miniso.modulo1.ResultadoSimulacao;
import miniso.modulo1.SimuladorModulo1;
import miniso.modulo2.ResultadoEscalonamento;
import miniso.modulo2.SimuladorModulo2;
import miniso.modulo3.ResultadoMemoria;
import miniso.modulo3.SimuladorModulo3;
import miniso.modulo4.ResultadoDeadlock;
import miniso.modulo4.SimuladorModulo4;
import miniso.modulo5.ResultadoSistemaArquivos;
import miniso.modulo5.SimuladorModulo5;

import java.awt.Desktop;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Servidor local que integra as telas HTML ao núcleo Java do Mini-SO. */
public final class MiniSO {
    private final Path raizImplementacao;
    private final int porta;

    private MiniSO(Path raizImplementacao, int porta) {
        this.raizImplementacao = raizImplementacao;
        this.porta = porta;
    }

    public static void main(String[] args) throws Exception {
        Configuracao configuracao = Configuracao.ler(args);
        new MiniSO(configuracao.raiz, configuracao.porta).iniciar(configuracao.abrirNavegador);
    }

    private void iniciar(boolean abrirNavegador) throws IOException {
        validarArquivos();
        HttpServer servidor = HttpServer.create(new InetSocketAddress("127.0.0.1", porta), 0);
        ExecutorService executor = Executors.newCachedThreadPool();
        servidor.createContext("/api/modulo1", this::executarModulo1);
        servidor.createContext("/api/modulo2", this::executarModulo2);
        servidor.createContext("/api/modulo3", this::executarModulo3);
        servidor.createContext("/api/modulo4", this::executarModulo4);
        servidor.createContext("/api/modulo5", this::executarModulo5);
        servidor.createContext("/api/status", troca -> enviarJson(troca, 200, "{\"status\":\"online\"}"));
        servidor.createContext("/api/encerrar", troca -> encerrar(troca, servidor, executor));
        servidor.createContext("/", this::servirInterface);
        servidor.setExecutor(executor);
        servidor.start();

        URI endereco = URI.create("http://127.0.0.1:" + porta + "/");
        System.out.println("Mini-SO iniciado em " + endereco);
        System.out.println("Pressione Ctrl+C para encerrar.");

        if (abrirNavegador && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(endereco);
            } catch (Exception erro) {
                System.out.println("Abra o endereço acima no navegador.");
            }
        }
    }

    private void encerrar(HttpExchange troca, HttpServer servidor, ExecutorService executor) throws IOException {
        if (!"POST".equalsIgnoreCase(troca.getRequestMethod())) {
            troca.getResponseHeaders().set("Allow", "POST");
            enviarJson(troca, 405, erroJson("Use o método POST para encerrar o Mini-SO."));
            return;
        }

        enviarJson(troca, 200, "{\"status\":\"encerrando\"}");
        Thread encerramento = new Thread(() -> {
            try {
                Thread.sleep(250L);
            } catch (InterruptedException erro) {
                Thread.currentThread().interrupt();
            }
            servidor.stop(0);
            executor.shutdownNow();
            System.out.println("Mini-SO encerrado pelo menu.");
        }, "Encerramento do Mini-SO");
        encerramento.start();
    }

    private void executarModulo1(HttpExchange troca) throws IOException {
        if (!"POST".equalsIgnoreCase(troca.getRequestMethod())) {
            troca.getResponseHeaders().set("Allow", "POST");
            enviarJson(troca, 405, erroJson("Use o método POST para iniciar uma simulação."));
            return;
        }

        String simulacao = parametros(troca.getRequestURI().getRawQuery()).get("simulacao");
        if (simulacao == null || simulacao.trim().isEmpty()) {
            enviarJson(troca, 400, erroJson("Informe a simulação desejada."));
            return;
        }

        try {
            ResultadoSimulacao resultado = SimuladorModulo1.executar(simulacao);
            enviarJson(troca, 200, resultado.paraJson());
        } catch (IllegalArgumentException erro) {
            enviarJson(troca, 400, erroJson(erro.getMessage()));
        } catch (InterruptedException erro) {
            Thread.currentThread().interrupt();
            enviarJson(troca, 503, erroJson("A simulação foi interrompida."));
        } catch (RuntimeException erro) {
            enviarJson(troca, 500, erroJson("Não foi possível concluir a simulação: " + erro.getMessage()));
        }
    }

    private void executarModulo2(HttpExchange troca) throws IOException {
        if (!"POST".equalsIgnoreCase(troca.getRequestMethod())) {
            troca.getResponseHeaders().set("Allow", "POST");
            enviarJson(troca, 405, erroJson("Use o método POST para iniciar o escalonador."));
            return;
        }

        Map<String, String> consulta = parametros(troca.getRequestURI().getRawQuery());
        String algoritmo = consulta.get("algoritmo");
        String quantumInformado = consulta.get("quantum");
        if (algoritmo == null || algoritmo.trim().isEmpty()) {
            enviarJson(troca, 400, erroJson("Informe o algoritmo de escalonamento."));
            return;
        }

        try {
            int quantum = quantumInformado == null ? 2 : Integer.parseInt(quantumInformado);
            ResultadoEscalonamento resultado = SimuladorModulo2.executar(algoritmo, quantum);
            enviarJson(troca, 200, resultado.paraJson());
        } catch (NumberFormatException erro) {
            enviarJson(troca, 400, erroJson("O quantum deve ser um número inteiro."));
        } catch (IllegalArgumentException erro) {
            enviarJson(troca, 400, erroJson(erro.getMessage()));
        } catch (RuntimeException erro) {
            enviarJson(troca, 500, erroJson("Não foi possível concluir o escalonamento: " + erro.getMessage()));
        }
    }

    private void executarModulo3(HttpExchange troca) throws IOException {
        if (!"POST".equalsIgnoreCase(troca.getRequestMethod())) {
            troca.getResponseHeaders().set("Allow", "POST");
            enviarJson(troca, 405, erroJson("Use o método POST para iniciar a MMU."));
            return;
        }

        Map<String, String> consulta = parametros(troca.getRequestURI().getRawQuery());
        String algoritmo = consulta.get("algoritmo");
        String quadrosInformados = consulta.get("quadros");
        if (algoritmo == null || algoritmo.trim().isEmpty()) {
            enviarJson(troca, 400, erroJson("Informe o algoritmo de substituição de páginas."));
            return;
        }

        try {
            int quadros = quadrosInformados == null ? 3 : Integer.parseInt(quadrosInformados);
            ResultadoMemoria resultado = SimuladorModulo3.executar(algoritmo, quadros);
            enviarJson(troca, 200, resultado.paraJson());
        } catch (NumberFormatException erro) {
            enviarJson(troca, 400, erroJson("A quantidade de quadros deve ser um número inteiro."));
        } catch (IllegalArgumentException erro) {
            enviarJson(troca, 400, erroJson(erro.getMessage()));
        } catch (RuntimeException erro) {
            enviarJson(troca, 500, erroJson("Não foi possível concluir a simulação da MMU: " + erro.getMessage()));
        }
    }

    private void executarModulo4(HttpExchange troca) throws IOException {
        if (!"POST".equalsIgnoreCase(troca.getRequestMethod())) {
            troca.getResponseHeaders().set("Allow", "POST");
            enviarJson(troca, 405, erroJson("Use o método POST para iniciar a análise de deadlock."));
            return;
        }

        Map<String, String> consulta = parametros(troca.getRequestURI().getRawQuery());
        String experimento = consulta.get("experimento");
        String cenario = consulta.get("cenario");
        if (experimento == null || experimento.trim().isEmpty()
                || cenario == null || cenario.trim().isEmpty()) {
            enviarJson(troca, 400, erroJson("Informe o experimento e o cenário desejados."));
            return;
        }

        try {
            ResultadoDeadlock resultado = SimuladorModulo4.executar(experimento, cenario);
            enviarJson(troca, 200, resultado.paraJson());
        } catch (IllegalArgumentException erro) {
            enviarJson(troca, 400, erroJson(erro.getMessage()));
        } catch (RuntimeException erro) {
            enviarJson(troca, 500, erroJson("Não foi possível concluir a análise: " + erro.getMessage()));
        }
    }

    private void executarModulo5(HttpExchange troca) throws IOException {
        if (!"POST".equalsIgnoreCase(troca.getRequestMethod())) {
            troca.getResponseHeaders().set("Allow", "POST");
            enviarJson(troca, 405, erroJson("Use o método POST para iniciar o sistema de arquivos."));
            return;
        }

        String cenario = parametros(troca.getRequestURI().getRawQuery()).get("cenario");
        if (cenario == null || cenario.trim().isEmpty()) {
            enviarJson(troca, 400, erroJson("Informe o cenário do sistema de arquivos."));
            return;
        }

        try {
            ResultadoSistemaArquivos resultado = SimuladorModulo5.executar(cenario);
            enviarJson(troca, 200, resultado.paraJson());
        } catch (IllegalArgumentException erro) {
            enviarJson(troca, 400, erroJson(erro.getMessage()));
        } catch (RuntimeException erro) {
            enviarJson(troca, 500, erroJson("Não foi possível concluir as operações do disco: " + erro.getMessage()));
        }
    }

    private void servirInterface(HttpExchange troca) throws IOException {
        if (!"GET".equalsIgnoreCase(troca.getRequestMethod())) {
            enviarTexto(troca, 405, "Método não permitido.", "text/plain; charset=utf-8");
            return;
        }

        String caminho = troca.getRequestURI().getPath();
        Path arquivo;
        switch (caminho) {
            case "/":
            case "/index.html":
                arquivo = raizImplementacao.resolve("Menu/index.html");
                break;
            case "/menu.css":
                arquivo = raizImplementacao.resolve("Menu/menu.css");
                break;
            case "/menu.js":
                arquivo = raizImplementacao.resolve("Menu/menu.js");
                break;
            case "/modulo1":
            case "/modulo1/":
            case "/modulo1/index.html":
                arquivo = raizImplementacao.resolve("Modulo1/index.html");
                break;
            case "/modulo1/modulo1.css":
                arquivo = raizImplementacao.resolve("Modulo1/modulo1.css");
                break;
            case "/modulo1/modulo1.js":
                arquivo = raizImplementacao.resolve("Modulo1/modulo1.js");
                break;
            case "/modulo2":
            case "/modulo2/":
            case "/modulo2/index.html":
                arquivo = raizImplementacao.resolve("Modulo2/index.html");
                break;
            case "/modulo2/modulo2.css":
                arquivo = raizImplementacao.resolve("Modulo2/modulo2.css");
                break;
            case "/modulo2/modulo2.js":
                arquivo = raizImplementacao.resolve("Modulo2/modulo2.js");
                break;
            case "/modulo3":
            case "/modulo3/":
            case "/modulo3/index.html":
                arquivo = raizImplementacao.resolve("Modulo3/index.html");
                break;
            case "/modulo3/modulo3.css":
                arquivo = raizImplementacao.resolve("Modulo3/modulo3.css");
                break;
            case "/modulo3/modulo3.js":
                arquivo = raizImplementacao.resolve("Modulo3/modulo3.js");
                break;
            case "/modulo4":
            case "/modulo4/":
            case "/modulo4/index.html":
                arquivo = raizImplementacao.resolve("Modulo4/index.html");
                break;
            case "/modulo4/modulo4.css":
                arquivo = raizImplementacao.resolve("Modulo4/modulo4.css");
                break;
            case "/modulo4/modulo4.js":
                arquivo = raizImplementacao.resolve("Modulo4/modulo4.js");
                break;
            case "/modulo5":
            case "/modulo5/":
            case "/modulo5/index.html":
                arquivo = raizImplementacao.resolve("Modulo5/index.html");
                break;
            case "/modulo5/modulo5.css":
                arquivo = raizImplementacao.resolve("Modulo5/modulo5.css");
                break;
            case "/modulo5/modulo5.js":
                arquivo = raizImplementacao.resolve("Modulo5/modulo5.js");
                break;
            case "/favicon.ico":
                troca.sendResponseHeaders(204, -1);
                troca.close();
                return;
            default:
                enviarTexto(troca, 404, "Página não encontrada.", "text/plain; charset=utf-8");
                return;
        }

        if (!Files.isRegularFile(arquivo)) {
            enviarTexto(troca, 404, "Arquivo da interface não encontrado.", "text/plain; charset=utf-8");
            return;
        }

        String tipo = caminho.endsWith(".css") ? "text/css; charset=utf-8"
                : caminho.endsWith(".js") ? "application/javascript; charset=utf-8"
                : "text/html; charset=utf-8";
        byte[] conteudo = Files.readAllBytes(arquivo);
        troca.getResponseHeaders().set("Content-Type", tipo);
        troca.getResponseHeaders().set("Cache-Control", "no-store");
        troca.sendResponseHeaders(200, conteudo.length);
        troca.getResponseBody().write(conteudo);
        troca.close();
    }

    private void validarArquivos() {
        if (!Files.isRegularFile(raizImplementacao.resolve("Menu/index.html"))
                || !Files.isRegularFile(raizImplementacao.resolve("Modulo1/index.html"))
                || !Files.isRegularFile(raizImplementacao.resolve("Modulo2/index.html"))
                || !Files.isRegularFile(raizImplementacao.resolve("Modulo3/index.html"))
                || !Files.isRegularFile(raizImplementacao.resolve("Modulo4/index.html"))
                || !Files.isRegularFile(raizImplementacao.resolve("Modulo5/index.html"))) {
            throw new IllegalStateException("A raiz de implementação é inválida: " + raizImplementacao);
        }
    }

    private static Map<String, String> parametros(String consulta) throws IOException {
        Map<String, String> itens = new HashMap<>();
        if (consulta == null || consulta.isEmpty()) {
            return itens;
        }
        for (String parte : consulta.split("&")) {
            String[] par = parte.split("=", 2);
            String chave = URLDecoder.decode(par[0], "UTF-8");
            String valor = par.length > 1 ? URLDecoder.decode(par[1], "UTF-8") : "";
            itens.put(chave, valor);
        }
        return itens;
    }

    private static String erroJson(String mensagem) {
        String segura = mensagem.replace("\\", "\\\\").replace("\"", "\\\"");
        return "{\"erro\":\"" + segura + "\"}";
    }

    private static void enviarJson(HttpExchange troca, int status, String json) throws IOException {
        enviarTexto(troca, status, json, "application/json; charset=utf-8");
    }

    private static void enviarTexto(HttpExchange troca, int status, String texto, String tipo) throws IOException {
        byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
        troca.getResponseHeaders().set("Content-Type", tipo);
        troca.getResponseHeaders().set("Cache-Control", "no-store");
        troca.sendResponseHeaders(status, bytes.length);
        troca.getResponseBody().write(bytes);
        troca.close();
    }

    private static final class Configuracao {
        private final Path raiz;
        private final int porta;
        private final boolean abrirNavegador;

        private Configuracao(Path raiz, int porta, boolean abrirNavegador) {
            this.raiz = raiz;
            this.porta = porta;
            this.abrirNavegador = abrirNavegador;
        }

        private static Configuracao ler(String[] args) {
            Path raiz = localizarRaizPadrao();
            int porta = 8080;
            boolean abrir = true;
            for (String argumento : args) {
                if (argumento.startsWith("--raiz=")) {
                    raiz = Paths.get(argumento.substring("--raiz=".length())).toAbsolutePath().normalize();
                } else if (argumento.startsWith("--porta=")) {
                    porta = Integer.parseInt(argumento.substring("--porta=".length()));
                } else if ("--sem-navegador".equals(argumento)) {
                    abrir = false;
                }
            }
            if (porta < 1 || porta > 65535) {
                throw new IllegalArgumentException("A porta deve estar entre 1 e 65535.");
            }
            return new Configuracao(raiz, porta, abrir);
        }

        private static Path localizarRaizPadrao() {
            Path atual = Paths.get("").toAbsolutePath().normalize();
            if (Files.isDirectory(atual.resolve("Menu")) && Files.isDirectory(atual.resolve("Modulo1"))) {
                return atual;
            }
            if (Files.isDirectory(atual.resolve("Implementacao"))) {
                return atual.resolve("Implementacao");
            }
            if (atual.getParent() != null && Files.isDirectory(atual.getParent().resolve("Modulo1"))) {
                return atual.getParent();
            }
            return atual.resolve("Implementacao");
        }
    }
}
