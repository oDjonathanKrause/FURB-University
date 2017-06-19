
import java.util.Comparator;

class ListaPesquisaBinaria implements InterfaceExercicio8
{

    private Veiculo[] listaPesquisaBinaria;

    public void addAll(Veiculo[] vetor)
    {
        listaPesquisaBinaria = new Veiculo[vetor.length];
        System.arraycopy(vetor, 0, listaPesquisaBinaria, 0, vetor.length);
        mergeSort(listaPesquisaBinaria, 0, (listaPesquisaBinaria.length - 1));
    }

    public Veiculo[] localizaMaisAntigo()
    {
        // Verifica o veiculo mais antigo;
        int maisAntigo = Integer.MAX_VALUE;
        for (int i = 0; i < listaPesquisaBinaria.length; i++)
        {
            if (maisAntigo > listaPesquisaBinaria[i].getAno())
            {
                maisAntigo = listaPesquisaBinaria[i].getAno();
            }
        }
        // Verifica a quantidade dos ve�culos mais antigos;
        int qtdMaisAntigo = 0;
        for (int i = 0; i < listaPesquisaBinaria.length; i++)
        {
            if (listaPesquisaBinaria[i].getAno() == maisAntigo)
            {
                qtdMaisAntigo++;
            }
        }
        // Cria um Array do tamanho dos ve�culos antigos e os insere no array
        Veiculo[] retorno = new Veiculo[qtdMaisAntigo];
        for (int i = 0; i < listaPesquisaBinaria.length; i++)
        {
            if (listaPesquisaBinaria[i].getAno() == maisAntigo)
            {
                retorno[--qtdMaisAntigo] = listaPesquisaBinaria[i];

            }
        }
        return retorno;
    }

    @Override
    public Veiculo getMenorPlaca()
    {
        if (isEmpty())
        {
            return null;
        }
        return listaPesquisaBinaria[0];
    }

    @Override
    public Veiculo getMaiorPlaca()
    {
        if (isEmpty())
            return null;

        return listaPesquisaBinaria[listaPesquisaBinaria.length - 1];
    }

    @Override
    public Veiculo pesquisa(String placa)
    {
        if (isEmpty())
            return null;

        return pesquisaBinaria(placa);
    }

    private void mergeSort(Veiculo[] vetor, int de, int ate)
    {
        if (de == ate)
        {
            return;
        }
        int meio = (de + ate) / 2;
        // Sort the first and the second half
        mergeSort(vetor, de, meio);
        mergeSort(vetor, meio + 1, ate);
        merge(vetor, de, meio, ate);
    }

    private void merge(Veiculo[] vetor, int de, int meio, int ate)
    {
        int n = ate - de + 1;
        Veiculo[] auxiliar = new Veiculo[n];

        int valorDe = de;
        int valorMeio = meio + 1;
        int index = 0;

        while (valorDe <= meio && valorMeio <= ate)
        {
            if (vetor[valorDe].compareTo(vetor[valorMeio]) < 0)
            {
                auxiliar[index] = vetor[valorDe];
                valorDe++;
            } else
            {
                auxiliar[index] = vetor[valorMeio];
                valorMeio++;
            }
            index++;
        }

        while (valorDe <= meio)
        {
            auxiliar[index] = vetor[valorDe];
            valorDe++;
            index++;
        }
        while (valorMeio <= ate)
        {
            auxiliar[index] = vetor[valorMeio];
            valorMeio++;
            index++;
        }

        for (index = 0; index < n; index++)
        {
            vetor[de + index] = auxiliar[index];
        }
    }

    public static Veiculo[] bubbleSort(Veiculo[] input)
    {

        Veiculo temp;
        for (int i = 1; i < input.length; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (input[j].compareTo(input[j - 1]) < 0)
                {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }

    public Veiculo pesquisaBinaria(String placa)
    {
        int ini = 0;
        int fim = listaPesquisaBinaria.length - 1;
        int meio;
        
        while (ini <= fim)
        {
            meio = (ini + fim) / 2;
            if (listaPesquisaBinaria[meio].getPlaca().compareTo(placa) > 0)
            {
                fim = meio - 1;
            } else
            {
                if (listaPesquisaBinaria[meio].getPlaca().compareTo(placa) < 0)
                {
                    ini = meio + 1;
                } else
                {
                    return listaPesquisaBinaria[meio];
                }
            }
        }
        return null;
    }

    public boolean isEmpty()
    {
        return listaPesquisaBinaria == null;
    }

}
