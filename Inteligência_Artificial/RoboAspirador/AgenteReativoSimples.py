import numpy as np
import matplotlib.pyplot as plt
import random
from matplotlib.colors import ListedColormap

# Aluno: Mateus Dassoler Spezia - 19/09/2026


# Posição inicial aleatória do robô
linha = random.randint(1, 4)
coluna = random.randint(1, 4)


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


class Robo:

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


    def agenteReativoSimples(self, percepcao):

        # Recebe a percepção
        linha, coluna, estado = percepcao


        # Se houver sujeira, aspira
        if estado == 2:

            return "aspirar"


        # Percuso que passa pelo menos 1 vez em cada quadrado da sala 4x4

        # Linha 1: segue para a direita e depois desce
        if linha == 1:

            if coluna < 4:

                return "direita"

            else:

                return "abaixo"


        # Linha 2: a coluna 1 fecha o ciclo; as colunas 4, 3 e 2
        # seguem para a esquerda, descendo ao chegar na coluna 2
        if linha == 2:

            if coluna == 1:

                return "acima"

            elif coluna == 2:

                return "abaixo"

            else:

                return "esquerda"


        # Linha 3: a coluna 1 sobe; as demais seguem para a direita
        if linha == 3:

            if coluna == 1:

                return "acima"

            elif coluna < 4:

                return "direita"

            else:

                return "abaixo"


        # Linha 4: segue para a esquerda e depois sobe
        if linha == 4:

            if coluna > 1:

                return "esquerda"

            else:

                return "acima"


        # Proteção
        return "aspirar"


# PROGRAMA PRINCIPAL

ambiente = Ambiente()

robo = Robo()


# Mostra o ambiente inicial
print("Ambiente inicial:")
ambiente.imprimir()

ambiente.visualizar()


# Executa enquanto existir sujeira
for i in range(32):

    # O robô percebe o ambiente
    estado = ambiente.matriz[linha, coluna]

    percepcao = (
        linha,
        coluna,
        estado.item()
    )


    # O agente decide a ação
    acao = robo.agenteReativoSimples(percepcao)


    print(
        "Posição:", (linha, coluna),
        "| Percepção:", percepcao,
        "| Ação:", acao
    )


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


    # Atualiza a visualização
    ambiente.visualizar()


# FINAL

print("\nSala limpa!")

print("\nAmbiente final:")
ambiente.imprimir()


# Desliga o modo interativo
# e mantém a janela aberta
plt.ioff()
plt.show()


# PERGUNTA + RESPOSTA

# A sua solução é extensível para um mundo 3 x 3? E para um mundo 6 x 6? Explique sua resposta.
# Não, pois essa solução foi feita baseada em um mundo 4x4, então a movimentação do agente está restrita a esse tamanho de mundo. Porém, a solução pode ser extensível para ambos os mundos caso hajam algumas adaptações no código.