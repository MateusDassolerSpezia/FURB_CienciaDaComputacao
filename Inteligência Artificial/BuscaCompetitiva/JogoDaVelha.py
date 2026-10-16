# INTEGRANTES:
# Arthur Quintino Egert
# Mateus Dassoler Spezia
# Pedro Alegria Fiamoncini
# Vinícius Oneda

# ---------------------------------------------- #

# PARTE 1 #

# JOGO ESCOLHIDO:
'''
Qual jogo o grupo escolheu?
- Jogo da velha
Quais são suas regras?
- O jogo da velha é jogado em um tabuleiro 3x3.
- Dois jogadores se revezam para marcar espaços no tabuleiro, um com "X" e o outro com "O".
- O objetivo do jogo é conseguir alinhar três de seus símbolos (X ou O) em uma linha horizontal, vertical ou diagonal.
- O jogo termina quando um jogador consegue alinhar três de seus símbolos ou quando todos os espaços do tabuleiro estão preenchidos, resultando em empate.
'''

# ESTADO INICIAL:
'''
Como o jogo começa?
- O jogo começa com um tabuleiro vazio, representado por uma matriz 3x3.
Como esse estado pode ser representado?
- O estado inicial pode ser representado por uma matriz 3x3 em Python, onde cada elemento da matriz representa uma célula do tabuleiro. Inicialmente, cada célula guarda o número da sua posição (1 a 9), indicando que está livre.
'''

# JOGADORES:
'''
Quantos jogadores participam?
- O jogo da velha é jogado por dois jogadores.
Quem são MAX e MIN?
- MAX é o jogador que utiliza o símbolo "X" e busca maximizar suas chances de vitória.
- MIN é o jogador que utiliza o símbolo "O" e busca minimizar as chances de vitória do jogador MAX.
'''

# AÇÕES POSSÍVEIS:
'''
Quais ações cada jogador pode executar?
- Cada jogador pode executar a ação de marcar um espaço vazio no tabuleiro com seu símbolo (X ou O).
Existem restrições?
- Sim, um jogador só pode marcar um espaço que esteja vazio. Se o espaço já estiver ocupado por um símbolo, o jogador não pode realizar a ação.
- Não pode marcar um espaço que esteja fora da matriz 3x3 do tabuleiro.
'''

# FUNÇÃO DE TRANSIÇÃO:
'''
Como o estado do jogo muda após uma ação?
- Após um jogador marcar um espaço vazio no tabuleiro, o estado do jogo muda para refletir a nova configuração do tabuleiro com o símbolo do jogador que realizou a ação.
'''

# ESTADO TERMINAL:
'''
Em quais situações o jogo termina?
- O jogo termina quando um jogador consegue alinhar três de seus símbolos (X ou O) em uma linha horizontal, vertical ou diagonal, resultando em vitória para esse jogador.
- O jogo também termina quando todos os espaços do tabuleiro estão preenchidos e nenhum jogador conseguiu alinhar três de seus símbolos, resultando em empate.

Como identificar vitória, derrota ou empate?
- Vitória: Se um jogador conseguir alinhar três de seus símbolos em uma linha horizontal, vertical ou diagonal, ele é declarado vencedor.
- Derrota: Se o outro jogador conseguir alinhar três de seus símbolos antes do jogador atual, o jogador atual perde.
- Empate: Se todos os espaços do tabuleiro estiverem preenchidos e nenhum jogador conseguiu alinhar três de seus símbolos, o jogo termina em empate.
'''

# FUNÇÃO DE UTILIDADE:
'''
Como serão atribuídas pontuações aos resultados do jogo?
- Vitória para o jogador MAX (X): +1 ponto
- Vitória para o jogador MIN (O): -1 ponto
- Empate: 0 pontos
'''

# TIPO DE JOGO:
'''
O jogo é determinístico ou estocástico? Por quê?
- O jogo da velha é determinístico, pois não há elementos de aleatoriedade envolvidos.
'''

# -------------------------------------- #

# PARTE 2 #

# ALGORITMO DE BUSCA COMPETITIVA:
'''
Qual algoritmo será utilizado?
- Minimax. Ele analisa as possíveis jogadas e escolhe a melhor para maximizar as chances de vitória.
'''

'''
Como o jogo será representado em Python?
- O jogo será representado em Python por uma matriz 3×3 (lista de listas), em que cada posição corresponde a uma casa do tabuleiro, numerada de 1 a 9.
- Dois jogadores participam, utilizando os símbolos "X" e "O", alternando suas jogadas. O programa verifica se houve vitória por linha, coluna ou diagonal,
- ou se ocorreu empate (velha). A interação acontece pelo terminal, utilizando input() e print().
- Um dos jogadores é o usuário, que escolhe se joga com X ou com O, e o outro é a IA, que escolhe suas jogadas com o Minimax.
'''

