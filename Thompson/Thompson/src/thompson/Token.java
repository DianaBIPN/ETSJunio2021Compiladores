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
public class Token {
    private TokenExp identificador;
    private String valor;
    
    public Token(TokenExp token) throws Exception{
        switch(token){
            case PAR_IZQ:
                identificador = TokenExp.PAR_IZQ;
                valor = "(";
                break;
            case PAR_DER:
                identificador = TokenExp.PAR_DER;
                valor = ")";
                break;
            case ALTER:
                identificador = TokenExp.ALTER;
                valor = "|";
                break;
            case CER_KLEENE:
                identificador = TokenExp.CER_KLEENE;
                valor = "*";
                break;
            case CER_POS:
                identificador = TokenExp.CER_POS;
                valor = "+";
                break;
            case CONCA:
                identificador = TokenExp.CONCA;
                valor = ".";
                break;
            case FINAL:
                identificador = TokenExp.FINAL;
                valor = "";
                break;
            default:
                throw new Exception("Token Invalido");
        }  
    }
    
    public Token(TokenExp token, String simbolo) throws Exception {
        switch (token) {
            case ALFABETO:
                identificador = TokenExp.ALFABETO;
                valor = simbolo;
                break;
            case INVA:
                identificador = TokenExp.INVA;
                valor = simbolo;
                break;
            default:
                throw new Exception("Token invalido");
        }
    }
    
    public TokenExp getIdentificador(){
            return identificador;
        }
    
    public String getValor(){
            return valor;
        }
    
    @Override
    public String toString() {
        return valor;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
       
        if (getClass() != obj.getClass()) {
            return false;
        }
       
        final Token other = (Token) obj;
        if (this.identificador != other.identificador){ 
            return false;
        }
       
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.identificador != null ? this.identificador.hashCode() : 0);
        hash = 59 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }
}
