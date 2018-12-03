package bots;

import caminhamento.Dijkstra;
import grafos.Grafo;
import grafos.Vertice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Djonathan
 */
public class Bot extends Thread
{
    private Vertice atual, destino, origem;
    private String nomeBot;
    private Grafo grafo;
    private Dijkstra dijkstra;
    private List<Vertice> caminho = new ArrayList();

    /**
     * Construtor do bot. 
     * @param nomeBot - nome do bot.
     * @param grafo - Grafo em que o bot está.
     * @param atual - Vértice atual do bot.
     */
    public Bot(String nomeBot, Grafo grafo, Vertice atual)
    {
        this.nomeBot = nomeBot;
        this.grafo = grafo;
        this.atual = atual;
    }
    
    /**
     * Move o robô do vértice atual até o destino informado por parâmetro.
     * Executa o dijsktra para calcular o melhor caminho e percorre os vértices conforme o calculado.
     * @param destino vértice destino.
     */
    public void goTo(Vertice destino)
    {
        // Executa o dijsktra
        dijkstra = new Dijkstra(grafo, atual, destino);

        dijkstra.printMatrizRoteamento();
        
        // Pega o caminho que o dijkstra calculou como o menor
        caminho = dijkstra.getCaminho(destino);
        
        // Declara o vértice auxiliar: anterior
        Vertice anterior = new Vertice();
        
        // Executa e printa o passo a passo do bot
        for(Vertice v : caminho)
        {
            // Se o vértice de destino não tiver mais nenhum bot nesse momento, da o passo
            if(v.getBotCount() == 0)
            {
                // Incrementa o botCount do vértice para indicar que tem um bot nele
                v.setBotCount(v.getBotCount() + 1);
                
                // Decrementa o botCount do vértice anterior para libera-lo
                anterior.setBotCount(v.getBotCount() - 1);
                
                // Move o bot
                this.atual = v;
                
                // Print
                System.out.println("Bot está em\t" + this.atual.getRotulo());    
                
                // Seta este como anterior
                anterior = v;
            }
        }
        System.out.println("Bot chegou em\t" + destino.getRotulo());    
    }
    
    public void run()
    {
        System.out.println("thread is running...");  
    }
}
