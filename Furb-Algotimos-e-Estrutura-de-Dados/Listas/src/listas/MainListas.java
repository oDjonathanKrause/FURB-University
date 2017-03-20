package listas;

import ListaComArray.ListaComArray;
import ListaEncadeada.ListaEncadeada;

/**
 *
 * @author djonathan.krause
 */
public class MainListas
{
    public static void main(String[] args)
    {
        /* Lista Encadeada */
        System.out.println("Testes com Lista Encadeada");
        
        // Cria uma lista
        ListaEncadeada<String> listaEncadeada = new ListaEncadeada<>();

        // Add elementos na lista encadeada
        listaEncadeada.insere("A");
        listaEncadeada.insere("B");
        listaEncadeada.insere("D");
        listaEncadeada.insere("C");
        listaEncadeada.insere("C", 2);
        
        // Printa o tamanho da lista
        System.out.print("Tamanho da lista encadeada: " + listaEncadeada.getTamanho() + "\n");

        // Printa lista
        System.out.println(listaEncadeada.imprime());
        
        // Localiza elemento
        System.out.println("Localiza D - Posição " + listaEncadeada.localiza("D"));
        
        // Retira elemento da posição X
        listaEncadeada.retira(2);
        
        // Imprimipe lista
        System.out.println(listaEncadeada.imprime());
        
        // Consulta elemento da posição 1
        System.out.println("Consulta elemento da posição 1: " + listaEncadeada.consulta(1));
        
        
        /* Lista Com Array */
        System.out.println("\nTestes com Lista Com Array");
        
        // Cria lista com array do tipo String
        ListaComArray<String> listaComArray = new ListaComArray<>();
        
        // Insere elementos na lista
        listaComArray.insere("Valor 1");
        listaComArray.insere("Valor 2");
        listaComArray.insere("Valor 3");
        
        // Remove elemento da lista
        listaComArray.retira(1);
        
        // Imprime lista
        System.out.println(listaComArray.imprime());
        
        // Localiza Valor 3
        System.out.println("Valor 3 esta na posição: " + listaComArray.localiza("Valor 3"));
        
        // Consulta valor da posição 0
        System.out.println("Valor da posição 0 é: " + listaComArray.consulta(0));
    }

}
