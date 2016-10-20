package Controle.Verificador;

import Controle.generico.ParserConstants;
import Controle.sintatico.Sintatico;
import Controle.generico.Token;

/**
 * @author Tamires, Djonathan Krause
 */
public class Compilador implements ParserConstants {

    public Compilador() {
        
    }
    
    public void analisar() {
        
    }
    
    public String setErro(String message, int linha, String tipoErro, Sintatico sintatico) {
        String erro = "";
        Token currentToken = sintatico.getCurrentToken();
//        int expectedToken = sintatico.getExpectedToken();
        if (null != tipoErro) switch (tipoErro) {
            case "LE": // Léxico
                erro = "Erro na linha " + linha + " - " + message;
                break;
            case "SE": // Sintático
                erro = "Erro na linha " + linha + " - Encontrado " + currentToken.getLexeme() + " "+ getMessageEsperado(sintatico);
                break;
            case "SME": // Semântico
                erro = "Erro na linha " + linha + " - " + message;
                break;
        }
        return erro;
    }
    
    
    private String getMessageEsperado(Sintatico s) {
        String esperado = "Esperado ";
        int x = 0;
        if (s.getNonTerminalToken() != -999) {
            x = s.getNonTerminalToken();            
        } else if (s.getTerminalToken() != -999) {
            x = s.getTerminalToken();
        }
        esperado += PARSER_ERROR[x];

        return esperado;
    }

}
