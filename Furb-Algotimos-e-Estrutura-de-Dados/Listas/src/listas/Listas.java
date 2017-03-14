package listas;

import ListaEncadeada.ListaEncadeada;

/**
 *
 * @author djonathan.krause
 */
public class Listas
{
    public static void main(String[] args)
    {
        // Cria uma lista
        ListaEncadeada listaEncadeada = new ListaEncadeada();

        // Add no inicio da lista
        listaEncadeada.addInicio("A");
        listaEncadeada.addInicio("B");
        listaEncadeada.addInicio("C");
        listaEncadeada.addInicio("D");

        // Add no fim da lista
        listaEncadeada.addFim("E");
        listaEncadeada.addFim("F");

        System.out.println("Tamanho da lista: " + listaEncadeada.getTamanhoLista());
        
        listaEncadeada.printarLista();
        

    }

}
