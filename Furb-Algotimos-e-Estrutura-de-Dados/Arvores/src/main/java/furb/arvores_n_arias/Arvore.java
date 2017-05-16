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
    
    /**
     * Verifica a altura da arvore
     * @return int - Altura da arvore
     */
    public int getAltura()
    {
        // Altura inicial é zero
        int altura = 0;
        
        // Se a arvore estiver vazia, retorna -1
        if(vazia())
            return -1;
        
        // Procura a altura a partir da raiz
        altura = raiz.getAltura(raiz, altura);
        
        // Retorna a altura -1 (por causa do incremento no metodo do nó)
        return altura - 1;
    }
    
    /**
     * Retorna o nivel do no que tem o valor passado como parametro
     * @param infoNo - Valor do no procurado
     * @return int - nivel do no
     */
    public int getNivel(T infoNo)
    {
        int nivelNo;
        NoArvore noArvore = new NoArvore(infoNo);
        
        nivelNo = raiz.getNivel(noArvore, 0);
        
        return nivelNo;
    }
    
    /**
     * Verifica se a diferença entre os níveis de nós folha é maior que 1
     * @return false se a diferença entre os níveis de nós folha é maior que 1
     */
    public boolean isDegenerada()
    {
        return true;
    }
    
}
