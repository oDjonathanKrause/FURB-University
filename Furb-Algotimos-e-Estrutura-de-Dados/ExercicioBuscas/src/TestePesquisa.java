
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author Marcel
 */
public class TestePesquisa
{

    public static void main(String[] args)
    {
        Veiculo[] vetor = new Veiculo[10_000];
        String[] placasPesq = new String[5];
        Veiculo veic;

        // geração dos dados
        String nome, placa, modelo;
        int ano;

        for (int i = 0; i < vetor.length; i++)
        {
            nome = GeradorAleatorio.geraNome() + " " + i;
            placa = GeradorAleatorio.geraPlaca();
            modelo = GeradorAleatorio.geraModelo();
            ano = GeradorAleatorio.geraAno();
            vetor[i] = new Veiculo(placa, modelo, ano, nome);
            //System.out.println(vetor[i]);
        }

        // identificação das placas a pesquisar posteriormente
        placasPesq[0] = "zzz-9999"; // para não ser localizado
        for (int i = 1; i < placasPesq.length; i++)
        {
            int pos = vetor.length / i - 1;
            placasPesq[i] = vetor[pos].getPlaca();
        }

        // criação das estruturas
        InterfaceExercicio8[] estruturas = new InterfaceExercicio8[3];
        estruturas[0] = new ListaPesquisaLinear();
        estruturas[1] = new ListaPesquisaBinaria();
        estruturas[2] = new ArvoreBinariaBusca();

        // teste de estruturas vazias
        for (int i = 0; i < estruturas.length; i++)
        {
            try
            {
                veic = estruturas[i].getMenorPlaca();
                if (veic != null)
                {
                    System.out.println("Problema: menor não nulo em " + estruturas[i].getClass().getName());
                }
            } catch (NullPointerException npe)
            {
                System.out.println("Problema: menor com NullPointerException em " + estruturas[i].getClass().getName());
            }

            try
            {
                veic = estruturas[i].getMaiorPlaca();
                if (veic != null)
                {
                    System.out.println("Problema: MAIOR não nulo em " + estruturas[i].getClass().getName());
                }
            } catch (NullPointerException npe)
            {
                System.out.println("Problema: MAIOR com NullPointerException em " + estruturas[i].getClass().getName());
            }

            try
            {
                veic = estruturas[i].pesquisa(placasPesq[0]);
                if (veic != null)
                {
                    System.out.println("Problema: >>> Pesquisa não nulo em " + estruturas[i].getClass().getName());
                }
            } catch (NullPointerException npe)
            {
                System.out.println("Problema: >>> Pesquisa com NullPointerException em " + estruturas[i].getClass().getName());
            }

        }

        // carga das estruturas
        long tempoInicial, tempoFinal;

        for (int i = 0; i < estruturas.length; i++)
        {
            System.out.println("Carregando " + estruturas[i].getClass().getName());
            tempoInicial = System.nanoTime();
            estruturas[i].addAll(vetor);
            tempoFinal = System.nanoTime();
            System.out.format("Tempo: (nano)= %1$,d  \n", (tempoFinal - tempoInicial));
        }

        //teste dos métodos
        Veiculo[] vvAntigos;
        Veiculo[] vAntigo = estruturas[0].localizaMaisAntigo();
        Arrays.sort(vAntigo);
        System.out.println("Identificou " + vAntigo.length + " veículos mais antigos do ano " + vAntigo[0].getAno());
        Veiculo vMenor = estruturas[0].getMenorPlaca();
        System.out.println("Menor placa = " + vMenor);
        Veiculo vMaior = estruturas[0].getMaiorPlaca();
        System.out.println("Maior placa = " + vMaior);

        for (int i = 0; i < estruturas.length; i++)
        {
            System.out.println("\n -------- Executando com " + estruturas[i].getClass().getName());
            tempoInicial = System.nanoTime();
            vvAntigos = estruturas[i].localizaMaisAntigo();
            tempoFinal = System.nanoTime();
            System.out.format("Tempo Mais Antigo: (nano)= %1$,d  \n", (tempoFinal - tempoInicial));
            Arrays.sort(vvAntigos);
            if (!Arrays.equals(vAntigo, vvAntigos))
            {
                System.out.println("Problema: Antigos diferentes em " + estruturas[i].getClass().getName() + ". Quantidade = " + vvAntigos.length);
            }

            tempoInicial = System.nanoTime();
            veic = estruturas[i].getMenorPlaca();
            tempoFinal = System.nanoTime();
            System.out.format("Tempo menor placa: (nano)= %1$,d  \n", (tempoFinal - tempoInicial));
            if (veic != vMenor)
            {
                System.out.println("Problema: menor diferente em " + estruturas[i].getClass().getName() + " = " + veic);
            }

            tempoInicial = System.nanoTime();
            veic = estruturas[i].getMaiorPlaca();
            tempoFinal = System.nanoTime();
            System.out.format("Tempo MAIOR placa: (nano)= %1$,d  \n", (tempoFinal - tempoInicial));
            if (veic != vMaior)
            {
                System.out.println("Problema: MAIOR diferente em " + estruturas[i].getClass().getName() + " = " + veic);
            }
        }

        // teste do método de pesquisa SEM sucesso
        for (int i = 0; i < estruturas.length; i++)
        {
            System.out.println("\n <>>< Executando com " + estruturas[i].getClass().getName());
            tempoInicial = System.nanoTime();
            Veiculo v = estruturas[i].pesquisa(placasPesq[0]);
            tempoFinal = System.nanoTime();
            System.out.format("Tempo pesquisa sem sucesso: (nano)= %1$,d  \n", (tempoFinal - tempoInicial));
            if (v != null)
            {
                System.out.println("Problema: sem sucesso " + estruturas[i].getClass().getName() + " localizou " + placasPesq[0]);
            }
        }
        // teste do método de pesquisa com sucesso
        for (int i = 0; i < estruturas.length; i++)
        {
            System.out.println("\n +++++++ Executando com " + estruturas[i].getClass().getName());
            tempoInicial = System.nanoTime();
            for (int p = 1; p < placasPesq.length; p++)
            {
                Veiculo v = estruturas[i].pesquisa(placasPesq[p]);
                try
                {
                    if (!placasPesq[p].equals(v.getPlaca()))
                    {
                        System.out.println("Problema: COM sucesso:" + estruturas[i].getClass().getName() + ": Pesquisado " + placasPesq[p] + " localizado " + v.getPlaca());
                    }
                } catch (NullPointerException npe)
                {
                    System.out.println("Problema: COM sucesso:" + estruturas[i].getClass().getName() + ": Não encontrado " + placasPesq[p]);
                }
            }
            tempoFinal = System.nanoTime();
            System.out.format("Tempo pesquisa sem sucesso: (nano)= %1$,d  \n", (tempoFinal - tempoInicial));
        }

    }

}

