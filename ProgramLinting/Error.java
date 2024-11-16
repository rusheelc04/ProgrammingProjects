// Rusheel Chande
// March 2 2023

// This class represents a linting error with an error code, line number, 
// and an error message.

public class Error {
    private int code;
    private int lineNumber;
    private String message;

    // Constructor: makes an Error object with the given code, lineNumber,
    // and error message.
    public Error(int code, int lineNumber, String message) {
        this.code = code;
        this.lineNumber = lineNumber;
        this.message = message;
    }

    // Returns the line number where the error occurred
    public int getLineNumber() {
        return lineNumber;
    }

    // Returns the error code
    public int getCode() {
        return code;
    }

    // Error message gets returned
    public String getMessage() {
        return message;
    }

    // Returns a string of the Error object with the line number, error code, and message
    public String toString() {
        return "(Line: " + lineNumber + ") has error code " + code + "\n" + message;
    }
}
