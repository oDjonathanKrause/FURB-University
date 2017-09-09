/*
 * (1,0) Implemente um programa que aponte a existência ou não do isomorfismo entre 2 grafos. 
 */
package questao5;

import grafos.trabalho1.Grafo;
import grafos.trabalho1.Vertice;
import java.util.Arrays;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause
 */
public class isIsomorfico
{

    public static String isomorfirmo(Grafo g1, Grafo g2)
    {
        // Se a quantidade de vértices dos grafos for diferente, não são isomórficos
        if (g1.getQtdVertices() != g2.getQtdVertices())
            return "Grafo G1 e G2 com diferentes quantidades de vertices, não são isomorfos";

        // Verifica o grau dos grafos
        int[] grausG1, grausG2;
        grausG1 = new int[g1.getQtdVertices()];
        grausG2 = new int[g2.getQtdVertices()];

        for (int i = 0; i < g1.getQtdVertices(); i++)
        {
            int soma1 = 0;
            int soma2 = soma1;
            for (int j = 0; j < g1.getQtdVertices(); j++)
            {
                if (i == j)
                {
                    if (g1.getMatrizAdjacencia()[i][j] == 1)
                        soma1++;

                    if (g2.getMatrizAdjacencia()[i][j] == 1)
                        soma2++;
                }
                soma1 += g1.getMatrizAdjacencia()[i][j];
                soma2 += g2.getMatrizAdjacencia()[i][j];
            }
            grausG1[i] = soma1;
            grausG2[i] = soma2;
        }
        
        Arrays.sort(grausG1);
        Arrays.sort(grausG2);
        
        // Se a sequência de graus for diferente entre os 2 grafos, não são isomórficos
        if (!Arrays.equals(grausG1, grausG2))
            return "Grafo G1 e G2 com diferentes graus de vertices, não são isomorfos";

        // Cria lista de adjacência dos grafos
        Vertice[] lista1 = g1.getListaAdjacencia();
        Vertice[] lista2 = g2.getListaAdjacencia();
        Vertice[] lista3 = new Vertice[g1.getQtdVertices()];

        if(g1.getListaAdjacencia() != g2.getListaAdjacencia())
            return "Não são isomórticos";
        
        return "Grafos G1 e G2 são isomorfos";
        
    }

}
