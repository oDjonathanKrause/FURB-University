package furb.arvores_binarias;

public class ArvoreBinaria<T> extends ArvoreBinariaAbstract<T>
{

    // Construtor
    public ArvoreBinaria()
    {    }

    @Override
    public void setRaiz(NoArvoreBinaria<T> no)
    {
        this.raiz = no;
    }

}
