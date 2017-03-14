package ListaEncadeada;

/**
 *
 * @author djonathan.krause
 */
public class ListaEncadeada
{

    private ElementoLista<T> primeiroElemento = null;
    private ElementoLista<T> ultimoElemento = null;
    private int qtdElementos = 0;

    // Construtor 
    public ListaEncadeada()
    {
    }

    /**
     * Adiciona elemento no início da lista
     *
     * @param String - valorElemento
     */
    public void addInicio(T valorElemento)
    {


    }

    /**
     * Insere elemento no fim da lista
     *
     * @param valorElemento
     */
    public void addFim(T valorElemento)
    {
        // Cria elemento
        ElementoLista novoElemento = new ElementoLista();
        novoElemento.setElemento(valorElemento);

        // Se estiver vazia, o primeiro recebe o elemento criado
        if (this.verificarListaVazia())
        {
            // Atualiza o atributo de primeiro elemento
            primeiroElemento = novoElemento;
        } else
        {
            ultimoElemento.setProxElemento(novoElemento);
        }

        // Atualiza atributo de último elemento
        ultimoElemento = novoElemento;

        // Atualiza tamanho da lista
        qtdElementos++;
    }

    /**
     * Adiciona o valor na posição X
     *
     * @param valorElemento
     * @param posicao
     */
    public void addPosicaoX(String valorElemento, int posicao)
    {
        // Verifica se a posição informada é válida
        if (posicao >= 0 && posicao <= qtdElementos)
        {
            // Cria novo elemento
            ElementoLista novoElemento = new ElementoLista();
            novoElemento.setElemento(valorElemento);

            // Cria o elemento que esta na posição X
            ElementoLista elemPosicaoX;

            // Se quiser add na posição 0 (inicio da lista)
            if (posicao == 0)
            {
                // O próximo elemento do novo, é o antigo primeiro
                novoElemento.setProxElemento(primeiroElemento);

                // Seta novo elemento como o primeiro
                primeiroElemento = novoElemento;
            } else
            {
                // Descobre qual o elemento da posição X
                elemPosicaoX = this.consultaInterna(posicao - 1);

                // O próximo elemento do novo será o próximo do elemePosicaoX
                novoElemento.setProxElemento(elemPosicaoX.getProxElemento());

                // O próximo doelemPosicaoX será o novoElemento
                elemPosicaoX.setProxElemento(novoElemento);
            }

            qtdElementos++;

        }
    }

    /**
     * Percorre a lista encadeada até a posição X. Método privado.
     *
     * @param posicao
     * @return
     */
    private ElementoLista consultaInterna(int posicao)
    {
        // Verifica se a posição procurada é válida
        if (posicao >= 0 && posicao < this.qtdElementos)
        {
            ElementoLista proximo = primeiroElemento;

            // Percorre a lista até a posição X
            for (int i = 0; i < posicao; i++)
            {
                proximo = proximo.getProxElemento();
            }

            return proximo;
        } else
        {
            return null;
        }
    }

    public int localizarElemento(String elemento)
    {
        ElementoLista elementoLista = primeiroElemento;
        
        int i = 0;
        while ((elementoLista != null))
        {
            // Se o conteúdo do elemento atual for igual ao do passado por parametro
            if (elementoLista.getElemento().equals(elemento))
            {
                // Retorna posição do elemento
                return i;
            }
            
            i++;
            elementoLista = elementoLista.getProxElemento();
        }
        
        // Se não encontrar, retonar erro
        return -1;
    }

    /**
     * Imprime todos os valores da lista
     */
    public void printarLista()
    {
        if (verificarListaVazia())
        {
            System.out.println("Lista vazia");
        }

        // Instancia elemento e atribui o primeiro elemento da lista a ele
        ElementoLista elemento = this.primeiroElemento;

        // Enquanto o elemento não for null
        while (elemento != null)
        {
            // Printa valor do elemento
            System.out.print(elemento.getElemento() + " - ");

            // Seta próximo elemento
            elemento = elemento.getProxElemento();
        }

    }

    /**
     * Verifica se a lista esta vazia
     *
     * @return true se estiver vazia e false se tiver elementos na lista
     */
    public boolean verificarListaVazia()
    {
        return primeiroElemento == null;
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
        return qtdElementos;
    }

    public void setTamanhoLista(int tamanhoLista)
    {
        this.qtdElementos = tamanhoLista;
    }

}
