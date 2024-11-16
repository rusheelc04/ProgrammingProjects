// Rusheel Chande
// March 2 2023

// This is a class that implements the Check interface 
// to check for long lines in Java code.

import java.util.Optional;

public class LongLineCheck implements Check {
    
    // Takes a String line and integer lineNumber to check if the input line length is 
    // greater than or equal to 100 characters and returns an Error object with the error
    // code, line number, and message if it is.
    public Optional<Error> lint(String line, int lineNumber) {
        if (line.length() >= 100) {
            String message = "Line is too long (has " + line.length() + " characters)";
            return Optional.of(new Error(1, lineNumber, message));
        }
        return Optional.empty();
    }
}
