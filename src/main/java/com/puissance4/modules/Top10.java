package com.puissance4.modules;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.List;
import java.lang.*;


public class Top10 {
    //createfiletxt appel writeFileContents en gérant des erreurs avec IOException et prend les parametres utile comme le score et le pseudo
    public static void createfiletxt(String name, Integer score) {
        try {
            writeFileContents(name, score);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    //writeFile Contents attribue la chaine de caractere qu'on va ecrire dans File.txt
    public static void writeFileContents(String name, Integer score) throws IOException {
        try (FileWriter fileWriter = new FileWriter("File.txt", true)) {
            fileWriter.write(name+" "+score+"\n");
            fileWriter.close();
            lecturedeFile();
        }
    }
    //lecturedeFile permet d'avoir le top10 d'afficher
    public static void lecturedeFile() {
        try {
            File file = new File("File.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
            scanner.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //comparaisonDeScoreEtTri permet de comparet les score existant dans le fichier .txt de les séparer en deux colonnes  avec la class StringIntegerPair et de les afficher par ordre croissant (du meilleur joueur avec le meilleur score au pire joueur avec le pire score
    public static void comparaisonDeScoreEtTri() {
        List<StringIntegerPair> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("File.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {//continue temp qu'il y a des ligne a lire
                String[] parts = line.split(" ");//split pour recuperer joueur et score separer par des points
                String string = parts[0];//les joeueur
                int integer = Integer.parseInt(parts[1]);//les scores
                list.add(new StringIntegerPair(string, integer));//ajouts a la liste temporaire des données recuperer
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(list);//on tri la liste par ordre croissant
        List<StringIntegerPair> top10 = list;//on fait une condition du nombre d'element du top10
        Collections.reverse(top10);//on les inverse pour avoir les plus petit score en premier car les plus petis scores sont les meilleurs dans ce jeu
        for (StringIntegerPair pair : top10) {
            System.out.println(pair.getString() + " " + pair.getInteger());//on affiche le top 10
        }
    }
}



