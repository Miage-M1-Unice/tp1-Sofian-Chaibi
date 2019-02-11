package fr.unice.miage;

import java.io.File;

public class Exercice1{

    public void listingFile(String nomFichier){
        File file =  new File(nomFichier);
        for (File nom: file.listFiles())
        {
            System.out.println(nom.getName());

            if(nom.isDirectory())
            {
                listingFile(nom.getAbsolutePath());
            }
            else
                System.out.println("---------");

        }

    }

    public static void main(String[] args){
        Exercice1 exo = new Exercice1();
        exo.listingFile(".");



    }
}
