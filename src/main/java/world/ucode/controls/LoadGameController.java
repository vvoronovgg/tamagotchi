package world.ucode.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import world.ucode.App;
import world.ucode.utlits.Database;

import java.io.IOException;
import java.util.ArrayList;

public class LoadGameController {
    @FXML
    private Button startButton;

    @FXML
    private ChoiceBox<String> pokemonChoiceBox;

    @FXML
    public void initialize() {
        ArrayList<String> listNames = Database.selectNames();
        if (listNames.isEmpty()) {
            startButton.setDisable(true);
        } else {
            ObservableList<String> names = FXCollections.observableArrayList(listNames);
            pokemonChoiceBox.setItems(names);
            pokemonChoiceBox.setValue(listNames.get(0));
        }
    }

    @FXML
    void handleStartButton(MouseEvent event) throws IOException {
        String name_pokemon = pokemonChoiceBox.getValue();
        int id = Integer.parseInt(name_pokemon.substring(0, name_pokemon.indexOf('.')));
        Database.updateActive(id);
        App.setRoot("StartGame");
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }
}
