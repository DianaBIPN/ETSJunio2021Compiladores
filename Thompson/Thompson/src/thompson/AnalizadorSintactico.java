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
public class AnalizadorSintactico {
    //private LogiAS logiAS;
    private AnalizadorLexico analizadorLexico;
    private Token preAnS;
    private int contToken;
    
    public AnalizadorSintactico(Alfabeto alfabeto, String expReg) {
        analizadorLexico = new AnalizadorLexico(alfabeto, expReg);
        contToken = 0;
        //logiAS = new LogiAS();
    }
    
    public AFN analizar() throws Exception {
        preAnS = obtenerToken();
       
        if (preAnS.getIdentificador() == TokenExp.FINAL)
            error("Expresion regular vacia");
        //logiAS.vaciar();
        //logiAS.agregar("---------------------").nuevaLinea().nuevaLinea();
        
        AFN afn = ExpReg();
        afn.setAlfabeto(analizadorLexico.getAlfabeto());
        afn.setExpReg(analizadorLexico.getExpRegular());
        
        if (preAnS.getIdentificador() != TokenExp.FINAL)
            error("Caracter invalido");
       
        return afn;
    }
    
    private AFN ExpReg() throws Exception {
        //logiAS.agregar("ExpresionRegular -> Concatenar R1").nuevaLinea();
       
        AFN afn1 = Concatenar();
        AFN afn2 = R1();
       
        if (afn2 == null)
            return afn1;
        else
            return Thompson.union(afn1, afn2);
    }
    
    private AFN R1() throws Exception {
        if (preAnS.getIdentificador() == TokenExp.ALTER) {
            match(preAnS);
           
            //logiAS.agregar("R1 -> \"|\" Concatenar R1").nuevaLinea();
            AFN afn1 = Concatenar();
            AFN afn2 = R1();

            if (afn2 == null)
                return afn1;
            else
                return Thompson.union(afn1, afn2);
        }
        else {
            //logiAS.agregar("R1 -> " + Alfabeto.VACIO).nuevaLinea();
            return null;
        }
    }
        
    private AFN Concatenar() throws Exception {
        //logiAS.agregar("Concatenar -> Grupo R2").nuevaLinea();
       
        AFN afn1 = Grupo();
        AFN afn2 = R2();
       
        if (afn2 == null)
            return afn1;
        else
            return Thompson.concaAFN(afn1, afn2);
    }
    
    private AFN R2() throws Exception {
        switch (preAnS.getIdentificador()) {
            case PAR_IZQ:
            case ALFABETO:
               
                //logiAS.agregar("R2 -> Grupo R2").nuevaLinea();
                AFN afn1 = Grupo();
                AFN afn2 = R2();

                if (afn2 == null)
                    return afn1;
                else
                    return Thompson.concaAFN(afn1, afn2);
            default:
                //logiAS.agregar("R2 -> " + Alfabeto.VACIO).nuevaLinea();
                return null;
        }
    }
    
    private AFN Grupo() throws Exception {
        //logiAS.agregar("Grupo -> Elemento Operador").nuevaLinea();
       
        AFN afn = Elem();
        TokenExp operador = Oper();
       
        switch (operador) {
            case CER_KLEENE:
                return Thompson.cerraduraKlenne(afn);
            case CER_POS:
                return Thompson.cerraduraPositiva(afn);
            default:
                return afn;
        }
    }
    
    private TokenExp Oper() throws Exception {
        TokenExp operador;
       
        switch (preAnS.getIdentificador()) {
            case CER_KLEENE:
            case CER_POS:
                operador = preAnS.getIdentificador();
               
                //logiAS.agregar("Operador -> " + preAnS.getValor()).nuevaLinea();
                match(preAnS);
                break;
            default:
                //logiAS.agregar("Operador -> " + Alfabeto.VACIO).nuevaLinea();
                operador = TokenExp.INVA;
        }
        return operador;
    }
    
    private AFN Elem() throws Exception {
        AFN afn = null;
       
        switch (preAnS.getIdentificador()) {
            case PAR_IZQ:
                //logiAS.agregar("Elemento -> \"(\" ExprecionRegular \")\"").nuevaLinea();
               
                match(new Token(TokenExp.PAR_IZQ));
                afn = ExpReg();
                match(new Token(TokenExp.PAR_DER));
                break;
            case ALFABETO:
                //logiAS.agregar("Elemento -> SimpleElemento").nuevaLinea();
               
                afn = SimLen();
                break;
            default:
                error("Se espera parentesis de apertura o simbolo de alfabeto. " +
                    "Se encontro \"" + preAnS.getValor() + "\"");
        }
       
        return afn;
    }
    
    private AFN SimLen() throws Exception {
        String simbolo = preAnS.getValor();
       
        if (!analizadorLexico.getAlfabeto().contiene(simbolo)) {
            error("El simbolo \"" + simbolo +
                "\" no pertenece al alfabeto definido.");
        }
       
        //logiAS.agregar("SimpleElemento -> " + simbolo).nuevaLinea();
       
        AFN afn = Thompson.base(simbolo);
        match(preAnS);
        return afn;
    }
    
    private void match(Token entrada) throws Exception {
        if (preAnS.equals(entrada))
            preAnS = obtenerToken();
        else if (entrada.getIdentificador() == TokenExp.PAR_DER)
            error("Falta parentesis de cierre");
        else
            error("Caracter invalido");
    }
    
    private void error(String mensaje) throws Exception {
        String mensajeCompleto = "";
       
        mensajeCompleto += "Error de sintaxis\n";
        mensajeCompleto += "Caracter: " + preAnS.getValor() + "\n";
        mensajeCompleto += "Posicion: " + contToken + "\n";
        mensajeCompleto += "Mensaje : " + mensaje;
       
        throw new Exception(mensajeCompleto);
    }
    
    private Token obtenerToken() throws Exception {
        ++contToken;
        return analizadorLexico.bToken();
    }
    
   /* public LogiAS getLog() {
        return logiAS;
    }*/
}
