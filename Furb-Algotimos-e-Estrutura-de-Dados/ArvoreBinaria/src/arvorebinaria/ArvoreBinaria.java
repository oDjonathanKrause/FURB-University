package arvorebinaria;

/**
 * @author Djonathan Krause
 */
public class ArvoreBinaria<T>
{

    private NoArvoreBinaria<T> raiz;

    public String toString()
    {
        String retorno = null;
        
        if (this.raiz == null)
            return "A árvore está vazia";

        if (this.raiz.getEsq() != null)
        {
            this.raiz.getEsq().toString();
        }

        retorno += " - info: " + this.raiz.getInfo();

        if (this.raiz.getDir() != null)
        {
            this.raiz.getDir().toString();
        }
        
        return retorno;
    }

    /**
     * Procura se determinado dado está armazenado na árvore.
     *
     * @return nó cujo conteúdo é igual ao parâmetro
     */
    public T pertence(T valor)
    {
        return valor;
    }

    /**
     * Verifica se a arvore esta vazia
     *
     * @return true se estiver vazia | false se não estiver vazia
     */
    public boolean vazia()
    {
        return raiz == null;
    }

    // Construtor
    public ArvoreBinaria()
    {
    }

    public ArvoreBinaria(NoArvoreBinaria<T> raiz)
    {
        this.raiz = raiz;
    }

    /**
     * Retorna a raiz da arvore
     *
     * @return T - raiz
     */
    public NoArvoreBinaria<T> getRaiz()
    {
        return raiz;
    }

    public void setRaiz(NoArvoreBinaria<T> raiz)
    {
        this.raiz = raiz;
    }
}
