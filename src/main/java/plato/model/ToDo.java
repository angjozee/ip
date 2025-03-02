package plato.model;

/**
 * Represents a To-Do task with a description and priority.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a description and default priority (MEDIUM).
     *
     * @param description The task description.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Constructs a ToDo task with a description and a specific priority.
     *
     * @param description The task description.
     * @param priority The task priority.
     */
    public ToDo(String description, Priority priority) {
        super(description, TaskType.TODO);
        this.priority = priority;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat();
    }
}
