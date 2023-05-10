import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        // Draw a card
        Card drawnCard = gameBoard.drawCard();
        receiveCard(drawnCard);
        System.out.println(getName() + " drew a card: " + drawnCard);

        // Discard a card
        Card cardToDiscard = selectCardToDiscard(gameBoard.getTopCardOnDiscardPile());
        removeCardFromHand(cardToDiscard);
        gameBoard.addToDiscardPile(cardToDiscard);
        System.out.println(getName() + " discarded a card: " + cardToDiscard);
    }

    private Card selectCardToDiscard(Card topCard) {
        // Choose the first card that's not the same suit as the top card on the discard pile
        for (Card card : hand) {
            if (card.getSuit() != topCard.getSuit()) {
                return card;
            }
        }
        // If all cards are the same suit, choose a random card to discard
        int index = (int) (Math.random() * hand.size());
        return hand.get(index);
    }

    @Override
    public void receiveInitialHand(List<Card> cards) {
        this.hand = new ArrayList<>(cards);
    }
}
