# Mouse User Guide

Mouse is a chatbot that keeps your to-dos, deadlines, and events in one list.

You can talk to it in the GUI window or from the command line.
Tasks are saved to `data/mouse.txt` so they come back the next time you open Mouse.

## Quick start

1. Run Mouse with Java 25 (`java -jar mouse.jar` or `./gradlew run`).
2. Type a command and press Enter.
3. Type `help` to see the command list.
4. Type `bye` to exit.

Indexes in commands start at `1` (the first task in the current list).

## Add a to-do

`todo DESCRIPTION`

Example: `todo read book`

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

## Add a deadline

`deadline DESCRIPTION /by WHEN`

`WHEN` can be an ISO date (`2019-12-02`), a date and time (`2019-12-02 1800`), a slash date (`2/12/2019`), or free text such as `Friday`.

Example: `deadline return book /by 2019-12-02`

```
Got it. I've added this task:
  [D][ ] return book (by: Dec 02 2019)
Now you have 2 tasks in the list.
```

## Add an event

`event DESCRIPTION /from START /to END`

Example: `event meeting /from Mon 2pm /to 4pm`

```
Got it. I've added this task:
  [E][ ] meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
```

## List tasks

`list`

Shows every saved task, numbered from 1.

## Mark and unmark

`mark INDEX`

Marks that task as done.

`unmark INDEX`

Marks that task as not done.

Example: `mark 1`

```
Nice! I've marked this task as done:
  [T][X] read book
```

## Delete a task

`delete INDEX`

Removes that task and renumbers the remaining ones.

## Find tasks

`find KEYWORD`

Shows tasks whose description contains `KEYWORD` (case-insensitive).
Match numbers in the result are only among the matches, not the full list.

Example: `find book`

```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: Dec 02 2019)
```

## Set priority

`priority INDEX high|low|none`

Sets urgency on an existing task.
High and low show up as `(priority: high)` or `(priority: low)` in the list.
`none` clears the priority.

Example: `priority 1 high`

```
OK, I've set the priority of this task:
  [T][X] read book (priority: high)
```

## Help and exit

`help` prints the command guide.

`bye` closes Mouse.

## Saving

Mouse writes the full list to `data/mouse.txt` after each add, mark, unmark, delete, or priority change.
Closing the app and opening it again loads that file.

If the save file is missing, Mouse starts with an empty list.
Corrupt lines in the file are skipped.

## Command summary

- `todo DESCRIPTION`
- `deadline DESCRIPTION /by WHEN`
- `event DESCRIPTION /from START /to END`
- `list`
- `mark INDEX`
- `unmark INDEX`
- `delete INDEX`
- `find KEYWORD`
- `priority INDEX high|low|none`
- `help`
- `bye`
