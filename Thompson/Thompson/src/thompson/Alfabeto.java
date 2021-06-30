/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Marisol
 */
public class Alfabeto implements Iterable<String>{

    public static final String VACIO = "E";
    private Vector<String> simbolos;
    
    public Alfabeto(String caracteres) {      
        String[] arregloTemp = new String[caracteres.length()];
        for (int i=0; i < caracteres.length(); i++)
            arregloTemp[i] = "" + caracteres.charAt(i);
       
        Arrays.sort(arregloTemp);
       
        simbolos = new Vector<String>(arregloTemp.length);
        for (int i=0; i < arregloTemp.length; i++) {
            String temp = arregloTemp[i];
            if (!simbolos.contains(temp))
                simbolos.add(temp);
        }  
    }
    
    public int getCantidad() {
        return simbolos.size();
    }
    
    public String getSimbolo(int pos) {
        if (pos == getCantidad())
            return Alfabeto.VACIO;
        else
            return simbolos.get(pos);
    }
    
    public boolean contiene(String caracter) {
        return simbolos.contains(caracter);
    }
    
     public int obtenerPosicion(String caracter) {
        if (caracter.equals(Alfabeto.VACIO))
            return getCantidad();
        else
            return simbolos.indexOf(caracter);
    }
   
    @Override
    public String toString() {
        String salida = "{";
       
        for (int i=0; i < this.getCantidad(); i++) {
            salida += simbolos.get(i);
           
            if (i < this.getCantidad()-1)
                salida += ", ";
        }
       
        return salida;
    }
   
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
       
        if (getClass() != obj.getClass()) {
            return false;
        }
       
        final Alfabeto other = (Alfabeto) obj;
       
        if (other.getCantidad() != this.getCantidad())
            return false;
       
        for (int i=0; i < this.getCantidad(); i++) {
            String tmp1 = this.getSimbolo(i);
            String tmp2 = other.getSimbolo(i);
           
            if (!tmp1.equals(tmp2))
                return false;
        }
       
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.simbolos != null ? this.simbolos.hashCode() : 0);
        return hash;
    }
    
    @Override
    public Iterator<String> iterator() {
        return simbolos.iterator();
    }
    
    
    
    
    
}

