package caminhamento;

import grafos.Grafo;
import grafos.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 * NÃO FINALIZADO
 * Passos do algoritmo do Carteiro Chinês:
 *  P1: Determine os vértices de grau ímpar;
 *  P2: Aplique o Dijkstra e construa a matriz de distância D para os vértices de grau ímpar;
 * 
 * @author Djonathan
 */
public class CarteiroChines
{
    
    /**
     * Executa o passo 1 do algoritmo do carteiro chinês.
     * Monta uma lista com os vértices que tem grau ímpar.
     * @param grafo grafo analisado.
     * @return List de vértices.
     */
    public List<Vertice> getVerticesGrauImpar(Grafo grafo)
    {
        List verticesGrauImpar = new ArrayList();
        
        for(Vertice v : grafo.getVertices())
            if(v.getGrau() % 2 != 0)
                verticesGrauImpar.add(v);
        
        return verticesGrauImpar;
    }
    
    
}
