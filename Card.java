public class Card {
    // Enum for the suits of the cards
    public enum Suit {
        CLUBS('C'),
        DIAMONDS('D'),
        HEARTS('H'),
        SPADES('S');

        private final char symbol;

        Suit(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
    }

    // Enum for the ranks of the cards
    public enum Rank {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        String rankDisplay = (rank == Rank.JACK || rank == Rank.QUEEN || rank == Rank.KING) ? rank.toString().substring(0, 1) : Integer.toString(rank.getValue());
        return rankDisplay + suit.getSymbol();
    }
}
