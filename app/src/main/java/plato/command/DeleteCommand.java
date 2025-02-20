package plato.command;

import plato.exception.PlatoException;
import plato.model.Task;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand by extracting the task number from user input.
     *
     * @param input The raw user input specifying which task to delete.
     * @throws PlatoException If the input format is invalid.
     */
    public DeleteCommand(String input) throws PlatoException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new PlatoException("Invalid format for delete.");
        }
        this.taskNumber = Integer.parseInt(parts[1]) - 1;
    }

    /**
     * Executes the delete command by removing the specified task from the task list
     * and updating storage.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to persist the changes.
     * @throws PlatoException If an error occurs while accessing the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        Task task = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showMessage("Deleted: " + task);
    }
}
