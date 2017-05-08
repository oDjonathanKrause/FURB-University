package furb.arvores_n_arias;

/**
 * @author Djonathan
 */
public class NoArvore<T>
{

    private T info;
    private NoArvore<T> filho;
    private NoArvore<T> irmao;

    /**
     * Transforma a informação num nó. Sem irmãos e filhos.
     *
     * @param info - informação
     */
    public NoArvore(T info)
    {
        this.info = info;
        this.irmao = null;
        this.filho = null;
    }

    /**
     * Recebe a raiz de uma sub-arvore e estabelece que esta sub-arvore é filha do nó corrente
     * @param filho - sub-arvore que será o filho
     */
    public void inserirFilho(NoArvore<T> filho)
    {
        this.setFilho(filho);
        // sa.proximo ← primeiro;
        // primeiro ← sa;
    }

    /**
     * Printa a árvore pré-fixadamente
     * @return String com a árvore
     */
    public String imprimePre()
    {
        String retorno = "" + this.info;
        
        // Se o irmão não for nulo, printa
        if (this.irmao != null)
        {
            retorno += "(" + this.irmao.imprimePre() + ")";
        } 
        // Senão printa o filho
        else if (this.filho != null)
        {
            retorno += "(" + this.filho.imprimePre() + ")";
        } 
        
        return retorno;
    }

    /**
     * Verifica se o valor pertence a árvore
     * @param info - Valor procurado
     * @return valor se existir na árvore, null se não existir
     */
    public NoArvore<T> pertence(T info)
    {
        // Se o valor for igual a instancia atual de nó, retorna o nó
        if (this.info.equals(info))
        {
            return this;
        }
        // Se o filho do nó atual não for nulo, pertence()?
        else if (this.filho != null)
        {
            return this.filho.pertence(info);
        }
        // Se o irmao do nó atual não for nulo, pertence()?
        else if (this.irmao != null)
        {
            return this.irmao.pertence(info);
        }
        // Se não encontrar, retorna null
        else
        {
            return null;    
        }
    }

    // getters and setters
    public T getInfo()
    {
        return info;
    }

    public void setInfo(T info)
    {
        this.info = info;
    }

    public NoArvore<T> getFilho()
    {
        return filho;
    }

    public void setFilho(NoArvore<T> filho)
    {
        this.filho = filho;
    }

    public NoArvore<T> getIrmao()
    {
        return irmao;
    }

    public void setIrmao(NoArvore<T> irmao)
    {
        this.irmao = irmao;
    }

}
