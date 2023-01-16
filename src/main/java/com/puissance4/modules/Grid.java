package com.puissance4.modules;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Grid {
    static int Players;
    static String[][] Table = new String[6][7];
    static boolean Play = true;
    static int Round = 1;
    static Player player1;
    static Player player2;
    static Player actualPlayer;


    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de joueurs ? (1 ou 2) :");
        Integer numberOfPlayers = sc.nextInt();
        Scanner p1 = new Scanner(System.in);
        System.out.println("Pseudo du joueur1 :");
        String pseudoOfPlayers1 = p1.nextLine();
        Scanner p2 = new Scanner(System.in);
        System.out.println("Pseudo du joueur2 :");
        String pseudoOfPlayers2 = p2.nextLine();
        Scanner c1 = new Scanner(System.in);
        System.out.println("Caractere du joueur1 :");
        String caractereOfPlayers1 = c1.nextLine();
        Scanner c2 = new Scanner(System.in);
        System.out.println("Caractere du joueur2 :");
        String caractereOfPlayers2 = c2.nextLine();
        Scanner C1 = new Scanner(System.in);
        System.out.println("Couleur du joueur1 :");
        String couleurOfPlayers1 = C1.nextLine();
        Scanner C2 = new Scanner(System.in);
        System.out.println("Couleur du joueur2 :");
        String couleurOfPlayers2 = C2.nextLine();



        String cara1 = "X";
        String cara2 = "O";


        player1 = new Player(pseudoOfPlayers1, caractereOfPlayers1, couleurOfPlayers1);
        actualPlayer = player1;
        player2 = new Player(pseudoOfPlayers2, caractereOfPlayers2, couleurOfPlayers2);
        Players = numberOfPlayers;
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
        String render = "\n";
        for (int i = 0; i <= Table.length-1; i++) {
            for (int j = 0; j <= Table[0].length-1; j++) {
                if (Objects.equals(Table[i][j], player1.caractere) || Objects.equals(Table[i][j], player2.caractere)) {
                    render += Table[i][j];
                } else {
                    render += "-";
                }
            }
            render += "\n";
        }
        render += "\n";
        System.out.println(render);
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
        String sign = actualPlayer.caractere;
        boolean fallen = false;
        int length = 0;
        while (!fallen) {
            if (Table[0][i] == player1.caractere || Table[0][i] == player2.caractere) {
                System.out.println("Cette colonne est pleine");
                fallen = true;
            } else {
                if (length == Table.length-1 || Table[length+1][i] == player1.caractere || Table[length+1][i] == player2.caractere) {
                    Table[length][i] = sign;
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
