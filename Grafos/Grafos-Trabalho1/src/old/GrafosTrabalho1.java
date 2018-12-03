package old;

import old.Questao4;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause 
 */
public class GrafosTrabalho1
{
    private static int[][] matrizAdjacenciaGrafoNaoCompleto = {{0, 1, 0}
                                                             , {1, 1, 2}
                                                             , {0, 2, 0}};
    
    private static int[][] matrizAdjacenciaGrafoCompleto = {{0, 1, 1}
                                                          , {1, 1, 2}
                                                          , {1, 2, 0}};
    
    private static int[][] matrizAdjacenciaGrafoNulo = {{0, 0, 0}
                                                      , {0, 0, 0}
                                                      , {0, 0, 0}};
    
    private static int[][] matrizAdjacenciaNaoMultigrafo = {{0, 1, 1}
                                                          , {1, 0, 1}
                                                          , {1, 1, 0}};
    
    private static int[][] matrizAdjacenciaGrafoDirigido = {{0, 1, 1}
                                                          , {1, 0, 1}
                                                          , {0, 0, 0}};

    public static void main(/*String[] args*/)
    {
        Questao4 q4 = new Questao4();
        System.out.println("matrizAdjacenciaGrafoNaoCompleto: " + q4.tipoDoGrafo(matrizAdjacenciaGrafoNaoCompleto));
        System.out.println("Graus: " + q4.grausDoVertice(matrizAdjacenciaGrafoNaoCompleto, q4.isDirigido(matrizAdjacenciaGrafoNaoCompleto)) + "\n");
        
        System.out.println("matrizAdjacenciaGrafoCompleto: " + q4.tipoDoGrafo(matrizAdjacenciaGrafoCompleto));
        System.out.println("Graus: " + q4.grausDoVertice(matrizAdjacenciaGrafoCompleto, q4.isDirigido(matrizAdjacenciaGrafoCompleto)) + "\n");
        
        System.out.println("matrizAdjacenciaGrafoNulo: " + q4.tipoDoGrafo(matrizAdjacenciaGrafoNulo));
        System.out.println("Graus: " + q4.grausDoVertice(matrizAdjacenciaGrafoNulo, q4.isDirigido(matrizAdjacenciaGrafoNulo)) + "\n");
        
        System.out.println("matrizAdjacenciaNaoMultigrafo: " + q4.tipoDoGrafo(matrizAdjacenciaNaoMultigrafo));
        System.out.println("Graus: " + q4.grausDoVertice(matrizAdjacenciaNaoMultigrafo, q4.isDirigido(matrizAdjacenciaNaoMultigrafo)) + "\n");
        
        System.out.println("matrizAdjacenciaGrafoDirigido: " + q4.tipoDoGrafo(matrizAdjacenciaGrafoDirigido));
        System.out.println("Graus: " + q4.grausDoVertice(matrizAdjacenciaGrafoDirigido, q4.isDirigido(matrizAdjacenciaGrafoDirigido)) + "\n");
        
        System.out.println("matrizAdjacenciaGrafoDirigido: " + q4.tipoDoGrafo(matrizAdjacenciaGrafoCompleto));
        System.out.println("Graus: " + q4.grausDoVertice(matrizAdjacenciaGrafoCompleto, q4.isDirigido(matrizAdjacenciaGrafoCompleto)) + "\n");
        System.out.println("Arestas: " + q4.arestasDoGrafo(matrizAdjacenciaGrafoCompleto) + "\n");
        

    }

}
