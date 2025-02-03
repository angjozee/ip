package plato.command;

import plato.exception.PlatoException;
import plato.model.Task;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isMark;

    public MarkCommand(String input, boolean isMark) throws PlatoException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new PlatoException("Invalid format for mark/unmark.");
        }
        this.taskNumber = Integer.parseInt(parts[1]) - 1;
        this.isMark = isMark;
    }

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