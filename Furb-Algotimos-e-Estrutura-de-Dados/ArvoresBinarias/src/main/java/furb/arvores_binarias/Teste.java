package furb.arvores_binarias;

public class Teste
{

    public static void main(String[] args)
    {

        ArvoreBST<String> abc;

        NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<>(4);
        NoArvoreBinaria<Integer> no5 = new NoArvoreBinaria<>(5);
        NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<>(2, no4, no5);

        NoArvoreBinaria<Integer> no7 = new NoArvoreBinaria<>(7);
        NoArvoreBinaria<Integer> no6 = new NoArvoreBinaria<>(6, no7, null);

        NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<>(3, null, no6);

        NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<>(1, no2, no3);

        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();

        arvore.setRaiz(no1);
        System.out.println(no1.pertence(1));
        System.out.println(arvore.toString());
        System.out.println("-----");

        abc = new ArvoreBST<>();

        abc.inserir("renato");
        abc.inserir("pedro");
        abc.inserir("felipe");
        abc.inserir("henrique");
        abc.inserir("gabriel");
        abc.inserir("djonathan");
        abc.inserir("saul");
        abc.inserir("xuxa");
        System.out.println(abc.toString());
        System.out.println(abc.busca("gabriel").getInfo());
        System.out.println(abc.busca("xuxa").getInfo());
        System.out.println(abc.busca("marcel"));

    }

}
