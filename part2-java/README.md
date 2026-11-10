# Part 2: Java version

Enrollment management system written in Java 21.

## How to run

From this folder (`part2-java`):

```bash
javac -d out src/*.java
java -cp out Main
```

If you use IntelliJ IDEA, open this folder as a project and run the `Main` class.

## What the program does

The program shows an administrator menu:

1. Add student
2. Modify student
3. Remove student
4. Display all students
5. Parameter-passing demo
6. Exit

Each student has: first name, last name, date of birth, gender, GPA from a previous institution, current semester, program, and number of courses. Each student also gets an ID so we can find, modify, and remove them.

## Main files

| File | Purpose |
|---|---|
| `Student.java` | The student record |
| `EnrollmentManager.java` | Stores students and does add, modify, remove, and display |
| `Main.java` | The menu and user input |

(These names may change. Please update this table if they do.)

## Testing

We tested normal inputs, edge cases, and invalid inputs (for example, empty input, wrong date format, and a student ID that does not exist). Test screenshots are in the `tests/` folder, if included.

## Notes

- Code that was written or changed with help from an online tool is marked in the code comments.
