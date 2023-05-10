import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        // Populate the deck with 52 cards
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void addCards(List<Card> newCards) {
        cards.addAll(newCards);
    }
}
