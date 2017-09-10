package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Djonathan Krause
 */
public class Grafo
{
    private int qtdVertices;
    private int[][] matrizAdjacencia;
    private List<Vertice> vertices = new ArrayList();
    private List<Aresta> arestas = new ArrayList();

    /**
     * Contrutor do Grafo
     * @param qtdV quantidades de vértices que o grafo tem
     */
    public Grafo(int qtdV)
    {
        qtdVertices = qtdV;
        matrizAdjacencia = new int[qtdV][qtdV];
        
        for(int i = 0; i < qtdV; i++)
        {
            Vertice vertice = new Vertice(i, "v" + i++);
            vertices.add(vertice);
        }
    }

    /**
     * Insere aresta entre dois vértices v1 e v2
     * @param v1 vértice 1
     * @param v2 vértice 2
     * @param nomeAresta nome da aresta a ser inserida String
     * @throws IllegalArgumentException 
     */
    public void addAresta(int v1, int v2, String nomeAresta) throws IllegalArgumentException
    {
        // Diminui 1 dos valores informados para tratar como array (começa do 0)
        v1--;
        v2--;
        
        if (v1 < 0 || v2 < 0)
            throw new IllegalArgumentException("Índice menor do que o esperado");
        if (v1 > qtdVertices || v2 > qtdVertices)
            throw new IllegalArgumentException("Excede a quantidade de vértices permitidos");
        
        // Adiciona aresta na matriz de adjacência do grafo
        matrizAdjacencia[v1][v2]++;
        
        Aresta aresta = new Aresta(nomeAresta);
        List<Vertice> verticesAresta = new ArrayList();
    }
    
    /**
     * Override do toString para printar a matriz de adjacência do grafo
     * @return String com a matriz de adjacência
     */
    @Override
    public String toString()
    {
        StringBuilder retorno = new StringBuilder("Matriz de Adjacência:\n");

        // Monta cabeçalho
        retorno.append("\t");
        for (int i = 0; i < qtdVertices; i++)
            retorno.append(" " + (i + 1) + "\t");

        // Pula linha
        retorno.append("\n");

        // Percorre as linhas
        for (int linha = 0; linha < qtdVertices; linha++)
        {
            // Concatena nome da linha
            retorno.append(linha + 1);
            
            // Percorre colunas da linha i
            for (int coluna = 0; coluna < qtdVertices; coluna++)
                retorno.append("\t[" + matrizAdjacencia[linha][coluna] + "]");

            // Próxima linha
            retorno.append("\n");
        }
        
        retorno.append("\n");
        return retorno.toString();
    }
    
     /**
     * Verifica se o grafo é nulo. 
     * Percorre a matriz de adjacência, se encontrar algum valor diferente de zero, não é nulo.
     * @param matrizAdjacencia
     * @return true se for nulo
     */
    private boolean isNulo(int[][] matrizAdjacencia)
    {
        // Se for dirigido, percorre a matriz toda
        if(isDirigido(matrizAdjacencia))
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
                for(int coluna = 0; coluna < matrizAdjacencia[linha].length; coluna++)
                    // Se tiver algum relacionamento, não é nulo
                    if(matrizAdjacencia[linha][coluna] != 0)
                        return false;
        }
        // Se não for dirigido, percorre apenas uma diagonal da matriz de adjacência 
        else
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
                for(int coluna = linha; coluna < matrizAdjacencia[linha].length; coluna++)
                    // Se tiver algum relacionamento, não é nulo
                    if(matrizAdjacencia[linha][coluna] != 0)
                        return false;
        }
        return true;
    }
    
    /**
     * Verifica se um grafo é dirigido.
     * Se as diagonais opostas forem iguais, é dirigido.
     * Percorre e compara apenas uma diagonal da matriz de adjacência, desconsidera a diagonal principal (coluna = linha + 1)
     * Se o valor linhaXcoluna for diferente do colunaXlinha, é dirigido
     * @param matrizAdjacencia
     * @return true se for dirigido
     */
    public boolean isDirigido(int[][] matrizAdjacencia)
    {
        for(int linha = 0; linha < matrizAdjacencia.length; linha++)
            for(int coluna = linha + 1; coluna < matrizAdjacencia[linha].length; coluna++)
                if(matrizAdjacencia[linha][coluna] != matrizAdjacencia[coluna][linha])
                    return true;
        return false;
    }
    
    /**
     * Verifica se o grafo é completo.
     * Caso algum vértice tiver o valor 0, este vértice não tem relacionamento, então não é completo
     * @param matrizAdjacencia
     * @return true se for completo
     */
    public boolean isCompleto(int[][] matrizAdjacencia)
    {
        // Se for dirigido, percorre a matriz inteira
        if(isDirigido(matrizAdjacencia))
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
                for(int coluna = 0; coluna < matrizAdjacencia[linha].length; coluna++)
                    // Se não estiver na diagonal principal
                    if(linha != coluna)
                        // Se não tiver o relacionamento, não é completo
                        if(matrizAdjacencia[linha][coluna] == 0)
                            return false;
        }
        // Se não for dirigido, percorre apenas uma diagonal da matriz de adjacência 
        // Desconsidera a diagonal principal, por isso o coluna = linha + 1 no for
        else
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
                for(int coluna = linha + 1; coluna < matrizAdjacencia[linha].length; coluna++)
                    // Se não tiver o relacionamento, não é completo
                    if(matrizAdjacencia[linha][coluna] == 0)
                        return false;
        }
        return true;
    }

    // Gets e sets
    public int getQtdVertices()
    {
        return qtdVertices;
    }

    public void setQtdVertices(int qtdVertices)
    {
        this.qtdVertices = qtdVertices;
    }

    public int[][] getMatrizAdjacencia()
    {
        return matrizAdjacencia;
    }

    public void setMatrizAdjacencia(int[][] matrizAdjacencia)
    {
        this.matrizAdjacencia = matrizAdjacencia;
    }

    public List<Vertice> getVertices()
    {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices)
    {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas()
    {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas)
    {
        this.arestas = arestas;
    }
    
    
}
