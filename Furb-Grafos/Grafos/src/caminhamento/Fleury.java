package caminhamento;

import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djonathan
 */
public class Fleury
{
    private List<Vertice> vertices;
    private List<Aresta> arestas;

    /**
     * Contrutor do método. Popula as listas de vértices e arestas locais.
     * @param grafo
     */
    public Fleury(Grafo grafo)
    {
        this.vertices = grafo.getVertices();
        this.arestas = grafo.getArestas();
    }

    /**
     * Procura um ciclo euleriano num grafo G utilizando o algoritmo de Fleury.
     * @param grafo grafo analisado.
     * @return List com os vértices que formam o ciclo euleriano. 
     */
    public List<Vertice> getCicloEuleriano(Grafo grafo)
    {
        List<Vertice> cicloEuleriano = new ArrayList();
        
        // Para cada aresta do grafo
        // Verifica se é uma ponte, se for, passa por ela somente se não houver outra opção.
        // Remove a atual, se algum vértice ficar isolado, remove ele
        
        return cicloEuleriano;
    }
    
    /**
     * Verifica se a aresta em questão é uma ponte.
     * Uma ponte é definida por uma aresta que quando removida, deixa o grafo desconexo.
     * @param grafo grafo analisado.
     * @param aresta aresta analisada.
     * @return true se for uma ponte.
     */
    public boolean isPonte(Grafo grafo, Aresta aresta)
    {
        return false;
    }
    
}
