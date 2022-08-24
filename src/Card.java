public class Card {

    private String suitName;
    private int value;
    private String valueName;
    public Card(String suitName, int value, String valueName) {
        this.suitName = suitName;
        this.value = value;
        this.valueName = valueName;

    }

    public String getSuit() {
        return this.suitName;
    }

    public int getValue() {
        return this.value;
    }

    public String getValueName() {
        return this.valueName;
    }

}
