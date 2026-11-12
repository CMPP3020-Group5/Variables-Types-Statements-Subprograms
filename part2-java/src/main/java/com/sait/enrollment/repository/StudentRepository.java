package com.sait.enrollment.repository;

import com.sait.enrollment.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * A fake database. It keeps the students in memory. When the program ends, the data is gone.
 * <p>
 * CONCEPT: Data types - an ArrayList grows when we add items. The variable has the interface type List.
 */
public class StudentRepository {

    // CONCEPT: Scope - static means one shared list for the whole program.
    // final means the variable cannot point to another list.
    private final static List<Student> STUDENT_LIST = new ArrayList<Student>();

    /**
     * Gives the student a new ID and saves the student.
     * <p>
     * CONCEPT: Pass by value - the method gets a copy of the reference. It points to the same Student,
     * so the caller also sees the new ID.
     */
    public void insert(Student student) {
        student.setStudentId(IdGenerator.nextStudentId());
        STUDENT_LIST.add(student);
    }

    /**
     * Removes the student with this ID. Returns 1 if removed, or 0 if not found.
     * <p>
     * CONCEPT: Referencing - findStudentById returns null when there is no student.
     * We check null with == before we use the object.
     * CONCEPT: Conditionals - if handles the "not found" case.
     */
    public int delete(long studentId) {
        Student student = findStudentById(studentId);
        if (student == null) {
            return 0;
        }
        return STUDENT_LIST.remove(student) ? 1 : 0;
    }

    /**
     * Copies the new data into the stored student with this ID. Returns 1 if updated, or 0 if not found.
     * The ID does not change.
     * <p>
     * CONCEPT: Referencing - we change the same object that is in the list. We do not put a new object in it.
     * CONCEPT: Conditionals - if handles the "not found" case.
     */
    public int update(long studentId, Student student) {
        Student existing = findStudentById(studentId);
        if (existing == null) {
            return 0;
        }
        existing.copyFrom(student);
        return 1;
    }

    /**
     * Returns a copy of the list, so the code outside cannot add or remove students.
     * <p>
     * CONCEPT: Referencing - the list is new, but it holds the same Student objects.
     */
    public List<Student> findAll() {
        return List.copyOf(STUDENT_LIST);
    }

    /**
     * Returns the student with this ID, or null if there is none.
     * <p>
     * CONCEPT: Referencing - null means "no object". The caller must check for it.
     */
    public Student findStudentById(long studentId) {
        return STUDENT_LIST.stream().filter(student -> student.getStudentId() == studentId).findFirst().orElse(null);
    }
}
