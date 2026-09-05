package mouse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for GUI-facing command replies.
 */
public class MouseTest {
    @Test
    public void getResponse_todo_returnsAddedConfirmation() {
        Mouse mouse = new Mouse();
        String reply = mouse.getResponse("todo read book");
        assertTrue(reply.contains("Got it. I've added this task:"));
        assertTrue(reply.contains("read book"));
        assertTrue(reply.contains("1 tasks"));
        assertFalse(mouse.isExit("todo read book"));
    }

    @Test
    public void getResponse_unknownCommand_returnsError() {
        Mouse mouse = new Mouse();
        String reply = mouse.getResponse("cheese");
        assertTrue(reply.startsWith("OOPS!!!"));
        assertTrue(reply.contains("MOUSE NO UNDERSTAND"));
    }

    @Test
    public void isExit_bye_returnsTrue() {
        Mouse mouse = new Mouse();
        assertTrue(mouse.isExit("bye"));
        assertTrue(mouse.getResponse("bye").contains("Bye."));
    }

    @Test
    public void getResponse_priority_updatesExistingTask() {
        Mouse mouse = new Mouse();
        mouse.getResponse("todo read book");
        String reply = mouse.getResponse("priority 1 high");
        assertTrue(reply.contains("OK, I've set the priority of this task:"));
        assertTrue(reply.contains("priority: high"));
        String list = mouse.getResponse("list");
        assertTrue(list.contains("read book (priority: high)"));
    }

    @Test
    public void getResponse_help_returnsCommandGuide() {
        Mouse mouse = new Mouse();
        String reply = mouse.getResponse("help");
        assertTrue(reply.contains("todo DESCRIPTION"));
        assertTrue(reply.contains("deadline DESCRIPTION /by WHEN"));
        assertTrue(reply.contains("bye"));
        assertFalse(mouse.isExit("help"));
    }
}
