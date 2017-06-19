
class ListaPesquisaLinear implements InterfaceExercicio8
{

    private Veiculo[] listaPesquisaLinear;

    public void addAll(Veiculo[] vetor)
    {
        listaPesquisaLinear = new Veiculo[vetor.length];
        System.arraycopy(vetor, 0, listaPesquisaLinear, 0, vetor.length);
    }

    public Veiculo[] localizaMaisAntigo()
    {
        int maisAntigo = Integer.MAX_VALUE;

        //Verifica o veiculo mais antigo;
        for (int i = 0; i < listaPesquisaLinear.length; i++)
        {
            if (maisAntigo > listaPesquisaLinear[i].getAno())
            {
                maisAntigo = listaPesquisaLinear[i].getAno();
            }
        }
        
        //Verifica a quantidade dos veiculos mais antigos;
        int qtdMaisAntigo = 0;
        for (int i = 0; i < listaPesquisaLinear.length; i++)
        {
            if (listaPesquisaLinear[i].getAno() == maisAntigo)
            {
                qtdMaisAntigo++;
            }
        }
        
        //Cria um Array do tamanho dos veiculos antigos e os insere no array
        Veiculo[] retorno = new Veiculo[qtdMaisAntigo];
        for (int i = 0; i < listaPesquisaLinear.length; i++)
        {
            if (listaPesquisaLinear[i].getAno() == maisAntigo)
            {
                retorno[--qtdMaisAntigo] = listaPesquisaLinear[i];

            }
        }
        return retorno;
    }

    @Override
    public Veiculo getMenorPlaca()
    {
        if (isEmpty())
            return null;

        Veiculo retorno = listaPesquisaLinear[0];
        
        for (int i = 0; i < listaPesquisaLinear.length; i++)
        {
            if ((listaPesquisaLinear[i].getPlaca().compareTo(retorno.getPlaca()) < 0))
                retorno = listaPesquisaLinear[i];
        }

        return retorno;
    }

    @Override
    public Veiculo getMaiorPlaca()
    {
        if (isEmpty())
            return null;

        Veiculo retorno = listaPesquisaLinear[0];
        for (int i = 0; i < listaPesquisaLinear.length; i++)
        {
            if ((listaPesquisaLinear[i].getPlaca().compareTo(retorno.getPlaca()) > 0))
                retorno = listaPesquisaLinear[i];
        }

        return retorno;
    }

    @Override
    public Veiculo pesquisa(String placa)
    {
        if (isEmpty())
            return null;

        for (int i = 0; i < listaPesquisaLinear.length; i++)
        {
            if (listaPesquisaLinear[i].getPlaca().equalsIgnoreCase(placa))
                return listaPesquisaLinear[i];
        }
        return null;
    }

    public boolean isEmpty()
    {
        return listaPesquisaLinear == null;
    }

}
