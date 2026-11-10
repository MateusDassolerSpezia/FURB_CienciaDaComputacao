# Changelog

Todas as alterações relevantes do projeto serão registradas neste arquivo.

## 2026-10-18 — Módulo 5: Sistema de Arquivos FAT

### Adicionado

- Tela responsiva do Módulo 5 em `Implementacao/Modulo5`, concluindo os cinco módulos executáveis do Mini-SO.
- Disco simulado com 32 blocos de 64 bytes, totalizando 2 KB, dos quais dois blocos são reservados para metadados.
- Tabela FAT com entradas livres, reservadas, ponteiros para o próximo bloco e marcador `EOF`.
- Bitmap de 32 posições para gerenciamento e visualização do espaço livre e ocupado.
- Tabela de diretórios com caminho, tipo, tamanho, primeiro bloco e cadeia FAT de cada arquivo.
- Operações de criação de diretórios, criação de arquivos, leitura por encadeamento e exclusão com liberação de blocos.
- Alocador first-fit capaz de reaproveitar blocos liberados e produzir cadeias não contíguas quando o disco está fragmentado.
- Alocação atômica: todos os blocos necessários são localizados antes que a FAT seja modificada.
- Cenário de uso cotidiano com criação, exclusão, reaproveitamento e leitura de arquivos.
- Cenário de disco cheio com fragmentação, falha controlada e rollback sem alocação parcial.
- Verificação interna semelhante a `fsck`, comparando FAT, bitmap, proprietários, cadeias e tamanhos dos arquivos.
- Visualizações do mapa de blocos, bitmap, diretório, FAT e log de operações.
- Endpoint `POST /api/modulo5` para executar os cenários e retornar o estado completo do disco em JSON.

### Atualizado

- Menu principal com o Módulo 5 disponível e contador alterado para cinco módulos disponíveis.
- Texto do menu atualizado para indicar que o núcleo completo está pronto para execução.
- Servidor `MiniSO.java` com rotas da interface e API do Módulo 5.
- Script `Executar.ps1` para compilar automaticamente todos os cinco módulos.
- `Implementacao/README.md` com o sistema FAT e o comando de compilação manual completo.
- Validação inicial do servidor ampliada para exigir as telas dos cinco módulos.

### Decisões técnicas

- A FAT utiliza os valores internos `LIVRE`, `RESERVADO` e `EOF`, além do índice do próximo bloco para cadeias em andamento.
- Diretórios são mantidos na tabela de metadados e não consomem blocos de conteúdo nesta simulação simplificada.
- A quantidade de blocos de um arquivo é calculada por arredondamento superior de `tamanho ÷ 64`.
- A busca first-fit evidencia o reaproveitamento e permite demonstrar fragmentação externa das cadeias.
- Falhas por falta de espaço são detectadas antes da escrita, preservando FAT, bitmap e diretório.

### Validação realizada

- Compilação conjunta do Menu e dos cinco módulos concluída sem erros.
- Rotas HTML, CSS e JavaScript do Módulo 5 verificadas via HTTP com código `200` e tipos de conteúdo corretos.
- Cinco execuções consecutivas de cada cenário concluídas com integridade confirmada.
- No cenário de reuso, `/backup.bin` recebeu a cadeia `5 → 6 → 7 → 8 → 11 → EOF`, reutilizando quatro blocos liberados.
- O arquivo excluído deixou de aparecer no diretório e seus blocos foram corretamente reaproveitados.
- No cenário de lotação, 26 blocos permaneceram ocupados e seis livres.
- A tentativa de criar `/dados/grande.bin`, que precisava de 16 blocos, falhou sem criar entrada ou ocupar parcialmente os seis blocos restantes.
- Todas as cadeias apresentaram a quantidade de blocos compatível com o tamanho do arquivo e terminaram em `EOF`.
- FAT, bitmap e proprietários foram comparados bloco a bloco sem divergências.

## 2026-10-18 — Módulo 4: Impasses e Deadlocks

