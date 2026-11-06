# Trabalho 2: Algoritmo Genético para o Caixeiro Viajante
# Grupo: Pedro Alegria Fiamoncini, Arthur Quintino, Mateus Spezia e Vinicius Oneda

import numpy as np
import matplotlib.pyplot as plt


# ====================================================================
# 1. Carregar as cidades
# ====================================================================
# Carrega as coordenadas das cidades do arquivo Octave
def carregar_cidades(arquivo):
    with open(arquivo, "r") as f:
        linhas = f.readlines()

    x = None
    y = None

    for i, linha in enumerate(linhas):
        # Os valores ficam 4 linhas abaixo do "# name: ..."
        if linha.strip() == "# name: x":
            x = np.array(linhas[i + 4].split(), dtype=float)

        if linha.strip() == "# name: y":
            y = np.array(linhas[i + 4].split(), dtype=float)

    return x, y


# Carrega as coordenadas do arquivo
x, y = carregar_cidades("cidades.mat")

# Identifica a quantidade de cidades
n_cidades = len(x)


# ====================================================================
# 2. Função de custo (aptidão)
# ====================================================================
def calcular_custo_populacao(pop, x, y):
    n_pop, n_cidades = pop.shape

    # Adiciona a primeira cidade ao final do percurso
    tour = np.column_stack((pop, pop[:, 0]))

    # Cria a matriz de distâncias
    dcidade = np.zeros((n_cidades, n_cidades))

    for i in range(n_cidades):
        for j in range(n_cidades):
            # Coordenadas das cidades
            x_i = x[i]
            y_i = y[i]

            x_j = x[j]
            y_j = y[j]

            # Calcula a distância euclidiana
            diferenca_x = x_i - x_j
            diferenca_y = y_i - y_j

            dcidade[i, j] = np.sqrt(
                diferenca_x**2 + diferenca_y**2
            )

    # Calcula o custo de cada indivíduo
    dist = np.zeros(n_pop)

    for i in range(n_pop):
        for j in range(n_cidades):
            cidade_atual = tour[i, j]
            proxima_cidade = tour[i, j + 1]

            dist[i] += dcidade[cidade_atual, proxima_cidade]

    return dist

# Plot do melhor caminho encontrado (caso a animação não funcione)
def plotar_caminho(cromossomo, x, y, titulo="Percurso do Caixeiro Viajante"):
    # Adiciona a cidade inicial ao final do percurso
    caminho = np.append(cromossomo, cromossomo[0])

    # Coordenadas na ordem das cidades visitadas
    x_caminho = x[caminho]
    y_caminho = y[caminho]

    # Cria o gráfico
    plt.figure(figsize=(6, 6))

    # Desenha o percurso
    plt.plot(x_caminho, y_caminho, 'b-o')

    # Identifica as cidades
    for i in range(len(x)):
        plt.text(x[i] + 0.01, y[i] + 0.01, str(i + 1))

    # Configurações do gráfico
    plt.title(titulo)
    plt.xlabel("Coordenada X")
    plt.ylabel("Coordenada Y")
    plt.grid(True)
    plt.axis('equal')

    # (o gráfico aparece no plt.show() do final do arquivo)


# ====================================================================
# 3. População inicial (1ª geração)
# ====================================================================
# Define o tamanho da população
n_pop = 20

# Gera a população inicial aleatoriamente: cada cromossomo é uma permutação das cidades
pop = np.array([
    np.random.permutation(n_cidades)
    for i in range(n_pop)
])

# Calcula o custo de todos os indivíduos
custos = calcular_custo_populacao(pop, x, y)

# Guarda a população inicial para mostrar no final
pop_inicial = pop.copy()
custos_iniciais = custos.copy()

# Identifica o indivíduo com menor custo
indice_melhor = np.argmin(custos)
melhor_cromossomo = pop[indice_melhor]


# ====================================================================
# 4. Ordenar pela aptidão e eliminar a metade pior
# ====================================================================
# Ordena a população pela aptidão (menor custo = mais apto)
ordem = np.argsort(custos)        # índices dos indivíduos, do menor custo para o maior
pop = pop[ordem]                  # reorganiza os cromossomos nessa ordem
custos = custos[ordem]            # reorganiza os custos junto, para continuarem batendo

# Mantém só a metade mais apta (os 10 melhores) e elimina os 10 piores
n_manter = n_pop // 2             # 20 // 2 = 10
pop = pop[:n_manter]              # primeiras 10 linhas = os 10 melhores caminhos
custos = custos[:n_manter]


# ====================================================================
# 5. Seleção pela roleta
# ====================================================================
# Probabilidade de cada sobrevivente ser escolhido como pai
pesos = np.arange(n_manter, 0, -1)          # [10, 9, 8, ..., 1]
prob = pesos / np.sum(pesos)                # divide pela soma (55) para o total dar 1

# Probabilidade acumulada: são as "fatias" da roleta, de 0 até 1
prob_acumulada = np.cumsum(prob)


