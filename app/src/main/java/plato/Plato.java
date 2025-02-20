package plato;

import plato.command.Command;
import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.parser.Parser;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * The main class for the Plato Task Manager application.
 * It initializes and runs the task management system, handling user input and executing commands.
 */
public class Plato {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Plato instance, initializing UI, storage, and loading tasks from the file.
     *
     * @param filePath The file path to load and store tasks persistently.
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
     * Runs the task management system, processing user input until an exit command is issued.
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
     * The main entry point for the Plato Task Manager application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            new Plato("./data/tasks.txt").run();
        } catch (Exception e) {
            e.printStackTrace(); // Prints full error log to help debug
        }
    }

}
