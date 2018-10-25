package furb.arvores_binarias;

public class NoArvoreBST<T extends Comparable> extends NoArvoreBinaria
{

    // Construtor
    public NoArvoreBST(T info)
    {
        super(info);
    }

    /**
     * Método que insere valor na árvore
     *
     * @param info - Valor que será inserido
     */
    public void inserir(T info)
    {
        // Se o valor for maior que 0
        if (info.compareTo(this.getInfo()) >= 0)
        {
            // Se a direita do nó for nula
            if (dir == null)
            {
                // Seta o valor na direita
                setDir(new NoArvoreBST(info));
            } else
            {
                ((NoArvoreBST) dir).inserir(info);
            }
        }
        if (info.compareTo(this.getInfo()) < 0)
        {
            if (esq == null)
            {
                setEsq(new NoArvoreBST(info));
            } else
            {
                ((NoArvoreBST) esq).inserir(info);
            }
        }

    }

    /**
     * Busca a informação na Binary Search Tree
     *
     * @param info - Valor procurado
     * @return nó com o valor procurado
     */
    public NoArvoreBST<T> busca(T info)
    {
        // Se o valor procurado for o mesmo dessa instancia, retorna
        if (info.equals(this.getInfo()))
        {
            return this;
        }

        // Se o valor desse nó for maior do que zero
        if (info.compareTo(this.getInfo()) >= 0)
        {
            // se a direita for nula, retorna null
            if (dir == null)
            {
                return null;
            } // Senao, retorna o valor da busca da direita (recursivo)
            else
            {
                return ((NoArvoreBST) dir).busca(info);
            }
        }
        if (info.compareTo(this.getInfo()) < 0)
        {
            if (esq == null)
            {
                return null;
            } else
            {
                return ((NoArvoreBST) esq).busca(info);
            }
        }
        return null;
    }

}
