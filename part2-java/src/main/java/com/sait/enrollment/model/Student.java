package com.sait.enrollment.model;

import java.time.LocalDate;
import java.util.Locale;

/**
 * Holds the data of one student.
 * <p>
 * CONCEPT: Data types - this class uses primitive types (long, char, double, int)
 * and reference types (String, LocalDate).
 * CONCEPT: Scope - every field is private, so only the methods of this class
 * can use the fields directly. Other classes must use the getters and setters.
 */
public class Student {

    // CONCEPT: Variable names - names use camelCase and say what the value is.
    // Unique ID. It stays 0 until the repository gives the student an ID.
    private long studentId;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    // One letter: 'M', 'F' or 'X'. The input check is done when the user types it.
    private char gender;

    // GPA from the previous school, from 0.0 to 4.0.
    private double previousGpa;

    // Current semester, from 1 to 4.
    private int currentSemester;

    private String program;

    // Number of courses. It is 0 or more.
    private int numberOfCourses;

    /**
     * Creates a student with all fields except the ID. The repository sets the ID later.
     * <p>
     * CONCEPT: Subprograms - a constructor is a special method that sets up a new object.
     * CONCEPT: Scope - a parameter has the same name as a field (for example firstName).
     * The parameter hides the field inside this method, so we write this.firstName
     * to mean the field.
     */
    public Student(String firstName, String lastName, LocalDate birthDate, char gender, double previousGpa, int currentSemester, String program, int numberOfCourses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.previousGpa = previousGpa;
        this.currentSemester = currentSemester;
        this.program = program;
        this.numberOfCourses = numberOfCourses;
    }

    /**
     * Copy constructor: creates a new student with the same ID and the same data as the given student.
     * <p>
     * CONCEPT: Static binding - this class has two constructors with different parameters (method overloading).
     * Java chooses the right one at compile time, from the arguments in new Student(...).
     * CONCEPT: Referencing - the copy is a new object. Changing a field of the copy does not change the original.
     * String and LocalDate cannot be changed, so the copy can safely share them with the original.
     */
    public Student(Student student) {
        this.studentId = student.getStudentId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.birthDate = student.getBirthDate();
        this.gender = student.getGender();
        this.previousGpa = student.getPreviousGpa();
        this.currentSemester = student.getCurrentSemester();
        this.program = student.getProgram();
        this.numberOfCourses = student.getNumberOfCourses();
    }

    public long getStudentId() {
        return studentId;
    }

    /**
     * The repository calls this method to give the student an ID.
     * <p>
     * CONCEPT: Pass by value - a long is a primitive, so the method gets a copy of the number.
     * Changing studentId inside the method does not change the variable of the caller.
     */
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // CONCEPT: Pass by value of a reference - LocalDate is an object. The method gets a copy
    // of the reference, and both references point to the same object.
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getPreviousGpa() {
        return previousGpa;
    }

    public void setPreviousGpa(double previousGpa) {
        this.previousGpa = previousGpa;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getNumberOfCourses() {
        return numberOfCourses;
    }

    public void setNumberOfCourses(int numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }

    /**
     * Returns the student as one line of text, used by "Display all students".
     * Python must print exactly the same text.
     * <p>
     * CONCEPT: Subprograms - a method that returns a value (a String).
     */
    @Override
    public String toString() {
        return String.format(Locale.ROOT,
                "ID: %d | Name: %s %s | DOB: %s | Gender: %c | GPA: %.2f | Semester: %d | Program: %s | Courses: %d",
                studentId, firstName, lastName, birthDate, gender, previousGpa,
                currentSemester, program, numberOfCourses);
    }

    /**
     * Copies all fields from another student into this one, except the ID.
     * Used by the repository to update a stored student.
     * <p>
     * CONCEPT: Subprograms - a void method that changes the object it is called on.
     * CONCEPT: Pass by value of a reference - "student" is a copy of the reference;
     * we only read its fields. Primitive fields (double, int, char) are copied by value.
     * Reference fields (String, LocalDate) copy the reference, but both types are immutable,
     * so sharing them is safe.
     */
    public void copyFrom(Student student) {
        this.setFirstName(student.getFirstName());
        this.setLastName(student.getLastName());
        this.setBirthDate(student.getBirthDate());
        this.setGender(student.getGender());
        this.setPreviousGpa(student.getPreviousGpa());
        this.setCurrentSemester(student.getCurrentSemester());
        this.setProgram(student.getProgram());
        this.setNumberOfCourses(student.getNumberOfCourses());
    }
}
