/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.*;

/**
 *
 * @author Marisol
 */
public class Expresion {
    
    public static void muestraContenido (String archivoExpresion) throws FileNotFoundException, IOException{
        String cadenaE;
        FileReader fr = new FileReader(archivoExpresion);
        BufferedReader br = new BufferedReader(fr);
        while((cadenaE = br.readLine()) != null){
            System.out.println(cadenaE);
            String alfE = cadenaE.replaceAll("\\p{Punct}", "");
            System.out.println(alfE);
        }
        br.close();
    }
    public void verificaExpresion(String alfEx,String alfLe ){
        for(int x = 0; x < alfEx.length(); x++){
            for (int y = 0; y < alfLe.length(); y++) {
                if(alfEx.charAt(x) == alfLe.charAt(y) ){
                    System.out.println(alfEx.charAt(x) + "es parte del lenguaje");
                }
            }
        }
    }
    public static void main(String [] args) throws IOException {
       muestraContenido("C:\\Users\\Marisol\\Desktop\\ETSJunio2021Compiladores\\Thompson\\archivoExpresion.txt");
      // verificaExpresion(alfE,alfL);
 } 
    
    
    
}
