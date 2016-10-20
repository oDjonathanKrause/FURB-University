package Controle.lexico;

import Controle.generico.Token;
import Controle.generico.Constants;
import static Controle.generico.ScannerConstants.SCANNER_ERROR;
import static Controle.generico.ScannerConstants.SCANNER_TABLE;
import static Controle.generico.ScannerConstants.SCANNER_TABLE_INDEXES;
import static Controle.generico.ScannerConstants.SPECIAL_CASES_INDEXES;
import static Controle.generico.ScannerConstants.SPECIAL_CASES_KEYS;
import static Controle.generico.ScannerConstants.SPECIAL_CASES_VALUES;
import static Controle.generico.ScannerConstants.TOKEN_STATE;

/**
 *
 * @author Tamires, Djonathan Krause
 */
public class Lexico implements Constants {

    private int position;
    private String input;
    private String erro;
    private int linha;
    private int posicaoLinha;

    public Lexico() {
        this("");
        linha = 1;
        posicaoLinha = 1;
    }

    public Lexico(String input) {
        setInput(input);
    }

    public void setInput(String input) {
        this.input = input;
        setPosition(0);
    }

    public void setPosition(int pos) {
        position = pos;
    }

    public Token nextToken() throws LexicalError {
        erro = "";
        if (!hasInput()) {
            return null;
        }

        int start = position;

        int state = 0;
        int lastState = 0;
        int endState = -1;
        int end = -1;

        char caracter = ' ';
        char previousCharacter = ' ';
        boolean coment = false;
        while (hasInput()) {
            lastState = state;
            caracter = nextChar();
            state = nextState(caracter, state);

            if (state < 0) {
                break;
            } else {
                if (tokenForState(state) >= 0
                        || coment) {
                    if (caracter == '\n') {
                        linha++;
                        posicaoLinha = 1;
                    } else {
                        posicaoLinha++;
                    }
                    endState = state;
                    end = position;
                } 
                if (caracter == '*' 
                        && previousCharacter == '/') {
                        posicaoLinha++;
                        coment = true;
                } else if (caracter == '/' 
                        && previousCharacter == '*') {
                    coment = false;
                }
            }
            previousCharacter = caracter;
        }
        if (endState < 0 || (endState != state && tokenForState(lastState) == -2)) {
            erro = SCANNER_ERROR[lastState];
            String lexeme = input.substring(start, position);
            return new Token(0, lexeme, 0, linha);
        }

        position = end;

        int token = tokenForState(endState);

        if (token == 0) {
            return nextToken();
        } else {
            String lexeme = input.substring(start, end);
            token = lookupToken(token, lexeme);
            return new Token(token, lexeme, start, linha);
        }
    }

    private int nextState(char c, int state) {
        int start = SCANNER_TABLE_INDEXES[state];
        int end = SCANNER_TABLE_INDEXES[state + 1] - 1;

        while (start <= end) {
            int half = (start + end) / 2;

            if (SCANNER_TABLE[half][0] == c) {
                return SCANNER_TABLE[half][1];
            } else if (SCANNER_TABLE[half][0] < c) {
                start = half + 1;
            } else //(SCANNER_TABLE[half][0] > c)
            {
                end = half - 1;
            }
        }

        return -1;
    }

    private int tokenForState(int state) {
        if (state < 0 || state >= TOKEN_STATE.length) {
            return -1;
        }

        return TOKEN_STATE[state];
    }

    public int lookupToken(int base, String key) {
        int start = SPECIAL_CASES_INDEXES[base];
        int end = SPECIAL_CASES_INDEXES[base + 1] - 1;

        while (start <= end) {
            int half = (start + end) / 2;
            int comp = SPECIAL_CASES_KEYS[half].compareTo(key);

            if (comp == 0) {
                return SPECIAL_CASES_VALUES[half];
            } else if (comp < 0) {
                start = half + 1;
            } else //(comp > 0)
            {
                end = half - 1;
            }
        }

        return base;
    }

    private boolean hasInput() {
        return position < input.length();
    }

    private char nextChar() {
        if (hasInput()) {
            return input.charAt(position++);
        } else {
            return (char) -1;
        }
    }
    
    public String getErro() {
        return erro;
    }
    
    public int getPosition() {
        return position;
    }
}
