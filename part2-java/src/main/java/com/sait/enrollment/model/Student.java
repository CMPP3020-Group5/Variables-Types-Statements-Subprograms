package com.sait.enrollment.model;

import java.time.LocalDate;
import java.util.Locale;

/**
 * Holds the data of one student.
 * <p>
 * CONCEPT: Data types - long, char, double and int are primitive. String and LocalDate are objects.
 * CONCEPT: Scope - the fields are private. Other classes use the getters and setters.
 */
public class Student {

    // CONCEPT: Variable names - camelCase names that say what the value is.
    // Unique ID. It is 0 until the repository gives one.
    private long studentId;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    // 'M', 'F' or 'X'.
    private char gender;

    // GPA from the previous school (0.0 to 4.0).
    private double previousGpa;

    // Current semester (1 to 4).
    private int currentSemester;

    private String program;

    // Number of courses (0 or more).
    private int numberOfCourses;

    /**
     * Creates a student. The ID is not set here. The repository sets it later.
     * <p>
     * CONCEPT: Subprograms - a constructor sets up a new object.
     * CONCEPT: Scope - a parameter and a field have the same name. The parameter hides the field,
     * so we write this.firstName for the field.
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
     * Copy constructor: makes a new student with the same ID and data as the given student.
     * <p>
     * CONCEPT: Static binding - there are two constructors (overloading). Java picks one at compile time.
     * CONCEPT: Referencing - the copy is a new object. Changing it does not change the original.
     * String and LocalDate cannot be changed, so sharing them is safe.
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
     * The repository calls this to give the student an ID.
     * <p>
     * CONCEPT: Pass by value - a long is copied, so the caller's variable does not change.
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

    // CONCEPT: Pass by value - the method gets a copy of the reference.
    // Both references point to the same LocalDate object.
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
     * Returns the student as one line of text. Python must print exactly the same text.
     * <p>
     * CONCEPT: Dynamic binding - this overrides Object.toString(). println(student) calls this version at run time.
     * CONCEPT: Subprograms - a method that returns a value.
     */
    @Override
    public String toString() {
        return String.format(Locale.ROOT,
                "ID: %d | Name: %s %s | DOB: %s | Gender: %c | GPA: %.2f | Semester: %d | Program: %s | Courses: %d",
                studentId, firstName, lastName, birthDate, gender, previousGpa,
                currentSemester, program, numberOfCourses);
    }

    /**
     * Copies all fields except the ID from another student into this one.
     * The repository uses it to update a stored student.
     * <p>
     * CONCEPT: Subprograms - a void method that changes its own object.
     * CONCEPT: Pass by value - primitive fields are copied by value.
     * String and LocalDate cannot be changed, so sharing the reference is safe.
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
