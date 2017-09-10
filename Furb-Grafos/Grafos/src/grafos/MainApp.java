/*
 * Implementado na disciplina de grafos na FURB em 2017/2.
 */
package grafos;

/**
 * 
 * @author Djonathan Krause
 */
public class MainApp
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Grafo grafo1 = new Grafo(3);

        grafo1.addAresta(1, 2, "Ola");
        System.out.println(grafo1.toString());
        System.out.println("Completo? " + grafo1.isCompleto(grafo1.getMatrizAdjacencia()));
   
        Grafo grafoCompleto = new Grafo(2);
        grafoCompleto.addAresta(1, 2, "Aresta1");
        grafoCompleto.addAresta(2, 1, "Aresta2");
        System.out.println(grafoCompleto.toString());
        System.out.println("Completo? " + grafoCompleto.isCompleto(grafoCompleto.getMatrizAdjacencia()));
 /*
        Grafo grafo2 = new Grafo(5);
        grafo2.addAresta(1, 2);
        grafo2.addAresta(1, 3);
        grafo2.addAresta(1, 4);
        grafo2.addAresta(1, 5);
        System.out.println(grafo2.toString());
*/
    }
    
}
