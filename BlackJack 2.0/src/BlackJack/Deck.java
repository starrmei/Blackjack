package BlackJack;

public class Deck {
	private Card Deck[] = new Card[52];
	private int numLeft = 52, i, j;
	
	public Deck() {
		for (i = 0; i < 52; i++) {
			Deck[i] = new Card();
		}
	}
	
	public Card deal() {
		i = (52 - numLeft);
		Deck[i].setRandom();
			for (j = 0; j < i; j++) {
				if ((Deck[i].getRank() == Deck[j].getRank() && Deck[i].getSuit() == Deck[j].getSuit()) || (Deck[i].getRank() == Deck[0].getRank() && Deck[i].getSuit() == Deck[0].getSuit())) {
					Deck[i].setRandom();
					j = 0;
				}
			}
		numLeft--;
		return Deck[i];
	}
	
	public int cardsLeft() { return numLeft; }
	
	public void shuffle() { numLeft = 52; }

	public String toString() {
		String cardDrawn;
		cardDrawn = Deck[i].toString() + "\n";
		return cardDrawn;
	}
}

