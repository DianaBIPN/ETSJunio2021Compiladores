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
public abstract class ResultadoValidacion {
    protected Automata automata;
    protected String entrada;
    protected String entradaFaltante;
    
    public Automata getAutomata() {
        return automata;
    }
    
    public String getEntrada() {
        return entrada;
    }
    
    public abstract Conjunto getCamino();
    
    public String getEntradaFaltante() {
        return entradaFaltante;
    }
    public abstract boolean esValido();
}
