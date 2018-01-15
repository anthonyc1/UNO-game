/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author anthonychan
 */
public class GameSettingDialog extends Dialog {
    static GameSettingDialog gameDialog;

    private int setting = 0;

    private GameSettingDialog() {
        init();
    }

    public static GameSettingDialog getGameSettingDialog() {
        gameDialog = new GameSettingDialog();
        return gameDialog;
    }

    private void init() {
        Dialog dialog = new Dialog();
        dialog.setTitle("Select UNO Gameplay");
        dialog.initStyle(StageStyle.UNDECORATED);

//        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
//        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
//        closeButton.managedProperty().bind(closeButton.visibleProperty());
//        closeButton.setVisible(false);

        File file = new File("images/uno_cards.jpg");
        Image image = new Image("file:" + file.getPath());
        ImageView imageview = new ImageView(image);
        imageview.setFitHeight(120);
        imageview.setFitWidth(120);

        VBox body = new VBox(20);
        body.setPrefWidth(300);
        body.setPrefHeight(200);
        Label gameplayDetails = new Label("UNO Gameplay Settings");
        gameplayDetails.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        Button setting1 = new Button("Single Player - Text Version");
        Button setting1 = new Button("Single Player");
        setting1.setMinWidth(120);
        setting1.setStyle("-fx-background-color: lightgray");
        Button setting2 = new Button("Multi Player");
        setting2.setMinWidth(120);
        setting2.setStyle("-fx-background-color: lightgray");

//        setting3.setOnAction(e -> {
//            setting = 3;
//            Stage stage = (Stage) closeButton.getScene().getWindow();
//            stage.hide();
//        });
        HBox buttonsBar = new HBox(20);

        Button quit = new Button("Quit");
        quit.setStyle("-fx-background-color: red");
        quit.setMinWidth(75);
        quit.setOnAction(e -> {
            setting = 0;
            Stage stage = (Stage) quit.getScene().getWindow();
            stage.hide();
            System.exit(0);
        });
        
        Button proceed = new Button("Continue");
        proceed.setStyle("-fx-background-color: lightblue");
        proceed.setMinWidth(75);
        proceed.setDisable(true);
        proceed.setOnAction(e -> {
            Stage stage = (Stage) proceed.getScene().getWindow();
            stage.hide();
        });
        buttonsBar.getChildren().addAll(proceed, quit);

        setting1.setOnAction(e -> {
            setting = 1;
            setting1.setStyle("-fx-background-color: lightgreen");
            setting2.setStyle("-fx-background-color: lightgray");
            proceed.setDisable(false);
        });
        setting2.setOnAction(e -> {
            setting = 2;
            setting1.setStyle("-fx-background-color: lightgray");
            setting2.setStyle("-fx-background-color: lightgreen");
            proceed.setDisable(false);
        });

        body.getChildren().addAll(gameplayDetails, imageview, setting1, setting2, buttonsBar);

        BorderPane container = new BorderPane();
        container.setCenter(body);

        dialog.getDialogPane().setContent(container);
        dialog.showAndWait();
    }

    public int getSetting() {
        return setting;
    }

    public void closeDialog() {
        this.close();
    }

}
