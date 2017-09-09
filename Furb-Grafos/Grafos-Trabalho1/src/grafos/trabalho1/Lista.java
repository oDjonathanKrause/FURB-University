/*
 * @author Carlos Henrique Stapait Junior, Djonathan Krause 
 */
package grafos.trabalho1;

public class Lista
{

    private NoLista primeiro;

    public NoLista getPrimeiro()
    {
        return primeiro;
    }

    public void inserirNo(int info)
    {
        NoLista no = new NoLista(info);
        no.setProximoNo(primeiro);
        primeiro = no;
    }

    public String imprimirListaRecursivo()
    {
        return this.imprimirRecursivo(primeiro, "");
    }

    private String imprimirRecursivo(NoLista no, String saida)
    {
        if (no != null)
        {
            saida += no.getInfo() + "\n" + this.imprimirRecursivo(no.getProximoNo(), saida);
        }
        return saida;
    }

    public boolean listaVazia()
    {
        return primeiro == null;
    }

    public NoLista buscarNo(int info)
    {
        NoLista no = primeiro;
        while (no != null)
        {
            if (no.getInfo() == info)
            {
                return no;
            }
            no = no.getProximoNo();
        }
        return null;
    }

    public int comprimentoLista()
    {
        NoLista no = primeiro;
        int tamanho = 0;
        while (no != null)
        {
            tamanho++;
            no = no.getProximoNo();
        }
        return tamanho;
    }
}
