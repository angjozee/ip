package plato.model;

/**
 * Represents a To-Do task, which is a basic task without any deadlines or time constraints.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a given description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Converts the task into a file-friendly format for saving.
     *
     * @return A formatted string representation of the ToDo task for file storage.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat();
    }
}
