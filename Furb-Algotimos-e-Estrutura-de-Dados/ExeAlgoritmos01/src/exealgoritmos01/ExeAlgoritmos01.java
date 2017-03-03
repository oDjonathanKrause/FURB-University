package exealgoritmos01;

import java.util.Random;

/**
 * @author Djonathan Krause
 *
 * Seja uma matriz A de elementos inteiros de dimensões n x n. Os elementos de
 * cada linha e de cada coluna estão ordenados em ordem não decrescente. Dado um
 * valor x, escreva um algoritmo para determinar se existem i,j ∈ 1,2, … , n tais que x = Ai,j
 */
public class ExeAlgoritmos01
{

    public static void main(String[] args)
    {
        int valorProcurado = 10;

        // Declaração da matriz
        int[][] matriz = new int[10][10];

        // Popula a matriz
        popularMatriz(matriz);

        // Printa a matriz
        System.out.println("Matriz");
        printarMatrizT(matriz);

        // Ordena matriz
        int[][] matrizOrdenada = bubbleSort(matriz);

        // Printa matriz ordenada
        System.out.println("Matriz Ordenada");
        printarMatrizT(matrizOrdenada);

        System.out.println("\nInformações");
        
        // Procura o numero na matriz
        procurarNaMatriz(valorProcurado, matrizOrdenada);

        // Verifica a quantidade de ocorrências do valor informado na matriz
        ocorrenciasDoValorNaMatriz(valorProcurado, matrizOrdenada);

        // Procura o numero na matriz e retorna como plano cartesiano
        procuraNaMatrizCartesiano(valorProcurado, matrizOrdenada);
        

    }

    /** Procura valor inputado na matriz
     * @param valor valor que será procurado
     * @param matriz matriz onde o valor será procurado
     * @return Boolean - Número encontrado true ou false */
    public static boolean procurarNaMatriz(int valor, int[][] matriz)
    {
        // Percorre matriz
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                // Se o numero da posição i, j for igual ao informado, retorna true e retorna mensagem
                if (matriz[i][j] == valor)
                {
                    System.out.println("Valor " + matriz[i][j] + " encontrado na posição "
                            + i + ", " + j + " da matriz");

                    return true;
                }
            }
        }

        // Se não encontrar valor procurado na matriz, retorna false e retorna mensagem
        System.out.println("Valor " + valor + " não encontrado na matriz");
        return false;
    }

    /** Verifica quantas vezes o valor apareceu na matriz
     *  @param valor valor procurado 
     *  @param matriz matriz onde o valor foi procurado
     *  @return int - Quantidade de vezes que o valor foi encontrado na matriz
     */
    public static int ocorrenciasDoValorNaMatriz(int valor, int[][] matriz)
    {
        int qtdOcorrencias = 0;

        // Percorre matriz
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                // Se o numero da posição i, j for igual ao informado, retorna true e retorna mensagem
                if (matriz[i][j] == valor)
                {
                    qtdOcorrencias += 1;
                }
            }
        }

        // Se encontrar alguma vez, printa informando
        if (qtdOcorrencias > 0)
        {
            System.out.println("O valor " + valor + " foi encontrado " + qtdOcorrencias + " vezes na matriz");
        }

        return qtdOcorrencias;
    }

    /** Popula a matriz com números aleatórios
     * @param matriz matriz que será populada
     * @return void - Passa matriz por referência e popula 
     */
    public static void popularMatriz(int[][] matriz)
    {
        Random numeroAleatorio = new Random();

        // Percorre matriz
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                // Atribui valor aleatorio 
                matriz[i][j] = numeroAleatorio.nextInt(100);
            }
        }
    }

    /** Procura valor inputado na matriz
     * @param valor valor que será procurado
     * @param matriz matriz onde o valor será procurado
     * @return boolean - true + print da posição ou false se não encontrar
     */
    public static boolean procuraNaMatrizCartesiano(int valor, int[][] matriz)
    {
        // Percorre matriz
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                // Se o numero da posição i, j for igual ao informado, retorna true e retorna mensagem
                if (matriz[i][j] == valor)
                {
                    System.out.println("Valor " + matriz[i][j] + " encontrado na posição "
                            + (j + 1) + "x " + (matriz[i].length - i) + "y, " + " da matriz (plano cartesiano)");

                    return true;
                }
            }
        }

        // Se não encontrar valor procurado na matriz, retorna false e retorna mensagem
        System.out.println("Valor " + valor + " não encontrado na matriz");
        return false;
    }

    /** Método que ordena a matriz utilizando bubbleSort
     * @param matriz matriz que será ordenada
     * @return int[][] - Matriz ordenada
     */
    public static int[][] bubbleSort(int[][] matriz)
    {
        // Transforma matriz recebida num array 
        int array[] = transformaEmArray(matriz);

        boolean troca = true;
        int aux;

        while (troca)
        {
            troca = false;
            for (int i = 0; i < array.length - 1; i++)
            {
                if (array[i] > array[i + 1])
                {
                    aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                    troca = true;
                }
            }
        }

        int matrizOrdenada[][] = transformaEmMatriz(array);

        return matrizOrdenada;

    }

    /** Transforma a matriz num array unidimensional para fazer a ordenação
     * @param matriz matriz que será transformada num array unidimensional
     * @return int[] - array
     */
    public static int[] transformaEmArray(int[][] matriz)
    {
        int tamanhoMatriz = matriz.length;
        int[] array = new int[tamanhoMatriz * tamanhoMatriz];

        int k = 0;

        // Percorre matriz
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                array[k] = matriz[i][j];
                k++;
            }
        }

        return array;
    }

    /** Transforma array em matriz 
     * @param array array que será transformado em matriz
     * @return int[][] - matriz ordenada
     */
    public static int[][] transformaEmMatriz(int[] array)
    {
        System.out.println("");

        // O tamanho da matriz será a raiz quadrada do array
        int tamanhoMatriz = (int) Math.sqrt(array.length);

        // Declara matriz
        int[][] matrizOrdenada = new int[tamanhoMatriz][tamanhoMatriz];

        // Percorre matriz e atribui valores conforme valo do array (já ordenado)
        int k = 0;
        for (int i = 0; i < matrizOrdenada.length; i++)
        {
            for (int j = 0; j < matrizOrdenada[i].length; j++)
            {
                matrizOrdenada[i][j] = array[k];
                k++;
            }
        }

        return matrizOrdenada;
    }

    /** Printa matriz com tabulação (formatada) 
     * @param matriz matriz que será printada
     */
    public static void printarMatrizT(int[][] matriz)
    {
        String str = "|\t";

        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                str += matriz[i][j] + "\t";
            }

            System.out.println(str + "|");
            str = "|\t";
        }

    }
    
    /** Printa matriz sem tabulação (menos formatada) 
     * @param matriz matriz que será printada
     */
    public static void printarMatriz(int[][] matriz)
    {
        // Percorre linhas
        for (int i = 0; i < matriz.length; i++)
        {
            // Percorre colunas
            for (int j = 0; j < matriz[i].length; j++)
            {
                // Printa valores da linha
                System.out.print(matriz[i][j] + " ");
            }
            // Proxima linha
            System.out.println();
        }

        System.out.println();
    }

}
