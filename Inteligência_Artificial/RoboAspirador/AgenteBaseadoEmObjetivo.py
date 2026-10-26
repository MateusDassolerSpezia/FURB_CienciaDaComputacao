import numpy as np
import matplotlib.pyplot as plt
import random
from matplotlib.colors import ListedColormap

# Aluno: Mateus Dassoler Spezia - 19/09/2026


# Posição inicial do robô
linha = 1
coluna = 1


class Ambiente:

    def __init__(self):

        # Matriz 6x6
        # As posições 1 até 4 representam a sala 4x4
        self.matriz = np.zeros((6, 6), dtype=int)

        # Paredes
        self.matriz[0, :] = 1
        self.matriz[5, :] = 1
        self.matriz[:, 0] = 1
        self.matriz[:, 5] = 1

        # Coloca a sujeira
        self.colocar_sujeira()

        # Cria a janela apenas uma vez
        plt.ion()
        self.fig, self.ax = plt.subplots()


    def colocar_sujeira(self):

        # Quantidade aleatória de sujeira
        quantidade = random.randint(1, 16)

        # Posições internas da sala
        posicoes = [
            (x, y)
            for x in range(1, 5)
            for y in range(1, 5)
        ]

        # Escolhe posições sem repetição
        sujeira = random.sample(posicoes, quantidade)

        for x, y in sujeira:
            self.matriz[x, y] = 2


    def imprimir(self):

        print(self.matriz)


    def visualizar(self):

        # 0 = branco (limpo)
        # 1 = cinza (parede)
        # 2 = preto (sujeira)

        cores = [
            "white",
            "gray",
            "black"
        ]

        cmap = ListedColormap(cores)

        # Limpa o gráfico anterior
        self.ax.clear()

        self.ax.imshow(
            self.matriz,
            cmap=cmap,
            vmin=0,
            vmax=2
        )

        # Coloca as linhas no perímetro das células
        self.ax.set_xticks(
            np.arange(-0.5, 6, 1),
            minor=True
        )

        self.ax.set_yticks(
            np.arange(-0.5, 6, 1),
            minor=True
        )

        self.ax.grid(
            which="minor",
            color="lightgray",
            linestyle="-",
            linewidth=1
        )

        # Remove os ticks menores
        self.ax.tick_params(
            which="minor",
            bottom=False,
            left=False
        )

        # Mantém os números 0 até 5
        self.ax.set_xticks(range(6))
        self.ax.set_yticks(range(6))

        # Coloca o agente no ambiente
        self.ax.plot(
            [coluna],
            [linha],
            marker='o',
            color='r',
            ls='',
            markersize=30
        )

        # Mostra o valor de cada posição dentro do quadrado
        for x in range(6):

            for y in range(6):

                valor = self.matriz[x, y]

                # O texto branco fica visível sobre a sujeira preta
                cor_texto = "white" if valor == 2 else "black"

                self.ax.text(
                    y,
                    x,
                    str(valor),
                    ha="center",
                    va="center",
                    color=cor_texto,
                    fontweight="bold",
                    zorder=3
                )

        # Atualiza a janela
        self.fig.canvas.draw()
        self.fig.canvas.flush_events()

        # Tempo entre cada movimento
        plt.pause(0.5)


# Verifica se o objetivo ainda não foi atingido.
# Retorna 1 quando existe sujeira e 0 quando a sala está limpa.
def checkObj(sala):

    if 2 in sala:

        return 1

    return 0


