import java.util.Scanner;

/**
 * Entry point for the Mouse chatbot.
 */
public class Mouse {
    private static final String LINE = "    " + "_".repeat(60);
    private static final String INDENT = "     ";
    private static final int MAX_TASKS = 100;

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
     * Adds todos, deadlines, and events; lists, marks, and unmarks them; exits on {@code bye}.
     */
    private static void handleCommands() {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                printReply("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                printList(tasks, taskCount);
            } else if (input.startsWith("mark ")) {
                markTask(tasks, taskCount, input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(tasks, taskCount, input);
            } else if (input.startsWith("todo ")) {
                taskCount = addTodo(tasks, taskCount, input);
            } else if (input.startsWith("deadline ")) {
                taskCount = addDeadline(tasks, taskCount, input);
            } else if (input.startsWith("event ")) {
                taskCount = addEvent(tasks, taskCount, input);
            }
        }
    }

    /**
     * Adds a todo from {@code todo DESCRIPTION}.
     */
    private static int addTodo(Task[] tasks, int taskCount, String input) {
        String description = input.substring("todo ".length()).trim();
        return addTask(tasks, taskCount, new ToDo(description));
    }

    /**
     * Adds a deadline from {@code deadline DESCRIPTION /by WHEN}.
     */
    private static int addDeadline(Task[] tasks, int taskCount, String input) {
        String rest = input.substring("deadline ".length()).trim();
        int byIndex = rest.indexOf("/by");
        String description = rest.substring(0, byIndex).trim();
        String by = rest.substring(byIndex + "/by".length()).trim();
        return addTask(tasks, taskCount, new Deadline(description, by));
    }

    /**
     * Adds an event from {@code event DESCRIPTION /from START /to END}.
     */
    private static int addEvent(Task[] tasks, int taskCount, String input) {
        String rest = input.substring("event ".length()).trim();
        int fromIndex = rest.indexOf("/from");
        int toIndex = rest.indexOf("/to");
        String description = rest.substring(0, fromIndex).trim();
        String from = rest.substring(fromIndex + "/from".length(), toIndex).trim();
        String to = rest.substring(toIndex + "/to".length()).trim();
        return addTask(tasks, taskCount, new Event(description, from, to));
    }

    private static int addTask(Task[] tasks, int taskCount, Task task) {
        tasks[taskCount] = task;
        taskCount++;
        printReply("Got it. I've added this task:",
                "  " + task,
                "Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }

    /**
     * Marks the task at the 1-based index in {@code mark N} as done.
     */
    private static void markTask(Task[] tasks, int taskCount, String input) {
        int index = Integer.parseInt(input.substring("mark ".length()).trim()) - 1;
        Task task = tasks[index];
        task.markAsDone();
        printReply("Nice! I've marked this task as done:",
                "  " + task);
    }

    /**
     * Marks the task at the 1-based index in {@code unmark N} as not done.
     */
    private static void unmarkTask(Task[] tasks, int taskCount, String input) {
        int index = Integer.parseInt(input.substring("unmark ".length()).trim()) - 1;
        Task task = tasks[index];
        task.markAsNotDone();
        printReply("OK, I've marked this task as not done yet:",
                "  " + task);
    }

    /**
     * Prints the numbered task list.
     */
    private static void printList(Task[] tasks, int taskCount) {
        String[] lines = new String[taskCount + 1];
        lines[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskCount; i++) {
            lines[i + 1] = (i + 1) + "." + tasks[i];
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
