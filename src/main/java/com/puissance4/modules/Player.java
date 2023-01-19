package com.puissance4.modules;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Player {
    protected String pseudo;

    public String getCaractere() {
        return caractere;
    }

    protected String caractere;
    protected String couleur;

    public Player(String pseudo, String caractere, String couleur) {
        //constructor
        this.pseudo = pseudo;
        this.caractere = caractere;
        this.couleur = couleur;
    }
    public void Playerspersonnalisation(String CaraJoueur1){
        /*
        Fonction qui permet à l'utilisateur d'avoir la possibilité de personnaliser sont personnage.
        modifie le pseudo, le symbole et le caractere du personnage si voulu.
         */

        //Initialisaton des variables
        String couleurOfPlayers;
        boolean valide = false;
        String ANSI_GREEN = "\u001B[32m";//permet de changer la couleur du texte dans la console en vert
        String ANSI_BLUE = "\u001B[34m";//permet de changer la couleur du texte dans la console en bleu
        String ANSI_RED = "\u001B[31m"; //permet de changer la couleur du texte dans la console en rouge
        String ANSI_PURPLE = "\u001B[35m"; //permet de changer la couleur du texte dans la console en rouge
        String ANSI_YELLOW = "\u001B[33m"; //permet de changer la couleur du texte dans la console en rouge
        String ANSI_RESET = "\u001B[0m"; //reset la couleur

        //personnalisation des pseudos
        while(!valide){//tant que la reponse n'est pas valide
            System.out.println("\nVoulez vous personnaliser les pseudo? \n"+ANSI_GREEN+"1) Oui "+ANSI_RESET+"\t"+ANSI_RED+"2) Non"+ANSI_RESET);
            Scanner choixpseudo = new Scanner(System.in);

            if(choixpseudo.hasNextInt()) {//si la reponse donné est un nombre entier
                int choixpseudoplayers = choixpseudo.nextInt();
                if (choixpseudoplayers == 1) {////si le joueur veut changer de pseudo
                    Scanner p1 = new Scanner(System.in);
                    System.out.println("\nQuel est votre nom ?");
                    this.pseudo = p1.nextLine();//mais la variable pseudo de l'objet joueur a la valeur donné.
                }
                valide = true;
            }else{
                System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);//message d'erreur
            }
        }

        //personnalisation des symboles
        valide =  false;
        while(!valide){ //tant que la reponse n'est pas valide
            System.out.println("\nVoulez vous personnaliser vos symboles ? \n"+ANSI_GREEN+"1) Oui "+ANSI_RESET+"\t"+ANSI_RED+"2) Non"+ANSI_RESET);
            Scanner scan = new Scanner(System.in);
            if(scan.hasNextInt()){//si la valeur donné est un nombre entier
                int choixsymboleplayer = scan.nextInt();
                if(choixsymboleplayer == 1){//si le joueur veut changer de symbole
                    boolean caraBon = false;

                    while(!caraBon){//tant que le symbole n'est pas valide

                        System.out.println("\n"+this.pseudo + "  : Quel symbole voulez-vous ?");
                        Scanner c1 = new Scanner(System.in);


                        if(!c1.hasNextInt()) { //si le symbole donné n'est pas un nombre
                            String cara1 = c1.next();
                            if (cara1.equals("-") || cara1.length() != 1) { //si le symbole n'est pas - et si ca taille n'est pas egal a 1
                                System.out.println(ANSI_RED + "\nSymbole non valide! Veuillez réessayer !" + ANSI_RESET);//message d'erreur
                                caraBon = false;
                            } else {
                                if (cara1 == CaraJoueur1) {//si le caractere du joueur 2 est egal a celui du joueur 1
                                    System.out.println(ANSI_RED + "\nSymbole non valide! Veuillez réessayer !" + ANSI_RESET);//message d'erreur
                                    caraBon = false;
                                } else { //sinon cela signifie qui le caractere est valide
                                    this.caractere = cara1;
                                    caraBon = true;
                                }

                            }
                        }else{
                            System.out.println(ANSI_RED + "\nLe symbole ne peut pas être un nombre! Veuillez réessayer!" + ANSI_RESET);//message d'erreur
                        }

                    }
                }
                valide = true;
            }else{
                System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);//message d'erreur
            }

        }

        //personnalisation des couleurs
        valide = false;
        while(!valide){ //tant que la reponse donné n'est pas valide
            System.out.println("\nVoulez vous personnaliser la couleur de vos symboles ? \n"+ANSI_GREEN+"1) Oui "+ANSI_RESET+"\t"+ANSI_RED+"2) Non"+ANSI_RESET);
            Scanner scancouleur = new Scanner(System.in);
            if(scancouleur.hasNextInt()) { //si la reponse donné est en nombre entier
                int choixcouleurplayer = scancouleur.nextInt();
                if (choixcouleurplayer == 1) { //si le joueur veut changer de couleurs
                    boolean caracouleur = false;
                    while (!caracouleur) { //tant que la couleur n'est pas valide
                        Scanner C1 = new Scanner(System.in);
                        System.out.println("\nQuelle couleur souhaitez vous utiliser ?\nVous pouvez choisir parmis les couleurs suivantes : ["+ANSI_BLUE+"Bleu"+ANSI_RESET+", "+ANSI_YELLOW+"Jaune"+ANSI_RESET+", "+ANSI_PURPLE+"Violet"+ANSI_RESET+" et "+ANSI_RED+"Rouge"+ANSI_RESET+"]");

                        couleurOfPlayers = C1.nextLine();

                        //changement de la couleur de l'objet celon la reponse donné
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
                            //si la reponse pour la couleur est differente des choix donné alors renvoie une erreur
                            System.out.println(ANSI_RED+"\nCouleur non valide car non reconnues! Veuillez réessayer !"+ANSI_RESET);//message d'erreur
                            caracouleur = false;
                        } else {
                            caracouleur = true;
                        }
                    }

                }
                valide = true;
            }else{
                System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);//message d'erreur
            }
        }
    }

}


