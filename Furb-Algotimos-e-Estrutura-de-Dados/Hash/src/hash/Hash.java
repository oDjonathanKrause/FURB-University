package hash;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author drkrause
 */
public class Hash
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        MapaDispersao<String, Veiculo> mp = new MapaDispersao(10000);

        // geração dos dados
        String nome, placa, modelo;
        int ano;

        for (int i = 0; i < 5000; i++)
        {
            nome = GeradorAleatorio.geraNome() + " " + i;
            placa = GeradorAleatorio.geraPlaca();
            modelo = GeradorAleatorio.geraModelo();
            ano = GeradorAleatorio.geraAno();
            Veiculo v = new Veiculo(placa, modelo, ano, nome);

            mp.inserir(v.getPlaca(), v);
            System.out.println("número de colisões " + mp.verificaColisao(v.getPlaca()) + "\n");
        }

        mp.posicaoComMaisColisoes();
    }
}

class GeradorAleatorio
{

    private static Random geraNumero = new Random();
    private static String[] nomes =
    {
        "José", "Maria", "Pedro", "João", "Mario", "Paulo", "Paula", "Sandra", "André", "Carla"
    };
    private static String[] modelos =
    {
        "Gol", "Mobi", "Fox", "Fusca", "C3", "Captur", "i30", "Fiesta", "Fit", "Picanto"
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



/*
Link: http://ava.furb.br/ava/resources/tela_view.php?ds_diretorio=3436627&nm_arquivo=AED____Trabalho____Mapa_de_dispersao.pdf

* O construtor MapaDispersao(int) deve criar uma mapa com vetor encapsulado, cujo
tamanho será calculado com base no argumento quantidade, que é a quantidade estimada
de elementos a serem inseridos. Considere as boas práticas para determinar o tamanho deste
vetor.
* O método privado calcularHash() deve delegar para a classe K o cálculo do hash,
reusando o método hashCode() do objeto recebido como argumento (chave). Entretanto,
o método calcularHash() deverá compactar o valor retornado por hashCode() para um
intervalo aceitável para ser armazenado no vetor tabela.
* O método inserir(K, T) deve armazenar o objeto T, fornecido como argumento, no mapa
de dispersão de acordo com o valor da chave (K).
* O método remover(K) deve remover do mapa de dispersão o objeto que possui a mesma
chave de busca da chave fornecida como argumento, retornando este objeto. Caso não
localize, deve retornar null.
* O método buscar(K) deve procurar no mapa de dispersão um objeto que possua chave de
busca igual à fornecida como argumento. Como resultado do seu processamento, o método
deve retornar o objeto localizado ou null caso não localize

 */
