/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 *
 * @author anthonychan
 */
public class NumericCard extends Card {

    protected String color;
    protected int number;

    public NumericCard(Image image, int height, int width, String color, int number) {
        super(image, height, width);
        this.color = color;
        this.number = number;
        setOnMouseClicked(e -> {
            Card discard = Workspace.getDiscard();
            if (Workspace.getTurn().equals("PLAYER")) {
                if (discard instanceof NumericCard) {
                    playCard();
                } else if (discard instanceof PlusOne) {
                    if (((PlusOne) discard).color.equals(color)) {
                        playCard();
                    }
                } else if (discard instanceof PlusTwo) {
                    if (((PlusTwo) discard).color.equals(color)) {
                        playCard();
                    }
                } else if (discard instanceof WildCard){
                    if (((WildCard) discard).color.equals(color)) {
                        playCard();
                    }
                }

            } else {
                Workspace.setDialog("Invalid move");
            }

        });
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

}
