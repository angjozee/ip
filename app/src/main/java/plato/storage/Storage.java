package plato.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import plato.exception.PlatoException;
import plato.model.Task;
import plato.parser.Parser;

/**
 * Handles file operations for saving and loading tasks.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object to handle reading and writing tasks to a file.
     *
     * @param filePath The file path for storing task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return A list of tasks retrieved from the file.
     * @throws PlatoException If an error occurs while reading the file.
     */
    public List<Task> loadTasksFromFile() throws PlatoException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        System.out.println("DEBUG: Looking for file at -> " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("ERROR: File does not exist.");
            return tasks; // Return empty list if file is missing
        }

        System.out.println("DEBUG: Loading tasks from " + filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("DEBUG: Read line -> '" + line + "'");
                try {
                    Task task = Parser.parseTask(line);
                    tasks.add(task);
                    System.out.println("DEBUG: Successfully loaded -> " + task);
                } catch (IllegalArgumentException e) {
                    System.out.println("WARNING: Skipping invalid task -> " + line);
                }
            }
        } catch (IOException e) {
            throw new PlatoException("Error reading tasks from file.");
        }

        System.out.println("DEBUG: Loaded " + tasks.size() + " tasks.");
        return tasks;
    }
    
    /**
     * Saves the list of tasks to the specified file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws PlatoException If an error occurs while writing to the file.
     */
    public void saveTasksToFile(List<Task> tasks) throws PlatoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new PlatoException("Error saving tasks to file.");
        }
    }
}
