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


    private static void handleCommands() {
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                printReply("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                printList(tasks);
            } else {
                tasks.add(input);
                printReply("added: " + input);
            }
        }
    }

    private static void printList(List<String> tasks) {
        String[] lines = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            lines[i] = (i + 1) + ". " + tasks.get(i);
        }
        printReply(lines);
    }

    private static void printReply(String... messages) {
        System.out.println(LINE);
        for (String message : messages) {
            System.out.println(INDENT + message);
        }
        System.out.println(LINE);
    }
}
