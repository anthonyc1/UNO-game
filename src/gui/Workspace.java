/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
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

/**
 *
 * @author anthonychan
 */
public class Workspace {
    
    BorderPane canvas;
    StackPane root;
    
    Button startGameButton;
    Button drawCard;
    Button callUno;
    
    HBox opponent;
    HBox player;
    HBox opponentHand;
    HBox playerHand;
    
    Scene scene;
    
    File file;
    Image image;
    
    public Workspace(Stage primaryStage){
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
            startGame(primaryStage, canvas, root);
        });

        root.getChildren().add(startGameButton);
        canvas.setCenter(root);

        // Add CSS to elements
        //pane.getStyleClass().add("field");
        primaryStage.setTitle("UNO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startGame(Stage primaryStage, BorderPane pane, StackPane root) {
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

        HBox centerpile = new HBox();
        centerpile.setMinSize(300, 100);
        centerpile.setSpacing(40);
        centerpile.setAlignment(Pos.CENTER);

        VBox deckpile = new VBox();
        deckpile.setMinSize(100, 300);
        deckpile.setAlignment(Pos.CENTER);

        Label deckName = new Label("Deck");
        deckName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        deckName.setTextFill(Color.WHITE);

        VBox info = new VBox();
        info.setMinSize(100, 300);
        info.setSpacing(60);
        info.setAlignment(Pos.CENTER);

        Label playerName = new Label("Player");
        playerName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        playerName.setTextFill(Color.WHITE);

        Label computerName = new Label("Computer");
        computerName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        computerName.setTextFill(Color.WHITE);

        VBox dialog = new VBox();
        dialog.setMinSize(100, 200);
        dialog.setAlignment(Pos.CENTER);
        Label dialogText = new Label("Let's play!");
        dialogText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        dialogText.setTextFill(Color.WHITE);

        dialog.getChildren().add(dialogText);
        dialog.getStyleClass().add("border");

        //callUno.setOnAction(e -> {computerName.setText("Hi");});
        info.getChildren().addAll(computerName, dialog, playerName);

        pane.setTop(opponent);
        pane.setBottom(player);
        pane.setCenter(centerpile);
        pane.setRight(deckpile);
        pane.setLeft(info);

        opponent.getStyleClass().add("border");
        opponent.setSpacing(40);
        opponent.setAlignment(Pos.CENTER);

        player.getStyleClass().add("border");
        player.setSpacing(40);
        player.setAlignment(Pos.CENTER);
        //root.getStyleClass().add("field");

        

        File cardback = new File("resources/back.jpg");
        Image image = new Image("file:" + cardback.getPath());
        Card back = new Card(image, 75, 50);
        Card back2 = new Card(image, 75, 50);
        Card back3 = new Card(image, 75, 50);
        Card back4 = new Card(image, 75, 50);
        Card back5 = new Card(image, 75, 50);
        Card back6 = new Card(image, 75, 50);
        Card back7 = new Card(image, 75, 50);

        opponentHand.getChildren().addAll(back, back2, back3, back4, back5, back6, back7);

        File blue1card = new File("resources/1_blue.jpg");
        File green1card = new File("resources/1_green.jpg");
        File red1card = new File("resources/1_red.jpg");

        Card blue1 = new Card(new Image("file:" + blue1card.getPath()), 75, 50);
        Card green1 = new Card(new Image("file:" + green1card.getPath()), 75, 50);
        Card red1 = new Card(new Image("file:" + red1card.getPath()), 75, 50);

        playerHand.getChildren().addAll(blue1, green1, red1);
        
        Card deck = new Card(image, 75, 50);
        Card discard = new Card(image, 75, 50);

        centerpile.getChildren().addAll(discard);

        deckpile.getChildren().addAll(deck, deckName);
    }
}
