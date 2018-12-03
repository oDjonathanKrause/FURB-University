package grafos;

import java.util.List;

/**
 *
 * @author Djonathan
 */
public class Aresta
{

    private String rotulo;
    private float valor;
    private Vertice verticeOrigem, verticeDestino;

    /**
     * Construtor da aresta.
     * @param rotulo identificação da aresta.
     * @param valor caso o grafo seja valorado.
     * @param verticeOrigem vértice de origem da aresta.
     * @param verticeDestino  vértice de destino da aresta.
     */
    public Aresta(String rotulo, float valor, Vertice verticeOrigem, Vertice verticeDestino)
    {
        this.rotulo = rotulo;
        this.valor = valor;
        this.verticeDestino = verticeDestino;
        this.verticeOrigem = verticeOrigem;
    }
    
    // Construtor vazio 
    public Aresta() {}
    
    /**
     * Verifica se a aresta em questão é uma ponte.
     * Uma aresta é uma ponte quando ao remove-la, o grafo fica disconexo.
     * @param grafo.
     * @return true se a aresta for uma ponte.
     */
    public boolean isPonte(Grafo grafo)
    {
        // Remove a aresta do grafo.
        grafo.getArestas().remove(this);
        
        // Se o grafo ficar disconexo, retorna true.
        return !grafo.isConexoConjuntosDisjuntos();
    }

    
    // Gets e sets
    public float getValor()
    {
        return valor;
    }

    public void setValor(float valor)
    {
        this.valor = valor;
    }
    

    public String getRotulo()
    {
        return rotulo;
    }

    public void setRotulo(String rotulo)
    {
        this.rotulo = rotulo;
    }

    public Vertice getVerticeOrigem()
    {
        return verticeOrigem;
    }

    public void setVerticeOrigem(Vertice verticeOrigem)
    {
        this.verticeOrigem = verticeOrigem;
    }

    public Vertice getVerticeDestino()
    {
        return verticeDestino;
    }

    public void setVerticeDestino(Vertice verticeDestino)
    {
        this.verticeDestino = verticeDestino;
    }

}
