/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno.gui;

import uno.data.Data;
import uno.data.WildCard;
import uno.data.Card;
import uno.data.BackCard;
import uno.data.PlusTwo;
import static uno.gui.GameResultDialog.getGameResultDialog;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author anthonychan
 */
public class Workspace {

    Data dataManager;
    static protected Stage primaryStage;
    
    protected static final String userHome = System.getProperty("user.home");

    BorderPane canvas;
    StackPane root;

    Button startGameButton;
    Button drawCard;
    Button callUno;

    static boolean gameOver = false;
    static String winner;

    HBox opponent;
    HBox player;
    static HBox opponentHand;
    static HBox playerHand;
    static int playerHandSize;
    static int opponentHandSize;

    static boolean playerCalledUno = false;
    static boolean opponentCalledUno = false;
    static boolean playerToDraw;
    static boolean opponentToDraw;

    static boolean playerPlayed = false;

    protected static HBox centerpile;
    static VBox deckpile;
    static protected Label deckName;

    VBox info;
    Label playerName;
    Label computerName;

    static VBox dialog;
    protected static VBox playerDialog;
    protected static VBox opponentDialog;
    protected static Label dialogText;
    protected static Label playerDialogText;
    protected static Label opponentDialogText;

    File cardback;

    Card back;
    static Card deck;
    protected static Card discard;

    protected static ArrayList<Card> deckOfCards;
    protected static ArrayList<Card> discardedCards;
    protected static ArrayList<Card> opponentCards;

    Scene scene;

    protected static String turn;

    File file;
    Image image;

    public Workspace(Stage primaryStage) {
        initGUI(primaryStage);
    }

    private void initGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;

        canvas = new BorderPane();
        canvas.setMinSize(1000, 600);
        scene = new Scene(canvas);

        file = new File(userHome + "/Desktop/multiplayerUNO/images/green.jpg");
        image = new Image("file:" + file.getPath());
        //ImageView imageView = new ImageView(image);
        double height = canvas.getMinHeight();
        double width = canvas.getMinWidth();
        BackgroundSize size = new BackgroundSize(width, height, false, false, false, false);
        BackgroundImage bkgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        Background bkg = new Background(bkgImage);

        canvas.setBackground(bkg);

        //Load the stylesheet
        scene.getStylesheets().add("uno/css/style.css");

        // Event handling
        root = new StackPane();
        root.setMinSize(700, 300);

        startGameButton = new Button();
        startGameButton.setText("Play UNO!");
        startGameButton.setOnAction(e -> {
            root.getChildren().remove(startGameButton);
            setupGame(canvas);
        });

        root.getChildren().add(startGameButton);
        canvas.setCenter(root);

