package caminhamento;

import grafos.Grafo;
import grafos.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 * Um grafo é Hamiltoniano quando é possível passar por todos os seus vértices
 * sem repetir nenhum deles.
 *
 * @author Djonathan. FURB - Grafos 2017.
 */
public class Hamiltoniano
{

    private List<Vertice> s;
    private final String NAO_EXPLORADO = "NÃO EXPLORADO";
    private final String EXPLORADO = "EXPLORADO";

    public Hamiltoniano()
    {
        s = new ArrayList();
    }

    /**
     * O algoritmo de Robert e Flores verifica um ciclo hamiltoniano em um
     * grafo. Para isso, a partir de um vértice inicial, determina um caminho -
     * se possível - que leva ao próximo vértice; Se um vértice que não foi
     * explorado é encontrado, efetua o caminho até ele e repete; Senão
     * encontrar um vértice viável, efetua o backtracking na tentativa de
     * encontrar um caminho possível por outro vértice; A busca é encerrada
     * quando todos os caminhos possíveis forem explorados.
     *
     * Se o vértice inicial e o último a ser adicionado na pilha forem o mesmo,
     * encontrou um cicli hamiltoniano que passa por todos os vértices.
     *
     * @param grafo grafo que será analisado.
     * @return true se encontrar um cicli hamiltoniano.
     */
    public boolean RobertsAndFlores(Grafo grafo)
    {
        // Seta o status de todos os vértices para NAO EXPLORADO
        grafo.getVertices().stream().forEach((vertice) -> {
            vertice.setStatus(NAO_EXPLORADO);
        });
        
        // Percorre todos os vértices do grafo
        for (Vertice v : grafo.getVertices())
        {
            // Add o vértice atual na fila
            s.add(v);

            // Seta o status dele para EXPLORADO
            v.setStatus(EXPLORADO);
            
            // Executa a recursividade, se na recursividade voltar true, é hamiltoniano.
            return isHamiltoniano(v, grafo);
        }
       
        return false;
    }

    /**
     * Método auxiliar para o algoritmo de Roberts e Flores.
     * Efetua a verificação recursivamente para todos os vértices adjacentes ao atual.
     * @param atual vértice que está sendo explorado.
     * @param grafo grafo que está sendo analisado.
     * @return true se encontrar um ciclo hamiltoniano.
     */
    public boolean isHamiltoniano(Vertice atual, Grafo grafo)
    {
        // Pega os vértices adjancentes ao que está sendo analisado
        for (Vertice adjacenteDoAtual : atual.getAdjacentes(grafo.isDirigido()))
        {
            // Se o vértice adjacente não estiver na lista, ou seja, o status não é EXPLORADO
            // Continua a partir deste vértice
            if (adjacenteDoAtual.getStatus().equals(NAO_EXPLORADO))
            {
                // Add o vértice na fila
                s.add(adjacenteDoAtual);
                
                // Seta o status como EXPLORADO
                adjacenteDoAtual.setStatus(EXPLORADO);

                // Segue a busca a partir dele
                isHamiltoniano(adjacenteDoAtual, grafo);
            }
            else
            {
                // Se todos os vértices já estão em S, quer dizer que fechou um ciclo hamiltoniano.
                if(s.size() == grafo.getOrdem())
                    return true;
                // Senão, seta o status do adjacente como não explorado e segue com a execução
                else
                {
                    s.remove(adjacenteDoAtual);
                    adjacenteDoAtual.setStatus(NAO_EXPLORADO);
                }
            }
        }
        
        return false;
    }

    /**
     * Aplica o teorema de Ore para verificar se o grafo é hamiltoniano. Se a
     * soma dos graus de cada par de vértices não-adjacentes seja no mínimo n. N
     * sendo o número de vértices. O grafo também tem que ter ordem maior ou
     * igual a 3 e ser simples. Há exeções para o teorema.
     *
     * @param grafo - grafo que será analisado.
     * @return true se o grafo for Hamiltoniano.
     */
    public boolean teoremaDeOre(Grafo grafo)
    {
        if (grafo.getOrdem() < 3 || grafo.isSimples())
        {
            return false;
        }
        // NOT OK
        return true;
    }

    /**
     * Aplica o teorema de Dirac para verificar se é hamiltoniano. O teorema de
     * Dirac diz que o grau de todo vértice de G seja no mínimo n/2, onde n é o
     * número de vértices em G. Há exeções para o teorema.
     *
     * @param grafo - grafo que será analisado.
     * @return true se o grafo for hamiltoniano.
     */
    public boolean teoremaDeDirac(Grafo grafo)
    {
        float aux = grafo.getOrdem() / 2;

        for (Vertice v : grafo.getVertices())
        {
            return (v.getGrau() < aux);
        }
        return true;
    }

}
