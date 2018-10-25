package BubbleSort;

/**
 * @author Djonathan Krause
 */
public class BubbleSort
{
    /**
     * Ordena vetor utilizando bubble sort
     * @param array - vetor que será ordenado
     */
    public static void ordenar(int[] array)
    {
        int aux;

        // Para cada elemento do array
        for (int i = 0; i < array.length; i++)
        {
            // Para cada elemento do array -1
            for (int j = 0; j < array.length - 1; j++)
            {
                // Se o elemento atual for menor do que o próximo, troca os dois de posição
                if (array[j] > array[j + 1])
                {
                    // Seta valor atual para um aux
                    aux = array[j];
                    
                    // Posição atual recebe o valor da próxima posição
                    array[j] = array[j + 1];
                    
                    // Próxima posição recebe elemento atual
                    array[j + 1] = aux;
                }
            }
        }
    }

    /**
     * Imprime valores do array
     * @param array que será ordenado
     */
    public static void imprimir(int[] array)
    {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " | ");
    }
}
