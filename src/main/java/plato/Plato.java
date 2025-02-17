package plato;

import plato.command.Command;
import plato.exception.PlatoException;
import plato.parser.Parser;
import plato.storage.Storage;
import plato.model.TaskList;
import plato.ui.Ui;

public class Plato {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Plato(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (PlatoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (PlatoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Plato("./data/tasks.txt").run();
    }
}
