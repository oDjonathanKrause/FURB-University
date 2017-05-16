package furb.arvores_n_arias;

/**
 * @author Djonathan, Eliseu, Isabela
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
     * @param info valor do no
     * @return int - Nivel do no
     */
    public int getNivel(NoArvore no, T info, int nivel)
    { 
        int nivelAux;

        // Se o no for nulo, retorna -1
        if (no == null)
        {
            return -1;
        }
        // Se o no for igual a info que procuramos, retorna o nivel
        else if (no.getInfo().equals(info)) 
        {
            return nivel;
        } 
        else 
        {
            // Faz a recursao pro irmao
            nivelAux = getNivel(no.getIrmao(), info, nivel);
            
            // Se for maior que zero, retorna o nivelAux, senao retorna a recursao do filho
            if (nivelAux >= 0)
                return nivelAux;
            else
                return getNivel(no.getFilho(), info, nivel + 1);
        }
    }
    
    public boolean isDegenerada(NoArvore no, int alturaArvore, int nivel)
    {
        // Se o no for folha
        if(no.filho == null)
        {
            int nivelNoAtual = no.getNivel(no, no.info, nivel);
            // Se o nivel do nó folha for igual a altura da arvore -1, não é degenerada
            if(nivelNoAtual == (alturaArvore - 1) || nivelNoAtual == alturaArvore)
                return false;
            else 
                return true;
        } 
        
        // Se o nó tiver filho
        if(no.filho != null)
        {
            nivel++;
            return  isDegenerada(no.filho, alturaArvore, nivel);
        }
        // Se o nó tiver irmão
        else if(no.irmao != null)
        {
            return isDegenerada(no.irmao, alturaArvore, nivel);
        }
        
         return false;
        
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
