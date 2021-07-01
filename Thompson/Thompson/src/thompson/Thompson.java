/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

/**
 *
 * @author Marisol
 */
public class Thompson {

    public static AFN base(String simbolo){
        AFN afn = new AFN();
        Estado inicial = new Estado(0);
        Estado fin = new Estado(1, true);
        
        Transicion transicion = new Transicion(fin, simbolo);
        inicial.getTransiciones().agregar(transicion);
        
        afn.agregarEstado(inicial);
        afn.agregarEstado(fin);
        
        return afn;
    }
    
    public static AFN cerraduraKlenne(AFN afn){
        AFN afnSalida = new AFN();
        Estado nuevoInicioEstado = new Estado(afnSalida.cantidadEstados());
        
        afnSalida.agregarEstado(nuevoInicioEstado);
        Automata.copiarEstados(afn, afnSalida, afnSalida.cantidadEstados());
        Estado nuevoFinEstado = new Estado(afnSalida.cantidadEstados(),true);
        afnSalida.agregarEstado(nuevoFinEstado);
        nuevoInicioEstado.getTransiciones().agregar(new Transicion(afnSalida.getEstado(1),Alfabeto.VACIO));
        
    }
    
    
    
}