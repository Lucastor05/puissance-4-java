package com.puissance4.modules;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Top10 {
    ArrayList<Integer> top10 = new ArrayList<>();
    public static void createfiletxt(){
        ArrayList<Integer> top10 = new ArrayList<>();
        try {
            File myObj = new File("File.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

    }
    /*static ArrayList<Integer> top10 = new ArrayList<>();
    static File logFile = new File("top10.txt");

    public static void createTopTen(){
        ArrayList<Integer> top10 = new ArrayList<>();
        File logFile = new File("top10.txt");
    }

    public static void updateTopTen(Integer ScorePlayeur){
        // Ajouter ou modifier les entrées du top 10
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            if (ScorePlayeur<top10[i]){

                if (i < top10.size()) {
                    top10.set(i, ScorePlayeur);
                } else {
                    top10.add(ScorePlayeur);
                }
            }
        }
    }

    public static void saveTopTen(Integer ScorePlayeur){
        // Enregistrer le top 10 mis à jour dans le fichier de journal
        try (PrintWriter logWriter = new PrintWriter(logFile)) {
            for ( ScorePlayeur : top10) {
                logWriter.println(ScorePlayeur);
            }
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'enregistrement du fichier de journal.");
        }
    }


    public static void affichageTopTen() {
        for (int i = 0; i < top10.size() ; i++) { //une boucle avec i qui a pour valeur la taille du tableau, se qui equivaut au ligne
            // Charger le top 10 existant à partir du fichier de journal
            try (Scanner logScanner = new Scanner(logFile)) {
                while (logScanner.hasNextLine()) {
                    System.out.println(top10.add(logScanner.nextInt()));
                }
            } catch (FileNotFoundException e) {
                System.out.println("Aucun fichier de journal n'a été trouvé, un nouveau fichier sera créé.");
            }
        }
    }*/
}}