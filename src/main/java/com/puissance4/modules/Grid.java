package com.puissance4.modules;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;

public class Grid {
    static int Players;
    static String[][] Table = new String[6][7];
    static boolean Play = true;
    static int Round = 1;
    static Player player1;
    static Player player2;
    static Player actualPlayer;


    public void play() {

        String caractereOfPlayers1 = "X";
        String caractereOfPlayers2 = "0";

        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de joueurs ? (1 ou 2) :");
        Integer numberOfPlayers = sc.nextInt();

        Scanner p1 = new Scanner(System.in);
        System.out.println("Pseudo du joueur1 :");
        String pseudoOfPlayers1 = p1.nextLine();

        Scanner p2 = new Scanner(System.in);
        System.out.println("Pseudo du joueur2 :");
        String pseudoOfPlayers2 = p2.nextLine();

        //Demandes les pseudos
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nVoulez vous personnaliser vos symboles ? \n1-Oui\n2-Non");
        int choix = scan.nextInt();


        if(choix == 1){
            boolean caraBon = false;

            while(!caraBon){
                Scanner c1 = new Scanner(System.in);
                System.out.println("Joueur 1 : Quel symbole voulez-vous ?");
                caractereOfPlayers1 = c1.nextLine();

                if(caractereOfPlayers1 == "." || caractereOfPlayers1.length() != 1){
                    System.out.println("\nSymbole non valide! Veuillez réessayer !");
                    caraBon = false;
                }else{
                    caraBon = true;
                }

            }

            caraBon = false;

            while(!caraBon) {
                while (!caraBon) {
                    Scanner c2 = new Scanner(System.in);
                    System.out.println("Joueur 2 : Quel symbole voulez-vous ?");
                    caractereOfPlayers2 = c2.nextLine();

                    if (caractereOfPlayers2 == "." || caractereOfPlayers2.length() != 1) {
                        caraBon = false;
                    } else {
                        caraBon = true;
                    }

                }

                if (caractereOfPlayers1.equals(caractereOfPlayers2)) {
                    System.out.println("\nSymbole non valide! Veuillez réessayer !");
                    caraBon = false;
                } else {
                    caraBon = true;
                }
            }
        }


        String couleurOfPlayers1="";
        String couleurcaractere1="";

        String couleurOfPlayers2="";
        String couleurcaractere2="";


        Scanner scancouleur = new Scanner(System.in);
        System.out.println("\n\nVoulez vous personnaliser la couleur de vos symboles ? \n1-Oui\n2-Non");
        int choixcouleur = scancouleur.nextInt();
            if(choixcouleur == 1) {
                boolean caracouleur = false;

                while(!caracouleur){

                Scanner C1 = new Scanner(System.in);
                System.out.println("Couleur du joueur1 :");
                couleurOfPlayers1 = C1.nextLine();

                    if(couleurOfPlayers1.equals("Bleu")){
                        couleurcaractere1= "\\u001B[34m";
                    }
                    if(couleurOfPlayers1.equals("Jaune")){
                        couleurcaractere1= "\\u001B[33m";
                    }
                    if(couleurOfPlayers1.equals("Violet")){
                        couleurcaractere1= "\u001B[31m";
                    }
                    if(couleurOfPlayers1.equals("Rouge")){
                        couleurcaractere1= "\\u001B[35m";
                    }
                    if (!(couleurOfPlayers1.equals("Rouge")||couleurOfPlayers1.equals("Violet")||couleurOfPlayers1.equals("Jaune")||couleurOfPlayers1.equals("Bleu"))) {
                        System.out.println("\nCouleur non valide car non reconnues! Veuillez réessayer !");
                        caracouleur = false;
                    } else {
                        caracouleur = true;
                    }
                }

                caracouleur=false;

                while(!caracouleur) {
                    Scanner C2 = new Scanner(System.in);
                    System.out.println("Couleur du joueur2 :");
                    couleurOfPlayers2 = C2.nextLine();

                    if (couleurOfPlayers2.equals("Bleu")) {
                        couleurcaractere2 = "\\u001B[34m";
                    }
                    if (couleurOfPlayers2.equals("Jaune")) {
                        couleurcaractere2 = "\\u001B[33m";
                    }
                    if (couleurOfPlayers2.equals("Violet")) {
                        couleurcaractere2 = "\u001B[31m";
                    }
                    if (couleurOfPlayers2.equals("Rouge")) {
                        couleurcaractere2 = "\\u001B[35m";
                    }
                    if (!(couleurOfPlayers1.equals(couleurOfPlayers2) || (couleurOfPlayers1.equals("Rouge") || couleurOfPlayers1.equals("Violet") || couleurOfPlayers1.equals("Jaune") || couleurOfPlayers1.equals("Bleu")))) {
                        System.out.println("\nCouleur non valide ! Veuillez réessayer !");
                        caracouleur = false;
                    } else {
                        caracouleur = true;
                    }
                }
                player1 = new Player(pseudoOfPlayers1, caractereOfPlayers1, couleurcaractere1);
                actualPlayer = player1;
                player2 = new Player(pseudoOfPlayers2, caractereOfPlayers2, couleurcaractere2);
                Players = numberOfPlayers;
            }
            while (Play) {
                if (numberOfPlayers == 2 || Round%2 != 0) {
                    sout();
                    System.out.println("Choisissez la colonne sur laquelle vous voulez jouer :");
                    int i = sc.nextInt();
                    handleFall(i-1);
                } else {
                    randomPlace();
                }
            }
        }

