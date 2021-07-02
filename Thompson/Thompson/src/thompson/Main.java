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
public class Main {
    
    public static String muestraContenidoA(String archivoAlfabeto) throws FileNotFoundException, IOException{
        String cadena;
        String alfA = null;
        FileReader fr = new FileReader(archivoAlfabeto);
        BufferedReader br = new BufferedReader(fr);
        while((cadena = br.readLine()) != null){
            System.out.println("L= {" + cadena + "}" );
            alfA = cadena.replaceAll(",", "");
            System.out.println(alfA);
        }
        br.close();
        return(alfA);
    }

    public static String muestraContenidoE(String archivoExpresion) throws FileNotFoundException, IOException{
        String cadenaE;
        String alfE=null;
        FileReader fr = new FileReader(archivoExpresion);
        BufferedReader br = new BufferedReader(fr);
        while((cadenaE = br.readLine()) != null){
            System.out.println(cadenaE);
            alfE = cadenaE;
        }
        br.close();
        return(alfE);
    }
    
    public AFN probadorAfn() throws IOException, Exception{
        Alfabeto alfA = new Alfabeto(muestraContenidoA("C:\\Users\\Marisol\\Desktop\\ETSJunio2021Compiladores\\Thompson\\archivoAlfabeto.txt"));
        String alfE = muestraContenidoE("C:\\Users\\Marisol\\Desktop\\ETSJunio2021Compiladores\\Thompson\\archivoExpresion.txt");
        AnalizadorSintactico anaS = new AnalizadorSintactico(alfA, alfE);
        
        AFN salida = anaS.analizar();
        return salida;
    }
 
    public static void main(String[] args) throws Exception{
        
        Alfabeto alfA = new Alfabeto(muestraContenidoA("C:\\Users\\Marisol\\Desktop\\ETSJunio2021Compiladores\\Thompson\\archivoAlfabeto.txt"));
        String alfE = muestraContenidoE("C:\\Users\\Marisol\\Desktop\\ETSJunio2021Compiladores\\Thompson\\archivoExpresion.txt");
        AnalizadorSintactico anaS = new AnalizadorSintactico(alfA, alfE);
        
        AFN salida = anaS.analizar();
        System.out.printf("AFN:\n%s", salida);
        
        System.out.println();
        TablaTransicion tabla = salida.getTablaTransicion();
        
               
        for (int i=0; i < tabla.getColumna(); i++)
            System.out.printf("%s\t", tabla.getNombreColumna(i));
       
        System.out.println();
        for (int i=0; i < tabla.getFila(); i++) {
            for (int j=0; j < tabla.getColumna(); j++)
                System.out.printf("%s\t", tabla.getValor(i, j));
           
            System.out.println();
        }
        
    }
}
