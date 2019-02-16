package fr.unice.miage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Exercice2 {


    public class SeLit {
        void lecture(Scanner source) {

            while(source.hasNextLine()) {
                String s = source.nextLine();
               System.out.println("LU: "+s);
               // A modifier
            }
        }
    }

    public static void main(String[] args) {
        // A compléter
    }
}
