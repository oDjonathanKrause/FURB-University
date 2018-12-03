
public class NoArvoreBinaria<T>
{

    protected T info;
    protected NoArvoreBinaria<T> esq;
    protected NoArvoreBinaria<T> dir;

    public NoArvoreBinaria(T info)
    {
        this.info = info;
    }

    public NoArvoreBinaria(T info, NoArvoreBinaria<T> esq, NoArvoreBinaria<T> dir)
    {
        this.info = info;
        this.esq = esq;
        this.dir = dir;
    }

    protected T getInfo()
    {
        return info;
    }

    protected void setInfo(T info)
    {
        this.info = info;
    }

    protected NoArvoreBinaria<T> getEsq()
    {
        return esq;
    }

    protected void setEsq(NoArvoreBinaria<T> esq)
    {
        this.esq = esq;
    }

    protected NoArvoreBinaria<T> getDir()
    {
        return dir;
    }

    protected void setDir(NoArvoreBinaria<T> dir)
    {
        this.dir = dir;
    }

    public NoArvoreBinaria pertence(T info)
    {

        if (this.info.equals(info))
        {
            return this;
        }

        NoArvoreBinaria retEsq = null, retDir = null;

        if (esq != null)
        {
            retEsq = esq.pertence(info);
            if (retEsq != null)
            {
                return retEsq;
            }
        }

        if (dir != null)
        {
            retDir = dir.pertence(info);
            if (retDir != null)
            {
                return retDir;
            }
        }

        return null;
    }

    public String imprimePre()
    {
        String retorno = "" + this.info;
        if (esq != null)
        {
            retorno += "(" + esq.imprimePre() + ")";
        } else
        {
            retorno += "( )";
        }
        if (dir != null)
        {
            retorno += "(" + dir.imprimePre() + ")";
        } else
        {
            retorno += "( )";
        }
        return retorno;
    }

}
