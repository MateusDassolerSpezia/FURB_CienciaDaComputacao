import paho.mqtt.client as mqtt
import json

# Configurações do broker onde o assinante se inscreve
BROKER = "broker.hivemq.com"
PORT = 1883
TOPIC = "/SMART_CPS"

# Callback quando conectar ao broker
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Conectado ao broker MQTT com sucesso!")
        client.subscribe(TOPIC, qos=2)
        print(f"Inscrito no tópico: {TOPIC}")
    else:
        print(f"Erro na conexão. Código: {rc}")

# Callback quando uma mensagem for recebida
def on_message(client, userdata, msg):
    try:
        payload = json.loads(msg.payload.decode())
        print("Mensagem recebida:")
        print(f"Temperatura: {payload.get('Temp')} °C")
        print(f"Umidade: {payload.get('Umid')} %")
        print(f"Pressão: {payload.get('Pressao')} hPa")
        print(f"RPM: {payload.get('Rpm')}")
        print("-" * 40)
    except Exception as e:
        print(f"Erro ao processar a mensagem: {e}")

#Cria o cliente MQTT
client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message

# Conecta ao broker
client.connect(BROKER, PORT, 60)

# Loop para escutar as mensagens
print("Aguardando mensagens no tópico MQTT...")
client.loop_forever()
