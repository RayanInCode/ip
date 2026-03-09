/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {

    protected String from; // start time of the event
    protected String to;   // end time of the event

     // Creates a new Event with description, start time, and end time.
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    // Returns a formatted string representation of the event
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from + " to: " + to + ")";
    }

}