'''
Como o programa identificará as ações válidas e verificará o término do jogo?
- O programa identificará as ações válidas verificando se o jogador digitou um número entre 1 e 9 e se a posição escolhida está livre.
- Caso contrário, solicitará uma nova jogada.
- O término do jogo será verificado pela função verificar_vitoria(), que analisa as linhas, colunas e diagonais para identificar três símbolos iguais. 
- Se todas as casas estiverem ocupadas e ninguém vencer, o jogo terminará em empate (velha).
'''

'''
Como o algoritmo escolherá a melhor jogada considerando as possíveis respostas do adversário?
- Gera as jogadas possíveis: verifica quais casas estão livres.
- Simula cada jogada: testa o que acontece se jogar em cada casa.
- Analisa as respostas do adversário: considera as próximas jogadas possíveis.
- Atribui pontuações: vitória = +1, empate = 0 e derrota = −1.
- Escolhe a melhor jogada: maximiza sua pontuação e considera que o adversário tentará minimizá-la.
'''

'''
O algoritmo escolhido foi adequado?
- Sim. O jogo da velha é determinístico, tem informação perfeita (os dois jogadores veem o tabuleiro inteiro), tem só dois jogadores e é de soma zero (o que um ganha o outro perde). É exatamente o tipo de jogo para o qual o Minimax foi feito.
- Como o tabuleiro é pequeno, a árvore de jogadas também é pequena (no máximo 9! = 362.880 sequências), então o Minimax consegue explorar todas as possibilidades até o fim da partida em pouco tempo, sem precisar de heurística.
- Com isso ele sempre escolhe a jogada ótima: nunca perde, e se os dois lados jogarem bem a partida termina em velha.
Quais foram suas limitações?
- Explora a árvore inteira, inclusive caminhos que não mudariam a decisão final. Por isso faz muito cálculo desnecessário, principalmente na primeira jogada, quando o tabuleiro está vazio.
- Calcula várias vezes o mesmo tabuleiro quando ele aparece por ordens de jogada diferentes, porque não guarda os resultados que já calculou.
- Não diferencia vencer rápido de vencer devagar: uma vitória na próxima jogada e uma vitória daqui a 5 jogadas valem os dois +1. Isso pode fazer a IA demorar para fechar o jogo.
- Supõe que o adversário sempre joga perfeitamente, então não aproveita os erros de um jogador humano.
- Não escala para jogos maiores, como xadrez ou damas: a quantidade de jogadas possíveis cresce tão rápido que fica inviável explorar a árvore até o fim. Nesse caso seria preciso limitar a profundidade e usar uma heurística, e aí o resultado deixa de ser garantidamente ótimo.
'''

'''
Outro algoritmo de busca competitiva poderia apresentar melhores resultados? Por quê?
- Em qualidade de jogada, não. O Minimax já explora a árvore inteira e escolhe sempre a jogada ótima, então nenhum outro algoritmo consegue jogar melhor que ele no jogo da velha.
- Em desempenho, sim. A poda Alfa-Beta chega exatamente na mesma jogada que o Minimax, mas analisa bem menos tabuleiros. Ela para de explorar um caminho assim que percebe que ele não vai mudar a decisão, porque o adversário nunca deixaria a partida chegar ali. Com isso resolve a primeira limitação que citamos (o cálculo desnecessário).
- O Minimax também pode ser melhorado com memorização (tabela de transposição): guardar o resultado de cada tabuleiro já calculado e reaproveitar quando ele aparecer de novo por outra ordem de jogadas. Isso resolve a segunda limitação.
- Para a IA fechar o jogo mais rápido, dá para descontar a profundidade na pontuação (por exemplo, vitória = 10 - profundidade). Assim uma vitória na próxima jogada vale mais que uma vitória daqui a 5 jogadas.
- Algoritmos como o Expectiminimax não fazem sentido aqui, porque ele serve para jogos com sorte (dados, cartas), e o jogo da velha é determinístico.
- O Monte Carlo Tree Search (MCTS) é útil em jogos enormes como o Go, onde não dá para explorar a árvore toda. No jogo da velha ele seria pior, porque trabalha com simulações aleatórias e não garante a jogada ótima, enquanto o Minimax garante.
- Conclusão: a melhor opção para o jogo da velha seria o Minimax com poda Alfa-Beta, que dá o mesmo resultado ótimo com muito menos processamento.
'''

# -------------------------------------- #

# CÓDIGO:

# Tabuleiro inicial com as posições numeradas
tabuleiro = [
    ["1", "2", "3"],
    ["4", "5", "6"],
    ["7", "8", "9"]
]

# Função que imprime o tabuleiro
def imprimir_tabuleiro(tabuleiro):
    print()
    for i, linha in enumerate(tabuleiro):
        print(" " + " | ".join(linha))
        if i < 2:
            print("-" * 11)
    print()

