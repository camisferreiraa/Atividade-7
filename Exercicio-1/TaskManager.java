import java.util.Scanner;

// Classe que representa uma tarefa
class Task {
    String description;
    boolean completed;
    Task next;

    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.next = null;
    }
}

// Classe que representa a lista de tarefas
class TaskManeger {
    private Task head;

    // Adiciona uma nova tarefa
    public void addTask(String description) {
        Task newTask = new Task(description);
        if (head == null) {
            head = newTask;
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
        System.out.println("Tarefa adicionada: " + description);
    }

    // Remove uma tarefa
    public void removeTask(String description) {
        if (head == null) {
            System.out.println("A lista de tarefas está vazia.");
            return;
        }

        if (head.description.equals(description)) {
            head = head.next;
            System.out.println("Tarefa removida: " + description);
            return;
        }

        Task current = head;
        while (current.next != null) {
            if (current.next.description.equals(description)) {
                current.next = current.next.next;
                System.out.println("Tarefa removida: " + description);
                return;
            }
            current = current.next;
        }
        System.out.println("Tarefa não encontrada: " + description);
    }

    // Marca uma tarefa como concluída
    public void markTaskCompleted(String description) {
        Task current = head;
        while (current != null) {
            if (current.description.equals(description)) {
                current.completed = true;
                System.out.println("Tarefa marcada como concluída: " + description);
                return;
            }
            current = current.next;
        }
        System.out.println("Tarefa não encontrada: " + description);
    }

    // Exibe todas as tarefas
    public void displayTasks() {
        if (head == null) {
            System.out.println("A lista de tarefas está vazia.");
            return;
        }

        Task current = head;
        while (current != null) {
            String status = current.completed ? "[Concluída]" : "[Pendente]";
            System.out.println(status + " " + current.description);
            current = current.next;
        }
    }
}

// Classe principal para interagir com o usuário
public class TaskManager {
    public static void main(String[] args) {
        TaskManeger taskList = new TaskManeger();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Gerenciador de Tarefas");
        while (true) {
            System.out.println("\nComandos: add <descrição>, remove <descrição>, complete <descrição>, show, exit");
            command = scanner.nextLine();

            if (command.startsWith("add ")) {
                String description = command.substring(4);
                taskList.addTask(description);
            } else if (command.startsWith("remove ")) {
                String description = command.substring(7);
                taskList.removeTask(description);
            } else if (command.startsWith("complete ")) {
                String description = command.substring(9);
                taskList.markTaskCompleted(description);
            } else if (command.equals("show")) {
                taskList.displayTasks();
            } else if (command.equals("exit")) {
                System.out.println("Saindo do gerenciador de tarefas.");
                break;
            } else {
                System.out.println("Comando inválido.");
            }
        }

        scanner.close();
    }
}
