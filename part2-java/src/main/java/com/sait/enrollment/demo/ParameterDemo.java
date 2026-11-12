package com.sait.enrollment.demo;

import com.sait.enrollment.model.Student;

import java.time.LocalDate;
import java.util.Locale;

/**
 * Shows how Java passes arguments to methods. It does not read input.
 * Each part prints "Before", "Inside method" and "After".
 * <p>
 * Java always passes by value. For an object, the value that is copied is the reference.
 * <p>
 * CONCEPT: Subprograms - run() calls four small methods, one for each case.
 * CONCEPT: Static binding - all methods are static, so Java knows at compile time which one is called.
 */
public class ParameterDemo {

    /**
     * Runs the four parts one after the other.
     */
    public static void run() {
        System.out.println();
        System.out.println("=== Parameter-passing demo ===");

        demoPrimitive();
        demoChangeField();
        demoNewObject();
        demoString();
    }

    // ---------- Part 1: primitive ----------

    /**
     * CONCEPT: Pass by value - an int is copied, so the variable here does not change.
     * CONCEPT: Scope - courses here and courses in changeNumber are two different variables.
     */
    private static void demoPrimitive() {
        System.out.println();
        System.out.println("[1] Primitive (int): pass by value");
        int courses = 5;
        System.out.println("Before: courses = " + courses);
        changeNumber(courses);
        System.out.println("After:  courses = " + courses);
        System.out.println("-> The method got a copy of the number, so the original did not change.");
    }

    private static void changeNumber(int courses) {
        courses = courses + 1;
        System.out.println("Inside method: courses = " + courses);
    }

    // ---------- Part 2: object, change a field ----------

    /**
     * CONCEPT: Pass by value of a reference - the method gets a copy of the reference.
     * Both references point to the same Student, so the change is visible here.
     */
    private static void demoChangeField() {
        System.out.println();
        System.out.println("[2] Object (Student): change a field");
        Student student = createStudent();
        System.out.println("Before: " + describe(student));
        changeGpa(student);
        System.out.println("After:  " + describe(student));
        System.out.println("-> The method got a copy of the reference, but it points to the same object, so the change is visible.");
    }

    private static void changeGpa(Student student) {
        student.setPreviousGpa(4.0);
        System.out.println("Inside method: GPA = " + formatGpa(student.getPreviousGpa()));
    }

    // ---------- Part 3: object, assign a new object ----------

    /**
     * CONCEPT: Java is NOT pass by reference. Assigning a new object to the parameter does not change
     * the caller's variable, because the method has only a copy of the reference.
     */
    private static void demoNewObject() {
        System.out.println();
        System.out.println("[3] Object (Student): assign a new object");
        Student student = createStudent();
        System.out.println("Before: " + student.getFirstName() + " " + student.getLastName());
        replaceStudent(student);
        System.out.println("After:  " + student.getFirstName() + " " + student.getLastName());
        System.out.println("-> The method changed only its own copy of the reference. The caller's reference did not change.");
    }

    private static void replaceStudent(Student student) {
        student = new Student("Amy", "Lee", LocalDate.of(2001, 5, 20), 'F', 3.9, 2, "Software Development", 4);
        System.out.println("Inside method: " + student.getFirstName() + " " + student.getLastName());
    }

    // ---------- Part 4: String ----------

    /**
     * CONCEPT: Data types - String is an object, but it cannot be changed.
     * Adding text makes a new String.
     */
    private static void demoString() {
        System.out.println();
        System.out.println("[4] String: immutable");
        String program = "Software Development";
        System.out.println("Before: program = " + program);
        changeProgram(program);
        System.out.println("After:  program = " + program);
        System.out.println("-> A String cannot be changed. The method made a new String.");
    }

    private static void changeProgram(String program) {
        program = program + " (Year 2)";
        System.out.println("Inside method: program = " + program);
    }

    // ---------- helpers ----------

    private static Student createStudent() {
        return new Student("Jason", "Wu", LocalDate.of(2000, 1, 1), 'M', 3.6, 1, "Software Development", 5);
    }

    private static String describe(Student student) {
        return student.getFirstName() + " " + student.getLastName() + ", GPA " + formatGpa(student.getPreviousGpa());
    }

    private static String formatGpa(double gpa) {
        return String.format(Locale.ROOT, "%.2f", gpa);
    }
}
