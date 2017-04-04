package pilha;

/**
 *
 * @author djonathan.krause
 */
public interface Pilha<T>
{
    /**
     * O método push empilha o valor passado como parâmetro
     * @param valor - valor a ser empilhado
     * @throws Exception 
     */
    void push(T valor) throws Exception;

    /**
     * Método pop desempilha o valor do topo da pilha
     * @return valor desempilhado
     * @throws Exception 
     */
    T pop() throws Exception;

    /**
     * Método peek verifica o valor do topo da pilha
     * @return valor do topo
     */
    T peek();

    /**
     * Vertifica se a pilha esta vazia
     * @return true se estiver vazia
     */
    boolean vazia();
    
    /**
     * Verifica se a pilha esta cheia
     * @return true se estiver cheia
     */
    boolean cheia();

    void libera();
}
