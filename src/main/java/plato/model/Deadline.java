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
                        + "(e.g., 2024-22-02 0800)");
        }
    }

    /**
     * Converts the task into a file-friendly format for saving.
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " || " + by.format(inputFormat);
    }

    /**
     * Returns a string representation of the Deadline task, including its due date.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(outputFormat) + ")";
    }
}
