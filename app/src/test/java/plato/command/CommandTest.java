package plato.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.storage.Storage;
import plato.ui.Ui;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("testTasks.txt");  // Can be mocked if needed
    }

    @Test
    public void testIsExitDefault() {
        Command mockCommand = new MockCommand();
        assertFalse(mockCommand.isExit(), "By default, isExit() should return false");
    }

    @Test
    public void testExecuteMockCommand() {
        Command mockCommand = new MockCommand();
        assertDoesNotThrow(() -> mockCommand.execute(taskList, ui, storage), "MockCommand should execute without throwing exceptions");
    }

    @Test
    public void testPlatoExceptionInFailingCommand() {
        Command failingCommand = new FailingCommand();
        Exception exception = assertThrows(PlatoException.class, () -> failingCommand.execute(taskList, ui, storage));
        assertEquals("Intentional failure for testing.", exception.getMessage());
    }

    // Mock command that performs basic execution
    private static class MockCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showMessage("MockCommand executed.");
        }
    }

    // Mock command that deliberately throws an exception
    private static class FailingCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
            throw new PlatoException("Intentional failure for testing.");
        }
    }
}
