import java.util.Scanner;

public class Tic {
    private static char[][] board = new char[3][3]; // 3x3 Tic-Tac-Toe board
    private static char currentPlayer = 'X'; // Starting player
    private static boolean gameWon = false;

    public static void main(String[] args) {
        initializeBoard();
        Scanner scanner = new Scanner(System.in);
        
        while (!gameWon) {
            displayBoard();
            System.out.println("Player " + currentPlayer + ", enter row and column (0, 1, or 2) to place your mark:");

            // Take user input for row and column
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // If the position is valid and unoccupied, make the move
            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                // Check if the current player has won
                gameWon = checkWinner();
                if (gameWon) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                } else {
                    // Switch to the other player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move, try again.");
            }

            // Check if the board is full (draw condition)
            if (isBoardFull() && !gameWon) {
                displayBoard();
                System.out.println("It's a draw!");
                break;
            }
        }

        scanner.close();
    }

    // Initializes the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Displays the current state of the board
    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Checks if the current move is valid
    private static boolean isValidMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            return true;
        }
        return false;
    }

    // Checks if there is a winner
    private static boolean checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    // Checks if the board is full (draw condition)
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}