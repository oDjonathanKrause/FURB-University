package mergesort;

import java.util.Random;

/**
 * @author Djonathan
 */
public class MergeSort
{
    public static void main(String[] args)
    {
        int[][] matriz = new int[10][10];

        popularMatriz(matriz);
        
        printarMatrizT(matriz);
    }
    
    /**
     * @param matriz matriz a ser populada
     */
    public static void popularMatriz(int[][] matriz)
    {
        // Declara numero aleatorio
        Random numAleatorio = new Random();
        
        // Percorre cada posição da matriz e popula com um randInt
        for (int[] linha : matriz)
            for (int valor = 0; valor < linha.length; valor++)    
                linha[valor] = numAleatorio.nextInt(100);
    }
    
    
    /** Printa matriz com tabulação (formatada) 
     * @param matriz matriz que será printada
     */
    public static void printarMatrizT(int[][] matriz)
    {
        String str = "|\t";

        for (int linha = 0; linha < matriz.length; linha++)
        {
            for (int valor = 0; valor < matriz[linha].length; valor++)
            {
                str += matriz[linha][valor] + "\t";
            }

            System.out.println(str + "|");
            str = "|\t";
        }

    }
    
}
