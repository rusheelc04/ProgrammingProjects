// Rusheel Chande
// Feb 16 2023

// This program aims to mimic a game similar to that of Wordle

import java.util.*;
import java.io.*;

public class Absurdle  {
    public static final String GREEN = "🟩";
    public static final String YELLOW = "🟨";
    public static final String GRAY = "⬜";

    // [[ ALL OF MAIN PROVIDED ]]
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the game of Absurdle.");

        System.out.print("What dictionary would you like to use? ");
        String dictName = console.next();

        System.out.print("What length word would you like to guess? ");
        int wordLength = console.nextInt();

        List<String> contents = loadFile(new Scanner(new File(dictName)));
        Set<String> words = pruneDictionary(contents, wordLength);

        List<String> guessedPatterns = new ArrayList<>();
        while (!isFinished(guessedPatterns)) {
            System.out.print("> ");
            String guess = console.next();
            String pattern = record(guess, words, wordLength);
            guessedPatterns.add(pattern);
            System.out.println(": " + pattern);
            System.out.println();
        }
        System.out.println("Absurdle " + guessedPatterns.size() + "/∞");
        System.out.println();
        printPatterns(guessedPatterns);
    }

    // [[ PROVIDED ]]
    // Prints out the given list of patterns.
    // - List<String> patterns: list of patterns from the game
    public static void printPatterns(List<String> patterns) {
        for (String pattern : patterns) {
            System.out.println(pattern);
        }
    }

    // [[ PROVIDED ]]
    // Returns true if the game is finished, meaning the user guessed the word. Returns
    // false otherwise.
    // - List<String> patterns: list of patterns from the game
    public static boolean isFinished(List<String> patterns) {
        if (patterns.isEmpty()) {
            return false;
        }
        String lastPattern = patterns.get(patterns.size() - 1);
        return !lastPattern.contains("⬜") && !lastPattern.contains("🟨");
    }

    // [[ PROVIDED ]]
    // Loads the contents of a given file Scanner into a List<String> and returns it.
    // - Scanner dictScan: contains file contents
    public static List<String> loadFile(Scanner dictScan) {
        List<String> contents = new ArrayList<>();
        while (dictScan.hasNext()) {
            contents.add(dictScan.next());
        }
        return contents;
    }

    // This method takes a List of dictionary contents and an Integer word length entered by
    // the user and goes through the contents and returns a set of words whose length is equal
    // to the word length given. If the length of the word is less than one, an IllegalArgumentException
    // is thrown.
    public static Set<String> pruneDictionary(List<String> contents, int wordLength) {
        if (wordLength < 1) {
            throw new IllegalArgumentException("word length must be greater than 1");
        }

        Set<String> filteredWords = new HashSet<String>();

        for (String word : contents) {
            if (word.length() == wordLength) {
                filteredWords.add(word);
            }
        }
        return filteredWords;
    }

    // This method takes in a user's guess as a String and a Set of String words, and an int
    // for desired word length. It return the best pattern of letters for the guess based on the
    // set of words. The methods also throws an IllegalArgumentException if the Set of words are 
    // empty or if the length of the guess doesn't match the desired word length. The contents of
    // the parameter words will be altered by this method 
    public static String record(String guess, Set<String> words, int wordLength) {
        if (words.isEmpty()) {
            throw new IllegalArgumentException("set of words cannot be empty");
        } else if (guess.length() != wordLength) {
            throw new IllegalArgumentException("the length of your guess is incorrect");
        }

        Map<String, Set<String>> patterns = new TreeMap<>();

        for (String word : words) {
            String pattern = patternFor(word, guess);
            if (!patterns.containsKey(pattern)) {
                patterns.put(pattern, new HashSet<>());
            }
            patterns.get(pattern).add(word);
        }

        String bestPattern = "";
        int bestCount = 0;
        for (Map.Entry<String, Set<String>> entry : patterns.entrySet()) {
            int count = entry.getValue().size();
            if (count > bestCount) {
                bestPattern = entry.getKey();
                bestCount = count;
            }
        }

        words.clear();
        words.addAll(patterns.get(bestPattern));
        return bestPattern;
    }

    // This method takes two Strings, one word and a guess entered by the user and the method
    // returns the Wordle pattern for the guess compared against the given word
    public static String patternFor(String word, String guess) {
        // Code all exact matches with !

        // initialize Strings to manipulate
        String codedWord = "";
        String codedGuess = "";

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(i)) {
                codedWord += "!";
                codedGuess += "!";
            } else {
                codedWord += word.charAt(i);
                codedGuess += guess.charAt(i);
            }
        }

        // Code all approximate matches with $
        for (int i = 0; i < word.length(); i++) {
            if (codedGuess.charAt(i) != '!') {
                char placeHolder = guess.charAt(i);
                int index = codedWord.indexOf(placeHolder);

                if (index != -1 && codedWord.charAt(index) != '!') {
                    codedGuess = codedGuess.substring(0, i) + "$" + codedGuess.substring(i + 1);
                    codedWord = codedWord.substring(0, index) + "$" + codedWord.substring(index + 1);
                }

            }
        }

        // Edit all non-matches with %
        // add '%'s to codedWord
        for (int i = 0; i < codedWord.length(); i++) {
            if (codedWord.charAt(i) != '!' && codedWord.charAt(i) != '$') {
                codedWord = codedWord.substring(0, i) + "%" + codedWord.substring(i + 1);
            }
        }
        // add '%'s to codedGuess
        for (int i = 0; i < codedGuess.length(); i++) {
            if (codedGuess.charAt(i) != '!' && codedGuess.charAt(i) != '$') {
                codedGuess = codedGuess.substring(0, i) + "%" + codedGuess.substring(i + 1);
            }
        }

        // Replace markers with colors
        String pattern = codedGuess.replace("!", GREEN).replace("$", YELLOW).replace("%", GRAY);
        return pattern;
    }
}
