package furb.arvores_binarias;

public class NoArvoreBinaria<T>
{

    protected T info;
    protected NoArvoreBinaria<T> esq;
    protected NoArvoreBinaria<T> dir;

    /**
     * Verifica se um valor pertence a árvore
     * @param info - Valor procurado
     * @return valor se existir na árvore
     */
    public NoArvoreBinaria pertence(T info)
    {
        // Se a informção buscada for igual a instância atual, retorna
        if (this.info.equals(info))
        {
            return this;
        }

        // Cria dois nós, esquera e direita
        NoArvoreBinaria retEsq = null, retDir = null;

        // Se o nó atual tiver esquerda
        if (esq != null)
        {
            // retEsq recebe a informação do nó a esquerda do atual
            retEsq = esq.pertence(info);
            
            // Se não for nulo, retorna o nó
            if (retEsq != null)
            {
                return retEsq;
            }
        }

        // Se o nó atual tiver direita
        if (dir != null)
        {
            // retDir recebe o nó da direita do atual
            retDir = dir.pertence(info);
            
            // Se houver valor, retorna o nó
            if (retDir != null)
            {
                return retDir;
            }
        }

        return null;
    }

    /**
     * Printa a árvore pré-fixadamente
     * @return String com a árvore
     */
    public String imprimePre()
    {
        String retorno = "" + this.info;
        if (esq != null)
        {
            retorno += "(" + esq.imprimePre() + ")";
        } else
        {
            retorno += "( )";
        }
        if (dir != null)
        {
            retorno += "(" + dir.imprimePre() + ")";
        } else
        {
            retorno += "( )";
        }
        return retorno;
    }
    
    // Contrutor apenas com a info do nó
    public NoArvoreBinaria(T info)
    {
        this.info = info;
    }

    // Construtor com info, direita e esquerda do nó
    public NoArvoreBinaria(T info, NoArvoreBinaria<T> esq, NoArvoreBinaria<T> dir)
    {
        this.info = info;
        this.esq = esq;
        this.dir = dir;
    }

    // Gets and sets
    public T getInfo()
    {
        return info;
    }

    protected void setInfo(T info)
    {
        this.info = info;
    }

    protected NoArvoreBinaria<T> getEsq()
    {
        return esq;
    }

    protected void setEsq(NoArvoreBinaria<T> esq)
    {
        this.esq = esq;
    }

    protected NoArvoreBinaria<T> getDir()
    {
        return dir;
    }

    protected void setDir(NoArvoreBinaria<T> dir)
    {
        this.dir = dir;
    }

    

}
