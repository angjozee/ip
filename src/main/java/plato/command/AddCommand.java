package plato.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import plato.exception.PlatoException;
import plato.model.Deadline;
import plato.model.Event;
import plato.model.Task;
import plato.model.TaskList;
import plato.model.TaskType;
import plato.model.ToDo;
import plato.storage.Storage;
import plato.ui.Ui;


/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String description;
    private TaskType type;

    /**
     * Constructs an AddCommand with the given input and task type.
     *
     * @param input The full input string provided by the user.
     * @param type  The type of task to be added (TODO, DEADLINE, or EVENT).
     */
    public AddCommand(String input, TaskType type) {
        assert input != null && !input.trim().isEmpty() : "Input should not be null or empty";
        assert type != null : "TaskType should not be null";

        this.type = type;
        this.description = input.substring(input.indexOf(" ") + 1).trim();
        assert !this.description.isEmpty() : "Description should not be empty";
    }

    /**
     * Executes the command by adding a task to the task list and saving it to storage.
     *
     * @param tasks   The task list where the new task will be added.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save tasks persistently.
     * @throws PlatoException If the input format is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        Task task;
        if (type == TaskType.TODO) {
            task = new ToDo(description);
        } else if (type == TaskType.DEADLINE) {
            String[] split = description.split("/by ");
            assert split.length > 0 : "Deadline task should have a description";
            assert split.length == 2 : "Deadline task should be split into two parts";

            validateDateTimeFormat(split[1].trim());
            task = new Deadline(split[0].trim(), split[1].trim());
        } else {
            String[] split = description.split("/from|/to");
            assert split.length > 0 : "Event task should have a description";
            assert split.length == 3 : "Event task should have exactly three parts";

            validateDateTimeFormat(split[1].trim());
            validateDateTimeFormat(split[2].trim());
            task = new Event(split[0].trim(), split[1].trim(), split[2].trim());
        }

        assert task != null : "Task creation failed";

        tasks.addTask(task);
        storage.saveTasksToFile(tasks.getAllTasks());

        return "Added: " + task;
    }

    /**
     * Validates whether the given date-time string matches the expected format.
     *
     * @param dateTime The date-time string to be validated.
     * @throws PlatoException If the format is incorrect.
     */
    private void validateDateTimeFormat(String dateTime) throws PlatoException {
        assert dateTime != null && !dateTime.trim().isEmpty() : "Date-time string should not be null or empty";

        try {
            LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new PlatoException("Invalid date format! Use: yyyy-MM-dd HHmm (e.g., 2023-12-02 1800).");
        }
    }
}