def roleta(prob_acumulada):
    # Gira a roleta: sorteia um número entre 0 e 1
    r = np.random.rand()
    # Devolve o primeiro indivíduo cuja fatia acumulada passa do número sorteado
    for i in range(len(prob_acumulada)):
        if r <= prob_acumulada[i]:
            return i
    return len(prob_acumulada) - 1          # segurança contra arredondamento


# São precisos 10 filhos, e cada casal gera 2, então são 5 casais
n_filhos = n_pop - n_manter                 # 20 - 10 = 10
n_casais = n_filhos // 2                    # 5


# ====================================================================
# 6. Recombinação: crossover cycle
# ====================================================================
def crossover_cycle(pai1, pai2):
    filho1 = pai1.copy()
    filho2 = pai2.copy()

    # Escolhe um local aleatório dentro do cromossomo
    pos = np.random.randint(len(pai1))

    while True:
        # Passo 1: os dois filhos trocam os números nesse local
        aux = filho1[pos]
        filho1[pos] = filho2[pos]
        filho2[pos] = aux

        # Procura se o número que entrou no filho 1 ficou duplicado em outra posição
        repetidos = np.where(filho1 == filho1[pos])[0]
        repetidos = repetidos[repetidos != pos]

        # Sem número duplicado: terminou
        if len(repetidos) == 0:
            break

        # Passos 2, 3 e 4: repete a troca no local do número duplicado
        pos = repetidos[0]

    return filho1, filho2


# ====================================================================
# 7. Mutação
# ====================================================================
def mutacao(cromossomo):
    # Escolhe aleatoriamente duas posições diferentes e troca as cidades
    i, j = np.random.choice(len(cromossomo), 2, replace=False)
    aux = cromossomo[i]
    cromossomo[i] = cromossomo[j]
    cromossomo[j] = aux
    return cromossomo


# ====================================================================
# 8. Evolução (10.000 gerações)
# ====================================================================
n_iter = 10000
historico_custo = []          # melhor custo de cada geração (para o gráfico)

# "Fotos" da evolução para a animação: (geração, melhor caminho, custo, quantas vezes cada um foi sorteado)
quadros = [(0, melhor_cromossomo.copy(), custos_iniciais.min(), np.zeros(n_manter, dtype=int))]

for geracao in range(1, n_iter + 1):
    # Seleção + crossover: 5 casais sorteados na roleta geram 10 filhos
    filhos = []
    vezes = np.zeros(n_manter, dtype=int)      # quantas vezes cada sobrevivente foi sorteado
    for k in range(n_casais):
        pai = roleta(prob_acumulada)
        mae = roleta(prob_acumulada)
        vezes[pai] += 1
        vezes[mae] += 1
        filho1, filho2 = crossover_cycle(pop[pai], pop[mae])

        # Mutação em cada membro da nova geração (os filhos)
        filhos.append(mutacao(filho1))
        filhos.append(mutacao(filho2))

    # Nova população: os 10 sobreviventes + os 10 filhos
    pop = np.vstack((pop, np.array(filhos)))

    # Avalia e ordena do mais apto ao menos apto
    custos = calcular_custo_populacao(pop, x, y)
    ordem = np.argsort(custos)
    pop_completa = pop[ordem]                  # população inteira (20), guardada para mostrar no final
    custos_completos = custos[ordem]

    # Só os 10 melhores seguem para a próxima geração
    pop = pop_completa[:n_manter]
    custos = custos_completos[:n_manter]

    historico_custo.append(custos[0])

    # Foto para a animação quando o melhor caminho melhora ou a cada 250 gerações
    if custos[0] < quadros[-1][2] or geracao % 250 == 0:
        quadros.append((geracao, pop[0].copy(), custos[0], vezes))



# ====================================================================
# 9. Resultados
# ====================================================================
print("Tamanho da População:", n_pop)

print("\nPopulação Inicial:")
for i in range(n_pop):
    print(pop_inicial[i] + 1, " custo =", round(custos_iniciais[i], 4))

print("\nPopulação Final:")
for i in range(n_pop):
    print(pop_completa[i] + 1, " custo =", round(custos_completos[i], 4))

print("\nNúmero de Cidades:", n_cidades)
print("Melhor Custo:", round(custos[0], 4))
print("Melhor Solução:", pop[0] + 1)


# ====================================================================
# 10. Gráfico: pontos e o melhor caminho encontrado
# ====================================================================
# Se o animacao.py estiver na pasta, mostra a animação (o último quadro é o melhor caminho).
# Se não estiver, mostra o gráfico simples do melhor caminho.
try:
    from animacao import animar
    animacao = animar(x, y, quadros, historico_custo, prob, n_iter)   # guarda numa variável para a animação não parar
except ImportError:
    print("(animacao.py não encontrado na pasta: mostrando só o gráfico do melhor caminho)")
    plotar_caminho(pop[0], x, y, "Melhor caminho encontrado (custo = " + str(round(custos[0], 4)) + ")")

# Abre todos os gráficos de uma vez
plt.show()
