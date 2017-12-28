package uno;

public class NumericCard extends Card implements Comparable<Object> {

    private String name = "number";
    private String color;

    private String num;

    public NumericCard(String num, String color) {
        this.num = num;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getNumber() {
        return num;
    }

    public int compareTo(Object o) {
        if (o instanceof NumericCard) {
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() {
        return "Card is a " + getColor() + " " + getNumber();
    }

}
