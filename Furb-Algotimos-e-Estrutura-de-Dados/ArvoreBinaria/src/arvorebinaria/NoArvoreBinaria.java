package arvorebinaria;

/**
 * @author drkrause
 */
public class NoArvoreBinaria<T>
{

    private T info;
    private NoArvoreBinaria<T> esq;
    private NoArvoreBinaria<T> dir;

    // Construtor vazio
    public NoArvoreBinaria()
    {
    }

    // Contrutor
    public NoArvoreBinaria(T info)
    {
        this.info = info;
    }
    

    /**
     * Procura se determinado dado está armazenado na sub-árvore.
     *
     * @return nó cujo conteúdo é igual ao parâmetro
     */
    public T pertence(T valor)
    {
        NoArvoreBinaria valorProcurado = new NoArvoreBinaria(valor);
        
        if(this.info.equals(valorProcurado.getInfo()))
            return valor;
        return null;
    }

    /**
     * Gets e sets
     */
    public T getInfo()
    {
        return info;
    }

    public void setInfo(T info)
    {
        this.info = info;
    }

    public NoArvoreBinaria<T> getEsq()
    {
        return esq;
    }

    public void setEsq(NoArvoreBinaria<T> esq)
    {
        this.esq = esq;
    }

    public NoArvoreBinaria<T> getDir()
    {
        return dir;
    }

    public void setDir(NoArvoreBinaria<T> dir)
    {
        this.dir = dir;
    }
}
