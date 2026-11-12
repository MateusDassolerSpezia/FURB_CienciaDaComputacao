# Relatório de continuidade — NewTec / ESB Mule

Atualizado em 12/11/2026. Documento para contextualizar uma nova conversa e continuar a implementação sem refazer o que já foi concluído. Conferir os arquivos atuais antes de modificar qualquer coisa.

## 1. Objetivo e decisões do usuário

O usuário está desenvolvendo um trabalho acadêmico de Sistemas Distribuídos. Pediu reunir no projeto `new_tec` todos os serviços Mule já implementados: fornecedores de robôs/reatores, CEP, frete legado, descontos, comissão RMI, confirmação manual pelo teclado e cartões Visa/Mastercard.

Preferências:

- Implementação preferencialmente linear; criar flows adicionais somente quando necessários.
- Preservar os demais projetos Mule do workspace.
- A restrição original de não alterar outros projetos teve autorizações específicas posteriores: corrigir os DAOs dos dois fornecedores no NetBeans e remover o total fixo da StarkSystem. Essas correções foram aplicadas. Não interpretar isso como autorização irrestrita para outros projetos.
- O usuário escolheu explicitamente comissão de 15% no new_tec, mantendo a chamada ao serviço RMI existente, que calcula 10%.
- A interface HTML foi melhorada por solicitação do usuário, sem mudar os campos, opções e lógica de envio.

A solicitação atual é apenas criar este relatório e salvá-lo em Downloads. Ainda não foi escolhido o próximo requisito a implementar.

## 2. Ambiente, fontes e caminhos

Windows / PowerShell. Workspace:

`C:\Users\mdspezia\AnypointStudio\workspace`

Projeto integrado:

`C:\Users\mdspezia\AnypointStudio\workspace\new_tec`

Anypoint Studio 6.6.11, Mule 3.9.5 EE, Java 8.

- JDK usado nos testes: `C:\ESB\OpenJDK8U-jdk_x64_windows_hotspot_8u502b07\jdk8u502-b07`
- Runtime: `C:\ESB\AnypointStudio\plugins\org.mule.tooling.server.3.9.5.ee_6.6.4.202212061409\mule`
- O Java padrão do terminal é Java 25; usar explicitamente Java 8 para esta aplicação.
- A sessão tinha filesystem restrito; gravações e algumas execuções exigiram elevação pela ferramenta. Respeitar as permissões da nova sessão.

Projetos Mule de referência preservados no workspace: `br_robotics`, `stark_system`, `ws_rest_cep`, `calcula_frete`, `calcula_comissao`, `cartao_visa`, `cartao_master`, `inserir_pedido`; também existe `frontend_teste`.

Materiais enviados e já lidos nesta conversa:

- `C:\Users\mdspezia\Downloads\Enunciado Trabalho final da disciplina - 2026_2.pdf`
- `C:\Users\mdspezia\Downloads\banco de dados.pdf`
- `C:\Users\mdspezia\Downloads\ConfirmarPedido.java`
- Diagrama: `C:\Users\mdspezia\Downloads\trabalho final com as tecnologias.png`

O script ConfirmarPedido.java continha uma classe TratarResultadoPedido de outro pacote. Foi adaptado ao pacote/modelo do new_tec, preservando a confirmação síncrona pelo teclado.

## 3. Serviços externos

O usuário executa Derby em NetBeans > Services; fornecedores via Deploy no NetBeans; comissão e cartões via Run As Java Application no Eclipse.

| Serviço | Endereço / contrato |
| --- | --- |
| Formulário | http://127.0.0.1:8085/ |
| Envio | POST /tratarForm |
| CEP | GET https://ws.homologacao.ufsc.br/CEPService/getCepInfo/{cep} |
| StarkSystem SOAP | http://127.0.0.1:8080/StarkSystem/StarkSystemService |
| WSDL | mesmo endereço com ?wsdl |
| BrRobotics REST | POST http://127.0.0.1:8080/BrRobotics/rest/BrRoboticsService/inserirPedido |
| Comissão RMI | rmi://127.0.0.1:11099/CalculoComissaoService |
| Método RMI | calcularValorComComissao(float) |
| Visa TCP | 127.0.0.1:8900 |
| Mastercard TCP | 127.0.0.1:8901 |
| Derby | localhost:1527; bancos StarkSystem e BrRobotics |

