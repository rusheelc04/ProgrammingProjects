// Rusheel Chande
// May 24 2023

// This program models a quiz using a binary tree, that has
// features for contructing the quiz, has user interaction, an exporting
// feature, and a feature for adding new questions.

import java.io.*;
import java.util.*;

public class QuizTree {
    public static class QuizTreeNode {
        public String data;
        public QuizTreeNode left;
        public QuizTreeNode right;
    }

    private QuizTreeNode root;

    // This method initializes a QuizTree object from an input source
    // Pre-conditions: Scanner object is not null
    // Post-conditions: A QuizTree object is constructed and initialized
    public QuizTree(Scanner inputFile) {
        this.root = constructTree(inputFile);
    }

    // This method creates a QuizTreeNode
    // Pre-conditions: Scanner object is not null
    // Post-conditions: Returns a QuizTreeNode instance
    private QuizTreeNode constructTree(Scanner inputFile) {
        String line = inputFile.nextLine();
        QuizTreeNode node = new QuizTreeNode();

        if (line.startsWith("END:")) {
            node.data = line.substring(4);
        } else {
            int slashIndex = line.indexOf('/');
            node.data = line;
            node.left = constructTree(inputFile);
            node.right = constructTree(inputFile);
        }
        return node;
    }

    // This method facilitates the interaction with the quiz based
    // on the current QuizTree
    // Pre-conditions: Scanner object is not null
    // Post-conditions: Quiz is taken, results are displayed to the user
    public void takeQuiz(Scanner console) {
        QuizTreeNode currentNode = this.root;
        while (currentNode.left != null && currentNode.right != null) {
            System.out.print("Do you prefer " + 
            currentNode.data.substring(0, currentNode.data.indexOf('/')) + 
            " or " + 
            currentNode.data.substring(currentNode.data.indexOf('/') + 1) +
            "? ");
            String userInput = console.nextLine().toLowerCase();

            if (userInput.equals(
                currentNode.data.substring(0, currentNode.data.indexOf('/')).toLowerCase())) {
                currentNode = currentNode.left;
            } else if (userInput.equals(
                       currentNode.data.substring(currentNode.data.indexOf('/') + 1).toLowerCase())) {
                currentNode = currentNode.right;
            } else {
                System.out.println("  Invalid response; try again.");
            }
        }
        System.out.println("Your result is: " + currentNode.data);
    }

    // This method exports the QuizTree into a specific output format
    // Pre-conditions: PrintStream object is not null
    // Post-conditions: Current QuizTree is exported to a given output stream
    public void export(PrintStream outputFile) {
        preOrderTraversal(this.root, outputFile);
    }

    // This method traverses the QuizTree for export operation
    // Pre-conditions: QuizTreeNode and PrintStream objects are not null
    // Post-conditions: Traversed QuizTree data is written into the output stream
    private void preOrderTraversal(QuizTreeNode node, PrintStream outputFile) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                outputFile.println("END:" + node.data);
            } else {
                outputFile.println(node.data);
                preOrderTraversal(node.left, outputFile);
                preOrderTraversal(node.right, outputFile);
            }
        }
    }

    // This method adds a new question to the QuizTree
    // Pre-conditions: The toReplace, leftChoice, rightChoice, leftResult, 
    //                 and rightResult strings are not null
    // Post-conditions: QuizTree is updated with the new question
    public void addQuestion(String toReplace, String leftChoice, String rightChoice,
                            String leftResult, String rightResult) {
        this.root = addQuestion(this.root, toReplace, leftChoice, rightChoice, 
                                leftResult, rightResult);
    }

    // This method is the helper method to add a new question to the QuizTree
    // Pre-conditions: QuizTreeNode and toReplace, leftChoice, rightChoice, 
    //                 leftResult, rightResult strings are not null
    // Post-conditions: Returns a QuizTreeNode with added question
    private QuizTreeNode addQuestion(QuizTreeNode node, String toReplace, String leftChoice,
                                     String rightChoice, String leftResult, String rightResult) {
        if (node == null) {
            return null;
        } else if (node.data.equalsIgnoreCase(toReplace)) {
            node.data = leftChoice + "/" + rightChoice;
            node.left = new QuizTreeNode();
            node.left.data = leftResult;
            node.right = new QuizTreeNode();
            node.right.data = rightResult;
        } else {
            node.left = addQuestion(node.left, toReplace, leftChoice, 
                                    rightChoice, leftResult, rightResult);
            node.right = addQuestion(node.right, toReplace, leftChoice, 
                                     rightChoice, leftResult, rightResult);
        }
        return node;
    }
}
