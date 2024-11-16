// Rusheel Chande
// April 20 2023

// This class extends Task and represents a puzzle task that requires the correct
// solution to be given and the option to provide hints to the user exists

import java.util.*;

public class PuzzleTask extends Task {
    private String solution;
    private List<String> hints;
    private int hintIndex;

    // Constructor
    // Pre-conditions: hints is not empty
    // Post-conditions: The method initializes a PuzzleTask with the given solution,
    //                  hints, and description
    public PuzzleTask(String solution, List<String> hints, String description) {
        super(description);
        this.solution = solution;
        this.hints = hints;
        this.hintIndex = 0;
    }
    
    // This method returns an updated task description which includes the lastest hint
    // Pre-conditions: None
    // Post-conditions: The method returns an updated task description and also may include the
    //                  latest hint
    public String getDescription() {
        String description = super.getDescription();
        if (hintIndex > 0 && hintIndex <= hints.size()) {
            description += "\n  HINT: " + hints.get(hintIndex - 1);
        }
        return description;
    }
    
    // This method returns a list of valid actions that can be used on this puzzle task
    // Pre-conditions: None
    // Post-conditions: The method returns a List of Strings that contain the options: "hint" and 
    //                  "solve <solution>"
    public List<String> getActionOptions() {
        List<String> actions = new ArrayList<>();
        actions.add("hint");
        actions.add("solve <solution>");
        return actions;
    }

    // Determines whether the puzzle task has been completed or not.
    // Pre-conditions: None
    // Post-conditions: Returns true if the puzzle task is complete, false otherwise.
    public boolean isComplete() {
        return hintIndex > hints.size();
    }

    // Takes a given action to progress towards completing the puzzle task.
    // Pre-conditions: The action parameter must be either "hint" or "solve <solution>".
    // Post-conditions: If a valid hint is provided, increments the hintIndex and returns true.
    // If a valid solution is provided, marks the puzzle as complete and returns true.
    // Returns false if the action is incorrect or invalid.
    // Throws IllegalArgumentException if the action parameter is invalid.
    public boolean takeAction(String action) {
        if (!action.equals("hint") && !action.startsWith("solve ")) {
            throw new IllegalArgumentException("Unacceptable action: " + action);
        }
        if (action.equals("hint")) {
            if (hintIndex < hints.size()) {
                hintIndex++;
                return true;
            }
            return false;
        } else if (action.startsWith("solve ")) {
            String attemptedSolution = action.substring("solve ".length());
            if (attemptedSolution.equals(solution)) {
                hintIndex = hints.size() + 1;
                return true;
            }
            return false;
        }
        return false;
    }
}
