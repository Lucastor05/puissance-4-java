package com.puissance4.modules;
import java.util.ArrayList;

public class CoordinateList {
    ArrayList<Coordinate> coordinates;

    CoordinateList() {
        coordinates = new ArrayList<Coordinate>();
    }

    void addCoordinate(int x, int y) {
        coordinates.add(new Coordinate(x, y));
    }

    boolean isEmpty() {
        return coordinates.isEmpty();
    }

    void clear(){
        coordinates.clear();
    }

    boolean contains(int x, int y) {
        for (Coordinate coordinate : coordinates) {
            if (coordinate.x == x && coordinate.y == y) {
                return true;
            }
        }
        return false;
    }

    void printList() {
        System.out.print("[");
        for (Coordinate c : coordinates) {
            System.out.print("[" + c.x + ", " + c.y + "], ");
        }
        System.out.println("]");
    }

    void merge(CoordinateList other) {
        coordinates.addAll(other.coordinates);
    }
}
