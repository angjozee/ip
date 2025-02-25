package plato.command;

import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

/**
 * Abstract base class for all chatbot commands.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException;

    // Ensure this method exists
    public boolean isExit() {
        return false; // Default behavior: most commands do not exit
    }
}