        // Add CSS to elements
        //pane.getStyleClass().add("field");
        primaryStage.setTitle("UNO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupGame(BorderPane pane) {

        dataManager = new Data();
        deckOfCards = Data.getDeck();
        opponentCards = new ArrayList<>();
        discardedCards = new ArrayList<>();

        setPlayerHandSize(0);
        setOpponentHandSize(0);

        back = new BackCard(image, 75, 50);

        opponent = new HBox();
        opponent.setMinSize(700, 100);
        opponent.setSpacing(100);
        opponent.setAlignment(Pos.CENTER);

        player = new HBox();
        player.setMinSize(700, 100);
        player.setSpacing(100);
        player.setAlignment(Pos.CENTER);

        setOpponentHand(new HBox());
        getOpponentHand().setMinSize(500, 100);
        getOpponentHand().setSpacing(20);
        getOpponentHand().setAlignment(Pos.CENTER);

        setPlayerHand(new HBox());
        getPlayerHand().setMinSize(500, 100);
        getPlayerHand().setSpacing(20);
        getPlayerHand().setAlignment(Pos.CENTER);

        drawCard = new Button("Draw Card");
        drawCard.getStyleClass().add("drawcard");
        drawCard.setOnMouseClicked(e -> {
            if (turn.equals("PLAYER")) {
                setPlayerToDraw(true);
                for (Node card : getPlayerHand().getChildren()) {
                    if (((Card) card).canPlay()) {
                        setPlayerToDraw(false);
                        break;
                    }
                }
                if (isPlayerToDraw()) {
                    setPlayerDialog("PLAYER draws");
                    setDialog("OPPONENT goes");
                    getPlayerHand().getChildren().add(getDeckOfCards().remove(0));
                    setPlayerHandSize(getPlayerHandSize() + 1);
                    updateDeckText();
                    Workspace.isDeckEmpty();

                    setTurn("OPPONENT");
                    setDialog(turn + "'s turn");

                    if (Workspace.getTurn().equals("OPPONENT")) {
                        Workspace.setOpponentToDraw(true);
                        for (Node card : Workspace.opponentCards) {
                            if (((Card) card).canPlay()) {
                                Workspace.setOpponentToDraw(false);
                                ((Card) card).playCardByOpponent();
                                break;
                            }
                        }
                        if (Workspace.isOpponentToDraw()) {
                            setOpponentDialog("OPPONENT draws");
                            Workspace.opponentCards.add(Workspace.getDeckOfCards().remove(0));
                            Workspace.getOpponentHand().getChildren().add(Data.getDeckOfCardbacks().remove(0));
                            Workspace.opponentHandSize++;
                            updateDeckText();
                        }
                        Workspace.setTurn("PLAYER");
                        setDialog(turn + "'s turn");
                        Workspace.isDeckEmpty();
                    }

                } else {
                    setDialog("Must play card");
                }
            }
        });
        drawCard.setOnMouseEntered(e -> {
            drawCard.setEffect(Data.getHighlightedEffect());
        });
        drawCard.setOnMouseExited(e -> {
            drawCard.setEffect(null);
        });

        callUno = new Button("Call Uno");
        callUno.getStyleClass().add("calluno");
        callUno.setOnMouseClicked(e -> {
            if (getPlayerHandSize() == 2 && !playerToDraw()) {
                setPlayerDialog("PLAYER calls UNO");
                playerCalledUno = true;
            } else if (getOpponentHandSize() == 1 && !opponentCalledUno) {
                setOpponentDialog("Fails to call UNO\nOPPONENT draws two");
                PlusTwo.opponentDrawTwo();
            } else {
                setDialog("Invalid call");
            }
        });
        callUno.setOnMouseEntered(e -> {
            callUno.setEffect(Data.getHighlightedEffect2());
        });
        callUno.setOnMouseExited(e -> {
            callUno.setEffect(null);
        });

        player.getChildren().addAll(callUno, getPlayerHand(), drawCard);
        opponent.getChildren().addAll(getOpponentHand());

        setCenterpile(new HBox());
        getCenterpile().setMinSize(300, 100);
        getCenterpile().setSpacing(40);
        getCenterpile().setAlignment(Pos.CENTER);

        deckpile = new VBox();
        deckpile.setMinSize(100, 300);
        deckpile.setAlignment(Pos.CENTER);

        setDeckName(new Label("Deck: " + getDeckOfCards().size()));
        getDeckName().setFont(Font.font("Arial", FontWeight.BOLD, 16));
        getDeckName().setTextFill(Color.WHITE);

        info = new VBox();
        info.setMinSize(100, 400);
        info.setSpacing(30);
        info.setAlignment(Pos.CENTER);

        String name = GameSettingDialog.getName();
        if (name.isEmpty()) {
            playerName = new Label("Player");
        } else {
            playerName = new Label(name);
        }
        playerName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        playerName.setTextFill(Color.WHITE);

        computerName = new Label("Computer");
        computerName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        computerName.setTextFill(Color.WHITE);

        dialog = new VBox();
        dialog.setMinSize(100, 60);
        dialog.setAlignment(Pos.CENTER);
        setDialogText(new Label("Let's play!"));
        getDialogText().setFont(Font.font("Arial", FontWeight.BOLD, 14));
        getDialogText().setTextFill(Color.YELLOW);

        playerDialog = new VBox();
        playerDialog.setMinSize(100, 60);
        playerDialog.setAlignment(Pos.CENTER);
        setPlayerDialogText(new Label("Let's play!"));
        getPlayerDialogText().setFont(Font.font("Arial", 12));
        getPlayerDialogText().setTextFill(Color.WHITE);

        opponentDialog = new VBox();
        opponentDialog.setMinSize(100, 60);
        opponentDialog.setAlignment(Pos.CENTER);
        setOpponentDialogText(new Label("Let's play!"));
        getOpponentDialogText().setFont(Font.font("Arial", 12));
        getOpponentDialogText().setTextFill(Color.WHITE);

        dialog.getChildren().add(getDialogText());
        playerDialog.getChildren().add(getPlayerDialogText());
        opponentDialog.getChildren().add(getOpponentDialogText());
        dialog.getStyleClass().add("dialog");
        playerDialog.getStyleClass().add("dialog");
        opponentDialog.getStyleClass().add("dialog");

        //callUno.setOnAction(e -> {computerName.setText("Hi");});
        info.getChildren().addAll(computerName, opponentDialog, dialog, playerDialog, playerName);

        pane.setTop(opponent);
        pane.setBottom(player);
        pane.setCenter(getCenterpile());
        pane.setRight(deckpile);
        pane.setLeft(info);

        opponent.getStyleClass().add("border");
        opponent.setSpacing(40);
        opponent.setAlignment(Pos.CENTER);

        player.getStyleClass().add("border");
        player.setSpacing(40);
        player.setAlignment(Pos.CENTER);
        //root.getStyleClass().add("field");

        cardback = new File(userHome + "/Desktop/multiplayerUNO/resources/back.jpg");
        image = new Image("file:" + cardback.getPath());

        deck = new BackCard(image, 75, 50);
        setDiscard(new BackCard(image, 75, 50));

        getCenterpile().getChildren().addAll(getDiscard());

        deckpile.getChildren().addAll(deck, getDeckName());
        startGame();
    }

