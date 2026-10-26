import socket
from pathlib import Path


HOST = "localhost"
PORTA = 6001
PASTA = Path(__file__).resolve().parent
ARQUIVO = PASTA / "FT_teste.xlsx"
TAMANHO_BLOCO = 1024 * 16


if not ARQUIVO.exists() or ARQUIVO.stat().st_size == 0:
    print("Erro: coloque uma planilha valida chamada FT_teste.xlsx na pasta:")
    print(PASTA)
    raise SystemExit(1)


servidor = socket.socket()
servidor.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
servidor.bind((HOST, PORTA))
servidor.listen(1)

print("Servidor iniciado")

# Primeira conexao: envia a planilha ao cliente.
conexao, endereco = servidor.accept()
print("Cliente conectado para receber o arquivo:", endereco)

with conexao, open(ARQUIVO, "rb") as arquivo:
    while True:
        dados = arquivo.read(TAMANHO_BLOCO)
        if not dados:
            break
        conexao.sendall(dados)

print("Arquivo enviado ao cliente")

# Segunda conexao: recebe a planilha editada e sobrescreve a antiga.
conexao, endereco = servidor.accept()
print("Cliente conectado para devolver o arquivo:", endereco)

with conexao, open(ARQUIVO, "wb") as arquivo:
    while True:
        dados = conexao.recv(TAMANHO_BLOCO)
        if not dados:
            break
        arquivo.write(dados)

print("Arquivo editado recebido e salvo como", ARQUIVO.name)
servidor.close()
