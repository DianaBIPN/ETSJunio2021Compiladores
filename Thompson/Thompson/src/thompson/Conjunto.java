/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Marisol
 * @param <T>
 */
public class Conjunto<T extends Comparable<T>>
    implements Iterable<T>, Comparable<Conjunto<T>> {
        private Vector<T> elementos;
        private boolean ordenado;
        
        public Conjunto() {
        elementos = new Vector<T>();
        ordenado = true;
    }
        
        public void agregar(T elemento){
            if(!estaVacio() && obtenerUltimo().compareTo(elemento) > 0)
                ordenado = false;
            
            elementos.add(elemento);
        }
        
        public void eliminar(T elemento) {
            boolean eliminado = elementos.remove(elemento);
            
            if (eliminado && !getOrdenado())
                ordenado = verificarOrden();
        }
        
        public T obtener(int i){
            return elementos.get(i);
        }
        
        public int obtenerPosicion(T elemento){
            return elementos.indexOf(elemento);
        }
        
        public T obtenerPrimero(){
            return elementos.firstElement();
        }
        
        public T obtenerUltimo(){
            return elementos.lastElement();
        }
        
        
        
        public int cantidad(){
            return elementos.size();
        }
        
        public boolean estaVacio(){
            return cantidad() == 0;
        }

        @Override
        public Iterator<T> iterator() {
            return elementos.iterator();
        }
    
        public boolean contiene(T elemento){
            return elementos.contains(elemento);
        }
    
        public boolean retener(Conjunto<T> subconjunto){
            return elementos.retainAll(subconjunto.elementos);
        }
    
        public void ordenar(){
            Object[] arregloTemp = elementos.toArray();
            Arrays.sort(arregloTemp);
        
            elementos.clear();
            for (Object e : arregloTemp)
                elementos.add((T) e);
        }

        public boolean getOrdenado(){
            return ordenado;
        }
    
        private boolean verificarOrden() {
            if (estaVacio())
                return true;
       
            T actual = obtenerPrimero();
       
            for (int i=1; i < cantidad(); i++) {
                T sgte = obtener(i);
           
                if (actual.compareTo(sgte) > 0)
                    return false;
           
                actual = sgte;
            }
       
            return true;
        }
    
        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
       
            if (getClass() != obj.getClass())
                return false;
       
            final Conjunto<T> other = (Conjunto<T>) obj;
            return this.elementos.equals(other.elementos);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + (this.elementos != null ? this.elementos.hashCode() : 0);
            return hash;
        }
   
        @Override
        public String toString() {
            return elementos.toString();
        }

        public int compareTo(Conjunto<T> obj) {
            if (!getOrdenado())
                ordenar();
       
            if (!obj.getOrdenado())
                obj.ordenar();
       
            for (int i=0;; i++) {
                if (cantidad() > i && obj.cantidad() > i) {
                    T a = obtener(i);
                    T b = obj.obtener(i);
                    int cmp = a.compareTo(b);
                    if (cmp != 0)
                        return cmp;
                }
            else if (cantidad() > i)
                return 1;
            else if (obj.cantidad() > i)
                return -1;
            else 
                return 0;
            }
        }
        
    }
    
