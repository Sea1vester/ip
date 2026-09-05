package mouse.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link CommandType#fromInput(String)}.
 */
public class CommandTypeTest {
    @Test
    public void fromInput_byeAndList_returnsMatchingTypes() {
        assertEquals(CommandType.BYE, CommandType.fromInput("bye"));
        assertEquals(CommandType.LIST, CommandType.fromInput("list"));
        assertEquals(CommandType.HELP, CommandType.fromInput("help"));
    }

    @Test
    public void fromInput_markUnmarkDelete_returnsMatchingTypes() {
        assertEquals(CommandType.MARK, CommandType.fromInput("mark 1"));
        assertEquals(CommandType.UNMARK, CommandType.fromInput("unmark 2"));
        assertEquals(CommandType.DELETE, CommandType.fromInput("delete 3"));
        assertEquals(CommandType.PRIORITY, CommandType.fromInput("priority 1 high"));
        assertEquals(CommandType.PRIORITY, CommandType.fromInput("priority"));
    }

    @Test
    public void fromInput_todoDeadlineEvent_returnsMatchingTypes() {
        assertEquals(CommandType.TODO, CommandType.fromInput("todo"));
        assertEquals(CommandType.TODO, CommandType.fromInput("todo read book"));
        assertEquals(CommandType.DEADLINE, CommandType.fromInput("deadline return book /by Sunday"));
        assertEquals(CommandType.EVENT, CommandType.fromInput("event meeting /from Mon /to Tue"));
        assertEquals(CommandType.FIND, CommandType.fromInput("find"));
        assertEquals(CommandType.FIND, CommandType.fromInput("find book"));
    }

    @Test
    public void fromInput_unknownOrIncomplete_returnsUnknown() {
        assertEquals(CommandType.UNKNOWN, CommandType.fromInput("blah"));
        assertEquals(CommandType.UNKNOWN, CommandType.fromInput("mark"));
        assertEquals(CommandType.UNKNOWN, CommandType.fromInput("deadline"));
    }

    @Test
    public void fromInput_leadingTrailingSpaces_stillRecognisesCommand() {
        assertEquals(CommandType.BYE, CommandType.fromInput("  bye  "));
        assertEquals(CommandType.LIST, CommandType.fromInput("\tlist\t"));
    }
}
