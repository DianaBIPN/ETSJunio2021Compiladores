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
public class AFD extends Automata{
    private Conjunto<Conjunto<Estado>> estadosD;
    
    public AFD() {
       this(null, "");
    }
    
    public AFD(Alfabeto alfabeto, String expReg) {
        super(alfabeto, expReg);
        estadosD = null;
    }
    
    public Conjunto<Conjunto<Estado>> getEstadosD() {
        return estadosD;
    }
    
    public void setEstadosD(Conjunto<Conjunto<Estado>> estadosD) {
        this.estadosD = estadosD;
    }
   
    public String estadosDtoString() {
        String str = "";
       
        for (int i=0; i < estadosD.cantidad(); i++) {
            Conjunto<Estado> conj = estadosD.obtener(i);
            Estado actual = getEstado(i);
           
            str += actual + " --> " + conj + "\n";
        }
       
        return str;
    }

    @Override
    public TablaTransicion getTablaTransicion() {
        TablaTransicion tabla;
       
        if (getEstadosD() != null) {
            int cantFil = getEstados().cantidad();
            int cantCol = getAlfabeto().getCantidad() + 2;

            tabla = llenarTablaTransicion(cantFil, cantCol, 1);
            tabla.setEncabezado("Estados del AFN", 0);

            for (int i=0; i < estadosD.cantidad(); i++)
                tabla.setValor(estadosD.obtener(i), i, 0);
        }
        else {
            int cantFil = getEstados().cantidad();
            int cantCol = getAlfabeto().getCantidad() + 1;

            tabla = llenarTablaTransicion(cantFil, cantCol, 0);
        }
       
        return tabla;
    }
    
    
}
