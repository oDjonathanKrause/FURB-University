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
    private static final String LINHA = "--------------------------------------------------------------------------";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        grafo1();
        grafo2();
        grafo3();
    }
    
    static void grafo1()
    {
        System.out.println("Grafo 1");
        System.out.println(LINHA);
        
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");
        
        Grafo grafo1 = new Grafo();
        grafo1.addAresta("aresta1", 2, v1, v2);
        grafo1.addAresta("aresta2", 5, v1, v3);
        grafo1.addAresta("aresta3", 7, v3, v4);
        grafo1.addAresta("aresta4", 4, v2, v4);
        grafo1.addAresta("aresta5", 3, v4, v5);
        
        System.out.println(grafo1.toString());
        System.out.println("Lista de Adjacência:" + grafo1.printListaAdjacencia());
        
        Dijkstra dijkstra = new Dijkstra(grafo1, v1, v4);
        System.out.print("\nMatriz Roteamento Dijkstra de v1 -> v4:");
        dijkstra.getMatrizRoteamento();
        
        dijkstra = new Dijkstra(grafo1, v1, null);
        System.out.print("\nMatriz Roteamento Dijkstra a partir de v1:");
        dijkstra.getMatrizRoteamento();
        
        System.out.println(LINHA);
    }
    
    static void grafo2()
    {
        Grafo grafo2 = new Grafo();
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");
        Vertice v6 = new Vertice("v6");
        Vertice v7 = new Vertice("v7");
        
        grafo2.addAresta("aresta1", 35, v1, v2);
        grafo2.addAresta("aresta2", 12, v1, v3);
        grafo2.addAresta("aresta3", 97, v1, v6);
        grafo2.addAresta("aresta4", 44, v1, v4);
        grafo2.addAresta("aresta5", 21, v2, v4);
        grafo2.addAresta("aresta6", 37, v3, v6);
        grafo2.addAresta("aresta7", 25, v4, v5);
        grafo2.addAresta("aresta8", 58, v4, v6);
        grafo2.addAresta("aresta9", 81, v4, v7);
        grafo2.addAresta("aresta10", 15, v6, v7);

        System.out.println("Grafo 2");
        System.out.println(grafo2.toString());
        
        System.out.println("Lista de Adjacência:" + grafo2.printListaAdjacencia());
        
        Dijkstra dijkstra = new Dijkstra(grafo2, v1, null);
        System.out.print("\nMatriz Roteamento Dijkstra a partir de v1:");
        dijkstra.getMatrizRoteamento();
        
        System.out.println(LINHA);
        
        dijkstra.setMatrizRoteamento(grafo2);
    }
    
    static void grafo3()
    {
        Grafo grafo3 = new Grafo();
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");
        
        grafo3.addAresta("aresta1", 10, v1, v2);
        grafo3.addAresta("aresta2", 50, v1, v3);
        grafo3.addAresta("aresta3", 65, v1, v4);
        grafo3.addAresta("aresta4", 4, v2, v5);
        grafo3.addAresta("aresta5", 30, v2, v3);
        grafo3.addAresta("aresta6", 20, v3, v4);
        grafo3.addAresta("aresta7", 44, v3, v5);
        grafo3.addAresta("aresta8", 23, v4, v5);
        grafo3.addAresta("aresta9", 70, v4, v2);
        grafo3.addAresta("aresta10", 6, v5, v1);

        System.out.println("Grafo 3");
        System.out.println(grafo3.toString());
        
        System.out.println("Lista de Adjacência:" + grafo3.printListaAdjacencia());
        
        Dijkstra dijkstra = new Dijkstra(grafo3, v1, null);
        System.out.print("\nMatriz Roteamento Dijkstra a partir de v1:");
        dijkstra.getMatrizRoteamento();
        
        System.out.println(LINHA);
    }
}
