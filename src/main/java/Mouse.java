/**
 * Entry point for the Mouse chatbot.
 */
public class Mouse {
    public static void main(String[] args) {
        printGreeting();
    }

    public static void printGreeting() {
        String banner = " __  __                      \n"
                + "|  \\/  | ___  _   _ ___  ___ \n"
                + "| |\\/| |/ _ \\| | | / __|/ _ \\\n"
                + "| |  | | (_) | |_| \\__ \\  __/\n"
                + "|_|  |_|\\___/ \\__,_|___/\\___|\n";
        String greeting = "____________________________________________________________\n"
        + banner + "\n"
        + "Hello! I'm Mouse.\n"
        + "What can I do for you?\n"
        + "____________________________________________________________\n"
        + "Bye. Hope to see you again soon!\n"
        + "____________________________________________________________\n";
        System.out.println(greeting);
    }
}


