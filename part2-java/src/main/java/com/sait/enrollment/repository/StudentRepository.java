package com.sait.enrollment.repository;

import com.sait.enrollment.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * A fake database. It keeps the students in memory only.
 * When the program ends, all data is lost.
 * <p>
 * CONCEPT: Data types - ArrayList is a non-primitive type that grows when we add items.
 * We store it in a List variable, which is the interface type.
 */
public class StudentRepository {

    // CONCEPT: Scope - static means one shared list for the whole program.
    // final means the variable cannot point to another list, but we can still add and remove items.
    private final static List<Student> STUDENT_LIST = new ArrayList<Student>();

    /**
     * Gives the student a new ID and saves the student in the list.
     * <p>
     * CONCEPT: Pass by reference (a copy of the reference) - the method gets a copy of the
     * reference, but it points to the same Student object. When we call setStudentId here,
     * the caller can also see the new ID.
     */
    public void insert(Student student) {
        student.setStudentId(IdGenerator.nextStudentId());
        STUDENT_LIST.add(student);
    }

    /**
     * Removes the student with this ID. Returns 1 if a student was removed, or 0 if not found.
     * <p>
     * CONCEPT: Referencing - findStudentById returns null when there is no student.
     * We check null with == before we use the object.
     * CONCEPT: Conditionals - an if statement handles the "not found" case.
     */
    public int delete(long studentId) {
        Student student = findStudentById(studentId);
        if (student == null) {
            return 0;
        }
        return STUDENT_LIST.remove(student) ? 1 : 0;
    }

    /**
     * Updates the student with this ID by copying the new data into the stored student.
     * Returns 1 if updated, or 0 if no student has this ID. The ID itself is not changed.
     * <p>
     * CONCEPT: Referencing - we change the same Student object that is in the list,
     * we do not put a new object in its place. Other code that holds this object
     * (for example the list from findAll) will see the new data.
     * CONCEPT: Pass by value of a reference - the parameter "student" is a copy of the reference.
     * We only read from it, so the caller's object is not changed.
     * CONCEPT: Conditionals - an if statement handles the "not found" case.
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
     * CONCEPT: Referencing - the copy is a new list, but it holds the same Student objects.
     * If someone changes a field of a student, the change is also seen in STUDENT_LIST.
     */
    public List<Student> findAll() {
        return List.copyOf(STUDENT_LIST);
    }

    /**
     * Looks for the student with this ID. Returns the student, or null if there is none.
     * <p>
     * CONCEPT: Referencing - null means "no object". The caller must check for null.
     */
    public Student findStudentById(long studentId) {
        return STUDENT_LIST.stream().filter(student -> student.getStudentId() == studentId).findFirst().orElse(null);
    }
}
