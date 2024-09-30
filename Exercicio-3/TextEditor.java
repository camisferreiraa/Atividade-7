import java.util.Scanner;

// Classe que representa uma ação de edição
class ActionNode {
    String action;
    ActionNode next;

    public ActionNode(String action) {
        this.action = action;
        this.next = null;
    }
}

// Classe que representa o histórico de ações e as funcionalidades de "Undo"
class TextEditorHistory {
    private ActionNode head;

    // Adiciona uma nova ação (edição) ao histórico
    public void addAction(String action) {
        ActionNode newNode = new ActionNode(action);
        newNode.next = head;
        head = newNode;
        System.out.println("Ação adicionada: " + action);
    }

    // Desfaz a última ação (Undo)
    public void undoAction() {
        if (head == null) {
            System.out.println("Nenhuma ação para desfazer.");
            return;
        }
        System.out.println("Desfazendo a última ação: " + head.action);
        head = head.next; // Remove a ação do topo da lista
    }

    // Exibe todas as ações realizadas (o estado atual do texto)
    public void showActions() {
        if (head == null) {
            System.out.println("Nenhuma ação registrada.");
            return;
        }

        System.out.println("Ações realizadas (da mais recente para a mais antiga):");
        ActionNode current = head;
        while (current != null) {
            System.out.println(current.action);
            current = current.next;
        }
    }
}

// Classe principal para interagir com o usuário
public class TextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditorHistory history = new TextEditorHistory();
        String command;

        System.out.println("Sistema de Edição de Texto com Controle de Reversão (Undo)");

        while (true) {
            System.out.println("\nComandos: add <texto>, undo, show, exit");
            command = scanner.nextLine();

            if (command.startsWith("add ")) {
                String action = command.substring(4); // Obtém o texto inserido
                history.addAction(action);
            } else if (command.equals("undo")) {
                history.undoAction();
            } else if (command.equals("show")) {
                history.showActions();
            } else if (command.equals("exit")) {
                System.out.println("Encerrando o editor de texto.");
                break;
            } else {
                System.out.println("Comando inválido.");
            }
        }

        scanner.close();
    }
}
