# Trabalho de SO

## Implementar o núcleo do SO

| Módulo | Tópico principal | Tecnologias / Conceitos-chave |
|---|---|---|
| Módulo 1 | Concorrência & Sincronização | Pthreads, Mutex, Semáforos (Buffer Circular, Filósofos) |
| Módulo 2 | Escalonamento de Processos | Round-Robin, Quantum, Métricas de Espera/Turnaround |
| Módulo 3 | Memória Virtual & MMU | Paginação, Tabela de Páginas, Page Fault, FIFO |
| Módulo 4 | Impasses / Deadlocks | Algoritmo do Banqueiro, Estado Seguro, Matriz Need |
| Módulo 5 | Sistema de Arquivos | Tabela FAT, Blocos de Disco, Encadeamento, EOF |

## 1. Visão Geral e Objetivos do Projetos

Este trabalho prático visa consolidar o aprendizado teórico dos conceitos fundamentais de Sistemas Operacionais através da construção de um simulador modular de um núcleo de sistema operacional (Mini-SO). Os alunos deverão projetar e implementar abstrações para concorrência e sincronização de processos, escalonamento de CPU, gerenciamento de memória virtual, prevenção/evitação de impasses (*deadlocks*) e estrutura física de um sistema de arquivos.

## 2. Detalhamento Técnico dos Módulos

### Módulo 1: Concorrência, Região Crítica e Sincronização (Peso: 30%)

**Objetivo:** Implementar controle de acesso concorrente a recursos compartilhados para evitar condições de corrida (*race conditions*) e garantir a integridade dos dados na região crítica.

- **Produtor / Consumidor:** Criar um buffer circular compartilhado limitado. Utilizar dois semáforos contadores (*vazio* e *cheio*) e um mutex de exclusão mútua para coordenar o acesso sem espera ocupada (*busy waiting*).
- **Jantar dos Filósofos:** Simular 5 filósofos/threads concorrentes disputando hashis compartilhados. Aplicar uma estratégia de prevenção de *deadlock* (ex.: assimetria de aquisição ou ordenação de recursos).
- **Leitores e Escritores / Barbeiro Adormecido:** Garantir prioridade adequada para sincronização sem provocar *starvation* de processos em espera.

### Módulo 2: Escalonamento de Processos (Peso: 20%)

**Objetivo:** Simular o escalonador de CPU gerenciando filas de processos nos estados *Pronto*, *Executando* e *Bloqueado*.

- **Algoritmo Round-Robin (Preemptivo):** Implementar fila circular com fatia de tempo (*Quantum*). Tratar a preempção quando o tempo expira.
- **Algoritmo por Prioridades com Envelhecimento (Aging):** Prevenir *starvation* de processos de baixa prioridade aumentando gradualmente sua prioridade à medida que aguardam na fila de prontos.
- **Cálculo de Métricas:** Calcular e exibir ao final da execução o Tempo Médio de Espera (TME) e o Tempo Médio de Turnaround (TMT).

### Módulo 3: Gerenciamento de Memória Virtual e Paginação (Peso: 25%)

**Objetivo:** Construir a simulação de uma MMU para tradução de endereços virtuais em físicos utilizando tabelas de páginas e memória secundária (*swapping*).

- **Tabela de Páginas e Tradução:** Mapear páginas virtuais em molduras de página (*frames*) de memória física.
- **Tratamento de Page Fault:** Detectar páginas não carregadas na memória principal e simular a busca no disco de swap.
- **Algoritmos de Substituição de Páginas:** Implementar os algoritmos FIFO (*First-In, First-Out*) e LRU (*Least Recently Used*) para substituição quando a memória física estiver cheia.

### Módulo 4: Prevenção e Tratamento de Impasses / Deadlocks (Peso: 15%)

**Objetivo:** Modelar a alocação de recursos não-preemptivos e aplicar algoritmos de verificação de estados seguros.

- **Grafo de Alocação de Recursos:** Construir o grafo direcionado de processos e recursos para detecção de ciclos de dependência circular.
- **Algoritmo do Banqueiro (Dijkstra):** Verificar se uma solicitação de recurso mantém o sistema em um *estado seguro* antes de efetuar a alocação.

### Módulo 5: Sistema de Arquivos Simulado (Peso: 10%)

**Objetivo:** Estruturar a organização física de blocos de disco simulados para armazenar arquivos e diretórios.

- **Estrutura de Alocação:** Implementar tabela FAT (*File Allocation Table*) ou i-nodes com blocos de endereçamento direto e indireto.
- **Gerenciamento de Espaço Livre:** Controlar blocos livres por mapa de bits (*bitmap*) ou lista encadeada.

## Prazos e apresentações

- Pode ser em dupla.
- **20/10 – Próxima aula.**
- Apresentação no computador e discussão das decisões de implementação.
- Postar um mini relatório das decisões de projeto tomadas, incluindo:
  - Linguagem de programação utilizada.
  - Possível arquitetura de integração.
  - Aspectos relevantes identificados no desenvolvimento do trabalho.