class GeradorAleatorio
{

    private static Random geraNumero = new Random();
    private static String[] nomes =
    {
        "José", "Maria", "Pedro", "João", "Mario",
        "Paulo", "Paula", "Sandra", "André", "Carla"
    };
    private static String[] modelos =
    {
        "Gol", "Mobi", "Fox", "Fusca", "C3", "Captur",
        "i30", "Fiesta", "Fit", "Picanto"
    };

    private static int anoAtual = (new GregorianCalendar()).get(Calendar.YEAR);

    public static String geraNome()
    {
        return nomes[geraNumero.nextInt(10)];
    }

    public static String geraPlaca()
    {
        String placa = "";
        for (int i = 0; i < 3; i++)
        {
            placa += (char) (65 + geraNumero.nextInt(26)); // ASCII 65 = A
        }
        placa += "-";
        for (int i = 0; i < 4; i++)
        {
            placa += (char) (48 + geraNumero.nextInt(10)); // ASCII 48 = 0
        }

        return placa;
    }

    public static String geraModelo()
    {
        return modelos[geraNumero.nextInt(10)];
    }

    public static int geraAno()
    {
        int redutor = geraNumero.nextInt(10);
        if (redutor > 8)
        {
            redutor = geraNumero.nextInt(60);
        } else
        {
            redutor = geraNumero.nextInt(20);
        }

        return anoAtual - redutor;
    }

}
