package card;

/**
 * 牌类
 */
public class Card {
    public String number;
    public String color;

    public Card(String color,String number) {
        super();
        this.number = number;
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }
}