/**
 * User commands Mouse understands.
 */
public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    UNKNOWN;

    /**
     * Identifies the command from a raw input line.
     */
    public static CommandType fromInput(String input) {
        String trimmed = input.trim();
        if (trimmed.equals("bye")) {
            return BYE;
        }
        if (trimmed.equals("list")) {
            return LIST;
        }
        if (trimmed.startsWith("mark ")) {
            return MARK;
        }
        if (trimmed.startsWith("unmark ")) {
            return UNMARK;
        }
        if (trimmed.startsWith("delete ")) {
            return DELETE;
        }
        if (trimmed.equals("todo") || trimmed.startsWith("todo ")) {
            return TODO;
        }
        if (trimmed.startsWith("deadline ")) {
            return DEADLINE;
        }
        if (trimmed.startsWith("event ")) {
            return EVENT;
        }
        return UNKNOWN;
    }
}
