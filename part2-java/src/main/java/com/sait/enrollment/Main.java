package com.sait.enrollment;

import com.sait.enrollment.model.Student;
import com.sait.enrollment.service.EnrollmentService;
import com.sait.enrollment.service.exception.EnrollmentException;

import java.time.LocalDate;

public class Main {
    
    public static void main(String[] args) {
        // Create one service object. All operations go through it.
        EnrollmentService service = new EnrollmentService();

        // Add two students. add() can throw an exception, so we use try-catch.
        try {
            service.add(new Student("Jason", "Wu", LocalDate.of(2000, 1, 1), 'M', 3.6, 1, "Software Development", 5));
            service.add(new Student("Amy", "Lee", LocalDate.of(2001, 5, 20), 'F', 3.9, 2, "Software Development", 4));
        } catch (EnrollmentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("--- After adding two students ---");
        printAll(service);

        // Remove a student that exists (true) and one that does not exist (false).
        System.out.println("Remove student 1: " + service.remove(1));
        System.out.println("Remove student 99: " + service.remove(99));

        System.out.println("--- After removing ---");
        printAll(service);
    }

    /**
     * Prints every student, or a message when there are none.
     *
     * CONCEPT: Subprograms - a small method that we call two times from main.
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
