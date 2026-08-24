package mouse.task;

/**
 * A task with no date or time attached.
 */
public class ToDo extends Task {
    /**
     * Creates a to-do with the given description.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the to-do with type letter {@code T}.
     */
    @Override
    public String toString() {
        return "[" + TaskType.TODO.getSymbol() + "]" + super.toString();
    }
}
