/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno.gui;

import java.io.File;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class WildCardDialog extends Dialog {
    protected String color;
    
    private WildCardDialog(){
        init();
    }
    
    public static WildCardDialog getWildCardDialog(){
        WildCardDialog dialog = new WildCardDialog();
        return dialog;
    }
    
    public void init(){
        Dialog dialog = new Dialog();
        dialog.setTitle("Select WildCard color");
        dialog.initStyle(StageStyle.UNDECORATED);
        
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        
        VBox body = new VBox(20);
        body.setPrefWidth(300);
        body.setPrefHeight(200);
        Label wildcardDetails = new Label("Select WildCard Color");
        wildcardDetails.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        Button red = new Button("Red");
        red.setMinWidth(100);
        red.setStyle("-fx-background-color: red");
        Button blue = new Button("Blue");
        blue.setMinWidth(100);
        blue.setStyle("-fx-background-color: lightblue");
        Button green = new Button("Green");
        green.setMinWidth(100);
        green.setStyle("-fx-background-color: lightgreen");
        Button yellow = new Button("Yellow");
        yellow.setMinWidth(100);
        yellow.setStyle("-fx-background-color: yellow");
        
        red.setOnAction(e -> {
            color = "red";
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.hide();
        });
        blue.setOnAction(e -> {
            color = "blue";
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.hide();
        });
        green.setOnAction(e -> {
            color = "green";
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.hide();
        });
        yellow.setOnAction(e -> {
            color = "yellow";
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.hide();
        });
        
//        if (color == null){
//            int chooseColor = (int)(100 * Math.random());
//            if (chooseColor < 25){
//                color = "blue";
//            } else if (chooseColor < 50){
//                color = "red";
//            } else if (chooseColor < 75){
//                color = "yellow";
//            } else if (chooseColor < 100){
//                color = "green";
//            }
//        }
        
        body.getChildren().addAll(wildcardDetails, red, blue, green, yellow);
        
        BorderPane container = new BorderPane();
        container.setCenter(body);
        
        dialog.getDialogPane().setContent(container);
        dialog.showAndWait();
    }    

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }
}
