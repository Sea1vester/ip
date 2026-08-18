import java.util.Scanner;

public class Mouse {
    private static final String LINE = "_".repeat(60);

    public static void main(String[] args) {
        printGreeting();
        echoUntilBye();
    }

    private static void printGreeting() {
        String banner = " __  __                      \n"
                + "|  \\/  | ___  _   _ ___  ___ \n"
                + "| |\\/| |/ _ \\| | | / __|/ _ \\\n"
                + "| |  | | (_) | |_| \\__ \\  __/\n"
                + "|_|  |_|\\___/ \\__,_|___/\\___|\n";
        System.out.println(LINE);
        System.out.print(banner);
        System.out.println("Hello! I'm Mouse.");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void echoUntilBye() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                printReply("Bye. Hope to see you again soon!");
                break;
            }
            printReply(input);
        }
    }

 
    private static void printReply(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
}
