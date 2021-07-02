/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Convertidor;

import  thompson.*;
/**
 *
 * @author Marisol
 */
public class RValidacionAFD extends ResultadoValidacion{
    private Conjunto<Par<Estado, String>> camino;
    
    public RValidacionAFD(AFD automata, String entrada,
        Conjunto<Par<Estado, String>> camino, String entradaFaltante) {
       
        this.automata = automata;
        this.entrada = entrada;
        this.camino = camino;
        this.entradaFaltante = (entradaFaltante == null) ? "" : entradaFaltante;
    }
    
    public Conjunto<Par<Estado, String>> getCamino() {
        return camino;
    }
    
    public boolean esValido() {
        if (!entradaFaltante.equals(""))
            return false;
       
        if (camino.obtenerUltimo().getPrimero().getFinal())
            return true;
        else
            return false;
    }
}
