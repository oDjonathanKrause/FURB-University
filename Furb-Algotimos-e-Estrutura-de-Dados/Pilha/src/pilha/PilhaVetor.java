package pilha;

import java.util.IllegalFormatException;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

/**
 *
 * @author djonathan.krause
 * @param <T> - Tipo de dado da pilha
 */
public class PilhaVetor<T> implements Pilha<T>
{

    private int qtdElementos;
    private int tamanho;
    private T[] vetor;

    // Construtor
    public PilhaVetor()
    {

        this.qtdElementos = 0;
        this.tamanho = 10;
        this.vetor = (T[]) new Object[this.tamanho];
    }

    @Override
    public void push(T valor) throws Exception
    {
        try
        {
            // Verifica se o valor é nulo
            if (valor == null)
            {
                throw new Exception("O valor não pode ser nulo!");
            }
            // Verifica se a pilha esta cheia
            else if (qtdElementos == tamanho)
            {
                throw new Exception("A pilha esta cheia!");
            }
            // Se não tiver exception, empilha elemento
            else
            {
                qtdElementos++;
                vetor[getTopo()] = valor;
            }

        } catch (Exception exception)
        {
            System.out.println("Exception: " + exception);
        }
    }

    @Override
    public T pop() throws Exception
    {
        T valor = vetor[getTopo()];

        if (!vazia())
        {
            qtdElementos--;
        }

        return valor;
    }

    @Override
    public T peek()
    {
        T valor = vetor[getTopo()];
        return valor;
    }

    @Override
    public boolean vazia()
    {
        if (this.qtdElementos == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Verifica o valor do topo da pilha
     *
     * @return valor do topo
     */
    public int getTopo()
    {
        if (vazia())
        {
            return 0;
        }
        return qtdElementos - 1;
    }
    
    @Override
    public boolean cheia()
    {
        if(this.qtdElementos == this.tamanho)
            return true;
        return false;
    }

    @Override
    public void libera()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getQtdElementos()
    {
        return qtdElementos;
    }

    public int getTamanho()
    {
        return tamanho;
    }
    
    

}
