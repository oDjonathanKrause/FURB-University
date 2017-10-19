/*
 * Implementado na disciplina de grafos na FURB em 2017/2.
 */
package grafos;

import caminhamento.Dijkstra;

/**
 * 
 * @author Djonathan Krause
 */
public class MainApp
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        
        Grafo grafo1 = new Grafo();
        grafo1.addAresta("aresta1", 2, v1, v2);
        grafo1.addAresta("aresta2", 5, v1, v3);
        grafo1.addAresta("aresta3", 7, v3, v4);
        grafo1.addAresta("aresta4", 4, v2, v4);
        
        
        System.out.println(grafo1.toString());
        System.out.println("Lista de Adjacência:" + grafo1.printListaAdjacencia());
        
        Dijkstra dijkstra = new Dijkstra(grafo1, v1, v4);
        System.out.print("\nMatriz Roteamento Dijkstra:");
        dijkstra.print();
        
      /*  Grafo grafoCompleto = new Grafo(2);
        grafoCompleto.addAresta(1, 2, "Aresta1", 0);
        grafoCompleto.addAresta(2, 1, "Aresta2", 0);
        System.out.println(grafoCompleto.toString());

        Grafo grafo2 = new Grafo(5);
        grafo2.addAresta(1, 2, "Aresta1", 0);
        grafo2.addAresta(1, 3, "Aresta2", 0);
        grafo2.addAresta(1, 4, "Aresta3", 0);
        grafo2.addAresta(1, 5, "Aresta4", 0);
        System.out.println(grafo2.toString());*/
        
        
    }
    
}
