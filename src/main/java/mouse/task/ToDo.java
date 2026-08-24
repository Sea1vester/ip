package mouse.task;

/**
 * A task with no date or time attached.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TODO.getSymbol() + "]" + super.toString();
    }
}