    public void sout() {
        System.out.println("  1   2   3   4   5   6   7  ");
        for (int i = 0; i < 6; i++) { //une boucle avec i qui a pour valeur la taille du tableau, se qui equivaut au ligne
            for (int j = 0; j < 7; j++) {
                String sign = "-";
                if (Objects.equals(Table[i][j], player1.caractere) || Objects.equals(Table[i][j], player2.caractere)) {
                    sign = Table[i][j];
                }
                if(j==0){
                    System.out.print("│ "+sign + " │");
                }else {
                    System.out.print(" "+sign + " │");
                }
            }
            System.out.print("\n");
        }
        System.out.println("└───┴───┴───┴───┴───┴───┴───┘");










    }


    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void winCondition (int i, int j) {
        int rowCount = 1;
        int columnCount = 1;
        int diagonalLeft = 1;
        int diagonalRight = 1;
        int tempi = i;
        int tempj = j;

        String sign = actualPlayer.caractere;

        //check left / right sides
        while (tempj > 0 && Table[i][tempj-1] == sign) {
            rowCount += 1;
            tempj -= 1;
        }
        tempj = j;
        while (tempj < 6 && Table[i][tempj+1] == sign) {
            rowCount += 1;
            tempj += 1;
        }


        //check down side
        while (tempi < 5 && Table[tempi+1][j] == sign) {
            columnCount += 1;
            tempi += 1;
        }

        //check diagonal right
        tempi = i;
        tempj = j;
        while (tempi < 5 && tempj > 0 && Table[tempi+1][tempj-1] == sign) {
            diagonalRight += 1;
            tempi += 1;
            tempj -= 1;
        }
        tempi = i;
        tempj = j;
        while (tempi > 0 && tempj < 6 && Table[tempi-1][tempj+1] == sign) {
            diagonalRight+= 1;
            tempi -= 1;
            tempj += 1;
        }

        //check diagonal left
        tempi = i;
        tempj = j;
        while (tempi > 0 && tempj < 0 && Table[tempi-1][tempj-1] == sign) {
            diagonalLeft += 1;
            tempi -= 1;
            tempj -= 1;
        }
        tempi = i;
        tempj = j;
        while (tempi < 5 && tempj < 6 && Table[tempi+1][tempj+1] == sign) {
            diagonalLeft += 1;
            tempi += 1;
            tempj += 1;
        }

        if (rowCount >= 4 || columnCount >= 4 || diagonalRight >= 4 || diagonalLeft >= 4) {
            sout();
            String ANSI_RESET = "\u001B[0m";
            String ANSI_GREEN = "\u001B[32m";
            System.out.println(ANSI_GREEN+"Victoire des "+actualPlayer.pseudo+ANSI_RESET);
            Play = false;
        }

        if (actualPlayer.equals(player1)) {
            actualPlayer = player2;
        } else {
            actualPlayer = player1;
        }
    }

    public void handleFall (int i) {
        String ANSI_RESET = "\u001B[0m";
        String sign = actualPlayer.caractere;
        String ANSI_PLAYER = actualPlayer.couleur;;
        boolean fallen = false;
        int length = 0;
        while (!fallen) {
            if (Table[0][i] == player1.caractere || Table[0][i] == player2.caractere) {
                System.out.println("Cette colonne est pleine");
                fallen = true;
            } else {
                if (length == Table.length-1 || Table[length+1][i] == player1.caractere || Table[length+1][i] == player2.caractere) {
                    Table[length][i] = (ANSI_PLAYER+sign+ANSI_RESET);
                    Round += 1;
                    fallen = true;
                } else {
                    length += 1;
                }
            }
        }
        winCondition(length, i);
    }

    public void randomPlace () {
        int randomX = getRandomNumberUsingNextInt(0, Table[0].length);
        while (Table[0][randomX] == player1.caractere || Table[0][randomX] == player2.caractere) {
            randomX = getRandomNumberUsingNextInt(0, Table[0].length);
        }
        handleFall(randomX);
    }
}
