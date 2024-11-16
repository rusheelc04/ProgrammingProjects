// Rusheel Chande
// April 20 2023

// This class represents a marathon task, which extends the EnduranceTask class,
// and requires a specific number of "run" action to be completed.

public class MarathonTask extends EnduranceTask {
    private int distance;
    private int numRunsRequired;
    private int runsCompleted;

    // Constructor
    // Pre-conditions: numRunsRequired > 0
    // Post-conditions: The method initializes a MarathonTask with the given distance,
    //                  description, and the number of runs required
    public MarathonTask(int distance, String description, int numRunsRequired) {
        super("run", distance, description);
        this.distance = distance;
        this.numRunsRequired = numRunsRequired;
        this.runsCompleted = 0;
    }

    // This method returns an updated task description that includes the distance of the marathon
    // Pre-conditions: None
    // Post-conditions: The method returns the task description and the distance in km 
    public String getDescription() {
        return super.getDescription() + " (" + distance + " km)";
    }

    // Attempts to take the given action towards completing the marathon task.
    // Pre-conditions: The given action must be a valid action specified by the EnduranceTask (parent class).
    // Post-conditions: Returns true if the action is successfully taken and the marathon task is not yet completed;
    //                  returns false otherwise.
    // Exceptions: Throws IllegalArgumentException if the action is not valid for the EnduranceTask (parent class).
    public boolean takeAction(String action) throws IllegalArgumentException {
        boolean result = super.takeAction(action);
        if (result) {
            runsCompleted++;
        }
        return result && runsCompleted <= numRunsRequired;
    }

    // This method determines if the marathon task has been completed
    // Pre-conditions: None
    // Post-conditions: The method returns true if the runsCompleted is greater than or equal to numRunsRequired,
    //                  false otherwise
    public boolean isComplete() {
        return runsCompleted >= numRunsRequired;
    }
}
