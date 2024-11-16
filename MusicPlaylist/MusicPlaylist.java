// Rusheel Chande
// March 9 2023

// This Java class called MusicPlaylist aims to mimic a music playlist with various features.
// It uses a queue to hold the playlist, a stack to track the history, and auxiliary data 
// structures. It has methods for adding a song, playing a song and storing it in the history 
// stack, printing the history stack, clearing it, and deleting a specific number of elements.

import java.util.*;

public class MusicPlaylist {

    // Fields
    private Queue<String> playlist;
    private Stack<String> history;
    private Queue<String> auxillaryQ;
    private Stack<String> auxillaryStk;

    // Constructor
    public MusicPlaylist() {
        playlist = new LinkedList<>();
        history = new Stack<>();
        auxillaryQ = new LinkedList<>();
        auxillaryStk = new Stack<>();
    }

    // Instance Methods

    // This method returns the playlist
    public Queue<String> getPlaylist() {
        return playlist;
    }

    // This method adds a String song to the playlist queue
    public void addSong(String song) {
        playlist.add(song);
    }

    // This method plays a song from the playlist queue, thus removing it,
    // from the queue and adding it to the history stack, and returns the 
    // String song that was played. The method also throws an IllegalStateException
    // if the playlist queue is empty.
    public String playSong() {
        if (playlist.isEmpty()) {
            throw new IllegalStateException("playlist must not be empty");
        }

        String playedSong = playlist.remove();
        history.push(playedSong);

        return playedSong;
    }

    // This method prints all the elements in the history stack. It throws an IllegalStateException
    // if the history stack is empty, and returns the history stack at the end 
    public Stack<String> printHistory() {
        if (history.isEmpty()) {
            throw new IllegalStateException("history is empty, history must not be empty");
        }
        
        // prints and removes history, and adds that history to auxillary queue
        while(!history.isEmpty()) {
            String songHistory = history.pop();
            System.out.println("    " + songHistory);
            auxillaryQ.add(songHistory);
        }

        // restore history and order of the history
        qToS(auxillaryQ, history);
        sToQ(history, auxillaryQ);
        qToS(auxillaryQ, history);

        return history;
    }

    // This method clears the history stack of played songs
    public void clearHistory() {
        while(!history.isEmpty()) {
            history.pop();
        }
    }
    
    // This method deletes elements from the history stack by taking an int, and if its positive
    // elements are deleted from the top of the stack and if its negative they are deleted from the bottom 
    // of the stack. The int also is the number of items to delete. Also, an IllegalArgumentException is
    // thrown if the int deletionNum exceeds the number of elements in the history stack
    public void deleteNumHistory(int deletionNum) {
        if (Math.abs(deletionNum) > history.size()) {
            throw new IllegalArgumentException("that number exceeds the amount in history");
        }

        if (deletionNum >= 0) {
            for (int i = 1; i <= deletionNum; i++) {
                history.pop();
            }
        } else if (deletionNum < 0){
            sToS2(history, auxillaryStk);
            for (int i = 1; i <= Math.abs(deletionNum); i++) {
                auxillaryStk.pop();
            }
            sToS2(auxillaryStk, history);
        }
    }

    // helper methods
    private void qToS(Queue<String> q, Stack<String> s) {
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
    }

    private void sToQ(Stack<String> s, Queue<String> q) {
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }

    private void sToS2(Stack<String> s, Stack<String> s2) {
        while (!s.isEmpty()) {
            s2.push(s.pop());
        }
    }
}