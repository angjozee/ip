package plato.model;

/**
 * Represents a task with description and type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a task with a description and type.
     *
     * @param description The description of the task.
     * @param type The type of task
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
    
    public String toFileFormat() {
        return type.name().charAt(0) + " || " + (isDone ? "X" : " ") + " || " + description.trim();
    }


    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
