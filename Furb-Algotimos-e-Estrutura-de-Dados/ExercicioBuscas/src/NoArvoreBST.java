
public class NoArvoreBST<T extends Comparable> extends NoArvoreBinaria
{

    public NoArvoreBST(T info)
    {
        super(info);
    }

    public void inserir(T info)
    {
        if (info.compareTo(this.getInfo()) >= 0)
        {
            if (dir == null)
            {
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

    public NoArvoreBST<T> busca(T info)
    {
        if (info.equals(this.getInfo()))
        {
            return this;
        }

        if (info.compareTo(this.getInfo()) >= 0)
        {
            if (dir == null)
            {
                return null;
            } else
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

    public Object retirar(T info)
    {
//		TODO verificar casos especiais na raiz.
        NoArvoreBST<T> excluir;

        if (esq != null && esq.getInfo().equals(info))
        {
            excluir = (NoArvoreBST<T>) esq;
            int caso = excluir.retornaCasoExclusao();
            if (caso == 0)
            {
                this.setEsq(null);
            }
            if (caso == 1)
            {
                this.setEsq(this.getEsq().esq);
            }
            if (caso == 2)
            {
                this.setEsq(this.getEsq().dir);
            }
            if (caso == 3)
            {
                NoArvoreBST<T> sucessor = excluir.sucessor();
                T infoSalva = (T) sucessor.getInfo();
                retirar(infoSalva);
                excluir.setInfo(infoSalva);
            }

        } else if (dir != null && dir.getInfo().equals(info))
        {
            excluir = (NoArvoreBST<T>) dir;
            int caso = excluir.retornaCasoExclusao();
            if (caso == 0)
            {
                this.setDir(null);
            }
            if (caso == 1)
            {
                this.setDir(this.getEsq().esq);
            }
            if (caso == 2)
            {
                this.setDir(this.getEsq().dir);
            }
            if (caso == 3)
            {
                NoArvoreBST<T> sucessor = excluir.sucessor();
                T infoSalva = (T) excluir.getInfo();
                retirar(infoSalva);
                this.setInfo(infoSalva);
            }
        } else if (info.compareTo(this.getInfo()) >= 0)
        {
            if (this.getDir() != null)
            {
                return ((NoArvoreBST) this.getDir()).retirar(info);
            } else
            {
                return null;
            }
        } else
        {
            if (this.getEsq() != null)
            {
                return ((NoArvoreBST) this.getEsq()).retirar(info);
            } else
            {
                return null;
            }

        }
        return null;
    }

    private int retornaCasoExclusao()
    {
//		Nenhum filho
        int retorno = 0;
//		Um filho a esquerda
        if (esq != null)
        {
            retorno = 1;
        }
//		Um filho a direita
        if (dir != null)
        {
            retorno = 2;
        }
//		Dois filhos
        if (esq != null && dir != null)
        {
            retorno = 3;
        }
        return retorno;

    }

    private NoArvoreBST<T> sucessor()
    {
        NoArvoreBST<T> retorno;
//		Move um n� a direita
        retorno = (NoArvoreBST<T>) this.getDir();
//		Move todos para esquerda at� achar um null
        while (retorno.getEsq() != null)
        {
            retorno = (NoArvoreBST<T>) retorno.getEsq();
        }
        return retorno;

    }

}
