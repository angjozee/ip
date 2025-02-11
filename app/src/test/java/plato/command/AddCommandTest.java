package plato.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plato.exception.PlatoException;
import plato.model.TaskList;
import plato.model.TaskType;
import plato.storage.Storage;
import plato.ui.Ui;

import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("testTasks.txt");  // Can mock this in real scenarios
    }

    @Test
    public void testAddToDo() {
        AddCommand addCommand = new AddCommand("todo Read a book", TaskType.TODO);
        assertDoesNotThrow(() -> addCommand.execute(taskList, ui, storage));

        assertEquals(1, taskList.size(), "Task list should have 1 task after adding a ToDo");
        assertEquals("Read a book", taskList.getAllTasks().get(0).getDescription(), "The task description should match the input");
    }

    @Test
    public void testAddDeadline() {
        AddCommand addCommand = new AddCommand("deadline Submit report /by 2025-02-14 2359", TaskType.DEADLINE);
        assertDoesNotThrow(() -> addCommand.execute(taskList, ui, storage));

        assertEquals(1, taskList.size(), "Task list should have 1 task after adding a Deadline");
        assertTrue(taskList.getAllTasks().get(0).toString().contains("Submit report"), "Deadline task description should match the input");
        assertTrue(taskList.getAllTasks().get(0).toString().contains("Feb 14 2025, 11:59 pm"),
                "Deadline date should match the input format in the toString() method");
    }

    @Test
    public void testAddEvent() {
        AddCommand addCommand = new AddCommand("event Meeting /from 2025-02-14 1400 /to 2025-02-14 1600", TaskType.EVENT);
        assertDoesNotThrow(() -> addCommand.execute(taskList, ui, storage));

        assertEquals(1, taskList.size(), "Task list should have 1 task after adding an Event");
        assertTrue(taskList.getAllTasks().get(0).toString().contains("Meeting"), "Event task description should match the input");
        assertTrue(taskList.getAllTasks().get(0).toString().contains("Feb 14 2025, 2:00 pm"),
                "Event start time should match the input format in the toString() method");
        assertTrue(taskList.getAllTasks().get(0).toString().contains("Feb 14 2025, 4:00 pm"), "Event end time should match the input");
    }

    @Test
    public void testInvalidDeadlineFormat() {
        AddCommand addCommand = new AddCommand("deadline Finish assignment /by wrong-date", TaskType.DEADLINE);
        Exception exception = assertThrows(PlatoException.class, () -> addCommand.execute(taskList, ui, storage));
        assertEquals("Invalid date format! Use: yyyy-MM-dd HHmm (e.g., 2023-12-02 1800).", exception.getMessage());
    }

    @Test
    public void testInvalidEventFormat() {
        AddCommand addCommand = new AddCommand("event Team meeting /from 2025-02-14 1400", TaskType.EVENT);
        Exception exception = assertThrows(PlatoException.class, () -> addCommand.execute(taskList, ui, storage));
        assertEquals("Invalid format for event.", exception.getMessage());
    }
}
