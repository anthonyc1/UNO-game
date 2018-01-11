/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

/**
 *
 * @author anthonychan
 */
public class Data {
    static Effect highlightedEffect;
            
    public Data(){
        DropShadow dropShadowEffect = new DropShadow();
        dropShadowEffect.setOffsetX(0.0f);
        dropShadowEffect.setOffsetY(0.0f);
        dropShadowEffect.setSpread(1.0);
        dropShadowEffect.setColor(Color.YELLOW);
        dropShadowEffect.setBlurType(BlurType.GAUSSIAN);
        dropShadowEffect.setRadius(5);
        highlightedEffect = dropShadowEffect;
    }
    
    public void highlightCard(Node shape){
        if (shape instanceof Card){
            ((Card)shape).setEffect(highlightedEffect);
        }
    }
    
    public void unhighlightCard(Node shape){
        if (shape instanceof Card){
            ((Card)shape).setEffect(null);
        }
    }

    public static Effect getHighlightedEffect(){
        return highlightedEffect;
    }
}
