package plato.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import plato.model.Deadline;
import plato.model.Event;
import plato.model.Task;
import plato.model.ToDo;

/**
 * Handles file operations for saving and loading tasks in the Plato chatbot.
 */
public class PlatoFileWriter {

    /**
     * Saves a list of tasks to a file. If the directory does not exist, it is created.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasksToFile(List<Task> tasks) {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("./data/plato.txt"))) {
                for (Task task : tasks) {
                    bw.write(task.toFileFormat());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file and returns them as a list.
     * If the file does not exist, an empty list is returned.
     * Corrupted lines are skipped with warnings.
     *
     * @return A list of tasks loaded from the file.
     */
    public static List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        File file = new File("./data/plato.txt");
        if (!file.exists()) {
            return tasks; // Return an empty list if the file doesn't exist.
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" # ");
                if (parts.length < 3) {
                    System.out.println("Warning: Skipping corrupted line - " + line);
                    continue; // Skip corrupted lines.
                }

                Task task;
                switch (parts[0]) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    String[] deadlineParts = parts[2].split(" \\| ");
                    if (deadlineParts.length != 2) {
                        System.out.println("Warning: Skipping corrupted deadline task - " + line);
                        continue;
                    }
                    task = new Deadline(deadlineParts[0], deadlineParts[1]);
                    break;
                case "E":
                    String[] eventParts = parts[2].split(" \\| ");
                    if (eventParts.length != 3) {
                        System.out.println("Warning: Skipping corrupted event task - " + line);
                        continue;
                    }
                    task = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    break;
                default:
                    System.out.println("Warning: Unknown task type - " + line);
                    continue;
                }

                if (parts[1].equals("X")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }
}
