import xmlrpc.client
s = xmlrpc.client.ServerProxy('http://localhost:8005')

numero1 = input("1º número: ")
numero2 = input("2º número: ")

print('Somar: ',s.add(int(numero1), int(numero2)))