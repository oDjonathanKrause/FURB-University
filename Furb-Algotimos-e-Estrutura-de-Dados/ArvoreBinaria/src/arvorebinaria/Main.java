package arvorebinaria;

/**
 *
 * @author drkrause
 */
public class Main
{
    public static void main(String[] args)
    {
        ArvoreBinaria<String> arvore = new ArvoreBinaria<>();
        NoArvoreBinaria<String> no = new NoArvoreBinaria<>();
        NoArvoreBinaria<String> noEsquerda = new NoArvoreBinaria<>("B");
        
        System.out.println("Vazia? " + arvore.vazia());
        
        no.setInfo("A");
        no.setEsq(noEsquerda);
        arvore.setRaiz(no);
        
        System.out.println("Conteudo: " + arvore.toString());
        System.out.println("Vazia? " + arvore.vazia());
        
    }

}
