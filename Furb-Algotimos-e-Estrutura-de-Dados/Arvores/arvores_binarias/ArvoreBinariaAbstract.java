package furb.arvores_binarias;

abstract class ArvoreBinariaAbstract<T>
{

    protected NoArvoreBinaria<T> raiz;

    // Construtor
    public ArvoreBinariaAbstract()
    {    }

    /**
     * Seta a raiz na árvore
     * @param no - nó que será a raiz da árvore
     */
    protected void setRaiz(NoArvoreBinaria<T> no)
    {
        this.raiz = no;
    }

    /**
     * Verifica se a árvore esta vazia
     * @return 
     */
    public boolean vazia()
    {
        return (this.raiz == null);
    }

    /**
     * Verifica se o item pertence a árvore
     * @param item - Item que será verificado
     * @return item se ele pertencer a árvore 
     */
    public NoArvoreBinaria<T> pertence(T item)
    {
        return raiz.pertence(item);
    }

    /**
     * Printa a árvore pré-fixada
     * @return String com a árvore
     */
    public String toString()
    {
        return "(" + raiz.imprimePre() + ")";
    }
}
