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

    protected boolean playCard;

    public Card(Image image, int height, int width) {
        setImage(image);
        setFitHeight(height);
        setFitWidth(width);
        setOnMouseEntered(e -> {
            setEffect(Data.getHighlightedEffect());
        });
        setOnMouseExited(e -> {
            setEffect(null);
        });

//        setOnMouseClicked(e -> {
//
//            if (e.getSource() instanceof Card) {
//                Card selected = (Card) e.getSource();
//                if (selected.getType().equals("WILD")) {
//                    playCard = true;
//                } else if (selected.getColor().equals(getColor())) {
//                    playCard = true;
//                } else if (selected.getType().equals(getType())) {
//                    if (selected.getType().equals("NUMERIC")) {
//                        if (selected.getNumber() == getNumber()) {
//                            playCard = true;
//                        } else {
//                            playCard = false;
//                        }
//                    } else {
//                        playCard = true;
//                    }
//                } else {
//                    playCard = false;
//                }
//            }
//        });
    }

    public void playCard() {
        Workspace.getDiscardedCards().add(Workspace.getDiscard());
        Workspace.setDiscard(this);
        Workspace.getCenterpile().getChildren().clear();
        Workspace.getCenterpile().getChildren().add(this);

        Workspace.setTurn("OPPONENT");
        Workspace.decrementPlayerHandSize();
    }

}
