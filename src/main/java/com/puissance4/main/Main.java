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
                //1er menu
                System.out.println("\nMenu :");
                System.out.println(" - Pour jouer taper "+ANSI_GREEN+"1"+ANSI_RESET);
                System.out.println(" - Pour voir le Top 10 des meilleurs parties de Puissance 4 taper "+ANSI_GREEN+"2"+ANSI_RESET);
                System.out.println(" - Pour quitter le jeu taper "+ANSI_RED+"3"+ANSI_RESET);
                System.out.println("Que souhaitez vous faire?");
                Scanner menu = new Scanner(System.in);

                if (menu.hasNextInt()) {//si la reponse est un nombre entier
                    int choixmenu = menu.nextInt();
                    if (choixmenu == 1) {//si le joueur descide de vouloir lancer une partie
                        boolean choixNbJoueurValide = false;

                        while(!choixNbJoueurValide) {//tant que la reponse donne au prochain menu n'est pas valide
                            //2eme menu
                            System.out.println("\nMenu de parti :");
                            System.out.println(" - Pour jouer contre une IA taper "+ANSI_GREEN+"1"+ANSI_RESET);
                            System.out.println(" - Pour jouer contre un autre joueur taper "+ANSI_GREEN+"2"+ANSI_RESET);
                            System.out.println(" - Pour quitter le jeu taper "+ANSI_RED+"3"+ANSI_RESET);
                            System.out.println("Que souhaitez-vous faire ?");
                            Scanner menuparti = new Scanner(System.in);
                            if (menuparti.hasNextInt()) {//si la reponse est un nombre entier
                                int choixmenuparti = menuparti.nextInt();
                                if (choixmenuparti == 1 || choixmenuparti == 2) {//si le joueur descide de ne pas quitter
                                    int numberOfPlayers = choixmenuparti;
                                    Scanner sc = new Scanner(System.in);
                                    Player player1 = new Player("Player_1", "X", "");//creation de l'objet joueur1
                                    player1.Playerspersonnalisation("hhghgh");//demande au joueur 1 si il veut personnaliser sont personnage
                                    grid.setPlayer1(player1);//initialise le joueur 1 comme joueur dans le plateau
                                    grid.setActualPlayer(player1);//met la variable joueur actuel a joueur 1


                                    if (numberOfPlayers == 2) { //si le joueur descide de vouloir lancer une partie contre un autre joueur (humain)

                                        Player player2 = new Player("joueur2", "O", "");//creation de l'objet joueur2
                                        player2.Playerspersonnalisation(player1.getCaractere());//demande au joueur 2 si il veut personnaliser sont personnage
                                        grid.setPlayer2(player2);//initialise le joueur 2 comme joueur dans le plateau

                                    } else {//si le joueur descide de vouloir lancer une partie contre une ia

                                        Ia bot = new Ia("BOT", "O", "", 4);//cree un objet ia
                                        bot.iaspersonnalisation();//demande a l'utilisateur de choisir la difficulté
                                        bot.getDifficulty();//recupere la difficulté choisi
                                        grid.setPlayer2(bot);//initialise l'ia comme joueur dans le plateau

                                    }
                                    //lancement jeu avec parametre precedent
                                    grid.setPlayers(numberOfPlayers);
                                    while (grid.isPlay()) {//si le jeu est en cour
                                        if (numberOfPlayers == 2 || grid.getRound() % 2 != 0) {//si le nombre de joueur est 2 ou que c'est au tour du joueur 1
                                            grid.printGrid();//affiche la grille
                                            System.out.println("Choisissez la colonne sur laquelle vous voulez jouer :");
                                            int i = sc.nextInt();
                                            grid.handleFall(i - 1);//place le pions et check si le joueur a gagner
                                        } else {//si c'est au tour de l'ia
                                            Ia player2 = (Ia) grid.getPlayer2();
                                            //faire jouez differente fonction de l'ia celon la difficulté choisi
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
                                } else if (choixmenuparti == 3) {
                                    System.exit(0);
                                }else{
                                    System.out.println(ANSI_RED+"\nNombre invalide ! Veuillez réessayer!"+ANSI_RESET);
                                }
                            }else{
                                System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);
                            }
                        }
                    } else if (choixmenu == 2) {//si l'utilisateur souhaite voir le top 10
                        Top10.lecturedeFile();
                        Top10.comparaisonDeScoreEtTri();
                        choixvalide = true;
                    } else if (choixmenu == 3) {//si il souhaite quitter
                        System.exit(0);
                    }else{
                        System.out.println(ANSI_RED+"\nNombre invalide ! Veuillez réessayer!"+ANSI_RESET);//message d'erreur
                    }
                }else{
                    System.out.println(ANSI_RED+"\nCeci n'est pas un nombre! Veuillez réessayer!"+ANSI_RESET);//message d'erreur
                }
            }




            //demander de restart le jeu
            System.out.println("Voulez-vous relancer la partie ? \n"+ANSI_GREEN+"1) Oui "+ANSI_RESET+"\t"+ANSI_RED+"2) Non"+ANSI_RESET);
            Scanner restartScan = new Scanner(System.in);
            if(restartScan.hasNextInt()){
                int restartAnswer = restartScan.nextInt();
                if(restartAnswer == 1){
                    System.out.println("\n");
                    grid.clearTable();
                    grid.setPlay(true);
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