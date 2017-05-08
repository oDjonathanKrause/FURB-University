package filas;

/**
 * @author djonathan.krause
 */
public class FilaVetor<T> implements Fila
{

    private int qtdElementos;
    private int inicio;
    private int tamanho;
    private T[] vetor;

    /**
     * Construtor. Seta instancia vetor, tamanho e qtd de elementos
     */
    public FilaVetor()
    {
        this.qtdElementos = 0;
        this.tamanho = 10;
        this.vetor = (T[]) new Object[this.tamanho];
    }

    @Override
    public void insere(Object valor)
    {
        // Se não estiver cheia, insere
        if(!cheia())
        {
            // Insere o valor na posição final da fila
            vetor[getFim()] = (T) valor;
            
            // Incrementa a qtd de elementos
            qtdElementos++;
        }
    }

    @Override
    public T retira()
    {
        // Cria elemento do tipo T que recebe o valor do inicio da fila
        T elemento = vetor[inicio];
        
        // Decrementa a qtd de elementos na fila
        qtdElementos--;
        
        // Retorna elemento
        return elemento;
    }

    @Override
    public boolean vazia()
    {
        return qtdElementos == 0;
    }

    @Override
    public boolean cheia()
    {
        return qtdElementos == tamanho;
    }

    @Override
    public void libera()
    {
        for(int i = 0; i < qtdElementos; i++)
            retira();
        qtdElementos = 0;
    }
    
    /**
     * Calcula valor do fim da fila. 
     * Fim = (inicio + qtdDeElementos) & tamanho
     * @return fim da fila
     */
    public int getFim()
    {
        return (inicio + qtdElementos) % tamanho;
    }

    public int getQtdElementos()
    {
        return qtdElementos;
    }

    public void setQtdElementos(int qtdElementos)
    {
        this.qtdElementos = qtdElementos;
    }

    public int getInicio()
    {
        return inicio;
    }

    public void setInicio(int inicio)
    {
        this.inicio = inicio;
    }

    public int getTamanho()
    {
        return tamanho;
    }

    public void setTamanho(int tamanho)
    {
        this.tamanho = tamanho;
    }

}
