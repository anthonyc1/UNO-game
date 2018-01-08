package gui;

import static gui.GameSettingDialog.getGameSettingDialog;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import uno.Game;
import uno.Setup;

public class Uno extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set up uno gameplay setting
        GameSettingDialog dialog = getGameSettingDialog();

        switch (dialog.getSetting()) {
            case 1:
                Setup.dealCards();
                Game.gamePlay();
                break;
            case 2:
                initGUI(primaryStage);
                //startGame(primaryStage);
                break;
            case 3:

                break;
            default:
                break;
        }

    }

    public void initGUI(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setMinSize(1000, 600);
        Scene scene = new Scene(pane);

        File file = new File("images/green.jpg");
        Image image = new Image("file:" + file.getPath());
        //ImageView imageView = new ImageView(image);
        double height = pane.getMinHeight();
        double width = pane.getMinWidth();
        BackgroundSize size = new BackgroundSize(width, height, false, false, false, false);
        BackgroundImage bkgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        Background bkg = new Background(bkgImage);

        pane.setBackground(bkg);
        //pane.setStyle("-fx-background-color: #" + 333333);

        //Load the stylesheet
        scene.getStylesheets().add("css/style.css");

//		pane.setRight(new CustomPane("Right"));
//		pane.setLeft(new CustomPane("Left"));
        // Event handling
        StackPane root = new StackPane();
        root.setMinSize(700, 300);

        Button btn = new Button();
        btn.setText("Play UNO!");
        btn.setOnAction(e -> {
            root.getChildren().remove(btn);
            startGame(primaryStage, pane, root);
        });

        root.getChildren().add(btn);
        pane.setCenter(root);

        // Add CSS to elements
        //pane.getStyleClass().add("field");
        
        primaryStage.setTitle("UNO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startGame(Stage primaryStage, BorderPane pane, StackPane root) {
        HBox opponent = new HBox();
        opponent.setMinSize(700, 100);
        opponent.setSpacing(100);
        opponent.setAlignment(Pos.CENTER);
        
        HBox player = new HBox();
        player.setMinSize(700, 100);
        player.setSpacing(100);
        player.setAlignment(Pos.CENTER);
        
        HBox opponentHand = new HBox();
        opponentHand.setMinSize(500, 100);
        opponentHand.setSpacing(40);
        opponentHand.setAlignment(Pos.CENTER);
        
        HBox playerHand = new HBox();
        playerHand.setMinSize(500, 100);
        playerHand.setSpacing(40);
        playerHand.setAlignment(Pos.CENTER);
        
        Button drawCard = new Button("Draw Card");
        drawCard.getStyleClass().add("drawcard");
        
        Button callUno = new Button("Call Uno");
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
        ImageView back = new ImageView(new Image("file:" + cardback.getPath()));
        back.setFitHeight(75);
        back.setFitWidth(50);
        
        ImageView back2 = new ImageView(new Image("file:" + cardback.getPath()));
        back2.setFitHeight(75);
        back2.setFitWidth(50);
        
        ImageView back3 = new ImageView(new Image("file:" + cardback.getPath()));
        back3.setFitHeight(75);
        back3.setFitWidth(50);
        
        ImageView back4 = new ImageView(new Image("file:" + cardback.getPath()));
        back4.setFitHeight(75);
        back4.setFitWidth(50);
        
        ImageView back5 = new ImageView(new Image("file:" + cardback.getPath()));
        back5.setFitHeight(75);
        back5.setFitWidth(50);
        
        opponentHand.getChildren().addAll(back, back2, back3, back4, back5);
        
        File blue1card = new File("resources/1_blue.jpg");
        File green1card = new File("resources/1_green.jpg");
        File red1card = new File("resources/1_red.jpg");
        
        ImageView blue1 = new ImageView(new Image("file:" + blue1card.getPath()));
        blue1.setFitHeight(75);
        blue1.setFitWidth(50);
        
        ImageView green1 = new ImageView(new Image("file:" + green1card.getPath()));
        green1.setFitHeight(75);
        green1.setFitWidth(50);
        
        ImageView red1 = new ImageView(new Image("file:" + red1card.getPath()));
        red1.setFitHeight(75);
        red1.setFitWidth(50);
        
//        ImageView back4 = new ImageView(new Image("file:" + cardback.getPath()));
//        back4.setFitHeight(75);
//        back4.setFitWidth(50);
//        
//        ImageView back5 = new ImageView(new Image("file:" + cardback.getPath()));
//        back5.setFitHeight(75);
//        back5.setFitWidth(50);
        
        playerHand.getChildren().addAll(blue1, green1, red1);

        ImageView deck = new ImageView(new Image("file:" + cardback.getPath()));
        deck.setFitHeight(75);
        deck.setFitWidth(50);
        
        ImageView discard = new ImageView(new Image("file:" + cardback.getPath()));
        discard.setFitHeight(75);
        discard.setFitWidth(50);
        
        centerpile.getChildren().addAll(discard);
        
        deckpile.getChildren().addAll(deck,deckName);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//class CustomPane extends StackPane {
//	public CustomPane(String title) {
//		getChildren().add(new Label(title));
//		setStyle("-fx-border-color: red");
//		setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
//	}
//}
