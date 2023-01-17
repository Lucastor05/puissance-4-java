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
    static int lasti;
    static int lastj;

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

    public void iaLvl2 () {
        int rowCount = 1;
        int columnCount = 1;
        int diagonalLeft = 1;
        int diagonalRight = 1;

        int i = lasti;
        int j = lastj;
        int tempi = i;
        int tempj = j;
        int mostLeft = tempj;

        String sign = player1.caractere;

        /*System.out.println(tempj);
        //check left / right sides
        while (tempj >= 0) {
            if (Table[i][tempj] == sign) {
                mostLeft = tempj;
            }
            tempj -= 1;
        }
        while (tempj < 6) {

            tempj++;
        }
        System.out.println("most left :"+mostLeft);*/


        i = 0;
        j = 0;
        int compteur = 0;
        int valeur_tempo = 0;

        while(compteur != 3 || j < 7){
            if(Table[i][j] == player1.caractere){
                compteur +=1;
                if(compteur == 3){
                    valeur_tempo = j+1;
                }
                j+=1;
            }else if(Table[i][j] == null){
                valeur_tempo = j;
                j+=1;
                while(Table[i][j] == player1.caractere){
                    compteur += 1;
                    j+=1;
                }
            }else{
                compteur = 0;
                j+=1;
            }
        }

        if(compteur >= 3){
            Table[i][valeur_tempo] = player2.caractere;
        }


        /*
        while (tempj > 0) {
            System.out.println(tempj);
            if (tempj > 1) {
                System.out.println(Table[i][tempj]);
                System.out.println(Table[i][tempj - 1]);
                System.out.println(Table[i][tempj - 2]);
            }
            if (tempj > 1 && Table[i][tempj - 1] == null && Table[i][tempj - 1] == player1.caractere && rowCount>=3) {
                rowCount += 1;
                tempj -= 2;
                break;
            }
            if (Table[i][tempj - 1] == sign) {
                rowCount += 1;
            }
            tempj -= 1;
        }
        tempj = j;
        while (tempj < 6 && Table[i][tempj+1] == sign) {
            rowCount += 1;
            tempj += 1;
        }*/

        //block left / right sides
        //System.out.println("rowCount :"+rowCount);
        /*if (rowCount >= 2) {
            while (tempj > 0 && Table[i][tempj-1] == sign) {
                tempj -= 1;
            }
            System.out.println(tempj);
            if (tempj>0) {
                if (Table[i][tempj - 1] == null && rowCount == 3) {
                    handleFall(tempj - 1);
                    return;
                } else if (tempj >= 2 && rowCount == 2 && Table[i][tempj - 1] == null && Table[i][tempj - 2] == sign) {
                    handleFall(tempj - 1);
                    return;
                }
            }

            tempj = j;
            while (tempj < 6 && Table[i][tempj+1] == sign) {
                rowCount += 1;
                tempj += 1;
            }
            if (tempj<6) {
                if (Table[i][tempj + 1] == null && rowCount == 3 && tempj >= 1) {
                    handleFall(tempj + 1);
                    return;
                } else if (rowCount == 2 && Table[i][tempj + 1] == null && Table[i][tempj + 2] == sign) {
                    handleFall(tempj + 1);
                    return;
                }
            }
        }*/


        //check down side
        while (tempi < 5 && Table[tempi+1][j] == sign) {
            columnCount += 1;
            tempi += 1;
        }
        if (columnCount==3) {
            handleFall(j);
            return;
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

        handleFall(0);
    }

    public void winCondition (int i, int j) {
        lasti = i;
        lastj = j;
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




    public static void setPlayer1(Player player1) {
        Grid.player1 = player1;
    }

    public static void setPlayer2(Player player2) {
        Grid.player2 = player2;
    }

    public static void setActualPlayer(Player actualPlayer) {
        Grid.actualPlayer = actualPlayer;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static Player getActualPlayer() {
        return actualPlayer;
    }

    public static boolean isPlay() {
        return Play;
    }
    public static int getRound() {
        return Round;
    }
}
