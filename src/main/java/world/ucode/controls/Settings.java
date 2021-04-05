package world.ucode.controls;

import javafx.fxml.FXML;
import world.ucode.App;

import java.io.IOException;

public class Settings {
    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }
}
