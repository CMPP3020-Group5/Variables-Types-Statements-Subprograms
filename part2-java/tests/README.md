# Tests

Each `.txt` file is a list of the lines a user would type. We feed it to the program instead of typing.

Run one file:

```bash
java -cp out com.sait.enrollment.Main < tests/happy-path.txt
```

Run all files (compiles first, saves each output in `tests/output/`):

```bash
bash tests/run-tests.sh
```

| File | What it tests |
|---|---|
| `exit-only.txt` | Choose Exit right away |
| `happy-path.txt` | Add two students, display them, exit |
| `invalid-menu.txt` | Wrong menu input (`abc`, `0`, `7`, empty line), spaces around a valid option, display with no students |
| `invalid-add.txt` | Wrong input for every field of Add (empty, spaces, wrong date format, date that does not exist, future date, wrong gender, GPA and number out of range, text instead of a number), then a correct value each time |
| `boundary.txt` | Lowest and highest allowed values (GPA 0.0 and 4.0, semester 1 and 4, courses 0 and 10) and extra spaces around the input |
| `eof-midway.txt` | The input ends in the middle of Add |
| `empty.txt` | The input is empty (ends at the first prompt) |