Fontes externas:

- `C:\Users\mdspezia\Documents\NetBeansProjects\StarkSystem`
- `C:\Users\mdspezia\Documents\NetBeansProjects\BrRobotics`
- Comissão: `C:\Users\mdspezia\Downloads\Sistemas Distribuídos\ws_rmi-comissao\eclipse`
- Cartões: `C:\Users\mdspezia\Downloads\Sistemas Distribuídos\processamentoCartao\processamentoCartao\eclipse`

Executável real de frete:

`C:\Users\mdspezia\Downloads\Sistemas Distribuídos\aula 9 2910 calcularFrete\Serviço de calcular o frete-20261029T104544Z-1-001\Serviço de calcular o frete\calculoFrete-win-x64.exe`

Recebe UF maiúscula e retorna número em formato americano. Invocado com ProcessBuilder, executável e UF como argumentos separados. Há timeout de 10 segundos e validação de saída negativa.

Configurações centralizadas em `new_tec/src/main/resources/integracao.properties`. O caminho do executável usa barras normais e escapes Unicode próprios de properties. Não corromper esses escapes ao editar.

## 4. Estado da integração

Arquivo principal: `new_tec/src/main/app/new_tec.xml`.

Dois flows:

- `new_tecFormulario`: serve o HTML, CSS e imagens.
- `new_tecPedido`: sequência linear abaixo.

Sequência implementada:

1. Validar formulário e cartão, agregar quantidades e criar Pedido.
2. Guardar o Pedido na variável de fluxo `pedido`.
3. Consultar CEP por GET/HTTPS e preencher endereço, bairro, cidade e UF.
4. Executar frete com a UF.
5. Calcular descontos dos reatores, transformar para SOAP, inserir na StarkSystem e preservar código/total retornados.
6. Calcular descontos dos robôs, transformar para JSON, inserir na BrRobotics e preservar código/total retornados.
7. Obter subtotal, invocar comissão RMI e consolidar com comissão de 15% e frete.
8. Mostrar resumo no console e aguardar SIM ou NAO + Enter.
9. Se SIM, chamar Visa/Mastercard; se NAO, não efetuar pagamento.
10. Mostrar resultado textual no navegador.

Classes em `src/main/java/br/furb/dsc`:

- `Tratamento`: formulário, quantidades e validações.
- `Pedido`: cliente, endereço completo, quantidades, peças, totais, confirmação e transação.
- `PedidoPeca`, `PedidoRobo`, `PedidoReator`: códigos, bruto, total com desconto e total retornado.
- `TratarPedidoRobo` / `TratarPedidoReator`: itens no formato dos contratos locais.
- `IntegracaoPedido`: descontos, CEP/JSON, leitura SOAP, subtotal e adaptação da comissão.
- `InvocarProcesso`: frete legado.
- `TratarResultadoPedido`: Scanner compartilhado com bloqueio para confirmação humana síncrona; entrada inválida pede novamente.
- `PagamentoPedido`: protocolos TCP, timeouts, transação, taxa e líquido.
- `ResultadoPedido`: resumo e mensagem da causa raiz de erros.

Classes antigas SimulacaoInvocacaoBrRobotics e SimulacaoInvocacaoStarkSystem permanecem, mas não são utilizadas no fluxo real.

Dependência RMI copiada para `new_tec/lib/calculocomissao-service-1.0.0-jar-with-dependencies.jar`, com referência relativa no `.classpath`. Security policy acadêmica em `src/main/app/java_policy`.

Detalhes importantes de compatibilidade:

- Transformações usam DataWeave 1.0 e payload application/java antes de converter para XML/JSON. Isso evita interpretar um objeto Java como XML por herança de MIME.
- O consumidor SOAP devolve XMLStreamReader parcial. receberReator percorre o cursor para extrair codigo/total. Não trocar por object-to-string ou conversão de documento inteiro sem testar.
- Os contratos dos projetos locais usam `itens` e `total`, diferentes dos exemplos de campos de quantidades no PDF.

