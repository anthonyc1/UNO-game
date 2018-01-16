/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno.data;

import uno.gui.Workspace;
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
            if (Workspace.getTurn().equals("PLAYER") && canPlay()) {
                playCardByPlayer();
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

    @Override
    public boolean canPlay() {
        Card discard = Workspace.getDiscard();
        if (discard instanceof NumericCard) {
            if (((NumericCard) discard).color.equals(color)) {
                return true;
            } else if (((NumericCard) discard).number == number){
                return true;
            }
        } else if (discard instanceof PlusOne) {
            if (((PlusOne) discard).color.equals(color)) {
                return true;
            }
        } else if (discard instanceof PlusTwo) {
            if (((PlusTwo) discard).color.equals(color)) {
                return true;
            }
        } else if (discard instanceof WildCard) {
            if (((WildCard) discard).getColor().equals(color)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return color + " " + number;
    }

}
