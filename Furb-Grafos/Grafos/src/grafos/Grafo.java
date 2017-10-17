package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Djonathan Krause
 */
public class Grafo
{
    private int qtdVertices;
    private int[][] matrizAdjacencia;
    private List<Vertice> verticesGrafo = new ArrayList();
    private List<Aresta> arestasGrafo = new ArrayList();
    private Map<Integer, Integer> listaAdjacencia = new HashMap();

    public Grafo()
    {    }
    
    /**
     * Cria aresta entre dois vértices. Adiciona aresta na lista de arestasGrafo do grafo, e os vértices na lista de vértices.
     * @param rotulo - Rótulo/nome da aresta
     * @param valor - Valor/peso da aresta
     * @param verticeOrigem - Vértice de origem
     * @param verticeDestino - Vértice de destino
     */    
    public void addAresta(String rotulo, float valor, Vertice verticeOrigem, Vertice verticeDestino)
    {
        // Cria nova instância de aresta
        Aresta aresta = new Aresta(rotulo, valor, verticeOrigem, verticeDestino);
        
        // Add aresta instanciada na lista de arestas do vértice de origem e destino
        verticeOrigem.arestas.add(aresta);
        verticeDestino.arestas.add(aresta);
        
        // Add aresta e vértices nas listas de arestas e vértices do grafo
        arestasGrafo.add(aresta);
        
        if(!verticesGrafo.contains(verticeOrigem))
            verticesGrafo.add(verticeOrigem);
        if(!verticesGrafo.contains(verticeDestino))
            verticesGrafo.add(verticeDestino);
    }

    /**
     * Da override no toString() para printar todas as arestasGrafo e vértices do grafo
     * @return String com o grafo no formato aresta: \n origem v1 | detino v2
     */
    @Override
    public String toString()
    {
        String grafoString = "";
        
        for(Aresta a : arestasGrafo)
            grafoString += "aresta: " + a.getRotulo() + "\n origem: " + a.getVerticeOrigem().getRotulo() + " | destino: " + a.getVerticeDestino().getRotulo() + "\n";
        
        return grafoString;
    }
    
    /**
     * Verifica quais são os vértices adjacentes a v
     * @param verticeAnalisado - Vértice onde os adjacentes serão retornados
     * @return Lista com vértices adjacentes a v
     */
    public List<Vertice> getAdjacentes(Vertice verticeAnalisado)
    {
        List adjacentes = new ArrayList();
        String adjacentesString = "Adjacentes a " + verticeAnalisado.getRotulo() + "\n ";
        
        // Percorre arestas do grafo
        for(Aresta aresta : arestasGrafo)
        {
            // Se a origem da aresta atual for igual ao vértice analisado, então o destino é adjacente a ele
            if(aresta.getVerticeOrigem().equals(verticeAnalisado))
            {
                adjacentesString += aresta.getVerticeDestino().getRotulo() + " - ";
                adjacentes.add(aresta.getVerticeDestino());
            } 
            // Se o destino da aresta atual for igual ao vértice analisado, então a origem é adjacente a ele
            else if (aresta.getVerticeDestino().equals(verticeAnalisado))
            {
                adjacentesString += aresta.getVerticeOrigem().getRotulo() + " - ";
                adjacentes.add(aresta.getVerticeOrigem());        
            }
        }
        
        //System.out.println(adjacentesString);
        return adjacentes;
    }
    

    
    
    
    /**
     * Se não houver nenhuma aresta no grafo, ele é nulo.
     * @return true se o grafo for nulo.
     */
    public boolean isNulo()
    {
        return arestasGrafo.isEmpty();
    }
    
    /**
     * Verifica a ordem do grafo, ou seja, a quantidade de vértices pertencentes a G.
     * @return int com a ordem do grafo. 
     */
    public int getOrdem()
    {
        return verticesGrafo.size();
    }
    
    /**
     * Verifica o tamanho do grafo, ou seja, a quantidade de arestasGrafo pertencentes a G
     * @return int com o tamanho do grafo.
     */
    public int getTamanho()
    {
        return arestasGrafo.size();
    }


    
    // Gets e sets
    public int getQtdVertices()
    {
        return qtdVertices;
    }

    public void setQtdVertices(int qtdVertices)
    {
        this.qtdVertices = qtdVertices;
    }

    public int[][] getMatrizAdjacencia()
    {
        return matrizAdjacencia;
    }

    public void setMatrizAdjacencia(int[][] matrizAdjacencia)
    {
        this.matrizAdjacencia = matrizAdjacencia;
    }

    public List<Vertice> getVertices()
    {
        return verticesGrafo;
    }

    public void setVertices(List<Vertice> vertices)
    {
        this.verticesGrafo = vertices;
    }

    public List<Aresta> getArestas()
    {
        return arestasGrafo;
    }

    public void setArestas(List<Aresta> arestas)
    {
        this.arestasGrafo = arestas;
    }

    public Map<Integer, Integer> getListaAdjacencia()
    {
        return listaAdjacencia;
    }

    public void setListaAdjacencia(Map<Integer, Integer> listaAdjacencia)
    {
        this.listaAdjacencia = listaAdjacencia;
    }
    

    
    
    
}
