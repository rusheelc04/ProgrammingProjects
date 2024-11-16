// Rusheel Chande
// March 2 2023

// This is a class that implements the Check interface to check for empty println
// statements in Java code.

import java.util.Optional;

public class BlankPrintlnCheck implements Check {

    // Takes a String line and Integer lineNumber to check if the input line contains an empty
    // System.out.println() statement and returns an Error object with the error code,
    // line number, and message if found.
    public Optional<Error> lint(String line, int lineNumber) {
        if (line.contains("System.out.println(\"\")")) {
            String message = "Line contains empty System.out.println() statement";
            return Optional.of(new Error(3, lineNumber, message));
        }
        return Optional.empty();
    }
}
