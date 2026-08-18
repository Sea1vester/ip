# UI test plan

This file is the source of truth for Mouse chatbot UI tests. The `test-ui` skill runs each test case in order.

## Program

- Main class: `Mouse`
- Source files: `src/main/java/Mouse.java`, `src/main/java/Task.java`
- Java: Azul JDK 25 (`25.0.3.fx-zulu`)
- Each test case starts a **new** program process, so task lists do not carry over.

## How to run

From the repository root:

```bash
python3 .cursor/skills/test-ui/scripts/run-ui-tests.py
```

## Test case format

Each test case has an aim, a list of console inputs (one command per line), and the exact expected console output.

## Test case: greet-and-exit

**Aim:** Greet the user and exit when they type bye.

**Inputs:**

```text
bye
```

**Expected output:**

```text
    ____________________________________________________________
      __  __                      
     |  \/  | ___  _   _ ___  ___ 
     | |\/| |/ _ \| | | / __|/ _ \
     | |  | | (_) | |_| \__ \  __/
     |_|  |_|\___/ \__,_|___/\___|
     Hello! I'm Mouse.
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: add-task

**Aim:** Add a task and confirm it was added before exiting.

**Inputs:**

```text
read book
bye
```

**Expected output:**

```text
    ____________________________________________________________
      __  __                      
     |  \/  | ___  _   _ ___  ___ 
     | |\/| |/ _ \| | | / __|/ _ \
     | |  | | (_) | |_| \__ \  __/
     |_|  |_|\___/ \__,_|___/\___|
     Hello! I'm Mouse.
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     added: read book
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: list-mark-unmark

**Aim:** Add two tasks, list them, mark the second as done, unmark it, then list again.

**Inputs:**

```text
read book
return book
list
mark 2
unmark 2
list
bye
```

**Expected output:**

```text
    ____________________________________________________________
      __  __                      
     |  \/  | ___  _   _ ___  ___ 
     | |\/| |/ _ \| | | / __|/ _ \
     | |  | | (_) | |_| \__ \  __/
     |_|  |_|\___/ \__,_|___/\___|
     Hello! I'm Mouse.
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     added: read book
    ____________________________________________________________
    ____________________________________________________________
     added: return book
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[ ] read book
     2.[ ] return book
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
       [X] return book
    ____________________________________________________________
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [ ] return book
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[ ] read book
     2.[ ] return book
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

