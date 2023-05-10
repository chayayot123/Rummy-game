import java.util.*;

public class BasicPlayer implements Player {
    private final String name;
    private final List<Card> hand;

    
    @Override
    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    @Override
    public List<Card> layDownMeld() {
        if (hand.size() < 3) {
            return new ArrayList<>();
        }
        List<Card> meld = new ArrayList<>();
        meld.add(hand.get(0));
        meld.add(hand.get(1));
        meld.add(hand.get(2));
        hand.removeAll(meld);
        return meld;
    }

    @Override
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
        Card cardToDiscard = selectCardToDiscard();
        removeCardFromHand(cardToDiscard);
        gameBoard.addToDiscardPile(cardToDiscard);
        System.out.println(getName() + " discarded a card: " + cardToDiscard);
    }

    @Override
    public void receiveInitialHand(List<Card> cards) {
        this.hand.addAll(cards);
    }
    
    private Card selectCardToDiscard() {
        // Choose a random card to discard
        int index = (int) (Math.random() * hand.size());
        return hand.get(index);
    }

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

}