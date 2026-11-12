package com.sait.enrollment.util;

import com.sait.enrollment.util.exception.InputEndedException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Reads and checks what the user types. Each method asks again until the input is valid.
 * If the input ends (EOF), the methods throw InputEndedException.
 * <p>
 * CONCEPT: Subprograms - small methods with parameters and return values.
 * CONCEPT: Data types - the user types text (String). We convert it to int, double, LocalDate or char ourselves.
 */
public class InputHelper {

    // CONCEPT: Scope - a private field that all methods share. final means it always points to the same Scanner.
    // Never call scanner.close(), because it would close System.in.
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Asks until the user types some text. Returns the text without spaces at both ends.
     * <p>
     * CONCEPT: Conditionals - if checks for empty input, and while asks again.
     * CONCEPT: Pass by value - prompt is a copy of the reference. A String cannot be changed,
     * so the caller's text is safe.
     */
    public String readNonEmpty(String prompt) throws InputEndedException {
        while (true) {
            System.out.print(prompt + ": ");
            checkEOF();
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                printError("value cannot be empty.");
                continue;
            }
            return input.trim();
        }
    }

    /**
     * Asks until the user types a whole number from min to max (both included).
     * <p>
     * CONCEPT: Pass by value - min and max are copied.
     * CONCEPT: Exceptions - Integer.parseInt throws NumberFormatException when the text is not a number.
     * We catch it and ask again.
     */
    public int readInt(String prompt, int min, int max) throws InputEndedException {
        while (true) {
            System.out.print(prompt + ": ");
            checkEOF();
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                printError("value cannot be empty.");
                continue;
            }
            try {
                // Convert the text to an int (explicit conversion).
                int value = Integer.parseInt(input.trim());
                if (min <= value && value <= max) {
                    return value;
                } else {
                    printError("please enter a whole number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                printError("please enter a whole number between " + min + " and " + max + ".");
            }
        }
    }

    /**
     * Asks until the user types a real date (yyyy-MM-dd) that is not in the future.
     * <p>
     * CONCEPT: Data types - LocalDate is an object type. parse() rejects wrong formats
     * and dates that do not exist, like 2001-02-30.
     */
    public LocalDate readDate(String prompt) throws InputEndedException {
        while (true) {
            System.out.print(prompt + ": ");
            checkEOF();
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                printError("value cannot be empty.");
                continue;
            }
            try {
                // parse() throws DateTimeParseException when the text is not a valid date.
                LocalDate date = LocalDate.parse(input.trim());
                if (date.isAfter(LocalDate.now())) {
                    printError("date of birth cannot be in the future.");
                    continue;
                }
                return date;
            } catch (DateTimeParseException e) {
                printError("invalid date, use yyyy-MM-dd.");
            }

        }
    }

    /**
     * Asks until the user types a number from min to max (both included). It is used for the GPA.
     * NaN is rejected too, because every comparison with NaN is false.
     * <p>
     * CONCEPT: Data types - double is a primitive type for numbers with a decimal point.
     */
    public double readDouble(String prompt, double min, double max) throws InputEndedException {
        while (true) {
            System.out.print(prompt + ": ");
            checkEOF();
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                printError("value cannot be empty.");
                continue;
            }
            try {
                // Convert the text to a double (explicit conversion).
                double value = Double.parseDouble(input.trim());
                if (min <= value && value <= max) {
                    return value;
                } else {
                    printError("please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                printError("please enter a number between " + min + " and " + max + ".");
            }
        }
    }

    /**
     * Asks until the user types M, F or X (lower case is accepted). Returns an upper case char.
     * <p>
     * CONCEPT: Data types - char holds one character. A String is not a char, so we use charAt(0).
     * CONCEPT: Referencing - == compares references, but equals() compares the text.
     * So we use equals() for Strings.
     */
    public char readGender(String prompt) throws InputEndedException {
        while (true) {
            System.out.print(prompt + ": ");
            checkEOF();
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                printError("value cannot be empty.");
                continue;
            }
            String gender = input.trim().toUpperCase();
            if (!gender.equals("F") && !gender.equals("M") && !gender.equals("X")) {
                printError("gender must be M, F or X.");
                continue;
            }
            // The text has one character here, so charAt(0) is safe.
            return gender.charAt(0);
        }
    }

    /** Prints an error in one fixed format. Python must use the same format. */
    private void printError(String errorMessage) {
        System.out.println("[ERROR]: " + errorMessage);
    }

    /**
     * Throws InputEndedException when there is no more input (EOF).
     * We call it before every nextLine(), because nextLine() crashes when there is no more input.
     */
    private void checkEOF() throws InputEndedException {
        if (!scanner.hasNextLine()) {
            throw new InputEndedException();
        }
    }
}
