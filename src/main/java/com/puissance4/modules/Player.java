package com.puissance4.modules;

import java.util.Scanner;

public class Player {
    protected String pseudo;
    protected String caractere;
    protected String couleur;

    public Player(String pseudo, String caractere, String couleur) {
        //constructor
        this.pseudo = pseudo;
        this.caractere = caractere;
        this.couleur = couleur;
    }
    public void Playerspersonnalisation(){
        String caractereOfPlayers = "X";
        String couleurOfPlayers;
        boolean valide = false;
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";


        System.out.println("Voulez vous personnaliser les pseudo? \n1-Oui\n2-Non");



        while(!valide){
            Scanner choixpseudo = new Scanner(System.in);

            if(choixpseudo.hasNextInt()) {
                int choixpseudoplayers = choixpseudo.nextInt();
                if (choixpseudoplayers == 1) {
                    Scanner p1 = new Scanner(System.in);
                    System.out.println("Pseudo du joueur " + pseudo + " :");
                    this.pseudo = p1.nextLine();
                }
                valide = true;
            }else{
                System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);
            }
        }


        valide =  false;

        System.out.println("\n\nVoulez vous personnaliser vos symboles ? \n1-Oui\n2-Non");

        while(!valide){
            Scanner scan = new Scanner(System.in);
            if(scan.hasNextInt()){
                int choixsymboleplayer = scan.nextInt();
                if(choixsymboleplayer == 1){
                    boolean caraBon = false;

                    while(!caraBon){
                        Scanner c1 = new Scanner(System.in);
                        System.out.println("Joueur  : Quel symbole voulez-vous ?");
                        this.caractere = c1.nextLine();

                        if(caractereOfPlayers.equals(".") || caractereOfPlayers.length() != 1){
                            System.out.println("\nSymbole non valide! Veuillez réessayer !");
                            caraBon = false;
                        }else{
                            caraBon = true;
                        }

                    }
                }
                valide = true;
            }else{
                System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);
            }

        }


        System.out.println("\n\nVoulez vous personnaliser la couleur de vos symboles ? \n1-Oui\n2-Non");



        valide = false;

        while(!valide){
            Scanner scancouleur = new Scanner(System.in);
            if(scancouleur.hasNextInt()) {
                int choixcouleurplayer = scancouleur.nextInt();
                if (choixcouleurplayer == 1) {
                    boolean caracouleur = false;
                    while (!caracouleur) {
                        Scanner C1 = new Scanner(System.in);
                        System.out.println("Couleur du joueur :\nVous pouvez choisir parmis les couleurs suivantes : [Bleu, Jaune, Violet et Rouge]");

                        couleurOfPlayers = C1.nextLine();

                        if (couleurOfPlayers.toLowerCase().equals("bleu")) {
                            this.couleur = "\u001B[34m";
                        }
                        if (couleurOfPlayers.toLowerCase().equals("jaune")) {
                            this.couleur = "\u001B[33m";
                        }
                        if (couleurOfPlayers.toLowerCase().equals("violet")) {
                            this.couleur = "\u001B[35m";
                        }
                        if (couleurOfPlayers.toLowerCase().equals("rouge")) {
                            this.couleur = "\u001B[31m";
                        }
                        if (!(couleurOfPlayers.toLowerCase().equals("rouge") || couleurOfPlayers.toLowerCase().equals("violet") || couleurOfPlayers.toLowerCase().equals("jaune") || couleurOfPlayers.toLowerCase().equals("bleu"))) {
                            System.out.println(ANSI_RED+"\nCouleur non valide car non reconnues! Veuillez réessayer !"+ANSI_RESET);
                            caracouleur = false;
                        } else {
                            caracouleur = true;
                        }
                    }

                }
                valide = true;
            }else{
                System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);
            }
        }
    }

}


