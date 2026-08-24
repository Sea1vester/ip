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

    /**
     * Creates a deadline from a date ({@code yyyy-MM-dd}) or date-time ({@code yyyy-MM-dd HHmm}).
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(by, DATE_TIME_IN); //Does by match yyyy-MM-dd HHmm?
        } catch (DateTimeParseException e1) {
            try {
                this.dateTime = LocalDateTime.parse(by, DATE_TIME_IN_SLASH);
            } catch (DateTimeParseException e2) {
                this.dateTime = null; //here!
                try {
                    this.date = LocalDate.parse(by, DATE_IN);
                } catch (DateTimeParseException e3) {
                    this.date = LocalDate.parse(by, DATE_IN_SLASH);
                }
            }
        }
    }

    @Override
    public String toString() {
        String output;
        if (dateTime != null) {
            output = dateTime.format(DATE_TIME_OUT);
        } else {
            output = date.format(DATE_OUT);
        }
        return "[" + TaskType.DEADLINE.getSymbol() + "]" + super.toString()
                + " (by: " + output + ")";
    }
}
