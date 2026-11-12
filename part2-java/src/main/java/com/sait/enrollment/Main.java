package com.sait.enrollment;

import com.sait.enrollment.demo.ParameterDemo;
import com.sait.enrollment.model.Student;
import com.sait.enrollment.service.EnrollmentService;
import com.sait.enrollment.service.exception.EnrollmentException;
import com.sait.enrollment.service.impl.LocalEnrollmentService;
import com.sait.enrollment.util.InputHelper;
import com.sait.enrollment.util.exception.InputEndedException;

import java.time.LocalDate;

/**
 * Program entry point. It shows the menu in a loop and calls the other classes.
 * The program ends when the user chooses Exit or when the input ends (EOF).
 * <p>
 * CONCEPT: Conditionals - switch picks the action, and while repeats the menu.
 */
public class Main {

    // CONCEPT: Subprograms - main is where Java starts the program.
    public static void main(String[] args) {

        try {
            // CONCEPT: Scope - these variables live for the whole loop.
            // We use only one InputHelper (one Scanner).
            InputHelper inputHelper = new InputHelper();
            // CONCEPT: Dynamic binding - the variable type is the interface, but the object is a LocalEnrollmentService.
            // Java runs the methods of the real object.
            EnrollmentService enrollmentService = new LocalEnrollmentService();
            while (true) {
                printMenu();
                // CONCEPT: Scope - choice exists only inside this loop block.
                int choice = inputHelper.readInt("Choose an option (1-6)", 1, 6);
                // CONCEPT: Conditionals - each case ends with break, so Java does not run the next case.
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
                        // Leave main. The finally block prints "Goodbye.".
                        return;
                    default:
                        break;
                }
            }
        } catch (InputEndedException e) {
            System.out.println();
            // The input ended (EOF), for example Ctrl+D or the end of an input file.
            // InputHelper throws InputEndedException, and we catch it only here.
            System.out.println("Input ended.");
        } finally {
            // CONCEPT: Exceptions - finally always runs, so "Goodbye." is printed once.
            exit();
        }

    }

    /**
     * Prints the main menu. The text is fixed, because Python must print the same text.
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
     * Asks for each field, checks it right away, and then adds the student.
     * <p>
     * CONCEPT: Data types - int, double and char are primitive. String and LocalDate are objects.
     * CONCEPT: Pass by value - the method gets copies of the references to inputHelper and service.
     * They point to the same objects.
     * CONCEPT: Exceptions - EnrollmentException is checked, so we must catch it.
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
     * Changes one field of a student. It asks for the ID, shows the student, and asks which field to change.
     * Choosing 0 cancels.
     * <p>
     * The change is made on a copy. The stored student changes only when service.modify() is called.
     * <p>
     * CONCEPT: Data types - the int is converted to a long automatically (implicit conversion).
     * CONCEPT: Referencing - null means "not found". The copy and the stored student are two objects.
     * CONCEPT: Conditionals - switch picks the field.
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

        // Work on a copy. The stored student stays the same until service.modify().
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
     * CONCEPT: Data types - the int is converted to a long automatically.
     * CONCEPT: Pass by value - studentId is copied, so remove() cannot change our variable.
     * CONCEPT: Conditionals - if/else prints success or "not found".
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
     * Prints the goodbye message.
     */
    private static void exit() {
        System.out.println("Goodbye.");
    }


    /**
     * Prints every student, or a message when there are none.
     * <p>
     * CONCEPT: Subprograms - a small method with one job.
     * CONCEPT: Conditionals - if handles the empty list.
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
