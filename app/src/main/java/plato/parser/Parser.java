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
    public static Task parseTask(String line) throws PlatoException {
        String[] parts = line.split(" \\|\\| ");

        // Ensure the line has at least 3 parts (task type, status, description)
        if (parts.length < 3) {
            throw new PlatoException("Corrupted task data (invalid format): " + line);
        }

        String type = parts[0].trim();
        String status = parts[1].trim(); // Can be "X" or empty
        String description = parts[2].trim(); // Always present

        if (description.isEmpty()) {
            throw new PlatoException("Invalid task format: Missing description in " + line);
        }

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            if (parts.length != 4) throw new PlatoException("Invalid deadline task format: " + line);
            task = new Deadline(description, parts[3].trim());
            break;
        case "E":
            if (parts.length != 5) throw new PlatoException("Invalid event task format: " + line);
            task = new Event(description, parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new PlatoException("Unknown task type: " + type);
        }

        // Fix: Handle empty status field correctly
        if (!status.isEmpty() && status.equals("X")) {
            task.markAsDone();
        }

        return task;
    }


}