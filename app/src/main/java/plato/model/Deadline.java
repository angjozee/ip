package plato.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = parseDateTime(by);
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use: yyyy-MM-dd HHmm (e.g., 2024-02-22 0800)");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }
}
