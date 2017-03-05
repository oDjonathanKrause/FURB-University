package binarysearch;

import java.util.Random;

/**
 * @author Djonathan
 */
public class BinarySearch
{

    public static void main(String[] args)
    {
        int[][] matriz = new int[10][10];
        int valorProcurado = 12;
        
        // Popula matriz
        popularMatriz(matriz);
        
        // Busca valor
        binarySearch(bubbleSort(matriz), valorProcurado);
        
        // Printa matriz
        printarMatrizT(toMatrix(bubbleSort(matriz)));
    }
    
    /**
     * @param matriz matriz que será transformada em array
     */
    public static int[] toArray(int[][] matriz)
    {
        // Calcula o tamanho do array 
        int tamanhoArray = matriz.length * matriz.length;
        
        // Cria o array
        int[] array = new int[tamanhoArray];
        
        int posicaoArray = 0;
        
        for(int linha = 0; linha < matriz.length; linha++)
        {
            for(int valor = 0; valor < matriz[linha].length; valor++)
            {
                array[posicaoArray] = matriz[linha][valor];
                posicaoArray++;
            }
        }
        
        return array;
    }
    
    /** Transforma array em matriz 
     * @param array array que será transformado em matriz
     * @return matriz
     */
    public static int[][] toMatrix(int[] array)
    {
        // O tamanho da matriz será a raiz quadrada do array
        int tamanhoMatriz = (int) Math.sqrt(array.length);

        // Declara matriz
        int[][] matriz = new int[tamanhoMatriz][tamanhoMatriz];

        // Percorre matriz e atribui valores conforme valo do array 
        int k = 0;
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                matriz[i][j] = array[k];
                k++;
            }
        }

        return matriz;
    }
   
    /**
     * @param array onde o valor será procurado
     * @param valorProcurado
     * @return true se encontrar e false se não encontrar o valor
     */
    public static boolean binarySearch(int[] array, int valorProcurado)
    {
        int inicio = 0;
        int meio;
        int fim = array.length - 1;
        
        while(inicio <= fim)
        {
            // Encontra o meio do array
            meio = (inicio + fim) / 2;
            
            // Se o valor que esta na posicão meio for o que procuramos, retorna true
            if(valorProcurado == array[meio])
            {
                System.out.println("Valor encontrado");
                return true;
            }
            
            // Se o valor procurado for menor do que o contido na posição do meio, tudo acima do meio é ignorado
            // E o fim do array passa a ser o meio (divide o mesmo pela metade)
            else if(valorProcurado < array[meio])
            {
                fim = meio - 1;
            }
            else
            {
                inicio = meio + 1;
            }
        }

        System.out.println("Valor não encontrado");
        return false;
    }
    
     /** Método que ordena a matriz utilizando bubbleSort
     * @param matriz matriz que será ordenada
     * @return int[] - Array ordenado
     */
    public static int[] bubbleSort(int[][] matriz)
    {
        // Transforma matriz recebida num array 
        int arrayOrdenado[] = toArray(matriz);

        // Ordena array
        boolean troca = true;
        int aux;

        while (troca)
        {
            troca = false;
            for (int i = 0; i < arrayOrdenado.length - 1; i++)
            {
                if (arrayOrdenado[i] > arrayOrdenado[i + 1])
                {
                    aux = arrayOrdenado[i];
                    arrayOrdenado[i] = arrayOrdenado[i + 1];
                    arrayOrdenado[i + 1] = aux;
                    troca = true;
                }
            }
        }

        return arrayOrdenado;
    }
    
    
    /** Popula a matriz com números aleatórios
     * @param matriz matriz que será populada
     * void - Passa matriz por referência e popula 
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
    
    
    
}
