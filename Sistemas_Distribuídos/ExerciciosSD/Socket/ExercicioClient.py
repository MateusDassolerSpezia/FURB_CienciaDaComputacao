from socket import *

serverHost = '127.0.0.1'
serverPort = 50009

sockobj = socket(AF_INET, SOCK_STREAM)
sockobj.connect((serverHost, serverPort))

print("Digite \"sair\" para finalizar a conversa")

while True:
    mensagem = input('Cliente: ')
    if mensagem == "":
        mensagem = "..."
    sockobj.send(mensagem.encode())

    if mensagem.lower() == 'sair':
        break

    resposta = sockobj.recv(1024).decode()
    print('Servidor:', resposta)

    if resposta.lower() == 'sair':
        break

sockobj.close()
