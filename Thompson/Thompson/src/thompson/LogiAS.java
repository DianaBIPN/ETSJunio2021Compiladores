/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import java.util.Vector;
/**
 *
 * @author Marisol
 */
public class LogiAS {
    private Vector<String> cadenas;
    
    public LogiAS(){
        cadenas = new  Vector<String>();
    }
    
    public LogiAS agregar(String linea) {
        cadenas.add(linea);
        return this;
    }
    
    public LogiAS nuevaLinea() {
        cadenas.add("\n");
        return this;
    }
    
    public LogiAS vaciar() {
        cadenas.clear();
        return this;
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for (String s : cadenas)
            str.append(s);
       
        return str.toString();
    }
}
