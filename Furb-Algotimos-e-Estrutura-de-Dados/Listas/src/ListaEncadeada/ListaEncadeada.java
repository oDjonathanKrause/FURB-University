package ListaEncadeada;

/**
 *
 * @author djonathan.krause
 */
public class ListaEncadeada
{

    private ElementoLista primeiroElemento;
    private ElementoLista ultimoElemento = null;
    private int tamanhoLista = 0;

    /**
     * Adiciona elemento no início da lista
     *
     * @param String - valorElemento
     */
    public void addInicio(String valorElemento)
    {
        // Cria elemento
        ElementoLista elemento = new ElementoLista(valorElemento, primeiroElemento);

        // Atualiza o parametro de primeiro elemento
        this.setPrimeiroElemento(elemento);

        // Atualiza tamanho da lista
        tamanhoLista++;
    }

    /**
     * Insere elemento no fim da lista
     *
     * @param valorElemento
     */
    public void addFim(String valorElemento)
    {
        // Cria elemento
        ElementoLista elemento = new ElementoLista(valorElemento, null);

        // Se a lista estiver vazia, insere na primeira posição e seta o ultimo item como o primeiro (lista apenas com um item)
        if (verificarListaVazia())
        {
            this.primeiroElemento = elemento;
            this.ultimoElemento = this.primeiroElemento;
        } else
        {
            // Senão, seta o último elemento como o elemento recebido por parâmetro
            this.ultimoElemento = elemento;
            
            // Seta referência de próximo elemento para ele mesmo
            this.ultimoElemento.setProxElemento(elemento);
        }

        // Atualiza tamanho da lista
        tamanhoLista++;
    }

    /**
     *
     * @param valorElemento
     * @param posicao
     */
    public void addPosicaoX(String valorElemento, int posicao)
    {
        // Cria elemento
        ElementoLista elemento = new ElementoLista(valorElemento, null);
        
        // Percorre lista
        for(int i = 0; i < tamanhoLista; i++)
        {
            // Se a posição atual for a posição de inserção
            if(i == posicao)
            {
                // Insere elemento e corrige referencias
            }
        }
    }

    /**
     * Imprime todos os valores da lista
     */
    public void printarLista()
    {
        if(verificarListaVazia())
            System.out.println("Lista vazia");
        
        // Instancia elemento e atribui o primeiro elemento da lista a ele
        ElementoLista elemento = this.primeiroElemento;

        // Enquanto o elemento não for null
        while (elemento != null)
        {
            // Printa valor do elemento
            System.out.println(elemento.getElemento());
            
            // Seta próximo elemento
            elemento = elemento.getProxElemento();
        }

    }
    
    /**
     * Verifica se a lista esta vazia
     * @return true se estiver vazia e false se tiver elementos na lista
     */
    public boolean verificarListaVazia()
    {
        return this.primeiroElemento == null;
    }

    /**
     * Gets e sets
     */
    public ElementoLista getPrimeiroElemento()
    {
        return primeiroElemento;
    }

    public void setPrimeiroElemento(ElementoLista primeiroElemento)
    {
        this.primeiroElemento = primeiroElemento;
    }

    public ElementoLista getUltimoElemento()
    {
        return ultimoElemento;
    }

    public void setUltimoElemento(ElementoLista ultimoElemento)
    {
        this.ultimoElemento = ultimoElemento;
    }

    public int getTamanhoLista()
    {
        return tamanhoLista;
    }

    public void setTamanhoLista(int tamanhoLista)
    {
        this.tamanhoLista = tamanhoLista;
    }

}
