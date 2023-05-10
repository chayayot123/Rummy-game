import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class DiscardPile extends Observable {
    private List<Card> pile;

    public DiscardPile() {
        pile = new ArrayList<>();
    }

    public void addCard(Card card) {
        pile.add(card);
        setChanged();
        notifyObservers(card);
    }

    public Card getTopCard() {
        if (pile.isEmpty()) {
            return null;
        }
        return pile.get(pile.size() - 1);
    }

    public List<Card> removeAllCards() {
        pile.clear();
        return null;
    }

    public boolean isEmpty() {
        return false;
    }
}
