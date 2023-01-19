package com.puissance4.modules;
import java.util.ArrayList;

public class CoordinateList {
    ArrayList<Coordinate> coordinates;

    CoordinateList() {
        /*
        * Constructeur de la classe, cree une list
        * */
        coordinates = new ArrayList<Coordinate>();
    }

    void addCoordinate(int x, int y) {
        /*
        * Fonction qui ajoute un objet coordonnées dans la liste, prends en parametre le x et le y des coordonnées
        * */
        coordinates.add(new Coordinate(x, y));
    }

    boolean isEmpty() {
        /*
        * Fonction booléenne qui renvoit true si la liste est vide et fasle si elle ne l'est pas
        * */
        return coordinates.isEmpty();
    }

    void clear(){
        /*
        * Fonction qui vide la liste
        * */
        coordinates.clear();
    }

    boolean contains(int x, int y) {
        /*
        * Fonction booleenne qui renvoit true si les coordonnées entrées en parametre sont dans la liste et false si elles ne le sont pas
        * */
        for (Coordinate coordinate : coordinates) {
            if (coordinate.x == x && coordinate.y == y) {
                return true;
            }
        }
        return false;
    }

    void printList() {
        /*
        * Fonction qui affiche la liste type: [[x,y], [x,y], [x,y], [x,y]]
        * */
        System.out.print("[");
        for (Coordinate c : coordinates) {
            System.out.print("[" + c.x + ", " + c.y + "], ");
        }
        System.out.println("]");
    }

    void merge(CoordinateList other) {
        /*
        * Fonction qui permet de fusionner l'objet avec une liste donné en parametre
        * */
        coordinates.addAll(other.coordinates);
    }
}
