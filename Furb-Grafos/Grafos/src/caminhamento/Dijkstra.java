package caminhamento;

import grafos.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Djonathan
 */
public class Dijkstra 
{
    private static final int INFINITO = Integer.MAX_VALUE;
    private List<Vertice> s = new ArrayList();
    private List<Vertice> q = new ArrayList();
    private Vertice destino;
    private static final Vertice nil = new Vertice("nil");

    private StringBuilder paiPrint = new StringBuilder();
    private StringBuilder dPrint = new StringBuilder();
    private StringBuilder cabecalhoPrint = new StringBuilder();

    /**
     * Contrutor do Dijsktra. Se o destino for nulo, aplica o algoritmo em todos os vétices do Grafo.
     * Se o destino for especificado, executa até chegar no vértice.
     * @param grafo Grafo.
     * @param origem Vértice de origem.
     * @param destino Vértice de destino. Pode ser nulo.
     */
    public Dijkstra(Grafo grafo, Vertice origem, Vertice destino) 
    {
        if(grafo.getOrdem() == 0)
            throw new IllegalArgumentException("O grafo precisa ser populado");
        
        // Se tiver um destino, seta ele no atributo
        if(destino != null)
            this.destino = destino;
        else 
            this.destino = null;
        
        // Seta valores para printar matriz de roteamento
        paiPrint.append("pai:\t");
        dPrint.append("d:\t");
        
        // Executa o dijkstra
        dijkstra(grafo, origem);
    }
    
    /**
     * Inicializa o grafo para aplicar o dijkstra.
     * Seta para cada vértice: seta a distância inicial como infinito, o status para aberto
     * add o vérice em q e seta o pai para null.
     * @param origem - Vértice de origem
     * @param grafo - Grafo onde o algoritmo será aplicado

     * Pseudo-código:
        INITIALIZE-SINGLE-SOURCE(G,s)
        01. for each vertex v ∈ V[G]  
        02.     do d[v] ←∞
        03.        ∏[v] ← NIL 
        04. d[s] ← 0
    */
    private void initialize(Grafo grafo, Vertice origem)
    {
        for(Vertice vertice : grafo.getVertices())
        {
            vertice.setDistancia(INFINITO);
            vertice.setStatus("ABERTO");
            vertice.setPai(nil);
            q.add(vertice);
        }

        origem.setDistancia(0);
        paiPrint.append("nil\t");
        dPrint.append("0\t");
    }
    
    /**
     * Verifica a menor distância entre os vértices do grafo
     * @param origem - vértice de origem
     * @param grafo - grafo onde o algoritmo será aplicado
     
     * Pseudo-código:
        DIJKSTRA(G, s)
        01. INITIALIZE-SINGLE-SOURCE(G,s) 
        02. S ←∅
        03. Q ← V[G] 
        04. while Q ≠∅
        05.    do u ← EXTRACT-MIN(Q) 
        06.        S ← S ∪ [u] 
        07.        for each vertex v ∈ Adj[u] 
        08.             do RELAX(u,v) 
     */
    public void dijkstra(Grafo grafo, Vertice origem)
    {
        // Inicializa o grafo para aplicar o algoritmo
        initialize(grafo, origem);
        
        while(!q.isEmpty())
        {
            // Pega o vértice não explorado com menor distância
            Vertice u = extractMin(q);
            
            // Concatena o vértice no cabeçalho para printar
            cabecalhoPrint.append(u.getRotulo() + "\t");
            
            // Para o loop se tiver um destino determinado e o vértice u for ele
            if(this.destino != null)
                if(u.equals(this.destino))
                {
                    // Limpa q e para o loop
                    q.clear();
                    break;
                }
            
            // Seta o vértice na lista dos explorados e altera o status dele
            s.add(u);
            u.setStatus("EXPLORADO");
            
            // Remove o vértive u da lista de não explorados
            q.remove(u);
            
            // Relaxa cada vértice não explorado adjacente a v
            for(Vertice v : u.getAdjacentes())
                if(!v.getStatus().equals("EXPLORADO"))
                    relax(u, v);
        }
        
        //setMatrizRoteamento(grafo);
    }
    
    /**
     * Faz o relaxamento dos vértices
     * @param u - vértice com menor distância sendo processado
     * @param v - vértice adjacente a u
     
     * Pseudo-código
        RELAX(u, v)
        01. if d[v] > d[u] + w(u,v) 
        02.     then d[v] ← d[u] + w(u,v)  
        03.          ∏[v] ← u
     */
    private void relax(Vertice u, Vertice v)
    {
        Aresta arestaUV = u.getArestaPorVertices(v);
        float wuv = arestaUV.getValor();
        
        // Se a distância do vérice adjacente for maior do que a distância de u + o vértice que leva até v
        //  Atualiza a distância dele
        if(v.getDistancia() > (u.getDistancia() + wuv))
        {
            // Seta valores para printar matriz de roteamento
            paiPrint.append(u.getRotulo() + "\t");
            dPrint.append(u.getDistancia() + wuv + "\t");
            
            // Atualiza valores do vértice
            v.setDistancia(u.getDistancia() + wuv);
            v.setPai(u);
        }
    }
    
    /**
     * Utiliza stream na lista q para verificar qual o vértice com menor distância.
     * @param q - lista de vértices não fechados
     * @return Vértice não explorado com menor distância
     */
    private Vertice extractMin(List<Vertice> q)
    {
        return Collections.min(q);
    }
    
    /**
     * Printa a matriz de roteamento do dijkstra.
     */
    public void getMatrizRoteamento()
    {
        System.out.println("\n\t" + cabecalhoPrint);
        System.out.println(paiPrint);
        System.out.println(dPrint);
        
    }
    
    private void setMatrizRoteamento(Grafo grafo) 
    {
        for(Vertice v : grafo.getVertices())
        {
            cabecalhoPrint.append(v.getRotulo() + "\t");
            paiPrint.append(v.getPai().getRotulo() + "\t");
            dPrint.append(v.getDistancia() + "\t");
        }
    }

    
    public Dijkstra(){}

}