    private void startGame() {

//
//        TranslateTransition tt = new TranslateTransition(Duration.millis(700), back);
//        tt.setByX(boundsInScene.getMaxX());
//        //tt.setByY(discard.getY());
//
//        tt.play();
//        
//        System.out.println(boundsInScene.getMaxX());
//        System.out.println(boundsInScene.getMaxY());
//        opponentHand.getChildren().addAll(back, back2, back3, back4, back5, back6, back7);
        Data.shuffleDeck(getDeckOfCards());

        getCenterpile().getChildren().clear();
        Card firstCard = getDeckOfCards().remove(0);
        if (firstCard instanceof WildCard) {
            int chooseColor = (int) (100 * Math.random());
            if (chooseColor < 25) {
                ((WildCard) firstCard).setColor("blue");
            } else if (chooseColor < 50) {
                ((WildCard) firstCard).setColor("red");
            } else if (chooseColor < 75) {
                ((WildCard) firstCard).setColor("yellow");
            } else if (chooseColor < 100) {
                ((WildCard) firstCard).setColor("green");
            }
            setDialog("First card is\n" + ((WildCard) firstCard).toString());
        }
        setDiscard(firstCard);
        getCenterpile().getChildren().add(getDiscard());

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                getPlayerHand().getChildren().add(getDeckOfCards().remove(0));
                setPlayerHandSize(getPlayerHandSize() + 1);
            } else {
                getOpponentCards().add(getDeckOfCards().remove(0));
                getOpponentHand().getChildren().add(Data.getDeckOfCardbacks().remove(0));
                opponentHandSize++;
            }
        }

        int chooseRandom = (int) (100 * Math.random());
        if (chooseRandom % 2 == 0) {
            setTurn("PLAYER");
            setPlayerDialog(turn + " goes first");
        } else {
            setTurn("OPPONENT");
            setOpponentDialog(turn + " goes first");
        }

        updateDeckText();

        //START GAME
