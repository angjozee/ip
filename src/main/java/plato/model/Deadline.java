package plato.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) {
        try {
            return LocalDateTime.parse(by, inputFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use: yyyy-MM-dd HHmm " + 
                    "(e.g., 2024-22-02 0800)");
        }
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " || " + by.format(inputFormat);
    }


    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(outputFormat) + ")";
    }
}
