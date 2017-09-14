

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	static ArrayList<Card> player = Setup.player;
	static ArrayList<Card> computer = Setup.computer;
	static ArrayList<Card> deck = Setup.deck;
	static ArrayList<Card> discard = Setup.discard;
	
	static String[] colors = {"blue", "red", "green", "yellow"};
	static boolean playerSkipTurn = false;
	static boolean computerSkipTurn = false;
	
	public static void gamePlay(){
		Scanner in = new Scanner(System.in);
		do{
			
			//normal gameplay
			int firstToGo = Setup.firstToGo;
			if (firstToGo >0){
					//player goes first	
				if(!(playerSkipTurn == true)){
					System.out.println("------------------");
					System.out.println("Play a card. Enter the index of the card to play, starting with 0.\nOr enter -1 to draw from deck.");
					int card2Play = in.nextInt();
					playCard(card2Play);
					playerCallsUno();
					printPlayerHand(player);
					printComputerHand(computer);
					if (endGameConditions()== true){
						break;
					}
				} else {
					playerSkipTurn = false;
				}
					//computer goes
				if(!(computerSkipTurn == true)){
					computerPlayCard();
					computerCallsUno();
					printPlayerHand(player);
					printComputerHand(computer);
					if (endGameConditions()== true){
						break;
					}
				} else {
					computerSkipTurn = false;
				}
			} else {
					//computer goes first
				if(!(computerSkipTurn == true)){
					computerPlayCard();
					computerCallsUno();
					printPlayerHand(player);
					printComputerHand(computer);
					reshuffleIntoDeck();
					if (endGameConditions()== true){
						break;
					}
				} else {
					computerSkipTurn = false;
				}
					//player goes
					if(!(playerSkipTurn == true)){
					System.out.println("------------------");
					System.out.println("Play a card. Enter the index of the card to play, starting with 0.\nOr enter -1 to draw from deck.");
					int card2Play = in.nextInt();
					playCard(card2Play);
					playerCallsUno();
					printPlayerHand(player);
					printComputerHand(computer);
					reshuffleIntoDeck();
					if (endGameConditions()== true){
						break;
					}
					} else {
						playerSkipTurn = false;
					}
			}
			} while(true);
		
	}
	
	//gameplay methods
	public static boolean colorMatch(Card played, Card onfield){
		if (played.getColor().equals(onfield.getColor())){
			return true;
		}
		return false;
	}
	
	public static boolean numberMatch(Card played, Card onfield){
		if (played.getNumber().equals(onfield.getNumber())){
			return true;
		}
		return false;
	}
	
	public static void reshuffleIntoDeck(){
		if (deck.size() == 0){
			//return discard pile, minus last card, to deck
			for (int i = 0; i<discard.size()-1; i++){
				deck.add(discard.remove(i));
			}
			//shuffle deck
			System.out.println("------------------");
			System.out.println("DISCARD PILE IS RESHUFFLED INTO DECK.");
			Setup.shuffleDeck(deck);
		}
	}
	
	public static boolean endGameConditions(){
		//end game
		if (player.size()==0){
			System.out.println("------------------");
			System.out.println("YOU WIN. END GAME");
			return true;
		}
		if ( computer.size()==0){
			System.out.println("------------------");
			System.out.println("YOU LOSE. END GAME");
			return true;
		}
		return false;
	}
	
	public static void printPlayerHand(ArrayList<Card> hand){
		System.out.println("------------------");
		System.out.println("PLAYER HAND:");
		for (int i=0;i<hand.size(); i++){
			System.out.println(hand.get(i).toString());
		}
	}
	
	public static void printComputerHand(ArrayList<Card> hand){
		System.out.println("------------------");
		System.out.println("COMPUTER HAND:");
		for (int i=0;i<hand.size(); i++){
			System.out.println(hand.get(i).toString());
		}
	}
	
	public static void playCard(int card2Play){
		Scanner in = new Scanner(System.in);
		Card discardCard = discard.get(discard.size()-1);
		if (card2Play == -1){
			Card card = deck.remove(0);
			System.out.println("CARD DRAWN:");
			System.out.println(card.toString());
			if (colorMatch(card,discardCard)==true || numberMatch(card,discardCard)==true ){
				discard.add(card);
				System.out.println("------------------");
				System.out.println("PLAYER PLAYS:");
				System.out.println(card.toString());
				if(card instanceof PlusOne){
					System.out.println("Computer draws 1");
					computer.add(deck.remove(0));
					computerSkipTurn = true;
					
				} else if (card instanceof PlusTwo){
					System.out.println("Computer draws 2");
					computer.add(deck.remove(0));
					computer.add(deck.remove(0));
					computerSkipTurn = true;
				}
			}
			else if (card instanceof WildCard){
				System.out.println("Choose a color (red, blue, green, or yellow):");
				String color = in.nextLine();
				((WildCard) card).setColor(color);
				discard.add(card);
				System.out.println("------------------");
				System.out.println("PLAYER PLAYS:");
				System.out.println(card.toString());
			} else {
				player.add(card);
				System.out.println("------------------");
				System.out.println("PLAYER DRAWS AND PASSES TURN.");
			}
			
		} else {
			Card card = player.get(card2Play);
			if (colorMatch(card,discardCard)==true || numberMatch(card,discardCard)==true ){
				discard.add(player.remove(card2Play));
				if(card instanceof PlusOne){
					System.out.println("Computer draws 1");
					computer.add(deck.remove(0));
					computerSkipTurn = true;
				} else if (card instanceof PlusTwo){
					System.out.println("Computer draws 2");
					computer.add(deck.remove(0));
					computer.add(deck.remove(0));
					computerSkipTurn = true;
				}
			} else if (card instanceof WildCard){
				System.out.println("Choose a color (red, blue, green, or yellow):");
				String color = in.nextLine();
				((WildCard) card).setColor(color);
				discard.add(player.remove(card2Play));
			} else {
				System.out.println("Invalid card. Play another one.");
				card2Play = in.nextInt();
				playCard(card2Play);
			}
			System.out.println("------------------");
			System.out.println("PLAYER PLAYS:");
			System.out.println(card.toString());
		}
	}
	
	public static void computerPlayCard(){
		Card discardCard = discard.get(discard.size()-1);
		boolean needToDrawCard = true;
		for(int i = 0; i<computer.size();i++){
			Card card = computer.get(i);
			if (colorMatch(card,discardCard)==true || numberMatch(card,discardCard)==true ){
				computer.remove(i);
				discard.add(card);
				System.out.println("------------------");
				System.out.println("COMPUTER PLAYS:");
				System.out.println(card.toString());
				needToDrawCard = false;
				if(card instanceof PlusOne){
					System.out.println("Player draws 1");
					player.add(deck.remove(0));
					playerSkipTurn = true;
				} else if (card instanceof PlusTwo){
					System.out.println("Player draws 2");
					player.add(deck.remove(0));
					player.add(deck.remove(0));
					playerSkipTurn = true;
				}
				break;
			} else if (card instanceof WildCard){
				int index = (int)(Math.floor(Math.random()*4));
				String color = colors[index];
				((WildCard) card).setColor(color);
				computer.remove(i);
				discard.add(card);
				System.out.println("------------------");
				System.out.println("COMPUTER PLAYS:");
				System.out.println(card.toString());
				needToDrawCard = false;
				break;
			}
		}
		if (needToDrawCard == true){
				Card card = deck.remove(0);
				System.out.println("CARD DRAWN:");
				System.out.println(card.toString());
				if (colorMatch(card,discardCard)==true || numberMatch(card,discardCard)==true ){
					discard.add(card);
					System.out.println("------------------");
					System.out.println("COMPUTER PLAYS:");
					System.out.println(card.toString());
					if(card instanceof PlusOne){
						System.out.println("Player draws 1");
						player.add(deck.remove(0));
						playerSkipTurn = true;
					} else if (card instanceof PlusTwo){
						System.out.println("Player draws 2");
						player.add(deck.remove(0));
						player.add(deck.remove(0));
						playerSkipTurn = true;
					}
				}
				else if (card instanceof WildCard){
					int index = (int)(Math.floor(Math.random()*4));
					String color = colors[index];
					((WildCard) card).setColor(color);
					discard.add(card);
					System.out.println("------------------");
					System.out.println("COMPUTER PLAYS:");
					System.out.println(card.toString());
				} else {
					computer.add(card);
					System.out.println("------------------");
					System.out.println("COMPUTER DRAWS AND PASSES TURN.");
				}
				
			}
	}
	
	public static void playerCallsUno(){
		if (player.size()==2){
			//initiate call UNO function before
			//second-to-last card is selected for discard pile
		Scanner in = new Scanner(System.in);
		System.out.println("Remember to call \"UNO\".");
		String uno = in.nextLine();
		if (uno.equalsIgnoreCase("uno")){
			System.out.println("Player calls UNO");
		} else {
			System.out.println("Player did not call UNO. Player draws 2.");
			player.add(deck.remove(0));
			player.add(deck.remove(0));
		}
		}
	}
	public static void computerCallsUno(){
		int chance;
		if(computer.size()==2){
			chance = (int)(Math.random()*10);
			if (chance<5){
				System.out.println("Computer calls UNO.");
			} else {
				System.out.println("Computer forgets to call UNO. Computer draws 2.");
				computer.add(deck.remove(0));
				computer.add(deck.remove(0));
			}
		}
	}
	
	public static void main(String[] args){

		Setup.dealCards();
		gamePlay();
	}
}
