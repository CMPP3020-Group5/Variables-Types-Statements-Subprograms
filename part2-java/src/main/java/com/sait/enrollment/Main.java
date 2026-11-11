package com.sait.enrollment;

import com.sait.enrollment.demo.ParameterDemo;
import com.sait.enrollment.model.Student;
import com.sait.enrollment.service.EnrollmentService;
import com.sait.enrollment.service.exception.EnrollmentException;
import com.sait.enrollment.util.InputHelper;
import com.sait.enrollment.util.exception.InputEndedException;

import java.time.LocalDate;

/**
 * Program entry point. It shows the menu in a loop and calls the other classes.
 * The program ends when the user chooses Exit or when the input ends (EOF).
 * <p>
 * CONCEPT: Conditionals - a switch statement sends each menu choice to the right method,
 * and a while loop repeats the menu.
 */
public class Main {

    // CONCEPT: Subprograms - main is the method where Java starts the program.
    public static void main(String[] args) {

        try {
            // CONCEPT: Scope - these two variables are declared before the loop (inside the try block), so they live for the whole loop.
            // We create only one InputHelper (one Scanner). Many Scanners on System.in can lose input.
            InputHelper inputHelper = new InputHelper();
            EnrollmentService enrollmentService = new EnrollmentService();
            while (true) {
                printMenu();
                // CONCEPT: Scope - choice is declared inside the loop, so it only exists in this block.
                // A new variable is made on each round of the loop.
                int choice = inputHelper.readInt("Choose an option (1-6)", 1, 6);
                // CONCEPT: Conditionals - switch chooses one case. Each case ends with break so Java
                // does not run the next case too.
                switch (choice) {
                    case 1:
                        addStudent(inputHelper, enrollmentService);
                        break;
                    case 2:
                        modifyStudent(inputHelper, enrollmentService);
                        break;
                    case 3:
                        removeStudent(inputHelper, enrollmentService);
                        break;
                    case 4:
                        printAll(enrollmentService);
                        break;
                    case 5:
                        ParameterDemo.run();
                        break;
                    case 6:
                        // Leave main. The finally block below prints "Goodbye." once.
                        return;
                    default:
                        break;
                }
            }
        } catch (InputEndedException e) {
            System.out.println();
            // The input stream ended (EOF), for example Ctrl+D or the end of an input file.
            // All methods in InputHelper throw InputEndedException, and we catch it only here.
            System.out.println("Input ended.");
        } finally {
            // CONCEPT: Exceptions - finally always runs, for both Exit and EOF, so "Goodbye." is printed once.
            exit();
        }

    }

