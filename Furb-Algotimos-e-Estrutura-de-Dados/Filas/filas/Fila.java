package filas;

/**
 * @author djonathan.krause
 * @param <T> Tipo de dado da Fila
 */
public interface Fila<T>
{
    void insere(T valor);
    
    T retira();
    
    boolean vazia();
    
    boolean cheia();
    
    void libera();
    
    
}
