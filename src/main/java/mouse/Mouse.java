package mouse;

import mouse.parser.CommandType;
import mouse.parser.Parser;
import mouse.task.TaskList;
import mouse.ui.Ui;

/**
 * Entry point for the Mouse chatbot. Coordinates the UI, parser, and task list.
 */
public class Mouse {
    private final Ui ui;
    private final TaskList tasks;

    public Mouse() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    public static void main(String[] args) {
        new Mouse().run();
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
     * Handles one command. Returns {@code true} when the session should end.
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
            case UNKNOWN:
                throw new MouseException("MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE");
            default:
                throw new MouseException("MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE");
            }
        } catch (MouseException | IndexOutOfBoundsException e) {
            if (e instanceof IndexOutOfBoundsException) {
                ui.showError("That task does not exist GRR");
            } else {
                ui.showError(e.getMessage());
            }
        }
        return false;
    }
}
