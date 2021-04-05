package world.ucode.controls;

import javafx.fxml.FXML;
import world.ucode.App;

import java.io.IOException;

public class GameOverController {
    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }
}
