package com.puissance4.modules;
import com.puissance4.modules.Top10;
import com.puissance4.modules.Player;
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
        Scanner menuparti = new Scanner(System.in);
        System.out.println("Menu de parti:");
        System.out.println("Pour jouer contre une IA tapé 1");
        System.out.println("Pour jouer contre un autre joueur tapé 2");
        System.out.println("Pour quitter le jeu tapé 3");
        System.out.println("Que souhaitez vous faire?");
        Integer choixmenuparti= menuparti.nextInt();
        if(choixmenuparti==1||choixmenuparti==2) {
            Integer numberOfPlayers=choixmenuparti;
            Scanner sc = new Scanner(System.in);
            player1 = new Player("joueur1", "X", "");
            player1.Playerspersonnalisation();
            actualPlayer = player1;
            player2 = new Player("joueur2", "O", "");
            player2.Playerspersonnalisation();
            Players = numberOfPlayers;
            while (Play) {
                if (numberOfPlayers == 2 || Round % 2 != 0) {
                    sout();
                    System.out.println("Choisissez la colonne sur laquelle vous voulez jouer :");
                    int i = sc.nextInt();
                    handleFall(i - 1);
                } else {
                    randomPlace();
                }
            }
        } else if (choixmenuparti==2) {
        System.exit(0);
        }
    }
    public void sout() {
        String ANSI_RESET = "\u001B[0m";
        System.out.println("  1   2   3   4   5   6   7  ");
        for (int i = 0; i < 6; i++) { //une boucle avec i qui a pour valeur la taille du tableau, se qui equivaut au ligne
            for (int j = 0; j < 7; j++) {
                String sign ="-";
                if (Objects.equals(Table[i][j], player1.caractere) || Objects.equals(Table[i][j], player2.caractere)) {
                    sign = Table[i][j];
                }
                if(j==0){
                    System.out.print("\u001B[34m"+"│ "+ANSI_RESET+sign +"\u001B[34m"+" │"+ANSI_RESET);
                }else {
                    System.out.print(" "+sign +"\u001B[34m"+" │"+ANSI_RESET);
                }
            }
            System.out.print("\n");
        }
        System.out.println("\u001B[34m"+"└───┴───┴───┴───┴───┴───┴───┘"+ANSI_RESET);
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
            System.out.println(ANSI_GREEN+"Victoire de "+actualPlayer.pseudo+" en utilisant les caractères '"+actualPlayer.caractere+"'"+ANSI_RESET);
            if(actualPlayer.equals(player1)){
                String Winnerplayer=actualPlayer.pseudo;
                Integer Score= Round/2;
                Top10.createfiletxt(Winnerplayer,Score);
                System.out.println(ANSI_GREEN+"Score de "+Score+" Round"+ANSI_RESET);
                System.out.println("voici notre tableau des meilleurs scores. Tu es dessus tu pense? on regarde!");
                Top10.comparaisonDeScoreEtTri();
            } else if (actualPlayer.equals(player2)) {
                String Winnerplayer=actualPlayer.pseudo;
                Integer Score= (Round/2)+1;
                Top10.createfiletxt(Winnerplayer,Score);
                System.out.println(ANSI_GREEN+"Score de "+Score+" Round"+ANSI_RESET);
                System.out.println("voici notre tableau des meilleurs scores. Tu es dessus tu pense? on regarde!");
                Top10.comparaisonDeScoreEtTri();
            }
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