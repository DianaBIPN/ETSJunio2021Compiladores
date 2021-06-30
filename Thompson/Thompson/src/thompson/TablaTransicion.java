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
    
    
}
