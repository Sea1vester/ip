package mouse.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task that must be done before a given date or time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_IN =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_IN =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_OUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter DATE_TIME_OUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter DATE_IN_SLASH =
            DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter DATE_TIME_IN_SLASH =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    protected LocalDate date;
    protected LocalDateTime dateTime;
    protected String byText;

    /**
     * Creates a deadline from a date, date-time, or free-text {@code by} value.
     * Accepts {@code yyyy-MM-dd}, {@code yyyy-MM-dd HHmm}, {@code d/M/yyyy},
     * {@code d/M/yyyy HHmm}, or plain text such as {@code Friday}.
     *
     * @param description Task description.
     * @param by Deadline date, date-time, or free text.
     */
    public Deadline(String description, String by) {
        super(description);
        this.dateTime = parseDateTime(by, DATE_TIME_IN, DATE_TIME_IN_SLASH);
        if (this.dateTime != null) {
            return;
        }
        this.date = parseDate(by, DATE_IN, DATE_IN_SLASH);
        if (this.date == null) {
            this.byText = by;
        }
    }

    private static LocalDateTime parseDateTime(String by, DateTimeFormatter... formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException exception) {
                // Try the next formatter.
            }
        }
        return null;
    }

    private static LocalDate parseDate(String by, DateTimeFormatter... formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(by, formatter);
            } catch (DateTimeParseException exception) {
                // Try the next formatter.
            }
        }
        return null;
    }

    /**
     * Returns the deadline with type letter and formatted {@code by} value.
     *
     * @return Display string for this deadline.
     */
    @Override
    public String toString() {
        String output;
        if (dateTime != null) {
            output = dateTime.format(DATE_TIME_OUT);
        } else if (date != null) {
            output = date.format(DATE_OUT);
        } else {
            output = byText;
        }
        return "[" + TaskType.DEADLINE.getSymbol() + "]" + super.toString()
                + " (by: " + output + ")";
    }
}
