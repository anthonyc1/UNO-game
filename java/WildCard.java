
public class WildCard extends Card implements Comparable<Object>{
	private String name = "wild";
	private String color="";
	private String number="";
	
	public WildCard(){
	}
	
	public WildCard(String color){
		this.color = color;
	}
	
	public String getColor(){
		return color;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public String getName(){
		return name;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String toString(){
		return "Card is a "+ getColor()+" "+getName() ;
	}
	
	public int compareTo(Object o){
		if (o instanceof WildCard){
			return 1;
		} else {
			return -1;
		}
	}
	
}
