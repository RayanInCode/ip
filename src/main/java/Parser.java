/*
 * Parser class processes user input commands
 * and extracts relevant information such as
 * task descriptions, task numbers, and keywords.
 */
public class Parser {

    // Offsets used to extract task descriptions from commands
    private static final int TODO_OFFSET = 5;
    private static final int DEADLINE_OFFSET = 9;
    private static final int EVENT_OFFSET = 6;

    // Extracts the command word from the user input
    public static String getCommand(String line) {
        return line.split(" ")[0];
    }

    /*
     * Extracts the task number from commands such as
     * mark, unmark, or delete.
     */
    public static int getTaskNumber(String line) throws DukeException {
        String[] parts = line.split(" ");

        if (parts.length < 2) {
            throw new DukeException("Please specify the task number.");
        }

        try {
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            // Handles invalid task number input
            throw new DukeException("Task number must be a valid integer.");
        }
    }

    // Extracts the description of a todo task.
    public static String parseTodo(String line) throws DukeException {

        if (line.length() <= TODO_OFFSET) {
            throw new DukeException("A todo must have a description.");
        }

        String desc = line.substring(TODO_OFFSET).trim();

        if (desc.isEmpty()) {
            throw new DukeException("A todo must have a description.");
        }

        return desc;
    }

    /*
     * Parses deadline command to extract
     * description and due date.
     */
    public static String[] parseDeadline(String line) throws DukeException {

        if (!line.contains(" /by ")) {
            throw new DukeException("Deadline format: deadline <description> /by <date>");
        }

        String taskDesc = line.substring(DEADLINE_OFFSET);
        String[] parts = taskDesc.split(" /by ");

        if (parts.length < 2) {
            throw new DukeException("Deadline must include description and date.");
        }

        return parts;
    }

    /*
     * Parses event command to extract description,
     * start time, and end time.
     */
    public static String[] parseEvent(String line) throws DukeException {

        if (!line.contains(" /from ") || !line.contains(" /to ")) {
            throw new DukeException("Event format: event <description> /from <start> /to <end>");
        }

        String taskDesc = line.substring(EVENT_OFFSET);
        String[] parts = taskDesc.split(" /from | /to ");

        if (parts.length < 3) {
            throw new DukeException("Event must include description, start time and end time.");
        }

        return parts;
    }

    // Extracts the keyword used in the find command.
    public static String parseFind(String line) throws DukeException {

        String[] parts = line.split(" ", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("Please provide a keyword to search.");
        }

        return parts[1].trim();
    }

}