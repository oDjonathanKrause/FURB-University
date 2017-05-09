package Testes;

import furb.arvores_binarias.ArvoreBST;
import furb.arvores_binarias.ArvoreBinaria;
import furb.arvores_binarias.NoArvoreBinaria;
import furb.arvores_n_arias.Arvore;
import furb.arvores_n_arias.NoArvore;

/**
 * @author Djonathan
 * Classe de testes das árvores
 */
public class Main
{

    public static void main(String[] args)
    {
        System.out.println("\tÁrvore n-ária: ");
        testaArvoreNAria();

        System.out.println("\n\n\n\tÁrvore Binária");
        testaArvoreBinaria();

        System.out.println("\n\n\n\tÁrvore Binária de Busca (BST)");
        testaBST();
    }

    /**
     * Testa árvore n-ária
     */
    public static void testaArvoreNAria()
    {
        // Cria nós
        NoArvore<Integer> no1 = new NoArvore<>(1);
        NoArvore<Integer> no2 = new NoArvore<>(2);
        NoArvore<Integer> no3 = new NoArvore<>(3);
        NoArvore<Integer> no4 = new NoArvore<>(4);
        NoArvore<Integer> no5 = new NoArvore<>(5);

        // Cria arvore
        Arvore<Integer> arvore = new Arvore<>();

        // Verifica se esta vazia
        System.out.println("Arvore vazia? " + arvore.vazia());

        // Seta raiz
        arvore.setRaiz(1);
        System.out.println("Raiz setada");

        // Verifica se esta vazia
        System.out.println("Arvore vazia? " + arvore.vazia());

        // Verifica a raiz
        System.out.println("getRaiz: " + arvore.getRaiz());

        // Insere filho
        arvore.getRaiz().inserirFilho(no2);
        arvore.getRaiz().inserirFilho(no3);
        no3.inserirFilho(no4);
        no2.inserirFilho(no5);

        // Verifica se os valores pertencem a arvore
        System.out.println("Pertence (1): " + arvore.pertence(1));
        System.out.println("Pertence (987): " + arvore.pertence(987));

        // Retorna a arvore em String
        System.out.println("Árvore printada: " + arvore.toString());
    }

    /**
     * Testa árvore binária
     */
    public static void testaArvoreBinaria()
    {
        // Cria arvore binaria
        ArvoreBinaria<Integer> arvoreBinaria = new ArvoreBinaria<>();

        // Cria nós
        NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<>(4);
        NoArvoreBinaria<Integer> no5 = new NoArvoreBinaria<>(5);
        NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<>(2, no4, no5);
        NoArvoreBinaria<Integer> no7 = new NoArvoreBinaria<>(7);
        NoArvoreBinaria<Integer> no6 = new NoArvoreBinaria<>(6, no7, null);
        NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<>(3, null, no6);
        NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<>(1, no2, no3);

        // Seta raiz
        arvoreBinaria.setRaiz(no1);

        // Verifica se pertence
        System.out.println(no1.pertence(1));

        // Imprime
        System.out.println(arvoreBinaria.toString());
    }

    /**
     * Testa árvore binária de busca
     */
    public static void testaBST()
    {
        // Cria arvore do tipo String
        ArvoreBST<String> binarySearchTree = new ArvoreBST<>();

        // Insere valores
        binarySearchTree.inserir("Renato");
        binarySearchTree.inserir("Pedro");
        binarySearchTree.inserir("Felipe");
        binarySearchTree.inserir("Henrique");
        binarySearchTree.inserir("Gabriel");
        binarySearchTree.inserir("Djonathan");
        binarySearchTree.inserir("Saul");
        binarySearchTree.inserir("Xuxa");
        
        // Imprime
        System.out.println(binarySearchTree.toString());
        
        // Pega a informação do nós
        System.out.println(binarySearchTree.busca("Djonathan").getInfo());
        System.out.println(binarySearchTree.busca("Xuxa").getInfo());
        System.out.println(binarySearchTree.busca("Marcel") + " (Marcel não foi inserido na árvore, irá retornar null)"); 

    }
}
