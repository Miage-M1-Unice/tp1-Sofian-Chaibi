package fr.unice.miage;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class Exercice1{

    public static String filtre; // pour la classe anonyme

    private class FiltreInterne implements FilenameFilter{

        private String regex;

        public FiltreInterne (String regex) {
            this.regex = regex;
        }

        @Override
        public boolean accept(File file, String name) {
            File f = new File(file.getPath()+"/"+name);
            return name.endsWith(regex)|| f.isDirectory() ;
        }

    }

    public static String getFiltre()
    {
        return filtre;
    }

    public static void setFiltre(String chaine)
    {
        filtre=chaine;
    }


    public void listingFile(String nomFichier,FilenameFilter f){
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

    public void listingFileInterne(String nomFichier,String trie){
        File file =  new File(nomFichier);
        FiltreInterne  f = this.new FiltreInterne(trie);
        for (File nom : file.listFiles(f)) {

            if(nom.isDirectory()) {
                listingFileInterne(nom.getAbsolutePath(),trie);
            }
            else {
                System.out.println(nom.getName());
            }
        }
    }

    public void listingFileAnonyme(String nomFichier,FilenameFilter f){
        File file =  new File(nomFichier);
        for (File nom : file.listFiles(f)) {

            if(nom.isDirectory()) {
                listingFileAnonyme(nom.getAbsolutePath(),f);
            }
            else {
                System.out.println(nom.getName());
            }
        }
    }


    public static void main(String[] args){
        Exercice1 exo = new Exercice1();
        String argument ;
        // permettant la lecture d'argument
        if(args.length ==1 ){
            argument=args[0];

        }
        else {
            argument=".java";
        }
        // filtre externe
        Filtre filtre = new Filtre(argument);
        exo.listingFile(".",filtre);
        System.out.println("------------------Filtre class externe---------------------");
        // filtre interne
        exo.listingFileInterne(".",argument);
        System.out.println("---------------------Filte class intern------------------");
        // filtre anonyme
        Exercice1.setFiltre(argument);
        FilenameFilter f = new FilenameFilter() {
            private String regex = Exercice1.getFiltre();
            @Override
            public boolean accept(File file, String name) {
                File f = new File(file.getPath()+"/"+name);
                return name.endsWith(regex)|| f.isDirectory() ;
            }
        };
        exo.listingFileAnonyme(".",f);
        System.out.println("-----------------Filtre class anonyme------------------------");
        // filtre regex
        FiltreRegex filtreRegex= new FiltreRegex(argument);
        exo.listingFile(".",filtreRegex);
        System.out.println("------------------Filtre class externe Regex---------------------");


    }
}
