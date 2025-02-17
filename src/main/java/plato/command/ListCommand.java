package plato.command;

import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying all tasks in the task list.
     *
     * @param tasks   The task list containing the user's tasks.
     * @param ui      The user interface to display messages.
     * @param storage The storage system (not used in this command but required for consistency).
     * @throws PlatoException If an error occurs during execution (not expected for this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        if (tasks.size() == 0) {
            ui.showMessage("Your task list is empty.");
        } else {
            ui.showMessage("Here is your list of tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
            }
        }
    }
}