### Adicionado

- Tela responsiva do Módulo 4 em `Implementacao/Modulo4`, com experimentos de prevenção e detecção de deadlocks.
- Algoritmo do Banqueiro de Dijkstra com cinco processos, três tipos de recurso e concessão tentativa antes da decisão final.
- Matrizes `Allocation`, `Max` e `Need`, calculando cada necessidade por `Need = Max − Allocation`.
- Verificação de segurança por vetores `Work` e `Finish`, com liberação progressiva dos recursos dos processos que podem terminar.
- Cenário seguro em que `P1` solicita `[1, 0, 2]` e a concessão é aprovada.
- Cenário inseguro em que `P4` solicita `[3, 3, 0]` e a concessão é negada antes de afetar o estado real.
- Exibição visual da decisão, vetor Available tentativo, sequência segura e processos impedidos.
- Grafo direcionado de alocação de recursos com processos, recursos de instância única, arestas de solicitação e arestas de alocação.
- Detecção de ciclos por busca em profundidade com marcação dos nós em visita.
- Cenários de grafo com e sem dependência circular, incluindo representação SVG e destaque do ciclo de deadlock.
- Log detalhado dos passos do Banqueiro e das arestas analisadas no grafo.
- Endpoint `POST /api/modulo4` para executar os quatro cenários e retornar os resultados em JSON.

### Atualizado

- Menu principal com o Módulo 4 marcado como disponível e contador alterado para quatro módulos disponíveis.
- Servidor `MiniSO.java` com rotas da interface e API do Módulo 4.
- Script `Executar.ps1` para compilar automaticamente os módulos 1, 2, 3 e 4.
- `Implementacao/README.md` com os recursos do Módulo 4 e o comando de compilação manual atualizado.
- Validação inicial do servidor ampliada para exigir os arquivos da tela do Módulo 4.

### Decisões técnicas

- O estado do Banqueiro é baseado no conjunto clássico de cinco processos e três recursos, facilitando a conferência acadêmica das matrizes.
- Solicitações válidas são aplicadas primeiro em uma cópia lógica do estado; apenas estados seguros são considerados aprovados.
- As matrizes exibidas representam o estado tentativo avaliado, permitindo observar por que a concessão insegura foi negada.
- Nos grafos, cada recurso possui uma única instância; portanto, a presença de ciclo é suficiente para confirmar deadlock.
- Uma nova instância de cada algoritmo é criada por requisição, mantendo os experimentos independentes.

### Validação realizada

- Compilação conjunta do Menu e dos módulos 1, 2, 3 e 4 concluída sem erros.
- Rotas HTML, CSS e JavaScript do Módulo 4 verificadas via HTTP com código `200` e tipos de conteúdo corretos.
- Cinco execuções consecutivas de cada um dos quatro cenários concluídas com resultados determinísticos.
- A solicitação segura foi aprovada com a sequência `P1 → P3 → P4 → P0 → P2`.
- A solicitação insegura foi negada com `P0`, `P1`, `P2`, `P3` e `P4` impedidos no estado tentativo.
- Todas as células da matriz Need foram comparadas com `Max − Allocation`.
- O ciclo `P1 → R2 → P2 → R1 → P1` foi detectado corretamente no cenário com deadlock.
- O cenário acíclico terminou sem falso positivo de deadlock.

## 2026-10-18 — Módulo 3: Memória Virtual e MMU

### Adicionado

- Tela responsiva do Módulo 3 em `Implementacao/Modulo3`, integrada ao servidor Java e acessível pelo menu principal.
- MMU simulada com espaço virtual de 2 KB, oito páginas de 256 bytes e memória física configurável entre dois e seis quadros.
- Tradução de 16 endereços virtuais em página, deslocamento, quadro e endereço físico.
- Tabela de páginas com número do quadro, bit de validade e indicação de residência na memória física ou no disco de swap.
- Tratamento de page faults com operações explícitas de `swap-in` e `swap-out`.
- Algoritmo FIFO, que substitui a página carregada há mais tempo na memória.
- Algoritmo LRU, que substitui a página usada menos recentemente.
- Histórico visual dos quadros após cada acesso, destacando hits, page faults e o quadro acessado.
- Resumo com hits, page faults, substituições, taxa de page fault, quantidade de quadros e tamanho da página.
- Log detalhado das consultas à tabela, substituições e fórmulas de tradução de cada endereço.
- Endpoint `POST /api/modulo3` para executar FIFO ou LRU e retornar os resultados em JSON.