class Robo:

    def __init__(self):

        self.sala_percebida = None
        self.rota = []
        self.plano = []


    def perceberSala(self, sala, posicao_inicial):

        # O agente recebe uma cópia do estado completo da sala antes
        # de agir e usa essa percepção para produzir o melhor plano.
        self.sala_percebida = sala.copy()
        self.plano = self.planejarMelhorRota(posicao_inicial)


    def planejarMelhorRota(self, posicao_inicial):

        sujeiras = []

        # Procura as sujeiras percorrendo a sala linha por linha.
        for linha in range(1, 5):

            for coluna in range(1, 5):

                if self.sala_percebida[linha, coluna] == 2:

                    sujeiras.append((linha, coluna))

        if not sujeiras:

            return []

        def distancia(posicao_a, posicao_b):

            diferenca_linha = posicao_a[0] - posicao_b[0]
            diferenca_coluna = posicao_a[1] - posicao_b[1]

            if diferenca_linha < 0:

                diferenca_linha = -diferenca_linha

            if diferenca_coluna < 0:

                diferenca_coluna = -diferenca_coluna

            return diferenca_linha + diferenca_coluna

        # Dicionário usado para não repetir cálculos.
        calculados = {}

        # Testa cada sujeira como a próxima parada.
        def buscarMelhorCaminho(indice_atual, sujeiras_restantes):

            chave = (indice_atual, sujeiras_restantes)

            if chave in calculados:

                return calculados[chave]

            if len(sujeiras_restantes) == 0:

                return 0, None

            if indice_atual == -1:

                posicao_atual = posicao_inicial

            else:

                posicao_atual = sujeiras[indice_atual]

            melhor_custo = 999999
            melhor_proxima = None

            for indice in sujeiras_restantes:

                sujeiras_depois = list(sujeiras_restantes)
                sujeiras_depois.remove(indice)
                sujeiras_depois = tuple(sujeiras_depois)

                custo_restante, _ = buscarMelhorCaminho(
                    indice,
                    sujeiras_depois
                )
                custo_total = (
                    distancia(posicao_atual, sujeiras[indice])
                    + custo_restante
                )

                if custo_total < melhor_custo:

                    melhor_custo = custo_total
                    melhor_proxima = indice

            resultado = (melhor_custo, melhor_proxima)
            calculados[chave] = resultado

            return resultado

        # Recupera a ordem escolhida pela busca.
        ordem = []
        indice_atual = -1
        sujeiras_restantes = tuple(range(len(sujeiras)))

        while len(sujeiras_restantes) > 0:

            _, proxima = buscarMelhorCaminho(
                indice_atual,
                sujeiras_restantes
            )
            ordem.append(sujeiras[proxima])
            lista_restantes = list(sujeiras_restantes)
            lista_restantes.remove(proxima)
            sujeiras_restantes = tuple(lista_restantes)
            indice_atual = proxima

        self.rota = ordem

        # Transforma a ordem das sujeiras nas ações do robô.
        acoes = []
        linha_atual, coluna_atual = posicao_inicial

        for linha_destino, coluna_destino in ordem:

            while linha_atual < linha_destino:

                acoes.append("abaixo")
                linha_atual += 1

            while linha_atual > linha_destino:

                acoes.append("acima")
                linha_atual -= 1

            while coluna_atual < coluna_destino:

                acoes.append("direita")
                coluna_atual += 1

            while coluna_atual > coluna_destino:

                acoes.append("esquerda")
                coluna_atual -= 1

            acoes.append("aspirar")

        return acoes

    def mapeamento(self, linha, coluna, acao):

        if acao == "acima":

            nova_linha = linha - 1
            nova_coluna = coluna

        elif acao == "abaixo":

            nova_linha = linha + 1
            nova_coluna = coluna

        elif acao == "esquerda":

            nova_linha = linha
            nova_coluna = coluna - 1

        elif acao == "direita":

            nova_linha = linha
            nova_coluna = coluna + 1

        else:

            return linha, coluna


        # Não permite atravessar paredes
        if ambiente.matriz[nova_linha, nova_coluna] == 1:

            return linha, coluna


        return nova_linha, nova_coluna


    def agenteObjetivo(self, percepcao, objObtido):

        # Encerra as ações quando o objetivo de limpar a sala foi atingido
        if objObtido == 0:

            return "NoOp"

        # Executa a próxima ação do plano calculado antes de agir
        if self.plano:

            return self.plano.pop(0)

        return "NoOp"


# PROGRAMA PRINCIPAL

ambiente = Ambiente()

robo = Robo()

pontos = 0


# Mostra o ambiente inicial
print("Ambiente inicial:")
ambiente.imprimir()

ambiente.visualizar()

# Antes de agir, o agente percebe a sala inteira e planeja a melhor rota
robo.perceberSala(ambiente.matriz, (linha, coluna))

print("\nOrdem planejada para limpar as sujeiras:", robo.rota)
print("Quantidade de ações planejadas:", len(robo.plano))


# Executa até o agente receber a informação de que atingiu o objetivo
while True:

    # O robô percebe o ambiente
    estado = ambiente.matriz[linha, coluna]

    percepcao = (
        linha,
        coluna,
        estado.item()
    )

    # Verifica se ainda existe sujeira na sala
    objObtido = checkObj(ambiente.matriz)


    # O agente decide a ação considerando o objetivo
    acao = robo.agenteObjetivo(percepcao, objObtido)


    print(
        "Posição:", (linha, coluna),
        "| Percepção:", percepcao,
        "| Objetivo pendente:", objObtido,
        "| Ação:", acao
    )


    # NoOp indica que a sala está limpa e nenhuma outra ação é necessária
    if acao == "NoOp":

        break


    # Se a ação for aspirar
    if acao == "aspirar":

        ambiente.matriz[linha, coluna] = 0


    # Caso contrário, movimenta o robô
    else:

        linha, coluna = robo.mapeamento(
            linha,
            coluna,
            acao
        )


    # Conta cada ação executada até atingir o objetivo
    pontos += 1


    # Atualiza a visualização
    ambiente.visualizar()


# FINAL

print("\nSala limpa!")

print("Pontos (passos até o objetivo):", pontos)

print("\nAmbiente final:")
ambiente.imprimir()


# Desliga o modo interativo
# e mantém a janela aberta
plt.ioff()
plt.show()


# PERGUNTA + RESPOSTA

# É possível ter todo o espaço limpo efetivamente? Justifique sua resposta.
# Sim, como o agente baseado em objetivos possui uma visão do ambiente completo antes de agir, ele consegue primeiro planejar a melhor rota para limpar todas as sujeiras presentes e então executá-la.