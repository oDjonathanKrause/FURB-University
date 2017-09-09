package questao4;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class ReconheceGrafo
{

    /**
     * Exercício 1 letra a: 
     * Verifica o tipo do grafo (dirigido ou não, simples ou multigrafo, regular, completo, nulo ou bipartido)
     * @param mAdjacencia
     * @return String com o tipo do grafo

     * Multirafo = pelo meno um laço ou aresta paralela
     * Regular = Se todos os vértices tem o mesmo grau
     * Completo = Quando todos os vértices se relacionam (se é completo, é regular) (K)
     * Nulo = Grafo sem arestas (N)
     * Bipartido = Origem e destino da aresta tem que estar em conjuntos diferentes. Ímpares não são bipartidos.
     */
    public String TipoDoGrafo(int[][] mAdjacencia)
    {
        boolean isDigrafo = true, isSimples = true, isRegular = true, isCompleto = true, isNulo = true,
                isBipartido = true;

        for (int i = 0; i < mAdjacencia.length; i++)
        {
            for (int j = 0; j < mAdjacencia.length; j++)
            {
                if (i == j)
                {
                    if (mAdjacencia[i][j] != 0)
                    {// se tiver loop
                        isSimples = false;
                        isNulo = false;
                        isBipartido = false;// grafos bipartidos não tem laço
                    }
                } else
                {
                    if (i > j)
                    {
                        if (mAdjacencia[i][j] == mAdjacencia[j][i])
                        {
                            isDigrafo = false;
                        }
                    }
                    if (mAdjacencia[i][j] == 0)
                    {
                        isCompleto = false;
                    } else
                    {
                        isNulo = false;
                    }
                }
            }
        }
        isSimples = isSimples(mAdjacencia, isDigrafo);
        isRegular = isRegular(mAdjacencia);
        if (!isNulo)
        {
            if (isBipartido)
            {
                if (mAdjacencia.length > 1)
                {
                    isBipartido = isBipartido(mAdjacencia, isDigrafo);
                } else
                {
                    isBipartido = false;
                }
            }
        }

        StringBuilder msg = new StringBuilder();
        msg.append("Grafo ");
        if (!isNulo)
        {
            msg.append(isDigrafo ? "é direcionado, " : "é não-direcionado, ");
        }
        msg.append(isSimples ? "é simples, " : "não é simples, é multigrafo, ");
        msg.append(isRegular ? "é regular, " : "não é regular, ");
        msg.append(isCompleto ? "é completo, " : "não é completo, ");
        msg.append(isNulo ? "é nulo, " : "não é nulo, ");
        msg.append(isBipartido ? "é bipartido." : "não é bipartido.");
        return msg.toString();
    }

    /*
    * Um grafo bipartido é definido por dois conjuntos de vertices adjacentes, tal
    * que cadas vertice adjacente pertença a um conjunto diferente. Um grafo
    * bipartido não pode ter laços.
     */
    public boolean isBipartido(int[][] mAdjacencia, boolean isDigrafo)
    {
        HashSet<Integer> setA = new HashSet<Integer>(mAdjacencia.length, 90f);
        HashSet<Integer> setB = new HashSet<Integer>(mAdjacencia.length, 90f);
        HashSet<Integer> set = null;
        setA.add(0);// primeiro vertice;
        for (int i = 0, aux; i < mAdjacencia.length; i++)
        {
            if (setA.contains(i))
            {
                set = setB;
            } else if (setB.contains(i))
            {
                set = setA;
            }
            if (isDigrafo)
            {
                aux = 0;
            } else
            {
                aux = i + 1;
            }
            for (int j = aux; j < mAdjacencia.length; j++)
            {
                if (i != j)
                {
                    if (mAdjacencia[i][j] != 0)
                    {
                        set.add(j);
                    }
                }
            }
            if (hasAdjacencia(set, mAdjacencia))
            {
                setA.clear();
                setB.clear();
                setA.add(i + 1);
                return false;
            }
        }
        return true;
    }

    /* método usado pelo método isBipartido */
    private boolean hasAdjacencia(HashSet<Integer> set, int[][] mAdjacencia)
    {
        for (Integer v1 : set)
        {
            for (Integer v2 : set)
            {
                if (v1 != v2)
                {
                    if (mAdjacencia[v1][v2] != 0 || mAdjacencia[v2][v1] != 0)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* Exercício 1 letra b: */
    public String arestasDoGrafo(int[][] mAdjacencia)
    {
        StringBuilder msg = new StringBuilder();
        boolean isDigrafo = isDigrafo(mAdjacencia);
        int qtdArestas = 0;
        for (int i = 0; i < mAdjacencia.length; i++)
        {
            for (int j = 0; j < mAdjacencia.length; j++)
            {
                if (isDigrafo)
                {
                    qtdArestas += mAdjacencia[i][j];
                    listaArestas(i, j, mAdjacencia[i][j], msg);
                } else if (i >= j)
                {
                    qtdArestas += mAdjacencia[i][j];
                    listaArestas(i, j, mAdjacencia[i][j], msg);
                }
            }
        }
        return "Quantidade de Arestas: " + qtdArestas + "\nLista de arestas" + msg.toString();
    }

    
    /* método usado pelo método arestaDoGrafo */
    private void listaArestas(int x, int y, int size, StringBuilder msg)
    {
        for (int i = 0; i < size; i++)
        {
            msg.append("(v" + (x + 1) + ",v" + (y + 1) + ") ");
        }
    }

    /**
     * Exercício 1 letra c:
     * Verifica e lista a sequência de graus dos vértices do grafo
     * @param mAdjacencia
     * @return String com o grau de cada vétice e a sequência de graus
     */
    public String grausDoVertice(int[][] mAdjacencia)
    {
        ArrayList<Integer> lista = new ArrayList<Integer>(mAdjacencia.length);
        int soma = 0;
        for (int i = 0; i < mAdjacencia.length; i++)
        {
            for (int j = 0; j < mAdjacencia.length; j++)
            {
                if (i == j && mAdjacencia[i][j] == 1)
                {
                    soma++;
                }
                soma += mAdjacencia[i][j];
            }
            lista.add(soma);
            soma = 0;
        }
        Collections.sort(lista);
        StringBuilder msg = new StringBuilder();
        lista.forEach(i -> msg.append(i + " "));
        return msg.toString();
    }

    public String verticesGrausImpar(int[][] mAdjacencia)
    {
        ArrayList<Integer> lista = new ArrayList<Integer>(mAdjacencia.length);
        int soma = 0;
        for (int i = 0; i < mAdjacencia.length; i++)
        {
            for (int j = 0; j < mAdjacencia.length; j++)
            {
                if (i == j && mAdjacencia[i][j] == 1)
                {
                    soma++;
                }
                soma += mAdjacencia[i][j];
            }
            if (soma % 2 != 0)
            {
                lista.add(soma);
            }
            soma = 0;
        }
        Collections.sort(lista);
        StringBuilder msg = new StringBuilder();
        lista.forEach(i -> msg.append(i + " "));
        return msg.toString();
    }

    /*
    * Um grafo dirigido é formado por um conjunto de arestas , onde cadas aresta
    * possui um vertice de origem e destino. Num grafo não dirigido as arestas não
    * possui origem e destino apenas ligam o vertices.
     */
    public boolean isDigrafo(int[][] mAdjacencia)
    {
        for (int i = 0; i < mAdjacencia.length; i++)
        {
            for (int j = i + 1; j < mAdjacencia.length; j++)
            {
                if (mAdjacencia[i][j] != mAdjacencia[j][i])
                {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    * Um grafo simples é um grafo que não contem laços e nem arestas paralelas. Um
    * multigrafo é um grafo que contrem pelo menus um laço ou um congunto de
    * arestas paralelas.
    */
    public boolean isSimples(int[][] mAdjacencia, boolean isDigrafo)
    {
        for (int i = 0, aux; i < mAdjacencia.length; i++)
        {
            if (isDigrafo)
            {
                aux = 0;
            } else
            {
                aux = i;
            }
            for (int j = aux; j < mAdjacencia.length; j++)
            {
                if (i == j)
                {// se tiver loop
                    if (mAdjacencia[i][j] != 0)
                    {
                        return false;
                    }
                } else if (!isDigrafo)
                {
                    if (mAdjacencia[i][j] > 1)
                    {
                        return false;
                    }
                } else if (isDigrafo)
                {
                    if (mAdjacencia[i][j] != 0 && mAdjacencia[j][i] != 0)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*
     * um grafo regular é um grafo onde todos os vertices possuem o mesmo grau.
     */
    public boolean isRegular(int[][] mAdjacencia)
    {
        int soma = 0, ref = 0;
        for (int i = 0; i < mAdjacencia.length; i++)
        {
            ref = soma;
            soma = 0;
            for (int j = 0; j < mAdjacencia.length; j++)
            {
                if (i == j)
                {
                    if (mAdjacencia[i][j] == 1)
                    {
                        soma++;
                    }
                }
                soma += mAdjacencia[i][j];
            }
            if (i > 0 && ref != soma)
            {
                return false;
            }
        }
        return true;
    }
}
