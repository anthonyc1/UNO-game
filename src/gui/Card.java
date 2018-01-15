/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.WildCardDialog.getWildCardDialog;
import static gui.Workspace.playerHand;
import static gui.Workspace.setDialog;
import static gui.Workspace.setOpponentDialog;
import static gui.Workspace.setPlayerDialog;
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

    protected boolean goAgain;

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
        goAgain = false;
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
//            Workspace.setDialog("Wild " + ((WildCard) this).getColor());
        } else if (this instanceof PlusOne) {
            playerHand.getChildren().add(Workspace.getDeckOfCards().remove(0));
            Workspace.isDeckEmpty();
            Workspace.playerHandSize++;
            Workspace.updateDeckText();
            goAgain = true;
        } else if (this instanceof PlusTwo) {
            playerHand.getChildren().add(Workspace.getDeckOfCards().remove(0));
            Workspace.isDeckEmpty();
            playerHand.getChildren().add(Workspace.getDeckOfCards().remove(0));
            Workspace.isDeckEmpty();
            Workspace.playerHandSize += 2;
            Workspace.updateDeckText();
            goAgain = true;
        }
        // add current discard card to list of discarded cards
            Workspace.getDiscardedCards().add(Workspace.getDiscard());
            // update the discard card in scene to newly played card
            Workspace.setDiscard(this);
            Workspace.getCenterpile().getChildren().clear();
            Workspace.getCenterpile().getChildren().add(Workspace.getDiscard());
            Workspace.setCenterpile(Workspace.getCenterpile());

            Workspace.opponentCards.remove(this);
            Data.getDeckOfCardbacks().add((Card) Workspace.opponentHand.getChildren().remove(0));

            Workspace.decrementOpponentHandSize();
            Workspace.setTurn("PLAYER");
            setOpponentDialog("LAST PLAYED:\n" + this.toString());
            setDialog(turn + "'s turn");
            Workspace.isGameOver(Workspace.getPrimaryStage());
            Workspace.callUNO();
            
        if (goAgain) {
            Workspace.setTurn("OPPONENT");
            setDialog(turn + " goes again");
            opponentPlayLogic();
        }
    }

    public void playCardByPlayer() {
        goAgain = false;
        if (this instanceof WildCard) {
            String color = getWildCardDialog().getColor();
            ((WildCard) this).setColor(color);
        }else if (this instanceof PlusOne) {
            goAgain = true;
        } else if (this instanceof PlusTwo) {
            goAgain = true;
        }
        // add current discard card to list of discarded cards
        Workspace.getDiscardedCards().add(Workspace.getDiscard());
        // update the discard card in scene to newly played card
        Workspace.setDiscard(this);
        Workspace.getCenterpile().getChildren().clear();
        Workspace.getCenterpile().getChildren().add(Workspace.getDiscard());
        Workspace.setCenterpile(Workspace.getCenterpile());

        Workspace.decrementPlayerHandSize();
        Workspace.setTurn("OPPONENT");
        setPlayerDialog("LAST PLAYED:\n" + this.toString());
        setDialog(turn + "'s turn");
        Workspace.isGameOver(Workspace.getPrimaryStage());
        Workspace.callUNO();
        
        if (!goAgain){
            opponentPlayLogic();
        } else {
            Workspace.setTurn("PLAYER");
            setDialog(turn + " goes again");
        }

    }
    
    public void opponentPlayLogic(){
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
                setOpponentDialog("OPPONENT draws");
                Workspace.opponentCards.add(Workspace.getDeckOfCards().remove(0));
                Workspace.opponentHand.getChildren().add(Data.getDeckOfCardbacks().remove(0));
                Workspace.opponentHandSize++;
                Workspace.updateDeckText();
            }
            
            Workspace.setTurn("PLAYER");
            setDialog(turn +"'s turn");
            Workspace.isDeckEmpty();
        }
    }

}
