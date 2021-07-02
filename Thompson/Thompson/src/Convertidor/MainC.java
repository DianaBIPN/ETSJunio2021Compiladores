/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Convertidor;
 import thompson.*;
/**
 *
 * @author Marisol
 */
public class MainC {
    
    public static void main(String[] args) throws Exception {
        Main A = new Main();
        AFN salida = A.probadorAfn();
        
        System.out.printf("AFN:\n%s", salida);
        System.out.println();
        AFD afd = Subconjuntos.getAFD(salida);
        System.out.printf("AFD:\n%s", afd);
        System.out.printf("\nEstados AFD:\n%s", afd.estadosDtoString());
        
        System.out.println();
        TablaTransicion tabla2 = afd.getTablaTransicion();
       
        for (int i=0; i < tabla2.getColumna(); i++)
            System.out.printf("%s\t\t", tabla2.getNombreColumna(i));
       
        System.out.println();
        for (int i=0; i < tabla2.getFila(); i++) {
            for (int j=0; j < tabla2.getColumna(); j++)
                System.out.printf("%s\t\t", tabla2.getValor(i, j));
           
            System.out.println();
        }
    }
    
    
    
}
