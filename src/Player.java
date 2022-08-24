import java.util.ArrayList;

public class Player {
    String name = "Player";
    private int account = 1000;
    private ArrayList<Card> hand = new ArrayList<>();

    public Player() {
        String name = "Player";
    }

    public Player(String name) {

    }

    public int bet(int amount) {
        account = account - amount;
        return amount;
    }

    public void addWinnings(int bet) {
        account += (bet * 2);
    }

    public int getAccount() {
        return account;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void discardHand() {
        hand.clear();
    }


}
