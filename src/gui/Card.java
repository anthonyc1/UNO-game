/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author anthonychan
 */
public class Card extends ImageView {
    
    public Card(Image image, int height, int width){
        setImage(image);
        setFitHeight(height);
        setFitWidth(width);
        setOnMouseEntered(e -> {
            setEffect(Data.getHighlightedEffect());
        });
        setOnMouseExited(e -> {
            setEffect(null);
        });
    }
}
