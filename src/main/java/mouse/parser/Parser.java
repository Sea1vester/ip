package mouse.parser;

import mouse.MouseException;
import mouse.task.Deadline;
import mouse.task.Event;
import mouse.task.ToDo;

/**
 * Extracts command details from a raw user input line.
 */
public class Parser {
    /**
     * Parses {@code todo DESCRIPTION} into a {@link ToDo}.
     *
     * @param input Full command line starting with {@code todo}.
     * @return Parsed to-do task.
     * @throws MouseException If the description is missing or empty.
     */
    public static ToDo parseTodo(String input) throws MouseException {
        if (input.trim().equals("todo")) {
            throw new MouseException("The description of a todo cannot be empty GRR");
        }

        String description = input.substring("todo ".length()).trim();
        if (description.isEmpty()) {
            throw new MouseException("The description of a todo cannot be empty GRR");
        }

        return new ToDo(description);
    }

    /**
     * Parses {@code deadline DESCRIPTION /by WHEN} into a {@link Deadline}.
     *
     * @param input Full command line starting with {@code deadline}.
     * @return Parsed deadline task.
     * @throws MouseException If {@code /by}, the description, or the time is missing.
     */
    public static Deadline parseDeadline(String input) throws MouseException {
        String rest = input.substring("deadline ".length()).trim();
        int byIndex = rest.indexOf("/by");
        if (byIndex == -1) {
            throw new MouseException("A deadline needs a '/by' time GRR");
        }
        String description = rest.substring(0, byIndex).trim();
        if (description.isEmpty()) {
            throw new MouseException("The description of a deadline cannot be empty GRR");
        }
        String by = rest.substring(byIndex + "/by".length()).trim();
        if (by.isEmpty()) {
            throw new MouseException("The '/by' time cannot be empty GRR");
        }
        return new Deadline(description, by);
    }

    /**
     * Parses {@code event DESCRIPTION /from START /to END} into an {@link Event}.
     *
     * @param input Full command line starting with {@code event}.
     * @return Parsed event task.
     * @throws MouseException If delimiters, description, or times are invalid.
     */
    public static Event parseEvent(String input) throws MouseException {
        String rest = input.substring("event ".length()).trim();
        int fromIndex = rest.indexOf("/from");
        int toIndex = rest.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new MouseException("An event needs both '/from' and '/to' times GRR");
        }
        if (fromIndex > toIndex) {
            throw new MouseException("The '/from' time must come before the '/to' time GRR");
        }
        String description = rest.substring(0, fromIndex).trim();
        if (description.isEmpty()) {
            throw new MouseException("The description of an event cannot be empty GRR");
        }
        String from = rest.substring(fromIndex + "/from".length(), toIndex).trim();
        if (from.isEmpty()) {
            throw new MouseException("The '/from' time cannot be empty GRR");
        }
        String to = rest.substring(toIndex + "/to".length()).trim();
        if (to.isEmpty()) {
            throw new MouseException("The '/to' time cannot be empty GRR");
        }
        return new Event(description, from, to);
    }

    /**
     * Parses the 1-based task number after {@code prefix} as a 0-based index.
     *
     * @param input Full command line, for example {@code mark 2}.
     * @param prefix Command prefix including the trailing space, for example {@code mark }.
     * @return Zero-based task index.
     * @throws MouseException If the number is missing or not an integer.
     */
    public static int parseIndex(String input, String prefix) throws MouseException {
        try {
            return Integer.parseInt(input.substring(prefix.length()).trim()) - 1;
        } catch (NumberFormatException exception) {
            throw new MouseException("That's not a valid task number GRR");
        }
    }
}
