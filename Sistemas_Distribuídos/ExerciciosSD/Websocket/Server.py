import asyncio
import websockets

async def hello(websocket, path=None):
    name = await websocket.recv()
    print(f"< {name}")

    greeting = f"Ola {name}!"

    await websocket.send(greeting)
    print(f"> {greeting}")

async def main():
    async with websockets.serve(hello, "localhost", 8765):
        print("Servidor iniciado em ws://localhost:8765")
        await asyncio.Future()  

if __name__ == "__main__":
    asyncio.run(main())