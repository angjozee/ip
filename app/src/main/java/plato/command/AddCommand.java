package plato.command;

import plato.exception.PlatoException;
import plato.model.*;
import plato.storage.Storage;
import plato.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String description;
    private TaskType type;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public AddCommand(String input, TaskType type) {
        this.type = type;
        this.description = input.substring(input.indexOf(" ") + 1).trim();
    }

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
            validateDateTimeFormat(split[1].trim());  // Validate date format
            task = new Deadline(split[0].trim(), split[1].trim());
        } else {
            String[] split = description.split("/from|/to");
            if (split.length != 3) {
                throw new PlatoException("Invalid format for event.");
            }
            validateDateTimeFormat(split[1].trim());  // Validate from date
            validateDateTimeFormat(split[2].trim());  // Validate to date
            task = new Event(split[0].trim(), split[1].trim(), split[2].trim());
        }

        tasks.addTask(task);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showMessage("Added: " + task);
    }

    private void validateDateTimeFormat(String dateTime) throws PlatoException {
        try {
            LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new PlatoException("Invalid date format! Use: yyyy-MM-dd HHmm (e.g., 2023-12-02 1800).");
        }
    }
}
