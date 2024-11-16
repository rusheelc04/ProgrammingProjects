// Rusheel Chande
// Jan 19 2023

// This program mimics a TODO list where users can add and mark thier
// TODO lists. Thay can also load in their list from a file and save
// their list to a file.

import java.util.*;
import java.io.*;

public class TodoListManager {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to your TODO List Manager!");
        Scanner console = new Scanner(System.in);
        // Initialize ArrayList
        List<String> todos = new ArrayList<>();

        String userChoice = "";

        while (!userChoice.equalsIgnoreCase("Q")) {
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (P)rioritize Tasks, (C)lear List, (Q)uit? ");

            userChoice = console.nextLine();
            if (userChoice.equalsIgnoreCase("A")) {
                addItem(console, todos);
                printTodos(todos);
            } else if (userChoice.equalsIgnoreCase("M")) {
                markItemAsDone(console, todos);
                printTodos(todos);
            } else if (userChoice.equalsIgnoreCase("L")) {
                loadItems(console, todos);
                printTodos(todos);
            } else if (userChoice.equalsIgnoreCase("S")) {
                saveItems(console, todos);
                printTodos(todos);
            } else if (userChoice.equalsIgnoreCase("Q")) {
                break;
            } else if (userChoice.equalsIgnoreCase("P")) {
                moveDownList(console, todos);
            } else if (userChoice.equalsIgnoreCase("C")) {
                clearList(console, todos);
            } else {
                System.out.println("Unknown input: " + userChoice);
                printTodos(todos);
            }
        }
    }

// This method prints out all the TODO items written in the todo ArrayList
// using a for loop embedden in an if conditional statement to recognize if
// the todo list is empty
    public static void printTodos(List<String> todos) {
        System.out.println("Today's TODOs:");
        if (todos.isEmpty()) {
            System.out.println("  You have nothing to do yet today! Relax!");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                System.out.print("  ");
                System.out.println(i + 1 + ": " + todos.get(i));
            }
        }
    }

// This method prints out all the TODO items written in the todo ArrayList
// using a for loop embedden in an if conditional statement to recognize if
// the todo list is empty
    public static void printList(List<String> todos) {
        if (todos.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                System.out.print("  ");
                System.out.println(i + 1 + ": " + todos.get(i));
            }
        }
    }

// This method adds items to the todos ArrayList and uses the number inputted by the user
// to decide where in the ArrayLists index to insert the String
    public static void addItem(Scanner console, List<String> todos) {
        System.out.print("What would you like to add? ");
        String addedItem = console.nextLine();

        if (todos.isEmpty()) {
            todos.add(addedItem);
        } else {
            System.out.print("Where in the list should it be (1-" + (todos.size() + 1) + ")? (Enter for end): ");
            String strNum = console.nextLine();

            if (strNum.equals("")) {
                todos.add(addedItem);
            } else {
                int location = Integer.parseInt(strNum) - 1;
                todos.add(location, addedItem);
            }
        }
    }

// This method removes Todo list items by scanning users input of an integer and
// removing whatever is on that integer's index (-1) on the todos ArrayList.
    public static void markItemAsDone(Scanner console, List<String> todos) {
        if (todos.isEmpty()) {
            System.out.println("All done! Nothing left to mark as done!");
        } else {
            System.out.print("Which item did you complete (1-" + todos.size() + ")? ");
            String completedStr = console.nextLine();
            int completedInt = Integer.parseInt(completedStr) - 1;
            todos.remove(completedInt);
        }
    }

// This method loads in a file and replaces the existing todo ArrayList with whatever
// is in said file
    public static void loadItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        System.out.print("File name? ");
        String fileName = console.nextLine();
        todos.clear();
        Scanner fileScan = new Scanner(new File(fileName));
        while (fileScan.hasNextLine()) {
            String nextTodo = fileScan.nextLine();
            todos.add(nextTodo);
        }
    }

// This method saves whatever is in the todos ArrayList into a file specified by the
// user. The for loop then prints all the indexes of the ArrayList into the file.
    public static void saveItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        System.out.print("File name? ");
        String outFile = console.nextLine();

        PrintStream output = new PrintStream(new File(outFile));
        for (int i = 0; i < todos.size(); i++) {
            output.println(todos.get(i));
        }
    }

// Prompts the user using the given Scanner for which task they would like to move down in 
// the given list, then moves that element down and prints out the updated list to the console. 
    public static void moveDownList(Scanner console, List<String> todos) {
        if (todos.isEmpty()) {
            System.out.println("Nothing in list! Add some tasks first!");
        } else {
            System.out.println("This is your current TODO list:");
            printList(todos);
            System.out.print("Which task in the list would you like to move down? ");
            String s = console.nextLine();
            int target = Integer.parseInt(s);
            moveDown(todos, target);
            System.out.println("Your new TODO list:");
            printList(todos);
        }
    }

// Moves the task at index target down by one in the given list if target is valid. 
// If target is negative or beyond the penultimate index of the list, prints an error message. 
    public static void moveDown(List<String> todos, int target) {
        if (target >= 0 && target < todos.size() - 1) {
            String element = todos.remove(target);
            todos.add(target + 1, element);
        } else {
            System.out.println("Your TODO list isn't long enough to perform that operation!");
        }
    }

// This method clears the current todos List. If there is nothing in the list, the user is let known 
// of that situation. A warning is also given to the user and if the user wishes to proceed, the todos List
// is cleared.
    public static void clearList(Scanner console, List<String> todos) {
        if (todos.isEmpty()) {
            System.out.println("The list is already empty!");
        } else {
            System.out.println("Are you sure you want to clear your current TODO list, you won't be able to recover lost information? (Y or N)");
            String userResponse = console.nextLine();
            if (userResponse.equalsIgnoreCase("Y")) {
                todos.clear();
                System.out.println("The list has been cleared!");
            } else {
                System.out.println("Returning to main menu");
            }
        }
    }
}