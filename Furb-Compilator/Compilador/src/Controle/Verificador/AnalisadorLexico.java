package Controle.Verificador;

import Interface.Apresentacao;
import java.util.HashMap;
import Controle.generico.Classes;
import Controle.lexico.LexicalError;
import Controle.lexico.Lexico;
import static Controle.generico.ScannerConstants.SPECIAL_CASES_KEYS;
import Controle.generico.Token;

/**
 *
 * @author Tamires, Djonathan Krause
 */
public class AnalisadorLexico {
    
    public AnalisadorLexico() {
        
    }
    
    public String getMsgErro(String lexeme, String mensagem) {
        String msgErro = "".equals(mensagem) ? "palavra reservada inválida" : mensagem;
        return msgErro;
    }

    /*
    * método criado para verificar se a palavra do tipo reservada é válida.
    * outras classes não verificam.
    */
    public boolean verificaToken(Token t, Lexico lexico) throws LexicalError {
        boolean achou = false;
        if (t.getId() == 2) { //palavra reservada
            for (String sck : SPECIAL_CASES_KEYS) {
                if (sck.equals(t.getLexeme())) {
                    achou = true;
                }
            } 
            if (!achou) {
                throw new LexicalError(getMsgErro("", ""), t.getPosition(), t.getLinha());
            }
        } else if (!"".equals(lexico.getErro())) {
            throw  new LexicalError(getMsgErro("", lexico.getErro()), t.getPosition(), t.getLinha());
        }
        
        return true;
    }


}
