import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AIPlayer implements Player {
    private String name;
    private List<Card> hand;
    private ScoreCalculator scoreCalculator;

    @Override
    public void removeCardFromHand(Card card) {
        hand.remove(card);
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

    @Override
    public void receiveInitialHand(List<Card> cards) {
        this.hand = new ArrayList<>(cards);
    }

    @Override
    public List<Card> layDownMeld() {
        List<Card> meld = selectCardsToDeclareRummy();
        hand.removeAll(meld);
        return meld;
    }

    public static class BasicScoreCalculator implements ScoreCalculator {
        @Override
        public int calculateScore(List<Card> hand) {
            int score = 0;
            for (Card card : hand) {
                score += card.getRank().getValue();
            }
            return score;
        }
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

    public Card selectCardToDiscard(Card topCard) {
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
    
    public List<Card> selectCardsToDeclareRummy() {
        List<Card> rummyCards = new ArrayList<>();
        // Sort the hand by rank
        Collections.sort(hand, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.getRank().getValue() - c2.getRank().getValue();
            }
        });
        // Look for runs
        int runCount = 0;
        Card previousCard = null;
        for (Card card : hand) {
            if (previousCard != null && card.getRank().getValue() == previousCard.getRank().getValue() + 1 && card.getSuit() == previousCard.getSuit()) {
                // Found a card that continues a run
                runCount++;
            } else {
                // Found a card that doesn't continue a run
                if (runCount >= 2) {
                    // We have a run of at least 3 cards, add them to the list of rummy cards
                    for (int i = 0; i < runCount + 1; i++) {
                        rummyCards.add(hand.get(hand.indexOf(previousCard) + i - runCount));
                    }
                }
                runCount = 0;
            }
            previousCard = card;
        }
        // Look for sets
        Set<Card.Rank> ranks = new HashSet<>();
        for (Card card : hand) {
            if (!ranks.add(card.getRank())) {
                // Found a card with a rank that's already in the set, add it to the list of rummy cards
                rummyCards.add(card);
            }
        }
        return rummyCards;
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

}
