/*
 * Meigan Starr
 * 3/7/17
 * Project 1 - Blackjack Driver (Single player)
 * 
 * Requirements
 * 	Write a driver program to play Blackjack with a single player
 * 
 * Design
 * 	Deck = new Deck
 * 	playerHand = new BlackjackHand
 * 	dealerHand = new BlackjackHand
 * 
 * input chips
 * input bet
 * chips = chips - bet
 * 	if bet > chips
 * 		output error
 * playerHand = deck.deal + deck.deal
 * dealerHand = deck.deal
 * 
 * output dealerHand + dealerHand.getScore
 * output playerHand.toString
 * 
 * Hit?
 * if yes
 * 	do
 * 		playerHand += playerHand + deck.deal
 * 		output playerhand.toString
 * 	while yes && score < 21
 * 
 * while dealerHand.getScore < 17
 * 	dealerHand.deal 
 *	output dealerHand
 * 
 * output results(playerHand.getScore, dealerHand.getScore)
 * output chips
 * 
 * Play again?
 * 
 * results(playerScore, dealerScore)
 * 	if playerScore == dealerScore
 * 	 	chips = chips + bet
 * 		output tie
 * 	if playerScore > 21 || playerScore < dealerScore && dealerScore <= 21
 * 		chips = chips - bet
 * 		output Bust
 * 	if playerScore == 21 || dealerScore > 21 || playerScore > dealerScore 
 * 		chips = chips + bet*2
 * 		output win 
 * 
 */
package BlackJack;

import javax.swing.JOptionPane;

public class blackJackDriver {
	public static Deck deck = new Deck();
	public static BlackJackHand playerHand = new BlackJackHand(), dealerHand = new BlackJackHand();
	public static int chips, playerScore = 0, ace, aceDealer[] = new int[4], acePlayer[] = new int[4];
	
	public static void main(String[] args) {
		String chipsInHand, chipsBet, handDealer, hand1, results, again = null;
		int bet, dealerScore = 0;
		boolean OK;
		
		//Input chips (in hand)
		do {
			OK = true;
		chipsInHand = JOptionPane.showInputDialog(null, "Enter number of chips in hand: ", "Chips?", 3);
			if (chipsInHand != null) {
				try { 
					chips = Integer.parseInt(chipsInHand);
					if (chips <= 0) {
						OK = false;
					}
				}
				catch (NumberFormatException e) {
					OK = false;
				}
				if (!OK) {
					JOptionPane.showConfirmDialog(null,"ERROR! Not valid entry, try again.","ERROR!", -1, 0);
				}
			}
			else {
				System.exit(0);
			}
		} while (!OK);
		chips = Integer.parseInt(chipsInHand);
	
		do {
			//Input bet
			do {
				OK = true;
			chipsBet = JOptionPane.showInputDialog(null, "Chips in hand: " + chips + "\nEnter number of chips to bet: ", "Bet?", 3);
				if (chipsBet != null) {
					try { 
						bet = Integer.parseInt(chipsBet);
						if (bet > chips || bet <= 0) {
							OK = false;
						}
					}
					catch (NumberFormatException e) {
						OK = false;
					}
					if (!OK) {
						JOptionPane.showConfirmDialog(null,"ERROR! Not valid entry, try again.","ERROR!", -1, 0);
					}
				}
				else {
					System.exit(0);
				}
			} while (!OK);
			bet = Integer.parseInt(chipsBet);
			
			//Deal initial cards
			//Dealer
			dealerHand.addCard(deck.deal());
			if (dealerHand.getScore(deck.deal().getRank()) == 11) {
				aceDealer[0] = -10;
			}
			else {
				aceDealer[0] = 0;
			}
			dealerScore = dealerHand.getScore(deck.deal().getRank());
			handDealer = "Dealer's hand:\n" + deck.toString(); 
			
			//Player
			playerHand.addCard(deck.deal());
			if (playerHand.getScore(deck.deal().getRank()) == 11) {
				acePlayer[0] = -10;
			}
			else {
				acePlayer[0] = 0;
			}
			playerScore += playerHand.getScore(deck.deal().getRank());
			hand1 = deck.toString();
			
			playerHand.addCard(deck.deal());
			if (playerHand.getScore(deck.deal().getRank()) == 11) {
				acePlayer[1] = -10;
			} 
			else {
				acePlayer[1] = 0;
			}
			playerScore += playerHand.getScore(deck.deal().getRank());
			hand1 = "Your hand:\n" + hand1 + deck.toString(); 
			
			//Output cards and choose hit
			takeHit(handDealer + dealerHand.toString() + "\nScore: " + dealerScore, hand1); 
			
			//Dealer's turn
			while (dealerScore < 17) {
				dealerHand.addCard(deck.deal());
				if (dealerHand.getScore(deck.deal().getRank()) == 11) {
					aceDealer[1] = -10;
				} 
				else {
					aceDealer[1] = 0;
				}
				dealerScore += dealerHand.getScore(deck.deal().getRank());
				if (dealerScore > 17) {
					dealerScore += aceDealer[0] + aceDealer[1];
				}
				handDealer += deck.toString();
				JOptionPane.showMessageDialog(null, "Your score: " + playerScore + "\n\n" + handDealer + dealerHand.toString() + "\nScore: " + dealerScore, "Dealer's turn", -1);
			}
			
			//Output results
			results = getResults(playerScore, dealerScore, bet);
			JOptionPane.showMessageDialog(null, "Dealer's score: " + dealerScore + "\nYour score: " + playerScore + "\n\n" + results + "\n\nChips: " + chips, "Results", -1);
			
			//Play again?
			if (chips > 0) {
				again = JOptionPane.showInputDialog(null, "Play again? (Y or N)", "Again?", 3);
				again = again.toUpperCase();
				while (again.charAt(0) != 'Y' && again.charAt(0) != 'N' || again == null) {
					JOptionPane.showConfirmDialog(null,"ERROR! Not valid entry, try again.","ERROR!", -1, 0);
					again = JOptionPane.showInputDialog(null, "Play again? (Y or N)", "Again?", 3);
					again = again.toUpperCase();
				}
				if (again.charAt(0) == 'N') {
					System.exit(0);
				}
			}
			//Reset hands and deck
			playerHand.resetHand();
			playerScore = 0;
			dealerHand.resetHand();
			dealerScore = 0;
			deck.shuffle();
		} while (again.charAt(0) == 'Y');
		
		System.exit(0);
	}

