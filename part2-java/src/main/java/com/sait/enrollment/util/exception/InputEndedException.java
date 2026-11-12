package com.sait.enrollment.util.exception;

/**
 * Thrown when the input ends (EOF) while the program wants to read more.
 * <p>
 * It extends RuntimeException (unchecked), so methods do not need to declare it. Main catches it.
 */
public class InputEndedException extends RuntimeException {
}
