# File: encrypted_chat_server.py

import socket, threading, base64

clients = []

def encrypt(msg):
    return base64.b64encode(msg.encode()).decode()

def decrypt(msg):
    return base64.b64decode(msg.encode()).decode()

def broadcast(message):
    for client in clients:
        client.send(encrypt(message).encode())

def handle(client):
    while True:
        try:
            msg = decrypt(client.recv(1024).decode())
            print("Client:", msg)
            broadcast(f"Echo -> {msg}")
        except:
            clients.remove(client)
            client.close()
            break

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(("localhost", 5555))
server.listen()

while True:
    client, addr = server.accept()
    print("Connected:", addr)
    clients.append(client)
    threading.Thread(target=handle, args=(client,)).start()
