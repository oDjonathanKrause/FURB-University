
package Controle.sintatico;

import Controle.generico.Constants;


/**
 *
 * @author Tamires
 */
public class Tipo {
    
    public static int cte_integer = Constants.t_constante_int;
    public static int cte_float = Constants.t_constante_float;
    public static int cte_string = Constants.t_constante_string;
    public static int cte_bool = Constants.t_boolean;
    private int valor;
    
    public Tipo(int valor) {
        this.valor = valor;
    }
    
    public int getTipo() {
        return valor;
    }
    
    public String toString() {
        String tipo = "";
        if (valor == cte_integer) {
            tipo = "int64";
        } else if (valor == cte_float) {
            tipo = "float64";
        } else if (valor == cte_string) {
            tipo = "string";
        } else if (valor == cte_bool) {
            tipo = "bool";
        }
        return tipo;
    }    
    
}
