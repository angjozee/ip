package plato.model;

import java.util.ArrayList;
import java.util.List;

import plato.exception.PlatoException;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) throws PlatoException {
        if (index < 0 || index >= tasks.size()) {
            throw new PlatoException("Task number out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Represents a method to delete a task based on the index in the list.
     */
    public void deleteTask(int index) throws PlatoException {
        if (index < 0 || index >= tasks.size()) {
            throw new PlatoException("Task number out of range.");
        }
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
