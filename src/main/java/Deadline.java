import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * Deadline task type.
 * Represents a task that must be completed before a specific date.
 */
public class Deadline extends Task {

    // Stores the deadline date
    protected LocalDate by;

    /*
     * Creates a Deadline task with a description and due date.
     * The date must be provided in d/M/yyyy format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            // Parse the input date string into LocalDate
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            this.by = LocalDate.parse(by, inputFormat);
        } catch (DateTimeParseException e) {
            // Throw custom exception if date format is invalid
            throw new DukeException("Invalid date format. Please use d/M/yyyy.");
        }
    }

    /*
     * Returns a formatted string representation of the deadline task
     * for display in the task list.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }

    // Converts the deadline task into the format used for file storage.
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}