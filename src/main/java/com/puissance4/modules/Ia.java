package com.puissance4.modules;

import java.util.Scanner;

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

    public void iaspersonnalisation(){
        boolean valide = false;

        String ANSI_GREEN = "\u001B[32m";//permet de changer la couleur du texte dans la console en bleu
        String ANSI_RED = "\u001B[31m"; //permet de changer la couleur du texte dans la console en rouge
        String ANSI_RESET = "\u001B[0m"; //reset la couleur


        while(!valide) {//si la reponse donné n'est pas valide
            Scanner difficultyScan = new Scanner(System.in);

            System.out.println("\nChoisissez la difficulté du bot ? "+ANSI_GREEN+"(de 1 à 4)"+ANSI_RESET);//message d'erreur

            if (difficultyScan.hasNextInt()) {//si la reponse est un nombre entier
                int nombre = difficultyScan.nextInt();
                if(nombre > 0 && nombre < 5){//si la reponse est entre 1 et 4 compris
                    this.difficulty = nombre; //MàJ de la variable difficulty
                    valide = true;
                }else {
                    System.out.println(ANSI_RED+"\nNombre invalide! Veuillez en entrer un nouveau!"+ANSI_RESET);//message d'erreur
                }
            } else {
                System.out.println(ANSI_RED+"\nNombre invalide! Veuillez en entrer un nouveau!"+ANSI_RESET);//message d'erreur
            }
        }
    }
}

