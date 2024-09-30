import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Classe que representa um cliente
class Cliente {
    private String nome;
    private int senha;

    public Cliente(String nome, int senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public int getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + " | Senha: " + senha;
    }
}

// Classe que gerencia a fila de atendimento
class FilaBanco {
    private Queue<Cliente> fila;
    private int proximaSenha;

    public FilaBanco() {
        this.fila = new LinkedList<>();
        this.proximaSenha = 1; // Senha inicial
    }

    // Adiciona um cliente à fila
    public void adicionarCliente(String nome) {
        Cliente cliente = new Cliente(nome, proximaSenha++);
        fila.add(cliente);
        System.out.println(cliente.getNome() + " foi adicionado à fila com a senha " + cliente.getSenha());
    }

    // Chama o próximo cliente da fila
    public void chamarProximoCliente() {
        if (fila.isEmpty()) {
            System.out.println("Nenhum cliente na fila.");
        } else {
            Cliente proximoCliente = fila.poll(); // Remove o cliente da fila
            System.out.println("Chamando o próximo cliente: " + proximoCliente);
        }
    }

    // Mostra todos os clientes na fila
    public void mostrarFila() {
        if (fila.isEmpty()) {
            System.out.println("A fila está vazia.");
        } else {
            System.out.println("Clientes na fila:");
            for (Cliente cliente : fila) {
                System.out.println(cliente);
            }
        }
    }
}

// Classe principal para executar o simulador
public class SimuladorFilaBanco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaBanco filaBanco = new FilaBanco();

        System.out.println("Simulador de Fila de Banco - Operações:");
        while (true) {
            System.out.println("\nComandos: add <nome>, call, show, exit");
            String comando = scanner.nextLine();
            String[] partes = comando.split(" ");

            if (partes[0].equals("add")) {
                String nome = partes[1];
                filaBanco.adicionarCliente(nome);
            } else if (partes[0].equals("call")) {
                filaBanco.chamarProximoCliente();
            } else if (partes[0].equals("show")) {
                filaBanco.mostrarFila();
            } else if (partes[0].equals("exit")) {
                System.out.println("Encerrando o simulador.");
                break;
            } else {
                System.out.println("Comando inválido.");
            }
        }

        scanner.close();
    }
}
