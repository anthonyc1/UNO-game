/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.WildCardDialog.getWildCardDialog;
import static gui.Workspace.playerHand;
import static gui.Workspace.setDialog;
import static gui.Workspace.turn;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author anthonychan
 */
public abstract class Card extends ImageView {

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

    public abstract boolean canPlay();

    public void playCardByOpponent() {
        try {
            //        if (this instanceof WildCard) {
//            String color = getWildCardDialog().getColor();
//            ((WildCard) this).setColor(color);
//        }
            Thread.sleep(1000);
            System.out.println("opponent plays");
        } catch (InterruptedException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this instanceof WildCard) {
            int chooseColor = (int) (100 * Math.random());
            if (chooseColor < 25) {
                ((WildCard) this).setColor("blue");
            } else if (chooseColor < 50) {
                ((WildCard) this).setColor("red");
            } else if (chooseColor < 75) {
                ((WildCard) this).setColor("yellow");
            } else if (chooseColor < 100) {
                ((WildCard) this).setColor("green");
            }
            Workspace.setDialog("Wild "+ ((WildCard) this).getColor());
        } else if (this instanceof PlusOne){
            playerHand.getChildren().add(Workspace.getDeckOfCards().remove(0));
            Workspace.playerHandSize++;
        } else if (this instanceof PlusTwo){
            playerHand.getChildren().add(Workspace.getDeckOfCards().remove(0));
            playerHand.getChildren().add(Workspace.getDeckOfCards().remove(0));
            Workspace.playerHandSize+=2;
        }
//            String color = getWildCardDialog().getColor();
//            ((WildCard) this).setColor(color);
        // add current discard card to list of discarded cards
        Workspace.getDiscardedCards().add(Workspace.getDiscard());
        // update the discard card in scene to newly played card
        Workspace.setDiscard(this);
        Workspace.getCenterpile().getChildren().clear();
        Workspace.getCenterpile().getChildren().add(this);
        Workspace.setCenterpile(Workspace.getCenterpile());

        Workspace.setTurn("PLAYER");
        Workspace.decrementOpponentHandSize();
        setDialog(turn + "'s turn");
        //Workspace.isGameOver();
    }

    public void playCardByPlayer() {
        if (this instanceof WildCard) {
            String color = getWildCardDialog().getColor();
            ((WildCard) this).setColor(color);
        }
        // add current discard card to list of discarded cards
        Workspace.getDiscardedCards().add(Workspace.getDiscard());
        // update the discard card in scene to newly played card
        Workspace.setDiscard(this);
        Workspace.getCenterpile().getChildren().clear();
        Workspace.getCenterpile().getChildren().add(this);
        Workspace.setCenterpile(Workspace.getCenterpile());

        Workspace.setTurn("OPPONENT");
        Workspace.decrementPlayerHandSize();
        setDialog(turn + "'s turn");
        System.out.println("player plays");
        //Workspace.isGameOver();

        if (Workspace.getTurn().equals("OPPONENT")) {
            Workspace.opponentToDraw = true;
            for (Node card : Workspace.opponentCards) {
                if (((Card) card).canPlay()) {
                    Workspace.opponentToDraw = false;
                    ((Card) card).playCardByOpponent();
                    break;
                }
            }
            if (Workspace.opponentToDraw) {
                setDialog("OPPONENT draws");
                Workspace.opponentCards.add(Workspace.getDeckOfCards().remove(0));
                Workspace.opponentHand.getChildren().add(Data.getDeckOfCardbacks().remove(0));
                Workspace.opponentHandSize++;
            }
            Workspace.setTurn("PLAYER");
            //Workspace.isGameOver();
        }
    }

}