### Atualizado

- Menu principal com o Módulo 3 marcado como disponível e contador alterado para três módulos disponíveis.
- Servidor `MiniSO.java` com rotas da interface e API do Módulo 3.
- Script `Executar.ps1` para compilar automaticamente os módulos 1, 2 e 3.
- `Implementacao/README.md` com o conteúdo do Módulo 3 e o comando de compilação manual atualizado.
- Validação inicial do servidor ampliada para exigir os arquivos da tela do Módulo 3.

### Decisões técnicas

- Página e quadro possuem o mesmo tamanho de 256 bytes; o endereço físico é calculado por `quadro × 256 + deslocamento`.
- A sequência de referências é fixa, percorre as oito páginas virtuais e produz decisões diferentes entre FIFO e LRU na configuração padrão.
- O FIFO registra o instante de carregamento de cada página, enquanto o LRU atualiza o instante do último uso a cada hit ou page fault.
- Páginas substituídas têm sua entrada invalidada antes do novo mapeamento, mantendo a tabela consistente com a memória física.
- Cada requisição cria uma MMU nova, evitando compartilhamento de estado entre execuções.

### Validação realizada

- Compilação conjunta do Menu e dos módulos 1, 2 e 3 concluída sem erros.
- Rotas HTML, CSS e JavaScript do Módulo 3 verificadas via HTTP com código `200` e tipos de conteúdo corretos.
- Cinco execuções consecutivas de FIFO e cinco de LRU concluídas com três quadros físicos.
- FIFO resultou em 3 hits, 13 page faults e 10 substituições na carga padrão.
- LRU resultou em 4 hits, 12 page faults e 9 substituições na carga padrão.
- Nos 16 acessos, foi confirmado que `hits + page faults = acessos` e que o deslocamento virtual foi preservado no endereço físico.
- A tabela de páginas final foi comparada com o conteúdo dos quadros e permaneceu consistente.
- Algoritmos desconhecidos e quantidades de quadros fora do intervalo permitido foram rejeitados com HTTP `400`.

## 2026-10-18 — Módulo 2: Escalonamento de Processos

### Adicionado

- Tela responsiva do Módulo 2 em `Implementacao/Modulo2`, com seleção do algoritmo, quantum configurável, carga de processos documentada e execução integrada ao núcleo Java.
- Simulador discreto de processos nos estados `NOVO`, `PRONTO`, `EXECUTANDO`, `BLOQUEADO` e `FINALIZADO`.
- Escalonamento Round-Robin preemptivo com fila circular e quantum configurável entre 1 e 10 unidades de tempo.
- Escalonamento não preemptivo por prioridades com aging a cada três unidades de espera, reduzindo o risco de starvation.
- Operações simuladas de E/S, com processos deixando a CPU, permanecendo bloqueados e retornando à fila de prontos.
- Linha do tempo da CPU no formato de gráfico de Gantt, incluindo períodos ociosos quando aplicável.
- Métricas individuais de chegada, CPU, prioridade, espera, turnaround, resposta e término.
- Cálculo e exibição do Tempo Médio de Espera (TME), Tempo Médio de Turnaround (TMT) e tempo médio de resposta.
- Registro textual das chegadas, despachos, preempções, bloqueios, desbloqueios e finalizações.
- Endpoint `POST /api/modulo2` no servidor local para executar os algoritmos e entregar os resultados em JSON.

### Atualizado

