package gui;

import static gui.GameSettingDialog.getGameSettingDialog;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
                break;
            case 3:
                
                break;
            default:
                break;
        }

    }
    
    public void initGUI(Stage primaryStage){
        BorderPane pane = new BorderPane();
        pane.setPrefSize(1000, 800);
        Scene scene = new Scene(pane);
        //Load the stylesheet
        scene.getStylesheets().add("css/style.css");

        GridPane opponent = new GridPane();
        opponent.setPrefSize(700, 100);
        GridPane player = new GridPane();
        player.setPrefSize(700, 100);

        pane.setTop(opponent);
        pane.setBottom(player);

//		pane.setRight(new CustomPane("Right"));
//		pane.setLeft(new CustomPane("Left"));
        // Event handling
//        Button btn = new Button();
//        btn.setText("Play UNO!");
//        btn.setOnAction(e -> {
//            
//        });
        

        StackPane root = new StackPane();
        root.setPrefSize(700, 600);
//        root.getChildren().add(btn);
        pane.setCenter(root);

        // Add CSS to elements
        pane.getStyleClass().add("field");
        opponent.getStyleClass().add("hand");
        player.getStyleClass().add("hand");
        root.getStyleClass().add("field");

        primaryStage.setTitle("UNO");
        primaryStage.setScene(scene);
        primaryStage.show();
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
