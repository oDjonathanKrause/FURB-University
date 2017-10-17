package grafos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Djonathan
 */
public class Vertice implements Comparable<Vertice>
{
    private float distancia;
    private String rotulo, Status;
    private Vertice pai;
    public List<Aresta> arestas = new ArrayList();

    public Vertice(String rotuloVertice)
    {
        this.rotulo = rotuloVertice;
    }
    
    public List<Vertice> getAdjacentes()
    {
        List<Vertice> verticesAdjacentes = new ArrayList();
        
        for(Aresta aresta : arestas)
        {
            if(aresta.getVerticeOrigem().equals(this))
                verticesAdjacentes.add(aresta.getVerticeDestino());
            else if(aresta.getVerticeDestino().equals(this))
                verticesAdjacentes.add(aresta.getVerticeOrigem());
        }
        
        return  verticesAdjacentes;
    }

    public Aresta getArestaPorVertices(Vertice vertice)
    {
        for(Aresta aresta : arestas)
        {
            if(aresta.getVerticeOrigem().equals(this) && aresta.getVerticeDestino().equals(vertice))
                return aresta;
            else if(aresta.getVerticeDestino().equals(this) && aresta.getVerticeOrigem().equals(vertice))
                return aresta;
        }
        System.out.println("NULLAO");
        return null;
    }

    public Vertice() { }
    
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
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public float getDistancia() {
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

 


    

    
}
