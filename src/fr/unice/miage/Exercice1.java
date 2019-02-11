package fr.unice.miage;

import java.io.File;
import java.io.FilenameFilter;

public class Exercice1{

    public void listingFile(String nomFichier){
        File file =  new File(nomFichier);
        for (File nom : file.listFiles()) {
            System.out.println(nom.getName());
            if(nom.isDirectory()) {
                listingFile(nom.getAbsolutePath());
            }
            else {
                System.out.println("---------");
            }
        }

    }

    public void listingFile(String nomFichier,Filtre f){
        File file =  new File(nomFichier);

        for (File nom : file.listFiles(f)) {

            if(nom.isDirectory()) {
                listingFile(nom.getAbsolutePath(),f);
            }
            else {
                System.out.println(nom.getName());
            }
        }

    }

    public static void main(String[] args){
        Exercice1 exo = new Exercice1();
        Filtre filtre = new Filtre(".java");
        exo.listingFile(".",filtre);
    }
}
