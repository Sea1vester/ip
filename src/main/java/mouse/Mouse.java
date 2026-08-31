package mouse;

import mouse.parser.CommandType;
import mouse.parser.Parser;
import mouse.task.TaskList;
import mouse.ui.Ui;

/**
 * Entry point for the Mouse chatbot.
 * Coordinates the UI, parser, and task list.
 */
public class Mouse {
    private final Ui ui;
    private final TaskList tasks;

    /**
     * Creates a Mouse session with an empty task list.
     */
    public Mouse() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    /**
     * Starts the chatbot from the command line.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Mouse().run();
    }

    /**
     * Returns the GUI greeting.
     *
     * @return Greeting text for a chat bubble.
     */
    public String getGreeting() {
        return ui.formatGreeting();
    }

    /**
     * Returns whether {@code input} is the exit command.
     *
     * @param input Raw user input.
     * @return {@code true} if the session should end.
     */
    public boolean isExit(String input) {
        return CommandType.fromInput(input.trim()) == CommandType.BYE;
    }

    /**
     * Returns Mouse's reply for one command, for the GUI.
     *
     * @param input Raw user input.
     * @return Reply text to show in a dialog bubble.
     */
    public String getResponse(String input) {
        input = input.trim();
        try {
            switch (CommandType.fromInput(input)) {
            case BYE:
                return ui.formatBye();
            case LIST:
                return ui.formatList(tasks);
            case MARK:
                return ui.formatMarked(tasks.mark(Parser.parseIndex(input, "mark ")));
            case UNMARK:
                return ui.formatUnmarked(tasks.unmark(Parser.parseIndex(input, "unmark ")));
            case DELETE:
                return ui.formatDeleted(tasks.delete(Parser.parseIndex(input, "delete ")), tasks.size());
            case TODO:
                return ui.formatAdded(tasks.add(Parser.parseTodo(input)), tasks.size());
            case DEADLINE:
                return ui.formatAdded(tasks.add(Parser.parseDeadline(input)), tasks.size());
            case EVENT:
                return ui.formatAdded(tasks.add(Parser.parseEvent(input)), tasks.size());
            case FIND:
                return ui.formatFind(tasks.find(Parser.parseFind(input)));
            case HELP:
                return ui.formatHelp();
            case UNKNOWN:
                throw new MouseException("MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE");
            default:
                throw new MouseException("MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE");
            }
        } catch (MouseException | IndexOutOfBoundsException exception) {
            if (exception instanceof IndexOutOfBoundsException) {
                return ui.formatError("That task does not exist GRR");
            }
            return ui.formatError(exception.getMessage());
        }
    }

    /**
     * Greets the user, then handles commands until {@code bye}.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            isExit = handleCommand(ui.readCommand());
        }
    }

    /**
     * Handles one raw command line.
     * Returns {@code true} when the session should end.
     *
     * @param input Raw user input.
     * @return Whether the chatbot should exit.
     */
    private boolean handleCommand(String input) {
        input = input.trim();
        try {
            switch (CommandType.fromInput(input)) {
            case BYE:
                ui.showBye();
                return true;
            case LIST:
                ui.showList(tasks);
                break;
            case MARK:
                ui.showMarked(tasks.mark(Parser.parseIndex(input, "mark ")));
                break;
            case UNMARK:
                ui.showUnmarked(tasks.unmark(Parser.parseIndex(input, "unmark ")));
                break;
            case DELETE:
                ui.showDeleted(tasks.delete(Parser.parseIndex(input, "delete ")), tasks.size());
                break;
            case TODO:
                ui.showAdded(tasks.add(Parser.parseTodo(input)), tasks.size());
                break;
            case DEADLINE:
                ui.showAdded(tasks.add(Parser.parseDeadline(input)), tasks.size());
                break;
            case EVENT:
                ui.showAdded(tasks.add(Parser.parseEvent(input)), tasks.size());
                break;
            case FIND:
                ui.showFind(tasks.find(Parser.parseFind(input)));
                break;
            case HELP:
                ui.showHelp();
                break;
            case UNKNOWN:
                throw new MouseException("MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE");
            default:
                throw new MouseException("MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE");
            }
        } catch (MouseException | IndexOutOfBoundsException exception) {
            if (exception instanceof IndexOutOfBoundsException) {
                ui.showError("That task does not exist GRR");
            } else {
                ui.showError(exception.getMessage());
            }
        }
        return false;
    }
}
