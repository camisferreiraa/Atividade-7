import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Classe que representa um trabalho de impressão
class TrabalhoImpressao {
    private String nomeDocumento;
    private int numeroPaginas;

    public TrabalhoImpressao(String nomeDocumento, int numeroPaginas) {
        this.nomeDocumento = nomeDocumento;
        this.numeroPaginas = numeroPaginas;
    }

    public String getNomeDocumento() {
        return nomeDocumento;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public String toString() {
        return "Documento: " + nomeDocumento + " | Páginas: " + numeroPaginas;
    }
}

// Classe que gerencia a fila de trabalhos de impressão
class GerenciamentoImpressao {
    private Queue<TrabalhoImpressao> filaImpressao;

    public GerenciamentoImpressao() {
        this.filaImpressao = new LinkedList<>();
    }

    // Adiciona um novo trabalho de impressão à fila
    public void adicionarTrabalho(String nomeDocumento, int numeroPaginas) {
        TrabalhoImpressao trabalho = new TrabalhoImpressao(nomeDocumento, numeroPaginas);
        filaImpressao.add(trabalho);
        System.out.println("Trabalho adicionado à fila: " + trabalho);
    }

    // Processa o próximo trabalho de impressão
    public void processarProximoTrabalho() {
        if (filaImpressao.isEmpty()) {
            System.out.println("Nenhum trabalho de impressão na fila.");
        } else {
            TrabalhoImpressao proximoTrabalho = filaImpressao.poll(); // Remove o próximo trabalho da fila
            System.out.println("Processando trabalho: " + proximoTrabalho);
        }
    }

    // Exibe todos os trabalhos de impressão na fila
    public void exibirFila() {
        if (filaImpressao.isEmpty()) {
            System.out.println("A fila de impressão está vazia.");
        } else {
            System.out.println("Trabalhos de impressão na fila:");
            for (TrabalhoImpressao trabalho : filaImpressao) {
                System.out.println(trabalho);
            }
        }
    }
}

// Classe principal para executar o simulador de gerenciamento de impressões
public class SimuladorGerenciamentoImpressao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciamentoImpressao gerenciamentoImpressao = new GerenciamentoImpressao();

        System.out.println("Sistema de Gerenciamento de Impressões - Operações:");
        while (true) {
            System.out.println("\nComandos: add <nomeDocumento> <numeroPaginas>, process, show, exit");
            String comando = scanner.nextLine();
            String[] partes = comando.split(" ");

            if (partes[0].equals("add")) {
                String nomeDocumento = partes[1];
                int numeroPaginas = Integer.parseInt(partes[2]);
                gerenciamentoImpressao.adicionarTrabalho(nomeDocumento, numeroPaginas);
            } else if (partes[0].equals("process")) {
                gerenciamentoImpressao.processarProximoTrabalho();
            } else if (partes[0].equals("show")) {
                gerenciamentoImpressao.exibirFila();
            } else if (partes[0].equals("exit")) {
                System.out.println("Encerrando o sistema de gerenciamento de impressões.");
                break;
            } else {
                System.out.println("Comando inválido.");
            }
        }

        scanner.close();
    }
}
