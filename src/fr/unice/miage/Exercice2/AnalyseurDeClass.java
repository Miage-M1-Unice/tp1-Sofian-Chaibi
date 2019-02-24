package fr.unice.miage.Exercice2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnalyseurDeClass {


    private Class cl;

    public AnalyseurDeClass(String nomDeClass) throws ClassNotFoundException {
        cl = Class.forName(nomDeClass);
    }

    public void afficherInfoClass()
    {
        System.out.println("----- entete ------");
        afficherEntete();
        System.out.println("----- Atributs ------");
        afficherAttribut();
        System.out.println("----- Constructeurs ------");
        afficherConstruteur();
        System.out.println("----- Methodes ------");
        afficherMethodes();

    }

    private void afficherMethodes() {
        Method[] methodes = cl.getDeclaredMethods();
        for (Method methode : methodes)
        {
            System.out.println(methode.toString());
        }
    }

    private void afficherConstruteur() {
        Constructor[] constructeurs = cl.getConstructors();
        for (Constructor constructeur : constructeurs)
        {
            System.out.println(constructeur.toString());
        }
    }

    private void afficherAttribut() {
        Field[] attributs = cl.getDeclaredFields();
        for (Field attribut : attributs)
        {
            System.out.println(attribut.toString());
        }
    }

    private void afficherEntete() {
        System.out.println(cl.getPackage());
        String chaine ="";
        chaine+=cl.getName()+" ";
        chaine+="extend "+cl.getSuperclass().getName()+" ";
        Object[] interfaces =cl.getInterfaces();
        for (Object interf : interfaces)
        {
            chaine+=interf.toString()+", ";
        }
        System.out.println(chaine);
    }

    public void GenericToString(Object obj,int profondeur)
    {
        System.out.println(obj.getClass().getName());
    }


    public static void main(String[] args) throws ClassNotFoundException {
        AnalyseurDeClass adc = new AnalyseurDeClass("java.awt.Point");
        adc.afficherInfoClass();
    }
}
