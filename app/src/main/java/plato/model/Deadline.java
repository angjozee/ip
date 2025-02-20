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
     * Constructs a Deadline task with a description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time in "yyyy-MM-dd HHmm" format.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = parseDateTime(by);
    }

    /**
     * Parses the date-time string into a LocalDateTime object.
     *
     * @param by The date-time string to be parsed.
     * @return The corresponding LocalDateTime object.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    private LocalDateTime parseDateTime(String by) {
        try {
            return LocalDateTime.parse(by, inputFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use: yyyy-MM-dd HHmm "
                    + "(e.g., 2024-22-02 0800)");
        }
    }

    /**
     * Converts the deadline task to a formatted string suitable for file storage.
     *
     * @return A string representing the deadline task in file format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + "|| " + by.format(inputFormat);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string containing the task description and formatted due date.
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + by.format(outputFormat) + ")";
    }
}
