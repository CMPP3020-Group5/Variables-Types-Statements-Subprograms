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
| `modify-normal.txt` | Modify: change each of the 8 fields once, then display |
| `modify-cancel.txt` | Modify: choose `0` (Cancel), the student stays the same |
| `modify-invalid.txt` | Modify: unknown ID, ID that is not a number, field choice out of range, and wrong values for each field (then a correct value each time) |
| `modify-eof.txt` | The input ends in the middle of Modify |
| `remove-normal.txt` | Remove: add two students, remove them one by one, display after each |
| `remove-invalid.txt` | Remove: unknown ID, ID that is not a number or out of range, removing the same ID twice |
| `demo.txt` | Menu option 5 (parameter-passing demo), then exit |
