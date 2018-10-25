package BinarySearch;

/**
 *
 * @author drkrause
 */
public class BinarySearch
{
    /**
     * Busca o valor informado 
     * @param array - array de valores 
     * @param chave - valor procurado
     * @return -1 se não encontrar, o próprio valor se encontrar
     */
    public static int busca(int[] array, int chave)
    {
        return buscaBinariaRecursiva(array, 0, array.length - 1, chave);
    }

    /**
     * Procura o valor usando a pesquisa binária recursivamente
     * @param array - array de valores 
     * @param menor - menor índice do array 
     * @param maior - maior índice do array
     * @param chave - valor procurado
     * @return valor procurado ou -1 se não encontrar
     */
    public static int buscaBinariaRecursiva(int[] array, int menor, int maior, int chave)
    {
        // Determina qual é a metade do array       
        int media = (maior + menor) / 2;
        int valorMeio = array[media];

        // Se o menor índice for maior do que o maior índice, o valor procurado não esta no array 
        if (menor > maior)
        {
            return -1;
        } 
        // Se o valor estiver no meio do array, retorna 
        else if (valorMeio == chave)
        {
            return media;
        } 
        // Se o valor da metade for maior do que o valor procurado, faz nova busca levando em consideração só o resto do array
        else if (valorMeio < chave)
        {
            return buscaBinariaRecursiva(array, media + 1, maior, chave);
        } else
        // Senão, faz nova busca levando em consideração só o resto do array
        {
            return buscaBinariaRecursiva(array, menor, media - 1, chave);
        }
    }

}
