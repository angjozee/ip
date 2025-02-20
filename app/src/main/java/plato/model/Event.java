package plato.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {

    private static final DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputFormat =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event in the format "yyyy-MM-dd HHmm".
     * @param to The end time of the event in the format "yyyy-MM-dd HHmm".
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTime The date-time string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws IllegalArgumentException If the date-time format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, inputFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
        }
    }

    /**
     * Converts the event task to a formatted string suitable for file storage.
     *
     * @return A string representing the event in file format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + "|| "
                + from.format(inputFormat) + " -> " + to.format(inputFormat);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string containing the event description, start time, and end time.
     */
    @Override
    public String toString() {
        return super.toString() + "(from: " + from.format(outputFormat)
                + " to: " + to.format(outputFormat) + ")";
    }
}
