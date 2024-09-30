import java.util.Scanner;

// Classe que representa uma URL no histórico
class URLNode {
    String url;
    URLNode next;

    public URLNode(String url) {
        this.url = url;
        this.next = null;
    }
}

// Classe que representa o histórico de navegação
class BrowserHistory {
    private URLNode head;
    private int maxSize;
    private int currentSize;

    public BrowserHistory(int maxSize) {
        this.maxSize = maxSize;
        this.head = null;
        this.currentSize = 0;
    }

    // Adiciona uma nova URL ao histórico
    public void addURL(String url) {
        URLNode newNode = new URLNode(url);
        if (currentSize < maxSize) {
            newNode.next = head;
            head = newNode;
            currentSize++;
        } else {
            // Remove a URL mais antiga
            removeOldestURL();
            // Adiciona a nova URL
            newNode.next = head;
            head = newNode;
        }
        System.out.println("URL adicionada: " + url);
    }

    // Remove a URL mais antiga
    private void removeOldestURL() {
        if (head == null) return;

        URLNode current = head;
        if (current.next == null) {
            head = null; // Lista ficará vazia
        } else {
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null; // Remove o último nó
        }
        currentSize--;
        System.out.println("URL mais antiga removida do histórico.");
    }

    // Exibe todas as URLs do histórico
    public void displayHistory() {
        if (head == null) {
            System.out.println("O histórico está vazio.");
            return;
        }

        URLNode current = head;
        System.out.println("Histórico de Navegação:");
        while (current != null) {
            System.out.println(current.url);
            current = current.next;
        }
    }
}

// Classe principal para interagir com o usuário
public class Browser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Defina o tamanho máximo do histórico: ");
        int maxSize = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        BrowserHistory history = new BrowserHistory(maxSize);
        String command;

        System.out.println("Simulador de Histórico de Navegação");
        while (true) {
            System.out.println("\nComandos: add <url>, show, exit");
            command = scanner.nextLine();

            if (command.startsWith("add ")) {
                String url = command.substring(4);
                history.addURL(url);
            } else if (command.equals("show")) {
                history.displayHistory();
            } else if (command.equals("exit")) {
                System.out.println("Saindo do simulador de histórico.");
                break;
            } else {
                System.out.println("Comando inválido.");
            }
        }

        scanner.close();
    }
}
