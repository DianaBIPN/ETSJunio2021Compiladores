/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Convertidor;

import java.util.LinkedList;
import java.util.Queue;
import thompson.*;

/**
 *
 * @author Marisol
 */
public class Validacion {
    public static ResultadoValidacion validarAFN(AFN afn, String entrada) {
        Queue<String> buffer = new LinkedList<String>();
       
        for (int i=0; i < entrada.length(); i++)
            buffer.add("" + entrada.charAt(i));
       
        Conjunto<Par<Conjunto<Estado>, String>> camino = new Conjunto<Par<Conjunto<Estado>, String>>();
        Conjunto<Estado> estadosActuales = Subconjuntos.cerraduraEpsilon(afn.getEstadoInicial());
        camino.agregar(new Par<Conjunto<Estado>, String>(estadosActuales, ""));
        
        String simbolo;

        while ((simbolo = buffer.poll()) != null) {
            estadosActuales = Subconjuntos.cerraduraEpsilon(Subconjuntos.mover(estadosActuales, simbolo));
           
            if (estadosActuales.estaVacio()) {
                break;
            }
            else {
                camino.agregar(new Par<Conjunto<Estado>, String>(estadosActuales, simbolo));
            }
        }
        
        if (estadosActuales.estaVacio()) {
            String entradaFaltante = simbolo;
            
            for (String s : buffer)
                entradaFaltante += s;
            return new RValidacionAFN(afn, entrada, camino, entradaFaltante);
        }
        else {
            return new RValidacionAFN(afn, entrada, camino, "");
        }
    }

    public static ResultadoValidacion validarAFD(AFD afd, String entrada) {
        Queue<String> buffer = new LinkedList<String>();
 
        for (int i=0; i < entrada.length(); i++)
            buffer.add("" + entrada.charAt(i));

        Conjunto<Par<Estado, String>> camino = new Conjunto<Par<Estado, String>>();
        Estado estadoActual = afd.getEstadoInicial();
        camino.agregar(new Par<Estado, String>(estadoActual, ""));
 
        String simbolo;

        while ((simbolo = buffer.poll()) != null) {
            estadoActual = mover(estadoActual, simbolo);
           
            if (estadoActual == null) {
                break;
            }
            else {
                camino.agregar(new Par<Estado, String>(estadoActual, simbolo));
            }
        }

        if (estadoActual == null) {
            String entradaFaltante = simbolo;
            for (String s : buffer)
                entradaFaltante += s;
            return new RValidacionAFD(afd, entrada, camino, entradaFaltante);
        }
        else {
            return new RValidacionAFD(afd, entrada, camino, "");
        }
    }

    private static Estado mover(Estado origen, String simbolo) {
        for (Transicion t : origen.getTransiciones())
            if (t.getSimbolo().equals(simbolo))
                return t.getEstado();
       
        return null;
        
    }
}
