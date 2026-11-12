package com.sait.enrollment.service.exception;

/**
 * Thrown when a student operation is not valid, for example bad data or an unknown ID.
 * <p>
 * It extends Exception (checked), so the caller must catch it or declare it with throws.
 */
public class EnrollmentException extends Exception {

    public EnrollmentException(String message) {
        super(message);
    }
}
