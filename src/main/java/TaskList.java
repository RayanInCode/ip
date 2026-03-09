import java.util.ArrayList;

// Represents a list that stores Task objects.
public class TaskList {

    private ArrayList<Task> tasks;

    // Creates an empty TaskList.
    public TaskList() {
        tasks = new ArrayList<>();
    }

    // Adds a task to the list
    public void add(Task task) {
        tasks.add(task);
    }

    // Returns the task at the given index
    public Task get(int index) {
        return tasks.get(index);
    }

    // Deletes and returns the task at the given index
    public Task delete(int index) {
        return tasks.remove(index);
    }

    // Returns the number of tasks
    public int size() {
        return tasks.size();
    }

    // Checks if the task list is empty
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    // Returns all tasks
    public ArrayList<Task> getAll() {
        return tasks;
    }

}