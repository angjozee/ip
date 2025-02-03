package plato.command;

import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

public class ListCommand extends Command {
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
