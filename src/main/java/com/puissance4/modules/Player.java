package com.puissance4.modules;

import java.util.Scanner;

public class Player {
    String pseudo;
    String caractere;
    String couleur;

    public Player(String pseudo, String caractere, String couleur) {
        //constructor
        this.pseudo = pseudo;
        this.caractere = caractere;
        this.couleur = couleur;
    }
    public void Playerspersonnalisation(){
        String caractereOfPlayers = "X";
        String couleurOfPlayers="";

        Scanner choixpseudo = new Scanner(System.in);
        System.out.println("Voulez vous personnaliser les pseudo? \n1-Oui\n2-Non");
        Integer choixpseudoplayers = choixpseudo.nextInt();

        if(choixpseudoplayers==1) {
            Scanner p1 = new Scanner(System.in);
            System.out.println("Pseudo du joueur "+pseudo+" :");
            this.pseudo = p1.nextLine();
        }


        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nVoulez vous personnaliser vos symboles ? \n1-Oui\n2-Non");
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

        String ANSI_RESET = "\u001B[0m";
        Scanner scancouleur = new Scanner(System.in);
        System.out.println("\n\nVoulez vous personnaliser la couleur de vos symboles ? \n1-Oui\n2-Non");
        int choixcouleurplayer = scancouleur.nextInt();
        if(choixcouleurplayer == 1) {
            boolean caracouleur = false;
            while(!caracouleur){
                Scanner C1 = new Scanner(System.in);
                System.out.println("Couleur du joueur :");
                couleurOfPlayers = C1.nextLine();

                if(couleurOfPlayers.equals("Bleu")){
                    this.couleur= "\u001B[34m";
                }
                if(couleurOfPlayers.equals("Jaune")){
                    this.couleur= "\u001B[33m";
                }
                if(couleurOfPlayers.equals("Violet")){
                    this.couleur= "\u001B[35m";
                }
                if(couleurOfPlayers.equals("Rouge")){
                    this.couleur= "\u001B[31m";
                }
                if (!(couleurOfPlayers.equals("Rouge")||couleurOfPlayers.equals("Violet")||couleurOfPlayers.equals("Jaune")||couleurOfPlayers.equals("Bleu"))) {
                    System.out.println("\nCouleur non valide car non reconnues! Veuillez réessayer !");
                    caracouleur = false;
                } else {
                    caracouleur = true;
                }
            }

                }
            }

        }


