package com.sait.enrollment.demo;

import com.sait.enrollment.model.Student;

import java.time.LocalDate;
import java.util.Locale;

/**
 * Shows how Java passes arguments to methods. It does not read any input and it does not use the stored students.
 * Each part prints "Before", "Inside method" and "After", so we can see what the method changed.
 * <p>
 * Java always passes arguments by value. For an object, the value that is copied is the reference.
 * <p>
 * CONCEPT: Subprograms - run() calls four small methods, and each method shows one case.
 * CONCEPT: Static binding - all methods here are static, so Java knows at compile time which method is called.
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
     * CONCEPT: Pass by value - an int is a primitive. The method gets a copy of the number,
     * so the variable in this method does not change.
     * CONCEPT: Scope - the variable courses here and the parameter courses in changeNumber have the same name,
     * but they are two different variables. Each one exists only inside its own method.
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
     * Both references point to the same Student object, so a change made through the copy is visible here.
     * CONCEPT: Referencing - two references to one object.
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
     * CONCEPT: Java is NOT pass by reference. If it were, assigning a new object to the parameter
     * would change the variable of the caller. It does not, because the method only has its own copy
     * of the reference.
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
     * CONCEPT: Data types - String is a reference type, but it is immutable (it cannot be changed).
     * Adding text to it creates a new String, so the caller's String stays the same.
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
