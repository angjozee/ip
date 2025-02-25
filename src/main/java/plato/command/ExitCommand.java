package plato.command;

import javafx.application.Platform;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit(); // Closes the JavaFX application
        return "Farewell. Hope to see you soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
