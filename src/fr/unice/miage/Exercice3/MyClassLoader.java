package fr.unice.miage.Exercice3;

import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class MyClassLoader extends SecureClassLoader {
    private ArrayList<File> path = null;
    private String binDir ="\\out\\production\\Exercice3Projet2\\";

    public MyClassLoader(ArrayList<File> p) {
        this.path = p;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = new byte[0];
        try {
            b = loadClassData(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) throws ClassNotFoundException, IOException {
        ByteArrayOutputStream buffer= new ByteArrayOutputStream();
        String cheminClass = name.replace(".", File.separator) + ".class";
        File f;

        for (File path : path) {
            if (path.isDirectory()) {
                System.out.println(path.getPath() + binDir + cheminClass);
                try {
                    f = new File(path.getPath() + binDir + cheminClass);
                    if (f.exists()) {
                        buffer.write(Files.readAllBytes(f.toPath()));
                        return buffer.toByteArray();
                    } else
                        continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (path.getPath().endsWith(".jar")) {
                    cheminClass = name.replace(".", "/" ) + ".class";
                    JarFile jf = new JarFile(path);
                    InputStream c = jf.getInputStream(jf.getJarEntry(cheminClass));
                    while (c.available()>0)
                    {
                        buffer.write(c.read());
                    }
                    return buffer.toByteArray();
                }


            }


        }
        return null;
    }


    public static void main(String[] args) {
        ArrayList<File> al = new ArrayList<File>();
        al.add(new File("E:\\Projet"));
        al.add(new File("E:\\Projet\\Exercice3Projet2\\target\\Exercice3Projet2-1.0-SNAPSHOT.jar")); // ajout de Path
        MyClassLoader myCL = new MyClassLoader(al);
        try {
            Class c= myCL.loadClass("fr.unice.miage.Test");
            Constructor construct = c.getConstructor();
            Object object = construct.newInstance();
            System.out.println(object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}

