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
        setOnMouseClicked(e->{
            Card discard = Workspace.getDiscard();
            if (discard instanceof WildCard){
                
            }
        });
    }
    
}
