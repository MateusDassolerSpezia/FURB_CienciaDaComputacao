import asyncio
import datetime
import random
import websockets
async def time(websocket, path=None):
    while True:
        now = await asyncio.to_thread(input, "Mensagem para o navegador: ")
        await websocket.send(now)
        await asyncio.sleep(random.random() * 2)

async def main():
    async with websockets.serve(time, "127.0.0.1", 5678):
        print("Servidor iniciado em ws://127.0.0.1:5678")
        await asyncio.Future()  # Mantém o servidor ligado

if __name__ == "__main__":
    asyncio.run(main())