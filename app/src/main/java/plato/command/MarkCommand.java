package plato.command;

import plato.exception.PlatoException;
import plato.model.Task;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Represents a command to mark tasks as done or undone.
 */
public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isMark;

    /**
     * Constructs a MarkCommand by extracting the task number and mark status from user input.
     *
     * @param input  The raw user input specifying which task to mark or unmark.
     * @param isMark {@code true} if marking as done, {@code false} if marking as not done.
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
     * Executes the mark command by updating the task's completion status and saving the change.
     *
     * @param tasks   The task list containing the task to be marked or unmarked.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to persist the changes.
     * @throws PlatoException If an error occurs while accessing the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        Task task = tasks.getTask(taskNumber);
        if (isMark) {
            task.markAsDone();
            ui.showMessage("Marked as done: " + task);
        } else {
            task.markAsNotDone();
            ui.showMessage("Marked as not done: " + task);
        }
        storage.saveTasksToFile(tasks.getAllTasks());
    }
}
