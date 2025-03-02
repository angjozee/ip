package plato.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with a given description and due date.
     *
     * @param description The description of the task.
     * @param by The due date and time in the format "yyyy-MM-dd HHmm".
     * @throws IllegalArgumentException If the provided date format is invalid.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = parseDateTime(by);
    }

    /**
     * Constructs a Deadline task with a given description, due date, and priority.
     *
     * @param description The description of the task.
     * @param by The due date and time in the format "yyyy-MM-dd HHmm".
     * @param priority The priority level of the task.
     * @throws IllegalArgumentException If the provided date format is invalid.
     */
    public Deadline(String description, String by, Priority priority) {
        super(description, TaskType.DEADLINE);
        this.by = parseDateTime(by);
        this.priority = priority;
    }

    /**
     * Updates the deadline date.
     *
     * @param newBy The new due date in the format "yyyy-MM-dd HHmm".
     */
    public void snooze(String newBy) {
        this.by = parseDateTime(newBy);
    }

    /**
     * Parses a date-time string into a {@link LocalDateTime} object.
     *
     * @param by The date-time string in the format "yyyy-MM-dd HHmm".
     * @return A {@link LocalDateTime} representation of the input date.
     * @throws IllegalArgumentException If the date format is incorrect.
     */
    private LocalDateTime parseDateTime(String by) {
        try {
            return LocalDateTime.parse(by, inputFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date format! Use: yyyy-MM-dd HHmm "
                            + "(e.g., 2024-12-02 0800)");
        }
    }

    /**
     * Retrieves the formatted deadline date.
     *
     * @return A formatted string representation of the deadline.
     */
    public String getFormattedDate() {
        return by.format(outputFormat);
    }

    /**
     * Converts the task into a file-friendly format for saving.
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D || " + (isDone ? "X" : " ") + " || " + description + " || "
                + by.format(inputFormat) + " || " + priority.name();
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description
                + " (by: " + by.format(outputFormat) + ", Priority: " + priority + ")";
    }
}
