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
    
    public abstract TablaTransicion getTablaTransicion();
    
    protected TablaTransicion llenarTablaTransicion(int cantFil, int cantCol, int colDesde) {
        String[] cabecera = new String[cantCol];
        Object[][] datos = new Object[cantFil][cantCol];
        cabecera[colDesde] = "Estados";
       
        for (int i=colDesde + 1; i < cantCol; i++)
            cabecera[i] = getAlfabeto().getSimbolo(i - colDesde - 1);
       
        for (int i=0; i < cantFil; i++)
            datos[i][colDesde] = getEstado(i);
       
        for (Estado e : getEstados()) {
            int fil = e.getIdentificador();
           
            for (Transicion t : e.getTransiciones()) {
                int col = getAlfabeto().obtenerPosicion(t.getSimbolo());
               
                if (datos[fil][col + colDesde + 1] == null)
                    datos[fil][col + colDesde + 1] = new Conjunto<Integer>();
               
                int id = t.getEstado().getIdentificador();
                ((Conjunto<Integer>) datos[fil][col + colDesde + 1]).agregar(id);
            }
        }
       
        String vacio = "";
        for (int i=0; i < cantFil; i++) {
            for (int j=colDesde + 1; j < cantCol; j++) {
                if (datos[i][j] == null)
                    datos[i][j] = vacio;
                else {                   
					Conjunto c = (Conjunto) datos[i][j];
                    if (c.cantidad() == 1)
                        datos[i][j] = c.obtenerPrimero();
                }
            }
        }
       
        return new TablaTransicion(cabecera, datos);
    }
    
    public String toString(){
        String str = "";
         
        for (Estado tmp : getEstados()) {
            str += tmp.toString();
            
            for (Transicion transicion : tmp.getTransiciones())
                str += "=>" + transicion.getEstado() + "{" + transicion.getSimbolo() + "}";
            
            str += "\n";
        }
        return str;
    }
    
    public static void copiarEstados(Automata autFiOrigen, Automata autFiDestino, int incremento){
        copiarEstados(autFiOrigen, autFiDestino, incremento, 0);
    }
    
    public static void copiarEstados(Automata autFiOrigen, Automata autFiDestino, int incrementoT, int omitidos){
        int incrementoEstado = incrementoT;
        
         for (int i = omitidos; i < autFiOrigen.cantidadEstados(); i++)
             autFiDestino.agregarEstado(new Estado(autFiDestino.cantidadEstados()));
         
         int cont = 0;
         
         for(Estado tmp : autFiOrigen.getEstados()){
             if(omitidos > cont++)
                 continue;
             
             Estado objetivo = autFiDestino.getEstado(tmp.getIdentificador() + incrementoEstado);
             copiarTransiciones(autFiDestino, tmp.getTransiciones(), objetivo, incrementoT);
         }     
    }
     
    public static void copiarTransiciones(Automata autFiDestino, Conjunto<Transicion> transiciones,
                        Estado objetivo, int incrementoT) {
       
        for (Transicion trans : transiciones) {
            int idDestino = trans.getEstado().getIdentificador();
            String simbolo = trans.getSimbolo();

            Estado estadoDestino = autFiDestino.getEstado(idDestino + incrementoT);
            Transicion nuevaTrans = new Transicion(estadoDestino, simbolo);

            objetivo.getTransiciones().agregar(nuevaTrans);
        }
    }
    
}