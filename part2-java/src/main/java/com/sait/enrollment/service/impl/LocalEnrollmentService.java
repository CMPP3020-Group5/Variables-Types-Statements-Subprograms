package com.sait.enrollment.service.impl;

import com.sait.enrollment.model.Student;
import com.sait.enrollment.repository.StudentRepository;
import com.sait.enrollment.service.EnrollmentService;
import com.sait.enrollment.service.exception.EnrollmentException;

import java.util.List;

/**
 * The rules of the system. Main calls it through the EnrollmentService interface,
 * and it calls the repository. It checks the data and throws an EnrollmentException when the data is not valid.
 * <p>
 * CONCEPT: Subprograms - each method does one job.
 * CONCEPT: Dynamic binding - this class implements EnrollmentService.
 * The annotation @Override tells the compiler that a method comes from the interface.
 */
public class LocalEnrollmentService implements EnrollmentService {

    private final StudentRepository studentRepository = new StudentRepository();

    /**
     * Adds a new student. A new student must not have an ID yet (it is 0). Returns the student with its new ID.
     * <p>
     * CONCEPT: Conditionals - if checks for null and for an existing ID.
     */
    @Override
    public Student add(Student student) throws EnrollmentException {
        if (null == student || student.getStudentId() > 0) {
            throw new EnrollmentException("invalid student data.");
        }
        studentRepository.insert(student);
        return student;
    }

    /**
     * Changes an existing student. The student must have a valid ID. The data is copied into the stored student.
     * Throws an exception if the data is not valid or the ID is not found. Returns the stored student.
     */
    @Override
    public Student modify(Student student) throws EnrollmentException {
        if (null == student || student.getStudentId() <= 0) {
            throw new EnrollmentException("invalid student data.");
        }

        if (studentRepository.update(student.getStudentId(), student) <= 0) {
            throw new EnrollmentException("student not found.");
        }
        return studentRepository.findStudentById(student.getStudentId());
    }

    /**
     * Removes the student with this ID. Returns true if removed, or false if the ID is not valid or not found.
     * <p>
     * CONCEPT: Pass by value - studentId is copied, so the caller's variable does not change.
     */
    @Override
    public boolean remove(long studentId) {
        if (studentId <= 0) {
            return false;
        }
        return studentRepository.delete(studentId) > 0;
    }

    /**
     * Returns all students (a copy of the list).
     */
    @Override
    public List<Student> showAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Returns the student with this ID, or null if there is none.
     * <p>
     * CONCEPT: Referencing - this returns the stored object itself, not a copy.
     * To change a student, copy it, change the copy, and call modify().
     */
    @Override
    public Student findStudentById(long studentId) {
        return studentRepository.findStudentById(studentId);
    }
}
