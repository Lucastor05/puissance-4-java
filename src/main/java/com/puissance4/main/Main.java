package com.puissance4.main;

import java.util.Random;
import java.util.Scanner;

import com.puissance4.modules.Grid;
import com.puissance4.modules.Player;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de joueurs ? (1 ou 2) :");
        Integer numberOfPlayers = sc.nextInt();
        /*Scanner p1 = new Scanner(System.in);
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
        String couleurOfPlayers2 = C2.nextLine();*/



        String cara1 = "X";
        String cara2 = "O";


        grid.setPlayer1(new Player("pseudoOfPlayers1", cara1, "couleurOfPlayers1"));
        grid.setActualPlayer(Grid.getPlayer1());
        grid.setPlayer2(new Player("pseudoOfPlayers2", cara2, "couleurOfPlayers2"));
        int Players = numberOfPlayers;
        while (grid.isPlay()) {
            if (numberOfPlayers == 2 || grid.getRound()%2 != 0) {
                grid.sout();
                System.out.println("Choisissez la colonne sur laquelle vous voulez jouer :");
                int i = sc.nextInt();
                grid.handleFall(i-1);
            } else {
                grid.iaLvl2();
                //randomPlace();
            }
        }
    }
}