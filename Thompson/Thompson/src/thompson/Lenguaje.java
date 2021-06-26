/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import java.io.*;

/**
 *
 * @author Marisol
 */
public class Lenguaje {

    public static void muestraContenido (String archivoLenguaje) throws FileNotFoundException, IOException{
        String cadena;
        FileReader fr = new FileReader(archivoLenguaje);
        BufferedReader br = new BufferedReader(fr);
        while((cadena = br.readLine()) != null){
            System.out.println("L= {" + cadena + "}" );
            String alf = cadena.replaceAll(",", "");
            System.out.println(alf);
        }
        br.close();
    }
    public static void main(String [] args) throws IOException {
       muestraContenido("C:\\Users\\Marisol\\Desktop\\ETSJunio2021Compiladores\\Thompson\\archivoLenguaje.txt");
 } 
}

