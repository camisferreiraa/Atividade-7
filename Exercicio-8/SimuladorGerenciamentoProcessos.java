import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Classe que representa um processo no sistema operacional
class Processo {
    private String nome;
    private int pid; // Identificador do processo (Process ID)

    public Processo(String nome, int pid) {
        this.nome = nome;
        this.pid = pid;
    }

    public String getNome() {
        return nome;
    }

    public int getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return "Processo: " + nome + " | PID: " + pid;
    }
}

// Classe que gerencia a fila de processos prontos
class GerenciamentoProcessos {
    private Queue<Processo> filaProcessos;
    private int contadorPid; // Contador para gerar IDs únicos de processos

    public GerenciamentoProcessos() {
        this.filaProcessos = new LinkedList<>();
        this.contadorPid = 1; // Começar com PID 1
    }

    // Adiciona um novo processo à fila
    public void adicionarProcesso(String nome) {
        Processo novoProcesso = new Processo(nome, contadorPid++);
        filaProcessos.add(novoProcesso);
        System.out.println("Processo adicionado à fila: " + novoProcesso);
    }

    // Remove e executa o processo mais antigo da fila
    public void executarProcesso() {
        if (filaProcessos.isEmpty()) {
            System.out.println("Nenhum processo disponível para execução.");
        } else {
            Processo processoExecutado = filaProcessos.poll(); // Remove o processo mais antigo
            System.out.println("Executando processo: " + processoExecutado);
        }
    }

    // Exibe todos os processos prontos na fila
    public void exibirProcessos() {
        if (filaProcessos.isEmpty()) {
            System.out.println("Nenhum processo na fila.");
        } else {
            System.out.println("Processos prontos na fila:");
            for (Processo processo : filaProcessos) {
                System.out.println(processo);
            }
        }
    }
}

// Classe principal para simular o gerenciamento de processos prontos para execução
public class SimuladorGerenciamentoProcessos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciamentoProcessos gerenciamentoProcessos = new GerenciamentoProcessos();

        System.out.println("Simulador de Gerenciamento de Processos - Comandos:");
        while (true) {
            System.out.println("\nComandos: add <nomeProcesso>, exec, show, exit");
            String comando = scanner.nextLine();
            String[] partes = comando.split(" ");

            if (partes[0].equals("add")) {
                String nomeProcesso = partes[1];
                gerenciamentoProcessos.adicionarProcesso(nomeProcesso);
            } else if (partes[0].equals("exec")) {
                gerenciamentoProcessos.executarProcesso();
            } else if (partes[0].equals("show")) {
                gerenciamentoProcessos.exibirProcessos();
            } else if (partes[0].equals("exit")) {
                System.out.println("Encerrando o simulador de processos.");
                break;
            } else {
                System.out.println("Comando inválido.");
            }
        }

        scanner.close();
    }
}
