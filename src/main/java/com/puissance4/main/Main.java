package com.puissance4.main;

import java.io.File;
import java.util.Random;
import java.util.Scanner;
import com.puissance4.modules.Grid;
import com.puissance4.modules.Top10;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        Scanner menu = new Scanner(System.in);
        System.out.println("Menu:");
        System.out.println("Pour jouer tapé 1");
        System.out.println("Pour voir le Top 10 des meilleurs parties de Puissance 4 tapé 2");
        System.out.println("Pour quitter le jeu tapé 3");
        System.out.println("Que souhaitez vous faire?");
        Integer choixmenu = menu.nextInt();
        if(choixmenu.equals(1)) {
            grid.play();
        } else if (choixmenu.equals(2)) {
            Top10.lecturedeFile();
            Top10.comparaisonDeScoreEtTri();
        } else if (choixmenu.equals(3)) {
            System.exit(0);
        }
    }
}