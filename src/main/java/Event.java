import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputFormat =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");


    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, inputFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
        }
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + "|| " +
                from.format(inputFormat) + " -> " + to.format(inputFormat);
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + from.format(outputFormat) +
                " to: " + to.format(outputFormat) + ")";
    }
}
