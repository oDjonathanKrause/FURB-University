package furb.arvores_binarias;

public class ArvoreBST<T extends Comparable> extends ArvoreBinariaAbstract<T>
{

    public ArvoreBST()
    {    }
    
    /**
     * Método que insere valores na árvore
     * @param info - Informação que será inserida
     */
    public void inserir(T info)
    {
        // Se a raiz for nula (arvore esta vazia) 
        if (raiz == null)
        {
            // Seta valor como raiz
            raiz = new NoArvoreBST<T>(info);
        } else
        {
            // Senao, insere valor na posicao correta
            ((NoArvoreBST<T>) raiz).inserir(info);
        }
    }

    /**
     * Método que busca um valor na árvore
     * @param info - Valor que será procurado
     * @return info - Valor
     */
    public NoArvoreBST<T> busca(T info)
    {
        // Cria um objeto de NoArvore
        NoArvoreBST<T> retorno = null;
        
        // Se a arvore não estiver vazia, busca
        if (raiz != null)
        {
            retorno = ((NoArvoreBST<T>) raiz).busca(info);
        }
        return retorno;
    }
}
