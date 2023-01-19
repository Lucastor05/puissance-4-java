package com.puissance4.main;

import java.io.File;
import java.util.Random;
import java.util.Scanner;
import com.puissance4.modules.Grid;
import com.puissance4.modules.Ia;
import com.puissance4.modules.Player;
import com.puissance4.modules.Top10;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        String ANSI_GREEN = "\u001B[32m";//permet de changer la couleur du texte dans la console en vert
        String ANSI_BLUE = "\u001B[34m";//permet de changer la couleur du texte dans la console en bleu
        String ANSI_RED = "\u001B[31m"; //permet de changer la couleur du texte dans la console en rouge
        String ANSI_RESET = "\u001B[0m";//reset la couleur


        boolean restart = true;
        while(restart) {

            boolean choixvalide = false;



            while (!choixvalide){
                System.out.println("\nMenu:");
                System.out.println("Pour jouer tapé "+ANSI_GREEN+"1"+ANSI_RESET);
                System.out.println("Pour voir le Top 10 des meilleurs parties de Puissance 4 tapé "+ANSI_GREEN+"2"+ANSI_RESET);
                System.out.println("Pour quitter le jeu tapé "+ANSI_RED+"3"+ANSI_RESET);
                System.out.println("Que souhaitez vous faire?");
                Scanner menu = new Scanner(System.in);
                if (menu.hasNextInt()) {
                    int choixmenu = menu.nextInt();
                    if (choixmenu == 1) {
                        boolean choixNbJoueurValide = false;

                        while(!choixNbJoueurValide) {
                            System.out.println("\nMenu de parti:");
                            System.out.println("Pour jouer contre une IA tapé "+ANSI_GREEN+"1"+ANSI_RESET);
                            System.out.println("Pour jouer contre un autre joueur tapé "+ANSI_GREEN+"2"+ANSI_RESET);
                            System.out.println("Pour quitter le jeu tapé "+ANSI_RED+"3"+ANSI_RESET);
                            System.out.println("Que souhaitez vous faire?");
                            Scanner menuparti = new Scanner(System.in);
                            if (menuparti.hasNextInt()) {
                                int choixmenuparti = menuparti.nextInt();
                                if (choixmenuparti == 1 || choixmenuparti == 2) {
                                    Integer numberOfPlayers = choixmenuparti;
                                    Scanner sc = new Scanner(System.in);
                                    Player player1 = new Player("Player_1", "X", "");
                                    player1.Playerspersonnalisation("hhghgh");
                                    grid.setPlayer1(player1);
                                    grid.setActualPlayer(player1);


                                    if (numberOfPlayers == 2) {
                                        Player player2 = new Player("joueur2", "O", "");
                                        player2.Playerspersonnalisation(player1.getCaractere());
                                        grid.setPlayer2(player2);
                                    } else {
                                        Ia bot = new Ia("BOT", "O", "", 4);
                                        bot.iaspersonnalisation();
                                        bot.getDifficulty();
                                        grid.setPlayer2(bot);
                                    }


                                    System.out.println(player1.getCaractere());

                                    //lancement jeu avec parametre precedent
                                    grid.setPlayers(numberOfPlayers);
                                    while (grid.isPlay()) {
                                        if (numberOfPlayers == 2 || grid.getRound() % 2 != 0) {
                                            grid.printGrid();
                                            System.out.println("Choisissez la colonne sur laquelle vous voulez jouer :");
                                            int i = sc.nextInt();
                                            grid.handleFall(i - 1);
                                        } else {
                                            Ia player2 = (Ia) grid.getPlayer2();
                                            if (player2.getDifficulty() == 1) {
                                                grid.randomPlace();
                                            } else if (player2.getDifficulty() == 2) {
                                                grid.iaLvl2();
                                            } else if (player2.getDifficulty() == 3) {
                                                grid.iaLvl3();
                                            } else if (player2.getDifficulty() == 4) {
                                                grid.iaLvl4();
                                            }
                                        }
                                    }
                                    choixNbJoueurValide = true;
                                    choixvalide = true;
                                } else if (choixmenuparti == 2) {
                                    System.exit(0);
                                }else{
                                    System.out.println(ANSI_RED+"\nNombre Invalide! Veuillez réessayer!"+ANSI_RESET);
                                }
                            }else{
                                System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);
                            }
                        }



                    } else if (choixmenu == 2) {
                        File logDir = new File("puissance-4-java");
                        File[] files = logDir.listFiles();
                        for (File file : files) {
                            if (file.isFile() && file.getName().equals("File.txt")) {
                                //1Top10.affichageTopTen();
                                // Le fichier de log a été trouvé, vous pouvez faire quelque chose avec
                                break;
                            } else {
                                System.out.println("il ny a pas encore de tableau des scores");
                            }
                        }
                        choixvalide = true;
                    } else if (choixmenu == 3) {
                        System.exit(0);
                    }else{
                        System.out.println(ANSI_RED+"\nNombre Invalide! Veuillez réessayer!"+ANSI_RESET);
                    }
                }else{
                    System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);
                }
            }




            //demander de restart le jeu
            System.out.println("Voulez-vous relancer la partie ? \n"+ANSI_GREEN+"1) Oui "+ANSI_RESET+"\t"+ANSI_RED+"2) Non"+ANSI_RESET);
            Scanner restartScan = new Scanner(System.in);
            if(restartScan.hasNextInt()){
                int restartAnswer = restartScan.nextInt();
                if(restartAnswer == 1){
                    System.out.println("\n");
                    restart = true;
                }else{
                    restart = false;
                }
            }else{
                restart = false;
            }
        }

    }
}