## 5. Regras já implementadas

As seis combinações do formulário são agregadas por tipo de robô e tipo de reator.

| Peça | Unitário | Desconto quando quantidade > 3 |
| --- | --- | --- |
| Robô doméstico | 900 | 25% |
| Robô segurança | 1800 | 15% |
| Robô médico | 2500 | 10% |
| Reator Ark | 2500 | 10% |
| Reator Solar | 1500 | 15% |

Depois dos descontos por quantidade: B2B BrRobotics de 15%; B2B StarkSystem de 10%. Arredondamento em centavos por fornecedor.

Os descontos são calculados no new_tec e os fornecedores gravam o total recebido. O requisito do PDF de calcular os descontos dentro dos próprios webservices não foi totalmente transferido para eles. Evitar desconto duplicado se isso for migrado futuramente.

Comissão: o RMI atual retorna subtotal acrescido de 10%. A integração valida esse contrato e, por escolha explícita do usuário, aplica comissão local de 15% sobre o subtotal dos fornecedores. Frete somado depois, sem comissão sobre ele. Se o serviço RMI mudar, revisar IntegracaoPedido.consolidar.

Frete executado pelo binário: SP 15; MG/PR/RJ/SC 29; DF/ES/GO/MS/RS 36; AL/BA/MT/PB/PE/SE/TO 47; demais UFs válidas 54.

Visa: PAY, número completo, nome, validade, valor, COMMIT; ACKs OK; retorno de 6 campos separados por dois-pontos. Mastercard: PAYMENT, quatro blocos de quatro dígitos, nome, validade, valor, COMMIT; PROCEED nos dados intermediários; OK no início e COMMIT; retorno de 4 campos.

Taxa do cartão = valor cobrado menos líquido retornado. Servidores fornecidos: Visa 5%, Mastercard 3%. Não é desconto para o cliente. Valor enviado com ponto e duas casas. Nome do cartão aceito apenas com letras sem acentos/espaços simples; validade MM/aaaa posterior ao mês atual, porque o servidor compara com o primeiro dia do mês.

Não há repetição automática após falha de pagamento. Falha após COMMIT gera aviso de resultado incerto. Dados completos do cartão são removidos do Pedido após processamento/cancelamento.

## 6. Problemas encontrados e correções

### Studio não reconhecia classes novas

IntegracaoPedido e ResultadoPedido não apareciam no Package Explorer. Solução: selecionar o projeto inteiro, F5, Project > Clean e Build Automatically. Não criar classes duplicadas nem imports desnecessários.

### Teste com Access restriction

O Studio restringia com.sun.net.httpserver no perfil JavaSE-1.8. O verificador saiu de src/test/java para `tools/verificacao/VerificarIntegracao.java`. É compilado explicitamente pelo verificar.ps1. Não recolocar no source path do Studio sem resolver a compatibilidade.

### JSON vazio do CEP

A configuração apareceu como HEAD em uma edição do XML. Foi corrigida para GET. A falha ocorreu antes de inserir pedidos/cartão. Há mensagens claras para CEP sem resultado/204 e BrRobotics vazia. O teste agora exige GET e cobre 204.

### CODIGO obrigatório sem geração automática

Inspeção JDBC somente leitura confirmou em ambos os bancos: ROOT.PEDIDOS.CODIGO é NUMERIC(10), NOT NULL, sem IDENTITY e sem DEFAULT. Havia três registros em cada banco naquele momento; isso é histórico, não contagem atual garantida.

Com autorização do usuário, foram alterados:

- `C:\Users\mdspezia\Documents\NetBeansProjects\StarkSystem\src\java\com\starksystem\dao\DAOPedido.java`
- `C:\Users\mdspezia\Documents\NetBeansProjects\BrRobotics\src\java\com\brrobotics\dao\DAOPedido.java`

