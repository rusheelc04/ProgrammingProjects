// Rusheel Chande
// Feb 23 2023

// This program calculates Twitter trends and checks for a possibility in 
// COVID misinformation in tweets.

import java.util.*;

public class TwitterTrends {
    // fields/instance variables
    private TweetBot tweetBot;

    // constructor method
    public TwitterTrends(TweetBot bot) {
        tweetBot = bot;
    }

    // Returns the most frequent word in the tweets retrieved by the TweetBot object
    // assigned to this TwitterTrends object.
    public String getMostFrequentWord() {
        Map<String, Integer> frequencyMap = new HashMap<>();

        tweetBot.reset();
        int numTweets = tweetBot.numTweets();
        for (int i = 0; i < numTweets; i++) {
            String tweet = tweetBot.nextTweet();
            Scanner scanner = new Scanner(tweet.toLowerCase());
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (frequencyMap.containsKey(word)) {
                    frequencyMap.put(word, frequencyMap.get(word) + 1);
                } else {
                    frequencyMap.put(word, 1);
                }
            }
        }

        String mostFrequent = "";
        int maxCount = 0;
        for (String word : frequencyMap.keySet()) {
            int count = frequencyMap.get(word);
            if (count > maxCount) {
                maxCount = count;
                mostFrequent = word;
            }
        }

        return mostFrequent;
    }

    // Checks each tweet retrieved by the TweetBot object assigned to this TwitterTrends
    // object for mention of COVID-related topics. If a tweet contains such topics,
    // prints a warning message.
    public void covidMisInfoFlagger() {
        String warning = "This tweet has potential misinformation on the COVID pandemic, " +
                         "visit https://www.cdc.gov/coronavirus/2019-ncov/index.html for " +
                         "the most accurate and up-to-date information";
        tweetBot.reset();
        for (int i = 0; i < tweetBot.numTweets(); i++) {
            String tweet = tweetBot.nextTweet();
            if (tweet.toLowerCase().contains("covid") || tweet.toLowerCase().contains("pandemic")
                || tweet.toLowerCase().contains("coronavirus")) {
                System.out.println("COVID related tweet found: " + tweet);
                System.out.println(warning);
            }
        }
    }
}
