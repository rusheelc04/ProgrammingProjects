// Rusheel Chande
// Feb 23 2023

// This program reads a list of tweets from a file, creates a TweetBot object to represent the list
// of tweets, and uses a TwitterTrends object to analyze the tweet data. It displays
// the most frequent word found in the tweets and also flags tweets containing COVID-related misinformation.

import java.io.*;
import java.util.*;

public class TwitterMain {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("tweets.txt")); // Make Scanner over tweet file
        List<String> tweets = new ArrayList<>();
        while (input.hasNextLine()) { // Add each tweet in file to List
            tweets.add(input.nextLine());
        }

        TweetBot bot = new TweetBot(tweets); // Create TweetBot object with list of tweets
        TwitterTrends trends = new TwitterTrends(bot); // Create TwitterTrends object

        // Call and display results from getMostFrequentWord and your creative method here
        System.out.println("Most frequent word: " + trends.getMostFrequentWord());

        // Call COVID misinformation flagger method
        System.out.println("Find COVID related tweets (nothing will be printed if there are none): ");
        trends.covidMisInfoFlagger();
    }
}
