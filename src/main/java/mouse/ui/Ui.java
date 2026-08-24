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
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the goodbye message.
     */
    public void showBye() {
        showReply("Bye. Hope to see you again soon!");
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
}
