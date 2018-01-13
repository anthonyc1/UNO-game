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
public class PlusTwo extends Card{
    
    protected String color;
    
    public PlusTwo(Image image, int height, int width, String color) {
        super(image, height, width);
        this.color = color;
        setOnMouseClicked(e->{
            Card discard = Workspace.getDiscard();
            if (discard instanceof PlusTwo){
                
            }
        });
    }
    
}
