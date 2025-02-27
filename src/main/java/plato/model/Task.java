package plato.model;

/**
 * Represents a task with a description, completion status, and task type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a new Task with the given description and type.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     * @param type The type of the task (e.g., TODO, DEADLINE, EVENT).
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Retrieves the status icon representing whether the task is done.
     *
     * @return "X" if the task is completed, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done tasks with X
    }

    /**
     * Retrieves the task description.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Converts the task into a formatted string for file storage.
     *
     * @return A string representation of the task in file format.
     */
    public String toFileFormat() {
        return type.name().charAt(0) + " || " + getStatusIcon() + " || " + description;
    }

    /**
     * Returns a string representation of the task, including its type and status.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
