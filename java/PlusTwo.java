
public class PlusTwo extends Card implements Comparable<Object>{
	private String name = "plusTwo";
	private String color;
	private String number = "2";
	
	public PlusTwo(String color){
		this.color = color;
	}
	
	public String getName(){
		return name;
	}
	
	public String getColor(){
		return color;
	}
	
	public String getNumber() {
		return number;
	}
	
	public int compareTo(Object o){
		if (o instanceof PlusTwo){
			return 1;
		} else {
			return -1;
		}
	}
	
	public String toString(){
		return "Card is a "+getColor()+" "+getName();
	}
}
