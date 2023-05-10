import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create players
        BasicPlayer player1 = new BasicPlayer("Player1");
        AIPlayer player2 = new AIPlayer("Player2", null);

        // Add players to a list
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        // Create the game board with the players
        GameBoard gameBoard = new GameBoard(players);

        // Start the game
        gameBoard.playGame();
    }
}