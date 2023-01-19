package com.puissance4.modules;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    //on définit une class comme pour les player ou on met deux élément une string pour le pseudo et un integer pour le score
    class StringIntegerPair implements Comparable<StringIntegerPair> {
        private String string;
        private int integer;

        public StringIntegerPair(String string, int integer) {
            this.string = string;
            this.integer = integer;
        }

        public int getInteger() {
            return integer;
        }

        public String getString() {
            return string;
        }

        //juste en dessous ca me permet de comparer deux élement pour saoir qui est supérieur ou inférieur (j'utilise le integer de la class pour comparé)
        public int compareTo(StringIntegerPair o) {
            if(this.integer > o.integer) {
                return -1;
            } else if(this.integer < o.integer) {
                return 1;
            } else {
                return 0;
            }
        }




    }


