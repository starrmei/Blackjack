/*
 * Meigan Starr
 * 3/7/16
 * Project 1 - BlackjackHand Class
 * 
 * Requirements
 * Design a class to play Blackjack following the UML
 * 
 * Design
 * Card[] hand = new Deck[11]
 * int numCards
 * 
 * BlackjackHand()
 * 	Card[0] = Card()
 * 	numCards = 0
 * 
 * getScore
 * 	score = hand[i].getRank
 * 	return score
 * 
 * addCard(Card c)
 * 	hand.deal
 * 
 * resetHand
 * 	numcards = 0
 * 	Card[] hand = new Deck[11]
 * 
 * toString
 * 	return "Cards in hand: " + numCards + hand.toString 
 * 
 */

package BlackJack;

public class BlackJackHand {
	private Card[] hand = new Card[11];
	private int numCards;
	private int score = 0;
	private String cardsInHand = "";
	
	public BlackJackHand() {
		for (int i = 0; i < 11; i ++) {
			hand[i] = new Card();
		}
		numCards = 0;
		score = 0;
	}
	
	public int getScore(int rank) {
		/*for (int i = 0; i < numCards; i++) {
			if (hand[i].getRank() == 11 || hand[i].getRank() == 12 || hand[i].getRank() == 13) {
				score += 10;
			}
			if (score >= 22 && hand[i].getRank() == 1) {
				score += 1 - 11;
			} 
			else {
				score += hand[i].getRank();
			} 
		} */
			
			if (rank != 11 && rank != 12 && rank != 13 && rank != 1) {
				score = rank;
			}
			else if (rank == 11 || rank == 12 || rank == 13) {
				score = 10;
			}
			else if (rank == 1) {
				score = 11;
			}
		return score;
	}
	
	public void addCard(Card c) {
		numCards++;
	}
	
	public void resetHand() {
		for (int i = 0; i < numCards; i++) {
			hand[i] = new Card();
		}
		numCards = 0;
		score = 0;
	}
	
	public String toString() {
		return "\nCards in hand: " + numCards + cardsInHand/* + "\nScore: " + score*/;
	}
}
