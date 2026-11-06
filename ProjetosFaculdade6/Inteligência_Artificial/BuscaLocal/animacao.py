# Animação do Algoritmo Genético para o Caixeiro Viajante
# Grupo: Pedro Alegria Fiamoncini, Arthur Quintino, Mateus Spezia e Vinicius Oneda
#
# Este arquivo não roda sozinho: o algoritmos_geneticos.py chama a função animar()
# no final, passando os dados que guardou durante a evolução.

import numpy as np
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

AZUL = '#1f6f8b'
VERMELHO = '#d1495b'
CINZA = '#d9d9d9'


def animar(x, y, quadros, historico, prob, n_iter, salvar_gif=False):
    """Monta a animação da evolução.

    x, y       coordenadas das cidades
    quadros    lista de "fotos": (geração, melhor caminho, custo, quantas vezes cada pai foi sorteado)
    historico  melhor custo de cada geração
    prob       probabilidades da roleta
    n_iter     número de gerações
    """
    n_cidades = len(x)
    n_manter = len(prob)

    # Janela com 3 partes: mapa, roleta e curva
    fig = plt.figure(figsize=(15, 6))
    fig.suptitle("Algoritmo Genético: Caixeiro Viajante com " + str(n_cidades) + " cidades",
                 fontsize=15, fontweight='bold', color='#222222')
    grade = fig.add_gridspec(1, 3, width_ratios=[1.1, 0.9, 1.1], wspace=0.3,
                             left=0.05, right=0.97, top=0.84, bottom=0.12)
    ax_mapa = fig.add_subplot(grade[0])
    ax_roleta = fig.add_subplot(grade[1])
    ax_curva = fig.add_subplot(grade[2])

    # ---------- Mapa: as cidades ficam fixas, só a linha do caminho muda ----------
    ax_mapa.scatter(x, y, s=70, color=VERMELHO, edgecolor='white', linewidth=1.5, zorder=3)
    for i in range(n_cidades):
        ax_mapa.text(x[i] + 0.018, y[i] + 0.018, str(i + 1), fontsize=9, color='#333333', zorder=4)
    linha_caminho, = ax_mapa.plot([], [], '-', color=AZUL, linewidth=2.2, zorder=2)
    ax_mapa.set_xlim(-0.03, 1.05)
    ax_mapa.set_ylim(-0.03, 1.07)
    ax_mapa.set_aspect('equal')
    ax_mapa.set_xlabel("Coordenada X")
    ax_mapa.set_ylabel("Coordenada Y")

    # ---------- Curva do melhor custo (escala log: a melhora acontece quase toda no começo) ----------
    curva, = ax_curva.plot([], [], color=AZUL, linewidth=2)
    ponto, = ax_curva.plot([], [], 'o', color=VERMELHO, markersize=7)
    ax_curva.set_xscale('log')
    ax_curva.set_xlim(1, n_iter)
    ax_curva.set_ylim(min(historico) * 0.95, quadros[0][2] * 1.03)
    ax_curva.set_title("Melhor custo por geração", fontsize=12)
    ax_curva.set_xlabel("Geração (escala log)")
    ax_curva.set_ylabel("Distância total")
    ax_curva.grid(alpha=0.3)
    texto_custo = ax_curva.text(0.97, 0.93, "", transform=ax_curva.transAxes, ha='right',
                                fontsize=13, fontweight='bold', color=AZUL)

    for ax in (ax_mapa, ax_curva):
        ax.spines['top'].set_visible(False)
        ax.spines['right'].set_visible(False)

    # ---------- Roleta: fatias dos pais sorteados ficam azuis e afastadas ----------
    def desenhar_roleta(vezes):
        ax_roleta.clear()
        cores = [AZUL if v > 0 else CINZA for v in vezes]
        afastar = [0.07 if v > 0 else 0 for v in vezes]
        ax_roleta.pie(prob, labels=[str(i + 1) + "º" for i in range(n_manter)], colors=cores,
                      explode=afastar, startangle=90, counterclock=False,
                      wedgeprops={'edgecolor': 'white', 'linewidth': 1.5},
                      textprops={'fontsize': 9, 'color': '#333333'})
        for i in range(n_manter):
            if vezes[i] > 0 and prob[i] >= 0.07:           # número só nas fatias grandes
                ang = np.deg2rad(90 - 360 * (np.sum(prob[:i]) + prob[i] / 2))
                ax_roleta.text(0.62 * np.cos(ang), 0.62 * np.sin(ang), str(vezes[i]) + "x",
                               ha='center', va='center', fontsize=10, fontweight='bold', color='white')
        ax_roleta.set_title("Roleta: pais sorteados", fontsize=12)
        sorteados = [str(i + 1) + "º (" + str(v) + "x)" for i, v in enumerate(vezes) if v > 0]
        if len(sorteados) > 0:
            legenda = "Sorteados: " + ", ".join(sorteados)
        else:
            legenda = "População inicial (ainda sem sorteio)"
        ax_roleta.text(0, -1.35, legenda, ha='center', fontsize=9, color='#555555')

    # ---------- Cada quadro da animação ----------
    def atualizar(q):
        geracao, caminho, custo, vezes = quadros[q]
        fechado = np.append(caminho, caminho[0])           # volta para a primeira cidade
        linha_caminho.set_data(x[fechado], y[fechado])
        ax_mapa.set_title("Melhor caminho na geração " + str(geracao), fontsize=12)
        curva.set_data(np.arange(1, geracao + 1), historico[:geracao])
        ponto.set_data([max(geracao, 1)], [custo])
        texto_custo.set_text("custo = " + str(round(custo, 4)))
        desenhar_roleta(vezes)

    animacao = FuncAnimation(fig, atualizar, frames=len(quadros), interval=150, repeat=False)

    if salvar_gif:
        animacao.save("evolucao.gif", writer='pillow', fps=8)

    return animacao
