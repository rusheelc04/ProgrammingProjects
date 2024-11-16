// Rusheel Chande
// April 20 2023

// This class represents and endurance task that where a specific action has to 
// be performed over and over a again for a specific number of times in order to 
// be considered complete. It is an extension of Task.

public class EnduranceTask extends Task {
    private int duration;
    private int completedActions;
    private String type;

    // Contructor
    // Pre-conditions: duration > 0
    // Post-conditions: This method initializes an EnduranceTask with the given type,
    //                  duration, and description
    public EnduranceTask(String type, int duration, String description) {
        super(description);
        this.type = type;
        this.duration = duration;
        this.completedActions = 0;
    }

    // This method determines whether the task has been completed or not
    // Pre-conditions: None
    // Post-conditions: Returns true if the completed actions are equal to the 
    //                  task's duration, and returns false if they aren't.
    //                  Determines whether the task is over.
    public boolean isComplete() {
        return completedActions == duration;
    }

    // This method attempts to complete a given task by taking a specified action.
    // Pre-conditions: The given action should be a valid action returned by the getActionOptions() method.
    // Post-conditions: Returns true if the action successfully completes the task, otherwise returns false.
    //                  Throws an IllegalArgumentException if the action is invalid.
    public boolean takeAction(String action) {
        if(!getActionOptions().contains(action)) {
            throw new IllegalArgumentException("Unacceptable action: " + action);
        }
        if (action.equals(type)) {
            completedActions++;
            return true;
        }
        return false;
    }
}
