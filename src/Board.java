public class Board {
    private Player[][] playerBoard;
    private static int openSpots = 9;

    public Board() {
        this.playerBoard = new Player[3][3];
    }

    public Player[][] getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(Player[][] playerBoard) {
        this.playerBoard = playerBoard;
    }

    boolean isValidMove(Position position, Player currentPlayer) {
        int row = position.getRow();
        int col = position.getCol();

        // out of bounds check
        if (row >= 3 || row < 0 || col >= 3 || col < 0) {
            return false;
        }

        // spot is already occupied
        if (playerBoard[row][col] != null) {
            return false;
        }

        return true;
    }

    void updateBoard(Position position, Player currentPlayer) {
        int row = position.getRow();
        int col = position.getCol();

        playerBoard[row][col] = currentPlayer;
        openSpots--;
    }

    public String checkWinner() {
        // 1. Check Rows
        for (int i = 0; i < 3; i++) {
            if (playerBoard[i][0] != null && playerBoard[i][1] != null && playerBoard[i][2] != null &&
                    playerBoard[i][0].getSymbol() == playerBoard[i][1].getSymbol() &&
                    playerBoard[i][1].getSymbol() == playerBoard[i][2].getSymbol()) {

                playerBoard[i][0].setWins(playerBoard[i][0].getWins() + 1);
                return playerBoard[i][0].getName();
            }
        }

        // 2. Check Columns
        for (int i = 0; i < 3; i++) {
            if (playerBoard[0][i] != null && playerBoard[1][i] != null && playerBoard[2][i] != null &&
                    playerBoard[0][i].getSymbol() == playerBoard[1][i].getSymbol() &&
                    playerBoard[1][i].getSymbol() == playerBoard[2][i].getSymbol()) {

                playerBoard[0][i].setWins(playerBoard[0][i].getWins() + 1);
                return playerBoard[0][i].getName();
            }
        }

        // 3. Check Diagonal (Top-Left to Bottom-Right)
        if (playerBoard[0][0] != null && playerBoard[1][1] != null && playerBoard[2][2] != null &&
                playerBoard[0][0].getSymbol() == playerBoard[1][1].getSymbol() &&
                playerBoard[1][1].getSymbol() == playerBoard[2][2].getSymbol()) {

            playerBoard[0][0].setWins(playerBoard[0][0].getWins() + 1);
            return playerBoard[0][0].getName();
        }

        // 4. Check Anti-Diagonal (Top-Right to Bottom-Left)
        if (playerBoard[0][2] != null && playerBoard[1][1] != null && playerBoard[2][0] != null &&
                playerBoard[0][2].getSymbol() == playerBoard[1][1].getSymbol() &&
                playerBoard[1][1].getSymbol() == playerBoard[2][0].getSymbol()) {

            playerBoard[0][2].setWins(playerBoard[0][2].getWins() + 1);
            return playerBoard[0][2].getName();
        }

        // No winner found
        return "";
    }

    public boolean checkStalemate() {
        // check if there are still any unused spots
        if (openSpots == 0 && checkWinner().isEmpty()) {
            return true;
        }
        return false;
    }

    void resetBoard() {
        playerBoard = new Player[3][3];
        openSpots = 9;
    }
}