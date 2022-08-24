import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public String[] suitNames = {"Clubs", "Diamonds", "Hearts", "Spades"};
    public int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    public String[] valueNames = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    public ArrayList<Card> myDeck = new ArrayList<Card>();

    public Deck() {
        for (String suitName : suitNames) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card(suitName, values[j], valueNames[j]);
                myDeck.add(card);
            }
        }
        Collections.shuffle(myDeck);
    }

    public Card draw() {
        Card card = myDeck.get(0);
        myDeck.remove(0);
        return card;
    }

    public int getDeckSize() {
        return this.myDeck.size();
    }
}