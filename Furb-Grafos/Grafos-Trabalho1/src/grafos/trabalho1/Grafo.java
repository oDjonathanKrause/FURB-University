package grafos.trabalho1;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause
 */
public class Grafo
{

    private final int qtdVertices;
    private int[][] matrizAdjacencia;

    public Grafo(int qtdV)
    {
        qtdVertices = qtdV;
        matrizAdjacencia = new int[qtdV][qtdV];
    }

    public void insertAresta(int A, int B) throws IllegalArgumentException
    {
        A--;
        B--;

        if (A < 0 || B < 0 || A > qtdVertices || B > qtdVertices)
        {
            throw new IllegalArgumentException("um dos vertices sao inválidos");
        }

        matrizAdjacencia[A][B] = matrizAdjacencia[A][B] + 1;
    }

    /*
	 * esse algoritmo funciona perfeitamente para grafo não dirigidos
     */
    public Vertice[] getListaAdjacencia()
    {
        Vertice[] lista = new Vertice[qtdVertices];
        for (int i = 0; i < qtdVertices; i++)
        {
            lista[i] = new Vertice(String.valueOf(i + 1));
        }

        for (int i = 0; i < qtdVertices; i++)
        {
            for (int j = 0; j < qtdVertices; j++)
            {
                if (matrizAdjacencia[i][j] != 0)
                {
                    for (int k = 0; k < matrizAdjacencia[i][j]; k++)
                    {
                        lista[i].addAdjacencia(lista[j]);
                    }
                }
            }
        }

        return lista;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("Matriz de Adjacencia:\n");

        result.append("V  ");
        for (int i = 0; i < qtdVertices; i++)
        {
            result.append((i + 1) + " ");
        }
        result.append("\n");

        for (int i = 0; i < qtdVertices; i++)
        {
            result.append(String.valueOf(i + 1));
            for (int j = 0; j < qtdVertices; j++)
            {
                result.append(" [" + matrizAdjacencia[i][j] + "]");
            }
            result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }

    public int getQtdVertices()
    {
        return qtdVertices;
    }

    public int[][] getMatrizAdjacencia()
    {
        return matrizAdjacencia;
    }
}
