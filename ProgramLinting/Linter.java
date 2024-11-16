// Rusheel Chande
// March 2 2023

// This class performs linting on code that use a list of Check objects

import java.io.*;
import java.util.*;

public class Linter {
    private List<Check> checks;

    // Constructor: makes a Linter object with a given list of Check objects.
    public Linter(List<Check> checks) {
        this.checks = checks;
    }

    // This lints a code with the given file name using the list of Check objects,
    // and returns a list of Error objects whicha are the linting errors found.
    public List<Error> lint(String fileName) throws FileNotFoundException {
        List<Error> errors = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            lineNumber++;
            String line = scanner.nextLine();
            for (Check check : checks) {
                Optional<Error> error = check.lint(line, lineNumber);
                if (error.isPresent()) {
                    errors.add(error.get());
                }
            }
        }
        return errors;
    }
}
