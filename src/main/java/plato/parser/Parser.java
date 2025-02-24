package plato.parser;

import plato.command.*;
import plato.exception.PlatoException;
import plato.model.*;

public class Parser {
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
        } else if (userInput.equals("Farewell")) {
            return new ExitCommand();
        } else {
            throw new PlatoException("Unknown command.");
        }
    }

    public static Task parseTask(String line) {
        System.out.println("DEBUG: Reading line -> " + line); // Debugging

        String[] parts = line.split(" \\|\\| ");
        System.out.println("DEBUG: Split parts length -> " + parts.length); // Debugging

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format: " + line);
        }

        Task task;
        boolean isDone = parts[1].trim().equals("X"); // Trim spaces

        switch (parts[0]) {
        case "T":
            task = new ToDo(parts[2].trim());
            break;
        case "D":
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid deadline format: " + line);
            }
            task = new Deadline(parts[2].trim(), parts[3].trim()); // Ensure deadline date is properly parsed
            break;
        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Invalid event format: " + line);
            }
            System.out.println("DEBUG: Creating Event task with from -> " + parts[3] + ", to -> " + parts[4]); // Debugging
            task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new IllegalArgumentException("Unknown task type in file: " + line);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}