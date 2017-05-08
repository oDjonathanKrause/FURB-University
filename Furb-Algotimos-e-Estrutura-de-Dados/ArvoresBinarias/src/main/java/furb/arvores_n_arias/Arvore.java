package furb.arvores_n_arias;

/**
 * @author Djonathan
 */
public class Arvore<T>
{

    private NoArvore raiz;

    /**
     * Contrutor da classe Arvore Cria a arvore com nenhuma raiz
     */
    public Arvore()
    {
        this.raiz = null;
    }
    
    /**
     * Verifica se determinada informação pertence a árvore
     * @param info - informação procurada
     * @return Se a informação existir, retorna o nó
     */
    public T pertence(T info)
    {
        // Começa a procurar pela raiz
        return (T) raiz.pertence(info);
    }
    
    /**
     * Transforma a representação textual da árvore
     * @return String da árvore
     */
    public String toString()
    {
        return "(" + raiz.imprimePre() + ")";
    }

    /**
     * Torna a raiz como o nó recebido no parametro
     *
     * @param raiz
     */
    public void setRaiz(T raiz)
    {
        NoArvore<T> noRaiz = new NoArvore<>(raiz);
        this.raiz = noRaiz;
    }

    /**
     * Retorna a raiz da arvore
     *
     * @return no raiz da arvore
     */
    public NoArvore getRaiz()
    {
        return raiz;
    }

    /**
     * Verifica se a arvore esta vazia
     *
     * @return true se estiver vazia
     */
    public boolean vazia()
    {
        return raiz == null;
    }

}
