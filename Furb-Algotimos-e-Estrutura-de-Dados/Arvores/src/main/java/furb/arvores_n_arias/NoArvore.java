package furb.arvores_n_arias;

/**
 * @author Djonathan
 * @param <T> - Tipo de dado da árvore
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
     * Recebe a raiz de uma sub-arvore e estabelece que esta sub-arvore é filha
     * do nó corrente
     *
     * @param filho - sub-arvore que será o filho
     */
    public void inserirFilho(NoArvore<T> filho)
    {
        filho.setIrmao(this.filho);
        // sa.proximo ← primeiro;
        
        this.filho = filho;
        // primeiro ← sa;
    }

    /**
     * Printa a árvore pré-fixadamente
     *
     * @return String com a árvore
     */
    public String imprimePre()
    {
        String retorno = "" + this.info;

        // Se tem filho, printa todos os filhos até acabar
        if (this.filho != null)
        {
            retorno += "(" + this.filho.imprimePre() + ")";
        }

        // Se tem irmão, printa todos até acabar
        if (this.irmao != null)
        {
            retorno += " " + this.irmao.imprimePre();
        } 

        return retorno;
    }

    /**
     * Verifica se o valor pertence a árvore
     *
     * @param info - Valor procurado
     * @return valor se existir na árvore, null se não existir
     */
    public NoArvore<T> pertence(T info)
    {
        // Se o valor for igual a instancia atual de nó, retorna o nó
        if (this.info.equals(info))
        {
            return this;
        } // Se o filho do nó atual não for nulo, pertence()?
        else if (this.filho != null)
        {
            return this.filho.pertence(info);
        } // Se o irmao do nó atual não for nulo, pertence()?
        else if (this.irmao != null)
        {
            return this.irmao.pertence(info);
        } // Se não encontrar, retorna null
        else
        {
            return null;
        }
    }
    
    /**
     * Verifica a altura do nó recursivamente
     * @param no onde sera verificada a altura
     * @param altura altura atual, é incrementada a cada no filho
     * @return int - altura do no
     */
    public int getAltura(NoArvore no, int altura)
    {
        int alturaAux = 0;
     
        // Se o no atual tiver filho
        if(no.filho != null)
        {
            // Soma um na altura
            altura++;
            
            // alturaAux recebe o resultado do getAltura do filho do no atual
            alturaAux = no.getAltura(no.filho, altura);
        }

        // Se o no atual não tiver filho mas tiver irmao
        if(no.irmao != null)
        {
            // Retorna a altura do irmao
            return no.getAltura(no.irmao, altura);
        }
        
        // Retorna a maior altura
        if(altura > alturaAux)
            return altura;
        else
            return alturaAux;
        
    }
    
    /**
     * Verifica o nivel do no onde a informação é igual a passada por parametro
     * @param no verificado
     * @param nivel do no
     * @return int - Nivel do no
     */
    public int getNivel(NoArvore no, int nivel)
    { 
        int nivelAux = 0;
        
        if(this.info.equals(no.info))
        {
            return nivel;
        } 
     
        // Se o no atual tiver filho
        if(this.filho != null)
        {
            // Soma um na altura
            nivel++;
            
            // alturaAux recebe o resultado do getAltura do filho do no atual
            nivelAux = no.getNivel(this.filho, nivel);
        }

        // Se o no atual não tiver filho mas tiver irmao
        if(this.irmao != null)
        {
            // Retorna a altura do irmao
            return no.getNivel(this.irmao, nivel);
        }
        
        if(nivel > nivelAux)
            return nivel;
        else
            return nivelAux;
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
