package BlackJack;

public class Card {
	private int rank, suit;
	
	public Card() {
		rank = 0;
		suit = 0;
	}
	
	public Card(int newRank, int newSuit) {
		rank = newRank;
		suit = newSuit;
	}
	
	public int getRank() { return rank; }
	public int getSuit() { return suit; }
	
	public void setRandom() {
		rank = (int) (Math.random() * 13 + 1);
		suit = (int) (Math.random() * 4 + 1);
	} 
	
	public int compare(Card card1) {
		return card1.rank;
	}
	
	public static int compare(Card card1, Card card2) {
		 if (card1.rank > card2.rank) {
			 return 1;
		 }
		 else if (card1.rank < card2.rank) {
			 return -1;
		 }
		 else {
			 return 0;
		 }
	}
	
	public String toString() {
		String s, r;
		
		//Find Suit
		s = Integer.toString(suit);
		if (suit == 1) {
			s = "Clubs";
		}
		else if (suit == 2) {
			s = "Hearts";
		}
		else if (suit == 3) {
			s = "Diamonds";
		}
		else if (suit == 4) {
			s = "Spades";
		}
		else {
			s = null;
		}
		
		//Find Rank
		r = Integer.toString(rank);
		if (rank >= 2 && rank <= 10) {
			r = Integer.toString(rank);
		}
		else if (rank == 1) {
			r = "Ace";
		}
		else if (rank == 11) {
			r = "Jack";
		}
		else if (rank == 12) {
			r = "Queen";
		}
		else if (rank == 13) {
			r = "King";
		}
		else {
			r = null;
		}
		return r + " of " + s;
	}
}
