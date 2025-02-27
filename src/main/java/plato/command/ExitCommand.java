package plato.command;

import javafx.application.Platform;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by terminating the JavaFX application.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display messages.
     * @param storage The storage system (not used in this command).
     * @return A farewell message indicating the chatbot is closing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit(); // Closes the JavaFX application
        return "Farewell. Hope to see you soon!";
    }

    /**
     * Indicates that this command will terminate the chatbot application.
     *
     * @return {@code true}, as executing this command should exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
