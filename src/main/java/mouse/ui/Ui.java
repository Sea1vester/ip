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
        showReply(append(BANNER.split("\n"),
                "Hello! I'm Mouse.",
                "What can I do for you?"));
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
        return joinLines("Hello! I'm Mouse.", "What can I do for you?");
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
        return joinLines(
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
        return joinLines("Nice! I've marked this task as done:", "  " + task);
    }

    /**
     * Returns confirmation that a task was unmarked.
     *
     * @param task Unmarked task.
     * @return Confirmation text.
     */
    public String formatUnmarked(Task task) {
        return joinLines("OK, I've marked this task as not done yet:", "  " + task);
    }

    /**
     * Returns confirmation that a task was deleted.
     *
     * @param task Deleted task.
     * @param taskCount Remaining number of tasks.
     * @return Confirmation text.
     */
    public String formatDeleted(Task task, int taskCount) {
        return joinLines(
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
     * Returns confirmation that a task's priority was updated.
     *
     * @param task Updated task.
     * @return Confirmation text.
     */
    public String formatPriority(Task task) {
        return joinLines("OK, I've set the priority of this task:", "  " + task);
    }

    /**
     * Returns a short guide to the commands Mouse understands.
     *
     * @return Help text.
     */
    public String formatHelp() {
        return joinLines(
                "Commands:",
                "• todo DESCRIPTION - Add a to-do",
                "• deadline DESCRIPTION /by WHEN - Add a deadline (e.g. 2019-12-02)",
                "• event DESCRIPTION /from START /to END - Add an event",
                "• list - Show all tasks",
                "• mark INDEX - Mark that task as done (1 is first)",
                "• unmark INDEX - Mark that task as not done",
                "• delete INDEX - Remove that task",
                "• find KEYWORD - Show tasks containing KEYWORD",
                "• priority INDEX high|low|none - Set that task's priority",
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
        showReply(splitLines(formatAdded(task, taskCount)));
    }

    /**
     * Prints confirmation that a task was marked done.
     *
     * @param task Marked task.
     */
    public void showMarked(Task task) {
        showReply(splitLines(formatMarked(task)));
    }

    /**
     * Prints confirmation that a task was unmarked.
     *
     * @param task Unmarked task.
     */
    public void showUnmarked(Task task) {
        showReply(splitLines(formatUnmarked(task)));
    }

    /**
     * Prints confirmation that a task was deleted, with the new task count.
     *
     * @param task Deleted task.
     * @param taskCount Remaining number of tasks.
     */
    public void showDeleted(Task task, int taskCount) {
        showReply(splitLines(formatDeleted(task, taskCount)));
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
        showReply(numberedLines("Here are the tasks in your list:", tasks));
    }

    /**
     * Prints matching tasks from a find, numbered from 1 among matches only.
     *
     * @param matches Tasks that matched the keyword.
     */
    public void showFind(TaskList matches) {
        showReply(numberedLines("Here are the matching tasks in your list:", matches));
    }

    /**
     * Prints a short guide to the commands Mouse understands.
     */
    public void showHelp() {
        showReply(splitLines(formatHelp()));
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
        return joinLines(numberedLines(header, tasks));
    }

    private String joinLines(String... lines) {
        return String.join("\n", lines);
    }

    private String[] splitLines(String text) {
        return text.split("\n", -1);
    }

    private String[] numberedLines(String header, TaskList tasks) {
        String[] lines = new String[tasks.size() + 1];
        lines[0] = header;
        for (int i = 0; i < tasks.size(); i++) {
            lines[i + 1] = (i + 1) + "." + tasks.get(i);
        }
        return lines;
    }

    private String[] append(String[] prefix, String... extra) {
        String[] result = new String[prefix.length + extra.length];
        System.arraycopy(prefix, 0, result, 0, prefix.length);
        System.arraycopy(extra, 0, result, prefix.length, extra.length);
        return result;
    }

}
