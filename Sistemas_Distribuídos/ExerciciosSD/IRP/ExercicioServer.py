from xmlrpc.server import SimpleXMLRPCRequestHandler
from xmlrpc.server import SimpleXMLRPCServer


class RequestHandler(SimpleXMLRPCRequestHandler):
    rpc_paths = ("/RPC2",)


def somar(numero1, numero2):
    resultado = numero1 + numero2
    print("Soma:", resultado)
    return resultado


def multiplicar(numero1, numero2):
    resultado = numero1 * numero2
    print("Multiplicacao:", resultado)
    return resultado


def potencia(numero1, numero2):
    resultado = numero1**numero2
    print("Potencia:", resultado)
    return resultado


def dividir(numero1, numero2):
    if numero2 == 0:
        return "Nao e possivel dividir por zero."

    resultado = numero1 / numero2
    print("Divisao:", resultado)
    return resultado


def subtrair(numero1, numero2):
    resultado = numero1 - numero2
    print("Subtracao:", resultado)
    return resultado


with SimpleXMLRPCServer(
    ("localhost", 8005), requestHandler=RequestHandler
) as server:
    server.register_introspection_functions()
    server.register_function(somar, "somar")
    server.register_function(multiplicar, "multiplicar")
    server.register_function(potencia, "potencia")
    server.register_function(dividir, "dividir")
    server.register_function(subtrair, "subtrair")

    print("Servidor iniciado em http://localhost:8005")
    server.serve_forever()
