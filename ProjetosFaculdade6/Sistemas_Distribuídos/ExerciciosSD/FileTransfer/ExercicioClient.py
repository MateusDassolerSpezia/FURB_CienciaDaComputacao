import socket
from pathlib import Path


HOST = "localhost"
PORTA = 6001
PASTA = Path(__file__).resolve().parent
ARQUIVO = PASTA / "FT_arquivo_recebido.xlsx"
TAMANHO_BLOCO = 1024 * 16


# Primeira conexao: recebe a planilha do servidor.
cliente = socket.socket()
cliente.connect((HOST, PORTA))

with cliente, open(ARQUIVO, "wb") as arquivo:
    while True:
        dados = cliente.recv(TAMANHO_BLOCO)
        if not dados:
            break
        arquivo.write(dados)

print("Arquivo recebido e salvo como", ARQUIVO.name)
input("Edite e salve a planilha. Depois, pressione Enter para devolve-la...")

# Segunda conexao: devolve a planilha editada ao servidor.
cliente = socket.socket()
cliente.connect((HOST, PORTA))

with cliente, open(ARQUIVO, "rb") as arquivo:
    while True:
        dados = arquivo.read(TAMANHO_BLOCO)
        if not dados:
            break
        cliente.sendall(dados)

print("Arquivo editado enviado ao servidor")
