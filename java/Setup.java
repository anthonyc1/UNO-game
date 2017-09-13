
import java.util.Scanner;

import java.util.ArrayList;

public class Setup {
	
	public static ArrayList<Card> deck = new ArrayList<Card>(36);
	
	public static ArrayList<Card> setup(){
		//assemble deck
		deck.add(new NumericCard("1","red"));
		deck.add(new NumericCard("1","blue"));
		deck.add(new NumericCard("1","yellow"));
		deck.add(new NumericCard("1","green"));
		
		deck.add(new NumericCard("2","red"));
		deck.add(new NumericCard("2","blue"));
		deck.add(new NumericCard("2","yellow"));
		deck.add(new NumericCard("2","green"));
		
		deck.add(new NumericCard("3","red"));
		deck.add(new NumericCard("3","blue"));
		deck.add(new NumericCard("3","yellow"));
		deck.add(new NumericCard("3","green"));
		
		deck.add(new NumericCard("4","red"));
		deck.add(new NumericCard("4","blue"));
		deck.add(new NumericCard("4","yellow"));
		deck.add(new NumericCard("4","green"));
		
		deck.add(new NumericCard("5","red"));
		deck.add(new NumericCard("5","blue"));
		deck.add(new NumericCard("5","yellow"));
		deck.add(new NumericCard("5","green"));
		
		deck.add(new NumericCard("6","red"));
		deck.add(new NumericCard("6","blue"));
		deck.add(new NumericCard("6","yellow"));
		deck.add(new NumericCard("6","green"));
	
		deck.add(new NumericCard("7","red"));
		deck.add(new NumericCard("7","blue"));
		deck.add(new NumericCard("7","yellow"));
		deck.add(new NumericCard("7","green"));
		
		deck.add(new PlusOne("red"));
		deck.add(new PlusOne("blue"));
		deck.add(new PlusTwo("green"));
		deck.add(new PlusTwo("yellow"));
		
		deck.add(new WildCard());
		deck.add(new WildCard());
		deck.add(new WildCard());
		deck.add(new WildCard());
		
		return shuffleDeck(deck);
	}
	
	public static ArrayList<Card> shuffleDeck(ArrayList<Card> s){
		//randomly shuffle ArrayList
				for(int i=0; i<36; i++){
					int rand = (int)(36*Math.random());
					Card temp = s.get(i);
					s.set(i, s.get(rand));
					s.set(rand, temp);

				}
				return s;
	}
	
	public static void printDeck(ArrayList<Card> s){
		//randomly shuffle ArrayList
				for(int i=0; i<s.size(); i++){
					System.out.println(s.get(i));
				}
	}
	
	public static int initialDraw(){
		System.out.println("START OF UNO GAME");
		System.out.println("----------");
		do {
		
		int first = (int)(36*Math.random());
		int second = (int)(36*Math.random());
		Card firstCard = deck.get(first);
		Card secondCard = deck.get(second);
		if (firstCard instanceof NumericCard && secondCard instanceof NumericCard){
			if (Integer.parseInt(((NumericCard) firstCard).getNumber()) > Integer.parseInt(((NumericCard) secondCard).getNumber())){
				System.out.println("TURN: PLAYER GOES FIRST.");
				return 1;
			} else if (Integer.parseInt(((NumericCard) firstCard).getNumber()) < Integer.parseInt(((NumericCard) secondCard).getNumber())){
				System.out.println("TURN: PLAYER GOES SECOND.");
				return -1;
			}
		}
		} while (true);
	}
	
	public static ArrayList<Card> player = new ArrayList<Card>();
	public static ArrayList<Card> computer = new ArrayList<Card>();
	public static ArrayList<Card> discard = new ArrayList<Card>(36);
	
	public static int firstToGo;
	
	public static void dealCards(){
		ArrayList<Card> deck = setup();
		//shuffle again
		System.out.println("DECK IN PLAY");
		System.out.println("----------");
		printDeck(shuffleDeck(deck));
		System.out.println();
		
		Card playerHand, computerHand;
		firstToGo = initialDraw();

			System.out.println("------------------");
			System.out.println("PLAYER HAND:");
			for (int i=0;i<5; i++){
				playerHand = deck.remove(i);
				player.add(playerHand);
				System.out.println(playerHand.toString());
			}
			System.out.println("------------------");
			System.out.println("COMPUTER'S HAND:");
			for (int i=5;i<10; i++){
				computerHand = deck.remove(i);
				computer.add(computerHand);
				System.out.println(computerHand.toString());
			}
		boolean flag = true;
		int i = 0;
		do{
			Card card = deck.remove(i);
			discard.add(card);
			i++;
		if (card instanceof NumericCard){
			flag = false;
			System.out.println("------------------");
			System.out.println("CARD IN PLAY:");
			System.out.println(card.toString());
		}
		
		} while(flag == true);
		
	}
	
	public static void main(String[] args){
		dealCards();
	}
	
}
