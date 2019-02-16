package fr.unice.miage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

public class SeLit {

    void parcour(String path,FilenameFilter f) throws FileNotFoundException {
        File file =  new File(path);

        for (File nom : file.listFiles(f)) {

            if(nom.isDirectory()) {
                parcour(nom.getAbsolutePath(),f);
            }
            else {
                lecture(new Scanner(nom),nom.getName());
            }
        }
    }

    // Ne doit en aucun cas etre lu
    void lecture(Scanner source,String nom) {
        while(source.hasNextLine()) {
            String s = source.nextLine(); // lol
            if (s.contains("// ")) {
                if(!s.contains("\"//" )) {
                    String[] split = s.split("// ");
                    if (split.length == 2) {
                        s = split[0];
                        System.out.println("LU: " + s);
                    }
                }
                else {
                    System.out.println("LU: " + s);
                }
            }
            else
            {
                System.out.println("LU: " + s);
            }
            // A modifier
        }
        System.out.println("---------- Fin de lecture "+ nom + " -----------------");
    }

    public static void main(String[] args) throws FileNotFoundException {
        SeLit  seLit = new SeLit();
        FiltreRegex filtreRegex = new FiltreRegex(".java");
        seLit.parcour(".",filtreRegex);
    }
}
