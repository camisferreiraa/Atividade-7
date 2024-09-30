import java.util.Scanner;

// Classe que representa um nó da lista duplamente encadeada
class ActionNode {
    String action;
    ActionNode prev;
    ActionNode next;

    public ActionNode(String action) {
        this.action = action;
        this.prev = null;
        this.next = null;
    }
}

// Classe que gerencia as operações de desfazer (undo) e refazer (redo)
class TextEditorHistory {
    private ActionNode head;
    private ActionNode current;

    public TextEditorHistory() {
        this.head = null;
        this.current = null;
    }

    // Adiciona uma nova ação (edição) ao histórico
    public void addAction(String action) {
        ActionNode newNode = new ActionNode(action);

        if (head == null) {
            head = newNode;
            current = newNode;
        } else {
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        System.out.println("Ação adicionada: " + action);
    }

    // Desfaz a última ação (undo)
    public void undoAction() {
        if (current == null) {
            System.out.println("Nenhuma ação para desfazer.");
            return;
        }
        System.out.println("Desfazendo a ação: " + current.action);
        if (current.prev != null) {
            current = current.prev;
        } else {
            current = null; // Nenhuma ação restante
        }
    }

    // Refaz a última ação desfeita (redo)
    public void redoAction() {
        if (current == null || current.next == null) {
            System.out.println("Nenhuma ação para refazer.");
            return;
        }
        current = current.next;
        System.out.println("Refazendo a ação: " + current.action);
    }

    // Exibe todas as ações realizadas até o ponto atual
    public void showActions() {
        if (head == null) {
            System.out.println("Nenhuma ação registrada.");
            return;
        }

        System.out.println("Ações realizadas (do mais antigo ao mais recente):");
        ActionNode temp = head;
        while (temp != null) {
            System.out.println(temp.action + (temp == current ? " <- Atual" : ""));
            temp = temp.next;
        }
    }
}

// Classe principal para interagir com o usuário
public class TextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditorHistory history = new TextEditorHistory();
        String command;

        System.out.println("Editor de Texto Simples com Undo/Redo");

        while (true) {
            System.out.println("\nComandos: add <texto>, undo, redo, show, exit");
            command = scanner.nextLine();

            if (command.startsWith("add ")) {
                String action = command.substring(4); // Obtém o texto inserido
                history.addAction(action);
            } else if (command.equals("undo")) {
                history.undoAction();
            } else if (command.equals("redo")) {
                history.redoAction();
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