    /**
     * Prints the main menu. The text is fixed because the Python version must print the same text.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("=== Student Enrollment Management System ===");
        System.out.println("1. Add student");
        System.out.println("2. Modify student");
        System.out.println("3. Remove student");
        System.out.println("4. Display all students");
        System.out.println("5. Parameter-passing demo");
        System.out.println("6. Exit");
    }

    /**
     * Asks for all fields of a new student, one by one, and then adds the student.
     * Each field is checked as soon as it is typed. The student is added only after all fields are valid.
     * <p>
     * CONCEPT: Data types - int, double and char are primitive types. String and LocalDate are reference types.
     * CONCEPT: Pass by value of a reference - the method gets copies of the references to inputHelper
     * and service. Both copies point to the same objects that main created.
     * CONCEPT: Exceptions - EnrollmentException is a checked exception, so we must catch it here.
     */
    private static void addStudent(InputHelper inputHelper, EnrollmentService service) {
        String firstName = inputHelper.readNonEmpty("First name");
        String lastName = inputHelper.readNonEmpty("Last name");
        LocalDate birthDate = inputHelper.readDate("Date of birth (yyyy-MM-dd)");
        char gender = inputHelper.readGender("Gender (M/F/X)");
        double previousGpa = inputHelper.readDouble("Previous GPA (0.0-4.0)", 0.0, 4.0);
        int currentSemester = inputHelper.readInt("Current semester (1-4)", 1, 4);
        String program = inputHelper.readNonEmpty("Program");
        int numberOfCourses = inputHelper.readInt("Number of courses", 0, 10);

        Student student = new Student(firstName, lastName, birthDate, gender,
                previousGpa, currentSemester, program, numberOfCourses);
        try {
            service.add(student);
            System.out.println("Student added.");
        } catch (EnrollmentException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }

    /**
     * Changes one field of an existing student: asks for the ID, shows the student, asks which field to change,
     * and reads the new value. Choosing 0 cancels.
     * <p>
     * The change is made on a copy of the student. The stored student is updated only at the end, by
     * service.modify(). So if the input ends in the middle, the stored data is not changed.
     * <p>
     * CONCEPT: Data types - readInt returns an int, and we store it in a long. Java converts it by itself
     * (implicit conversion), because every int value fits in a long.
     * CONCEPT: Referencing - the null check handles "student not found". The copy and the stored student
     * are two different objects.
     * CONCEPT: Conditionals - a switch chooses the field to change.
     */
    private static void modifyStudent(InputHelper inputHelper, EnrollmentService service) {
        long studentId = inputHelper.readInt("Student ID", 1, Integer.MAX_VALUE);
        Student student = service.findStudentById(studentId);
        if (student == null) {
            System.out.println("[ERROR]: student not found.");
            return;
        }
        System.out.println(student);
        System.out.println();
        System.out.println("0. Cancel");
        System.out.println("1. First name");
        System.out.println("2. Last name");
        System.out.println("3. Date of birth");
        System.out.println("4. Gender");
        System.out.println("5. Previous GPA");
        System.out.println("6. Current semester");
        System.out.println("7. Program");
        System.out.println("8. Number of courses");

        // Work on a copy. The stored student does not change until service.modify() is called.
        Student updatingStudent = new Student(student);
        int choice = inputHelper.readInt("Choose a field to change (0-8)", 0, 8);
        switch (choice) {
            case 0:
                System.out.println("Modify cancelled.");
                return;
            case 1:
                updatingStudent.setFirstName(inputHelper.readNonEmpty("First name"));
                break;
            case 2:
                updatingStudent.setLastName(inputHelper.readNonEmpty("Last name"));
                break;
            case 3:
                updatingStudent.setBirthDate(inputHelper.readDate("Date of birth (yyyy-MM-dd)"));
                break;
            case 4:
                updatingStudent.setGender(inputHelper.readGender("Gender (M/F/X)"));
                break;
            case 5:
                updatingStudent.setPreviousGpa(inputHelper.readDouble("Previous GPA (0.0-4.0)", 0.0, 4.0));
                break;
            case 6:
                updatingStudent.setCurrentSemester(inputHelper.readInt("Current semester (1-4)", 1, 4));
                break;
            case 7:
                updatingStudent.setProgram(inputHelper.readNonEmpty("Program"));
                break;
            case 8:
                updatingStudent.setNumberOfCourses(inputHelper.readInt("Number of courses", 0, 10));
                break;
            default:
                break;
        }
        try {
            service.modify(updatingStudent);
            System.out.println("Student updated.");
        } catch (EnrollmentException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }

    /**
     * Removes the student with the ID that the user types.
     * <p>
     * CONCEPT: Data types - the int from readInt is converted to a long automatically (implicit conversion).
     * CONCEPT: Conditionals - if/else prints a message for success or for "not found".
     * CONCEPT: Pass by value - the long studentId is copied into service.remove(). The method cannot change our variable.
     */
    private static void removeStudent(InputHelper inputHelper, EnrollmentService service) {
        long studentId = inputHelper.readInt("Student ID", 1, Integer.MAX_VALUE);
        if (service.remove(studentId)) {
            System.out.println("Student removed.");
        } else {
            System.out.println("[ERROR]: student not found.");
        }
    }

    /**
     * Prints the goodbye message. It does not stop the program by itself.
     */
    private static void exit() {
        System.out.println("Goodbye.");
    }


    /**
     * Prints every student, or a message when there are none.
     * <p>
     * CONCEPT: Subprograms - a small method with its own job. The menu calls it for "Display all students".
     * CONCEPT: Conditionals - if handles the empty list.
     * CONCEPT: Pass by value of a reference - the method gets a copy of the reference to the service.
     */
    private static void printAll(EnrollmentService service) {
        if (service.showAllStudents().isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : service.showAllStudents()) {
            System.out.println(student);
        }
    }
}
