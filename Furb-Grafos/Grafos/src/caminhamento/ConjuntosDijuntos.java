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
public class ConjuntosDijuntos
{
    private Grafo grafo;
    private List componentes = new ArrayList();
    
    public void connectedComponents()
    {
        for(Vertice v : grafo.getVertices())
            makeSet(v);
        
        //for(Aresta a : grafo.getArestas())
            //if(findSet(a.getVerticeOrigem()))
                //union(u, v);
    }
    
    private void makeSet(Vertice v)
    {
        
    }
    
    private boolean findSet(Vertice v)
    {
        return false;
    }
    
    private void union(Vertice u, Vertice v)
    {
        
    }
    
}
