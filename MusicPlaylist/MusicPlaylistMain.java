// Rusheel Chande
// March 9 2023

// This client program mimics a menu interface to add songs to a playlist, 
// play a song, print song history, clear history, or delete songs from history. 
// It takes user input and interacts with a MusicPlaylist object, which stores and manages 
// the playlist and history of played songs.

import java.util.*;
import java.io.*;

public class MusicPlaylistMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the CSE 122 Music Playlist!");
        Scanner console = new Scanner(System.in);

        // initializing playlist and history objects                    
        MusicPlaylist playlist = new MusicPlaylist();

        // Main menu
        String userChoice = "";
        while (!userChoice.equalsIgnoreCase("Q")) {

            System.out.println("(A) Add song");
            System.out.println("(P) Play song");
            System.out.println("(Pr) Print history");
            System.out.println("(C) Clear history");
            System.out.println("(D) Delete from history");
            System.out.println("(Q) Quit");
            System.out.println();
            System.out.print("Enter your choice: ");

            userChoice = console.nextLine();
            if (userChoice.equalsIgnoreCase("A")) {
                addSong(console, playlist);
                System.out.println();
                System.out.println();
            } else if (userChoice.equalsIgnoreCase("P")) {
                playSong(playlist);
                System.out.println();
                System.out.println();
            } else if (userChoice.equalsIgnoreCase("Pr")) {
                printHistory(playlist);
                System.out.println();
                System.out.println();
            } else if (userChoice.equalsIgnoreCase("C")) {
                clearHistory(playlist);
                System.out.println();
            } else if (userChoice.equalsIgnoreCase("D")) {
                deleteNumHistory(console, playlist);
                System.out.println();
            }
        }
    }

    // This method adds an element/song into the playlist queue. It takes the Scanner
    // console and MusicPlaylist object and adds what the user types in console into
    // the playlist
    public static void addSong(Scanner console, MusicPlaylist playlist) {
        System.out.print("Enter song name: ");
        String addedSong = console.nextLine();
        playlist.addSong(addedSong);
        System.out.println("Successfully added " + addedSong);
    }

    // This method plays a song removing it from the playlist and adds it to
    // the history.
    public static void playSong(MusicPlaylist playlist) {
        String playedSong = playlist.playSong();
        System.out.println("Playing song: " + playedSong);
    }

    // This method prints all the history of played songs.
    public static void printHistory(MusicPlaylist playlist) {
        playlist.printHistory();
    }

    // This method clears the history.
    public static void clearHistory(MusicPlaylist playlist) {
        playlist.clearHistory();
    }

    // This method deletes elements from the history based on the number the user inputs. 
    // If the user enters a negative number, elements are deleted from the bottom of the 
    // history and if the user enters a positive number, elements are deleted from the top
    // of the history. The method takes a Scanner for user input and a MusicPlaylist object
    public static void deleteNumHistory(Scanner console, MusicPlaylist playlist) {
        System.out.println("A positive number will delete from recent history.");
        System.out.println("A negative number will delete from the beginning of history.");
        System.out.print("Enter number of songs to delete: ");

        String deletionStr = console.nextLine();
        int deletionNum = Integer.parseInt(deletionStr);
        
        playlist.deleteNumHistory(deletionNum);
    }
}