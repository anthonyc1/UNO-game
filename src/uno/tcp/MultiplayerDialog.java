/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno.tcp;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author anthonychan
 */
public class MultiplayerDialog extends Dialog {

    private int setting = 0;
    protected String name;
    protected String ip;

    private MultiplayerDialog() {
        init();
    }

    public static MultiplayerDialog getMultiplayerDialog() {
        MultiplayerDialog dialog = new MultiplayerDialog();
        return dialog;
    }

    private void init() {
        Dialog dialog = new Dialog();
        dialog.setTitle("Select Multiplayer mode");

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        VBox body = new VBox(20);
        body.setPrefWidth(300);
        body.setPrefHeight(200);

        HBox settingBox = new HBox(20);
        Button setting1 = new Button("Play");
        setting1.setMinWidth(75);
        setting1.setStyle("-fx-background-color: lightgray");
        Button setting2 = new Button("Join");
        setting2.setMinWidth(75);
        setting2.setStyle("-fx-background-color: lightgray");

        setting1.setOnAction(e -> {
            setting = 1;
            setting1.setStyle("-fx-background-color: lightgreen");
            setting2.setStyle("-fx-background-color: lightgray");
        });
        setting2.setOnAction(e -> {
            setting = 2;
            setting2.setStyle("-fx-background-color: lightgreen");
            setting1.setStyle("-fx-background-color: lightgray");
        });

        TextField ip = new TextField();
        ip.setPromptText("IP Address (to join)");

        TextField name = new TextField();
        name.setPromptText("your display name");

        HBox buttonsBar = new HBox(20);

        Button proceed = new Button("Continue");
        proceed.setMinWidth(100);
        proceed.setStyle("-fx-background-color: lightblue");
        proceed.setOnAction(e -> {
            
        });
        buttonsBar.getChildren().addAll(proceed);

        settingBox.getChildren().addAll(setting1, setting2);

        body.getChildren().addAll(settingBox, ip, name, buttonsBar);

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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }
}
