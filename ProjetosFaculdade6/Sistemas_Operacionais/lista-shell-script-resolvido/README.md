# Lista de exercícios – Shell Script

Scripts desenvolvidos para execução no **GitHub Codespaces**, a partir da raiz do projeto `lista-shell-script`. O diretório `entrada/` contém arquivos pequenos para testes, `saida/` recebe os resultados e `scripts/` contém os exercícios. Todos os scripts começam com `#!/bin/bash`.

## Como executar

Abra o terminal na pasta principal do projeto. Os scripts já estão marcados como executáveis; também é possível usar `bash scripts/ex01.sh ...` sem depender dessa permissão.

| Exercício | Comando para testar |
|---|---|
| 1 | `./scripts/ex01.sh entrada/arquivo.txt` |
| 2 | `./scripts/ex02.sh Linux entrada/arquivo.txt` |
| 3 | `./scripts/ex03.sh entrada/incerteza.txt` |
| 4 | `./scripts/ex04.sh` |
| 5 | `./scripts/ex05.sh entrada/poemas.tar` |
| 6 | `./scripts/ex06.sh entrada/latin1.txt` |
| 7 | `./scripts/ex07.sh entrada 1` (ou `0`, sem log) |
| 8 | `./scripts/ex08.sh` e, para testar como root, `sudo ./scripts/ex08.sh` se permitido |
| 9 | `./scripts/ex09.sh` |
| 10 | `./scripts/ex10.sh` (encerre com **Ctrl+C**) |

## Explicação de cada exercício

### 1. Contar palavras

O primeiro argumento (`$1`) informa o arquivo. `-f` verifica se ele existe. `cat` lê seu conteúdo e o laço `for` percorre cada palavra separada por espaços, tabs ou quebras de linha; o contador aumenta a cada repetição. Por fim, `echo` mostra a contagem. O arquivo de exemplo contém **16 palavras**. O script mantém a estrutura já implementada para este exercício.

**Para apresentar:** destacar os parâmetros, o teste do arquivo e a variável `contador` alterada no laço `for`.

### 2. Remover uma palavra

Recebe a palavra (`$1`) e o arquivo (`$2`). `sed` substitui ocorrências da palavra por texto vazio. A saída é redirecionada com `>` para `saida/sem_palavra.txt`, preservando o original. O padrão delimita a palavra para não retirar parte de outras palavras. Este exemplo pressupõe uma palavra comum sem caracteres especiais de expressão regular.

**Para apresentar:** comparar `entrada/arquivo.txt` com `saida/sem_palavra.txt` após remover `Linux`.

### 3. Mostrar linha com “incerteza”

Se nenhum nome for recebido, imprime exatamente `Erro, arquivo faltando`. Se o arquivo existir, `grep "incerteza"` mostra as linhas que contêm essa sequência (com distinção entre maiúsculas e minúsculas).

**Para apresentar:** executar primeiro sem argumento e depois com `entrada/incerteza.txt`.

### 4. Verificar dia de pagamento

`date +%d` consulta o dia atual do mês. O `if` verifica se é dia 10 e escolhe uma das mensagens do enunciado. Não recebe parâmetros.

**Para apresentar:** explicar que o resultado depende da data configurada no Codespaces.

### 5. Extrair e montar poema

O arquivo `.tar` é recebido em `$1` e extraído em uma pasta temporária por `tar -xf`. Para cada palavra, na ordem `incerta`, `ódio` e `ilude`, `find` e `grep` procuram a primeira linha correspondente nos arquivos `.txt`. `echo >>` adiciona uma linha abaixo da anterior em `saida/poema_final.txt`. `gzip -c` gera também `saida/poema_final.txt.gz`. `trap` limpa a pasta temporária no fim.

**Para apresentar:** mostrar a entrada compactada, o arquivo de três linhas e a versão `.gz`. O `.tar` de exemplo foi criado apenas para teste; quando fornecido o `.tar` da atividade, use-o como parâmetro. Se alguma frase não for localizada, o script avisa.

### 6. Converter codificação

Recebe o arquivo em `$1`. `file --mime-encoding` procura identificar conteúdo binário e, se for o caso, interrompe a execução. `iconv` converte o texto de **ISO-8859-1** para **UTF-8**. O `if` testa o retorno do `iconv`. Depois, `read` pergunta se o original deve ser apagado; outro `if` verifica se `rm` funcionou.

**Para apresentar:** demonstrar com `entrada/latin1.txt`. Responder `n` mantém o original; responder `s` apaga o original e exigirá recriar o arquivo para repetir o teste. O teste de arquivo binário com `file` é simples e depende da detecção realizada pelo utilitário.

### 7. Backup com log opcional

O primeiro argumento indica a pasta a salvar e o segundo escolhe `1` (gerar log) ou `0` (não gerar log). `mkdir -p` cria `$HOME/backup`. `date` compõe o nome `backup-dd-mm-aaaa.tar.bz2`; `tar -cjf` arquiva e comprime recursivamente, preservando as permissões registradas no tar. Se a opção for `1`, o bloco `{ ... }` escreve em `$HOME/backup/logBKP` as datas, horários, caminhos completos dos arquivos e diretório do backup.

**Para apresentar:** rodar `./scripts/ex07.sh entrada 1`, conferir `$HOME/backup/` e abrir `logBKP`. Fazer o segundo teste com `0`. Backups no mesmo dia sobrescrevem o arquivo de mesmo nome.

### 8. Cumprimentar root

`id -u` retorna o identificador numérico do usuário; `0` representa root. Caso seja root, `date` mostra horário e data, e `free -h` com `awk` exibe memória livre e em uso. Caso contrário, mostra a mensagem orientando usar root ou `sudo`.

**Para apresentar:** executar normalmente; no Codespaces, `sudo` pode estar disponível, mas a atividade não exige mudar configurações do ambiente.

### 9. Cumprimentar conforme o período

`date +%H` obtém a hora de 0 a 23. O script exibe **Bom dia** antes das 12h, **Boa tarde** das 12h às 17h59 e **Boa noite** a partir das 18h, segundo o relógio do ambiente.

**Para apresentar:** explicar as três condições `if`, `elif` e `else`.

### 10. Avisar quando a CPU passar de 50%

Um `while true` monitora continuamente o uso total da CPU. O script lê duas amostras dos contadores do Linux em `/proc/stat`, separadas por `sleep 1`, e calcula a porcentagem utilizada por diferença entre os totais e os tempos ociosos. Quando for **maior que 50%**, mostra um aviso no terminal.

**Para apresentar:** executar e interromper com **Ctrl+C**. Se não ultrapassar o limite durante o teste, não aparecerá alerta; isso é esperado. Trata-se da CPU total, não da utilização de um processo específico.

## Observações

- Execute os comandos a partir da **pasta principal** do projeto, para que os caminhos `entrada/` e `saida/` funcionem.
- O exercício 5 usa um `.tar` de demonstração porque o PDF contém apenas o enunciado, não o `poemas.tar` original.
- No exercício 7, o diretório de destino é **`$HOME/backup/`**, conforme a instrução final do enunciado, e não o caminho meramente ilustrativo `/mnt/backup/`.
- Para mostrar o conteúdo de um arquivo de saída, use por exemplo `cat saida/poema_final.txt`.
