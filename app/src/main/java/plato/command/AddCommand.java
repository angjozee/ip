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
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String description;
    private TaskType type;

    /**
     * Constructs an AddCommand with the provided input and task type.
     *
     * @param input The raw user input.
     * @param type  The type of task to add (TODO, DEADLINE, or EVENT).
     */
    public AddCommand(String input, TaskType type) {
        this.type = type;
        this.description = input.substring(input.indexOf(" ") + 1).trim();
    }

    /**
     * Executes the command by adding a task to the task list and saving it to storage.
     *
     * @param tasks   The task list where the new task will be added.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to persist tasks.
     * @throws PlatoException If the input format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlatoException {
        Task task;
        if (type == TaskType.TODO) {
            task = new ToDo(description);
        } else if (type == TaskType.DEADLINE) {
            String[] split = description.split("/by ");
            if (split.length != 2) {
                throw new PlatoException("Invalid format for deadline.");
            }
            validateDateTimeFormat(split[1].trim()); // Validate date format
            task = new Deadline(split[0].trim(), split[1].trim());
        } else {
            String[] split = description.split("/from|/to");
            if (split.length != 3) {
                throw new PlatoException("Invalid format for event.");
            }
            validateDateTimeFormat(split[1].trim()); // Validate from date
            validateDateTimeFormat(split[2].trim()); // Validate to date
            task = new Event(split[0].trim(), split[1].trim(), split[2].trim());
        }

        tasks.addTask(task);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showMessage("Added: " + task);
    }

    /**
     * Validates whether the given date-time string matches the expected format.
     *
     * @param dateTime The date-time string to validate.
     * @throws PlatoException If the format is incorrect.
     */
    private void validateDateTimeFormat(String dateTime) throws PlatoException {
        try {
            LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new PlatoException("Invalid date format! Use: yyyy-MM-dd HHmm (e.g., 2023-12-02 1800).");
        }
    }
}
