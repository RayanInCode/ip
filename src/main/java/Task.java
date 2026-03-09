/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {

    protected String description; // task description
    protected boolean isDone; // indicates if task is completed

    /**
     * Creates a new task with the given description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Marks the task as done
    public void mark() {
        isDone = true;
    }

    // Marks the task as not done
    public void unmark() {
        isDone = false;
    }

    // Returns the status icon of the task
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // Returns a string representation of the task
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}