package cs445proj1;


public class BlackJack {

	//static variables
	public static int playerWins = 0;
	public static int dealerWins = 0;
	public static int ties = 0;
	public static boolean playerBust = false;
	
	public static void main(String args[]) {
		
		//Main statement
		System.out.println("Starting BlackJack with " + args[0] + " rounds and " + args[1] + " decks in the shoe.\n");
		
		//the logical size of the shoe at start
		int intendedShoeSize = Integer.parseInt(args[1]) * 52;
		
		//our queues declared for 4 enviornments
		MyQ<Card> shoe = new RandIndexQueue<Card>(4);
		MyQ<Card> discardPile = new RandIndexQueue<Card>(4);
		MyQ<Card> playerHand = new RandIndexQueue<Card>(21);
		MyQ<Card> dealerHand = new RandIndexQueue<Card>(21);
		
		//add every card to the shoe and it only took 8 lines :)
		for(int shoeAmount = 0; shoeAmount < Integer.parseInt(args[1]); shoeAmount++) {
			for (Card.Suits s: Card.Suits.values()) {
				for (Card.Ranks r: Card.Ranks.values()) {
					Card c1 = new Card(s, r);
					shoe.offer(c1);
				}
			}
		}
		
		//shuffle the deck
		((RandIndexQueue<Card>) shoe).shuffle();
		
		//for loop for each round
		for(int rounds = 0; rounds < Integer.parseInt(args[0]); rounds++) {
			
				System.out.println("Round " + (rounds + 1) + " beginning.");
				
				//the initial deal
				for(int initialDeal = 0; initialDeal < 2; initialDeal++) {
					playerHand.offer(shoe.poll());
					//noMoreCards(shoe,discardPile, rounds);
					dealerHand.offer(shoe.poll());
					//noMoreCards(shoe,discardPile, rounds);
				}
				
				//shoe each players hand
				printHand(playerHand, "Player");
				printHand(dealerHand, "Dealer");
				
				//check for a black jack
				if(checkValues(playerHand) == 21  || checkValues(dealerHand) == 21) {
					blackJack(playerHand, "Player");
					blackJack(dealerHand, "Dealer");
				} else {
				
					//hit until bust or stand
					hit(playerHand, shoe, discardPile, "Player", (rounds + 2));
					if(playerBust == false) {
						hit(dealerHand, shoe, discardPile, "Dealer", (rounds + 2));
					}
				}
				
				//show results
				checkResults(playerHand, dealerHand);
				
				//clear hand
				clearHand(playerHand, discardPile);
				clearHand(dealerHand, discardPile);
				
				//shuffle again just to make sure
				((RandIndexQueue<Card>) shoe).shuffle();
				
				//reset playerBust
				playerBust = false;
				
				System.out.println("");
				
				//shuffles back cards from discard pile into regular pile
				noMoreCards(shoe, discardPile, (rounds + 2), intendedShoeSize);
				
		}
		
		//Print final display message
		System.out.println("After " + args[0] + " rounds, here are the results:\n"
				+ "\tDealer Wins: " + dealerWins + "\n\t" + "Player Wins: " + playerWins + "\n\t"
						+ "Draws: " + ties);
				
	}
	
	private static int checkValues(MyQ hand) {
		
		//check values of hand
		int handValue = 0;
		int handValue2 = 0;
		int actualValue = 0;
		
		//intial value ace = 1
		for(int totalSizePlayer = 0; totalSizePlayer < hand.size(); totalSizePlayer++) {
			handValue += ((Card) ((RandIndexQueue<Card>) hand).get(totalSizePlayer)).value();
		}
		
		//initial value ace = 11
		for(int totalSizePlayer = 0; totalSizePlayer < hand.size(); totalSizePlayer++) {
			handValue2 += ((Card) ((RandIndexQueue<Card>) hand).get(totalSizePlayer)).value2();
		}
		
		//if we have an ace, change the value
		if(handValue > 21) {
			actualValue = handValue2;
		} else {
			actualValue = handValue;
		 }
		
		return actualValue;
		
	}
	
	private static void clearHand(MyQ hand, MyQ destination) {
		
		//size of the hand
		int actualSize = hand.size();
		
		//clear hand and poll into destination
		for(int handSize = 0; handSize < (actualSize); handSize++) {
			destination.offer(hand.poll());
		}
		
	}
	
	private static void noMoreCards(MyQ shoe, MyQ discard, int round, int shoeSize) {
		
		//check to see if shoesize is less than 1/4 of itself
		if(shoe.size() < (shoeSize/4)) { 
			System.out.println("Reshuffling the shoe in Round " + round);
			
			//offer discardpile into regular pile
			for(int discardSize = 0; discardSize < (shoeSize * 3/4); discardSize++) {
				shoe.offer(discard.poll());
			}
			
			((RandIndexQueue<Card>) shoe).shuffle();

		} else {
			//nothing
		}
		
	}
	
	private static void hit(MyQ hand, MyQ stack, MyQ discard, String person, int round) {	
		
		boolean hits = true;
		
		while(hits == true) {
		
			//decide if we hit or stay
		if (checkValues(hand) > 16 && checkValues(hand) < 22) {
			String stands = person + " STANDS: Contents: ";
			for(int amountOfCards = 0; amountOfCards < hand.size(); amountOfCards++) {
				stands += (((RandIndexQueue<Card>) hand).get(amountOfCards) + " ");
			}
			stands += checkValues(hand);
			System.out.println(stands);
			hits = false;
		} else if(checkValues(hand) < 17) {
			//continually hit
			hand.offer(stack.poll());
			//noMoreCards(stack ,discard, round);
			System.out.println(person + " hits: " + ((RandIndexQueue<Card>) hand).get(hand.size() - 1));
			//hand.size() - 1
		} else {
			//busts conditional
			String busts = person + " BUSTS: Contents: ";
			for(int amountOfCards = 0; amountOfCards < hand.size(); amountOfCards++) {
				busts += (((RandIndexQueue<Card>) hand).get(amountOfCards) + " ");
			}
			busts += checkValues(hand);
			System.out.println(busts);
			
			if(person == "Player") {
				playerBust = true;
			}
			
			hits = false;
		}
		}
		
	}
	
	private static void blackJack(MyQ hand, String person) {
		//BlackJack conditional
		if(checkValues(hand) == 21) {
			System.out.println("BlackJack " + person + " Wins!");
		}
	}
	
	private static void printHand(MyQ hand, String person) {
		//prints the hand of a person
		String handCount = person + " Contents: ";
		for(int amountOfCards = 0; amountOfCards < hand.size(); amountOfCards++) {
			handCount += (((RandIndexQueue<Card>) hand).get(amountOfCards) + " ");
		}
		handCount += checkValues(hand);
		System.out.println(handCount);
	}
	
	private static void checkResults(MyQ hand1, MyQ hand2) {
		//condiitonal to see who has the winning hand
		if(checkValues(hand1) > checkValues(hand2) && checkValues(hand1) < 22){
			playerWins++;
			System.out.println("Result: Player Wins!");
		} else if(checkValues(hand1) == checkValues(hand2) && checkValues(hand1) < 22 && checkValues(hand2) < 22) {
			ties++;
			System.out.println("Result: DRAW!");
		} else if(checkValues(hand1) < checkValues(hand2) && checkValues(hand2) < 22) {
			dealerWins++;
			System.out.println("Result: Dealer Wins!");
		} else if(playerBust = true) {
			dealerWins++;
			System.out.println("Result: Dealer Wins!");
		} else {
			playerWins++;
			System.out.println("Result: Player Wins!");
		}
	}
	
}
