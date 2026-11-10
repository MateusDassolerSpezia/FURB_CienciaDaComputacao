package miniso.modulo5;

/** Fachada dos cenários demonstrativos do sistema de arquivos FAT. */
public final class SimuladorModulo5 {
    private SimuladorModulo5() {
    }

    public static ResultadoSistemaArquivos executar(String cenario) {
        SistemaArquivosFAT sistema = new SistemaArquivosFAT();
        if ("reuso".equals(cenario)) {
            sistema.criarDiretorio("/docs");
            sistema.criarDiretorio("/imagens");
            sistema.criarArquivo("/docs/relatorio.txt", 150);
            sistema.criarArquivo("/imagens/logo.bmp", 210);
            sistema.criarArquivo("/docs/notas.txt", 90);
            sistema.excluirArquivo("/imagens/logo.bmp");
            sistema.criarArquivo("/backup.bin", 300);
            sistema.lerArquivo("/docs/relatorio.txt");
            return sistema.resultado("Alocação e reaproveitamento",
                    "Blocos liberados por um arquivo excluído são reutilizados e encadeados pela FAT.");
        }
        if ("lotacao".equals(cenario)) {
            sistema.criarDiretorio("/dados");
            sistema.criarArquivo("/dados/a.bin", 768);
            sistema.criarArquivo("/dados/b.bin", 640);
            sistema.excluirArquivo("/dados/a.bin");
            sistema.criarArquivo("/dados/c.bin", 896);
            try {
                sistema.criarArquivo("/dados/grande.bin", 1024);
            } catch (IllegalStateException erro) {
                sistema.registrarFalhaEsperada("o arquivo grande não aparece no diretório e nenhum bloco parcial foi ocupado.");
            }
            sistema.lerArquivo("/dados/c.bin");
            return sistema.resultado("Disco cheio e rollback",
                    "Uma solicitação maior que o espaço livre é recusada sem deixar alocação parcial no disco.");
        }
        throw new IllegalArgumentException("Cenário desconhecido: " + cenario);
    }
}
