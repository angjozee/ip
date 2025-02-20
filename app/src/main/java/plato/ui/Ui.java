package plato.ui;

import java.util.Scanner;

/**
 * Handles user interaction and displays messages to the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with a Scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("______________________________________");
        System.out.println("Greetings, I am Plato. Nice to meet you!");
        System.out.println("What would you like to do?");
        System.out.println("______________________________________");
    }

    /**
     * Reads and returns a command entered by the user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a separator line to improve readability.
     */
    public void showLine() {
        System.out.println("______________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Displays a custom message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
