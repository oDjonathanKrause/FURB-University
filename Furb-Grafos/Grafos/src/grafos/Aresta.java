package grafos;

import java.util.List;

/**
 *
 * @author Djonathan
 */
public class Aresta
{
    private String nome;
    private List<Vertice> vertices;

    // Contrutores
    public Aresta()
    {   }

    public Aresta(String nome)
    {
        this.nome = nome;
    }
    
    // Gets e sets
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }    

    public List<Vertice> getVertices()
    {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices)
    {
        this.vertices = vertices;
    }
    
    
}