Agora geram MAX(CODIGO)+1 no fornecedor dentro de transação com LOCK TABLE ROOT.PEDIDOS IN EXCLUSIVE MODE; inserem, fazem commit e devolvem o código; rollback em erro. O bloqueio evita colisão de códigos concorrentes. Nenhuma tabela foi recriada, nenhuma migração foi aplicada e nenhum registro foi apagado. A StarkSystem mantém nome/endereço em maiúsculas.

O usuário confirmou “Funcionou” depois de aplicar essas correções e fazer os passos de Deploy.

### Total fixo R$ 32.000

Última correção funcional, autorizada pelo usuário:

`C:\Users\mdspezia\Documents\NetBeansProjects\StarkSystem\src\java\com\starksystem\ws\StarkSystemService.java`

Foi removido pedido.setTotal(32000.0). O serviço valida total não nulo, finito e não negativo, preserva o total recebido, passa ao DAO e devolve o pedido. Compilou com Java 8. Teste isolado com DAO simulado confirmou preservação de vários totais e rejeição de valores inválidos, sem gravar no banco real.

O simulador SOAP passou a retornar 4590 em vez de 32000. O alerta de divergência em ResultadoPedido permanece útil para discrepâncias futuras.

**No encerramento, o usuário ainda não havia confirmado Clean and Build/Deploy desta última alteração nem a execução real depois dela. Os pedidos antigos com 32000 não foram recalculados.**

### GlassFish DomainResource

O usuário mostrou ClassNotFoundException de org.glassfish.admin.rest.resources.generatedASM.DomainResource e Registered component class cannot be null. Esse stack é da administração REST do GlassFish. A falha de compra apresentada tinha causa SQL no DAO. Não foi alterada a configuração administrativa do servidor.

## 7. Interface HTML atual

Arquivos: `src/main/app/docroot/index.html` e `src/main/app/docroot/estilo.css`.

Novo visual em português, verde, cartões das seis combinações, blocos de entrega e pagamento, layout responsivo, labels e foco visível. CSS local, sem JavaScript ou dependências visuais externas.

Imagens existentes em docroot/images: domestico.jpeg, seguranca.jpeg, medico.jpeg, ReatorArc.png e reatorSolar.jpeg.

Contrato preservado e comparado ao HTML anterior:

- POST /tratarForm.
- Quantidades QRDRA, QRDRS, QRSRA, QRSRS, QRMRA, QRMRS, inicialmente 0.
- Cliente: nome e cep.
- Cartão: bandeira (VISA/MASTERCARD), nomeCartao, numeroCartao, dataVencimento.
- Total de 12 campos nomeados; tipos, limites, required e patterns existentes mantidos. Os dois campos de robô médico já eram texto e continuam assim, com inputmode numérico.
- Submit/reset nativos e instrução de confirmação no console preservados.

O resultado final continua text/plain; não foi criada tela HTML de resultado.

## 8. Testes e artefatos

A partir do workspace:

```powershell
powershell -ExecutionPolicy Bypass -File new_tec/verificar.ps1
```

Código: tools/verificacao/VerificarIntegracao.java. Classes/log em .verification. Portas isoladas: Mule 18185, servidor simulado 18080, Visa 18900, Mastercard 18901, RMI 11999.

Cobertura: descontos/limite de três unidades/agregação, comissão 15%, fluxo HTTP com conectores Mule, frete real, SOAP/REST simulados, códigos/totais, Visa/Mastercard/taxas, entrada inválida na confirmação seguida de SIM, NÃO sem pagamento, formulário inválido, CEP 204, falha de fornecedor.

Consulta WSDL/XSD reais da StarkSystem em 8080 para iniciar o consumidor; exige esse serviço disponível. Não insere pedidos nos bancos reais e não paga nas operadoras reais.

Verificações adicionais da última alteração:

- `.verification/VerificarTotalStark.java`: serviço real com DAO simulado; testa total encaminhado e retornado, sem escrita real.
- `.verification/browser/verificar.cjs`: Playwright Core com Chrome instalado; servidor isolado 18087. Comparou contrato dos campos, imagens, larguras 1440/768/390/360 sem overflow, POST e reset.
- `.verification/desktop.png` e `.verification/mobile.png`: capturas inspecionadas visualmente.
- Pacote temporário playwright-core em .verification/browser/node_modules.

