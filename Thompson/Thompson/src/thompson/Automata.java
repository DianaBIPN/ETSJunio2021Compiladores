/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import java.io.ObjectStreamConstants;

/**
 *
 * @author Marisol
 */
public abstract class Automata {
    
    protected Conjunto<Estado> estados;
    protected String expReg;
    protected Alfabeto alfabeto;
    protected String LogProceso;
    
    protected Automata(){
        this(null,"");
    }
    
    protected Automata(Alfabeto alfabeto, String expReg){
        estados = new Conjunto<Estado>();
        setAlfabeto(alfabeto);
        setExpReg(expReg);
    }
    
    public String getLogProceso(){
        return LogProceso;
    }
    
    public void setLogProceso(String LogProceso){
        this.LogProceso = LogProceso;
    }
    
    public Alfabeto getAlfabeto(){
        return alfabeto;
    }
    
    public void setAlfabeto(Alfabeto alfabeto){
        this.alfabeto = alfabeto;
    }
    
    public String getExpReg(){
        return expReg;
    }
    
    public void setExpReg(String expReg){
        this.expReg = expReg;
    }
    
    public Estado getEstadoInicial() {
        return estados.obtenerPrimero();
    }
    
    public Conjunto<Estado> getEstadosFinales(){
        Conjunto<Estado> finales = new Conjunto<Estado>();
        
        for (Estado tmp : estados) 
            if(tmp.getFinal())
                finales.agregar(tmp);
            
        return finales;
    }
    
    public Conjunto<Estado> getEstadosNoFinales(){
        Conjunto<Estado> noFinales = new Conjunto<Estado>();
        
        for (Estado tmp : estados) 
            if(!tmp.getFinal())
                noFinales.agregar(tmp);
            
        return noFinales;
    }
    
    public void agregarEstado(Estado estado){
        estados.agregar(estado);
    }
    
    public Estado getEstado(int posicion){
        return estados.obtener(posicion);
    }
    
    public Conjunto<Estado> getEstados(){
        return estados;
    }
    
    public int cantidadEstados(){
        return estados.cantidad();
    }
    
    public void empezarRecorrido(){
        for(Estado tmp : estados)
            tmp.setVisitado(false);
    }
    
    
    
}