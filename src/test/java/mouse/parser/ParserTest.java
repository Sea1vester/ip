package mouse.parser;

import org.junit.jupiter.api.Test;

import mouse.MouseException;
import mouse.task.Deadline;
import mouse.task.Event;
import mouse.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link Parser} command parsing.
 */
public class ParserTest {
    @Test
    public void parseTodo_validDescription_returnsToDo() throws MouseException {
        ToDo todo = Parser.parseTodo("todo read book");
        assertEquals("read book", todo.getDescription());
    }

    @Test
    public void parseTodo_emptyDescription_throwsMouseException() {
        assertThrows(MouseException.class, () -> Parser.parseTodo("todo"));
        assertThrows(MouseException.class, () -> Parser.parseTodo("todo   "));
    }

    @Test
    public void parseDeadline_validIsoDate_returnsDeadline() throws MouseException {
        Deadline deadline = Parser.parseDeadline("deadline return book /by 2019-12-02");
        assertTrue(deadline.toString().contains("return book"));
        assertTrue(deadline.toString().contains("2019"));
    }

    @Test
    public void parseDeadline_missingBy_throwsMouseException() {
        assertThrows(MouseException.class, () -> Parser.parseDeadline("deadline return book"));
    }

    @Test
    public void parseDeadline_emptyBy_throwsMouseException() {
        assertThrows(MouseException.class, () -> Parser.parseDeadline("deadline return book /by"));
    }

    @Test
    public void parseDeadline_emptyDescription_throwsMouseException() {
        assertThrows(MouseException.class, () -> Parser.parseDeadline("deadline /by Sunday"));
    }

    @Test
    public void parseEvent_validFromAndTo_returnsEvent() throws MouseException {
        Event event = Parser.parseEvent("event meeting /from Mon 2pm /to 4pm");
        assertEquals("[E][ ] meeting (from: Mon 2pm to: 4pm)", event.toString());
    }

    @Test
    public void parseEvent_missingFromOrTo_throwsMouseException() {
        assertThrows(MouseException.class, () -> Parser.parseEvent("event meeting"));
        assertThrows(MouseException.class, () -> Parser.parseEvent("event meeting /from Mon"));
        assertThrows(MouseException.class, () -> Parser.parseEvent("event meeting /to 4pm"));
    }

    @Test
    public void parseEvent_fromAfterTo_throwsMouseException() {
        assertThrows(MouseException.class,
                () -> Parser.parseEvent("event meeting /to 4pm /from Mon"));
    }

    @Test
    public void parseIndex_validNumber_returnsZeroBasedIndex() throws MouseException {
        assertEquals(0, Parser.parseIndex("mark 1", "mark "));
        assertEquals(2, Parser.parseIndex("delete 3", "delete "));
    }

    @Test
    public void parseIndex_nonNumeric_throwsMouseException() {
        assertThrows(MouseException.class, () -> Parser.parseIndex("mark abc", "mark "));
    }
}
