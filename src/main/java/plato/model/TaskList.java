package plato.model;

import java.util.ArrayList;
import java.util.List;

import plato.exception.PlatoException;

/**
 * Represents a list of tasks managed by the chatbot.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with an existing list of tasks.
     *
     * @param tasks A list of tasks to initialize the task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task in the list (0-based).
     * @return The task at the specified index.
     * @throws PlatoException If the index is out of range.
     */
    public Task getTask(int index) throws PlatoException {
        if (index < 0 || index >= tasks.size()) {
            throw new PlatoException("Task number out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to be removed (0-based).
     * @throws PlatoException If the index is out of range.
     */
    public void deleteTask(int index) throws PlatoException {
        if (index < 0 || index >= tasks.size()) {
            throw new PlatoException("Task number out of range.");
        }
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves all tasks in the task list.
     *
     * @return A list containing all tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}
