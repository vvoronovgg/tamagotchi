package world.ucode.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import world.ucode.App;
import world.ucode.Pokemonchik;
import world.ucode.utlits.Database;

import java.io.IOException;

public class StartGameController {
    private Pokemonchik pokemonchik;
    private Timeline passTimeline;

    @FXML
    private Label name_pokemon;

    @FXML
    private ImageView id_img;

    @FXML
    private ImageView food;

    @FXML
    private ImageView health;

    @FXML
    private ImageView weed;

    @FXML
    private ImageView fatigue;

    @FXML
    private ImageView water;

    @FXML
    private Label stats;

    @FXML
    public void initialize() {
        pokemonchik = new Pokemonchik();
        name_pokemon.setText(pokemonchik.getName_pokemon());
        stats.setText(pokemonchik.getStats());
        switch (pokemonchik.getImg_pokemon()){
            case 0:
                id_img.setImage(new Image("img/pikachu.gif"));
                break;
            case 1:
                id_img.setImage(new Image("img/charizard.gif"));
                break;
            case 2:
                id_img.setImage(new Image("img/lapras.gif"));
        }
        passTimeline = setPassTimeline();
        passTimeline.setCycleCount(Timeline.INDEFINITE);
        passTimeline.play();
    }

    @FXML
    private void feed() throws IOException {
        pokemonchik.giveFood();
        renewStats();
    }

    @FXML
    private void drunk() throws IOException {
        pokemonchik.giveWater();
        renewStats();
    }

    @FXML
    private void treat() throws IOException {
        pokemonchik.giveHealth();
        renewStats();
    }

    @FXML
    private void smoked() throws IOException {
        pokemonchik.giveWeed();
        renewStats();
    }

    @FXML
    private void fatigued() throws IOException {
        pokemonchik.giveFatigue();
        renewStats();
    }

    private void renewStats() {
        dead();
        stats.setText(pokemonchik.getStats());
    }

    private void dead(){
        if(!pokemonchik.isAlive) {
            try {
                Database.deletePokemon(pokemonchik.id);
                switchToGameover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private Timeline setPassTimeline() {
        return new Timeline(
                new KeyFrame(
                        Duration.seconds(10),
                        event -> {
                            pokemonchik.passTime();
                            renewStats();
                        }
                )
        );
    }

    @FXML
    private void switchToGameover() throws IOException {
        App.setRoot("GameOver");
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        Database.saveModel(pokemonchik);
        App.setRoot("MainMenu");
    }

}
