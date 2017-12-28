package gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Collections;

public class Test4 extends Application {

	final int WIDTH = 600;
	final int HEIGHT = 400;

	double ballRadius = 40;
	double ballX = 100;
	double ballY = 200;
	double xSpeed = 4;
	double ySpeed = 3;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Basic JavaFX demo");
	
		Group root = new Group();
		
//		Rectangle r = new Rectangle();
//		r.setX(0);
//		r.setHeight(400);
//		r.setWidth(400);
//		r.setStyle("-fx-background-color:red");
//		
//		Rectangle sidebar = new Rectangle();
//		sidebar.setX(400);
//		sidebar.setHeight(400);
//		sidebar.setWidth(200);
//		sidebar.setStyle("-fx-border-color:green");
		
		Slider sliderX = new Slider(0,10,xSpeed);
		Slider sliderY = new Slider(0,10,ySpeed);
		
		sliderX.setSnapToTicks(true);
		sliderX.setOrientation(Orientation.VERTICAL);
		sliderX.setShowTickMarks(true);
	    sliderX.setShowTickLabels(true);
	    sliderX.setMajorTickUnit(1);
	    sliderX.setMinorTickCount(1);
		
	    sliderX.valueProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				
			}
        });
		
		root.getChildren().addAll( sliderX);
		
		Scene scene = new Scene(root,WIDTH+200, HEIGHT);

		Circle circle = new Circle();
		circle.setCenterX(ballX);
		circle.setCenterY(ballY);
		circle.setRadius(ballRadius);
		circle.setFill(Color.BLUE);
		root.getChildren().add(circle);

		stage.setScene(scene);
		stage.show();

		AnimationTimer animator = new AnimationTimer() {

			@Override
			public void handle(long arg0) {

				// UPDATE
				ballX += xSpeed;
				ballY += ySpeed;

				if (ballX + ballRadius >= WIDTH) {
					ballX = WIDTH - ballRadius;
					xSpeed *= -1;
				} else if (ballX - ballRadius < 0) {
					ballX = 0 + ballRadius;
					xSpeed *= -1;
				}
				
				if (ballY + ballRadius >= HEIGHT) {
					ballY = HEIGHT - ballRadius;
					ySpeed *= -1;
				} else if (ballY - ballRadius < 0) {
					ballY = 0 + ballRadius;
					ySpeed *= -1;
				}

				// RENDER
				circle.setCenterX(ballX);
				circle.setCenterY(ballY);
			}
		};

		animator.start();
	}
}
