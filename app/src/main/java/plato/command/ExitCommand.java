package plato.command;

import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Farewell. Hope to see you soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
