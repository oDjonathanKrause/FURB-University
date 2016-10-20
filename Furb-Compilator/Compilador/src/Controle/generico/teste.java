package Controle.generico;

import Controle.sintatico.SyntaticError;
import Controle.Verificador.AnalisadorLexico;
import Controle.Verificador.Compilador;
import Controle.lexico.LexicalError;
import Controle.lexico.Lexico;
import Controle.semantico.SemanticError;
import Controle.semantico.Semantico;
import Controle.sintatico.Sintatico;

/**
 *
 * @author Tamires
 */
public class teste {

    public static void main(String[] args) {
        Lexico lexico = new Lexico();
        Sintatico sintatico = new Sintatico();
        Semantico semantico = new Semantico();
        int linha = 1;
        lexico.setInput("main module {");
        Compilador comp = new Compilador();
//...
        try {
            sintatico.parse(lexico, semantico);
            System.out.println("Programa compilado com sucesso");
        } catch (LexicalError e) {
            System.out.println(comp.setErro(e.getMessage(), linha, "LE", sintatico));
        } catch (SyntaticError e) {
            System.out.println(comp.setErro(e.getMessage(), linha, "SE", sintatico));
        } catch (SemanticError e) {
            System.out.println(comp.setErro(e.getMessage(), linha, "SME", sintatico));
        }

    }

}
