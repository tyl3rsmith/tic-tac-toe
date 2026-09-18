import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    // Shared class field scanner prevents stream corruption and resource bugs
    private final Scanner scanner = new Scanner(System.in);

    void displayWelcome() {
        System.out.println("Welcome to Tic-Tac-Toe");
    }

    Map<Integer, String[]> getPlayerInfo() {
        Map<Integer, String[]> playerInfo = new HashMap<>();

        System.out.println("Player 1 please enter your name");
        String playerOneName = scanner.nextLine();

        char playerOneSymbol = ' ';
        while (playerOneSymbol != 'O' && playerOneSymbol != 'X') {
            System.out.println(playerOneName + " please enter your symbol (either O or X)");
            playerOneSymbol = scanner.next().toUpperCase().charAt(0);
        }

        scanner.nextLine();

        System.out.println("Player 2 please enter your name");
        String playerTwoName = scanner.nextLine();

        char playerTwoSymbol = (playerOneSymbol == 'O') ? 'X' : 'O';
        System.out.println(playerTwoName + " you have the symbol " + playerTwoSymbol);

        playerInfo.put(1, new String[]{playerOneName, String.valueOf(playerOneSymbol)});
        playerInfo.put(2, new String[]{playerTwoName, String.valueOf(playerTwoSymbol)});

        return playerInfo;
    }

    public void displayBoard(Player[][] playerBoard) {
        System.out.println("    0   1   2");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + " | ");
            for (int col = 0; col < 3; col++) {
                if (playerBoard[row][col] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(playerBoard[row][col].getSymbol());
                }
                if (col < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println(" |");
            if (row < 2) {
                System.out.println("  ----+---+----");
            }
        }
    }

    Position promptMove(Player currentPlayer, Board board) {
        Position position = null;
        boolean isValid = false;

        while (!isValid) {
            System.out.println(currentPlayer.getName() + ": enter row and col numbers separated by a space (e.g., 0 2):");

            int row = scanner.nextInt();
            int col = scanner.nextInt();
            scanner.nextLine();

            position = new Position(row, col);

            if (board.isValidMove(position, currentPlayer)) {
                isValid = true;
            } else {
                System.out.println("That move is invalid or the space is already taken! Try again.");
            }
        }

        return position;
    }

    void displayWinner(String name) {
        System.out.println("Yay congrats " + name + " you won!");
    }

    void displayWins(Player player1, Player player2) {
        if (player1.getWins() == 1) {
            System.out.println(player1.getName() + ": you have won 1 time!");
        } else {
            System.out.println(player1.getName() + ": you have won " + player1.getWins() + " times!");
        }

        if (player2.getWins() == 1) {
            System.out.println(player2.getName() + ": you have won 1 time!");
        } else {
            System.out.println(player2.getName() + ": you have won " + player2.getWins() + " times!");
        }
    }

    void displayStalemate() {
        System.out.println("Stalemate! Nobody wins :(");
    }

    boolean playAgain() {
        System.out.println("Would you like to play again (y/n) ?");
        char choice = scanner.next().toLowerCase().charAt(0);
        scanner.nextLine();

        return choice == 'y';
    }

    void displayGoodbye() {
        System.out.println("Thanks for playing have a nice day!");
    }
}