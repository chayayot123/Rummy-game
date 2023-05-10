import java.util.List;

public interface Player {
    void receiveCard(Card card);
    void takeTurn(GameBoard gameBoard);
    void receiveInitialHand(List<Card> cards);
    void removeCardFromHand(Card card);
    String getName();
    List<Card> getHand();
}
