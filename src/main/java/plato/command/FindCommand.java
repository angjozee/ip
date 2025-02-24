package plato.command;

import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Represents a command to find tasks by keyword in the task list.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the keyword.
     *
     * @param tasks   The task list to search within.
     * @param ui      The user interface to display messages.
     * @param storage The storage system (not used for this command).
     * @throws PlatoException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        boolean found = false;
        ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getDescription().contains(keyword)) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
                found = true;
            }
        }
        if (!found) {
            ui.showMessage("No matching tasks found.");
        }
    }
}
