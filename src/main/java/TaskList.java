/**
 * Stores up to {@value #MAX_TASKS} tasks and updates their status.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;

    private final Task[] tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

    public int size() {
        return taskCount;
    }

    public Task get(int index) {
        return tasks[index];
    }

    /**
     * Adds {@code task} and returns it.
     */
    public Task add(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        return task;
    }

    /**
     * Marks the task at the 0-based {@code index} as done.
     */
    public Task mark(int index) {
        Task task = tasks[index];
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the 0-based {@code index} as not done.
     */
    public Task unmark(int index) {
        Task task = tasks[index];
        task.markAsNotDone();
        return task;
    }
}