# Função para verificar se alguém ganhou ou se deu velha
# Retorna "X" ou "O" (vencedor), "Velha" (empate) ou None (o jogo continua)
def verificar_vitoria(tabuleiro):
    for simbolo in ("X", "O"):
        for i in range(3):
            if all(tabuleiro[i][j] == simbolo for j in range(3)):  # linha
                return simbolo
            if all(tabuleiro[j][i] == simbolo for j in range(3)):  # coluna
                return simbolo
        if all(tabuleiro[i][i] == simbolo for i in range(3)):  # diagonal principal
            return simbolo
        if all(tabuleiro[i][2 - i] == simbolo for i in range(3)):  # diagonal secundária
            return simbolo

    if all(celula in ("X", "O") for linha in tabuleiro for celula in linha):
        return "Velha"

    return None

# Ações possíveis: as casas livres (que ainda guardam o número da posição)
def acoes_possiveis(tabuleiro):
    return [(linha, coluna) for linha in range(3) for coluna in range(3)
            if tabuleiro[linha][coluna] not in ("X", "O")]

# Função de utilidade: vitória do MAX (X) = +1, vitória do MIN (O) = -1, velha = 0
def utilidade(resultado):
    if resultado == "X":
        return 1
    if resultado == "O":
        return -1
    return 0

# Minimax: devolve o valor do tabuleiro supondo que os dois jogadores jogam perfeitamente
def minimax(tabuleiro, vez_do_max):
    resultado = verificar_vitoria(tabuleiro)
    if resultado:  # estado terminal
        return utilidade(resultado)

    if vez_do_max:
        melhor = float("-inf")
        for linha, coluna in acoes_possiveis(tabuleiro):
            original = tabuleiro[linha][coluna]
            tabuleiro[linha][coluna] = "X"        # simula a jogada do MAX
            valor = minimax(tabuleiro, False)     # agora o MIN responde
            tabuleiro[linha][coluna] = original   # desfaz a jogada
            melhor = max(melhor, valor)
        return melhor
    else:
        melhor = float("inf")
        for linha, coluna in acoes_possiveis(tabuleiro):
            original = tabuleiro[linha][coluna]
            tabuleiro[linha][coluna] = "O"        # simula a jogada do MIN
            valor = minimax(tabuleiro, True)      # agora o MAX responde
            tabuleiro[linha][coluna] = original   # desfaz a jogada
            melhor = min(melhor, valor)
        return melhor

# Testa cada casa livre com o minimax e escolhe a melhor para a IA
# Se a IA é X (MAX) pega o maior valor, se é O (MIN) pega o menor
def melhor_jogada(tabuleiro, simbolo):
    melhor_valor = None
    melhor_casa = None
    for linha, coluna in acoes_possiveis(tabuleiro):
        original = tabuleiro[linha][coluna]
        tabuleiro[linha][coluna] = simbolo
        valor = minimax(tabuleiro, simbolo == "O")  # depois da IA, joga o adversário
        tabuleiro[linha][coluna] = original
        if (melhor_casa is None
                or (simbolo == "X" and valor > melhor_valor)
                or (simbolo == "O" and valor < melhor_valor)):
            melhor_valor = valor
            melhor_casa = (linha, coluna)
    return melhor_casa

# Função para o jogador humano escolher sua jogada
def solicitar_jogada(simbolo):
    while True:
        entrada = input(f"Sua vez ({simbolo}), escolha uma casa de 1 a 9: ")
        if not entrada.isdigit() or not 1 <= int(entrada) <= 9:
            print("Digite um número de 1 a 9.")
            continue
        casa = int(entrada) - 1
        linha, coluna = casa // 3, casa % 3
        if tabuleiro[linha][coluna] in ("X", "O"):
            print("Essa casa já está ocupada. Escolha outra.")
            continue
        tabuleiro[linha][coluna] = simbolo
        return

# Programa main
humano = ""
while humano not in ("X", "O"):
    humano = input("Você quer jogar com X (começa) ou O? ").strip().upper()
ia = "O" if humano == "X" else "X"

imprimir_tabuleiro(tabuleiro)
vez = "X"
while True:
    if vez == humano:
        solicitar_jogada(humano)
    else:
        print(f"IA ({ia}) pensando...")
        linha, coluna = melhor_jogada(tabuleiro, ia)
        tabuleiro[linha][coluna] = ia
        print(f"IA ({ia}) jogou na casa {linha * 3 + coluna + 1}.")
    imprimir_tabuleiro(tabuleiro)
    resultado = verificar_vitoria(tabuleiro)
    if resultado == "Velha":
        print("Velha!")
        break
    if resultado:
        print("Você venceu!" if resultado == humano else "A IA venceu!")
        break
    vez = "O" if vez == "X" else "X"