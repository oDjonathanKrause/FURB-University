package busca;

import grafos.Grafo;
import grafos.Vertice;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Djonathan
 */
public class BFS
{
    private static final Vertice NIL = new Vertice("nil");
    private static final int INFINITO = Integer.MAX_VALUE;
    private Queue<Vertice> q = new LinkedList();
    private String paiMatriz = "", dMatriz = "", cabecalhoMatriz = "";
    
    /**
     * 
     * @param g Grafo que será usado para busca.
     * @param s Vértice inicial.
     */
    public void execBSF(Grafo g, Vertice s)
    {
        // Inicializa os vértices
        for(Vertice v : g.getVertices())
        {
           v.setStatus("BRANCO");
           v.setPai(NIL);
           v.setDistancia(INFINITO);
        }
        
        // Inicializa o vértice de origem
        s.setDistancia(0);
        s.setStatus("CINZA");
        q.add(s);
        
        // Enquando a fila não estiver vazia
        while(!q.isEmpty())
        {
            // Pega o primero vértice da lista
            Vertice u = q.poll();
            
            // Pra cada vértice adjacente a u
            for(Vertice v : u.getAdjacentes(g.isDirigido()))
            {
                // Se o status for BRANCO
                if(v.getStatus().equals("BRANCO"))
                {
                    // Add o vértice na fila e atualiza os valores
                    q.add(v);
                    v.setStatus("CINZA");
                    v.setPai(u);
                    v.setDistancia(u.getDistancia() + 1);
                }  
            }
            
            // Quando não houver mais adjacentes a u, seta o status para PRETO
            u.setStatus("PRETO");
        }
        
        setMatrizRoteamento(g);
    }
    
     /**
     * Monta a matriz de roteamento do grafo após aplicar a busca em largura.
     * @param grafo - Grafo que a BFS foi aplicada.
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
    
    public void printMatrizRoteamento()
    {
        System.out.println("\n\t" + cabecalhoMatriz);
        System.out.println("pai:\t" + paiMatriz);
        System.out.println("d:\t" + dMatriz + "\n");
    }
}
