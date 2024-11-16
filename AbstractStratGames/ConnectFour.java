// Rusheel Chande
// April 12 2023

// This program implements the AbstractStrategyGame interface and mimics a
// two player Connect Four game. The point of the game is to connect four tokens
// either vertically, horizontally, or diagonally. The game ends in a tie if the
// board is filled without any player connecting four tokens

import java.util.*;

public class ConnectFour implements AbstractStrategyGame {
    private char[][] board;
    private boolean isXTurn;

    // Constructor
    // Pre-condition: None
    // Post-condition: An empty board 7 wide and 6 tall is created, and the first
    //                 player is set to X.
    public ConnectFour() {
        board = new char[][]{{'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'}};
        isXTurn = true;
    }

    // This method returns the instruction of the Connect Four game as a String
    // Pre-condition: None
    // Post-condition: A String with the instructions of the game is returned.
    public String instructions() {
        String result = "";
        result += "Player 1 is X and goes first. Choose column to drop token between 0-6 \n";
        result += "(0 is leftmost and 6 is the rightmost column.) The game ends when one player \n";
        result += "gets four tokens in a row either vertically, horizontally, or diagonally and \n";
        result += "that player wins. If the board fills up, the game ends in a tie. Player 1's tokens \n";
        result += "are marked by X and player 2's tokens are marked by O";
        return result;
    }

    // This method returns a String representation of the current state of the game 
    // Pre-condition: None
    // Post-condition: A String is returned that represents the current state of the game board
    public String toString() {
        String result = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    // This method identifies and returns the status of the game (over or not)
    // Pre-condition: None
    // Post-condition: Returns true if the game is over and false if it isn't
    public boolean isGameOver() {
        return getWinner() >= 0;
    }

    // This method determines the winner of the game
    // Pre-condition: None
    // Post-condition: Returns the winner of the game
    //                 -1 if the game is still active
    //                 0 if the game is a tie
    //                 1 if player 1 (X) wins
    //                 2 if player 2 (O) wins
    public int getWinner() {
        // check rows
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length - 3; j++) {
                if (board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && 
                    board[i][j] == board[i][j+3] && board[i][j] != '-') {
                    return board[i][j] == 'X' ? 1 : 2;
                }
            }
        }

        // check columns
        for (int j = 0; j < board[0].length; j++) {
            for (int i = 0; i < board.length - 3; i++) {
                if (board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && 
                    board[i][j] == board[i+3][j] && board[i][j] != '-') {
                    return board[i][j] == 'X' ? 1 : 2;
                }
            }
        }

        // check diagonal (top-left to bottom-right)
        for (int i = 0; i < board.length - 3; i++) {
            for (int j = 0; j < board[0].length - 3; j++) {
                if (board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && 
                    board[i][j] == board[i+3][j+3] && board[i][j] != '-') {
                    return board[i][j] == 'X' ? 1 : 2;
                }
            }
        }

        // check diagonal (bottom-left to top-right)
        for (int i = 3; i < board.length; i++) {
            for (int j = 0; j < board[0].length - 3; j++) {
                if (board[i][j] == board[i-1][j+1] && board[i][j] == board[i-2][j+2] && 
                    board[i][j] == board[i-3][j+3] && board[i][j] != '-') {
                    return board[i][j] == 'X' ? 1 : 2;
                }
            }
        }

        // check for tie
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    // unfilled space; game not over
                    return -1;
                }
            }
        }

        // it's a tie!
        return 0;
    }

    // This method returns the next player's turn returning a numerical
    // representation
    // Pre-condition: None
    // Post-condition: Returns 1 if player 1 (X) turn next or 2 if player 2 (O) next.
    public int getNextPlayer() {
        return isXTurn ? 1 : 2;
    }

    // This method takes a Scanner input and makes a move based on user input
    // Pre-condition: The game is active and Scanner isn't null
    // Post-condition: The token is placed and players turns are switched
    public void makeMove(Scanner input) {
        char currPlayer = isXTurn ? 'X' : 'O';

        System.out.print("Column? ");
        int col = input.nextInt();

        makeMove(col, currPlayer);
        isXTurn = !isXTurn;
    }

    // This helper method makes a move for the given player and column in the parameter and throws
    // an IllegalArgumentException if the column is not in valid range (0-6) or if the column is full
    // Pre-condition: The game is active, the given column is in valid (0-6) range and the column 
    //                isn't full
    // Post-condition: The player's token either X for player 1 or O for player 2 is placed in specified
    //                 column, and the state of the game is updated
    private void makeMove(int col, char player) {
        if(col < 0 || col >= board[0].length) {
            throw new IllegalArgumentException("Invalid column. Please enter a number between 0 and 6.");
        } else if (board[0][col] != '-') {
            throw new IllegalArgumentException("Column is already full");
        }

        int row = 0;
        while(row < board.length && board[row][col] == '-') {
            row++;
        }

        board[row - 1][col] = player;
    }
}
