package grafos;

import caminhamento.ConjuntosDisjuntos;
import caminhamento.Hamiltoniano;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Estrutura de dados Grafo implementada durante a disciplina de Teoria dos Grafos na FURB, 2017.
 * @author Djonathan Krause
 */
public class Grafo
{
    private int[][] matrizAdjacencia;
    private boolean isDirigido;
    private List<Vertice> verticesGrafo = new ArrayList();
    private List<Aresta> arestasGrafo = new ArrayList();

    /**
     * Construtor seta apenas a flag para determinar se o grafo é dirigido ou não.
     * @param isDirigido true se for dirigido.
     */
    public Grafo(boolean isDirigido)
    {
        this.isDirigido = isDirigido;
    }
    
    /**
     * Cria aresta entre dois vértices. Adiciona aresta na lista de arestasGrafo do grafo, e os vértices na lista de vértices.
     * @param rotulo - Rótulo/nome da aresta
     * @param valor - Valor/peso da aresta
     * @param verticeOrigem - Vértice de origem
     * @param verticeDestino - Vértice de destino
     */    
    public void addAresta(String rotulo, float valor, Vertice verticeOrigem, Vertice verticeDestino)
    {
        // Cria nova instância de aresta
        Aresta aresta = new Aresta(rotulo, valor, verticeOrigem, verticeDestino);
        
        // Add aresta instanciada na lista de arestas do vértice de origem e destino
        verticeOrigem.arestas.add(aresta);
        verticeDestino.arestas.add(aresta);
        
        // Incrementa o grau do vértice. Se for dirigido, incrementa apenas do vértices de origem.
        if(isDirigido)
            verticeOrigem.setGrau(verticeOrigem.getGrau() + 1);
        else
        {
            verticeOrigem.setGrau(verticeOrigem.getGrau() + 1);
            verticeDestino.setGrau(verticeDestino.getGrau() + 1);
        }
        
        // Add aresta e vértices nas listas de arestas e vértices do grafo
        arestasGrafo.add(aresta);
        
        if(!verticesGrafo.contains(verticeOrigem))
            verticesGrafo.add(verticeOrigem);
        if(!verticesGrafo.contains(verticeDestino))
            verticesGrafo.add(verticeDestino);
    }
    
    /**
     * Adiciona vértice v no grafo G.
     * Não faz nenhuma ligação, ou seja, até aqui, o vértice ficará desconexo.
     * @param v vértice que será adicionado do grafo.
     */
    public void addVertice(Vertice v)
    {
        verticesGrafo.add(v);
    }

    /**
     * Da override no toString() para printar todas as arestasGrafo e vértices do grafo
     * @return String com o grafo no formato aresta: \n origem v1 | detino v2
     */
    @Override
    public String toString()
    {
        String grafoString = "Grafo: \n";
        
        for(Aresta a : arestasGrafo)
            grafoString += "(" + a.getVerticeOrigem().getRotulo() + ", " + a.getVerticeDestino().getRotulo() + ")\n";
        
        return grafoString;
    }
    
    /**
     * Imprime a lista de adjacência de cada vértice do grafo.
     * @return Lista com vértices adjacentes a v
     */
    public String printListaAdjacencia()
    {
        String adjacentesString = "";
            
        for(Vertice vertice : verticesGrafo)
        {
            // Concatena o nome do vértice atual
            adjacentesString += "\n" + vertice.getRotulo() + ": ";
            
            // Percorre todos os adjacentes do atual e concatena
            for(Vertice vAdj : vertice.getAdjacentes(this.isDirigido))
                adjacentesString += vAdj.getRotulo() + " - ";
        }

        return adjacentesString;
    }
    
    /**
     * Se não houver nenhuma aresta no grafo, ele é nulo.
     * @return true se o grafo for nulo.
     */
    public boolean isNulo()
    {
        return arestasGrafo.isEmpty();
    }
    
