package plato.model;

/**
 * Represents a todo with description and type.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat();
    }
}


