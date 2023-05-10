import java.util.List;

public class AIPlayer implements Player {
    private String name;
    private List<Card> hand;
    private ScoreCalculator scoreCalculator;

    @Override
    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public AIPlayer(String name, ScoreCalculator scoreCalculator) {
        this.name = name;
        this.scoreCalculator = scoreCalculator;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public List<Card> getHand() {
        return hand;
    }

    public Card selectCardToDiscard() {
        // TODO: AI logic for selecting a card to discard
        return null;
    }

    public List<Card> selectCardsToDeclareRummy() {
        // TODO: AI logic for selecting cards to declare Rummy
        return null;
    }

    public int calculateScore() {
        return scoreCalculator.calculateScore(hand);
    }

    public String getName() {
        return name;
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    @Override
    public void takeTurn(GameBoard gameBoard) {
        // TODO: Implement AI player logic for taking a turn
    }

    @Override
    public void receiveInitialHand(List<Card> cards) {
        // TODO
    }
}
