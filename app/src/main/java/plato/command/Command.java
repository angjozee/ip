package plato.command;

import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Represents an abstract command that can be executed within the task manager.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list to be modified or accessed.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to persist task data.
     * @throws PlatoException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException;

    /**
     * Determines whether this command should terminate the program.
     *
     * @return {@code true} if the command exits the program, otherwise {@code false}.
     */
    public boolean isExit() {
        return false;
    }
}
