import asyncio
import websockets


async def somar(websocket, path=None):
    print("Cliente conectado")

    try:
        async for mensagem in websocket:
            try:
                numero1, numero2 = mensagem.split(",")
                resultado = float(numero1) + float(numero2)

                if resultado.is_integer():
                    resultado = int(resultado)

                resposta = f"Resultado: {resultado}"
            except (ValueError, TypeError):
                resposta = "Erro: informe dois numeros validos."

            await websocket.send(resposta)
            print(resposta)
    except websockets.ConnectionClosed:
        pass
    finally:
        print("Cliente desconectado")


async def main():
    async with websockets.serve(somar, "127.0.0.1", 5678):
        print("Servidor iniciado em ws://127.0.0.1:5678")
        await asyncio.Future()


if __name__ == "__main__":
    asyncio.run(main())
