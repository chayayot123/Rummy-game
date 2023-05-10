import java.util.*;

public class BasicPlayer implements Player {
    private final String name;
    private final List<Card> hand;

    public BasicPlayer(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    @Override
    public void receiveCard(Card card) {
        hand.add(card);
    }

    @Override
    public void takeTurn(GameBoard gameBoard) {
        // TODO: Implement the logic for the player's turn
    }

    @Override
    public void receiveInitialHand(List<Card> cards) {
        // TODO
    }
}
