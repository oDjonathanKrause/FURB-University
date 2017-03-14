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

        // Add no fim da lista
        listaEncadeada.addFim("A");
        listaEncadeada.addFim("B");
        listaEncadeada.addFim("D");
        listaEncadeada.addFim("C");
        listaEncadeada.addPosicaoX("C", 2);
        
        System.out.println("Tamanho da lista: " + listaEncadeada.getTamanhoLista() + "\n");
        
        listaEncadeada.printarLista();
        
        System.out.println("\n\nLocaliza D - Posição " + listaEncadeada.localizarElemento("D"));
        
        
        
        
        

    }

}
