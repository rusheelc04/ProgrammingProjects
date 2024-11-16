// Rusheel Chande
// May 3 2023

// This Repository class is a simplified version control system for managing commits and repositories.
// It supports basic operations like adding commits, getting the commit history, resetting, dropping and
// squashing commits in a repository.

import java.util.*;

public class Repository {

    private String name;
    private Commit head;

    // This method creates a new Repository with the given name
    // Pre-conditions: name is not null or empty or else it will throw an IllegalArgumentException
    // Post-conditions: A new Repository gets created with the given name or
    //                  an IllegalArgumentException is thrown if pre-conditions
    //                  are not met
    public Repository(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    // This method retrieves the ID of the head commit in the Repository
    // Pre-conditions: None
    // Post-conditions: Returns the head commit's ID, or null if no commits
    public String getRepoHead() {
        if (head == null) {
            return null;
        }
        return head.id;
    }

    // This method returns the Repository name and its head commit information
    // Pre-conditions: None
    // Post-conditions: Returns a string with the Repository's name and head commit info
    //                  or if head is null, then the name is returned along with a No commits
    //                  message
    public String toString() {
        if (head == null) {
            return name + " - No commits";
        }
        return name + " - Current head: " + head.toString();
    }

    // This method checks if the Repository contains a commit with the given targetId
    // Pre-conditions: targetId is not null
    // Post-conditions: Returns true if targetId is found, otherwise false
    public boolean contains(String targetId) {
        Commit curr = head;
        while (curr != null) {
            if (curr.id.equals(targetId)) {
                return true;
            }
            curr = curr.past;
        }
        return false;
    }

    // This method retrieves the commit history of the repository, up to n commits. 
    // If the given n exceeds the number of commits in the repository, the method will 
    // retrieve all the commits. If the repository has no commits, it will return an empty string.
    // Pre-conditions: n is a positive integer. Non-positive values will result in an 
    //                 IllegalArgumentException.
    // Post-conditions: Returns a string containing the history of the last n commits 
    //                  (or all if there are fewer than n). If n is a non-positive number, 
    //                  an IllegalArgumentException is thrown. If there are no commits in 
    //                  the repository, an empty string is returned.
    public String getHistory(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n cannot be a non-positive");
        }

        Commit curr = head;
        String history = "";
        int count = 0;

        while (curr != null && count < n) {
            if (count > 0) {
                history += "\n";
            }
            history += curr.toString();
            curr = curr.past;
            count++;
        }

        return history;
    }

    // This method adds a new commit with the given message to the Repository
    // Pre-conditions: message is not null
    // Post-conditions: Returns the ID of the newly created commit
    public String commit(String message) {
        Commit newCommit = new Commit(message, head);
        head = newCommit;
        return newCommit.id;
    }

    // This method resets the repository to n commits back.If n exceeds the number of commits
    // in the repository, the repository will be reset to the initial commit. If the repository 
    // has no commits, an IllegalStateException will be thrown.
    // Pre-conditions: n has to be a positive integer. Non-positive values will throw in an 
    //                 IllegalArgumentException.
    // Post-conditions: The repository's head is set to the commit n commits back (or to the initial commit if there are fewer than n). 
    //                  If n is a non-positive number, an IllegalArgumentException is thrown and if there are no commits in the repository,
    //			        an IllegalStateException is thrown.
    public void reset(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n can't be a non-positive");
        }

        Commit curr = head;
        int counter = 0;
        while(curr != null && counter < n) {
            curr = curr.past;
            counter++;
        }
        head = curr;
    }

    // This method drops a commit with the given targetId from the Repository
    // Pre-conditions: targetId is not null
    // Post-conditions: Returns true if the commit is removed, otherwise false
    public boolean drop(String targetId) {
        if (head == null) {
            return false;
        }
        if (head.id.equals(targetId)) {
            head = head.past;
            return true;
        }

        Commit curr = head;
        while(curr.past != null) {
            if (curr.past.id.equals(targetId)) {
                curr.past = curr.past.past;
                return true;
            }
            curr = curr.past;
        }

        return false;
    }

    // This method tries to squash two consecutive commits in the repository's commit history 
    // into a single commit based on a provided targetId. The squash operation may be unsuccessful 
    // under some conditions, which will result in the method returning false.
    // Pre-conditions: The targetId should not be null.
    // Post-conditions: The method will return true if successful, and the repository's commit history will 
    //			        be updated accordingly. In an unsuccessful squash, the method will return false,
    //                  and the commit history will remain unchanged.
    public boolean squash(String targetId) {
        if (head == null) {
            return false;
        }
        if (head.id.equals(targetId)) {
            String newMessage = "SQUASHED: " + head.message;
            Commit newCommit;
            if (head.past != null) {
                newMessage += "/" + head.past.message;
                newCommit = new Commit(newMessage, head.past.past);
            } else {
                newCommit = new Commit(newMessage, null);
            }
            head = newCommit;
            return true;
        }
        if (head.past != null && head.past.id.equals(targetId)) {
            if (head.past.past == null) {
                return false;
            }
            String newMessage = "SQUASHED: " + head.message + "/" + head.past.message;
            Commit newCommit = new Commit(newMessage, head.past.past.past);
            head = newCommit;
            return true;
        }
        Commit curr = head;
        while (curr.past != null && curr.past.past != null) {
            if (curr.past.past.id.equals(targetId)) {
                if (curr.past.past.past == null) {
                    return false;
                }
                String newMessage = "SQUASHED: " + curr.past.message + "/" + curr.past.past.message;
                Commit newCommit = new Commit(newMessage, curr.past.past.past);
                curr.past = newCommit;
                return true;
            }
            curr = curr.past;
        }
        return false;
    }

    public static class Commit {
        public final String id;
        public final String message;
        public Commit past;

        // This method creates a new Commit with the given message and past commit
        // Pre-conditions: message is not null
        // Post-conditions: A new Commit is created with a unique ID, the given message, and past commit
        public Commit(String message, Commit past) {
            this.id = getNewId();
            this.message = message;
            this.past = past;
        }

        // This method creates a new Commit with the given message and no past commit
        // Pre-conditions: message is not null
        // Post-conditions: A new Commit is created with a unique ID and the given message
        public Commit(String message) {
            this(message, null);
        }

        // This method returns the Commit's ID and message as a string
        // Pre-conditions: None
        // Post-conditions: Returns a string with the Commit's ID and message
        public String toString() {
            return id + ": " + message;
        }

        // This method generates a new unique ID for a commit
        // Pre-conditions: None
        // Post-conditions: Returns a unique string ID
        private static String getNewId() {
            return UUID.randomUUID().toString();
        }
    }
}
