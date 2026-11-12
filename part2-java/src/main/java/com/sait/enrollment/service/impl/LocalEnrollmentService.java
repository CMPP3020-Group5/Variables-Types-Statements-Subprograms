package com.sait.enrollment.service.impl;

import com.sait.enrollment.model.Student;
import com.sait.enrollment.repository.StudentRepository;
import com.sait.enrollment.service.EnrollmentService;
import com.sait.enrollment.service.exception.EnrollmentException;

import java.util.List;

/**
 * The rules of the system. The menu (UI) calls this class through the EnrollmentService interface,
 * and this class calls the repository.
 * It checks the data first and throws an EnrollmentException when the data is not valid.
 * <p>
 * CONCEPT: Subprograms - each public method does one job: add, modify, remove, show all.
 * CONCEPT: Dynamic binding - this class implements EnrollmentService. In Main the variable has the
 * interface type, but the object is a LocalEnrollmentService, so Java runs the methods of this class at run time.
 * The annotation @Override tells the compiler that the method comes from the interface.
 */
public class LocalEnrollmentService implements EnrollmentService {

    private final StudentRepository studentRepository = new StudentRepository();

    /**
     * Adds a new student. A new student must not have an ID yet (the ID is 0).
     * Returns the same student, now with an ID.
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
     * Changes an existing student. The student must have a valid ID (greater than 0).
     * The data in the given student is copied into the stored student.
     * Throws an exception if the data is not valid or the ID is not found.
     * Returns the student that is stored in the repository (not the object that was passed in).
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
     * Removes the student with this ID. Returns true if removed, false if the ID is not valid
     * or not found.
     * <p>
     * CONCEPT: Pass by value - studentId is a primitive. This method gets a copy,
     * so the caller's variable never changes.
     */
    @Override
    public boolean remove(long studentId) {
        if (studentId <= 0) {
            return false;
        }
        return studentRepository.delete(studentId) > 0;
    }

    /**
     * Returns all students. The list is a copy (see StudentRepository.findAll).
     */
    @Override
    public List<Student> showAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Returns the student with this ID, or null if there is none.
     * <p>
     * CONCEPT: Referencing - this returns the stored object itself, not a copy. The caller should not
     * change it directly. To change a student, make a copy, change the copy and call modify().
     */
    @Override
    public Student findStudentById(long studentId) {
        return studentRepository.findStudentById(studentId);
    }
}
