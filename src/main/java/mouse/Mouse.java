package mouse;

import java.util.function.Function;

import mouse.parser.CommandType;
import mouse.parser.Parser;
import mouse.parser.PriorityCommand;
import mouse.storage.Storage;
import mouse.task.Task;
import mouse.task.TaskList;
import mouse.ui.Ui;

/**
 * Entry point for the Mouse chatbot.
 * Coordinates the UI, parser, storage, and task list.
 */
public class Mouse {
    public static final String DEFAULT_SAVE_PATH = "data/mouse.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Creates a Mouse session that saves to {@link #DEFAULT_SAVE_PATH}.
     */
    public Mouse() {
        this(DEFAULT_SAVE_PATH);
    }

    /**
     * Creates a Mouse session that loads and saves at {@code filePath}.
     *
     * @param filePath Path to the save file.
     */
    public Mouse(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = storage.load();
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
        return execute(input.trim());
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
        String trimmed = input.trim();
        ui.showReply(execute(trimmed).split("\n", -1));
        return isExit(trimmed);
    }

    /**
     * Executes one command and returns the reply text.
     * Shared by the CLI loop and the GUI.
     *
     * @param input Trimmed user input.
     * @return Reply text to show the user.
     */
    private String execute(String input) {
        try {
            switch (CommandType.fromInput(input)) {
            case BYE:
                return ui.formatBye();
            case LIST:
                return ui.formatList(tasks);
            case MARK:
                return changeTask(tasks.mark(Parser.parseIndex(input, "mark ")),
                        ui::formatMarked);
            case UNMARK:
                return changeTask(tasks.unmark(Parser.parseIndex(input, "unmark ")),
                        ui::formatUnmarked);
            case DELETE:
                Task deleted = tasks.delete(Parser.parseIndex(input, "delete "));
                storage.save(tasks);
                return ui.formatDeleted(deleted, tasks.size());
            case TODO:
                return addTask(Parser.parseTodo(input));
            case DEADLINE:
                return addTask(Parser.parseDeadline(input));
            case EVENT:
                return addTask(Parser.parseEvent(input));
            case FIND:
                return ui.formatFind(tasks.find(Parser.parseFind(input)));
            case HELP:
                return ui.formatHelp();
            case PRIORITY:
                PriorityCommand priorityCommand = Parser.parsePriority(input);
                return changeTask(tasks.setPriority(
                        priorityCommand.getIndex(), priorityCommand.getPriority()),
                        ui::formatPriority);
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

    private String addTask(Task task) throws MouseException {
        Task added = tasks.add(task);
        storage.save(tasks);
        return ui.formatAdded(added, tasks.size());
    }

    private String changeTask(Task task, Function<Task, String> formatter) throws MouseException {
        storage.save(tasks);
        return formatter.apply(task);
    }
}
