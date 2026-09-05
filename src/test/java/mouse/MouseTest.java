package mouse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests for GUI-facing command replies.
 */
public class MouseTest {
    @TempDir
    Path tempDir;

    private Mouse mouse() {
        return new Mouse(tempDir.resolve("mouse.txt").toString());
    }

    @Test
    public void getResponse_todo_returnsAddedConfirmation() {
        Mouse mouse = mouse();
        String reply = mouse.getResponse("todo read book");
        assertTrue(reply.contains("Got it. I've added this task:"));
        assertTrue(reply.contains("read book"));
        assertTrue(reply.contains("1 tasks"));
        assertFalse(mouse.isExit("todo read book"));
    }

    @Test
    public void getResponse_unknownCommand_returnsError() {
        Mouse mouse = mouse();
        String reply = mouse.getResponse("cheese");
        assertTrue(reply.startsWith("OOPS!!!"));
        assertTrue(reply.contains("MOUSE NO UNDERSTAND"));
    }

    @Test
    public void isExit_bye_returnsTrue() {
        Mouse mouse = mouse();
        assertTrue(mouse.isExit("bye"));
        assertTrue(mouse.getResponse("bye").contains("Bye."));
    }

    @Test
    public void getResponse_priority_updatesExistingTask() {
        Mouse mouse = mouse();
        mouse.getResponse("todo read book");
        String reply = mouse.getResponse("priority 1 high");
        assertTrue(reply.contains("OK, I've set the priority of this task:"));
        assertTrue(reply.contains("priority: high"));
        String list = mouse.getResponse("list");
        assertTrue(list.contains("read book (priority: high)"));
    }

    @Test
    public void getResponse_afterRestart_keepsSavedTasks() {
        Path saveFile = tempDir.resolve("mouse.txt");
        Mouse first = new Mouse(saveFile.toString());
        first.getResponse("todo read book");
        first.getResponse("priority 1 high");

        Mouse second = new Mouse(saveFile.toString());
        String list = second.getResponse("list");
        assertTrue(list.contains("read book (priority: high)"));
    }

    @Test
    public void getResponse_help_returnsCommandGuide() {
        Mouse mouse = mouse();
        String reply = mouse.getResponse("help");
        assertTrue(reply.contains("todo DESCRIPTION"));
        assertTrue(reply.contains("deadline DESCRIPTION /by WHEN"));
        assertTrue(reply.contains("bye"));
        assertFalse(mouse.isExit("help"));
    }
}
