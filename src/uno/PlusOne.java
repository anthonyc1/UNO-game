package uno;

public class PlusOne extends Card implements Comparable<Object> {

    private String name = "plusOne";
    private String color;
    private String number = "1";

    public PlusOne(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public int compareTo(Object o) {
        if (o instanceof PlusOne) {
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() {
        return "Card is a " + getColor() + " " + getName();
    }
}
