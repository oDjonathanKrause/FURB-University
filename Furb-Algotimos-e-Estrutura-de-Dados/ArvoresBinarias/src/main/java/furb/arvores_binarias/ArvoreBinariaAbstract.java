package furb.arvores_binarias;

abstract class ArvoreBinariaAbstract<T>
{

    protected NoArvoreBinaria<T> raiz;

    public ArvoreBinariaAbstract()
    {    }

    protected void setRaiz(NoArvoreBinaria<T> no)
    {
        this.raiz = no;
    }

    public boolean vazia()
    {
        return (this.raiz == null);
    }

    public NoArvoreBinaria<T> pertence(T item)
    {
        return raiz.pertence(item);
    }

    public String toString()
    {
        return "(" + raiz.imprimePre() + ")";
    }
}
