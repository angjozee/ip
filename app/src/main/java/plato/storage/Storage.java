package plato.storage;

import plato.exception.PlatoException;
import plato.model.Task;
import plato.parser.Parser;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public java.util.List<Task> loadTasksFromFile() throws PlatoException {
        java.util.List<Task> tasks = new java.util.ArrayList<>();
        java.io.File file = new java.io.File(filePath);
        if (!file.exists()) {
            return tasks; // Return an empty list if the file doesn't exist
        }

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(Parser.parseTask(line));
            }
        } catch (java.io.IOException e) {
            throw new PlatoException("Error reading tasks from file.");
        }
        return tasks;
    }

    public void saveTasksToFile(java.util.List<Task> tasks) throws PlatoException {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            throw new PlatoException("Error saving tasks to file.");
        }
    }
}