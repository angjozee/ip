import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("______________________________________");
        System.out.println("Greetings, I am Plato. Nice to meet you!");
        System.out.println("What would you like to do?");
        System.out.println("______________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("______________________________________");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}