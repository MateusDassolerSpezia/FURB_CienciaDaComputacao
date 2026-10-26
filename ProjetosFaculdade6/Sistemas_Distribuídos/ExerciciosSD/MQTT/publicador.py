import paho.mqtt.client as mqtt
import json
import time
import random
# Fazer uma interface de cliente
# Configurações do broker onde as msg serão publicadas 
BROKER = "broker.hivemq.com"
PORT = 1883
TOPIC = "/SMART_CPS"

# Função para quando a conexão for estabelecida
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Conectado ao broker MQTT com sucesso!")
    else:
        print("Falha na conexão. Código de retorno: {rc}")

# Inicializa o cliente que vai publicar as mensagens
client = mqtt.Client()
client.on_connect = on_connect

# Conecta ao broker
client.connect(BROKER, PORT, 60)
client.loop_start()

try:
    while True:
        # Gera dados aleatórios simulando o sensor
        payload = {
            "Temp": round(random.uniform(25.0, 30.0), 2),
            "Umid": round(random.uniform(70.0, 90.0), 1),
            "Pressao": round(random.uniform(60, 70), 1),
            "Rpm": random.randint(100, 150)
        }

        # Publica os dados no tópico MQTT
        client.publish(TOPIC, json.dumps(payload), qos=2)
        print(f"Publicado: {payload}")
        time.sleep(3)

except KeyboardInterrupt:
    print("Encerrando a publicação MQTT...")
    client.loop_stop()
    client.disconnect()