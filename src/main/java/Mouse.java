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
        while (true) {
            String input = ui.readCommand();
            if (input.equals("bye")) {
                ui.showBye();
                break;
            }
            handleCommand(input);
        }
    }

    private void handleCommand(String input) {
        try {
            if (input.equals("list")) {
                ui.showList(tasks);
            } else if (input.startsWith("mark ")) {
                ui.showMarked(tasks.mark(Parser.parseIndex(input, "mark ")));
            } else if (input.startsWith("unmark ")) {
                ui.showUnmarked(tasks.unmark(Parser.parseIndex(input, "unmark ")));
            } else if (input.startsWith("todo") && (input.length() == 4 || input.charAt(4) == ' ')) {
                ui.showAdded(tasks.add(Parser.parseTodo(input)), tasks.size());
            } else if (input.startsWith("deadline ")) {
                ui.showAdded(tasks.add(Parser.parseDeadline(input)), tasks.size());
            } else if (input.startsWith("event ")) {
                ui.showAdded(tasks.add(Parser.parseEvent(input)), tasks.size());
            } else {
                throw new MouseException("MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE");
            }
        } catch (MouseException | IndexOutOfBoundsException e) {
            if (e instanceof IndexOutOfBoundsException) {
                ui.showError("That task does not exist GRR");
            } else {
                ui.showError(e.getMessage());
            }
        }
    }
}
