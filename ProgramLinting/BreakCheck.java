// Rusheel Chande
// March 2 2023

// This class implements the Check interface to check for break statements
// outside of coimments in Java code

import java.util.Optional;

public class BreakCheck implements Check {
    // Takes a String line and Integer lineNumber to check if the input line
    // contains a break statement outside of a comment and returns an Error object
    // with the error code and line number, and message if it does.
    public Optional<Error> lint(String line, int lineNumber) {
        // ignore comments
        int commentIndex = line.indexOf("//");
        
        if (commentIndex >= 0) {
            line = line.substring(0, commentIndex);
        }

        // check for break outside of comments after/if line is shortened
        if (line.contains("break")) {
            return Optional.of(new Error(2, lineNumber, "Line contains 'break' outside of a comment"));
        }

    return Optional.empty();
    }
}
