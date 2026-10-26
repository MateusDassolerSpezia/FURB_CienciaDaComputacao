import socket
port = 6001
s = socket.socket()
host = 'localhost'
s.bind((host, port))
s.listen(1)

while True:
    conn, addr = s.accept()
    print('Conectado por: ', addr)

    filename='teste.xlsx'
    f = open(filename, 'rb')
    l = f.read(1024*16)

    while (l):
        conn.send(l)
        l = f.read(1024*16)

    f.close()

    print('Envio completo')
    conn.close()
    print('Conexão fechada')