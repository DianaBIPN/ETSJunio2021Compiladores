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
        nuevoInicioEstado.getTransiciones().agregar(new Transicion(nuevoFinEstado, Alfabeto.VACIO));
        Estado antesFinal = afnSalida.getEstado(afnSalida.cantidadEstados()-2);
        antesFinal.getTransiciones().agregar(new Transicion(afnSalida.getEstado(1), Alfabeto.VACIO));
        antesFinal.getTransiciones().agregar(new Transicion(nuevoFinEstado, Alfabeto.VACIO));
        
        return afnSalida;    
    }
    
    public static AFN cerraduraPositiva(AFN afn){
        return concaAFN(afn, cerraduraKlenne(afn));
    }
    
    public static AFN union(AFN afn1, AFN afn2){
        Transicion transicion;
        AFN afn = new AFN();
        Estado nuevoInicioEstado = new Estado(afn.cantidadEstados());
        afn.agregarEstado(nuevoInicioEstado);    
        Automata.copiarEstados(afn1, afn, afn.cantidadEstados());
        Automata.copiarEstados(afn2, afn, afn.cantidadEstados());
        Estado nuevEstado = new Estado(afn.cantidadEstados(), true);
        afn.agregarEstado(nuevoInicioEstado);
        
        transicion = new Transicion();
        transicion.setEstado(afn.getEstado(1));
        transicion.setSimbolo(Alfabeto.VACIO);
        nuevoInicioEstado.getTransiciones().agregar(transicion);
        
        transicion = new Transicion();
        transicion.setEstado(afn.getEstado(afn1.cantidadEstados() + 1));
        transicion.setSimbolo(Alfabeto.VACIO);
        nuevoInicioEstado.getTransiciones().agregar(transicion);
        
        transicion = new Transicion();
        transicion.setEstado(afn.getEstado(afn.cantidadEstados() - 1));
        transicion.setSimbolo(Alfabeto.VACIO);
        afn.getEstado(afn1.cantidadEstados() - 2).getTransiciones().agregar(transicion);
        
        transicion = new Transicion();
        transicion.setEstado(afn.getEstado(afn.cantidadEstados() - 1));
        transicion.setSimbolo(Alfabeto.VACIO);
        afn.getEstado(afn1.cantidadEstados() - 2).getTransiciones().agregar(transicion);
        
        return afn;
        
    }
    
    public static AFN concaAFN(AFN afn1, AFN afn2){
        AFN afn = new AFN();
        Automata.copiarEstados(afn1, afn, afn.cantidadEstados());
        Estado ultimEstado = afn.getEstado(afn.cantidadEstados() - 1);
        Automata.copiarEstados(afn2, afn, afn.cantidadEstados() - 1, 1);
        Estado inicioAFN2 = afn2.getEstadoInicial();
        Automata.copiarTransiciones(afn, inicioAFN2.getTransiciones(), ultimEstado, ultimEstado.getIdentificador());
        afn.getEstado(afn.cantidadEstados() - 1).setFinal(true);
        
        return afn;
    }
    
}