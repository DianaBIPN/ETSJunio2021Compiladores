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
public class RValidacionAFN extends ResultadoValidacion{
    private Conjunto<Par<Conjunto<Estado>, String>> camino;
    
    public RValidacionAFN(AFN automata, String entrada,
        Conjunto<Par<Conjunto<Estado>, String>> camino, String entradaFaltante) {
       
        this.automata = automata;
        this.entrada = entrada;
        this.camino = camino;
        this.entradaFaltante = (entradaFaltante == null) ? "" : entradaFaltante;
    }
    
    public Conjunto<Par<Conjunto<Estado>, String>> getCamino() {
        return camino;
    }
    
    public boolean esValido() {
        if (!entradaFaltante.equals(""))
            return false;
       
        for (Estado e : camino.obtenerUltimo().getPrimero())
            if (e.getFinal())
                return true;
       
        return false;
    }
}
