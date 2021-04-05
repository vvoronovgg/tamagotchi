package world.ucode;

import javafx.fxml.FXML;
import world.ucode.utlits.Calculation;
import world.ucode.utlits.Database;

import java.io.IOException;

public class Pokemonchik {
    private Calculation calc;
    public int id;
    private String name_pokemon;
    private int img_pokemon;

    private int food;
    private int water;
    private int health;
    private int weed;
    private int fatigue;
    public boolean isAlive = true;

    public void setName_pokemon(String name_pokemon) {
        this.name_pokemon = name_pokemon;
    }
    public void setImg_pokemon(int img_pokemon) {
        this.img_pokemon = img_pokemon;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public void setWater(int water) {
        this.water = water;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setWeed(int weed) {
        this.weed = weed;
    }
    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public int getId() { return id; }
    public String getName_pokemon() {
        return name_pokemon;
    }
    public int getImg_pokemon() {
        return img_pokemon;
    }
    public int getFood() {
        return food;
    }
    public int getWater() {
        return water;
    }
    public int getHealth() {
        return health;
    }
    public int getWeed() {
        return weed;
    }
    public int getFatigue() {
        return fatigue;
    }

    public Pokemonchik() {
        Database.loadPokemon(this);
    }
    public String getStats() {
        return  "hunger \t" + food + "\n" +
                "thirst \t" + water + "\n" +
                "health \t" + health + "\n" +
                "smoky \t" + weed + "\n" +
                "fatigue\t" + fatigue;
    }
    public void passTime() {
        food--;
        health--;
        fatigue--;

//        if (food >= calc.m7()) {
//            health--;
//        }
//        if (fatigue >= calc.m7()) {
//            health--;
//        }
//        if ( health<= calc.m3()) {
//            fatigue--;
//        }
        updStats();
    }

    private void updStats() {
        if (food > 100 || food < 0) { health--; }
        if (water > 100 || water < 0) { health--; }
        if (weed < 0)            { health--; }


        food = food > 100 ? 100 : (food = food < 0 ? 0 : food);
        water = water > 100 ? 100 : (water = water < 0 ? 0 : water);
        weed = weed > 100 ? 100 : (weed = weed < 0 ? 0 : weed);
        fatigue = fatigue > 100 ? 100 : (fatigue = fatigue < 0 ? 0 : fatigue);
        health = health > 100 ? 100 : health;
        if (health <= 0) {
            health = 0;
            isAlive = false;
        }
    }


    public void giveFood() {
        food += 10;
        health -= 10;
        fatigue -= 10;
        updStats();
    }

    public void giveWater() {
        water += 10;
        weed -= 10;
        fatigue -= 10;
        updStats();
    }

    public void giveHealth() {
        health += 10;
        water -= 10;
        food -= 10;
        updStats();
    }

    public void giveWeed() {
        health -= 20;
        weed += 10;
        water -= 10;
        food -= 10;
        updStats();
    }

    public void giveFatigue() {
        fatigue = 90;
        water -= 10;
        food -= 10;
        weed -= 10;
        updStats();
    }

}
