import socket
import struct
import threading

ip = "192.168.1.31"
port = 8080
socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
header = 100000
list_of_conn = []
addr = (ip, port)
socket.bind(addr)
format =  "utf-8"

def boradcast(addr, msg):
    msg_send = ""
    for cnxn in list_of_conn:
        print(f"Broadcasting message to : {cnxn}")
        addr = str(addr)
        print(addr)
        msg =  addr + f": {msg}"
        # msg_len = str(len(msg)).encode(format)
        # msg_send =  msg_len + b" " * (he
        cnxn.send(struct.pack(">H", len(msg)))

        cnxn.send(str(msg).encode("utf-8"))


def exit(conn):
    conn.send("Disconnecting")
    list_of_conn.remove(conn)


def handle_client(conn, addr):
    connected = True
    msg_len = ""
    while connected:
        msg_len = "2"
        # msg_len = (conn.recv(header).decode("utf-8"))
        if msg_len != "":
            msg =  str(conn.recv(int(1024)).decode("utf-8"))
            if(msg !=  ""):
                if msg.lower() != "exit":
                    print(msg)
                    boradcast(addr, msg)
                else:
                    exit(conn)
                    conn.close()
                    connected = False


def start():
    socket.listen()
    while True:
        conn, addr = socket.accept()
        print("conn connected")
        thread = threading.Thread(target= handle_client,args = (conn,addr) )
        print("coon")
        list_of_conn.append(conn)
        print(f"{addr} joined the room")
        msg = "joined the room"
        boradcast(addr, msg)
        thread.start()
start()

