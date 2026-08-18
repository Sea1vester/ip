import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the Mouse chatbot.
 */
public class Mouse {
    private static final String LINE = "    " + "_".repeat(60);
    private static final String INDENT = "     ";

    public static void main(String[] args) {
        printGreeting();
        handleCommands();
    }

    /**
     * Prints the startup banner and greeting.
     */
    private static void printGreeting() {
        String banner = " __  __                      \n"
                + "|  \\/  | ___  _   _ ___  ___ \n"
                + "| |\\/| |/ _ \\| | | / __|/ _ \\\n"
                + "| |  | | (_) | |_| \\__ \\  __/\n"
                + "|_|  |_|\\___/ \\__,_|___/\\___|";
        System.out.println(LINE);
        for (String row : banner.split("\n")) {
            System.out.println(INDENT + row);
        }
        System.out.println(INDENT + "Hello! I'm Mouse.");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Adds tasks, lists them, marks or unmarks them, and exits on {@code bye}.
     */
    private static void handleCommands() {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                printReply("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                printList(tasks);
            } else if (input.startsWith("mark ")) {
                markTask(tasks, input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(tasks, input);
            } else {
                Task task = new Task(input);
                tasks.add(task);
                printReply("added: " + task.getDescription());
            }
        }
    }

    /**
     * Marks the task at the 1-based index in {@code mark N} as done.
     */
    private static void markTask(List<Task> tasks, String input) {
        int index = Integer.parseInt(input.substring(5).trim()) - 1;
        Task task = tasks.get(index);
        task.markAsDone();
        printReply("Nice! I've marked this task as done:",
                "  " + task.toDisplayString());
    }

    /**
     * Marks the task at the 1-based index in {@code unmark N} as not done.
     */
    private static void unmarkTask(List<Task> tasks, String input) {
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        Task task = tasks.get(index);
        task.markAsNotDone();
        printReply("OK, I've marked this task as not done yet:",
                "  " + task.toDisplayString());
    }

    /**
     * Prints the numbered task list with done status.
     */
    private static void printList(List<Task> tasks) {
        String[] lines = new String[tasks.size() + 1];
        lines[0] = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            lines[i + 1] = (i + 1) + "." + tasks.get(i).toDisplayString();
        }
        printReply(lines);
    }

    /**
     * Prints each message wrapped in horizontal lines.
     */
    private static void printReply(String... messages) {
        System.out.println(LINE);
        for (String message : messages) {
            System.out.println(INDENT + message);
        }
        System.out.println(LINE);
    }
}
