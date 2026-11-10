# Implementação do Mini-SO

Esta implementação contém o menu principal e os cinco módulos do simulador. A interface usa HTML, CSS e JavaScript, enquanto as simulações são executadas em Java por um servidor HTTP local do próprio JDK.

## Requisitos

- JDK 11 ou superior com `java` e `javac` disponíveis no `PATH`;
- navegador moderno.

## Como executar no Windows

### Opção 1 — liberar scripts somente no terminal atual

Abra o PowerShell na raiz do projeto e execute:

```powershell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
```

Em seguida, inicie o Mini-SO:

```powershell
.\Implementacao\Menu\Executar.ps1
```

Essa liberação vale apenas para a janela atual do PowerShell. Ela não exige permissão de administrador e não modifica permanentemente a política de execução do Windows.

### Opção 2 — executar com liberação em um único comando

Na raiz do projeto, execute:

```powershell
powershell -ExecutionPolicy Bypass -File .\Implementacao\Menu\Executar.ps1
```

Também não é necessário abrir o PowerShell como administrador.

### Acessar e encerrar

Após a compilação, o navegador deverá abrir automaticamente em <http://127.0.0.1:8080/>. Caso isso não aconteça, copie esse endereço e abra-o manualmente no navegador.

Para encerrar o servidor:

- use a opção **Encerrar o Mini-SO** no menu; ou
- pressione `Ctrl+C` no terminal em que o sistema está sendo executado.

> Se aparecer a mensagem de que a execução de scripts foi desabilitada, utilize uma das duas opções acima. Não é necessário alterar a política permanentemente com `LocalMachine` ou `CurrentUser`.

## Execução manual

Também é possível compilar sem o script:

```powershell
New-Item -ItemType Directory -Force .\Implementacao\Menu\build
$fontesModulo1 = Get-ChildItem .\Implementacao\Modulo1 -Filter '*.java' | ForEach-Object FullName
$fontesModulo2 = Get-ChildItem .\Implementacao\Modulo2 -Filter '*.java' | ForEach-Object FullName
$fontesModulo3 = Get-ChildItem .\Implementacao\Modulo3 -Filter '*.java' | ForEach-Object FullName
$fontesModulo4 = Get-ChildItem .\Implementacao\Modulo4 -Filter '*.java' | ForEach-Object FullName
$fontesModulo5 = Get-ChildItem .\Implementacao\Modulo5 -Filter '*.java' | ForEach-Object FullName
javac -encoding UTF-8 -d .\Implementacao\Menu\build .\Implementacao\Menu\MiniSO.java $fontesModulo1 $fontesModulo2 $fontesModulo3 $fontesModulo4 $fontesModulo5
java -cp .\Implementacao\Menu\build miniso.menu.MiniSO "--raiz=$PWD\Implementacao"
```

Argumentos opcionais do servidor:

- `--porta=8081`: utiliza outra porta;
- `--sem-navegador`: não tenta abrir o navegador automaticamente;
- `--raiz=C:\caminho\Implementacao`: informa manualmente a pasta das telas.

## Conteúdo implementado

- Menu responsivo com os cinco módulos planejados, indicação dos módulos disponíveis e orientação de saída;
- produtor/consumidor com buffer circular, dois semáforos contadores e mutex;
- jantar dos cinco filósofos com prevenção de deadlock por ordenação de recursos;
- leitores/escritores com leituras paralelas, escrita exclusiva e lock justo;
- Round-Robin preemptivo com quantum configurável e fila circular;
- escalonamento por prioridades com aging para evitar starvation;
- processos com estados novo, pronto, executando, bloqueado e finalizado, incluindo operações de E/S;
- cálculo de tempo de espera, turnaround, resposta e término por processo, além de TME e TMT;
- tradução de endereços virtuais em físicos com páginas e quadros de 256 bytes;
- tabela de páginas com bit de validade, tratamento de page faults e simulação do disco de swap;
- algoritmos FIFO e LRU com quantidade configurável de quadros físicos;
- comparação visual dos quadros a cada acesso, taxa de page fault e estado final da tabela de páginas;
- grafo direcionado de processos e recursos de instância única com detecção de ciclos;
- Algoritmo do Banqueiro com matrizes Allocation, Max e Need, concessão tentativa e verificação de segurança;
- cenários de solicitação segura/insegura, sequência segura e decisão de aprovação ou negação;
- sistema de arquivos FAT com 32 blocos, encadeamento, EOF e tabela de diretórios;
- bitmap para gerenciamento do espaço livre, criação, leitura, exclusão e reaproveitamento de blocos;
- cenário de disco cheio com rollback atômico e verificação de integridade entre FAT, bitmap e diretório;
- terminais visuais, logs ordenados, linhas do tempo e resumos após as execuções.
