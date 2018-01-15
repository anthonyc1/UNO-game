/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author anthonychan
 */
public class Data {
    
    protected static ArrayList<Card> deck;
    protected static ArrayList<Card> discard;
    protected static ArrayList<Card> playerHand;
    protected static ArrayList<Card> opponentHand;
    
    protected static ArrayList<Card> deckOfCardbacks;
    File cardback;
    Image image;
    Card back;
    Card back2;
    Card back3;
    Card back4;
    Card back5;
    Card back6;
    Card back7;
    Card back8;
    Card back9;
    Card back10;
    Card back11;
    Card back12;
    Card back13;
    Card back14;
    Card back15;
    Card back16;
    Card back17;
    Card back18;
    Card back19;
    Card back20;
    Card back21;
    Card back22;
    Card back23;
    Card back24;
    Card back25;
    Card back26;
    Card back27;
    Card back28;
    Card back29;
    Card back30;
    Card back31;
    
    static Effect highlightedEffect;
    static Effect highlightedEffect2;
    
    static File blue1card;
    static File green1card;
    static File red1card;
    static File yellow1card;
    static File blue2card;
    static File green2card;
    static File red2card;
    static File yellow2card;
    static File blue3card;
    static File green3card;
    static File red3card;
    static File yellow3card;
    static File blue4card;
    static File green4card;
    static File red4card;
    static File yellow4card;
    static File blue5card;
    static File green5card;
    static File red5card;
    static File yellow5card;
    static File blue6card;
    static File green6card;
    static File red6card;
    static File yellow6card;
    static File blue7card;
    static File green7card;
    static File red7card;
    static File yellow7card;
    static File blue1pluscard;
    static File red1pluscard;
    static File green2pluscard;
    static File yellow2pluscard;
    static File wildcard;
    
    static Card blue1;
    static Card red1;
    static Card green1;
    static Card yellow1;
    static Card blue2;
    static Card red2;
    static Card green2;
    static Card yellow2;
    static Card blue3;
    static Card red3;
    static Card green3;
    static Card yellow3;
    static Card blue4;
    static Card red4;
    static Card green4;
    static Card yellow4;
    static Card blue5;
    static Card red5;
    static Card green5;
    static Card yellow5;
    static Card blue6;
    static Card red6;
    static Card green6;
    static Card yellow6;
    static Card blue7;
    static Card red7;
    static Card green7;
    static Card yellow7;
    static Card red1plus;
    static Card blue1plus;
    static Card green2plus;
    static Card yellow2plus;
    static Card wild1;
    static Card wild2;
    static Card wild3;
    static Card wild4;
            

