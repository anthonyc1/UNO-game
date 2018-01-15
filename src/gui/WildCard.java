/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.image.Image;

/**
 *
 * @author anthonychan
 */
public class WildCard extends Card{
    
    protected String color;
    
    public WildCard(Image image, int height, int width) {
        super(image, height, width);
        setOnMouseClicked(e -> {
            if (Workspace.getTurn().equals("PLAYER") && canPlay()) {
                playCardByPlayer();
            } else {
                Workspace.setDialog("Invalid move");
            }
        });
    }
    
    @Override
    public boolean canPlay() {
        return true;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String toString(){
        return color + " wild";
    }
    
}
