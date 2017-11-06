package grafos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djonathan
 */
public class Vertice implements Comparable<Vertice>
{
    private float distancia;
    private int grau;
    private String rotulo, status;
    private Vertice pai;
    public List<Aresta> arestas;
    List<Vertice> verticesAdjacentes;
    int botCount;

    
    /**
     * Construtor do vértice. Inicializa as listas e seta o identificador do vértice.
     * @param rotuloVertice - Identificado do vértice.
     */
    public Vertice(String rotuloVertice)
    {
        this.rotulo = rotuloVertice;
        this.verticesAdjacentes = new ArrayList();
        this.arestas = new ArrayList();
        this.botCount = 0;
    }
    
    /**
     * Verifica quais são os vértices adjacentes a this.
     * A tratativa é diferente caso o grafo for dirigido. Se for dirigido, não entra no segundo if.
     * @param isDirigido - Parâmetro que informa se o grafo é ou não dirigido.
     * @return List de vértices adjacentes
     */
    public List<Vertice> getAdjacentes(boolean isDirigido)
    {
        // Percorre todas as arestas do vértice
        for(Aresta aresta : arestas)
        {
            // Se a origem da aresta atual for this, add o vértice de destino na lista
            if(aresta.getVerticeOrigem().equals(this))
                verticesAdjacentes.add(aresta.getVerticeDestino());
            // Se o destino da aresta atual for this e o grafo não for dirigido, add o vértice de origem na lista
            else if(!isDirigido && aresta.getVerticeDestino().equals(this))
                verticesAdjacentes.add(aresta.getVerticeOrigem());
        }

        return verticesAdjacentes;
    }

    /**
     * Dado o vértice this, verifica quais são as arestas até o destino
     * @param vertice vértice de destino
     * @return Aresta entre this e vertice de destino (param vertice)
     */
    public Aresta getArestaPorVertices(Vertice vertice)
    {
        for(Aresta aresta : arestas)
        {
            if(aresta.getVerticeOrigem().equals(this) && aresta.getVerticeDestino().equals(vertice))
                return aresta;
            else if(aresta.getVerticeDestino().equals(this) && aresta.getVerticeOrigem().equals(vertice))
                return aresta;
        }

        return null;
    }

    /**
     * Override do compareTo para utilização do Collections.min().
     * @param v - vértice analisado.
     * @return 1 se this tem menor distância, -1 se não, 0 se for igual.
     */
    @Override
    public int compareTo(Vertice v) 
    {
        return this.distancia > v.getDistancia() ? 1 : this.distancia < v.getDistancia() ? -1 : 0;
    }
    
    // Gets e sets
    public String getRotulo()
    {
        return rotulo;
    }

    public void setRotulo(String rotulo)
    {
        this.rotulo = rotulo;
    }

    public Vertice getPai() {
        return pai;
    }

    public void setPai(Vertice pai) {
        this.pai = pai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public float getDistancia() 
    {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public int getGrau()
    {
        return grau;
    }

    public void setGrau(int grau)
    {
        this.grau = grau;
    }

    public int getBotCount()
    {
        return botCount;
    }

    public void setBotCount(int botCount)
    {
        this.botCount = botCount;
    }

    public Vertice(){}
}