Todos passaram. Isso não equivale a validar uma compra real após o último Deploy.

Consultas reais anteriores confirmaram CEP/WSDL respondendo e portas Derby/Visa/Mastercard acessíveis. RMI recusou conexão inicialmente; depois o usuário informou funcionamento. Não assumir que continua indisponível: verificar o estado atual.

Backups em `new_tec/tools/correcoes-fornecedores/backups/`:

- 20261112-101641: DAOs originais antes da correção.
- 20261112-105204: StarkSystemService.java.bak e index.html.bak antes da última mudança.

Auxiliares: tools/diagnostico/InspecionarBancos.java, cópias corrigidas dos DAOs em tools/correcoes-fornecedores/StarkSystem e BrRobotics, README.md, verificar.ps1.

Documentos antigos podem mencionar o total fixo como pendência: foi resolvido no código na última alteração, aguardando ativação por Deploy caso ainda não realizado.

## 9. Próximas implementações pendentes

O usuário avisou que estoque e nota fiscal em diante ainda não estavam implementados. Continuam pendentes:

1. Estoque/IoT por fornecedor, desconto adicional de 5% quando desconto=1; nenhum quando desconto=0. Endereços do PDF: https://starksystem.vercel.app:443/api e https://brrobotics.vercel.app:443/api. Não há consulta ou desconto de estoque no fluxo atual.
2. Nota fiscal: POST XML UTF-8 em http://127.0.0.1:8903/gerarNotaFiscal; preservar número e URL do PDF.
3. Persistência consolidada no banco NewTec, com código automático. Esse banco não foi inspecionado. Hoje o Pedido existe em memória durante a requisição.
4. Amazon S3 por número do pedido. Bucket e credenciais ainda precisam ser conferidos/fornecidos.
5. E-mail ao cliente com dados do pedido e nota fiscal.
6. Relatório Excel diário com consulta de pedidos e envio ao gerente.
7. Campos adicionais necessários, como email, CPF/CNPJ e telefone, conforme o PDF/serviços.
8. Se solicitado, aderência plena ao cálculo dos descontos nos fornecedores, evitando reaplicar o desconto já calculado no new_tec.

Não implementar tudo automaticamente apenas por constar nesta lista. A próxima conversa deve seguir o escopo pedido pelo usuário.

## 10. Limitações e retomada

- Pedidos dos fornecedores são inseridos antes da confirmação humana. Cancelar evita o pagamento, mas não apaga esses registros; APIs não oferecem estorno/cancelamento.
- Falhas interrompem etapas seguintes e retornam HTTP 500. O aviso sobre registros já inseridos é genérico; o estágio do log determina se houve inserção.
- Não repetir automaticamente pagamento após COMMIT incerto.
- Bancos usam REAL para TOTAL; a integração usa double arredondado em centavos. Não houve migração monetária.
- Exemplos Mule também usam 8085: executar apenas new_tec nessa porta.
- Não editar target/classes como fonte.

Para ativar as últimas alterações:

1. NetBeans: Clean and Build e Deploy da StarkSystem para remover o total fixo em execução.
2. Studio: selecionar new_tec, F5, Project > Clean e executar com Java 8.
3. Navegador: http://127.0.0.1:8085/ e Ctrl+F5 para atualizar o CSS.
4. Enviar pedido, deixar navegador aguardando, conferir resumo no console do processo Mule e responder SIM ou NAO + Enter.

## 11. Texto para iniciar nova conversa

“Leia C:\Users\mdspezia\Downloads\CONTEXTO_CONTINUIDADE_NEWTEC.md e confira os arquivos atuais em C:\Users\mdspezia\AnypointStudio\workspace\new_tec. O documento contém o contexto da implementação anterior. Quero continuar a partir desse estado, mantendo o fluxo preferencialmente linear e sem alterar outros projetos fora do escopo que eu autorizar. Minha próxima tarefa é: [descrever aqui].”
