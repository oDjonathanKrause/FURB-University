/*
 * Implementado na disciplina de grafos na FURB em 2017/2.
 */
package grafos;

import bots.Bot;
import caminhamento.CiclosHamiltonianos;
import caminhamento.ConjuntosDisjuntos;
import caminhamento.Dijkstra;

/**
 *
 * @author Djonathan Krause
 */
public class MainApp
{

    private static final String LINHA = "--------------------------------------------------------------------------";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //grafo1();
        //grafo2();
        //grafo3();
        //grafo4();
        //grafo5();
        //grafoBuilder1();
        //grafoConjuntosDisjuntos1();
        //grafoConjuntosDisjuntos2();
        //grafoConjuntosDisjuntos3();
        //bot1();
        //bot2();
        //botThread1();
        //grafoHamiltoniano1();
        //grafoHamiltoniano2();
        grafoHamiltoniano3();

    }

    static void grafoBuilder1()
    {
        Grafo grafoBuilder1 = new Grafo(false);
        grafoBuilder1.buildGrafo(100, 100);
    }

    static void grafo1()
    {
        System.out.println(LINHA);

        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");

        Grafo grafo1 = new Grafo(false);
        grafo1.addAresta("aresta1", 2, v1, v2);
        grafo1.addAresta("aresta2", 5, v1, v3);
        grafo1.addAresta("aresta3", 7, v3, v4);
        grafo1.addAresta("aresta4", 4, v2, v4);
        grafo1.addAresta("aresta5", 3, v4, v5);

        System.out.println("Grafo 1");
        System.out.println(grafo1.toString());

        System.out.println("Lista de Adjacência:" + grafo1.printListaAdjacencia());

        Dijkstra dijkstra = new Dijkstra(grafo1, v1, v4);
        System.out.println("\nTempo de execução do Dijkstra de v1 -> v4: " + dijkstra.getTempoExecucao().getSeconds() + " segundos." + "\nMatriz Roteamento do Dijkstra");
        dijkstra.printMatrizRoteamento();

        dijkstra = new Dijkstra(grafo1, v1, null);
        System.out.print("\nMatriz Roteamento Dijkstra a partir de v1:");
        System.out.println("\nTempo de execução do Dijkstra a partir de v1: " + dijkstra.getTempoExecucao().getSeconds() + " segundos." + "\nMatriz Roteamento do Dijkstra:");
        dijkstra.printMatrizRoteamento();

        System.out.println(LINHA);
    }

    static void grafo2()
    {
        Grafo grafo2 = new Grafo(false);
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");
        Vertice v6 = new Vertice("v6");
        Vertice v7 = new Vertice("v7");

        grafo2.addAresta("aresta1", 35, v1, v2);
        grafo2.addAresta("aresta2", 12, v1, v3);
        grafo2.addAresta("aresta3", 97, v1, v6);
        grafo2.addAresta("aresta4", 44, v1, v4);
        grafo2.addAresta("aresta5", 21, v2, v4);
        grafo2.addAresta("aresta6", 37, v3, v6);
        grafo2.addAresta("aresta7", 25, v4, v5);
        grafo2.addAresta("aresta8", 58, v4, v6);
        grafo2.addAresta("aresta9", 81, v4, v7);
        grafo2.addAresta("aresta10", 15, v6, v7);

        System.out.println("Grafo 2");
        System.out.println(grafo2.toString());

        System.out.println("Lista de Adjacência:" + grafo2.printListaAdjacencia());

        Dijkstra dijkstra = new Dijkstra(grafo2, v1, null);
        System.out.println("\nTempo de execução do Dijkstra: " + dijkstra.getTempoExecucao().getSeconds() + " segundos." + "\nMatriz Roteamento Dijkstra a partir de v1:");
        dijkstra.printMatrizRoteamento();

        System.out.println(LINHA);

        dijkstra.setMatrizRoteamento(grafo2);
    }

    static void grafo3()
    {
        Grafo grafo3 = new Grafo(false);
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");

        grafo3.addAresta("aresta1", 10, v1, v2);
        grafo3.addAresta("aresta2", 50, v1, v3);
        grafo3.addAresta("aresta3", 65, v1, v4);
        grafo3.addAresta("aresta4", 4, v2, v5);
        grafo3.addAresta("aresta5", 30, v2, v3);
        grafo3.addAresta("aresta6", 20, v3, v4);
        grafo3.addAresta("aresta7", 44, v3, v5);
        grafo3.addAresta("aresta8", 23, v4, v5);
        grafo3.addAresta("aresta9", 70, v4, v2);
        grafo3.addAresta("aresta10", 6, v5, v1);

        System.out.println("Grafo 3");
        System.out.println(grafo3.toString());

        System.out.println("Lista de Adjacência:" + grafo3.printListaAdjacencia());

        Dijkstra dijkstra = new Dijkstra(grafo3, v1, null);
        System.out.println("\nTempo de execução do Dijkstra: " + dijkstra.getTempoExecucao().getSeconds() + " segundos."
                + "\nMatriz Roteamento Dijkstra a partir de v1:");
        dijkstra.printMatrizRoteamento();

        System.out.println(LINHA);
    }

    static void grafo4()
    {
        Grafo grafo4 = new Grafo(true);
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");

        grafo4.addAresta("a1", 12, v1, v2);
        System.out.println(grafo4.printListaAdjacencia());
    }
    
    static void grafo5()
    {
        // Imagem do grafo https://imgur.com/a/pXKNh
        Grafo grafo5 = new Grafo(false);
        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");
        Vertice f = new Vertice("F");
        Vertice g = new Vertice("G");
        Vertice h = new Vertice("H");
        Vertice i = new Vertice("I");
        Vertice j = new Vertice("J");

        grafo5.addAresta("a1", 2, a, i);
        grafo5.addAresta("a1", 6, a, b);
        grafo5.addAresta("a1", 1, a, h);
        grafo5.addAresta("a1", 9, a, g);
        grafo5.addAresta("a1", 2, b, i);
        grafo5.addAresta("a1", 9, b, j);
        grafo5.addAresta("a1", 8, b, c);
        grafo5.addAresta("a1", 7, b, h);
        grafo5.addAresta("a1", 9, c, e);
        grafo5.addAresta("a1", 1, c, h);
        grafo5.addAresta("a1", 2, c, g);
        grafo5.addAresta("a1", 7, d, f);
        grafo5.addAresta("a1", 9, d, e);
        grafo5.addAresta("a1", 2, d, j);
        grafo5.addAresta("a1", 1, e, f);
        grafo5.addAresta("a1", 3, f, j);
        grafo5.addAresta("a1", 8, f, i);
        grafo5.addAresta("a1", 8, g, h);
        grafo5.addAresta("a1", 9, i, j);
        
        Dijkstra dijkstra;

        // Aplica dijsktra pros vértices de grau ímpar
        for(Vertice v : grafo5.getVertices())
        { 
            if(v.getGrau() % 2 != 0)
            {
                dijkstra = new Dijkstra(grafo5, v, null);
                System.out.print("Dijkstra a partir de " + v.getRotulo());
                dijkstra.printMatrizRoteamento();
            }
        }
        
    }

    static void grafoConjuntosDisjuntos1()
    {
        // Imagem do grafo https://awwapp.com/b/uijsgwsyi/
        Grafo g = new Grafo(false);

        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");
        Vertice v6 = new Vertice("v6");

        g.addAresta("a1", 0, v1, v2);
        g.addAresta("a2", 0, v1, v3);
        g.addAresta("a3", 0, v2, v5);
        g.addAresta("a4", 0, v3, v5);
        g.addAresta("a5", 0, v3, v4);
        g.addAresta("a6", 0, v4, v6);

        ConjuntosDisjuntos conjuntos = new ConjuntosDisjuntos(g);

        System.out.println("\nConjuntos: \n" + conjuntos.toString() + "\nisConexo: " + g.isConexoConjuntosDisjuntos());
    }

    static void grafoConjuntosDisjuntos2()
    {
        // Imagem do grafo https://awwapp.com/b/uungdfhsl/
        Grafo g = new Grafo(false);

        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");
        Vertice v6 = new Vertice("v6");
        Vertice v7 = new Vertice("v7");

        g.addAresta("a1", 0, v1, v2);
        g.addAresta("a2", 0, v1, v3);
        g.addAresta("a3", 0, v2, v5);
        g.addAresta("a4", 0, v3, v5);
        g.addAresta("a5", 0, v3, v4);
        g.addAresta("a6", 0, v4, v6);

        g.addVertice(v7);

        ConjuntosDisjuntos conjuntos = new ConjuntosDisjuntos(g);

        System.out.println("\nConjuntos: \n" + conjuntos.toString() + "\nisConexo: " + g.isConexoConjuntosDisjuntos());
    }
    
    static void grafoConjuntosDisjuntos3()
    {
        Grafo grafo = new Grafo(true);

        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");
        Vertice f = new Vertice("F");
        Vertice g = new Vertice("G");

        grafo.addAresta("a1", 0, a, c);
        grafo.addAresta("a2", 0, a, d);
        grafo.addAresta("a3", 0, c, d);
        grafo.addAresta("a4", 0, d, b);
        grafo.addAresta("a5", 0, e, f);
        grafo.addAresta("a6", 0, f, g);
        grafo.addAresta("a6", 0, g, e);

        ConjuntosDisjuntos conjuntos = new ConjuntosDisjuntos(grafo);

        System.out.println("\nConjuntos: \n" + conjuntos.toString() + "\nisConexo: " + grafo.isConexoConjuntosDisjuntos());
    }
    
    static void bot1()
    {
        Grafo g = new Grafo(false);
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");

        g.addAresta("a1", 2, v1, v2);
        g.addAresta("a2", 3, v2, v3);
        g.addAresta("a3", 3, v3, v4);

        Bot bot = new Bot("bot", g, v1);
        bot.goTo(v4);
    }
    
    static void bot2()
    {
        Grafo g = new Grafo(true);
        g.buildGrafo(10, 100);
        
        Bot bot = new Bot("bot1", g, g.getVerticePorRotulo("v1"));
        bot.goTo(g.getVerticePorRotulo("v10"));
    }
    
    static void botThread1()
    {
        Grafo g = new Grafo(true);
        g.buildGrafo(10, 100);
        
        Bot bot1 = new Bot("bot1", g, g.getVerticePorRotulo("v1"));
        Bot bot2 = new Bot("bot2", g, g.getVerticePorRotulo("v1"));
        
        bot1.start();
        bot2.start();
    }
    
    static void grafoHamiltoniano1()
    {
        Grafo g = new Grafo(true);
        
        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");
        
        g.addAresta("a1", 0, a, b);
        g.addAresta("a2", 0, a, d);
        g.addAresta("a3", 0, b, e);
        g.addAresta("a4", 0, b, d);
        g.addAresta("a5", 0, c, e);
        g.addAresta("a6", 0, c, b);
        g.addAresta("a7", 0, d, c);
        g.addAresta("a8", 0, e, a);
        g.addAresta("a9", 0, e, c);
        
        System.out.println("Roberts e Flores: " + g.isHamiltoniano());
        System.out.println("Dirac: " + g.teoremaDeDirac() + ". Ore: " + g.teoremaDeOre());
    }
    
    static void grafoHamiltoniano2()
    {
        /**
         * A---B    
         * |   |   
         * D---C
         */
        Grafo g = new Grafo(false);
        
        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        
        g.addAresta("a1", 0, a, b);
        g.addAresta("a2", 0, a, d);
        g.addAresta("a3", 0, b, c);
        g.addAresta("a4", 0, d, c);
        
        System.out.println("Roberts e Flores: " + g.isHamiltoniano());
        System.out.println("Dirac: " + g.teoremaDeDirac() + ". Ore: " + g.teoremaDeOre());
    }
    
    static void grafoHamiltoniano3()
    {
        // Imagem do grafo: https://imgur.com/KlryeFt
        Grafo g = new Grafo(false);
        
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");
        Vertice v6 = new Vertice("v6");
        Vertice v7 = new Vertice("v7");
        Vertice v8 = new Vertice("v8");
        Vertice v9 = new Vertice("v9");
        
        g.addAresta("a", 0, v1, v2);
        g.addAresta("a", 0, v2, v3);
        g.addAresta("a", 0, v3, v4);
        g.addAresta("a", 0, v4, v5);
        g.addAresta("a", 0, v5, v6);
        g.addAresta("a", 0, v6, v7);
        g.addAresta("a", 0, v7, v8);
        g.addAresta("a", 0, v8, v9);
        g.addAresta("a", 0, v9, v1);
        g.addAresta("a", 0, v1, v7);
        g.addAresta("a", 0, v8, v6);
        g.addAresta("a", 0, v6, v4);
        g.addAresta("a", 0, v5, v3);
        g.addAresta("a", 0, v5, v2);
        g.addAresta("a", 0, v9, v7);
        g.addAresta("a", 0, v9, v5);
        g.addAresta("a", 0, v7, v2);
        g.addAresta("a", 0, v7, v5);
        g.addAresta("a", 0, v7, v4);
        
        System.out.println("Roberts e Flores: " + g.isHamiltoniano());
        System.out.println("Dirac: " + g.teoremaDeDirac() + ". Ore: " + g.teoremaDeOre());
    }
}
