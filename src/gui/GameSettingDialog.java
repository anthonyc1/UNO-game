/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author anthonychan
 */
public class GameSettingDialog extends Dialog{
    private int setting = 0;
    
    private GameSettingDialog(){
        init();
    }
    
    public static GameSettingDialog getGameSettingDialog(){
        GameSettingDialog dialog = new GameSettingDialog();
        return dialog;
    }
    
    public void init(){
        Dialog dialog = new Dialog();
        dialog.setTitle("Select UNO Gameplay");
        
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        
        VBox body = new VBox(20);
        body.setPrefWidth(300);
        body.setPrefHeight(200);
        Label gameplayDetails = new Label("UNO Gameplay Settings");
        gameplayDetails.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Button setting1 = new Button("Single Player - Text Version");
        Button setting2 = new Button("Single Player - GUI Version");
        Button setting3 = new Button("Multi Player");
        
        setting1.setOnAction(e -> {
            setting = 1;
            Stage stage = (Stage) setting1.getScene().getWindow();
            stage.hide();
        });
        setting2.setOnAction(e -> {
            setting = 2;
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.hide();
        });
        setting3.setOnAction(e -> {
            setting = 3;
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.hide();
        });
        
        HBox buttonsBar = new HBox(20);
//        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
//        ok.setOnAction(e->{
//            Stage stage = (Stage) closeButton.getScene().getWindow();
//            stage.hide();
//        });
        cancel.setOnAction(e->{
            setting = 0;
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.hide();
        });
        buttonsBar.getChildren().addAll(cancel);
        
        body.getChildren().addAll(gameplayDetails, setting1,setting2,setting3, buttonsBar);
        
        BorderPane container = new BorderPane();
        container.setCenter(body);
        
        dialog.getDialogPane().setContent(container);
        dialog.showAndWait();
    }
    
    public int getSetting(){
        return setting;
    }
    
    public void closeDialog(){
        this.close();
    }
    
}