	public static void takeHit(String dealer, String player) {

		String hit, handDealer = dealer, hand1 = player;
		
		hit = JOptionPane.showInputDialog(null, handDealer + "\n\n" + hand1 + playerHand.toString() + "\nScore: " + playerScore + "\n\nHit?", "Hit? (Y or N)", 3);
		hit = hit.toUpperCase();
		do {
			while (hit.charAt(0) != 'Y' && hit.charAt(0) != 'N' || hit == null) {
				JOptionPane.showConfirmDialog(null,"ERROR! Not valid entry, try again.","ERROR!", -1, 0);
				hit = JOptionPane.showInputDialog(null, hand1 + playerHand.toString() + "\nScore: " + playerScore + "\n\nHit?", "Hit? (Y or N)", -1);
				hit = hit.toUpperCase();
			}
			if (hit.charAt(0) == 'Y') {
				playerHand.addCard(deck.deal());
				if (playerHand.getScore(deck.deal().getRank()) == 11) {
					acePlayer[2] = -10;
				}
				else {
					acePlayer[2] = 0;
				}
				playerScore += playerHand.getScore(deck.deal().getRank());
				if (playerScore > 21) {
					playerScore += acePlayer[0] + acePlayer[1] + acePlayer[2];
				}
				hand1 += deck.toString();
				hit = JOptionPane.showInputDialog(null, hand1 + playerHand.toString() + "\nScore: " + playerScore + "\n\nHit?", "Hit? (Y or N)", -1);
				hit = hit.toUpperCase();
			} 
		} while (hit.charAt(0) == 'Y' && playerScore < 21);
	}
	
	public static String getResults(int playerScore, int dealerScore, int bet) {
		if (playerScore == dealerScore) {
			return "Tie!";
		}
		else if (playerScore > 21 || playerScore < dealerScore && dealerScore <= 21) {
			chips = chips - bet;
			return "Bust!";
		}
		else if (playerScore == 21 || dealerScore > 21 || playerScore > dealerScore) {
			chips = chips + bet;
			return "Win!";
		}
		else {
			return null;
		}
	}

}
