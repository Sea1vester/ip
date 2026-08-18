import java.util.ArrayList;
import java.util.List;

/**
 * Stores a dynamic list of tasks and updates their status.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds {@code task} and returns it.
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Marks the task at the 0-based {@code index} as done.
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
     */
    public Task delete(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        return tasks.remove(index);
    }
}
