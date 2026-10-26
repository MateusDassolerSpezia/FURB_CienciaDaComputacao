import xmlrpc.client


servidor = xmlrpc.client.ServerProxy("http://localhost:8005")

numero1 = float(input("1o numero: "))
numero2 = float(input("2o numero: "))

print("Soma:", servidor.somar(numero1, numero2))
print("Multiplicacao:", servidor.multiplicar(numero1, numero2))
print("Potencia:", servidor.potencia(numero1, numero2))
print("Divisao:", servidor.dividir(numero1, numero2))
print("Subtracao:", servidor.subtrair(numero1, numero2))
