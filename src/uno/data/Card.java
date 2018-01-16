/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno.data;

import uno.gui.Workspace;
import static uno.gui.WildCardDialog.getWildCardDialog;
import static uno.gui.Workspace.callUNO;
import static uno.gui.Workspace.decrementOpponentHandSize;
import static uno.gui.Workspace.decrementPlayerHandSize;
import static uno.gui.Workspace.getCenterpile;
import static uno.gui.Workspace.getDiscardedCards;
import static uno.gui.Workspace.getOpponentCards;
import static uno.gui.Workspace.getOpponentHand;
import static uno.gui.Workspace.getPlayerHand;
import static uno.gui.Workspace.getPlayerHandSize;
import static uno.gui.Workspace.setDialog;
import static uno.gui.Workspace.setOpponentDialog;
import static uno.gui.Workspace.setPlayerDialog;
import static uno.gui.Workspace.getTurn;
import static uno.gui.Workspace.isDeckEmpty;
import static uno.gui.Workspace.isGameOver;
import static uno.gui.Workspace.setCenterpile;
import static uno.gui.Workspace.setDiscard;
import static uno.gui.Workspace.setOpponentHandSize;
import static uno.gui.Workspace.setOpponentToDraw;
import static uno.gui.Workspace.setPlayerHandSize;
import static uno.gui.Workspace.setTurn;
import static uno.gui.Workspace.updateDeckText;
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
            getPlayerHand().getChildren().add(Workspace.getDeckOfCards().remove(0));
            isDeckEmpty();
            setPlayerHandSize(getPlayerHandSize() + 1);
            updateDeckText();
            goAgain = true;
        } else if (this instanceof PlusTwo) {
            getPlayerHand().getChildren().add(Workspace.getDeckOfCards().remove(0));
            isDeckEmpty();
            getPlayerHand().getChildren().add(Workspace.getDeckOfCards().remove(0));
            isDeckEmpty();
            setPlayerHandSize(getPlayerHandSize() + 2);
            updateDeckText();
            goAgain = true;
        }
        // add current discard card to list of discarded cards
            getDiscardedCards().add(Workspace.getDiscard());
            // update the discard card in scene to newly played card
            setDiscard(this);
            getCenterpile().getChildren().clear();
            getCenterpile().getChildren().add(Workspace.getDiscard());
            setCenterpile(Workspace.getCenterpile());

            getOpponentCards().remove(this);
            Data.getDeckOfCardbacks().add((Card) Workspace.getOpponentHand().getChildren().remove(0));

            decrementOpponentHandSize();
            setTurn("PLAYER");
            setOpponentDialog("LAST PLAYED:\n" + this.toString());
            setDialog(getTurn() + "'s turn");
            isGameOver(Workspace.getPrimaryStage());
            callUNO();
            
        if (goAgain) {
            setTurn("OPPONENT");
            setDialog(getTurn() + " goes again");
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
        getDiscardedCards().add(Workspace.getDiscard());
        // update the discard card in scene to newly played card
        setDiscard(this);
        getCenterpile().getChildren().clear();
        getCenterpile().getChildren().add(Workspace.getDiscard());
        setCenterpile(Workspace.getCenterpile());

        decrementPlayerHandSize();
        setTurn("OPPONENT");
        setPlayerDialog("LAST PLAYED:\n" + this.toString());
        setDialog(getTurn() + "'s turn");
        isGameOver(Workspace.getPrimaryStage());
        callUNO();
        
        if (!goAgain){
            opponentPlayLogic();
        } else {
            setTurn("PLAYER");
            setDialog(getTurn() + " goes again");
        }

    }
    
    public void opponentPlayLogic(){
        if (getTurn().equals("OPPONENT")) {
            setOpponentToDraw(true);
            for (Node card : getOpponentCards()) {
                if (((Card) card).canPlay()) {
                    setOpponentToDraw(false);
                    ((Card) card).playCardByOpponent();
                    break;
                }
            }
            if (Workspace.isOpponentToDraw()) {
                setOpponentDialog("OPPONENT draws");
                getOpponentCards().add(Workspace.getDeckOfCards().remove(0));
                getOpponentHand().getChildren().add(Data.getDeckOfCardbacks().remove(0));
                setOpponentHandSize(Workspace.getOpponentHandSize() + 1);
                updateDeckText();
            }
            
            setTurn("PLAYER");
            setDialog(getTurn() +"'s turn");
            isDeckEmpty();
        }
    }

}
