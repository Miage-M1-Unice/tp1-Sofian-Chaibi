package fr.unice.miage.Exercice1;

import java.io.*;
import java.util.Scanner;

public class SeLit {

    // parcours d'une arborescence et lecture des fichier filté
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

    // lecture d'un fichier

    void lecture(Scanner source,String nom) {
        while(source.hasNextLine()) {
            String s = source.nextLine();
            // regarde la presence de commentaire avec le //
            if (s.contains("// ")) {
                // verifie qu'il s'agit bien d'un commentaire
                if(!s.contains("\"//" )) {
                    // split dans le cas ou il se situerait en fin de ligne a lire
                    String[] split = s.split("// ");
                    if (split.length == 1) {
                        s = split[0]; // ne s'affichera pas
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

    public static void main(String[] args) throws IOException {
        SeLit  seLit = new SeLit();
        PrintStream sortiStandard = System.out; // recuperation de la sortie standard
        File output = new File("./Output.txt");
        output.createNewFile(); // creation du fichier sortie
        System.setOut(new PrintStream(output)); // modification de la sortie
        FiltreRegex filtreRegex = new FiltreRegex(".java");
        seLit.parcour(".",filtreRegex);
        System.setOut(sortiStandard); // reset de la sortie standard
        seLit.lecture(new Scanner(output),output.getName()); // lecture du fichier créé
    }
}
