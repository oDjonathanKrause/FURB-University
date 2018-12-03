/*
 *(3,0) Implemente o problema “Rerisson e o Churrasco” existente no URI.
 *   https://www.urionlinejudge.com.br/judge/pt/problems/view/1923 
 */
package questao6;

import grafos.trabalho1.Vertice;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause
 */
public class Churrasco
{

    private Vertice rerisson;
    private LinkedList<Vertice> listaAdjacencia;

    private int relacoes, grau;

    public Churrasco()
    {
        rerisson = new Vertice("RERISSON");
        listaAdjacencia = new LinkedList<Vertice>();
        listaAdjacencia.add(rerisson);
    }

    public void entradas(Scanner sc)
    {
        System.out.println("Somente são aceitos:");
        System.out.println("Relações: maior ou igual a 3 e menor ou igual a 103.");
        System.out.println("Grau: maior ou igual a 1 e menor ou igual a 500.\n");

        relacoes = -1;
        grau = relacoes;

        while (relacoes != 0 && grau != 0)
        {
            System.out.println("Dgt separado por espaço a quantidade de relações e o grau de distância máxima:");
            try
            {
                String[] values = sc.nextLine().split(" ");
                if (values.length != 2)
                {
                    throw new IllegalArgumentException("não foram digitados 2 valores!");
                }

                relacoes = Integer.parseInt(values[0]);
                grau = Integer.parseInt(values[1]);

                if (relacoes != 0 || grau != 0)
                {
                    if (relacoes < 3 || relacoes > 103)
                    {
                        throw new IllegalArgumentException(
                                "Valor invalido" + relacoes + "\nRelações: maior ou igual a 3 e menor ou igual a 103.");
                    }
                    if (grau < 1 || grau > 500)
                    {
                        throw new IllegalArgumentException(
                                "Valor invalido" + grau + "\nGrau: maior ou igual a 1 e menor ou igual a 500.");
                    }
                }
            } catch (IllegalArgumentException e)
            {
                System.err.println(e.getMessage());
                relacoes = -1;
                grau = relacoes;
            }
        }

        for (int i = 0; i < relacoes; i++)
        {
            System.out.println("Relações restantes: " + (relacoes - i) + ":");
            System.out.println("Dgt separado por espaço o nome dos relacionamentos:");

            try
            {

                String[] values = sc.nextLine().split(" ");
                if (values.length != 2)
                {
                    throw new IllegalArgumentException("não foram digitados 2 valores!");
                }

                if (values[0].equalsIgnoreCase(values[1]))
                {
                    throw new IllegalArgumentException(values[0] + " e " + values[1] + " não podem ser iguais");
                }

                Vertice v1 = getRelacao(values[0].toUpperCase());
                Vertice v2 = getRelacao(values[1].toUpperCase());
                v1.addAdjacencia(v2);
                v2.addAdjacencia(v1);

            } catch (IllegalArgumentException e)
            {
                System.err.println(e.getMessage());
                i--;
            }
        }
    }

    public void buscaRelacionamentos()
    {
        LinkedList<Vertice> relacoes = new LinkedList<>();

        rerisson.getAdjacencia().forEach(v -> buscaRelacionamentoR(relacoes, v, 0));

        relacoes.sort((v1, v2) -> v1.getNome().compareTo(v2.getNome()));
        System.out.println(relacoes.size());
        relacoes.forEach(v -> System.out.println(v.getNome()));
    }

    private void buscaRelacionamentoR(LinkedList<Vertice> relacoes, Vertice v, int depth)
    {
        if (depth == grau)
        {
            return;
        }

        if (!relacoes.contains(v) && !v.getNome().equals(rerisson.getNome()))
        {
            relacoes.add(v);
        }
        v.getAdjacencia().forEach(vv -> buscaRelacionamentoR(relacoes, vv, depth + 1));
    }

    private Vertice getRelacao(String value)
    {
        Optional<Vertice> optional = listaAdjacencia.stream().filter(v -> v.getNome().equals(value)).findFirst();
        if (optional.isPresent())
        {
            return optional.get();
        } else
        {
            listaAdjacencia.addLast(new Vertice(value));
            return listaAdjacencia.getLast();
        }
    }
}
