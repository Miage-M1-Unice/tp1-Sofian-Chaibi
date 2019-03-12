package fr.unice.miage.Exercice3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderTest {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:E:\\Projet\\Exercice3Projet2\\target\\Exercice3Projet2-1.0-SNAPSHOT.jar")});
        Class c = urlClassLoader.loadClass("fr.unice.miage.Test");
        Constructor construct = c.getConstructor();
        Object object = construct.newInstance();
    }
}
