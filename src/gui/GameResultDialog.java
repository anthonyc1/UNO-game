/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.GameSettingDialog.getGameSettingDialog;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author anthonychan
 */
public class GameResultDialog extends Dialog {
    
    Label result;
    
    private GameResultDialog(Stage primaryStage, String winner) {
        init(primaryStage, winner);
    }

    public static GameResultDialog getGameResultDialog(Stage primaryStage, String winner) {
        GameResultDialog dialog = new GameResultDialog(primaryStage, winner);
        return dialog;
    }

    private void init(Stage primaryStage, String winner) {
        Dialog dialog = new Dialog();
        dialog.setTitle("UNO Game Result");

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

//        File file = new File("images/celebrate.jpg");
//        Image image = new Image("file:" + file.getPath());
//        ImageView imageview = new ImageView(image);
//        imageview.setFitHeight(120);
//        imageview.setFitWidth(120);

        if (winner.equals("PLAYER")){
            result = new Label("YOU WIN!");
            result.setTextFill(Color.GOLD);
        } else {
            result = new Label("YOU LOSE!");
            result.setTextFill(Color.DIMGRAY);
        }
        VBox body = new VBox(20);
        body.setPrefWidth(300);
        body.setPrefHeight(200);
        
        result.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        result.setAlignment(Pos.CENTER);

        HBox buttonsBar = new HBox(20);

        Button quit = new Button("Back to Menu");
        quit.setStyle("-fx-background-color: red");
        quit.setMinWidth(75);
        quit.setOnAction(e -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.hide();
            getGameSettingDialog();
        });

        Button proceed = new Button("Play Again");
        proceed.setStyle("-fx-background-color: lightblue");
        proceed.setMinWidth(75);
        proceed.setOnAction(e -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.hide();
            new Workspace(primaryStage);
        });
        buttonsBar.getChildren().addAll(proceed, quit);


        body.getChildren().addAll(result, buttonsBar);

        BorderPane container = new BorderPane();
        container.setCenter(body);

        dialog.getDialogPane().setContent(container);
        dialog.showAndWait();
    }
}
