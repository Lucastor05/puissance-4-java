package com.puissance4.main;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static int Players;
    static String[][] Table = new String[6][7];
    static boolean Play = true;
    static int Round = 1;
    static String Sign = "X";

    static void output () {
        String render = "\n";
        for (int i = 0; i <= Table.length-1; i++) {
            for (int j = 0; j <= Table[0].length-1; j++) {
                if (Table[i][j] == "X" || Table[i][j] == "O") {
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

    static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    static void winCondition (int i, int j) {
        int rowCount = 1;
        int columnCount = 1;
        int diagonalLeft = 1;
        int diagonalRight = 1;
        int tempi = i;
        int tempj = j;

        //check left / right sides
        while (tempj > 0 && Table[i][tempj-1] == Sign) {
            rowCount += 1;
            tempj -= 1;
        }
        tempj = j;
        while (tempj < 6 && Table[i][tempj+1] == Sign) {
            rowCount += 1;
            tempj += 1;
        }


        //check down side
        while (tempi < 5 && Table[tempi+1][j] == Sign) {
            columnCount += 1;
            tempi += 1;
        }

        //check diagonal right
        tempi = i;
        tempj = j;
        while (tempi < 5 && tempj > 0 && Table[tempi+1][tempj-1] == Sign) {
            diagonalRight += 1;
            tempi += 1;
            tempj -= 1;
        }
        tempi = i;
        tempj = j;
        while (tempi > 0 && tempj < 6 && Table[tempi-1][tempj+1] == Sign) {
            diagonalRight+= 1;
            tempi -= 1;
            tempj += 1;
        }

        //check diagonal left
        tempi = i;
        tempj = j;
        while (tempi > 0 && tempj < 0 && Table[tempi-1][tempj-1] == Sign) {
            diagonalLeft += 1;
            tempi -= 1;
            tempj -= 1;
        }
        tempi = i;
        tempj = j;
        while (tempi < 5 && tempj < 6 && Table[tempi+1][tempj+1] == Sign) {
            diagonalLeft += 1;
            tempi += 1;
            tempj += 1;
        }

        if (rowCount >= 4 || columnCount >= 4 || diagonalRight >= 4 || diagonalLeft >= 4) {
            output();
            System.out.println("Victoire des "+Sign);
            Play = false;
        }

        if (Sign == "X") {
            Sign = "O";
        } else {
            Sign = "X";
        }
    }

    static void handleFall (int i) {
        boolean fallen = false;
        int length = 0;
        while (!fallen) {
            if (Table[0][i] == "X" || Table[0][i] == "O") {
                System.out.println("Cette colonne est pleine");
                fallen = true;
            } else {
                if (length == Table.length-1 || Table[length+1][i] == "X" || Table[length+1][i] == "O") {
                    Table[length][i] = Sign;
                    Round += 1;
                    fallen = true;
                } else {
                    length += 1;
                }
            }
        }
        winCondition(length, i);
    }

    static void randomPlace () {
        int randomX = getRandomNumberUsingNextInt(0, Table[0].length);
        while (Table[0][randomX] == "X" || Table[0][randomX] == "O") {
            randomX = getRandomNumberUsingNextInt(0, Table[0].length);
        }
        handleFall(randomX);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de joueurs ? (1 ou 2) :");
        int numberOfPlayers = sc.nextInt();
        Players = numberOfPlayers;
        while (Play) {
            if (numberOfPlayers == 2 || Round%2 != 0) {
                output();
                System.out.println("Choisissez la colonne sur laquelle vous voulez jouer :");
                int i = sc.nextInt();
                handleFall(i-1);
            } else {
                randomPlace();
            }
        }
    }
}