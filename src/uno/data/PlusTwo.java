/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno.data;

import uno.gui.Workspace;
import static uno.gui.Workspace.getOpponentCards;
import static uno.gui.Workspace.getOpponentHand;
import static uno.gui.Workspace.getOpponentHandSize;
import static uno.gui.Workspace.getPlayerHand;
import static uno.gui.Workspace.isDeckEmpty;
import static uno.gui.Workspace.setDialog;
import static uno.gui.Workspace.setOpponentHandSize;
import static uno.gui.Workspace.setPlayerHandSize;
import static uno.gui.Workspace.updateDeckText;
import javafx.scene.image.Image;

/**
 *
 * @author anthonychan
 */
public class PlusTwo extends Card {

    protected String color;

    public PlusTwo(Image image, int height, int width, String color) {
        super(image, height, width);
        this.color = color;
        setOnMouseClicked(e -> {
            if (Workspace.getTurn().equals("PLAYER") && canPlay()) {
                opponentDrawTwo();
                playCardByPlayer();

            } else {
                setDialog("Invalid move");
            }
        });
    }

    @Override
    public boolean canPlay() {
        Card discard = Workspace.getDiscard();
        if (discard instanceof PlusTwo) {
            return true;
        } else if (discard instanceof NumericCard) {
            if (((NumericCard) discard).color.equals(color)) {
                return true;
            }
        } else if (discard instanceof WildCard) {
            if (((WildCard) discard).getColor().equals(color)) {
                return true;
            }
        }
        return false;
    }

    public static void opponentDrawTwo() {
        getOpponentHand().getChildren().add(Data.getDeckOfCardbacks().remove(0));
        getOpponentHand().getChildren().add(Data.getDeckOfCardbacks().remove(0));
        getOpponentCards().add(Workspace.getDeckOfCards().remove(0));
        isDeckEmpty();
        getOpponentCards().add(Workspace.getDeckOfCards().remove(0));
        isDeckEmpty();
        setOpponentHandSize(getOpponentHandSize() + 2);
        updateDeckText();
    }
    
    public static void playerDrawTwo() {
        getPlayerHand().getChildren().add(Workspace.getDeckOfCards().remove(0));
        isDeckEmpty();
        getPlayerHand().getChildren().add(Workspace.getDeckOfCards().remove(0));
        isDeckEmpty();
        setPlayerHandSize(Workspace.getPlayerHandSize() + 2);
        updateDeckText();
    }

    @Override
    public String toString() {
        return color + " plus two";
    }

}
