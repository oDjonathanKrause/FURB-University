package caminhamento;

import grafos.Grafo;
import grafos.Vertice;
import java.util.Stack;

/**
 * Um grafo é Hamiltoniano quando é possível passar por todos os seus vértices
 * sem repetir nenhum deles e voltar a origem. 
 * Um ciclo hamiltoniano é o caminho que se faz ao passar por todos os vértices e voltando a origem.
 * Um caminho hamiltoniano tem a mesma caracteristica, porém, não volta a origem.
 *
 * @author Djonathan. FURB - Grafos 2017.
 */
public class CiclosHamiltonianos
{

    private Stack<Vertice> S;
    private final String NAO_EXPLORADO = "NÃO EXPLORADO";
    private final String EXPLORADO = "EXPLORADO";

    public CiclosHamiltonianos()
    {
        S = new Stack();
    }

    /**
     * O algoritmo de Robert e Flores verifica um ciclo hamiltoniano em um
     * grafo. Para isso, a partir de um vértice inicial, determina um caminho -
     * se possível - que leva ao próximo vértice; 
     * Se um vértice que não foi explorado é encontrado, efetua o caminho até ele e repete; 
     * Senão encontrar um vértice viável, efetua o backtracking na tentativa de
     * encontrar um caminho possível por outro vértice
     * A busca é encerrada quando todos os caminhos possíveis forem explorados.
     *
     * Se o vértice inicial e o último a ser adicionado na pilha forem o mesmo,
     * encontrou um ciclo hamiltoniano que passa por todos os vértices.
     *
     * @param grafo grafo que será analisado.
     * @return true se encontrar um ciclo hamiltoniano.
     */
    public boolean RobertsAndFlores(Grafo grafo)
    {
        // Seta o status de todos os vértices para NAO EXPLORADO
        grafo.getVertices().stream().forEach((vertice) -> {
            vertice.setStatus(NAO_EXPLORADO);
        });
        
        // Começa a partir do primeiro vértice (o vértice inicial é indiferente para o algoritmo)
        Vertice verticeInicial = grafo.getVertices().get(0);
        
        // Add o vértice inicial na pilha
        S.add(verticeInicial);

        // Seta o status dele para EXPLORADO
        verticeInicial.setStatus(EXPLORADO);

        // Executa a recursividade a partir do vértice inicial, se na recursividade voltar true, é hamiltoniano.
        return robertsFloresRecursivo(verticeInicial, grafo);
       
    }

    /**
     * Método auxiliar para o algoritmo de Roberts e Flores.
     * Efetua a verificação recursivamente para todos os vértices adjacentes ao atual.
     * @param atual vértice que está sendo explorado.
     * @param grafo grafo que está sendo analisado.
     * @return true se encontrar um ciclo hamiltoniano.
     */
    public boolean robertsFloresRecursivo(Vertice atual, Grafo grafo)
    {
        // Para cada vértice adjancente ao que está sendo analisado
        for (Vertice adjacenteDoAtual : atual.getAdjacentes(grafo.isDirigido()))
        {
            // Se o vértice adjacente não estiver na pilha, ou seja, o status não é EXPLORADO
            // Continua a partir deste vértice
            if (adjacenteDoAtual.getStatus().equals(NAO_EXPLORADO))
            {
                // Add o vértice na pilha
                S.add(adjacenteDoAtual);
                
                // Seta o status como EXPLORADO
                adjacenteDoAtual.setStatus(EXPLORADO);

                // Segue a busca a partir dele
                return robertsFloresRecursivo(adjacenteDoAtual, grafo);
            }
            // Se o vértice analisado for o mesmo que o inicial e todos os vértices já estão na pilha
            // Fechou o ciclo hamiltoniano, retorna true
            else if(adjacenteDoAtual.equals(S.get(0)))
                if(S.size() == grafo.getOrdem())
                    return true;
        }
        
        return false;
    }

    /**
     * Aplica o teorema de Ore para verificar se o grafo é hamiltoniano. 
     * Se a soma dos graus de cada par de vértices não-adjacentes seja no mínimo n. 
     * N sendo o número de vértices. O grafo também tem que ter ordem maior ou
     * igual a 3 e ser simples. Há exeções para o teorema.
     *
     * @param grafo - grafo que será analisado.
     * @return true se o grafo for Hamiltoniano.
     */
    public boolean teoremaDeOre(Grafo grafo)
    {
        // Se a quantidade de vértices for menor que 3 ou se não for simples, não é hamiltoniano 
        if (grafo.getOrdem() < 3 || !grafo.isSimples())
            return false;

        // Percorre todos os vértices do grafo
        for(Vertice v : grafo.getVertices())
            // Percorre todos os vértices não adjacentes ao vértice V
            for(Vertice verticeNaoAdjacente : v.getNaoAdjacente(grafo))
                // Se a soma dos graus de V e cada vértice não adjacente for menor que a ordem do grafo, não é hamiltoniano
                if(v.getGrau() + verticeNaoAdjacente.getGrau() < grafo.getOrdem())
                    return false;
        
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
            if(v.getGrau() < aux)
                return false;
        return true;
    }

}