    /**
     * Um grafo simples não possuí laços e nem arestas paralelas.
     * @return true se for simples.
     */
    public boolean isSimples()
    {
        for(Aresta a : arestasGrafo)
        {
            // Se o vértice de origem for o mesmo do de destino, é um laço. Não é simples.
            if(a.getVerticeOrigem().equals(a.getVerticeDestino()))
                return false;
            // Se tiver mais de uma aresta onde a origem Vi e o destino Vii, tem aresta paralela. Não é simples.

        }
        return true;
    }
    
    /**
     * Um grafo é Euleriano se é possível passr por todas as arestas e voltar a origem.
     * Caso algum vértice do grafo tenha grau ímpar, o grafo não é euleriano. Do contrário, o grafo é euleriano.
     * @return true se for euleriano.
     */
    public boolean isEuleriano()
    {
        // Percorre todos os vértices do grafo. Caso um deles tenha grau ímpar, retorna false.  
        return verticesGrafo.stream().noneMatch((v) -> (v.getGrau() % 2 != 0));
    }
    
    /**
     * Um grafo é Hamiltoniano quando é possível passar por todos os vértices sem repetir nenhum.
     * Utiliza a classe Hamiltoniano da package de caminhamento em grafos para efetuar a verificação.
     * @return true se for hamiltoniano.
     */
    public boolean isHamiltoniano()
    {
        Hamiltoniano h = new Hamiltoniano();
        return h.RobertsAndFlores(this);
    }
    
    /**
     * Verifica se o grafo é conexo utilizando a estrutura de conjuntos disjuntos.
     * Utiliza o método isConexo da classe ConjuntosDisjuntos para fazer a verificação. 
     * @return true se for conexo.
     */
    public boolean isConexoConjuntosDisjuntos()
    {
        return new ConjuntosDisjuntos(this).isConexo();
    }
    
    /**
     * Verifica a ordem do grafo, ou seja, a quantidade de vértices pertencentes a G.
     * @return int com a ordem do grafo. 
     */
    public int getOrdem()
    {
        return verticesGrafo.size();
    }
    
    /**
     * Verifica o tamanho do grafo, ou seja, a quantidade de arestasGrafo pertencentes a G
     * @return int com o tamanho do grafo.
     */
    public int getTamanho()
    {
        return arestasGrafo.size();
    }
    
    /**
     * Retorna o vértice V do grafo G pelo rótulo.
     * @param rotuloVertice rótudo do vértice que será procurado.
     * @return null se não encontrar o vértice. Objeto do vértice se encontrar.
     */
    public Vertice getVerticePorRotulo(String rotuloVertice)
    {
        for(Vertice vertice : this.verticesGrafo)
            if(vertice.getRotulo().equals(rotuloVertice))
                return vertice;
        return null;
    }
    
    
    /**
     * Gera um grafo de ordem e tamanho informados por parâmetro.
     * O peso das arestas é um número aleatório entre 0 e 1000.
     * Os vértices terão o rótulo vi e as arestas arestai. Onde i é o índice dos loops internos.
     * @param ordem número de vértices.
     * @param tamanho número de arestas.
     */
    public void buildGrafo(int ordem, int tamanho)
    {
        List<Vertice> vertices = new ArrayList();
        int randIndexV1, randIndexV2, randValue;

        for (int i = 0; i <= ordem; i++)
            vertices.add(new Vertice("v" + i));

        for (int i = 0; i <= tamanho; i++)
        {
            Random rand = new Random();
            randIndexV1 = rand.nextInt((ordem - 1) + 1) + 1;
            randIndexV2 = rand.nextInt((ordem - 1) + 1) + 1;
            randValue = rand.nextInt((1000 - 1) + 1) + 1;
            
            this.addAresta("aresta" + i, randValue, vertices.get(randIndexV1), vertices.get(randIndexV2));
        }
        
    }

    // Gets e sets
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
        return verticesGrafo;
    }

    public void setVertices(List<Vertice> vertices)
    {
        this.verticesGrafo = vertices;
    }

    public List<Aresta> getArestas()
    {
        return arestasGrafo;
    }

    public void setArestas(List<Aresta> arestas)
    {
        this.arestasGrafo = arestas;
    }

    public boolean isDirigido()
    {
        return isDirigido;
    }

    public void setIsDirigido(boolean isDirigido)
    {
        this.isDirigido = isDirigido;
    }
    
    

}
