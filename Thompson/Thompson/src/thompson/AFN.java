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
public class AFN extends Automata{

    public AFN() {
        super();
    }
    
    public AFN(Alfabeto alfabeto, String expReg){
        super(alfabeto, expReg);
    }
    
    public TablaTransicion getTablaTransicion(){
        int cantFila = getEstados().cantidad();
        int cantColumna = getAlfabeto().getCantidad() + 2;
        
        return llenarTablaTransicion(cantFila, cantColumna, 0);
    }
    
}
