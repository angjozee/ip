package plato.command;

import plato.exception.PlatoException;
import plato.model.Task;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Represents a command to mark or unmark a task as done.
 */
public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isMark;

    /**
     * Constructs a MarkCommand to either mark or unmark a task.
     *
     * @param input  The full input string provided by the user.
     * @param isMark {@code true} if marking the task as done, {@code false} if unmarking.
     * @throws PlatoException If the input format is invalid.
     */
    public MarkCommand(String input, boolean isMark) throws PlatoException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new PlatoException("Invalid format for mark/unmark.");
        }
        this.taskNumber = Integer.parseInt(parts[1]) - 1;
        this.isMark = isMark;
    }

    /**
     * Executes the mark/unmark command by updating the task's completion status
     * and saving the changes to storage.
     *
     * @param tasks   The task list containing the task to be updated.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to persist task changes.
     * @return A message indicating the updated task status.
     * @throws PlatoException If the specified task number is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        Task task = tasks.getTask(taskNumber);
        if (isMark) {
            task.markAsDone();
            storage.saveTasksToFile(tasks.getAllTasks());
            return "Marked as done: " + task; // Return response
        } else {
            task.markAsNotDone();
            storage.saveTasksToFile(tasks.getAllTasks());
            return "Marked as not done: " + task; // Return response
        }
    }
}
