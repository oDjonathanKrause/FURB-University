package Testes;

import BinarySearch.BinarySearch;
import BubbleSort.BubbleSort;
import java.util.Arrays;

/**
 *
 * @author drkrause
 */
public class Main
{

    public static void main(String[] args)
    {
        System.out.println("\tBinary Search");
        int[] array = new int[10000];
        int randomInt;
        
        for(int i = 0; i < 10000; i++)
            array[i] = randomInt = 0 + (int)(Math.random() * 10000); 

        Arrays.sort(array);
        System.out.println("6 esta no array? " + BinarySearch.busca(array, 6));
        System.out.println("78 esta no array? " + BinarySearch.busca(array, 78));
        System.out.println("90 esta no array? " + BinarySearch.busca(array, 90));
        System.out.println("-100 esta no array? " + BinarySearch.busca(array, -100));
        
        System.out.println("\n\n\tBubble Sort");
        BubbleSort.ordenar(array);
        BubbleSort.imprimir(array);
    }

}
