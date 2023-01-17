package com.puissance4.main;

import java.util.Scanner;

import com.puissance4.modules.Grid;
import com.puissance4.modules.Player;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de joueurs ? (1 ou 2) :");
        int numberOfPlayers = sc.nextInt();

        grid.setPlayer1(new Player("test", "X", "ff"));
        grid.setActualPlayer(Grid.getPlayer1());
        grid.setPlayer2(new Player("test2", "0", "Z"));

        grid.setPlayers(numberOfPlayers);
        while (grid.isPlay()) {
            if (numberOfPlayers == 2 || grid.getRound()%2 != 0) {
                grid.printGrid();
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