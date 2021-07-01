/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
 *
 * @author Marisol
 */
public class TablaTransicion {
    private Object [][] datos;
    private String[] inicio;
    
    public TablaTransicion(String[] inicio, Object[][] datos){
        this.datos = datos;
        this.inicio = inicio;
    }
    
    public int getFila(){
        return datos.length;
    }
    
    public int getColumna(){
        return inicio.length;
    }
    
    public String getNombreColumna(int encabezadoColumna){
        return inicio[encabezadoColumna];
    }
    
    public Class<?> getNombreClass(int encabezadoColumna){
        return getValor(0, encabezadoColumna).getClass();
    }
    
    public boolean celdaEdit(int encabezadoFila, int encabezadoColumna){
        return false;
    }
    
    public Object getValor(int encabezadoFila, int encabezadoColumna ){
        return datos[encabezadoFila] [encabezadoColumna];
    }
    
    public void setValor(Object Valor, int encabezadoFila, int encabezadoColumna){
        datos[encabezadoFila] [encabezadoColumna] = Valor;
    }
    
    public void setEncabezado(String valor, int encabezadoColumna){
        inicio[encabezadoColumna] = valor;
    }
    
    public void addTableModelListener(TableModelListener listener){
        return;
    }
    
    public void removeTableModelListener(TableModelListener listener){
        return;
    }
}
