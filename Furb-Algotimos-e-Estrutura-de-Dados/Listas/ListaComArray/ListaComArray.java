package ListaComArray;

import listas.InterfaceListas;

/**
 *
 * @author Djonathan
 * @param <T> - Tipo de dado
 */
public class ListaComArray<T> implements InterfaceListas<T>
{

    private T[] valores;
    private static final int MAXTAM = 100; // tamanho máximo do array
    private int ultimo = 0;

    public ListaComArray()
    {
        valores = (T[]) new Object[MAXTAM];
    }

    /**
     * Insere valor no fim da lista
     *
     * @param valor
     */
    @Override
    public void insere(T valor)
    {
        // Tenta inserir o valor na última posição
        try
        {
            // Atribui o valor a última posição da lista
            valores[ultimo] = valor;

            // Aumenta o tamanho da lista
            ultimo++;
        } catch (ArrayIndexOutOfBoundsException aiobe)
        {
            // não é feito nada, ou seja, o elemento não é incluído por não ter mais espaço
        }
    }

    /**
     * Insere valor na posição X
     *
     * @param valor
     * @param posicao
     */
    public void insere(T valor, int posicao)
    {
        // Se a lista não estiver cheia, insere
        if (!this.estaCheia() && posicao <= ultimo)
        {
            T temp;
            
            // Percorre até a posição X
            for (int i = posicao; i <= ultimo; i++)
            {
                // Faz a substituição de valores
                temp = valores[i];
                valores[i] = valor;
                valor = temp;
            }
            
            // Incrementa tamanho da lista
            ultimo++;
        }
    }

    /**
     * Verifica se a lista esta cheia
     *
     * @return true se estiver cheia
     */
    public boolean estaCheia()
    {
        return (ultimo == MAXTAM);
    }

    /**
     * Verifica se a lista esta vazia
     *
     * @return true se estiver vazia
     */
    public boolean estaVazia()
    {
        return (ultimo == 0);
    }

    /**
     * Retira valor da posição X
     * @param posicao
     * @return 
     */
    public T retira(int posicao)
    {
        T valorRemovido = null;
        
        // Se a lista não estiver vazia, a posição for maior do que 0 e a posição for menor do que o último...
        if (!this.estaVazia() && posicao >= 0 && posicao < ultimo)
        {
            // Atribui o valor removido 
            valorRemovido = valores[posicao];
            
            // Percorre e reorganiza a lista
            for (int i = posicao; i < ultimo - 1; i++)
            {
                valores[i] = valores[i + 1];
            }
            
            // Decrementa tamanho 
            ultimo--;
        }
        
        // Retorna valor removido
        return valorRemovido;
    }

    /**
     * Localiza posição do valor X
     * @param valor
     * @return posição do valor X
     */
    @Override
    public int localiza(T valor)
    {
        // Percorre a lista
        for (int i = 0; i < this.ultimo; i++)
        {
            // Se o valor da posição i for igual ao passado por parâmetro, retona posicao
            if (valores[i].equals(valor))
            {
                // Retorna posição
                return i;
            }
        }
        
        // Valor não encontrado, retorna erro
        return -1;
    }

    /**
     * Verifica o tamanho da lista
     * @return int - tamanho da lista
     */
    @Override
    public int getTamanho()
    {
        return this.ultimo;
    }

    /**
     * Imprime lista concatenando os valores numa String
     * @return String com os valores da lista
     */
    @Override
    public String imprime()
    {
        String retorno = "[";

        for (int i = 0; i < this.ultimo; i++)
        {
            retorno += valores[i] + "; ";
        }
        try
        {
            // Retira a última vírgula e espaço
            retorno = retorno.substring(0, retorno.length() - 2);

            // Concatena fechamento e retorna a String
            return retorno + "]";
        } catch (StringIndexOutOfBoundsException strExc)
        {
            return "[]";
        }
    }

    /**
     * Consulta valor da posição X
     * @param posicao
     * @return T - Valor da posição X
     */
    @Override
    public T consulta(int posicao)
    {
        // Tenta pegar o valor da posição X, senão conseguir, a posição não existe
        try
        {
            // Retorna valor da posição X
            return this.valores[posicao];
        } catch (IndexOutOfBoundsException iae)
        {
            return null;
        }
    }
}