- Menu principal com o Módulo 2 marcado como disponível e acesso direto à sua tela.
- Contador do menu alterado de um para dois módulos disponíveis.
- Script `Executar.ps1` atualizado para compilar automaticamente os fontes Java dos módulos 1 e 2.
- `Implementacao/README.md` atualizado com o conteúdo do Módulo 2 e o comando de compilação manual correspondente.
- Validação inicial do servidor ampliada para exigir também os arquivos da tela do Módulo 2.

### Decisões técnicas

- Os dois algoritmos usam a mesma carga determinística de quatro processos, permitindo comparar resultados de forma reproduzível.
- A prioridade numérica menor representa maior prioridade; o aging diminui esse número conforme o tempo acumulado na fila de prontos.
- O tempo de espera conta apenas unidades passadas no estado `PRONTO`; turnaround inclui CPU, espera e E/S.
- Uma nova instância do escalonador é criada para cada requisição, mantendo as execuções independentes.

### Validação realizada

- Compilação conjunta do Menu, Módulo 1 e Módulo 2 concluída sem erros.
- Rotas HTML, CSS e JavaScript do Módulo 2 verificadas via HTTP com código `200` e tipos de conteúdo corretos.
- Cinco execuções consecutivas de cada algoritmo concluídas com quatro processos finalizados e métricas válidas.
- Round-Robin com quantum 2 resultou em TME de 10,50 e TMT de 17,25 unidades de tempo.
- Prioridades com aging resultou em TME de 7,50 e TMT de 14,25 unidades de tempo.
- Parâmetros inválidos de algoritmo e quantum foram rejeitados corretamente com HTTP `400`.

## 2026-10-18 — Menu e Módulo 1

### Adicionado

- Menu principal responsivo em HTML, CSS e JavaScript dentro de `Implementacao/Menu`, com acesso ao Módulo 1, visualização dos módulos futuros e uma ação para encerrar o servidor com segurança.
- Servidor HTTP local `MiniSO.java`, baseado apenas em recursos do JDK, para servir as telas e integrar a interface ao código Java.
- Script PowerShell `Executar.ps1` para compilar e iniciar o projeto.
- Tela interativa do Módulo 1 dentro de `Implementacao/Modulo1`, com seleção dos experimentos, estado da execução, terminal de eventos e resumo dos resultados.
- Simulação produtor/consumidor com buffer circular limitado, dois produtores, dois consumidores, semáforos de posições vazias/cheias e mutex justo.
- Simulação do jantar dos cinco filósofos com locks justos e ordenação global dos hashis para prevenir deadlock.
- Simulação de leitores/escritores com `ReentrantReadWriteLock` justo, permitindo leituras simultâneas e garantindo exclusividade aos escritores.
- Registro de eventos thread-safe e validações do estado final de cada experimento.
- Documentação de requisitos, execução e arquitetura em `Implementacao/README.md`.

### Decisões técnicas

- Foi usado o servidor `HttpServer` do JDK para evitar frameworks e dependências externas.
- Cada requisição cria uma nova instância da simulação, impedindo que o estado de uma execução contamine a seguinte.
- Os mecanismos de bloqueio substituem espera ocupada; a justiça dos locks e semáforos reduz o risco de starvation.
- A interface foi organizada para funcionar em telas grandes e pequenas e respeitar a preferência de redução de movimento do navegador.

### Validação realizada

- Compilação de todas as classes com `javac -encoding UTF-8` concluída sem erros.
- Rotas do menu, Módulo 1, CSS, JavaScript e status verificadas via HTTP com código `200` e tipos de conteúdo corretos.
- Cinco execuções consecutivas de cada experimento concluídas durante o teste de concorrência.
- Produtor/consumidor terminou com 12 itens produzidos, 12 consumidos e buffer vazio.
- Todos os cinco filósofos concluíram três refeições, sem deadlock.
- Leitores/escritores concluiu nove leituras e seis escritas, com acesso obtido por todas as threads.
- A ação de encerramento do menu foi verificada e finalizou o servidor local.
