package busca;

import grafos.Grafo;
import grafos.Vertice;

/**
 * NÃO FINALIZADO
 * @author Djonathan
 */
public class DFS
{
    private int tempo;
   
    public void DFS(Grafo g)
    {
        for(Vertice v : g.getVertices())
        {
            v.setStatus("BRANCO");
            v.setDistancia(0);
        }
        
        this.tempo = 0;

        for(Vertice v : g.getVertices())
            if(v.getStatus().equals("BRANCO"))
                DFSVisit(v, g);
        
    }
    
    private void DFSVisit(Vertice u, Grafo g)
    {
        u.setStatus("CINZA");
        tempo++;
        u.setAbertura(tempo);
        
        for(Vertice v : u.getAdjacentes(g.isDirigido()))
            if(v.getStatus().equals("BRANCO"))
                DFSVisit(u, g);
        
        u.setStatus("PRETO");
        u.setFechamento(tempo + 1);
    }
}
