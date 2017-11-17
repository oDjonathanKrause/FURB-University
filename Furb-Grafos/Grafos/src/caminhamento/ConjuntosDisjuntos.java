package caminhamento;

import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 * Aplica a teoria dos conjuntos disjuntos para verificar se um grafo é conexo.
 * O construtor da classe já irá executar o algoritmo.
 * @author Djonathan
 */
public final class ConjuntosDisjuntos
{
    private final Grafo grafo;
    private final List<List<Vertice>> conjuntos = new ArrayList();
    private List representanteU, representanteV;
    
    /**
     * Construtor da classe. Aplica o algoritmo.
     */
    public ConjuntosDisjuntos(Grafo grafo)
    {
        this.grafo = grafo;
        
        // Cria um conjunto para cada vértice do grafo.
        for(Vertice v : grafo.getVertices())
            makeSet(v);
        
        // Conecta componentes conexos.
        connectedComponents();
    }
    
    /**
     * Conecta componentes conexos. 
     * Cria um conjunto para cada vértice do grafo.
     * Une os conjuntos que estão separados.
     */
    public void connectedComponents()
    {
        // Une os vértices de cada aresta do grafo. As condições para a união estão no método union.
        for(Aresta a : grafo.getArestas())
             union(a.getVerticeOrigem(), a.getVerticeDestino());
        
        for(List<Vertice> conjunto : conjuntos)
            if(conjunto.isEmpty())
                conjuntos.remove(conjunto);
    }
    
    /**
     * Cria um conjunto o vértice v.
     * @param v vértice.
     */
    private void makeSet(Vertice v)
    {
        List itensConjunto = new ArrayList();
        itensConjunto.add(v);
        
        conjuntos.add(itensConjunto);
    }
       
    /**
     * Une os conjuntos que contém o vértice u e v.
     * Para isso, remove o vértice v do conjunto atual e adiciona no conjunto do vértice u.
     * @param u - Vértice u. O conjunto deste será incrementada com u.
     * @param v - Vértice v. O conjunto deste terá v removido.
     */
    private void union(Vertice u, Vertice v)
    {
        // Procura os representantes dos vértices
        representanteU = findSet(u);
        representanteV = findSet(v);

        // Se a representante de u for diferente da de v
        // add v na de u e remove a representante original de v.
        if(!representanteU.equals(representanteV))
        {
            //System.out.println("Colocou " + v.getRotulo() + " na representante " + representanteU);
            representanteU.add(v);

            //System.out.println("Removeu " + v.getRotulo() + " da representante " + representanteV);
            representanteV.remove(v);

            // Se o conjunto original de v ficou fazio, remove ele da lista de conjuntos
            if(representanteV.isEmpty())
                conjuntos.remove(representanteV);
        }
    }
    
    /**
     * Procura o representante do vértice v.
     * @param v vértice.
     * @return 
     */
    private List findSet(Vertice v)
    {
        for(List verticesConjunto : conjuntos)
            if(verticesConjunto.contains(v))
                return verticesConjunto;
        return null;
    }
    
    /**
     * Caso após a execução do algoritmo, se todos os vértices ficarem em um só conjunto, o grafo é conexo.
     * @return true se for conexo
     */
    public boolean isConexo()
    {
        return conjuntos.size() == 1;
    }
    
    /**
     * Retorna os conjuntos disjuntos calculados a partir da construção da classe.
     * @return Uma lista de lista de vértices.
     */
    public List<List<Vertice>> getConjuntosDisjuntos()
    {
        return conjuntos;
    }

    /**
     * Printa conjuntos contruídos.
     * @return String {conjunto}, {conjunto}, ...
     */
    @Override
    public String toString()
    {
        StringBuilder strConjuntos = new StringBuilder();
        
        for(List<Vertice> conjuntoVertices : conjuntos)
        {
            strConjuntos.append("{");
            
            for(Vertice v : conjuntoVertices)
                strConjuntos.append(v.getRotulo()).append(",");
            strConjuntos.append("}");
        }
        
        // Corrige string para imprimir
        for(int i = 0; i < strConjuntos.length(); i++)
        {
            if(strConjuntos.charAt(i) == '{' && strConjuntos.charAt(i + 1) == '}')
                //strConjuntos.delete(i, i + 2); // Substitui por espaço em branco pra não ferrar com a referência i
                strConjuntos.replace(i, i + 2, " ");
            else if((strConjuntos.charAt(i) == ',' && strConjuntos.charAt(i + 1) == '}') || (strConjuntos.charAt(i) == ' ' && strConjuntos.charAt(i + 1) == '}'))
                strConjuntos.replace(i, i + 1, " ");
        }
        
        // Remove espaços em branco
        int j = 0;
        for(int i = 0; i < strConjuntos.length(); i++) 
        {
          if (!Character.isWhitespace(strConjuntos.charAt(i))) 
             strConjuntos.setCharAt(j++, strConjuntos.charAt(i));
        }
        strConjuntos.delete(j, strConjuntos.length());
        
        return strConjuntos.toString().trim();
    }
}

