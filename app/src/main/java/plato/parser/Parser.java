package plato.parser;

import plato.command.*;
import plato.exception.PlatoException;
import plato.model.*;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate command.
     *
     * @param userInput The raw user input string.
     * @return The corresponding Command object.
     * @throws PlatoException If the command is unknown or incorrectly formatted.
     */
    public static Command parse(String userInput) throws PlatoException {
        if (userInput.equals("tasks?")) {
            return new ListCommand(); // Handle the tasks? command
        } else if (userInput.startsWith("todo")) {
            return new AddCommand(userInput, TaskType.TODO);
        } else if (userInput.startsWith("deadline")) {
            return new AddCommand(userInput, TaskType.DEADLINE);
        } else if (userInput.startsWith("event")) {
            return new AddCommand(userInput, TaskType.EVENT);
        } else if (userInput.startsWith("mark")) {
            return new MarkCommand(userInput, true);
        } else if (userInput.startsWith("unmark")) {
            return new MarkCommand(userInput, false);
        } else if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.startsWith("find")) {
            String keyword = userInput.substring(5).trim();
            return new FindCommand(keyword);
        } else if (userInput.equals("Farewell")) {
            return new ExitCommand();
        } else {
            throw new PlatoException("Unknown command.");
        }
    }

    /**
     * Parses a task string from storage and returns a Task object.
     *
     * @param line A line from the storage file representing a task.
     * @return The corresponding Task object.
     */
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task;
        boolean isDone = parts[1].trim().equals("X");
        switch (parts[0]) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type in file.");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
