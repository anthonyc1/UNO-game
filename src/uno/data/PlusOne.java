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
import static uno.gui.Workspace.isDeckEmpty;
import static uno.gui.Workspace.setOpponentHandSize;
import static uno.gui.Workspace.updateDeckText;
import javafx.scene.image.Image;

/**
 *
 * @author anthonychan
 */
public class PlusOne extends Card {

    protected String color;

    public PlusOne(Image image, int height, int width, String color) {
        super(image, height, width);
        this.color = color;
        setOnMouseClicked(e -> {
            if (Workspace.getTurn().equals("PLAYER") && canPlay()) {
                getOpponentHand().getChildren().add(Data.getDeckOfCardbacks().remove(0));
                getOpponentCards().add(Workspace.getDeckOfCards().remove(0));
                isDeckEmpty();
                setOpponentHandSize(getOpponentHandSize() + 1);
                updateDeckText();
                playCardByPlayer();
            } else {
                Workspace.setDialog("Invalid move");
            }

        });
    }

    @Override
    public boolean canPlay() {
        Card discard = Workspace.getDiscard();
        if (discard instanceof PlusOne) {
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
    
    @Override
    public String toString(){
        return color + " plus one";
    }

}
