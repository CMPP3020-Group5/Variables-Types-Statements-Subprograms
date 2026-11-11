package com.sait.enrollment.util.exception;

/**
 * Thrown when the input ends (EOF) while the program wants to read more.
 * <p>
 * It extends RuntimeException, so it is an unchecked exception. Methods do not need to declare it
 * with throws, and it can go up through many methods until Main catches it.
 */
public class InputEndedException extends RuntimeException {
}
