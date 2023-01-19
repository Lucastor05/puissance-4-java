package com.puissance4.modules;

import java.util.Scanner;

public class Ia extends Player{
    private  int difficulty;
    public Ia(String pseudo, String caractere, String couleur, int difficulty) {
        super(pseudo, caractere, couleur);
        this.difficulty = difficulty;
    }
    public  int getDifficulty() {
        return difficulty;
    }
    public  void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void iaspersonnalisation(){
        boolean valide = false;

        while(!valide) {
            Scanner difficultyScan = new Scanner(System.in);

            System.out.println("\n\nChoisissez la difficulté du bot ? (de 1 à 4)\n");

            if (difficultyScan.hasNextInt()) {
                int nombre = difficultyScan.nextInt();
                if(nombre > 0 && nombre < 5){
                    this.difficulty = nombre;
                    valide = true;
                }else {
                    System.out.println("\nNombre invalide! Veuillez en entrer un nouveau!");
                }
            } else {
                System.out.println("\nNombre invalide! Veuillez en entrer un nouveau!");
            }
        }
    }
}

