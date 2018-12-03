package caminhamento;

import grafos.Grafo;
import grafos.Vertice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * NÃO FINALIZADO!
 * @author Djonathan
 */
public class CaixeiroViajante
{
    private final String NAO_EXPLORADO = "NÃO EXPLORADO";
    private final String EXPLORADO = "EXPLORADO";
    
    /**
     * Utiliza o algoritmo do vizinho mais próximo para traçar um caminho a partir do vértice inicial V.
     * @param grafo grafo que será analisado.
     * @param verticeInicial vértice inicial.
     * @return List com os vértices do caminho.
     */
    public List<Vertice> getListVizinhoMaisProximo(Grafo grafo, Vertice verticeInicial)
    {
        List<Vertice> listaVizinhoMaisProximo = new ArrayList();
       
        getVizinhoMaisProximoRecursivo(grafo, verticeInicial, listaVizinhoMaisProximo);
        
        return listaVizinhoMaisProximo;
    }
    
    public Vertice getVizinhoMaisProximoRecursivo(Grafo grafo, Vertice verticeAtual, List<Vertice> listaVizinhoMaisProximo)
    {
        // Considera os vértices adjacentes ao atual
        List<Vertice> adjacentesDoAtual = verticeAtual.getAdjacentes(grafo.isDirigido());
        
        // Pega o vizinho mais próximo
        Vertice vizinhoMaisProximo = Collections.min(adjacentesDoAtual);
        
        // Seta o vértice não foi explorado, segue a partir dele
            // Seta o status para explorado
            // Add o vértice na lista
            // Efetua a recursão para este vértice
        return vizinhoMaisProximo;       
    }
    
}
