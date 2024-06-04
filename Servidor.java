import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class Product {
    String name;
    AtomicInteger quantity;

    public Product(String name, int initialQuantity) {
        this.name = name;
        this.quantity = new AtomicInteger(initialQuantity);
    }

    public boolean purchase(int qty) {
        while (true) {
            int existingQuantity = quantity.get();
            if (existingQuantity < qty) {
                return false;
            }
            if (quantity.compareAndSet(existingQuantity, existingQuantity - qty)) {
                return true;
            }
        }
    }

    @Override
    public String toString() {
        return name + ": " + quantity.get() + " disponivel";
    }
}

class Order {
    int clientId;
    String productName;
    int quantity;

    public Order(int clientId, String productName, int quantity) {
        this.clientId = clientId;
        this.productName = productName;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cliente " + clientId + " comprou " + quantity + " unidade(s) de " + productName;
    }
}

public class Servidor {
    private static final int PORT = 8080;
    private static Map<String, Product> catalog = new ConcurrentHashMap<>();
    private static Map<Integer, Map<String, Integer>> salesReport = new ConcurrentHashMap<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor em execucao e aguardando conexoes...");

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    pool.execute(new ClientHandler(clientSocket)); // Usar pool de threads
                } catch (IOException e) {
                    System.out.println("Erro ao lidar com cliente: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void initializeProducts(int scenario) {
        catalog.clear();
        switch (scenario) {
            case 1:
                for (int i = 0; i < 5; i++) {
                    catalog.put("Product" + i, new Product("Product" + i, 1));
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    catalog.put("Product" + i, new Product("Product" + i, 5));
                }
                break;
            case 3:
                for (int i = 0; i < 10; i++) {
                    catalog.put("Product" + i, new Product("Product" + i, 100));
                }
                break;
            default:
                System.out.println("Cenario invalido!");
                System.exit(1);
        }
        System.out.println("Produtos inicializados para o cenario " + scenario);
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            System.out.println("Lidando com cliente...");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String command;
                while ((command = in.readLine()) != null) {
                    System.out.println("Comando recebido: " + command);

                    if (command.startsWith("INIT")) {
                        handleInit(command, out);
                    } else if ("PURCHASE".equals(command)) {
                        handlePurchase(in, out);
                    } else if ("SALES_REPORT".equals(command)) {
                        handleSalesReport(out);
                    } else if ("END".equals(command)) {
                        break;
                    } else {
                        out.println("Comando desconhecido");
                        System.out.println("Comando desconhecido: " + command);
                    }
                }

            } catch (IOException e) {
                System.out.println("Erro durante comunicação com cliente: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar socket: " + e.getMessage());
                }
            }
        }

        private static synchronized void handleInit(String command, PrintWriter out) {
            String[] parts = command.split(" ");
            if (parts.length != 2) {
                out.println("Comando INIT invalido");
                return;
            }
            int scenario;
            try {
                scenario = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                out.println("Numero de cenario invalido");
                return;
            }
            initializeProducts(scenario);
            out.println("Cenário inicializado");
        }

        private void handlePurchase(BufferedReader in, PrintWriter out) throws IOException {
            System.out.println("Lidando com compra...");
            out.println("ACK"); // Enviar reconhecimento
            String purchaseDetails = in.readLine();
            System.out.println("Detalhes da compra recebidos: " + purchaseDetails);

            String[] details = purchaseDetails.split(" ");
            if (details.length != 3) {
                out.println("Formato de detalhes da compra invalido");
                return;
            }

            int clientId;
            int productId;
            int quantity;

            try {
                clientId = Integer.parseInt(details[0]);
                productId = Integer.parseInt(details[1]);
                quantity = Integer.parseInt(details[2]);
            } catch (NumberFormatException e) {
                out.println("Formato de detalhes da compra invalido");
                return;
            }

            String productName = "Product" + productId;
            Product product = catalog.get(productName);
            if (product != null && product.purchase(quantity)) {
                salesReport.computeIfAbsent(clientId, k -> new ConcurrentHashMap<>());
                Map<String, Integer> clientPurchases = salesReport.get(clientId);
                clientPurchases.put(productName, clientPurchases.getOrDefault(productName, 0) + quantity);
                out.println("Compra bem-sucedida");
                System.out.println("Compra bem-sucedida: Cliente " + clientId + ", Produto: " + productName + ", Quantidade: " + quantity);
            } else {
                out.println("Compra falhou");
                System.out.println("Compra falhou: Cliente " + clientId + ", Produto: " + productName + ", Quantidade: " + quantity);
            }
        }

        private static void handleSalesReport(PrintWriter out) {
            System.out.println("Iniciando envio do relatorio de vendas...");
        
            StringBuilder report = new StringBuilder();
            report.append("Detalhes das Vendas:\n");
            for (Map.Entry<Integer, Map<String, Integer>> entry : salesReport.entrySet()) {
                int clientId = entry.getKey();
                Map<String, Integer> products = entry.getValue();
                report.append("Cliente ").append(clientId).append(" comprou total de produtos: ").append(products.values().stream().mapToInt(Integer::intValue).sum()).append("\n");
                for (Map.Entry<String, Integer> productEntry : products.entrySet()) {
                    report.append("   - ").append(productEntry.getKey()).append(": ").append(productEntry.getValue()).append("\n");
                }
            }
            report.append("Quantidade restante de cada produto:\n");
            for (Product product : catalog.values()) {
                report.append(product.toString()).append("\n");
            }
            report.append("END_OF_REPORT\n");
        
            try (Scanner scanner = new Scanner(report.toString())) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    out.println(line);
                    out.flush();
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrompida durante o envio do relatorio.");
            }
        }
        
    }
}
