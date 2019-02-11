package fr.unice.miage;

import java.io.File;
import java.io.FilenameFilter;

public class Filtre implements FilenameFilter {

    private String regex;

    public Filtre(String regex) {
        this.regex = regex;
    }


    @Override
    public boolean accept(File file, String name) {
        File f = new File(file.getPath()+"/"+name);
        return name.endsWith(regex)|| f.isDirectory() ;
    }

}
