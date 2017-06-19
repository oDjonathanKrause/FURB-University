
public class ArvoreBST<T extends Comparable> extends ArvoreBinariaAbstract<T>
{

    public ArvoreBST()
    {    }

    public void inserir(T info)
    {
        if (raiz == null)
        {
            raiz = new NoArvoreBST<T>(info);
        } else
        {
            ((NoArvoreBST<T>) raiz).inserir(info);
        }
    }

    public NoArvoreBST<T> busca(T info)
    {
        NoArvoreBST<T> retorno = null;
        if (raiz != null)
        {
            retorno = ((NoArvoreBST<T>) raiz).busca(info);
        }
        return retorno;
    }

    public void retirar(T info)
    {
        if (raiz != null && info.equals(raiz.info) && raiz.esq == null && raiz.dir == null)
        {
            raiz = null;
        }
        if (info.equals(raiz.dir) && raiz.esq == null)
        {
            raiz = raiz.dir;
        }
        if (info.equals(raiz.esq) && raiz.dir == null)
        {
            raiz = raiz.dir;
        } else if (raiz != null)
        {
            ((NoArvoreBST<T>) raiz).retirar(info);
        }
    }

}
