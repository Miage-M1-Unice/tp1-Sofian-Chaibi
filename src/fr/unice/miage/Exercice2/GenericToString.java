package fr.unice.miage.Exercice2;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class GenericToString {

    private String chaine;

    public GenericToString() {
        chaine = "";
    }

    public String toString(Object obj, int profondeur) throws IllegalAccessException {
        if (profondeur == 0 || obj == null) {
            return chaine + "]";
        }
        chaine += obj.getClass().getName() + "[";
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field attribut : obj.getClass().getDeclaredFields()) {
            attribut.setAccessible(true);
            chaine += attribut.getName() + "=";
            Object objField = attribut.get(obj);
            if (!attribut.getType().isPrimitive()) {
                if (attribut.getType().isArray()) {
                    chaine += "{";
                    for (int i = 0; i < Array.getLength(objField); i++) {
                        chaine += Array.get(objField, i).toString() + ",";
                    }
                    chaine += "}";
                    chaine += "; ";
                    continue;
                } else
                    return toString(objField, profondeur - 1);
            }
            chaine += objField.toString();
            chaine += "; ";
        }

        Class cl = obj.getClass().getSuperclass();
        while (cl !=null) {
                for (Field attribut : cl.getFields()) {
                    attribut.setAccessible(true);
                    chaine += attribut.getName() + "=";
                    Object objField = attribut.get(obj);
                    if (!attribut.getType().isPrimitive()) {
                        if (attribut.getType().isArray()) {
                            chaine += "{";
                            for (int i = 0; i < Array.getLength(objField); i++) {
                                chaine += Array.get(objField, i).toString() + ",";
                            }
                            chaine += "}";
                            chaine += "; ";
                            continue;
                        } else
                            return toString(objField, profondeur);
                    }
                    chaine += objField.toString();
                    chaine += "; ";
                }
            cl = cl.getSuperclass();
        }

        return toString(null, profondeur - 1);

    }

    /*public String LoadAttribute(String chaine, Field attribut){

    }*/
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        System.out.println(new GenericToString().toString(new Point(12, 24), 1));

        Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20, 30, 40}, 3);
        pol.getBounds();
        System.out.println(new GenericToString().toString(pol, 2));
    }
}
