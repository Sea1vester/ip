package mouse.task;

/**
 * A task that starts and ends at given dates or times.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an event with a start and end time.
     *
     * @param description Task description.
     * @param from Start date or time text.
     * @param to End date or time text.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the event with type letter and from/to range.
     */
    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getSymbol() + "]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }
}
