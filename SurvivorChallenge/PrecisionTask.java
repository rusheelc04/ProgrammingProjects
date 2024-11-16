// Rusheel Chande
// April 20 2023

// This class extends Task and represents a precision task that needs a specific
// sequence of actions to be given by the user in order to get completed

import java.util.*;

public class PrecisionTask extends Task {
    private List<String> requiredActions;
    private List<String> completedActions;

    // Constructor
    // Pre-conditions: requiredActions shouldn't be empty
    // Post-conditions: The method initializes a PrecisionTask with the given
    //                  required actions and description
    public PrecisionTask(List<String> requiredActions, String description) {
        super(description);
        this.requiredActions = requiredActions;
        this.completedActions = new ArrayList<>(); 
    }

    // This method checks if the task has been completed.
    // Pre-conditions: None
    // Post-conditions: Returns true if the task is complete (all required actions have been performed),
    // and false if the task is not yet complete.
    public boolean isComplete() {
        return completedActions.equals(requiredActions);
    }

    // This method attempts to perform the given action to progress towards task completion.
    // Pre-conditions: The given action should be one of the valid actions provided by the getActionOptions() method.
    // Post-conditions: If the action is valid and matches the next required action in the sequence, the action
    //                  is added to completedActions and the method returns true. If the action is valid but doesn't
    //                  match the next required action, the method returns false. If the action is not valid,
    //                  an IllegalArgumentException is thrown.
    public boolean takeAction(String action) {
        if(!getActionOptions().contains(action)) {
            throw new IllegalArgumentException("Unacceptable action: " + action);
        }
        if (requiredActions.get(completedActions.size()).equals(action)) {
            completedActions.add(action);
            return true;
        }
        return false;
    }
}
