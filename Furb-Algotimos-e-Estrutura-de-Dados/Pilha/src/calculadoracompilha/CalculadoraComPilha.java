package calculadoracompilha;

import java.util.regex.Pattern;
import pilha.PilhaVetor;

/**
 * @author djonathan.krause - Percorre cada elemento da expressão - Pra cada
 * elemento: - Se for um número, empilha; - Se for um operador: - Desempilha 2
 * elementos do topo; - Efetua operação com os elementos; - Empilha o resultado
 *
 * - No fim da expressão, retirar o elemento do topo, que será o resultado
 * final.
 */
public class CalculadoraComPilha
{

    private PilhaVetor<String> pilhaVetor = new PilhaVetor<>();
    private String numeros = new String("\\d*");
    private String operadores = new String("-|/|\\+|\\*+");

    public String calcula(String expressao) throws Exception
    {
        String resultado = "";
        String a, b, charAt;
        int resultadoInt;

        for (int i = 0; i < expressao.length(); i++)
        {
            // Pega valor da posição atual
            charAt = String.valueOf(expressao.charAt(i));

            // Utiliza expressão regular para verificar se o elemento atual é um dígito, se for, empilha
            if (Pattern.matches(numeros, charAt))
            {
                pilhaVetor.push(charAt);
            } 
            // Se for operador:
            else if (Pattern.matches(operadores, charAt))
            {
                // Desempilha dois últimos elementos
                a = pilhaVetor.pop();
                b = pilhaVetor.pop();

                // Verifica qual é o operador
                switch (expressao.charAt(i))
                {
                    case '+': // Caso o caracter atual for "+", soma
                        // Soma os dois valores desempilhados
                        resultadoInt = Integer.valueOf(a) + Integer.valueOf(b);

                        // Empilha resultado
                        pilhaVetor.push(String.valueOf(resultadoInt));
                        break;

                    case '-': // Caso o caracter atual for "-", subtrae
                        // Soma os dois valores desempilhados
                        resultadoInt = Integer.valueOf(a) - Integer.valueOf(b);

                        // Empilha resultado
                        pilhaVetor.push(String.valueOf(resultadoInt));
                        break;

                    case '*': // Caso o caracter atual for "*", multiplica
                        // Soma os dois valores desempilhados
                        resultadoInt = Integer.valueOf(a) * Integer.valueOf(b);

                        // Empilha resultado
                        pilhaVetor.push(String.valueOf(resultadoInt));
                        break;

                    case '/': // Caso o caracter atual for "/", divide
                        // Soma os dois valores desempilhados
                        resultadoInt = Integer.valueOf(a) / Integer.valueOf(b);

                        // Empilha resultado
                        pilhaVetor.push(String.valueOf(resultadoInt));
                        break;

                    case ' ': // Caso o caracter atual for um espaço em branco, ignora e segue para o próximo
                        break;
                }
            }

        }
        resultado = pilhaVetor.peek();
        System.out.println("Resultado Final: " + resultado);
        return resultado;
    }

}
