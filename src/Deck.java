import java.util.Random;

public class Deck {

	Card[] cards = new Card[52];
	private int place;

	public Deck() {
		for (int i = 0; i < 52; i++) {
			switch (i % 4) {
			case 0: cards[i] = new Card(i%13 + 1, "clubs");
					break;
			case 1: cards[i] = new Card(i%13 + 1, "spades");
					break;
			case 2: cards[i] = new Card(i%13 + 1, "diamonds");
					break;
			case 3: cards[i] = new Card(i%13 + 1, "hearts");
					break;
			}
		}
		shuffle();
		place = 0;
	}
	
	private void shuffle() {
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			swap(rand.nextInt(52), rand.nextInt(52));
		}
	}
	
	private void swap(int x, int y) {
		Card temp = cards[x];
		cards[x] = cards[y];
		cards[y] = temp;
	}

	public Card next() {
		place++;
		if (place > 52) {
			place = 0;
			shuffle();
		}
		return cards[place];
		
	}
}
