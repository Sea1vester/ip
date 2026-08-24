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
        try {
            this.dateTime = LocalDateTime.parse(by, DATE_TIME_IN);
        } catch (DateTimeParseException e1) {
            try {
                this.dateTime = LocalDateTime.parse(by, DATE_TIME_IN_SLASH);
            } catch (DateTimeParseException e2) {
                this.dateTime = null;
                try {
                    this.date = LocalDate.parse(by, DATE_IN);
                } catch (DateTimeParseException dateException) {
                    try {
                        this.date = LocalDate.parse(by, DATE_IN_SLASH);
                    } catch (DateTimeParseException slashDateException) {
                        this.date = null;
                        this.byText = by;
                    }
                }
            }
        }
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
