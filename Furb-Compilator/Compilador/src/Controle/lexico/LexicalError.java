package Controle.lexico;

import Controle.generico.AnalysisError;

/**
 *
 * @author Tamires, Djonathan Krause
 */
public class LexicalError extends AnalysisError {

    public LexicalError(String msg, int position, int linha) {
        super(msg, position, linha);
    }

    public LexicalError(String msg) {
        super(msg);
    }
}
