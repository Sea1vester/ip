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
todo read book
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
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: add-todo-deadline-event

**Aim:** Add a todo, deadline, and event, then list them to confirm polymorphic display.

**Inputs:**

```text
todo borrow book
deadline submit report /by Friday
event project meeting /from Mon 2pm /to 4pm
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
     Got it. I've added this task:
       [T][ ] borrow book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] submit report (by: Friday)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 3 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] submit report (by: Friday)
     3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: list-mark-unmark

**Aim:** Add two tasks, list them, mark the second as done, unmark it, then list again.

**Inputs:**

```text
todo read book
todo return book
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
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] return book
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[T][ ] return book
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
       [T][X] return book
    ____________________________________________________________
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [T][ ] return book
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[T][ ] return book
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: invalid-commands

**Aim:** Test unknown commands and typos.

**Inputs:**

```text
blah
todo
todo   
list
mark
unmark
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
     OOPS!!! MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! The description of a todo cannot be empty GRR
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! The description of a todo cannot be empty GRR
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: missing-delimiters

**Aim:** Test tasks missing required date/time delimiters.

**Inputs:**

```text
deadline return book
event meeting
event meeting /to 4pm
event meeting /from Mon
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
     OOPS!!! A deadline needs a '/by' time GRR
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! An event needs both '/from' and '/to' times GRR
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! An event needs both '/from' and '/to' times GRR
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! An event needs both '/from' and '/to' times GRR
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: invalid-task-numbers

**Aim:** Test marking and unmarking invalid task numbers.

**Inputs:**

```text
todo read book
mark 2
unmark 0
mark abc
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
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! That task does not exist GRR
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! That task does not exist GRR
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! That's not a valid task number GRR
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: mixed-valid-invalid

**Aim:** Interleave positive and negative test cases to detect incorrect inputs affecting the correctness of the internal states.

**Inputs:**

```text
todo borrow book
blah
deadline return book /by Sunday
todo 
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
     Got it. I've added this task:
       [T][ ] borrow book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! MOUSE NO UNDERSTAND. GIVE CHEESE TO MOUSE
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: Sunday)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! The description of a todo cannot be empty GRR
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sunday)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: delete-task

**Aim:** Delete a task from the middle of the list.

**Inputs:**

```text
todo read book
todo return book
todo buy bread
list
delete 2
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
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] return book
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] buy bread
     Now you have 3 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[T][ ] return book
     3.[T][ ] buy bread
    ____________________________________________________________
    ____________________________________________________________
     Noted. I've removed this task:
       [T][ ] return book
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[T][ ] buy bread
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case: invalid-delete

**Aim:** Test deleting invalid task numbers.

**Inputs:**

```text
todo read book
delete 2
delete 0
delete abc
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
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! That task does not exist GRR
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! That task does not exist GRR
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! That's not a valid task number GRR
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

