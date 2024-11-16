// Rusheel Chande
// March 9 2023

// This program tests various aspects of the MusicPlaylist
// programs using JUnit test.

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.jupiter.api.DisplayName;

public class MusicPlaylistTests {

    @Test
    @DisplayName("Testing the add method and add functionality")
    public void testAddSong() {
        MusicPlaylist playlist = new MusicPlaylist();
        playlist.addSong("song1");
        playlist.addSong("song2");
        playlist.addSong("song3");

        Queue<String> expectedPlaylist = new LinkedList<String>();
        expectedPlaylist.add("song1");
        expectedPlaylist.add("song2");
        expectedPlaylist.add("song3");
        
        assertEquals(expectedPlaylist, playlist.getPlaylist());
    }

    @Test
    @DisplayName("Testing the playSong method and its functionality")
    public void testPlaySong() {
        MusicPlaylist playlist = new MusicPlaylist();
        playlist.addSong("song1");
        playlist.addSong("song2");
        playlist.addSong("song3");

        assertEquals("song1", playlist.playSong());
        assertEquals("song2", playlist.playSong());
        assertEquals("song3", playlist.playSong());
    }

    @Test
    @DisplayName("Testing the printHistory method and if it works")
    public void testPrintHistory() {
        MusicPlaylist playlist = new MusicPlaylist();
        playlist.addSong("song1");
        playlist.addSong("song2");
        playlist.addSong("song3");
        playlist.playSong();
        playlist.playSong();
        playlist.playSong();
        
        Stack<String> expectedHistory = new Stack<String>();
        expectedHistory.push("song1");
        expectedHistory.push("song2");
        expectedHistory.push("song3");

        assertEquals(expectedHistory, playlist.printHistory());
    }

    @Test
    @DisplayName("Testing the clearHistory method and if it works as intended")
    public void testClearHistory() {
        MusicPlaylist playlist = new MusicPlaylist();
        playlist.addSong("song1");
        playlist.addSong("song2");
        playlist.playSong();
        playlist.clearHistory();
        playlist.playSong();

        Stack<String> expectedHistory = new Stack<String>();
        expectedHistory.add("song2");

        assertEquals(expectedHistory, playlist.printHistory());
    }

    @Test
    @DisplayName("Testing if both parts of the deleteNumHistory")
    public void testDeleteNumHistory() {
        MusicPlaylist playlist = new MusicPlaylist();
        playlist.addSong("song1");
        playlist.addSong("song2");
        playlist.addSong("song3");
        playlist.playSong();
        playlist.playSong();
        playlist.playSong();

        // delete top 1 song from history
        playlist.deleteNumHistory(1);

        Stack<String> expectedHistory = new Stack<String>();
        expectedHistory.push("song1");
        expectedHistory.push("song2");

        assertEquals(expectedHistory, playlist.printHistory());

        // delete bottom 1 song from history
        playlist.deleteNumHistory(-1);

        expectedHistory = new Stack<String>();
        expectedHistory.push("song2");

        assertEquals(expectedHistory, playlist.printHistory());
    }

}