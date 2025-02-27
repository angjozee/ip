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
     * Constructs a DeleteCommand by parsing the task number from the input.
     *
     * @param input The full input string provided by the user.
     * @throws PlatoException If the input format is invalid or task number is missing.
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
     * and updating the storage file.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save changes persistently.
     * @return A string message confirming the deletion of the task.
     * @throws PlatoException If the specified task number is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        Task task = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        storage.saveTasksToFile(tasks.getAllTasks());

        return "Deleted: " + task; // Return response
    }
}
