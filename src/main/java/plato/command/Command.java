package plato.command;

import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Abstract base class for all chatbot commands.
 */
public abstract class Command {
    /**
     * Executes the command on the provided task list.
     *
     * @param tasks   The task list the command should operate on.
     * @param ui      The UI to provide user feedback.
     * @param storage The storage to save any changes.
     * @return A string response describing the command execution result.
     * @throws PlatoException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException;

    /**
     * Determines whether the command should cause the application to exit.
     * Defaults to `false` for most commands.
     *
     * @return `true` if the command should terminate the application, `false` otherwise.
     */
    public boolean isExit() {
        return false; // Default behavior: most commands do not exit
    }
}
