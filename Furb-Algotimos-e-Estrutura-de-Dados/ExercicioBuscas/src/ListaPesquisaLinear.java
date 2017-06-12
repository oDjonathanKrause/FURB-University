
/**
 *
 * @author nomes....
 */
class ListaPesquisaLinear implements InterfaceExercicio8
{

    private ElementoLista<Veiculo> primeiroElemento = null;
    private ElementoLista<Veiculo> ultimoElemento = null;
    private int qtdElementos = 0;

    public void addAll(Veiculo[] vetor)
    {
        // Cria elemento
        ElementoLista novoElemento = new ElementoLista();
        novoElemento.setElemento(vetor[0]);

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

    public Veiculo[] localizaMaisAntigo()
    {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public Veiculo getMenorPlaca()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Veiculo getMaiorPlaca()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Veiculo pesquisa(String placa)
    {
        ElementoLista elementoLista = primeiroElemento;
        Veiculo temp = new Veiculo(placa, "nao econtrado", 0000, "nao econtrado");

        int i = 0;
        while ((elementoLista != null))
        {
            // Se o conteúdo do elemento atual for igual ao do passado por parametro
            if (elementoLista.getElemento().compareTo(temp) == 0)
            {
                // Retorna posição do elemento
                return elementoLista.getElemento();
            }

            i++;
            elementoLista = elementoLista.getProxElemento();
        }

        // Se não encontrar, retonar erro
        return temp;
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
     *
     * @author djonathan.krause
     * @param <T> - Tipo de dado genérico
     */
    public class ElementoLista<T>
    {

        private Veiculo elemento;
        private ElementoLista proxElemento = null;
        private ElementoLista elementoAnt = null;

        // Construtor vazio
        public ElementoLista()
        {
        }

        /**
         * Construtor da classe
         *
         * @param elemento
         * @param proxElemento
         */
        public ElementoLista(Veiculo elemento, ElementoLista proxElemento)
        {
            this.setElemento(elemento);
            this.setProxElemento(proxElemento);
            this.setElementoAnt(elementoAnt);
        }

        /**
         * Gets e sets da classe ElementoLista
         *
         * @return T - tipo de dado
         */
        public Veiculo getElemento()
        {
            return elemento;
        }

        public void setElemento(Veiculo elemento)
        {
            this.elemento = elemento;
        }

        public ElementoLista getProxElemento()
        {
            return proxElemento;
        }

        public void setProxElemento(ElementoLista proxElemento)
        {
            this.proxElemento = proxElemento;
        }

        public ElementoLista getElementoAnt()
        {
            return elementoAnt;
        }

        public void setElementoAnt(ElementoLista elementoAnt)
        {
            this.elementoAnt = elementoAnt;
        }

    }

}
