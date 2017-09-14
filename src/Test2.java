import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test2 extends Application {
	@Override
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setPrefSize(1000,800);
		// opponent's hands
		GridPane opponent = new GridPane();
		opponent.setPrefSize(700, 100);
		pane.setTop(opponent);
		
//		pane.setRight(new CustomPane("Right"));
//		pane.setLeft(new CustomPane("Left"));
		
		// your hand
		GridPane player = new GridPane();
		player.setPrefSize(700, 100);
		pane.setBottom(player);
		
		
		pane.setCenter(new CustomPane("Center"));
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}

class CustomPane extends StackPane {
	public CustomPane(String title) {
		getChildren().add(new Label(title));
		setStyle("-fx-border-color: red");
		setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	}
}
