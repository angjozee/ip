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
     * Executes the command by returning all tasks in the task list as a string.
     *
     * @param tasks   The task list containing the user's tasks.
     * @param ui      The user interface (not used here).
     * @param storage The storage system (not used in this command but required for consistency).
     * @return A formatted string of all tasks.
     * @throws PlatoException If an error occurs during execution (not expected for this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        if (tasks.size() == 0) {
            return "Your task list is empty.";
        }

        StringBuilder response = new StringBuilder("Here is your list of tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return response.toString();
    }
}
