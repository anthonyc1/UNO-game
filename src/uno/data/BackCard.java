/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno.data;

import javafx.scene.image.Image;

/**
 *
 * @author anthonychan
 */
public class BackCard extends Card{

    public BackCard(Image image, int height, int width) {
        super(image, height, width);
    }

    @Override
    public boolean canPlay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
