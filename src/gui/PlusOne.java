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
public class PlusOne extends Card{
    
    protected String color;
    
    public PlusOne(Image image, int height, int width, String color) {
        super(image, height, width);
        this.color = color;
        setOnMouseClicked(e->{
            Card discard = Workspace.getDiscard();
            if (Workspace.getTurn().equals("PLAYER")) {
                if (discard instanceof PlusOne){

                }
                Workspace.setTurn("OPPONENT");
                Workspace.decrementPlayerHandSize();
                
            } else {
                Workspace.setDialog("Invalid move");
            }
            
        });
    }
    
}
