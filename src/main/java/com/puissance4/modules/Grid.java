package com.puissance4.modules;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;



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



    public void printGrid() {
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




    public boolean blockDiagonalRight(int i, int j, int mostLeft, int mostRight) {
        String sign = player1.caractere;
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
        while (diagonalRight <= 3 && tempj <= mostRight && tempi >= mostTop) {
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

        if (diagonalRight >= 3) {
            if (holei > 0 && holej > 0 && Table[holei+1][holej] != null) {
                handleFall(holej);
                return true;
            } else {
                if (mostLeft > 0 && mostBottom == 4 && Table[mostBottom+1][mostLeft-1] == null) {
                    handleFall(mostLeft - 1);
                    return true;
                } else if (mostLeft > 0 && mostBottom < 4 && Table[mostBottom+1][mostLeft-1] == null && Table[mostBottom+2][mostLeft-1] != null) {
                    handleFall(mostLeft - 1);
                    return true;
                }
                else if (mostRight < 6 && mostTop > 0 && Table[mostTop-1][mostRight+1] == null && Table[mostTop][mostRight+1] != null) {
                    handleFall(mostRight + 1);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean blockDiagonalLeft(int i, int j, int mostLeft, int mostRight) {
        String sign = player1.caractere;
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
        while (diagonalLeft <= 3 && tempj >= mostLeft && tempi >= mostTop) {
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

        if (diagonalLeft >= 3) {
            if (holei > 0 && Table[holei+1][holej+1] != null) {
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
        return false;
    }
    public boolean blockRow(int i, int j, int mostLeft, int mostRight, String sign, int max) {
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
            if (hole > 0) {
                handleFall(hole);
                return true;
            } else {
                if (i < 5) {
                    if (mostLeft > 0 && Table[i][mostLeft-1] == null && Table[i+1][mostLeft-1] != null) {
                        handleFall(mostLeft - 1);
                        return true;
                    } else if (mostRight < 6 && Table[i][mostRight+1] == null && Table[i+1][mostRight+1] != null) {
                        handleFall(mostRight + 1);
                        return true;
                    }
                } else {
                    if (mostLeft > 0 && Table[i][mostLeft-1] == null) {
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

    public int isRowEmpty(int i, int j){
        int rowCount = 0;
        int tempIaJ = j;

        String signIa = player2.caractere;


        while (tempIaJ >= 0 && Table[i][tempIaJ] != player1.caractere) {
            if (Objects.equals(Table[i][tempIaJ], signIa) || Table[i][tempIaJ] == null) {
                rowCount++;
            }
            tempIaJ -= 1;
        }
        tempIaJ = j+1;
        while (tempIaJ < 6 && Table[i][tempIaJ] != player1.caractere) {
            if (Objects.equals(Table[i][tempIaJ], signIa) || Table[i][tempIaJ] == null) {
                rowCount++;
            }
            tempIaJ += 1;
        }
        System.out.println("full right : "+(rowCount+1)+" / i : "+i+" / j : "+j);
        return rowCount+1;
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

        boolean returnRow = blockRow(i,j,mostLeft,mostRight, sign, 3);
        if (returnRow) return;
        if (i>0) {
            if (j>0) {
                returnRow = blockRow(i-1,j-1,mostLeft,mostRight, sign, 3);
                if (returnRow) return;
            } else if (j<6) {
                returnRow = blockRow(i-1,j+1,mostLeft,mostRight, sign, 3);
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

        boolean returnDiagonal = blockDiagonalRight(i,j,mostLeft,mostRight);
        if (returnDiagonal) return;
        returnDiagonal = blockDiagonalRight(i,j-1,mostLeft,mostRight);
        if (returnDiagonal) return;


        returnDiagonal = blockDiagonalLeft(i,j,mostLeft,mostRight);
        if (returnDiagonal) return;
        if (j<6) {
            returnDiagonal = blockDiagonalLeft(i, j+1, mostLeft, mostRight);
            if (returnDiagonal) return;
        }
        randomPlace();
    }
    public void iaLvl4 () {
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
        int tempi = i;
        int tempj = j;
        int mostLeft = tempj;
        int mostRight = tempj;


        String signIa = player2.caractere;
        String sign = player1.caractere;


        //IA WIN
        //Horizontal
        boolean returnRow = blockRow(IaI,IaJ,mostLeftIA,mostRightIA, signIa, 3);
        if (returnRow) return;
        if (IaI>0) {
            if (IaJ>0) {
                returnRow = blockRow(IaI-1,IaJ-1,mostLeftIA,mostRightIA, signIa, 3);
                if (returnRow) return;
            } else if (IaJ<6) {
                returnRow = blockRow(IaI-1,IaJ+1,mostLeftIA,mostRightIA, signIa, 3);
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
        boolean returnDiagonal = blockDiagonalRight(IaI,IaJ,mostLeftIA,mostRightIA);
        if (returnDiagonal) return;
        returnDiagonal = blockDiagonalRight(IaI,IaJ-1,mostLeftIA,mostRightIA);
        if (returnDiagonal) return;


        //Diagonal Gauche
        returnDiagonal = blockDiagonalLeft(IaI,IaJ,mostLeftIA,mostRightIA);
        if (returnDiagonal) return;
        if (IaJ<6) {
            returnDiagonal = blockDiagonalLeft(IaI, IaJ+1, mostLeftIA, mostRightIA);
            if (returnDiagonal) return;
        }

        //IA BLOCK

        columnCount = 1;
        returnRow = blockRow(i,j,mostLeft,mostRight, sign, 3);
        if (returnRow) return;
        if (i>0) {
            if (j>0) {
                returnRow = blockRow(i-1,j-1,mostLeft,mostRight, sign, 3);
                if (returnRow) return;
            } else if (j<6) {
                returnRow = blockRow(i-1,j+1,mostLeft,mostRight, sign, 3);
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

        returnDiagonal = blockDiagonalRight(i,j,mostLeft,mostRight);
        if (returnDiagonal) return;
        returnDiagonal = blockDiagonalRight(i,j-1,mostLeft,mostRight);
        if (returnDiagonal) return;


        returnDiagonal = blockDiagonalLeft(i,j,mostLeft,mostRight);
        if (returnDiagonal) return;
        if (j<6) {
            returnDiagonal = blockDiagonalLeft(i, j+1, mostLeft, mostRight);
            if (returnDiagonal) return;
        }

        randomPlace();
        System.out.println(isRowEmpty(lasti,lastj));
    }




    public void winCondition (int i, int j) {
        lastIaI = lasti;
        lastIaJ = lastj;


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
        while (tempj > 0 && Objects.equals(Table[i][tempj - 1], sign)) {
            rowCount += 1;
            tempj -= 1;
        }
        tempj = j;
        while (tempj < 6 && Objects.equals(Table[i][tempj + 1], sign)) {
            rowCount += 1;
            tempj += 1;
        }


        //check down side
        while (tempi < 5 && Objects.equals(Table[tempi + 1][j], sign)) {
            columnCount += 1;
            tempi += 1;
        }

        //check diagonal right
        tempi = i;
        tempj = j;
        while (tempi < 5 && tempj > 0 && Objects.equals(Table[tempi + 1][tempj - 1], sign)) {
            diagonalRight += 1;
            tempi += 1;
            tempj -= 1;
        }
        tempi = i;
        tempj = j;
        while (tempi > 0 && tempj < 6 && Objects.equals(Table[tempi - 1][tempj + 1], sign)) {
            diagonalRight+= 1;
            tempi -= 1;
            tempj += 1;
        }

        //check diagonal left
        tempi = i;
        tempj = j;
        while (tempi > 0 && tempj > 0 && Objects.equals(Table[tempi - 1][tempj - 1], sign)) {
            diagonalLeft += 1;
            tempi -= 1;
            tempj -= 1;
        }
        tempi = i;
        tempj = j;
        while (tempi < 5 && tempj < 6 && Objects.equals(Table[tempi + 1][tempj + 1], sign)) {
            diagonalLeft += 1;
            tempi += 1;
            tempj += 1;
        }

        if (rowCount >= 4 || columnCount >= 4 || diagonalRight >= 4 || diagonalLeft >= 4) {
            printGrid();
            System.out.println("Victoire de "+actualPlayer.pseudo);
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
    public void handleFall (int i) {
        String sign = actualPlayer.caractere;
        boolean fallen = false;
        int length = 0;
        while (!fallen) {
            if (Objects.equals(Table[0][i], player1.caractere) || Objects.equals(Table[0][i], player2.caractere)) {
                System.out.println("Cette colonne est pleine");
                fallen = true;
            } else {
                if (length == Table.length-1 || Objects.equals(Table[length + 1][i], player1.caractere) || Objects.equals(Table[length + 1][i], player2.caractere)) {
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
        while (Objects.equals(Table[0][randomX], player1.caractere) || Objects.equals(Table[0][randomX], player2.caractere)) {
            randomX = getRandomNumberUsingNextInt(0, Table[0].length);
        }
        handleFall(randomX);
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