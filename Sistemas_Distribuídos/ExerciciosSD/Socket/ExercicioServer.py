from socket import *

meuHost = '127.0.0.1'
minhaPort = 50009

print("Servidor iniciado")

sockjob = socket(AF_INET, SOCK_STREAM)
sockjob.bind((meuHost, minhaPort))
sockjob.listen(1)

conexao, endereco = sockjob.accept()
print('Servidor conectado por', endereco)
print("Digite \"sair\" para finalizar a conversa")

while True:
    mensagem = conexao.recv(1024).decode()
    print('Cliente:', mensagem)

    if mensagem.lower() == 'sair':
        break

    resposta = input('Servidor: ')
    if resposta == "":
        resposta = "..."
    conexao.send(resposta.encode())

    if resposta.lower() == 'sair':
        break

conexao.close()
sockjob.close()
