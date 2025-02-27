package plato.ui;

import java.util.Scanner;

/**
 * Handles user interactions for the Plato chatbot.
 * Provides methods to display messages, read user input, and handle errors.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcome() {
        System.out.println("______________________________________");
        System.out.println("Greetings, I am Plato. Nice to meet you!");
        System.out.println("What would you like to do?");
        System.out.println("______________________________________");
    }

    /**
     * Reads a command input from the user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a separator line for better readability of console output.
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
     * Displays an error message when tasks fail to load from storage.
     * Notifies the user that a new empty task list will be started.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}

