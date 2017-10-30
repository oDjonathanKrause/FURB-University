package caminhamento;

import grafos.Grafo;
import grafos.Vertice;
import java.util.Queue;

/**
 * Um grafo é Hamiltoniano quando é possível passar por todos os seus vértices
 * e voltar a origem sem repetir nenhum deles.
 * @author Djonathan. FURB - Grafos 2017.
 */
public class Hamiltoniano 
{
    private Queue s;
    
    /**
     * Aplica o teorema de Ore para verificar se o grafo é hamiltoniano.
     * Se a soma dos graus de cada par de vértices não-adjacentes seja no mínimo n. 
     * N sendo o número de vértices. O grafo também tem que ter ordem maior ou igual a 3 e ser simples.
     * Há exeções para o teorema. 
     * @param grafo - grafo que será analisado.
     * @return true se o grafo for Hamiltoniano.
     */
    public boolean teoremaDeOre(Grafo grafo)
    {
        if(grafo.getOrdem() < 3 || grafo.isSimples())
            return false;
        // NOT OK
        return true;
    }

    /**
     * Aplica o teorema de Dirac para verificar se é hamiltoniano.
     * O teorema de Dirac diz que o grau de todo vértice de G seja no mínimo n/2, onde n é o número de vértices em G.
     * Há exeções para o teorema. 
     * @param grafo - grafo que será analisado.
     * @return true se o grafo for hamiltoniano.
     */
    public boolean teoremaDeDirac(Grafo grafo)
    {
        float aux = grafo.getOrdem() / 2;
        
        for(Vertice v : grafo.getVertices())
            return (v.getGrau() < aux);
        return true;
    }
    
    public boolean RobertAndFlores()
    {
        return true;
    }
    
}
