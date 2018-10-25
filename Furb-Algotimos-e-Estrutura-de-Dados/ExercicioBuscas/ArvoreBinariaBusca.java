
class ArvoreBinariaBusca implements InterfaceExercicio8
{

    private ArvoreBST aBST = new ArvoreBST();

    public void addAll(Veiculo[] vetor)
    {
        for (int i = 0; i < (vetor.length); i++)
        {
            aBST.inserir(vetor[i]);
        }
    }

    public Veiculo[] localizaMaisAntigo()
    {

        NoArvoreBST<Veiculo> temp = (NoArvoreBST<Veiculo>) aBST.raiz;
        int maisAntigo = maisAntigo(temp, Integer.MAX_VALUE);
        int qtdMaisAntigo = qtdMaisAntigo(temp, maisAntigo);

        Veiculo[] retorno = new Veiculo[qtdMaisAntigo];

        return arrayDeAntigo(temp, maisAntigo, retorno);
    }

    private Veiculo[] arrayDeAntigo(NoArvoreBST<Veiculo> temp, int maisAntigo, Veiculo[] retorno)
    {

        if (((Veiculo) temp.getInfo()).getAno() == maisAntigo)
        {
            for (int i = 0; i < retorno.length; i++)
            {
                if (retorno[i] == null)
                {
                    retorno[i] = (Veiculo) temp.getInfo();
                    break;
                }
            }
        }

        if (temp.esq != null)
        {
            arrayDeAntigo(((NoArvoreBST<Veiculo>) temp.esq), maisAntigo, retorno);
        }

        if (temp.dir != null)
        {
            arrayDeAntigo(((NoArvoreBST<Veiculo>) temp.dir), maisAntigo, retorno);
        }

        return retorno;
    }

    private int qtdMaisAntigo(NoArvoreBST<Veiculo> temp, int i)
    {
        int retorno = 0;
        if (((Veiculo) temp.getInfo()).getAno() == i)
        {
            retorno++;
        }
        if (temp.esq != null)
        {
            retorno = retorno + qtdMaisAntigo(((NoArvoreBST<Veiculo>) temp.esq), i);
        }
        if (temp.dir != null)
        {
            retorno = retorno + qtdMaisAntigo(((NoArvoreBST<Veiculo>) temp.dir), i);
        }
        return retorno;
    }

    private int maisAntigo(NoArvoreBST<Veiculo> temp, int maisAntigo)
    {
        int retorno = maisAntigo;
        int retornoChamada;
        if (((Veiculo) temp.getInfo()).getAno() < retorno)
        {
            retorno = ((Veiculo) temp.getInfo()).getAno();
        }
        if (temp.esq != null)
        {
            retornoChamada = maisAntigo(((NoArvoreBST<Veiculo>) temp.esq), retorno);
            if (retornoChamada < retorno)
            {
                retorno = retornoChamada;
            }

        }
        if (temp.dir != null)
        {
            retornoChamada = maisAntigo(((NoArvoreBST<Veiculo>) temp.dir), retorno);
            if (retornoChamada < retorno)
            {
                retorno = retornoChamada;
            }
        }
        return retorno;

    }

    @Override
    public Veiculo getMenorPlaca()
    {
        if (aBST.vazia())
        {
            return null;
        }
        NoArvoreBST<Veiculo> temp = (NoArvoreBST<Veiculo>) aBST.raiz;
        while (temp.esq != null)
        {
            temp = (NoArvoreBST<Veiculo>) temp.esq;
        }
        return (Veiculo) temp.info;
    }

    @Override
    public Veiculo getMaiorPlaca()
    {
        if (aBST.vazia())
        {
            return null;
        }
        NoArvoreBST<Veiculo> temp = (NoArvoreBST<Veiculo>) aBST.raiz;
        while (temp.dir != null)
        {
            temp = (NoArvoreBST<Veiculo>) temp.dir;
        }
        return (Veiculo) temp.info;
    }

    @Override
    public Veiculo pesquisa(String placa)
    {
        if (aBST.vazia())
        {
            return null;
        }
        NoArvoreBST<Veiculo> temp = (NoArvoreBST<Veiculo>) aBST.raiz;
        return pesquisaPlaca(temp, placa);
    }

    private Veiculo pesquisaPlaca(NoArvoreBST<Veiculo> temp, String placa)
    {

        if (((Veiculo) temp.info).getPlaca().equals(placa))
        {
            return (Veiculo) temp.info;
        }
        if (((Veiculo) temp.info).getPlaca().compareTo(placa) < 0)
        {
            if (temp.dir == null)
            {
                return null;
            } else
            {
                return pesquisaPlaca((NoArvoreBST<Veiculo>) temp.dir, placa);
            }
        }
        if (((Veiculo) temp.info).getPlaca().compareTo(placa) > 0)
        {
            if (temp.esq == null)
            {
                return null;
            } else
            {
                return pesquisaPlaca((NoArvoreBST<Veiculo>) temp.esq, placa);
            }
        }
        return null;

    }

}
