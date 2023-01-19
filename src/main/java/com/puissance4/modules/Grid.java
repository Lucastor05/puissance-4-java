package com.puissance4.modules;

import java.sql.SQLOutput;
import java.util.*;
import com.puissance4.modules.Top10;
import com.puissance4.modules.Player;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;



public class Grid {
    private int Players;
    private String[][] Table = new String[6][7];
    private boolean Play = true;
    private int Round = 1;
    private Player player1;
    private Player player2;
    private Player actualPlayer;
    private int lasti = 5;
    private int lastj;
    private int lastIaI;
    private int lastIaJ;
    private List<Integer> forbiddenCases = new ArrayList<Integer>();
    private List<Integer> mustPlay = new ArrayList<Integer>();

    public CoordinateList getListWinningPons() {
        return listWinningPons;
    }

    private CoordinateList listWinningPons = new CoordinateList();



    public void printGrid() {
        /*
        Fonction qui affiche l'etat actuel de la grille en jeu
         */
        String ANSI_RED = "\u001B[31m"; //permet de changer la couleur du texte dans la console en rouge
        String ANSI_GREEN = "\u001B[32m"; //permet de changer la couleur du texte dans la console en rouge

        String ANSI_RESET = "\u001B[0m";
        System.out.println("  1   2   3   4   5   6   7  ");//affiche les numeros de colonne
        for (int i = 0; i < 6; i++) { //une boucle avec i qui a pour valeur a ne pas depasser 6, se qui equivaut au ligne
            for (int j = 0; j < 7; j++) {//une boucle avec j qui a pour valeur a ne pas depasser 7, se qui equivaut au colonne
                String sign = "-";
                if (player1.caractere.equals(Table[i][j]) || player2.caractere.equals(Table[i][j])) {
                    /*
                    modifie la variable sign celon si la case possede le symbole du joueur 1 ou 2
                     */
                    sign = Table[i][j];
                }
                if(!this.listWinningPons.contains(i,j)) {
                    if (player1.caractere.equals(Table[i][j])) {//si la case appartient au joueur 1
                        if (j == 0) {
                            System.out.print("\u001B[34m" + "│ " + ANSI_RESET + player1.couleur + sign + ANSI_RESET + "\u001B[34m" + " │" + ANSI_RESET);//affiche la case avec la couleur du joueur 1
                        } else {
                            System.out.print(" " + player1.couleur + sign + ANSI_RESET + "\u001B[34m" + " │" + ANSI_RESET);//affiche la case avec la couleur du joueur 1
                        }
                    } else if (player2.caractere.equals(Table[i][j])) {//si la case appartient au joueur 2
                        if (j == 0) {
                            System.out.print("\u001B[34m" + "│ " + ANSI_RESET + player2.couleur + sign + ANSI_RESET + "\u001B[34m" + " │" + ANSI_RESET);//affiche la case avec la couleur du joueur 2
                        } else {
                            System.out.print(" " + player2.couleur + sign + ANSI_RESET + "\u001B[34m" + " │" + ANSI_RESET);//affiche la case avec la couleur du joueur 2
                        }
                    } else {//si la case est vide
                        if (j == 0) {
                            System.out.print("\u001B[34m" + "│ " + ANSI_RESET + sign + "\u001B[34m" + " │" + ANSI_RESET);//affiche la case sans couleur
                        } else {
                            System.out.print(" " + sign + "\u001B[34m" + " │" + ANSI_RESET);//affiche la case sans couleur
                        }
                    }
                }else{
                    if (j == 0) {
                        System.out.print("\u001B[34m" + "│ " + ANSI_RESET + ANSI_GREEN + sign + ANSI_RESET + "\u001B[34m" + " │" + ANSI_RESET);
                    } else {
                        System.out.print(" " + ANSI_GREEN + sign + ANSI_RESET + "\u001B[34m" + " │" + ANSI_RESET);
                    }

                }

            }
            System.out.print("\n");
        }
        System.out.println("\u001B[34m"+"└───┴───┴───┴───┴───┴───┴───┘"+ANSI_RESET);//affiche le bas du tableau
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        /*
         * Fontion qui renvoie un nombre aleatoire compris entre le minimum et le maximum entré en parametre
         * return un nombre entien aleatoire
         */
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public boolean checkDiagonal(int mostRight, int mostLeft, int j, int i, int holej, int holei) {

        if (mostRight < j) {
            return false;
        }
        if (mostLeft > j) {
            return false;
        }
        if (holei > 0) {
            if (holei != i-1 && holei != i+1 && holej != j-1 && holej != j+1) {
                return false;
            } else {
                if (holei<4 && Table[holei+2][holej] == null) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        if (mostLeft > 0 && Table[i][mostLeft-1] == null) {
            return true;
        } else if (mostRight < 6 && Table[i][mostRight+1] == null) {
            return true;
        }
        return false;
    }

    public boolean blockDiagonalRight(int i, int j, int mostLeft, int mostRight, boolean shouldIplay, String sign, int max) {
        int diagonalRight = 0;
        int tempj = j;
        int tempi = i;
        int mostTop = tempi;
        int mostBottom = tempi;
        while (tempi <= 5 && tempj >= 0) {
            if (Objects.equals(Table[tempi][tempj], sign)) {
                mostLeft = tempj;
                mostBottom = tempi;
            }
            tempi += 1;
            tempj -= 1;
        }
        tempi = mostBottom;
        tempj = mostLeft;
        while (tempi >= 0 && tempj <= 6) {
            if (Objects.equals(Table[tempi][tempj], sign)) {
                mostRight = tempj;
                mostTop = tempi;
            }
            tempi -= 1;
            tempj += 1;
        }

        tempj = mostLeft;
        tempi = mostBottom;
        int holej = -1;
        int holei = -1;
        while (diagonalRight <= max && tempj <= mostRight && tempi >= mostTop) {
            if (Objects.equals(Table[tempi][tempj], sign)) {
                diagonalRight++;
            } else if (tempj < 6 && tempi > 0 && Table[tempi][tempj] == null && Objects.equals(Table[tempi - 1][tempj + 1], sign)) {
                diagonalRight++;
                holej = tempj;
                holei = tempi;
                tempi--;
                tempj++;
            } else {
                diagonalRight = 0;
            }
            tempi--;
            tempj++;
        }

        if (diagonalRight >= max) {
            if (!shouldIplay) {
                return checkDiagonal(mostRight, mostLeft, j, i, holej, holei);
            } else {
                if (holei > 0 && holej > 0 && Table[holei + 1][holej] != null) {
                    handleFall(holej);
                    return true;
                } else {
                    if (mostLeft > 0 && mostBottom == 4 && Table[mostBottom + 1][mostLeft - 1] == null) {
                        handleFall(mostLeft - 1);
                        return true;
                    } else if (mostLeft > 0 && mostBottom < 4 && Table[mostBottom + 1][mostLeft - 1] == null && Table[mostBottom + 2][mostLeft - 1] != null) {
                        handleFall(mostLeft - 1);
                        return true;
                    } else if (mostRight < 6 && mostTop > 0 && Table[mostTop - 1][mostRight + 1] == null && Table[mostTop][mostRight + 1] != null) {
                        handleFall(mostRight + 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean blockDiagonalLeft(int i, int j, int mostLeft, int mostRight, boolean shouldIplay, String sign, int max) {
        int diagonalLeft = 0;
        int tempj = j;
        int tempi = i;
        int mostTop = tempi;
        int mostBottom = tempi;
        while (tempi >= 0 && tempj >= 0) {
            if (Objects.equals(Table[tempi][tempj], sign)) {
                mostLeft = tempj;
                mostTop = tempi;
            }
            tempi -= 1;
            tempj -= 1;
        }
        tempi = mostTop;
        tempj = mostLeft;
        while (tempi <= 5 && tempj <= 6) {
            if (Objects.equals(Table[tempi][tempj], sign)) {
                mostRight = tempj;
                mostBottom = tempi;
            }
            tempi += 1;
            tempj += 1;
        }

        tempj = mostRight;
        tempi = mostBottom;
        int holej = -1;
        int holei = -1;
        while (diagonalLeft <= max && tempj >= mostLeft && tempi >= mostTop) {
            if (Objects.equals(Table[tempi][tempj], sign)) {
                diagonalLeft++;
            } else if (tempj >0 && tempi > 0 && Table[tempi][tempj] == null && Objects.equals(Table[tempi - 1][tempj - 1], sign)) {
                diagonalLeft++;
                holej = tempj;
                holei = tempi;
                tempi--;
                tempj--;
            } else {
                diagonalLeft = 0;
            }
            tempi--;
            tempj--;
        }

        if (diagonalLeft >= max) {
            if (!shouldIplay) {
                return checkDiagonal(mostRight, mostLeft, j, i, holej, holei);
            } else {
                if (holei > 0 && Table[holei+1][holej] != null) {
                    handleFall(holej);
                    return true;
                } else {
                    if (mostRight < 6 && mostBottom == 4 && Table[mostBottom+1][mostRight+1] == null) {
                        handleFall(mostRight + 1);
                        return true;
                    } else if (mostRight < 6 && mostBottom < 4 && Table[mostBottom+1][mostRight+1] == null && Table[mostBottom+2][mostRight+1] != null) {
                        handleFall(mostRight + 1);
                        return true;
                    }
                    else if (mostLeft > 0 && mostTop > 0 && Table[mostTop-1][mostLeft-1] == null && Table[mostTop][mostLeft-1] != null) {
                        handleFall(mostLeft - 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean blockRow(int i, int j, int mostLeft, int mostRight, boolean shouldIplay, String sign, int max) {
        int tempj = j;
        int rowCount = 0;
        //block left / right sides
        //get the most left and the most right case on the row
        while (tempj >= 0) {
            if (Objects.equals(Table[i][tempj], sign)) {
                mostLeft = tempj;
            }
            tempj -= 1;
        }
        tempj = 0;
        while (tempj < 7) {
            if (Objects.equals(Table[i][tempj], sign)) {
                mostRight = tempj;
            }
            tempj += 1;
        }

        //find the number of case in a row
        tempj = mostLeft;
        int hole = -1;
        while (rowCount <= max && tempj <= mostRight) {
            if (Objects.equals(Table[i][tempj], sign)) {
                rowCount++;
            } else if (tempj<6 && Table[i][tempj] == null && Objects.equals(Table[i][tempj + 1], sign)) {
                rowCount++;
                hole = tempj;
                tempj++;
            } else {
                rowCount = 0;
            }
            tempj++;
        }

        //block the next move of the user
        if (rowCount >= max) {
            if (!shouldIplay) {
                if (mostRight < j) {
                    return false;
                }
                if (mostLeft > j) {
                    return false;
                }
                if (hole > 0) {
                    if (hole != j-1 && hole != j+1) {
                        return false;
                    } else {
                        return true;
                    }
                }
                if (mostLeft > 0 && Table[i][mostLeft-1] == null) {
                    return true;
                } else if (mostRight < 6 && Table[i][mostRight+1] == null) {
                    return true;
                }
            } else {
                if (i < 5) {
                    if (hole > 0 && Table[i+1][hole] != null) {
                        handleFall(hole);
                        return true;
                    }
                    if (mostLeft > 0 && Table[i][mostLeft - 1] == null && Table[i + 1][mostLeft - 1] != null) {
                        handleFall(mostLeft - 1);
                        return true;
                    } else if (mostRight < 6 && Table[i][mostRight + 1] == null && Table[i + 1][mostRight + 1] != null) {
                        handleFall(mostRight + 1);
                        return true;
                    }
                } else {
                    if (hole > 0) {
                        handleFall(hole);
                        return true;
                    }
                    if (mostLeft > 0 && Table[i][mostLeft - 1] == null) {
                        handleFall(mostLeft - 1);
                        return true;
                    } else if (mostRight < 6 && Table[i][mostRight + 1] == null) {
                        handleFall(mostRight + 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean usefulDiagonalRight(int IaI, int IaJ) {
        System.out.println("can play diag right 2 : "+blockDiagonalRight(IaI, IaJ, IaI, IaJ, false, player2.caractere, 2));
        if (IaJ < 6 && IaI > 0 && IaI < 5 && Table[IaI-1][IaJ+1] == null && Table[IaI+1][IaJ+1] != null) {
            System.out.println("handle fall : "+(IaJ+1));
            if (Table[IaI][IaJ+1] == null) {
                mustPlay.add(IaJ+1);
            }
            handleFall(IaJ+1);
            return true;
        }
        return false;
    }

    public boolean canWinHereColumn(int j){
        int compteur = 0;
        for(int i = 0; i <6; i++){
            if(!player1.caractere.equals(Table[i][j])){
                compteur++;
            }else{
                break;
            }
        }

        if(compteur >= 4){return true;}
        return false;

    }

    public boolean usefulRow(int IaI, int IaJ) {
        if (Table[IaI][3] != null && !Table[IaI][3].equals(player1.caractere)) {
            if (IaJ < 3) {
                if (Table[IaI][0] != null && !Table[IaI][0].equals(player1.caractere)) {
                    return false;
                }
                for (int j = 0; j<3; j++) {
                    if (Table[IaI][j] != null && Table[IaI][j].equals(player1.caractere)) {
                        if (IaI < 4 && Table[IaI+2][j] == null) {
                            return false;
                        }
                    }
                }
                return true;
            } else {
                if (Table[IaI][6] != null && !Table[IaI][6].equals(player1.caractere)) {
                    return false;
                }
                for (int j = 3; j<7; j++) {
                    if (Table[IaI][j] != null && Table[IaI][j].equals(player1.caractere)) {
                        if (IaI < 4 && Table[IaI+2][j] == null) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void iaLvl2 () {
        int columnCount = 1;

        int i = lasti;
        int j = lastj;

        int tempi = i;
        int tempj = j;

        int mostLeft = tempj;
        int mostRight = tempj;

        String sign = player1.caractere;

        boolean returnRow = blockRow(i,j,mostLeft,mostRight, true, sign, 3);
        if (returnRow) return;
        if (i>0) {
            if (j>0) {
                returnRow = blockRow(i,j,mostLeft,mostRight, true, sign, 3);
                if (returnRow) return;
            } else if (j<6) {
                returnRow = blockRow(i,j,mostLeft,mostRight, true, sign, 3);
                if (returnRow) return;
            }
        }

        //block down side
        while (tempi < 5 && Objects.equals(Table[tempi + 1][j], sign)) {
            columnCount += 1;
            tempi += 1;
        }
        if (columnCount==3) {
            handleFall(j);
            return;
        }

        boolean returnDiagonal = blockDiagonalRight(i,j,mostLeft,mostRight, true, sign, 3);
        if (returnDiagonal) return;
        returnDiagonal = blockDiagonalRight(i,j-1,mostLeft,mostRight, true, sign, 3);
        if (returnDiagonal) return;


        returnDiagonal = blockDiagonalLeft(i,j,mostLeft,mostRight, true, sign, 3);
        if (returnDiagonal) return;
        if (j<6) {
            returnDiagonal = blockDiagonalLeft(i, j+1, mostLeft, mostRight, true, sign, 3);
            if (returnDiagonal) return;
        }

        iaLvl4AlignThree();
    }

    public void iaLvl4AlignThree() {
        int columnCount = 1;

        //IA variables
        int IaI = lastIaI;
        int IaJ = lastIaJ;
        int tempIaI = IaI;
        int tempIaJ = IaJ;
        int mostLeftIA = tempIaJ;
        int mostRightIA = tempIaJ;

        //players variables
        int i = lasti;
        int j = lastj;
        int tempj = j;

        String signIa = player2.caractere;

        //Horizontal
        System.out.println("last coordinates : "+IaI+","+IaJ);
        if (usefulRow(IaI, IaJ)) {
            System.out.println("can play row 2");
            boolean returnRow = blockRow(IaI, IaJ, mostLeftIA, mostRightIA, true, signIa, 2);
            if (returnRow) return;
            if (IaI > 0) {
                if (IaJ > 0) {
                    returnRow = blockRow(IaI - 1, IaJ - 1, mostLeftIA, mostRightIA, true, signIa, 2);
                    if (returnRow) return;
                } else if (IaJ < 6) {
                    returnRow = blockRow(IaI - 1, IaJ + 1, mostLeftIA, mostRightIA, true, signIa, 2);
                    if (returnRow) return;
                }
            }
        }

        //Vertical
        while (tempIaI < 5 && Objects.equals(Table[tempIaI + 1][IaJ], signIa)) {
            columnCount += 1;
            tempIaI += 1;
        }
        if (columnCount==2) {
            if(canWinHereColumn(IaJ)){
                handleFall(IaJ);
                return;
            }
        }


        //Diagonal Droite
        boolean returnDiagonal;
        if (usefulDiagonalRight(IaI, IaJ)) {
            return;/*
            returnDiagonal = blockDiagonalRight(IaI,IaJ,mostLeftIA,mostRightIA,true, signIa, 2);
            if (returnDiagonal) return;
            returnDiagonal = blockDiagonalRight(IaI,IaJ-1,mostLeftIA,mostRightIA,true, signIa, 2);
            if (returnDiagonal) return;*/
        }

        //Diagonal Gauche
        returnDiagonal = blockDiagonalLeft(IaI,IaJ,mostLeftIA,mostRightIA,true, signIa, 2);
        if (returnDiagonal) return;
        if (IaJ<6) {
            returnDiagonal = blockDiagonalLeft(IaI, IaJ+1, mostLeftIA, mostRightIA,true, signIa, 2);
            if (returnDiagonal) return;
        }

        randomPlace();
    }

    public void iaLvl3() {
        System.out.println("lvl3");
        for (int j = 0; j<Table[0].length; j++) {
            int mostBottom = 6;
            for (int i = 0; i<Table.length; i++) {
                if (Table[i][j] != null) {
                    mostBottom = i;
                    break;
                }
            }
            boolean canPlayerWin = false;
            if (mostBottom > 1) {
                if (j>0) {
                    canPlayerWin = blockRow(mostBottom-2, j-1, 0, 0, false, player1.caractere, 3);
                    if (canPlayerWin && !forbiddenCases.contains(j)) {
                        forbiddenCases.add(j);
                    }
                    canPlayerWin = blockDiagonalRight(mostBottom-1, j-1, 0, 0, false, player1.caractere, 3);
                    if (canPlayerWin && !forbiddenCases.contains(j)) {
                        forbiddenCases.add(j);
                    }
                }
                if (j<6) {
                    canPlayerWin = blockRow(mostBottom-2, j+1, 0, 0, false, player1.caractere, 3);
                    if (canPlayerWin && !forbiddenCases.contains(j)) {
                        forbiddenCases.add(j);
                    }
                    canPlayerWin = blockDiagonalLeft(mostBottom-1, j+1, 0, 0, false, player1.caractere, 3);
                    if (canPlayerWin && !forbiddenCases.contains(j)) {
                        forbiddenCases.add(j);
                    }
                }
            }
        }
        iaLvl2();
    }

    public void iaLvl4 () {
        int columnCount = 1;
        System.out.println("lvl4");

        //IA variables
        int IaI = lastIaI;
        int IaJ = lastIaJ;
        int tempIaI = IaI;
        int tempIaJ = IaJ;
        int mostLeftIA = tempIaJ;
        int mostRightIA = tempIaJ;

        //players variables
        int i = lasti;
        int j = lastj;
        int tempj = j;



        String signIa = player2.caractere;

        //IA WIN
        //Horizontal
        boolean returnRow = blockRow(IaI,IaJ,mostLeftIA,mostRightIA, true, signIa, 3);
        if (returnRow) return;
        if (IaI>0) {
            if (IaJ>0) {
                returnRow = blockRow(IaI-1,IaJ-1,mostLeftIA,mostRightIA, true, signIa, 3);
                if (returnRow) return;
            } else if (IaJ<6) {
                returnRow = blockRow(IaI-1,IaJ+1,mostLeftIA,mostRightIA, true, signIa, 3);
                if (returnRow) return;
            }
        }

        //Vertical
        while (tempIaI < 5 && Objects.equals(Table[tempIaI + 1][IaJ], signIa)) {
            columnCount += 1;
            tempIaI += 1;
        }
        if (columnCount==3) {
            handleFall(IaJ);
            return;
        }


        //Diagonal Droite
        boolean returnDiagonal = blockDiagonalRight(IaI,IaJ,mostLeftIA,mostRightIA,true, signIa, 3);
        if (returnDiagonal) return;
        returnDiagonal = blockDiagonalRight(IaI,IaJ-1,mostLeftIA,mostRightIA,true, signIa, 3);
        if (returnDiagonal) return;


        //Diagonal Gauche
        returnDiagonal = blockDiagonalLeft(IaI,IaJ,mostLeftIA,mostRightIA,true, signIa, 3);
        if (returnDiagonal) return;
        if (IaJ<6) {
            returnDiagonal = blockDiagonalLeft(IaI, IaJ+1, mostLeftIA, mostRightIA,true, signIa, 3);
            if (returnDiagonal) return;
        }

        iaLvl3();
    }

    public void winCondition (int i, int j) {
        lastIaI = lasti;
        lastIaJ = lastj;

        lasti = i;
        lastj = j;

        CoordinateList save = new CoordinateList();


        int rowCount = 1;
        int columnCount = 1;
        int diagonalLeft = 1;
        int diagonalRight = 1;
        int tempi = i;
        int tempj = j;

        String sign = actualPlayer.caractere;

        boolean full = true;
        for (int ifull = 0; ifull<Table.length; ifull++) {
            for (int jfull = 0; jfull<Table[0].length; jfull++) {
                if (Table[ifull][jfull] == null) {
                    full = false;
                }
            }
        }

        if (full) {
            System.out.println("Égalité, le tableau est plein");
            Play=false;
        }

        //check left / right sides
        while (tempj > 0 && Objects.equals(Table[i][tempj - 1], sign)) {
            this.listWinningPons.addCoordinate(i ,tempj-1);
            rowCount += 1;
            tempj -= 1;
        }
        tempj = j;
        while (tempj < 6 && Objects.equals(Table[i][tempj + 1], sign)) {
            this.listWinningPons.addCoordinate(i ,tempj+1);
            rowCount += 1;
            tempj += 1;
        }

        this.listWinningPons.addCoordinate(i ,j);
        if(rowCount < 4){
            this.listWinningPons.clear();
        }else{
            save.merge(this.listWinningPons);
            this.listWinningPons.clear();
        }


        //check down side
        while (tempi < 5 && Objects.equals(Table[tempi + 1][j], sign)) {
            this.listWinningPons.addCoordinate(tempi+1 ,j);
            columnCount += 1;
            tempi += 1;
        }

        this.listWinningPons.addCoordinate(i ,j);
        if(columnCount < 4){
            this.listWinningPons.clear();
        }else{
            save.merge(this.listWinningPons);
            this.listWinningPons.clear();
        }


        //check diagonal right
        tempi = i;
        tempj = j;
        while (tempi < 5 && tempj > 0 && Objects.equals(Table[tempi + 1][tempj - 1], sign)) {
            this.listWinningPons.addCoordinate(tempi-1 ,tempj-1);
            diagonalRight += 1;
            tempi += 1;
            tempj -= 1;
        }
        tempi = i;
        tempj = j;
        while (tempi > 0 && tempj < 6 && Objects.equals(Table[tempi - 1][tempj + 1], sign)) {
            this.listWinningPons.addCoordinate(tempi-1 ,tempj+1);
            diagonalRight+= 1;
            tempi -= 1;
            tempj += 1;
        }

        this.listWinningPons.addCoordinate(i ,j);
        if(diagonalRight < 4){
            this.listWinningPons.clear();
        }else{
            save.merge(this.listWinningPons);
            this.listWinningPons.clear();
        }

        //check diagonal left
        tempi = i;
        tempj = j;
        while (tempi > 0 && tempj > 0 && Objects.equals(Table[tempi - 1][tempj - 1], sign)) {
            this.listWinningPons.addCoordinate(tempi-1 ,tempj-1);
            diagonalLeft += 1;
            tempi -= 1;
            tempj -= 1;
        }
        tempi = i;
        tempj = j;
        while (tempi < 5 && tempj < 6 && Objects.equals(Table[tempi + 1][tempj + 1], sign)) {
            this.listWinningPons.addCoordinate(tempi+1 ,tempj+1);
            diagonalLeft += 1;
            tempi += 1;
            tempj += 1;
        }

        this.listWinningPons.addCoordinate(i ,j);
        if(diagonalLeft < 4){
            this.listWinningPons.clear();
        }else{
            save.merge(this.listWinningPons);
            this.listWinningPons.clear();
        }

        this.listWinningPons.merge(save);
        save.clear();


        if (rowCount >= 4 || columnCount >= 4 || diagonalRight >= 4 || diagonalLeft >= 4) {
            printGrid();
            String ANSI_RESET = "\u001B[0m";
            String ANSI_GREEN = "\u001B[32m";
            System.out.println(ANSI_GREEN+"Victoire de "+actualPlayer.pseudo+" en utilisant les caractères '"+actualPlayer.caractere+"'"+ANSI_RESET);
            if(actualPlayer.equals(player1)){
                String Winnerplayer=actualPlayer.pseudo;
                int Score= Round/2;
                Top10.createfiletxt();
                System.out.println(ANSI_GREEN+"Score de "+Score+" Round"+ANSI_RESET);
            } else if (actualPlayer.equals(player2)) {
                String Winnerplayer=actualPlayer.pseudo;
                Integer Score= (Round/2)+1;
                Top10.createfiletxt();
                System.out.println("2");
                System.out.println(ANSI_GREEN+"Score de "+Score+" Round"+ANSI_RESET);
            }
            Play = false;
        }

        changePlayer();
    }

    public void changePlayer() {
        if (actualPlayer.equals(player1)) {
            actualPlayer = player2;
        } else {
            actualPlayer = player1;
        }
    }

    public void removeForbiddenCase(int i) {
        if (forbiddenCases.contains(i)) {
            int index = forbiddenCases.indexOf(i);
            forbiddenCases.remove(index);
        }
    }

    public int removeMustPlayCase(int i) {
        if (mustPlay.contains(0)) {
            int index = mustPlay.indexOf(0);
            return mustPlay.remove(index);
        }
        return -1;
    }

    public void handleFall (int i) {
        String sign = actualPlayer.caractere;
        boolean fallen = false;
        int length = 0;
        Scanner sc = new Scanner(System.in);
        removeForbiddenCase(i);
        int returnMustPlay = removeMustPlayCase(i);
        if (actualPlayer.equals(player2) && mustPlay.size() > 0) {
            if (returnMustPlay > 0) i = returnMustPlay;
        }
        System.out.println("must play : "+mustPlay);
        if (mustPlay.size() > 0) System.out.println("play : "+i);
        while (!fallen) {
            if (player1.caractere.equals(Table[0][i]) || player2.caractere.equals(Table[0][i])) {
                System.out.println("Cette colonne est pleine");
                System.out.println("Entré une nouvelle colonne : ");
                i = sc.nextInt()-1;
            } else {
                if (length == Table.length-1 || player1.caractere.equals(Table[length + 1][i]) || player2.caractere.equals(Table[length + 1][i])) {
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
        int i = 0;
        for (int j = 0; j<Table[0].length; j++) {
            if (Table[0][j] == null) {
                i++;
            }
        }

        int randomX = getRandomNumberUsingNextInt(0, Table[0].length);
        if (i <= forbiddenCases.size()) {
            handleFall(forbiddenCases.get(0));
            removeForbiddenCase(0);
        } else {
            while (forbiddenCases.contains(randomX) || Objects.equals(Table[0][randomX], player1.caractere) || Objects.equals(Table[0][randomX], player2.caractere)) {
                randomX = getRandomNumberUsingNextInt(0, Table[0].length);
            }
            handleFall(randomX);
        }
    }


    public int getPlayers() {
        return Players;
    }
    public void setPlayers(int players) {
        Players = players;
    }
    public boolean isPlay() {
        return Play;
    }
    public void setPlay(boolean play) {
        Play = play;
    }
    public int getRound() {
        return Round;
    }
    public void setRound(int round) {
        Round = round;
    }
    public Player getPlayer1() {
        return player1;
    }
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    public Player getActualPlayer() {
        return actualPlayer;
    }
    public void setActualPlayer(Player actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

}