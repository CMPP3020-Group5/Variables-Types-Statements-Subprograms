# Part 2: Java version

Enrollment management system written in Java 21.

## How to run

From this folder (`part2-java`):

```bash
javac -d out $(find src/main/java -name '*.java')
java -cp out com.sait.enrollment.Main
```

If you use IntelliJ IDEA, open this folder as a project and run the `Main` class in the `com.sait.enrollment` package.

## What the program does

The program shows an administrator menu:

1. Add student
2. Modify student
3. Remove student
4. Display all students
5. Parameter-passing demo
6. Exit

Each student has: first name, last name, date of birth, gender, GPA from a previous institution, current semester, program, and number of courses. Each student also gets an ID so we can find, modify, and remove them.

## Project structure

The code is in `src/main/java/com/sait/enrollment/`.

| File | Purpose |
|---|---|
| `Main.java` | Program entry point (the menu and user input) |
| `model/Student.java` | The student record: fields, getters, setters, `toString()` and `copyFrom()` |
| `repository/StudentRepository.java` | The "fake database": keeps the students in memory and does insert, update, delete, and find |
| `repository/IdGenerator.java` | Gives out the student IDs (1, 2, 3, ...) |
| `service/EnrollmentService.java` | The rules: checks the data, then calls the repository for add, modify, remove, and show all |
| `service/exception/EnrollmentException.java` | The error thrown when the data is not valid or a student is not found |
| `util/InputHelper.java` | Reads the user input and checks it. It asks again until the input is valid |
| `util/exception/InputEndedException.java` | The error thrown when the input ends (for example Ctrl+D) while the program wants to read more |

How the classes work together: `Main` shows the menu and uses `InputHelper` to read the input. It then calls `EnrollmentService`, the service calls `StudentRepository`, and the repository stores `Student` objects.

(These names may change. Please update this table if they do.)

## Status

Work in progress.

| Menu option | Status |
|---|---|
| 1. Add student | Done |
| 2. Modify student | Not done yet |
| 3. Remove student | Not done yet |
| 4. Display all students | Done |
| 5. Parameter-passing demo | Not done yet |
| 6. Exit | Done |

## Testing

The folder `tests/` has input files. Each file is a list of the lines a user would type, so we can run the program without typing.

Run one file (compile first, see "How to run"):

```bash
java -cp out com.sait.enrollment.Main < tests/happy-path.txt
```

Run all files. The script compiles the program and saves each output in `tests/output/`:

```bash
bash tests/run-tests.sh
```

We tested normal inputs, edge cases, and invalid inputs (for example, empty input, wrong date format, a date that does not exist, and numbers out of range), and input that ends in the middle of a question. See `tests/README.md` for what each file tests.

When the input comes from a file, the typed lines are not shown on the screen, so the questions appear one after another on the same line. This is normal.
