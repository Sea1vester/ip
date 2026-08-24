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

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showBye() {
        showReply("Bye. Hope to see you again soon!");
    }

    public void showAdded(Task task, int taskCount) {
        showReply("Got it. I've added this task:",
                "  " + task,
                "Now you have " + taskCount + " tasks in the list.");
    }

    public void showMarked(Task task) {
        showReply("Nice! I've marked this task as done:",
                "  " + task);
    }
    

    public void showUnmarked(Task task) {
        showReply("OK, I've marked this task as not done yet:",
                "  " + task);
    }

    public void showDeleted(Task task, int taskCount) {
        showReply("Noted. I've removed this task:",
                "  " + task,
                "Now you have " + taskCount + " tasks in the list.");
    }

    public void showError(String errorMessage) {
        showReply("OOPS!!! " + errorMessage);
    }

    public void showDeleted(Task task) {
        showReply("Noted, I've removed this task:", " " + task);
    }

    /**
     * Prints the numbered task list.
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
     * Prints each message wrapped in horizontal lines.
     */
    public void showReply(String... messages) {
        System.out.println(LINE);
        for (String message : messages) {
            System.out.println(INDENT + message);
        }
        System.out.println(LINE);
    }

}
