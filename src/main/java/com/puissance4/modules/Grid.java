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


    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de joueurs ? (1 ou 2) :");
        int numberOfPlayers = sc.nextInt();
        player1 = new Player("test", "X", "ff");
        actualPlayer = player1;
        player2 = new Player("test2", "O", "ff");
        Players = numberOfPlayers;
        while (Play) {
            if (numberOfPlayers == 2 || Round%2 != 0) {
                sout();
                System.out.println("Choisissez la colonne sur laquelle vous voulez jouer :");
                int i = sc.nextInt();
                handleFall(i-1);
            } else {
                iaLvl2();
                //randomPlace();
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

    public void iaLvl2 () {
        int rowCount = 0;
        int columnCount = 1;
        int diagonalLeft = 1;
        int diagonalRight = 1;

        int i = lasti;
        int j = lastj;
        int tempi = i;
        int tempj = j;
        int mostLeft = tempj;
        int mostRight = tempj;

        String sign = player1.caractere;

        //check left / right sides
        while (tempj >= 0) {
            if (Table[i][tempj] == sign) {
                mostLeft = tempj;
            }
            tempj -= 1;
        }
        tempj = 0;
        while (tempj < 7) {
            if (Table[i][tempj] == sign) {
                mostRight = tempj;
            }
            tempj += 1;
        }

        tempj = mostLeft;
        int hole = -1;
        while (rowCount <= 3 && tempj <= mostRight) {
            if (Objects.equals(Table[i][tempj], sign)) {
                rowCount++;
            } else if (tempj<6 && Table[i][tempj] == null && Table[i][tempj+1] == sign) {
                rowCount++;
                hole = tempj;
                tempj++;
            } else {
                rowCount = 0;
            }
            tempj++;
        }

        if (rowCount >= 3) {
            if (hole > 0) {
                handleFall(hole);
                return;
            } else {
                if (mostLeft > 0 && Table[i][mostLeft - 1] == null) {
                    handleFall(mostLeft - 1);
                    return;
                } else if (mostRight < 6 && Table[i][mostRight + 1] == null) {
                    handleFall(mostRight + 1);
                    return;
                }
            }
        }

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

        randomPlace();
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
            System.out.println("Victoire de "+actualPlayer.pseudo);
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
