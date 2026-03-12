
---

# Magnemite Chatbot User Guide

## Magnemite – Task Management Chatbot

**Magnemite** is a simple chatbot that helps you manage your tasks efficiently.
You can add tasks, mark them as done, delete them, and search tasks using easy text commands.

Magnemite runs in a command-line interface where you interact with the chatbot by typing commands.

---

# Features

* Add different types of tasks
* View all tasks
* Mark tasks as done or undone
* Delete tasks
* Search tasks using keywords
* Automatically saves tasks

---

# Getting Started

1. Run the Magnemite program.
2. Magnemite will display a welcome message.
3. Enter commands to manage your tasks.

Example:

```
Hello! I'm Magnemite.
What can I do for you?
```

---

# Command Summary

| Command    | Description                |
| ---------- | -------------------------- |
| `todo`     | Add a to-do task           |
| `deadline` | Add a task with a deadline |
| `event`    | Add an event               |
| `list`     | Show all tasks             |
| `mark`     | Mark a task as done        |
| `unmark`   | Mark a task as not done    |
| `delete`   | Delete a task              |
| `find`     | Search tasks               |
| `bye`      | Exit the chatbot           |

---

# Adding Tasks

## To-Do Task

Add a simple task without a deadline.

```
todo <task description>
```

Example:

```
todo Read research article
```

---

## Deadline Task

Add a task that must be completed by a certain date/time.

```
deadline <task description> /by <date>
```

Example:

```
deadline Submit report /by Friday
```

---

## Event Task

Add an event occurring at a specific time.

```
event <task description> /from <start> /to <end>
```

Example:

```
event Project meeting /from Monday /to Thursday
```

---

# Viewing Tasks

To see all saved tasks:

```
list
```

Magnemite will display all tasks along with their status.

---

# Marking Tasks as Done

```
mark <task number>
```

Example:

```
mark 2
```

---

# Marking Tasks as Not Done

```
unmark <task number>
```

Example:

```
unmark 2
```

---

# Deleting a Task

```
delete <task number>
```

Example:

```
delete 3
```

---

# Finding Tasks

Search tasks containing a specific keyword.

```
find <keyword>
```

Example:

```
find meeting
```

---

# Exiting Magnemite

To exit the chatbot:

```
bye
```

Example output:

```
Bye. Hope to see you again soon!
```

---

# Notes

* Task numbers correspond to the numbers shown in the `list` command.
* Magnemite automatically saves tasks.
* Tasks will still be available the next time you run the program.

---
