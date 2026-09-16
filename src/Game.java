import java.util.Map;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private final static Board board = new Board();
    private Player currentPlayer;
    private final static Controller controller = new Controller();

    public Game() {
        this.playerOne = null;
        this.playerTwo = null;
        this.currentPlayer = null;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    void play() {
        while (true) {
            changeTurns();

            Position position = controller.promptMove(currentPlayer, board);
            board.updateBoard(position, currentPlayer);
            controller.displayBoard(board.getPlayerBoard());

            String winner = board.checkWinner();

            if (!winner.isEmpty()) {
                controller.displayWinner(winner);
                break;
            }

            if (board.checkStalemate()) {
                controller.displayStalemate();
                break;
            }
        }
    }

    void startGame() {
        controller.displayWelcome();
        initializePlayers();

        while (true) {
            board.resetBoard();
            currentPlayer = playerOne;

            controller.displayBoard(board.getPlayerBoard());

            Position position = controller.promptMove(currentPlayer, board);
            board.updateBoard(position, currentPlayer);
            controller.displayBoard(board.getPlayerBoard());

            play();

            if (!controller.playAgain()) {
                controller.displayGoodbye();
                break;
            }
        }
    }

    void changeTurns() {
        if (currentPlayer.equals(playerOne)) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    void initializePlayers() {
        Map<Integer, String[]> playerInfo = controller.getPlayerInfo();

        playerOne = new Player(playerInfo.get(1)[0], playerInfo.get(1)[1].charAt(0));
        playerTwo = new Player(playerInfo.get(2)[0], playerInfo.get(2)[1].charAt(0));

        // player 1 automatically goes first
        this.currentPlayer = playerOne;
    }
}