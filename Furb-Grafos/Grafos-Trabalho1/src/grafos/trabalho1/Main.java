package grafos.trabalho1;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause
 */
import java.util.Scanner;
import questao4.ReconheceGrafo;
import questao6.Churrasco;
import questao7.Energia;

/*Carlos Henrique Stapait Junior*/
public class Main
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Grafo grafo = null;

        System.out.println("Desenvolvido por: Carlos Henrique Stapait Junior.");
        int option = 1;
        while (option != 0)
        {
            System.out.println("Dgt a opção:" + "\n1: Criar grafo" + "\n2: imprimir grafo" + "\n3: Tipo do grafo"
                    + "\n4: Arestas do grafo" + "\n5: Graus do vertice" + "\n6: Grafo de teste"
                    + "\n7: Rerisson e o Churrasco" + "\n8: transmissão de energia" + "\n0: encerrar.");
            option = sc.nextInt();

            switch (option)
            {
                case 1:
                    System.out.println("NOVO GRAFO:");
                    System.out.println("Dgt a quantidade de vertices.");
                    int value1 = sc.nextInt();

                    if (value1 > 0)
                    {
                        grafo = new Grafo(value1);
                        System.out.println("ADICIONANDO ARESTAS:");
                        int value2 = -1;
                        sc.nextLine();// esvaziar o buffer do teclado

                        while (value1 != 0 && value2 != 0)
                        {
                            System.out.println("Dgt separado por espaço os vértices relacionados, 0 0 para retornar:");

                            try
                            {
                                String[] values = sc.nextLine().split(" ");
                                if (values.length != 2)
                                {
                                    throw new IllegalArgumentException("não foram digitados 2 valores!");
                                }

                                value1 = Integer.parseInt(values[0]);
                                value2 = Integer.parseInt(values[1]);

                                if (value1 != 0 || value2 != 0)
                                {
                                    if (value1 < 0 || value1 >= grafo.getQtdVertices())
                                    {
                                        throw new IllegalArgumentException("Vértice " + value1 + " não existe!");
                                    }
                                    if (value2 < 0 || value2 >= grafo.getQtdVertices())
                                    {
                                        throw new IllegalArgumentException("Vértice " + value2 + " não existe!");
                                    }

                                    grafo.insertAresta(value1, value2);
                                    System.out.println(grafo.toString());
                                }
                            } catch (IllegalArgumentException e)
                            {
                                System.err.println(e.getMessage());
                                value1 = -1;
                                value2 = value1;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println(grafo.toString());
                    break;
                case 3:
                    System.out.println("TIPO DO GRAFO:");
                    if (grafo != null)
                    {
                        System.out.println(new ReconheceGrafo().TipoDoGrafo(grafo.getMatrizAdjacencia()));
                    }
                    break;
                case 4:
                    System.out.println("ARESTAS DO GRAFO:");
                    if (grafo != null)
                    {
                        System.out.println(new ReconheceGrafo().arestasDoGrafo(grafo.getMatrizAdjacencia()));
                    }
                    break;
                case 5:
                    System.out.print("GRAUS DO VERTICE:");
                    if (grafo != null)
                    {
                        System.out.println(new ReconheceGrafo().grausDoVertice(grafo.getMatrizAdjacencia()));
                    }
                    break;
                case 6:
                    System.out.println("GRAFO DE TESTE:");
                    grafo = new Grafo(4);
                    grafo.insertAresta(1, 2);
                    grafo.insertAresta(1, 3);
                    grafo.insertAresta(1, 3);
                    grafo.insertAresta(1, 4);
                    grafo.insertAresta(2, 4);
                    grafo.insertAresta(4, 4);
                    break;
                case 7:
                    System.out.println("RERISSON E O CHURRASCO");
                    sc.nextLine();// esvaziar o buffer do teclado
                    Churrasco churrasco = new Churrasco();
                    churrasco.entradas(sc);
                    churrasco.buscaRelacionamentos();
                    break;
                case 8:
                    System.out.println("TRANSMISSÃO DE ENERGIA");
                    sc.nextLine();// esvaziar o buffer do teclado
                    Energia energia = new Energia();
                    energia.entrada(sc);
                    energia.testeN();
            }

        }
        sc.close();
        System.out.print("Programa encerrado.");
    }
}
