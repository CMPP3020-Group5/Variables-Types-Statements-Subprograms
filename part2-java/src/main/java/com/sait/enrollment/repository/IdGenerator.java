package com.sait.enrollment.repository;

/**
 * Gives out student IDs: 1, 2, 3, ...
 * <p>
 * CONCEPT: Scope - lastStudentId is static, so there is one copy for the whole program.
 */
public class IdGenerator {

    // The last ID given out. It starts at 0, so the first ID is 1. IDs are never reused.
    private static long lastStudentId = 0L;

    /**
     * Returns the next ID (++ adds 1 first, then returns the new value).
     * <p>
     * CONCEPT: Static binding - this is a static method. Java knows at compile time which method is called,
     * and no object is needed.
     */
    public static long nextStudentId() {
        return ++lastStudentId;
    }
}
