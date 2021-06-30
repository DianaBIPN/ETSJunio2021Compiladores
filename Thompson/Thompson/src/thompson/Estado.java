/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import java.util.HashMap;
/**
 *
 * @author Marisol
 */
public class Estado implements Comparable<Estado>{
    private int identificador;
    private boolean esFinal;
    private String etiqueta;
    private Conjunto<Transicion> transiciones;
    private boolean visitado;
    
    public Estado(int identificador){
        this(identificador,false);
    }
    
    public Estado(int identificador, boolean esFinal){
        setIdentificador(identificador);
        setFinal(esFinal);
        setEtiqueta(String.valueOf(identificador));
        transiciones = new Conjunto<Transicion>();
    }
    
    public void setIdentificador(int identificador){
        this.identificador = identificador;
    }
    
    public int getIdentificador(){
        return identificador;
    }
    
    public String getEtiqueta(){
        return etiqueta;
    }
    
    public void setEtiqueta(String etiqueta){
        this.etiqueta = etiqueta;
    }
    
    public boolean getFinal(){
        return esFinal;
    }
    
    public void setFinal(boolean esFinal){
        this.esFinal  = esFinal;
    }
    
    
    public boolean getInicial(){
        return identificador == 0;
    }
    
    public Conjunto<Transicion> getTransiciones(){
        return transiciones;
    }
    
    public HashMap<String,Estado> getTransicionesPorAlfabeto(Alfabeto alfabeto){
        HashMap<String, Estado> tran = new HashMap<String, Estado>();
        
        for(String s : alfabeto)
            tran.put(s, null);
        
        for (Transicion t : getTransiciones())
            tran.put(t.getSimbolo(), t.getEstado());
        
        return tran;
    }
    
    public boolean getVisitado(){
        return visitado;
    }
    
    public void setVisitado(boolean visitado){
        this.visitado = visitado;
    }
    
    public boolean getIdentidad() {
        for (Transicion trans : getTransiciones())
            if (!this.equals(trans.getEstado()))
                return false;
       
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
       
        if (getClass() != obj.getClass())
            return false;
       
        final Estado other = (Estado) obj;
        if (this.identificador == other.identificador)
            return true;
       
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.identificador;
        return hash;
    }

    public int compareTo(Estado obj) {
        return this.identificador - obj.identificador;
    }
   
    @Override
    public String toString() {
        String valor;
       
        if (getEtiqueta().equals(""))
            valor = String.valueOf(identificador);
        else
            valor = getEtiqueta();
       
        if (getInicial())
            valor += "i";
       
        if (getFinal())
            valor += "f";
               
        return valor;
    }


}
