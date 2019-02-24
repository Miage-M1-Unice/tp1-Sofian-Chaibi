package fr.unice.miage.Exercice1;

import java.io.File;
import java.io.FilenameFilter;
import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiltreRegex implements FilenameFilter{

    private Pattern pattern ;
    private Matcher matcher ;

    public FiltreRegex (String chaine)
    {
        pattern = Pattern.compile(".*"+chaine+".*");

    }
    @Override
    public boolean accept(File dir, String name) {
        File f = new File(dir.getPath()+"/"+name);
        matcher = pattern.matcher(name);
        return matcher.matches()|| f.isDirectory() ;
    }
}
