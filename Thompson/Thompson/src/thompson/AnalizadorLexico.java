/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import java.util.Hashtable;

/**
 *
 * @author Marisol
 */
public class AnalizadorLexico {
    private Alfabeto alfabeto;
    private String expRegular;
    private StringBuffer buffer;
    private Hashtable<String, TokenExp> tablaSimbolos;
    
    public AnalizadorLexico(Alfabeto alfabeto, String expRegular){
        this.alfabeto = alfabeto;
        this.expRegular = expRegular;
        this.buffer = new StringBuffer(expRegular);
        crearTablaSimbolos();
    }
    
    public Token bToken() throws Exception{
        String lexema = bLexema();
        
        if(lexema.matches("\\s"))
            return bToken();
        
        TokenExp tipoTokenExp = tablaSimbolos.get(lexema);
        
        if(tipoTokenExp == null)
            return new Token(tipoTokenExp.INVA, lexema);
        else if (tipoTokenExp == TokenExp.ALFABETO)
            return new Token(TokenExp.ALFABETO, lexema);
        else
            return new Token(tipoTokenExp);
        
    }
    
    public Alfabeto getAlfabeto() {
        return alfabeto;
    }
    
    public String getExpRegular() {
        return expRegular;
    }
    
    public String bLexema(){
        String salida = "";
        
        if(buffer.length() > 0){
            salida += buffer.charAt(0);
            buffer.deleteCharAt(0);
        }
        return salida;
    }
    
    private void crearTablaSimbolos(){
        tablaSimbolos = new Hashtable<String, TokenExp>();
        
        tablaSimbolos.put("|", TokenExp.ALTER);
        tablaSimbolos.put("*", TokenExp.CER_KLEENE);
        tablaSimbolos.put("+", TokenExp.CER_POS);
        tablaSimbolos.put("(", TokenExp.PAR_IZQ);
        tablaSimbolos.put(")", TokenExp.PAR_DER);
        tablaSimbolos.put("", TokenExp.FINAL);
            
        for (int i = 0; i < alfabeto.getCantidad(); i++) {
            String simbolo = alfabeto.getSimbolo(i);
            tablaSimbolos.put(simbolo, TokenExp.ALFABETO);
        }
        
    }
    
}
