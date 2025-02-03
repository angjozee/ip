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
        // Implement parsing logic to load tasks from file
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