package mouse.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores a dynamic list of tasks and updates their status.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns how many tasks are stored.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given 0-based index.
     *
     * @param index Zero-based index.
     * @return Task at that index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds {@code task} and returns it.
     *
     * @param task Task to add.
     * @return The same task.
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Marks the task at the 0-based {@code index} as done.
     *
     * @param index Zero-based index.
     * @return The marked task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task mark(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the 0-based {@code index} as not done.
     *
     * @param index Zero-based index.
     * @return The unmarked task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task unmark(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Removes and returns the task at the 0-based {@code index}.
     *
     * @param index Zero-based index.
     * @return The removed task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task delete(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        return tasks.remove(index);
    }

    /**
     * Returns tasks whose description contains {@code keyword} (case-insensitive).
     *
     * @param keyword Text to look for in descriptions.
     * @return A new list of matching tasks, numbered independently when printed.
     */
    public TaskList find(String keyword) {
        TaskList matches = new TaskList();
        String needle = keyword.toLowerCase();
        tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(needle))
                .forEach(matches::add);
        return matches;
    }
}
