package mergesort;

import java.util.Random;

/**
 * @author Djonathan
 */
public class MergeSort
{
    public static void main(String[] args)
    {
        int[] array = new int[4]; 
        
        popularArray(array);
        
        printarArray(array);
    }
    
    public static void mergeSort(int[] array)
    {
        
        
    }
    
    public static void popularArray(int[] array)
    {
        Random numAleatorio = new Random();
        
        for(int posicao = 0; posicao < array.length; posicao++)
            array[posicao] = numAleatorio.nextInt(100);
    }
    
    public static void printarArray(int[] array)
    {
        for(int valor : array)
            System.out.print(valor + " ");
    }
    
    
}
