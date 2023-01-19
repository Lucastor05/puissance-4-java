package com.puissance4.modules;

public class Ia extends Player{
    public int difficulty;
    public Ia(String pseudo, String caractere, String couleur, int difficulty) {
        super(pseudo, caractere, couleur);
        this.difficulty = difficulty;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public  void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}

