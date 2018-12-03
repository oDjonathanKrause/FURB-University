package ListaEncadeada;

/**
 *
 * @author djonathan.krause
 * @param <T> - Tipo de dado
 */
public class ListaEncadeada<K, T> 
{

    private ElementoLista<T> primeiroElemento = null;
    private ElementoLista<T> ultimoElemento = null;
    private int qtdElementos = 0;

    // Construtor 
    public ListaEncadeada()
    {
    }

    /**
     * Retorna o valor do último nó que armazena o valor igual ao informado por
     * parâmetro. Também verifica a quantidade de ocorrências de um elemento (valor não retornado)
     *
     * @param x - Valor do elemento
     * @return indice - int posição do último elemento igual a X
     */
    public int ultimoIndiceDe(T x)
    {
        ElementoLista elementoLista = primeiroElemento;
        int qtdOcorrencias = 0;
        int ultimaOcorrencia = 0;

        int i = 0;
        while ((elementoLista != null))
        {
            // Se o conteúdo do elemento atual for igual ao do passado por parametro
            if (elementoLista.getElemento().equals(x))
            {
                // Incrementa a quantidade de ocorrencias
                qtdOcorrencias += 1;

                // Seta a ultima ocorrencia como o ultimo indice encontrado para o elemento
                ultimaOcorrencia = i;
            }

            i++;
            elementoLista = elementoLista.getProxElemento();
        }

        if (qtdOcorrencias == 0)
        {
            return -1;
        }
        return ultimaOcorrencia;
    }

    /**
     * Imprime a lista invertida
     * @return String - lista invertida
     */
    public String imprimeInverso()
    {
        String listaInvertida = "";

        // Percorre a lista de tras pra frente
        for(int i = qtdElementos - 1; i >= 0; i--)
            listaInvertida += consulta(i) + " - ";

        return listaInvertida;
    }

    /**
     * Insere elemento no inicio da lista
     *
     * @param elemento
     */
    public void insere(T elemento)
    {
        // Cria elemento
        ElementoLista novoElemento = new ElementoLista();
        novoElemento.setElemento(elemento);

        // Se estiver vazia, o primeiro recebe o elemento criado
        if (this.estaVazia())
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
     * Insere o elemento na posição X
     *
     * @param valorElemento
     * @param posicao
     */
    public void insere(T valorElemento, int posicao)
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
     * Retira o elemento da posição X
     *
     * @param posicao
     * @return Elemento removido
     */
    public T retira(int posicao)
    {
        // Se a posição for zero, é o primeiro elemento
        if (posicao == 0)
        {
            // Seta retorno como o atual primeiro elemento
            T elementoRemovido = this.primeiroElemento.getElemento();

            // O primeiro elemento passa a ser o seu próximo
            this.primeiroElemento = primeiroElemento.getProxElemento();

            // Diminui a qtd de elementos na lista
            qtdElementos--;

            // Retorna elemento removido
            return elementoRemovido;
        } // Se a posição for maior do que zero e maior do que a qtd de elementos na lista
        else if (posicao > 0 && posicao < qtdElementos)
        {
            // Encontra elemento da posição anterior a informada
            ElementoLista<T> elementoLista = this.consultaInterna(posicao - 1);

            // Seta o elemento removido como o próximo do encontrado acima
            ElementoLista<T> elementoRemovido = elementoLista.getProxElemento();

            // Seta o próximo do elemento anterior ao removido como o próximo do removido
            elementoLista.setProxElemento(elementoRemovido.getProxElemento());

            // Diminui a qtd de elemtnos na lista
            qtdElementos--;

            // Retorna o elemento removido
            return elementoRemovido.getElemento();
        } // Se a posição for a última da lista
        else if (posicao == qtdElementos)
        {
            // Seta o último elemento como o removido
            T elementoRemovido = this.ultimoElemento.getElemento();

            // Encontra o pnúltimo elemeneto da lista
            ElementoLista novoUltimo = this.consultaInterna(posicao - 1);

            // Seta o pnúltimo como o novo último
            this.ultimoElemento = novoUltimo;

            // Seta o próximo do novo último como null
            novoUltimo.setProxElemento(null);

            // Diminui a qtd de elementos da lista
            qtdElementos--;

            // Retorna o elemento removido
            return elementoRemovido;
        }

        // Se a posição não estiver na lista, retorna null
        return null;
    }

    /**
     * Verifica se a lista esta vazia
     *
     * @return true se estiver vazia e false se tiver elementos na lista
     */
    public boolean estaVazia()
    {
        return primeiroElemento == null;
    }

    /**
     * Percorre a lista encadeada até a posição X. Método privado.
     *
     * @param posicao
     * @return
     */
    private ElementoLista<T> consultaInterna(int posicao)
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

    /**
     * Localiza posição do elemento
     *
     * @param elemento - elemento que será localizado
     * @return posição do elemento
     */
    public int localiza(T elemento)
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
     * Concatena todos os elementos da lista numa String e retorna.
     *
     * @return String - lista
     */
    public String imprime()
    {
        String lista = "";

        if (estaVazia())
        {
            return lista = "Lista vazia";
        }

        // Instancia elemento e atribui o primeiro elemento da lista a ele
        ElementoLista elemento = this.primeiroElemento;

        // Enquanto o elemento não for null
        while (elemento != null)
        {
            // Printa valor do elemento
            //System.out.print(elemento.getElemento() + " - ");

            // Concatena valor na String lista
            lista += elemento.getElemento() + " - ";

            // Seta próximo elemento
            elemento = elemento.getProxElemento();
        }

        return lista;
    }

    /**
     * Consulta o elemento da posição X
     *
     * @param posicao
     * @return elemento
     */
    public T consulta(int posicao)
    {
        return this.consultaInterna(posicao).getElemento();
    }

    /**
     * Verifica o tamanho da lista e retorna a quantidade de elementos
     *
     * @return qtdElementos
     */
    public int getTamanho()
    {
        return qtdElementos;
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

    public void setTamanhoLista(int tamanhoLista)
    {
        this.qtdElementos = tamanhoLista;
    }
}