/**
 * Extracts command details from a raw user input line.
 */
public class Parser {
    /**
     * Parses {@code todo DESCRIPTION} into a {@link ToDo}.
     */
    public static ToDo parseTodo(String input) {
        String description = input.substring("todo ".length()).trim();
        return new ToDo(description);
    }

    /**
     * Parses {@code deadline DESCRIPTION /by WHEN} into a {@link Deadline}.
     */
    public static Deadline parseDeadline(String input) {
        String rest = input.substring("deadline ".length()).trim();
        int byIndex = rest.indexOf("/by");
        String description = rest.substring(0, byIndex).trim();
        String by = rest.substring(byIndex + "/by".length()).trim();
        return new Deadline(description, by);
    }

    /**
     * Parses {@code event DESCRIPTION /from START /to END} into an {@link Event}.
     */
    public static Event parseEvent(String input) {
        String rest = input.substring("event ".length()).trim();
        int fromIndex = rest.indexOf("/from");
        int toIndex = rest.indexOf("/to");
        String description = rest.substring(0, fromIndex).trim();
        String from = rest.substring(fromIndex + "/from".length(), toIndex).trim();
        String to = rest.substring(toIndex + "/to".length()).trim();
        return new Event(description, from, to);
    }

    /**
     * Parses the 1-based task number after {@code prefix}, as a 0-based index.
     */
    public static int parseIndex(String input, String prefix) {
        return Integer.parseInt(input.substring(prefix.length()).trim()) - 1;
    }
}
