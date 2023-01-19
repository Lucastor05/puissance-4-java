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
    //static List<Integer>[] matrice;
    public static void createfiletxt(String name, Integer score) {
        try {
            writeFileContents(name, score);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void writeFileContents(String name, Integer score) throws IOException {
        try (FileWriter fileWriter = new FileWriter("File.txt", true)) {
            fileWriter.write(name+" "+score+"\n");
            fileWriter.close();
            lecturedeFile();
        }
    }
    public static void lecturedeFile() {
        try {
            File file = new File("File.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //System.out.println(line);
            }
            scanner.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void comparaisonDeScoreEtTri() {
        List<StringIntegerPair> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("File.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String string = parts[0];
                int integer = Integer.parseInt(parts[1]);
                list.add(new StringIntegerPair(string, integer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(list);
        List<StringIntegerPair> top10 = list;
        Collections.reverse(top10);
        for (StringIntegerPair pair : top10) {
            System.out.println(pair.getString() + " " + pair.getInteger());
        }
    }
}



