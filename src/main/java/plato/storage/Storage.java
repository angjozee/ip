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
 * Handles file operations for loading and saving tasks.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with a specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     * If the file does not exist, returns an empty task list.
     *
     * @return A list of tasks loaded from the file.
     * @throws PlatoException If an error occurs while reading the file.
     */
    public List<Task> loadTasksFromFile() throws PlatoException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return an empty list if the file doesn't exist
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(Parser.parseTask(line));
            }
        } catch (IOException e) {
            throw new PlatoException("Error reading tasks from file.");
        }
        return tasks;
    }

    /**
     * Saves a list of tasks to the specified file.
     * If the file's parent directory does not exist, it is created.
     *
     * @param tasks The list of tasks to be saved.
     * @throws PlatoException If an error occurs while writing to the file.
     */
    public void saveTasksToFile(List<Task> tasks) throws PlatoException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            // Create the folder if it doesn't exist
            if (directory != null && !directory.exists()) {
                boolean dirCreated = directory.mkdirs();
                if (!dirCreated) {
                    throw new PlatoException("Failed to create directory: " + directory.getAbsolutePath());
                }
            }

            // Write tasks to file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (Task task : tasks) {
                    bw.write(task.toFileFormat());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new PlatoException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