    public Data() {
        DropShadow dropShadowEffect = new DropShadow();
        dropShadowEffect.setOffsetX(0.0f);
        dropShadowEffect.setOffsetY(0.0f);
        dropShadowEffect.setSpread(1.0);
        dropShadowEffect.setColor(Color.YELLOW);
        dropShadowEffect.setBlurType(BlurType.GAUSSIAN);
        dropShadowEffect.setRadius(5);
        highlightedEffect = dropShadowEffect;
        
        DropShadow dropShadowEffect2 = new DropShadow();
        dropShadowEffect2.setOffsetX(0.0f);
        dropShadowEffect2.setOffsetY(0.0f);
        dropShadowEffect2.setSpread(1.0);
        dropShadowEffect2.setColor(Color.RED);
        dropShadowEffect2.setBlurType(BlurType.GAUSSIAN);
        dropShadowEffect2.setRadius(5);
        highlightedEffect2 = dropShadowEffect2;
        
        deck = new ArrayList<>();
        discard = new ArrayList<>();
        playerHand = new ArrayList<>();
        opponentHand = new ArrayList<>();

        blue1card = new File("resources/1_blue.jpg");
        green1card = new File("resources/1_green.jpg");
        red1card = new File("resources/1_red.jpg");
        yellow1card = new File("resources/1_yellow.jpg");
        blue2card = new File("resources/2_blue.jpg");
        green2card = new File("resources/2_green.jpg");
        red2card = new File("resources/2_red.jpg");
        yellow2card = new File("resources/2_yellow.jpg");
        blue3card = new File("resources/3_blue.jpg");
        green3card = new File("resources/3_green.jpg");
        red3card = new File("resources/3_red.jpg");
        yellow3card = new File("resources/3_yellow.jpg");
        blue4card = new File("resources/4_blue.jpg");
        green4card = new File("resources/4_green.jpg");
        red4card = new File("resources/4_red.jpg");
        yellow4card = new File("resources/4_yellow.jpg");
        blue5card = new File("resources/5_blue.jpg");
        green5card = new File("resources/5_green.jpg");
        red5card = new File("resources/5_red.jpg");
        yellow5card = new File("resources/5_yellow.jpg");
        blue6card = new File("resources/6_blue.jpg");
        green6card = new File("resources/6_green.jpg");
        red6card = new File("resources/6_red.jpg");
        yellow6card = new File("resources/6_yellow.jpg");
        blue7card = new File("resources/7_blue.jpg");
        green7card = new File("resources/7_green.jpg");
        red7card = new File("resources/7_red.jpg");
        yellow7card = new File("resources/7_yellow.jpg");
        blue1pluscard = new File("resources/1_plus_blue.jpg");
        red1pluscard = new File("resources/1_plus_red.jpg");
        green2pluscard = new File("resources/2_plus_green.jpg");
        yellow2pluscard = new File("resources/2_plus_yellow.jpg");
        wildcard = new File("resources/wild.jpg");

        blue1 = new NumericCard(new Image("file:" + blue1card.getPath()), 75, 50, "blue", 1);
        green1 = new NumericCard(new Image("file:" + green1card.getPath()), 75, 50, "green", 1);
        red1 = new NumericCard(new Image("file:" + red1card.getPath()), 75, 50, "red", 1);
        yellow1 = new NumericCard(new Image("file:" + yellow1card.getPath()), 75, 50, "yellow", 1);
        blue2 = new NumericCard(new Image("file:" + blue2card.getPath()), 75, 50, "blue", 2);
        green2 = new NumericCard(new Image("file:" + green2card.getPath()), 75, 50, "green", 2);
        red2 = new NumericCard(new Image("file:" + red2card.getPath()), 75, 50, "red", 2);
        yellow2 = new NumericCard(new Image("file:" + yellow2card.getPath()), 75, 50, "yellow", 2);
        blue3 = new NumericCard(new Image("file:" + blue3card.getPath()), 75, 50, "blue", 3);
        green3 = new NumericCard(new Image("file:" + green3card.getPath()), 75, 50, "green", 3);
        red3 = new NumericCard(new Image("file:" + red3card.getPath()), 75, 50, "red", 3);
        yellow3 = new NumericCard(new Image("file:" + yellow3card.getPath()), 75, 50, "yellow", 3);
        blue4 = new NumericCard(new Image("file:" + blue4card.getPath()), 75, 50, "blue", 4);
        green4 = new NumericCard(new Image("file:" + green4card.getPath()), 75, 50, "green", 4);
        red4 = new NumericCard(new Image("file:" + red4card.getPath()), 75, 50, "red", 4);
        yellow4 = new NumericCard(new Image("file:" + yellow4card.getPath()), 75, 50, "yellow", 4);
        blue5 = new NumericCard(new Image("file:" + blue5card.getPath()), 75, 50, "blue", 5);
        green5 = new NumericCard(new Image("file:" + green5card.getPath()), 75, 50, "green", 5);
        red5 = new NumericCard(new Image("file:" + red5card.getPath()), 75, 50, "red", 5);
        yellow5 = new NumericCard(new Image("file:" + yellow5card.getPath()), 75, 50, "yellow", 5);
        blue6 = new NumericCard(new Image("file:" + blue6card.getPath()), 75, 50, "blue", 6);
        green6 = new NumericCard(new Image("file:" + green6card.getPath()), 75, 50, "green", 6);
        red6 = new NumericCard(new Image("file:" + red6card.getPath()), 75, 50, "red", 6);
        yellow6 = new NumericCard(new Image("file:" + yellow6card.getPath()), 75, 50, "yellow", 6);
        blue7 = new NumericCard(new Image("file:" + blue7card.getPath()), 75, 50, "blue", 7);
        green7 = new NumericCard(new Image("file:" + green7card.getPath()), 75, 50, "green", 7);
        red7 = new NumericCard(new Image("file:" + red7card.getPath()), 75, 50, "red", 7);
        yellow7 = new NumericCard(new Image("file:" + yellow7card.getPath()), 75, 50, "yellow", 7);
        blue1plus = new PlusOne(new Image("file:" + blue1pluscard.getPath()), 75, 50, "blue");
        red1plus = new PlusOne(new Image("file:" + red1pluscard.getPath()), 75, 50, "red");
        green2plus = new PlusTwo(new Image("file:" + green2pluscard.getPath()), 75, 50, "green");
        yellow2plus = new PlusTwo(new Image("file:" + yellow2pluscard.getPath()), 75, 50, "yellow");
        wild1 = new WildCard(new Image("file:" + wildcard.getPath()), 75, 50);
        wild2 = new WildCard(new Image("file:" + wildcard.getPath()), 75, 50);
        wild3 = new WildCard(new Image("file:" + wildcard.getPath()), 75, 50);
        wild4 = new WildCard(new Image("file:" + wildcard.getPath()), 75, 50);
        
        deck.add(blue1);
        deck.add(green1);
        deck.add(red1);
        deck.add(yellow1);
        deck.add(blue2);
        deck.add(green2);
        deck.add(red2);
        deck.add(yellow2);
        deck.add(blue3);
        deck.add(green3);
        deck.add(red3);
        deck.add(yellow3);
        deck.add(blue4);
        deck.add(green4);
        deck.add(red4);
        deck.add(yellow4);
        deck.add(blue5);
        deck.add(green5);
        deck.add(red5);
        deck.add(yellow5);
        deck.add(blue6);
        deck.add(green6);
        deck.add(red6);
        deck.add(yellow6);
        deck.add(blue7);
        deck.add(green7);
        deck.add(red7);
        deck.add(yellow7);
        deck.add(blue1plus);
        deck.add(green2plus);
        deck.add(red1plus);
        deck.add(yellow2plus);
        deck.add(wild1);
        deck.add(wild2);
        deck.add(wild3);
        deck.add(wild4);
        
        setupCardbacks();
    }

