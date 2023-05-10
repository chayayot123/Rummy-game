import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameBoard {
    private Deck deck;
    private BasicPlayer humanPlayer;
    private DiscardPile discardPile;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean gameWon;

    public GameBoard(List<Player> players) {
        this.deck = new Deck();
        this.discardPile = new DiscardPile();
        this.players = players;
        this.currentPlayerIndex = 0;
        this.gameWon = false;
    
        // Shuffle the deck
        this.deck.shuffle();
    
        // Deal cards to each player
        for (Player player : this.players) {
            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < 13; i++) {
                Card card = this.deck.draw();
                hand.add(card);
            }
            player.receiveInitialHand(hand);
            if (player instanceof BasicPlayer) {
                this.humanPlayer = (BasicPlayer) player;
            }
        }
    
        // Place the first card on the discard pile
        Card firstCard = this.deck.draw();
        this.discardPile.addCard(firstCard);
    }

    public void playGame() {
        // Play the game until someone wins
        while (!this.gameWon) {
            Player currentPlayer = this.players.get(this.currentPlayerIndex);

            // Display the current state of the game
            displayGameState();

            // Ask the player to take their turn
            currentPlayer.takeTurn(this);

            // Check if the player has won
            if (currentPlayer.getHand().isEmpty()) {
                this.gameWon = true;
                displayGameState();
                System.out.println(currentPlayer.getName() + " wins!");
            }

            // Move on to the next player
            this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        }
    }

    public boolean isDeckEmpty() {
        return this.deck.isEmpty() && this.discardPile.isEmpty();
    }

    public Card drawCard() {
        if (this.deck.isEmpty()) {
            if (this.discardPile.isEmpty()) {
                // Both the deck and discard pile are empty, end the game
                this.gameWon = true;
                return null;
            } else {
                // Shuffle the discard pile and use it as the new deck
                List<Card> cards = this.discardPile.removeAllCards();
                if (cards != null && !cards.isEmpty()) {
                    this.deck.addCards(cards);
                }
                this.deck.shuffle();
            }
        }
        return this.deck.draw();
    }

    public void discardCard(Card card) {
        Player currentPlayer = this.players.get(this.currentPlayerIndex);
    
        if (currentPlayer == humanPlayer) {
            try (// Prompt the human player to select a card to discard
            Scanner scanner = new Scanner(System.in)) {
                System.out.println("Select a card to discard:");
                List<Card> hand = currentPlayer.getHand();
                for (int i = 0; i < hand.size(); i++) {
                    System.out.println(i + 1 + ". " + hand.get(i));
                }
                int selectedIndex = scanner.nextInt() - 1;
                Card cardToDiscard = hand.get(selectedIndex);
                currentPlayer.removeCardFromHand(cardToDiscard);
                this.discardPile.addCard(cardToDiscard);
            }
        } else {
            // AI player - choose a random card to discard
            List<Card> hand = currentPlayer.getHand();
            int index = (int) (Math.random() * hand.size());
            Card cardToDiscard = hand.get(index);
            currentPlayer.removeCardFromHand(cardToDiscard);
            this.discardPile.addCard(cardToDiscard);
        }
    }

    public Card getTopCardOnDiscardPile() {
        return this.discardPile.getTopCard();
    }

    public void addToDiscardPile(Card card) {
        this.discardPile.addCard(card);
    }

    public void displayGameState() {
        // Display the state of the game
        System.out.println("Top card on discard pile: " + this.getTopCardOnDiscardPile());

        for (Player player : this.players) {
            System.out.println(player.getName() + "'s hand: " + player.getHand());
        }
    }
}
