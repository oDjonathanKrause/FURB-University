package pilha;

/**
 *
 * @author djonathan.krause
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
            if (valor == null)
            {
                System.out.println("O valor não pode ser nulo!");
            }
            if (qtdElementos == tamanho)
            {
                System.out.println("A pilha esta cheia!");
            }

            
            qtdElementos++;
            vetor[getTopo()] = valor;
            
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
            qtdElementos--;
        
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

    @Override
    public void libera()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getQtdElementos()
    {
        return qtdElementos;
    }
    
    
    public int getTopo()
    {
        if (vazia())
        {
            return 0;
        }
        return qtdElementos - 1;
    }
    
    
    
    

}