    public void highlightCard(Node shape) {
        if (shape instanceof Card) {
            ((Card) shape).setEffect(highlightedEffect);
        }
    }

    public void unhighlightCard(Node shape) {
        if (shape instanceof Card) {
            ((Card) shape).setEffect(null);
        }
    }

    public static Effect getHighlightedEffect() {
        return highlightedEffect;
    }
    
    public static Effect getHighlightedEffect2() {
        return highlightedEffect2;
    }
    
    public static ArrayList<Card> shuffleDeck(ArrayList<Card> s) {
        int size = s.size();
        for (int i = 0; i < size; i++) {
            int rand = (int) (size * Math.random());
            Card temp = s.get(i);
            s.set(i, s.get(rand));
            s.set(rand, temp);
        }
        return s;
    }
    
    private void setupCardbacks(){
        cardback = new File("resources/back.jpg");
        image = new Image("file:" + cardback.getPath());
        back = new BackCard(image, 75, 50);
        back2 = new BackCard(image, 75, 50);
        back3 = new BackCard(image, 75, 50);
        back4 = new BackCard(image, 75, 50);
        back5 = new BackCard(image, 75, 50);
        back6 = new BackCard(image, 75, 50);
        back7 = new BackCard(image, 75, 50);
        back8 = new BackCard(image, 75, 50);
        back9 = new BackCard(image, 75, 50);
        back10 = new BackCard(image, 75, 50);
        back11 = new BackCard(image, 75, 50);
        back12 = new BackCard(image, 75, 50);
        back13 = new BackCard(image, 75, 50);
        back14 = new BackCard(image, 75, 50);
        back15 = new BackCard(image, 75, 50);
        back16 = new BackCard(image, 75, 50);
        back17 = new BackCard(image, 75, 50);
        back18 = new BackCard(image, 75, 50);
        back19 = new BackCard(image, 75, 50);
        back20 = new BackCard(image, 75, 50);
        back21 = new BackCard(image, 75, 50);
        back22 = new BackCard(image, 75, 50);
        back23 = new BackCard(image, 75, 50);
        back24 = new BackCard(image, 75, 50);
        back25 = new BackCard(image, 75, 50);
        back26 = new BackCard(image, 75, 50);
        back27 = new BackCard(image, 75, 50);
        back28 = new BackCard(image, 75, 50);
        back29 = new BackCard(image, 75, 50);
        back30 = new BackCard(image, 75, 50);
        back31 = new BackCard(image, 75, 50);

        deckOfCardbacks = new ArrayList<>();
        
        deckOfCardbacks.add(back);
        deckOfCardbacks.add(back2);
        deckOfCardbacks.add(back3);
        deckOfCardbacks.add(back4);
        deckOfCardbacks.add(back5);
        deckOfCardbacks.add(back6);
        deckOfCardbacks.add(back7);
        deckOfCardbacks.add(back8);
        deckOfCardbacks.add(back9);
        deckOfCardbacks.add(back10);
        deckOfCardbacks.add(back11);
        deckOfCardbacks.add(back12);
        deckOfCardbacks.add(back13);
        deckOfCardbacks.add(back14);
        deckOfCardbacks.add(back15);
        deckOfCardbacks.add(back16);
        deckOfCardbacks.add(back17);
        deckOfCardbacks.add(back18);
        deckOfCardbacks.add(back19);
        deckOfCardbacks.add(back20);
        deckOfCardbacks.add(back21);
        deckOfCardbacks.add(back22);
        deckOfCardbacks.add(back23);
        deckOfCardbacks.add(back24);
        deckOfCardbacks.add(back25);
        deckOfCardbacks.add(back26);
        deckOfCardbacks.add(back27);
        deckOfCardbacks.add(back28);
        deckOfCardbacks.add(back29);
        deckOfCardbacks.add(back30);
        deckOfCardbacks.add(back31);
    }

    /**
     * @return the deck
     */
    public static ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * @return the discard
     */
    public static ArrayList<Card> getDiscard() {
        return discard;
    }

    /**
     * @return the playerHand
     */
    public static ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    /**
     * @return the opponentHand
     */
    public static ArrayList<Card> getOpponentHand() {
        return opponentHand;
    }

    /**
     * @return the deckOfCardbacks
     */
    public static ArrayList<Card> getDeckOfCardbacks() {
        return deckOfCardbacks;
    }

}