//        do {
        if (Workspace.getTurn().equals("OPPONENT")) {
            Workspace.setOpponentToDraw(true);
            for (Node card : Workspace.opponentCards) {
                if (((Card) card).canPlay()) {
                    Workspace.setOpponentToDraw(false);
                    ((Card) card).playCardByOpponent();
                    break;
                }
            }
            if (Workspace.isOpponentToDraw()) {
                setOpponentDialog("OPPONENT draws");
                Workspace.opponentCards.add(Workspace.getDeckOfCards().remove(0));
                Workspace.getOpponentHand().getChildren().add(Data.getDeckOfCardbacks().remove(0));
                Workspace.opponentHandSize++;
            }
            Workspace.setTurn("PLAYER");

        }
    }

    /**
     * @return the discard
     */
    public static Card getDiscard() {
        return discard;
    }

    /**
     * @param discard the discard to set
     */
    public static void setDiscard(Card discard) {
        Workspace.discard = discard;
    }

    /**
     * @return the deckName
     */
    public static Label getDeckName() {
        return deckName;
    }

    /**
     * @param deckName the deckName to set
     */
    public static void setDeckName(Label deckName) {
        Workspace.deckName = deckName;
    }

    /**
     * @return the deckOfCards
     */
    public static ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public static void updateDeckText() {
        getDeckName().setText("Deck: " + getDeckOfCards().size());
        setDeckName(getDeckName());
        deckpile.getChildren().clear();
        deckpile.getChildren().addAll(deck, getDeckName());
    }

    public static void isGameOver(Stage primaryStage) {
        if (getPlayerHandSize() == 0) {
            winner = "PLAYER";
            gameOver = true;
            getGameResultDialog(primaryStage, winner);
        } else if (getOpponentHandSize() == 0) {
            winner = "OPPONENT";
            gameOver = true;
            getGameResultDialog(primaryStage, winner);
        } else {
            gameOver = false;
        }
    }

    public boolean playerToDraw() {
        setPlayerToDraw(true);
        for (Node card : getPlayerHand().getChildren()) {
            if (((Card) card).canPlay()) {
                setPlayerToDraw(false);
                break;
            }
        }
        return isPlayerToDraw();
    }

    // determine if you need to call uno
    public static void callUNO() {
        int fiftyfifty = (int) (100 * Math.random());
        opponentCalledUno = false;
        if (getPlayerHandSize() == 1 && !playerCalledUno) {
            if (fiftyfifty >= 50) {
                setPlayerDialog("Fails to call UNO\nPLAYER draws two");
                PlusTwo.playerDrawTwo();
            }
        } else if (getPlayerHandSize() == 1 && playerCalledUno) {
            playerCalledUno = false;
        }
        if (getOpponentHandSize() == 1) {
            if (fiftyfifty < 50) {
                opponentCalledUno = true;
                setOpponentDialog("OPPONENT calls UNO");
            }

        }
    }

    public static void isDeckEmpty() {
        if (deckOfCards.isEmpty()) {
            Data.shuffleDeck(discardedCards);
            deckOfCards.addAll(discardedCards);
            discardedCards.clear();
            updateDeckText();
        }
    }

    /**
     * @return the centerpile
     */
    public static HBox getCenterpile() {
        return centerpile;
    }

    /**
     * @param centerpile the centerpile to set
     */
    public static void setCenterpile(HBox centerpile) {
        Workspace.centerpile = centerpile;
    }

    /**
     * @return the turn
     */
    public static String getTurn() {
        return turn;
    }

    /**
     * @param turn the turn to set
     */
    public static void setTurn(String turn) {
        Workspace.turn = turn;
    }

    /**
     * @return the opponentCards
     */
    public static ArrayList<Card> getOpponentCards() {
        return opponentCards;
    }

    /**
     * @param aOpponentCards the opponentCards to set
     */
    public static void setOpponentCards(ArrayList<Card> aOpponentCards) {
        opponentCards = aOpponentCards;
    }

    public static void decrementPlayerHandSize() {
        setPlayerHandSize(getPlayerHandSize() - 1);
    }

    public static void decrementOpponentHandSize() {
        opponentHandSize--;
    }

    /**
     * @return the discardedCards
     */
    public static ArrayList<Card> getDiscardedCards() {
        return discardedCards;
    }

    /**
     * @return the dialogText
     */
    public static Label getDialogText() {
        return dialogText;
    }

    /**
     * @param dialogText the dialogText to set
     */
    public static void setDialogText(Label dialogText) {
        Workspace.dialogText = dialogText;
    }

    public static void setDialog(String dialogText) {
        getDialogText().setText(dialogText);
        setDialogText(getDialogText());
        dialog.getChildren().clear();
        dialog.getChildren().add(getDialogText());
    }

    public static void setPlayerDialog(String dialogText) {
        getPlayerDialogText().setText(dialogText);
        setPlayerDialogText(getPlayerDialogText());
        playerDialog.getChildren().clear();
        playerDialog.getChildren().add(getPlayerDialogText());
    }

    public static void setOpponentDialog(String dialogText) {
        getOpponentDialogText().setText(dialogText);
        setOpponentDialogText(getOpponentDialogText());
        opponentDialog.getChildren().clear();
        opponentDialog.getChildren().add(getOpponentDialogText());
    }

    /**
     * @return the primaryStage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @return the playerDialogText
     */
    public static Label getPlayerDialogText() {
        return playerDialogText;
    }

    /**
     * @param aPlayerDialogText the playerDialogText to set
     */
    public static void setPlayerDialogText(Label aPlayerDialogText) {
        playerDialogText = aPlayerDialogText;
    }

    /**
     * @return the opponentDialogText
     */
    public static Label getOpponentDialogText() {
        return opponentDialogText;
    }

    /**
     * @param aOpponentDialogText the opponentDialogText to set
     */
    public static void setOpponentDialogText(Label aOpponentDialogText) {
        opponentDialogText = aOpponentDialogText;
    }

    /**
     * @return the opponentHand
     */
    public static HBox getOpponentHand() {
        return opponentHand;
    }

    /**
     * @return the playerHand
     */
    public static HBox getPlayerHand() {
        return playerHand;
    }

    /**
     * @return the playerHandSize
     */
    public static int getPlayerHandSize() {
        return playerHandSize;
    }

    /**
     * @return the opponentHandSize
     */
    public static int getOpponentHandSize() {
        return opponentHandSize;
    }

    /**
     * @param aOpponentHand the opponentHand to set
     */
    public static void setOpponentHand(HBox aOpponentHand) {
        opponentHand = aOpponentHand;
    }

    /**
     * @param aPlayerHand the playerHand to set
     */
    public static void setPlayerHand(HBox aPlayerHand) {
        playerHand = aPlayerHand;
    }

    /**
     * @param aPlayerHandSize the playerHandSize to set
     */
    public static void setPlayerHandSize(int aPlayerHandSize) {
        playerHandSize = aPlayerHandSize;
    }

    /**
     * @param aOpponentHandSize the opponentHandSize to set
     */
    public static void setOpponentHandSize(int aOpponentHandSize) {
        opponentHandSize = aOpponentHandSize;
    }

    /**
     * @return the playerToDraw
     */
    public static boolean isPlayerToDraw() {
        return playerToDraw;
    }

    /**
     * @param aPlayerToDraw the playerToDraw to set
     */
    public static void setPlayerToDraw(boolean aPlayerToDraw) {
        playerToDraw = aPlayerToDraw;
    }

    /**
     * @return the opponentToDraw
     */
    public static boolean isOpponentToDraw() {
        return opponentToDraw;
    }

    /**
     * @param aOpponentToDraw the opponentToDraw to set
     */
    public static void setOpponentToDraw(boolean aOpponentToDraw) {
        opponentToDraw = aOpponentToDraw;
    }

    /**
     * @return the userHome
     */
    public static String getUserHome() {
        return userHome;
    }

}
