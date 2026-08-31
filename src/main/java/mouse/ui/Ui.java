package mouse.ui;

import java.util.Scanner;

import mouse.task.Task;
import mouse.task.TaskList;

/**
 * Reads user input and prints chatbot replies.
 */
public class Ui {
    private static final String LINE = "    " + "_".repeat(60);
    private static final String INDENT = "     ";
    private static final String BANNER = " __  __                      \n"
            + "|  \\/  | ___  _   _ ___  ___ \n"
            + "| |\\/| |/ _ \\| | | / __|/ _ \\\n"
            + "| |  | | (_) | |_| \\__ \\  __/\n"
            + "|_|  |_|\\___/ \\__,_|___/\\___|";

    private final Scanner scanner;

    /**
     * Creates a UI that reads from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the startup banner and greeting.
     */
    public void showGreeting() {
        System.out.println(LINE);
        for (String row : BANNER.split("\n")) {
            System.out.println(INDENT + row);
        }
        System.out.println(INDENT + "Hello! I'm Mouse.");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Reads the next command line from the user.
     *
     * @return Raw input line.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns the GUI greeting without the ASCII banner.
     *
     * @return Greeting text for a chat bubble.
     */
    public String formatGreeting() {
        return "Hello! I'm Mouse.\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message.
     *
     * @return Goodbye text.
     */
    public String formatBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns confirmation that a task was added.
     *
     * @param task Added task.
     * @param taskCount Current number of tasks.
     * @return Confirmation text.
     */
    public String formatAdded(Task task, int taskCount) {
        return String.join("\n",
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Returns confirmation that a task was marked done.
     *
     * @param task Marked task.
     * @return Confirmation text.
     */
    public String formatMarked(Task task) {
        return String.join("\n", "Nice! I've marked this task as done:", "  " + task);
    }

    /**
     * Returns confirmation that a task was unmarked.
     *
     * @param task Unmarked task.
     * @return Confirmation text.
     */
    public String formatUnmarked(Task task) {
        return String.join("\n", "OK, I've marked this task as not done yet:", "  " + task);
    }

    /**
     * Returns confirmation that a task was deleted.
     *
     * @param task Deleted task.
     * @param taskCount Remaining number of tasks.
     * @return Confirmation text.
     */
    public String formatDeleted(Task task, int taskCount) {
        return String.join("\n",
                "Noted. I've removed this task:",
                "  " + task,
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Returns an error message with the {@code OOPS!!!} prefix.
     *
     * @param errorMessage Error text without the prefix.
     * @return Error text for display.
     */
    public String formatError(String errorMessage) {
        return "OOPS!!! " + errorMessage;
    }

    /**
     * Returns the numbered task list.
     *
     * @param tasks Task list to display.
     * @return List text.
     */
    public String formatList(TaskList tasks) {
        return formatNumberedTasks("Here are the tasks in your list:", tasks);
    }

    /**
     * Returns matching tasks from a find, numbered from 1 among matches only.
     *
     * @param matches Tasks that matched the keyword.
     * @return Find result text.
     */
    public String formatFind(TaskList matches) {
        return formatNumberedTasks("Here are the matching tasks in your list:", matches);
    }

    /**
     * Returns a short guide to the commands Mouse understands.
     *
     * @return Help text.
     */
    public String formatHelp() {
        return String.join("\n",
                "Commands:",
                "• todo DESCRIPTION - Add a to-do",
                "• deadline DESCRIPTION /by WHEN - Add a deadline (e.g. 2019-12-02)",
                "• event DESCRIPTION /from START /to END - Add an event",
                "• list - Show all tasks",
                "• mark INDEX - Mark that task as done (1 is first)",
                "• unmark INDEX - Mark that task as not done",
                "• delete INDEX - Remove that task",
                "• find KEYWORD - Show tasks containing KEYWORD",
                "• help - Show this guide",
                "• bye - Exit Mouse");
    }

    /**
     * Prints the goodbye message.
     */
    public void showBye() {
        showReply(formatBye());
    }

    /**
     * Prints confirmation that a task was added.
     *
     * @param task Added task.
     * @param taskCount Current number of tasks.
     */
    public void showAdded(Task task, int taskCount) {
        showReply("Got it. I've added this task:",
                "  " + task,
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints confirmation that a task was marked done.
     *
     * @param task Marked task.
     */
    public void showMarked(Task task) {
        showReply("Nice! I've marked this task as done:",
                "  " + task);
    }

    /**
     * Prints confirmation that a task was unmarked.
     *
     * @param task Unmarked task.
     */
    public void showUnmarked(Task task) {
        showReply("OK, I've marked this task as not done yet:",
                "  " + task);
    }

    /**
     * Prints confirmation that a task was deleted, with the new task count.
     *
     * @param task Deleted task.
     * @param taskCount Remaining number of tasks.
     */
    public void showDeleted(Task task, int taskCount) {
        showReply("Noted. I've removed this task:",
                "  " + task,
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints confirmation that a task was deleted.
     *
     * @param task Deleted task.
     */
    public void showDeleted(Task task) {
        showReply("Noted, I've removed this task:", " " + task);
    }

    /**
     * Prints an error message wrapped like other replies.
     *
     * @param errorMessage Error text without the {@code OOPS!!!} prefix.
     */
    public void showError(String errorMessage) {
        showReply("OOPS!!! " + errorMessage);
    }

    /**
     * Prints the numbered task list.
     *
     * @param tasks Task list to display.
     */
    public void showList(TaskList tasks) {
        String[] lines = new String[tasks.size() + 1];
        lines[0] = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            lines[i + 1] = (i + 1) + "." + tasks.get(i);
        }
        showReply(lines);
    }

    /**
     * Prints matching tasks from a find, numbered from 1 among matches only.
     *
     * @param matches Tasks that matched the keyword.
     */
    public void showFind(TaskList matches) {
        String[] lines = new String[matches.size() + 1];
        lines[0] = "Here are the matching tasks in your list:";
        for (int i = 0; i < matches.size(); i++) {
            lines[i + 1] = (i + 1) + "." + matches.get(i);
        }
        showReply(lines);
    }

    /**
     * Prints a short guide to the commands Mouse understands.
     */
    public void showHelp() {
        showReply(formatHelp().split("\n", -1));
    }

    /**
     * Prints each message wrapped in horizontal lines.
     *
     * @param messages Lines to print between the borders.
     */
    public void showReply(String... messages) {
        System.out.println(LINE);
        for (String message : messages) {
            System.out.println(INDENT + message);
        }
        System.out.println(LINE);
    }

    private String formatNumberedTasks(String header, TaskList tasks) {
        String[] lines = new String[tasks.size() + 1];
        lines[0] = header;
        for (int i = 0; i < tasks.size(); i++) {
            lines[i + 1] = (i + 1) + "." + tasks.get(i);
        }
        return String.join("\n", lines);
    }

}
