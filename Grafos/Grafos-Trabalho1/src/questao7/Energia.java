/*
 *(3,0) Implemente o problema “ENERGIA - Transmissão de Energia” existente no SPOJ.
 * http://br.spoj.com/problems/ENERGIA/ 
 */
package questao7;

import grafos.trabalho1.Vertice;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause
 */
public class Energia
{

    private Vertice[] listaAdjacencia;
    private int estacoes, linhas, casos;

    public void entrada(Scanner sc)
    {
        System.out.println("Somente são aceitos:");
        System.out.println("Estações: maior ou igual a 3 e menor ou igual a 100.");
        System.out.println("Linhas de transmissão: maior ou igual a Estações -1 e menor ou igual a Estações × (Estações − 1)/2).\n");

        estacoes = -1;
        linhas = estacoes;
        casos = linhas;

        while (estacoes != 0 && linhas != 0)
        {
            System.out.println("Dgt separado por espaço a quantidade de estações linhas de transmissão:");
            try
            {
                String[] values = sc.nextLine().split(" ");
                if (values.length != 2)
                    throw new IllegalArgumentException("não foram digitados 2 valores!");

                estacoes = Integer.parseInt(values[0]);
                linhas = Integer.parseInt(values[1]);

                if (estacoes != 0 || linhas != 0)
                {
                    if (estacoes < 3 || estacoes > 100)
                        throw new IllegalArgumentException("Valor invalido " + estacoes + "\nEstações: maior ou igual a 3 e menor ou igual a 100.");

                    if (linhas < (estacoes - 1) || linhas > (estacoes * ((estacoes - 1) / 2)))
                        throw new IllegalArgumentException("Valor invalido " + linhas + "\nLinhas de transmissão: maior ou igual a Estações -1 e menor ou igual a Estações × (Estações - 1)/2).");

                    listaAdjacencia = new Vertice[estacoes];
                    for (int i = 0; i < listaAdjacencia.length; i++)
                        listaAdjacencia[i] = new Vertice(String.valueOf(i + 1));

                    insertRelacoes(sc);
                    casos++;
                }
            } catch (IllegalArgumentException e)
            {
                System.err.println(e.getMessage());
                estacoes = -1;
                linhas = -1;
            }
        }
    }

    private void insertRelacoes(Scanner sc)
    {
        int x = -1;
        int y = x;

        while (x != y && y != 0)
        {
            System.out.println("Dgt separado por espaço as estacoes que se relacionam:");
            try
            {
                String[] values = sc.nextLine().split(" ");
                if (values.length != 2)
                    throw new IllegalArgumentException("não foram digitados 2 valores!");

                x = Integer.parseInt(values[0]);
                y = Integer.parseInt(values[1]);

                if (x != y || y != 0)
                {
                    if ((x < 0 || x > estacoes))
                        throw new IllegalArgumentException("Valor invalido " + x + "\nEstações: maior que 1 e menor ou igual a " + estacoes + ".");

                    if ((y < 0 || y > estacoes) || x == 0 && y != 0)
                        throw new IllegalArgumentException("Valor invalido " + y + "\nEstações: maior que 1 e menor ou igual a " + estacoes + ".");

                    listaAdjacencia[x - 1].addAdjacencia(listaAdjacencia[y - 1]);
                }

            } catch (IllegalArgumentException e)
            {
                x = -1;
                y = -1;
                System.err.println(e.getMessage());
            }
        }
    }

    public void testeN()
    {
        System.out.println("Teste " + casos);

        for (int u = 0; u < listaAdjacencia.length; u++)
            listaAdjacencia[u].setCor('b');

        Vertice u = listaAdjacencia[0];
        Queue<Vertice> fila = new LinkedList<Vertice>();
        ArrayList<Vertice> adjU;
        u.setCor('c');
        fila.add(u);
        while (!fila.isEmpty())
        {
            u = fila.poll();
            adjU = u.getAdjacencia();
            for (int v = 0; v < adjU.size(); v++)
            {
                if (adjU.get(v).getCor() == 'b')
                {
                    fila.add(adjU.get(v));
                    adjU.get(v).setCor('c');
                }
                u.setCor('p');
            }
        }
        boolean falhou = false;

        for (int j = 0; j < listaAdjacencia.length && falhou == false; j++)
            falhou = listaAdjacencia[j].getCor() == 'b';

        System.out.println(falhou ? "falha" : "normal");
        System.out.println();
    }
}
