package furb.arvores_n_arias;

/**
 * @author Djonathan
 */
public class Main
{

    public static void main(String[] args)
    {
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
        
        // Verifica se os valores pertencem a arvore
        System.out.println("Pertence (1): " + arvore.pertence(1));
        System.out.println("Pertence (987): " + arvore.pertence(987));

        // Retorna a arvore em String
        System.out.println("Árvore: " + arvore.toString());
    }

}
