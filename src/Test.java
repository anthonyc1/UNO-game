import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class Test extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane pane = new GridPane(); // Create a pane and set its properties
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		// Place nodes in the pane at positions column,row
		pane.add(new Label("First Name:"), 0, 0);
		pane.add(new TextField(), 1, 0);
		pane.add(new Label("MI:"), 0, 1);
		pane.add(new TextField(), 1, 1);
		pane.add(new Label("Last Name:"), 0, 2);
		pane.add(new TextField(), 1, 2);
		Button btAdd = new Button("Add Name");
		pane.add(btAdd, 1, 3);
		GridPane.setHalignment(btAdd, HPos.RIGHT);
		Scene scene = new Scene(pane);
		primaryStage.setTitle("ShowGridPane");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}