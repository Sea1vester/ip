package mouse.task;

/**
 * A task with a description and done status.
 * Base type for to-dos, deadlines, and events.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns {@code X} if done, otherwise a space.
     *
     * @return Status icon character as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return Description text.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon and description, for example {@code [ ] borrow book}.
     *
     * @return Display string without the task-type letter.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
