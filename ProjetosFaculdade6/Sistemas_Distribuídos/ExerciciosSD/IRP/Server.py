from xmlrpc.server import SimpleXMLRPCServer
from xmlrpc.server import SimpleXMLRPCRequestHandler

class RequestHandler(SimpleXMLRPCRequestHandler):
    rpc_paths = ('/RPC2',)

with SimpleXMLRPCServer(('localhost', 8005), 
        requestHandler=RequestHandler) as server:
    server.register_introspection_functions()

    def adder_function(x, y):
        print(x+y)
        return x + y

    server.register_function(adder_function, 'add')

    server.serve_forever()