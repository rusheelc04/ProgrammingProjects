// Rusheel Chande
// March 2 2023

import java.io.*;
import java.util.*;

public class LinterMain {
    public static final String FILE_NAME = "TestFile.java";

    public static void main(String[] args) throws FileNotFoundException{
        List<Check> checks = new ArrayList<>();
        checks.add(new LongLineCheck());
        Linter linter = new Linter(checks);
        List<Error> errors = linter.lint(FILE_NAME);
        for (Error e : errors) {
            System.out.println(e);
        }
    }
}
