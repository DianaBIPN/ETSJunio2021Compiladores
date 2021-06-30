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
class Transicion implements Comparable<Transicion> {
    
    private Estado estado;
    private String simbolo;
    
    public Transicion(Estado estado, String simbolo){
        this.estado = estado;
        this.simbolo = simbolo;
    }
    
    public Transicion(){
        this(null, null);
    }
    
    public Estado getEstado(){
        return estado;
    }
    
    public void setEstado(Estado estado){
        this.estado = estado;
    }
    
    public String getSimbolo(){
        return simbolo;
    }
    
    public void setSimbolo(String simbolo){
        this.simbolo = simbolo;
    }
    
    @Override
    public String toString() {
        return "(" + getEstado() + ", " + getSimbolo() + ")";
    }

     public int compareTo(Transicion obj) {
        Estado e1 = this.getEstado();
        Estado e2 = obj.getEstado();
   
        int diferencia = e1.getIdentificador()- e2.getIdentificador();
       
        if (diferencia != 0)
            return diferencia;
       
        String s1 = this.getSimbolo();
        String s2 = obj.getSimbolo();
       
        return s1.compareTo(s2);
    }
    
    
}
