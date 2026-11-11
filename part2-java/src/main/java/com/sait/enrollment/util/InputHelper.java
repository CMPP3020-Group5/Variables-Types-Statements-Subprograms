package com.sait.enrollment.util;

import com.sait.enrollment.util.exception.InputEndedException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Reads and checks the text the user types. Every read method keeps asking until the input is valid.
 * If the input ends (EOF), the methods throw InputEndedException.
 * <p>
 * CONCEPT: Subprograms - small methods with parameters and return values. Each method has one job.
 * CONCEPT: Data types - the user always types text (String). We convert it to int, double,
 * LocalDate or char ourselves, so the conversion is explicit.
 */
public class InputHelper {

    // CONCEPT: Scope - private field: only this class can use the Scanner, and all methods share it.
    // final means the variable always points to the same Scanner. Never call scanner.close(), it would close System.in.
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Asks until the user types some text that is not empty. Returns the text without spaces at both ends.
     * <p>
     * CONCEPT: Conditionals - an if statement checks for empty input, and the while loop asks again.
     * CONCEPT: Pass by value - the parameter prompt is a copy of the reference to the String.
     * Strings cannot be changed, so the caller's text is never affected.
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
     * CONCEPT: Pass by value - min and max are primitives, so the method gets copies of the numbers.
     * CONCEPT: Exceptions - Integer.parseInt throws NumberFormatException when the text is not a number.
     * We catch it and ask again, so the program does not crash.
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
                // Explicit conversion from String to int.
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
     * Asks until the user types a real date in the format yyyy-MM-dd that is not in the future.
     * <p>
     * CONCEPT: Data types - LocalDate is a reference type. Its parse method checks the format and also
     * rejects dates that do not exist, for example 2001-02-30.
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
                // Throws DateTimeParseException when the text is not a valid date.
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
     * Asks until the user types a number from min to max (both included).
     * It is used for the GPA. The check also rejects NaN, because every comparison with NaN is false.
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
                // Explicit conversion from String to double.
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
     * Asks until the user types M, F or X (lower case is also accepted). Returns it as an upper case char.
     * <p>
     * CONCEPT: Data types - char holds one character. A String is not a char, so we use charAt(0).
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
            // The text has exactly one character here, so charAt(0) is safe.
            return gender.charAt(0);
        }
    }

    /** Prints an error in one fixed format. The Python version must use the same format. */
    private void printError(String errorMessage) {
        System.out.println("[ERROR]: " + errorMessage);
    }

    /**
     * Checks that there is another line to read. If not, the input has ended (EOF) and we throw an exception.
     * We must call this before every nextLine(), because nextLine() crashes when there is no more input.
     */
    private void checkEOF() throws InputEndedException {
        if (!scanner.hasNextLine()) {
            throw new InputEndedException();
        }
    }
}
