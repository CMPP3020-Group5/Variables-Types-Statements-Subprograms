package com.sait.enrollment.repository;

/**
 * Gives out student IDs: 1, 2, 3, ...
 * <p>
 * CONCEPT: Scope - lastStudentId is static. There is only one copy for the whole program,
 * and it lives as long as the program runs (class-level scope).
 */
public class IdGenerator {

    // The last ID that was given out. It starts at 0, so the first ID is 1.
    // IDs are never reused, even after a student is removed.
    private static long lastStudentId = 0L;

    /**
     * Returns the next ID. The ++ adds 1 first, and then returns the new value.
     * <p>
     * CONCEPT: Static binding - this is a static method, so Java knows at compile time
     * which method is called (IdGenerator.nextStudentId()). No object is needed.
     */
    public static long nextStudentId() {
        return ++lastStudentId;
    }
}
