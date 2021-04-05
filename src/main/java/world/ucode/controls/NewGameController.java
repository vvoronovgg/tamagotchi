package world.ucode.controls;

import java.io.IOException;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import world.ucode.App;
import world.ucode.utlits.CircularIterator;
import world.ucode.utlits.Database;

public class NewGameController {
    int i;
    private List<Object> images = new ArrayList<>();
    private CircularIterator circularIterator;
    private final Image pikaImage1 = new Image("img/pikachu.gif");
    private final Image pikaImage2 = new Image("img/charizard.gif");
    private final Image pikaImage3 = new Image("img/lapras.gif");



    @FXML
    public void initialize() {
        images.add(pikaImage1);
        images.add(pikaImage2);
        images.add(pikaImage3);
        circularIterator = new CircularIterator(images);
        pika_img.setImage((Image)circularIterator.getElement(0));
    }

    @FXML
    private ImageView pika_img;

    @FXML
    private TextField name_pokemon;

    @FXML
    void arrow_left_clicked(MouseEvent event) {
        changePokemon(circularIterator.previous());
    }

    @FXML
    void arrow_right_clicked(MouseEvent event) {
        changePokemon(circularIterator.next());
    }

    private void changePokemon(Object obj) {
        System.out.println(obj);
        System.out.println(circularIterator.getIndexSelected());
        pika_img.setImage((Image) obj);
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }
    @FXML
    private void switchToStartGame() throws IOException {
        int i = circularIterator.getIndexSelected();
        System.out.println(i);
        System.out.println(name_pokemon.getText());
        System.out.println(i);
        Database.insertPokemonDB(name_pokemon.getText(),i);
        App.setRoot("StartGame");
    }
}