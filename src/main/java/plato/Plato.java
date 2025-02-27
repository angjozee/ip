package plato;

import javafx.application.Platform;
import plato.command.Command;
import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.parser.Parser;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * The main class for the Plato chatbot application.
 * It manages user interactions, command execution, and task storage.
 */
public class Plato {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Plato} instance using the default file path for task storage.
     */
    public Plato() {
        this("./data/tasks.txt"); // Provide a default file path
    }

    /**
     * Constructs a {@code Plato} instance with a specified file path for task storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Plato(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (PlatoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot, continuously reading user input and executing commands
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (PlatoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the Plato application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Plato().run(); // Now you can create Plato without parameters
    }

    /**
     * Processes user input and returns a response from the chatbot.
     *
     * @param input The user input as a string.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);

            // If the command is an exit command, close JavaFX
            if (command.isExit()) {
                Platform.exit();
            }

            return response;
        } catch (PlatoException e) {
            return "Error: " + e.getMessage();
        }
    }
}
