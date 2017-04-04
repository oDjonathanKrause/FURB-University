package calculadoracompilha;

import java.util.Scanner;
import pilha.PilhaVetor;

/**
 * @author djonathan.krause
 *
 * Percorre cada elemento da expressão - Pra cada elemento: Se for um número:
 * empilha; Se for um operador: Desempilha 2 elementos do topo; - Efetua
 * operação com os elementos; - Empilha o resultado
 *
 * - No fim da expressão, retirar o elemento do topo, que será o resultado
 * final.
 */
public class CalculadoraComPilha
{

    public static int calcula(String expressao) throws Exception
    {
        // Instância scanner para auxiliar na análise da expressão
        Scanner stringScanner = new Scanner(expressao);

        // Instância pilha
        PilhaVetor<Integer> pilha = new PilhaVetor<>();

        // Variaveis de apoio
        int a, b;
        char operador;

        // Enquanto a String tiver caracateres
        while (stringScanner.hasNext())
        {
            // Se o caracter analisado for um int, é um operando
            if (stringScanner.hasNextInt())
            {
                // Empilha o caracter e vai para o próximo carac da expressão
                pilha.push(stringScanner.nextInt());
                continue;
            }

            // Se o caracter analisado não for um int, é um operador
            // Desempilha dois últimos operandos
            b = pilha.pop();
            a = pilha.pop();

            // pega o operador da String (será a última posição analisada)
            operador = stringScanner.next().charAt(0);

            // Verifica qual é o operador e efetua a operação
            switch (operador)
            {
                case '+': // Soma os dois valores desempilhados e empilha o resultado
                    pilha.push(a + b);
                    break;
                case '-': // Subtrae os dois valores desempilhados e empilha o resultado
                    pilha.push(a - b);
                    break;
                case '*': // multiplica e empilha o resultado
                    pilha.push(a * b);
                    break;
                case '/': // Divide os dois valores desempilhados e empilha o resultado
                    pilha.push(a / b);
                    break;
                default:
                    break;
            }
        }

        // Fecha o scanner
        stringScanner.close();

        // Retorna o último valor da pilha (resultado final) limpando-a
        return pilha.pop();
    }

    /* 
     * Tentativa 1 - Não funciona com números negativos
     */
 /* private PilhaVetor<String> pilhaVetor = new PilhaVetor<>();
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
            } // Se o charAt for um espaço
            else if (!charAt.matches("\\S"))
            {
                System.out.println("Espaço");
            } // Se for operador:
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
    }*/
}
