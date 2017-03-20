package listas;

import ListaEncadeada.ElementoLista;

/**
 *
 * @author Djonathan
 * @param <T> - Tipo de dado
 */
public interface InterfaceListas<T> 
{
    void insere(T elemento);
    
    void insere(T elemento, int posicao);
    
    T retira(int posicao);
    
    int localiza(T elemento);
    
    String imprime();
    
    boolean estaVazia();
    
    T consulta(int posicao);
    
    int getTamanho();
}
