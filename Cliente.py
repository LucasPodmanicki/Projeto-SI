import socket
import random
import threading

def send_message(s, message):
    s.sendall((message + '\n').encode())
    response = s.recv(1024).decode().strip()
    return response

def purchase_product(s, client_id, product_id, quantity):
    ack = send_message(s, "PURCHASE")
    if ack == "ACK":
        message = f"{client_id} {product_id} {quantity}"
        response = send_message(s, message)
        return response
    else:
        return "Falhou"

def client_simulation(client_id, server_ip, num_products, product_quantity_range, barrier):
    print(f"Cliente {client_id + 1} conectando...")
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((server_ip, 8080))
        print(f"Cliente {client_id + 1} conectado")
        
        barrier.wait()
        
        num_purchases = random.randint(*product_quantity_range)
        for purchase_id in range(num_purchases):
            product_id = random.randint(0, num_products - 1)
            quantity = 1
            print(f"Cliente {client_id + 1} tentando comprar {quantity} unidade(s) do produto {product_id}")
            response = purchase_product(s, client_id + 1, product_id, quantity)
            print(f"Cliente {client_id + 1} recebeu resposta: {response}")
    print(f"Cliente {client_id + 1} completou as compras")

def simulate_purchases(server_ip, scenario, num_clients, num_products, product_quantity_range, scenario_description):
    print(f"\n### Iniciando cenário: {scenario_description} ###")
    
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((server_ip, 8080))
        init_response = send_message(s, f"INIT {scenario}")
        print(f"Resposta de inicializacao: {init_response}")

    barrier = threading.Barrier(num_clients)
    threads = []
    
    for client_id in range(num_clients):
        thread = threading.Thread(target=client_simulation, args=(client_id, server_ip, num_products, product_quantity_range, barrier))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()
    print(f"\n### Cenario '{scenario_description}' finalizado ###\n")



def get_sales_report(server_ip):
    print("\n>>> Solicitando relatório de vendas...")
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((server_ip, 8080))
        send_message(s, "SALES_REPORT")
        response = []
        while True:
            chunk = s.recv(1024).decode()
            if "END_OF_REPORT" in chunk:
                response.append(chunk.split("END_OF_REPORT")[0])
                break
            response.append(chunk)
        full_report = "".join(response)
        print(f"\n### Relatório de vendas:\n{full_report}\n###")


        

if __name__ == "__main__":
    server_ip = "192.168.0.77"


    simulate_purchases(server_ip, scenario=1, num_clients=2, num_products=5, product_quantity_range=(2, 4), scenario_description="1")
    get_sales_report(server_ip)

    simulate_purchases(server_ip, scenario=2, num_clients=10, num_products=10, product_quantity_range=(2, 4), scenario_description="2")
    get_sales_report(server_ip)

    simulate_purchases(server_ip, scenario=3, num_clients=1000, num_products=10, product_quantity_range=(1, 1), scenario_description="3")

    get_sales_report(server_ip)
