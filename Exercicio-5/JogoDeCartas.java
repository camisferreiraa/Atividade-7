import java.util.Scanner;

// Classe que representa uma carta do baralho
class Carta {
    String valor;
    String naipe;

    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}

// Nó da lista duplamente encadeada representando uma carta na mão do jogador
class NoCarta {
    Carta carta;
    NoCarta prev;
    NoCarta next;

    public NoCarta(Carta carta) {
        this.carta = carta;
        this.prev = null;
        this.next = null;
    }
}

// Classe que gerencia a mão do jogador
class MaoJogador {
    private NoCarta head; // Primeiro nó da lista
    private NoCarta tail; // Último nó da lista

    public MaoJogador() {
        this.head = null;
        this.tail = null;
    }

    // Adiciona uma carta à mão
    public void adicionarCarta(Carta carta) {
        NoCarta novoNo = new NoCarta(carta);
        if (head == null) {
            head = tail = novoNo; // Lista está vazia
        } else {
            tail.next = novoNo;
            novoNo.prev = tail;
            tail = novoNo;
        }
        System.out.println("Carta adicionada: " + carta);
    }

    // Remove uma carta específica da mão
    public void removerCarta(String valor, String naipe) {
        NoCarta atual = head;

        while (atual != null) {
            if (atual.carta.valor.equals(valor) && atual.carta.naipe.equals(naipe)) {
                // Se a carta a ser removida for a única
                if (atual == head && atual == tail) {
                    head = tail = null;
                } else if (atual == head) {
                    head = head.next;
                    head.prev = null;
                } else if (atual == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    atual.prev.next = atual.next;
                    atual.next.prev = atual.prev;
                }
                System.out.println("Carta removida: " + atual.carta);
                return;
            }
            atual = atual.next;
        }
        System.out.println("Carta não encontrada.");
    }

    // Reorganiza as cartas trocando duas posições
    public void reorganizarCartas(int pos1, int pos2) {
        if (pos1 == pos2) {
            System.out.println("As posições são as mesmas, nada a ser trocado.");
            return;
        }

        NoCarta carta1 = obterCartaNaPosicao(pos1);
        NoCarta carta2 = obterCartaNaPosicao(pos2);

        if (carta1 == null || carta2 == null) {
            System.out.println("Posição inválida.");
            return;
        }

        // Troca os valores das cartas
        Carta temp = carta1.carta;
        carta1.carta = carta2.carta;
        carta2.carta = temp;

        System.out.println("Cartas nas posições " + pos1 + " e " + pos2 + " foram trocadas.");
    }

    // Obtém a carta na posição especificada
    private NoCarta obterCartaNaPosicao(int posicao) {
        NoCarta atual = head;
        int contador = 1;

        while (atual != null && contador < posicao) {
            atual = atual.next;
            contador++;
        }

        return atual;
    }

    // Mostra todas as cartas na mão do jogador
    public void mostrarMao() {
        if (head == null) {
            System.out.println("A mão está vazia.");
            return;
        }

        NoCarta atual = head;
        System.out.println("Cartas na mão:");
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". " + atual.carta);
            atual = atual.next;
            posicao++;
        }
    }
}

// Classe principal para gerenciar o jogo
public class JogoDeCartas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MaoJogador mao = new MaoJogador();

        System.out.println("Jogo de Cartas - Operações disponíveis:");
        while (true) {
            System.out.println("\nComandos: add <valor> <naipe>, remove <valor> <naipe>, swap <pos1> <pos2>, show, exit");
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            if (parts[0].equals("add")) {
                String valor = parts[1];
                String naipe = parts[2];
                mao.adicionarCarta(new Carta(valor, naipe));
            } else if (parts[0].equals("remove")) {
                String valor = parts[1];
                String naipe = parts[2];
                mao.removerCarta(valor, naipe);
            } else if (parts[0].equals("swap")) {
                int pos1 = Integer.parseInt(parts[1]);
                int pos2 = Integer.parseInt(parts[2]);
                mao.reorganizarCartas(pos1, pos2);
            } else if (parts[0].equals("show")) {
                mao.mostrarMao();
            } else if (parts[0].equals("exit")) {
                System.out.println("Saindo do jogo.");
                break;
            } else {
                System.out.println("Comando inválido.");
            }
        }

        scanner.close();
    }
}
