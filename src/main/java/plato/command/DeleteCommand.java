package plato.command;

import plato.exception.PlatoException;
import plato.model.Task;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(String input) throws PlatoException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new PlatoException("Invalid format for delete.");
        }
        this.taskNumber = Integer.parseInt(parts[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        Task task = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        storage.saveTasksToFile(tasks.getAllTasks());

        return "Deleted: " + task; // Return response
    }

}