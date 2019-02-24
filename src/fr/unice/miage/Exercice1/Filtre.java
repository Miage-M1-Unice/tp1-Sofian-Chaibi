package fr.unice.miage.Exercice1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class Filtre implements FilenameFilter {

    private String regex;

    private Pattern p;

    public Filtre(String regex) {
        this.regex = regex;
    }


    @Override
    public boolean accept(File file, String name) {
        File f = new File(file.getPath()+"/"+name);
        return name.matches(regex)|| f.isDirectory() ;
    }

}
