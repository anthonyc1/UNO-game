package gui;

import static gui.GameSettingDialog.getGameSettingDialog;
import javafx.application.Application;
import javafx.stage.Stage;
import static tcp.MultiplayerDialog.getMultiplayerDialog;
import uno.Game;
import uno.Setup;

public class Uno extends Application {
    Workspace workspace;
    Data data;

    @Override
    public void start(Stage primaryStage) {
        // Set up uno gameplay setting
        GameSettingDialog dialog = getGameSettingDialog();
        data = new Data();

        switch (dialog.getSetting()) {
            case 1:
                workspace = new Workspace(primaryStage);
                //startGame(primaryStage);
                break;
            case 2:
                getMultiplayerDialog();
                break;
            default:
                break;
        }

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
