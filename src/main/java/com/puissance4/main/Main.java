package com.puissance4.main;

import java.util.Scanner;

import com.puissance4.modules.Grid;
import com.puissance4.modules.Ia;
import com.puissance4.modules.Player;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de joueurs ? (1 ou 2) :");
        int numberOfPlayers = sc.nextInt();

        grid.setPlayer1(new Player("Player_1", "X", "ff"));
        grid.setActualPlayer(grid.getPlayer1());

        if(numberOfPlayers == 2){
            grid.setPlayer2(new Player("Player_2", "0", "green"));
            grid.setPlayers(numberOfPlayers);
        }else{
            grid.setPlayer2(new Ia("BOT", "0", "red", 4));
            grid.setPlayers(numberOfPlayers);
        }



        while (grid.isPlay()) {
            if (numberOfPlayers == 2 || grid.getRound()%2 != 0) {
                grid.printGrid();
                System.out.println("Choisissez la colonne sur laquelle vous voulez jouer :");
                int i = sc.nextInt();
                grid.handleFall(i-1);
            } else {
                Ia player2 = (Ia)grid.getPlayer2();
                if(player2.getDifficulty()==1){
                    grid.randomPlace();
                }else if(player2.getDifficulty()==2){
                    grid.iaLvl2();
                }else if(player2.getDifficulty()==3){
                    //grid.iaLvl3();
                }else if(player2.getDifficulty()==4){
                    grid.iaLvl4();
                }
            }
        }
    }
}