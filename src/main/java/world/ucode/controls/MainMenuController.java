package world.ucode.controls;

import java.io.IOException;
import javafx.fxml.FXML;
import world.ucode.App;

public class MainMenuController {

    @FXML
    private void switchToNewGame() throws IOException {
        App.setRoot("NewGame");
    }

    @FXML
    private void switchToLoadGame() throws IOException {
        App.setRoot("LoadGame");
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("Settings");
    }

    @FXML
    private void switchToExit() throws IOException {
        System.exit(0);
    }
}
