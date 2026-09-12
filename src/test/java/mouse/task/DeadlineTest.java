package mouse.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mouse.MouseException;

/**
 * Tests for {@link Deadline} date parsing and display.
 */
public class DeadlineTest {
    @Test
    public void constructor_isoDate_formatsMonthDayYear() throws MouseException {
        Deadline deadline = new Deadline("return book", "2019-12-02");
        assertEquals("[D][ ] return book (by: Dec 02 2019)", deadline.toString());
    }

    @Test
    public void constructor_isoDateTime_includesFormattedTime() throws MouseException {
        Deadline deadline = new Deadline("return book", "2019-12-02 1800");
        String text = deadline.toString();
        assertTrue(text.startsWith("[D][ ] return book (by: Dec 02 2019"));
        assertTrue(text.contains("6:00"));
        assertTrue(text.contains("PM") || text.contains("pm"));
    }

    @Test
    public void constructor_slashDate_formatsMonthDayYear() throws MouseException {
        Deadline deadline = new Deadline("return book", "2/12/2019");
        assertEquals("[D][ ] return book (by: Dec 02 2019)", deadline.toString());
    }

    @Test
    public void constructor_slashDateTime_includesFormattedTime() throws MouseException {
        Deadline deadline = new Deadline("return book", "2/12/2019 1800");
        String text = deadline.toString();
        assertTrue(text.startsWith("[D][ ] return book (by: Dec 02 2019"));
        assertTrue(text.contains("6:00"));
    }

    @Test
    public void constructor_freeText_keepsOriginalByText() throws MouseException {
        Deadline deadline = new Deadline("return book", "Friday");
        assertEquals("[D][ ] return book (by: Friday)", deadline.toString());
    }
}
