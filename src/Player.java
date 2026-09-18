public class Player {
    private String name;
    private char symbol;
    private int wins;

    Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}