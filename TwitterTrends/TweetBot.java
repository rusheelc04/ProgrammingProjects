// Rusheel Chande
// Feb 23 2023

// This code simulates a Twitter bot by keeping track of a list of tweets, allowing
// the user to add, remove, and retrieve tweets, as well as reset the index for the next tweet.

import java.util.ArrayList;
import java.util.List;

public class TweetBot {
    // fields/instance variables
    private List<String> tweets;
    private int currentIndex;

    // constructor method
    // Constructs a new TweetBot object with the given list of tweets.
    // If the list is empty, throws an IllegalArgumentException.
    public TweetBot(List<String> tweets) {
        if (tweets.size() < 1) {
            throw new IllegalArgumentException("List of tweets must contain at least one tweet.");
        }
        this.tweets = new ArrayList<>(tweets);
        this.currentIndex = 0;
    }

    // Returns the number of tweets in the tweet list.
    public int numTweets() {
        return this.tweets.size();
    }

    // Adds the given tweet to the tweet list.
    public void addTweet(String tweet) {
        this.tweets.add(tweet);
    }
    
    // Returns the next tweet in the tweet list.
    // If the tweet list contains only one tweet, returns that tweet repeatedly.
    public String nextTweet() {
        if (this.tweets.size() == 1) {
            return this.tweets.get(0);
        }
        String tweet = this.tweets.get(this.currentIndex);
        this.currentIndex = (this.currentIndex + 1) % this.tweets.size();
        return tweet;
    }

    // Removes the given tweet from the tweet list.
    // If the tweet is not in the tweet list, does nothing.
    // If the removed tweet was at the current index or after, decrements currentIndex by 1.
    // If the removed tweet was the last tweet in the tweet list, sets currentIndex to 0.
    public void removeTweet(String tweet) {
        int index = this.tweets.indexOf(tweet);
        if (index >= 0) {
            this.tweets.remove(index);
            if (this.currentIndex > index) {
                this.currentIndex--;
            } else if (this.currentIndex == this.tweets.size()) {
                this.currentIndex = 0;
            }
        }
    }

    // Resets the TweetBot object's currentIndex to zero, causing the next call to nextTweet()
    // to return the first tweet in the tweet list.
    public void reset() {
        this.currentIndex = 0;
    }
}

