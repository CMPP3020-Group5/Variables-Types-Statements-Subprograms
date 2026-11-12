package com.sait.enrollment.service;

import com.sait.enrollment.model.Student;
import com.sait.enrollment.service.exception.EnrollmentException;

import java.util.List;

/**
 * The actions the menu can ask for. It says WHAT can be done, not HOW.
 * LocalEnrollmentService does the work.
 * <p>
 * CONCEPT: Dynamic binding - Main uses a variable of this interface type.
 * Java runs the method of the real object at run time.
 * Another class (for example one that uses a database) could implement this interface,
 * and Main would not need to change.
 */
public interface EnrollmentService {

    /** Adds a new student (it must not have an ID yet). Returns the student with its new ID. */
    Student add(Student student) throws EnrollmentException;

    /** Changes an existing student. Throws an exception if the data is not valid or the ID is not found. */
    Student modify(Student student) throws EnrollmentException;

    /** Removes the student with this ID. Returns true if it was removed. */
    boolean remove(long studentId);

    /** Returns all students. */
    List<Student> showAllStudents();

    /** Returns the student with this ID, or null if there is none. */
    Student findStudentById(long studentId);
}
