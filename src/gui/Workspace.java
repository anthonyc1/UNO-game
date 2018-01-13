/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    BorderPane canvas;
    StackPane root;

    Button startGameButton;
    Button drawCard;
    Button callUno;

    boolean gameOver = false;
    String winner;

    HBox opponent;
    HBox player;
    HBox opponentHand;
    HBox playerHand;
    protected static int playerHandSize = 0;
    protected static int opponentHandSize = 0;

    protected static HBox centerpile;
    VBox deckpile;
    protected Label deckName;

    VBox info;
    Label playerName;
    Label computerName;

    static VBox dialog;
    protected static Label dialogText;

    File cardback;

    Card back;
    Card deck;
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

    public void initGUI(Stage primaryStage) {
        canvas = new BorderPane();
        canvas.setMinSize(1000, 600);
        scene = new Scene(canvas);

        file = new File("images/green.jpg");
        image = new Image("file:" + file.getPath());
        //ImageView imageView = new ImageView(image);
        double height = canvas.getMinHeight();
        double width = canvas.getMinWidth();
        BackgroundSize size = new BackgroundSize(width, height, false, false, false, false);
        BackgroundImage bkgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        Background bkg = new Background(bkgImage);

        canvas.setBackground(bkg);
        //pane.setStyle("-fx-background-color: #" + 333333);

        //Load the stylesheet
        scene.getStylesheets().add("css/style.css");

//		pane.setRight(new CustomPane("Right"));
//		pane.setLeft(new CustomPane("Left"));
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

        opponent = new HBox();
        opponent.setMinSize(700, 100);
        opponent.setSpacing(100);
        opponent.setAlignment(Pos.CENTER);

        player = new HBox();
        player.setMinSize(700, 100);
        player.setSpacing(100);
        player.setAlignment(Pos.CENTER);

        opponentHand = new HBox();
        opponentHand.setMinSize(500, 100);
        opponentHand.setSpacing(20);
        opponentHand.setAlignment(Pos.CENTER);

        playerHand = new HBox();
        playerHand.setMinSize(500, 100);
        playerHand.setSpacing(20);
        playerHand.setAlignment(Pos.CENTER);

        drawCard = new Button("Draw Card");
        drawCard.getStyleClass().add("drawcard");

        callUno = new Button("Call Uno");
        callUno.getStyleClass().add("calluno");

        player.getChildren().addAll(callUno, playerHand, drawCard);
        opponent.getChildren().addAll(opponentHand);

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
        info.setMinSize(100, 300);
        info.setSpacing(60);
        info.setAlignment(Pos.CENTER);

        playerName = new Label("Player");
        playerName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        playerName.setTextFill(Color.WHITE);

        computerName = new Label("Computer");
        computerName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        computerName.setTextFill(Color.WHITE);

        dialog = new VBox();
        dialog.setMinSize(100, 200);
        dialog.setAlignment(Pos.CENTER);
        setDialogText(new Label("Let's play!"));
        getDialogText().setFont(Font.font("Arial", FontWeight.BOLD, 12));
        getDialogText().setTextFill(Color.WHITE);

        dialog.getChildren().add(getDialogText());
        dialog.getStyleClass().add("dialog");

        //callUno.setOnAction(e -> {computerName.setText("Hi");});
        info.getChildren().addAll(computerName, dialog, playerName);

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

        cardback = new File("resources/back.jpg");
        image = new Image("file:" + cardback.getPath());

        deck = new Card(image, 75, 50);
        setDiscard(new Card(image, 75, 50));

        getCenterpile().getChildren().addAll(getDiscard());

        deckpile.getChildren().addAll(deck, getDeckName());
        startGame();
    }

    private void startGame() {
        back = new Card(image, 75, 50);
//        Card back2 = new Card(image, 75, 50);
//        Card back3 = new Card(image, 75, 50);
//        Card back4 = new Card(image, 75, 50);
//        Card back5 = new Card(image, 75, 50);
//        Card back6 = new Card(image, 75, 50);
//        Card back7 = new Card(image, 75, 50);

//        Bounds boundsInScene = back.localToScene(back.getBoundsInLocal());
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
        setDiscard(getDeckOfCards().remove(0));
        getCenterpile().getChildren().add(getDiscard());

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                playerHand.getChildren().add(getDeckOfCards().remove(0));
                playerHandSize++;
            } else {
                getOpponentCards().add(getDeckOfCards().remove(0));
                opponentHand.getChildren().add(Data.getDeckOfCardbacks().remove(0));
                opponentHandSize++;
            }
        }

        int chooseRandom = (int) (100 * Math.random());
        if (chooseRandom % 2 == 0) {
            setTurn("PLAYER");
        } else {
            setTurn("OPPONENT");
        }

        updateDeckText();
        System.out.print(turn);

        //START GAME
//        while (!gameOver) {
//            if (Workspace.getTurn().equals("OPPONENT")) {
//                
//            } else {
//                
//            }
//            isGameOver();
//        }

    }

    public void addCardToHand() {

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
    public Label getDeckName() {
        return deckName;
    }

    /**
     * @param deckName the deckName to set
     */
    public void setDeckName(Label deckName) {
        this.deckName = deckName;
    }

    /**
     * @return the deckOfCards
     */
    public static ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public void updateDeckText() {
        getDeckName().setText("Deck: " + getDeckOfCards().size());
        setDeckName(getDeckName());
        deckpile.getChildren().clear();
        deckpile.getChildren().addAll(deck, getDeckName());
    }

    public void isGameOver() {
        if (playerHandSize == 0) {
            winner = "PLAYER";
            gameOver = true;
        } else if (opponentHandSize == 0) {
            winner = "OPPONENT";
            gameOver = true;
        } else {
            if (deckOfCards.isEmpty()) {
                getDiscardedCards().add(Workspace.getDiscard());
                Data.shuffleDeck(discardedCards);
                deckOfCards = discardedCards;
                updateDeckText();

                getCenterpile().getChildren().clear();
                //setDiscard(getDeckOfCards().remove(0));
                //getCenterpile().getChildren().add(getDiscard());
            }
            gameOver = false;
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

    /**
     * @return the playerHandSize
     */
    public static void decrementPlayerHandSize() {
        playerHandSize--;
    }

    /**
     * @return the opponentHandSize
     */
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
}
