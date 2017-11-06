package caminhamento;

import grafos.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Djonathan
 */
public class Dijkstra 
{
    private static final int INFINITO = Integer.MAX_VALUE;
    private List<Vertice> s = new ArrayList();
    private List<Vertice> q = new ArrayList();
    private Vertice destino, origem;
    private static final Vertice nil = new Vertice("nil");
    private String paiMatriz = "", dMatriz = "", cabecalhoMatriz = "";
    private Duration tempoExecucao;

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
        
        this.origem = origem;
    
        // Starta o time para verificar o tempo de execução
        Instant start = Instant.now(); 
        
        // Executa o dijkstra
        dijkstra(grafo, origem);
        
        // Para o timer
        Instant end = Instant.now(); 
        tempoExecucao = Duration.between(start, end);
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

        // Origem inicia com a distância 0
        origem.setDistancia(0);
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
            for(Vertice v : u.getAdjacentes(grafo.isDirigido()))
                if(!v.getStatus().equals("EXPLORADO"))
                    relax(u, v);
        }
        
        // Monta matriz de roteamento
        setMatrizRoteamento(grafo);
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
    public void printMatrizRoteamento()
    {
        System.out.println("\n\t" + cabecalhoMatriz);
        System.out.println("pai:\t" + paiMatriz);
        System.out.println("d:\t" + dMatriz + "\n");
    }

    /**
     * Monta a matriz de roteamento do grafo após aplicar o Dijsktra.
     * @param grafo - Grafo que o dijkstra foi aplicado.
     */
    public void setMatrizRoteamento(Grafo grafo) 
    {
        cabecalhoMatriz = "";
        paiMatriz = "";
        dMatriz = "";
     
        for(Vertice v : grafo.getVertices())
        {
            if(v.getDistancia() == INFINITO)
            {
                cabecalhoMatriz += v.getRotulo() + "\t";
                dMatriz += "- \t";
                paiMatriz += "- \t";
            }
            else
            {
                cabecalhoMatriz += v.getRotulo() + "\t";
                paiMatriz += v.getPai().getRotulo() + "\t";
                
                // Seta as casas decimais só quando são significantes   
                //dMatriz += v.getDistancia() + "\t";
                if(v.getDistancia() == (long) v.getDistancia())
                    dMatriz += String.format("%d",(long)v.getDistancia()) + "\t";
                else
                    dMatriz += String.format("%s",v.getDistancia()) + "\t";
            }
        }
    }
    
    /**
     * Constroi o caminho de vértices da origem ao destino.
     * Para isso, baseia-se no pai de cada vértice. Ou seja, a partir do destino, pega o pai até chegar na origem.
     * @param destino vértice de destino do caminho.
     * @return List de vértices até o destino.
     */
    public List<Vertice> getCaminho(Vertice destino)
    {
        List caminho = new ArrayList();
        boolean chegou = false;
        Vertice atual = destino.getPai();

        // Enquanto não chegar ao destino
        while(!chegou)
        {
            if(atual.getPai() == null)
                System.out.println("Pá, de ruim no " + atual.getRotulo());
            
            // Se o vértice atual não for igual ao da origem
            if(!atual.equals(this.origem))
            {
                // Add o vértice atual na lista de caminho
                caminho.add(atual);
                
                // O atual será seu pai
                atual = atual.getPai();
            }
            // Quando chegar no destino, add o atual na lista e sai do loop
            else
            {
                caminho.add(atual);
                chegou = true;
            }
        }
        
        // Inverte ordem da lista visto que o caminho é baseado no pai dos vértices
        Collections.reverse(caminho);
        return caminho;
    }
    
    // Construtor vazio.
    public Dijkstra(){}

    public Duration getTempoExecucao()
    {
        return tempoExecucao;
    }

    
}
