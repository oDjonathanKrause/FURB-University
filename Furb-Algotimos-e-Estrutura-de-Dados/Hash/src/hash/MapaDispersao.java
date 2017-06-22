package hash;
import ListaEncadeada.ListaEncadeada;
import ListaEncadeada.ElementoLista;

/**
 *
 * @author Djonathan krause
 * @param <K> - Tipo da chave
 * @param <T> - Valor
 */
public class MapaDispersao<K, T>
{

    private ListaEncadeada<K, T>[] tabela;

    /**
     * Cria um mapa com vetor encapsulado de tamanho calculado a partir das
     * regras de boas práticas
     *
     * @param qtd - qtd de elementos
     */
    public MapaDispersao(int qtd)
    {
        // Determina o tamanho como 50% da quantidade de elementos
        int tamanho = (int) (qtd * 0.5);

        // Calcula o melhor tamanho para o vetor
        int melhorTamanho = procuraPrimo(tamanho, 10);

        System.out.println("Melhor tamanho: " + melhorTamanho);

        int mapa[] = new int[melhorTamanho];

    }

    /**
     * Delega para a classe K o cálculo do hash, reusando o método hashCode() do
     * objeto recebido como argumento (chave). Entretanto, o método
     * calcularHash() deverá compactar o valor retornado por hashCode() para um
     * intervalo aceitável para ser armazenado no vetor tabela.
     *
     * @return hash
     */
    private int calculaHash(K chave)
    {
        return 1;
    }

    /**
     * Armazenar o objeto T no mapa de dispersão de acordo com o valor da chave
     * (K)
     *
     * @param K - valor da chave
     * @param T - Objeto que será inserido
     */
    private void inserir(K chave, T objeto)
    {
        // Se a chave ou o objeto forem nulos, retorna. Erro.
        if (chave == null || objeto == null)
            return;    

        // Calcula o hash 
        int hash = calculaHash(chave);
        
        // Cria elemento e seta valor
        ElementoLista<T> elemento = new ElementoLista<>();
        elemento.setElemento(objeto);
            
        // Se a posição do hash estiver vazia, insere
        if (tabela[hash] == null)
        {
            // Cria lista
            ListaEncadeada<K, T> lista = new ListaEncadeada<>();
            
            // Insere objeto na lista
            lista.insere((T) elemento);
            
            // Insere lista na tabela
            tabela[hash] = lista;
        }
        else // Se a posição não estiver vazia, insere elemento na lista que já esta la
        {
            // Lista auxiliar recebe a lista que esta na posição x
            ListaEncadeada<K, T> listaAux = tabela[hash];
            
            // Insere objeto na lista aux
            listaAux.insere((T) elemento);
            
            // Insere a listaAux na posição x
            tabela[hash] = listaAux;
        }
    }
    
    /**
     * Procura objeto que esta na posição da chave informada
     * @param chave - chave onde o objeto esta
     * @return null se não encontrar o objeto, return objeto se encontrar
     */
    private T buscar(K chave)
    {
        // Chave nula, erro.
        if(chave == null)
            return null;
        
        // Calcula o hash 
        int hash = calculaHash(chave);

        // Se não encontrar nada na posição, retorna null
        if(tabela[hash] == null)
            return null;
        else
        {
            // Cria objeto e pega valor da posição x
            ElementoLista<T> objeto = new ElementoLista<>();
            objeto.setElemento((T) tabela[hash]);
            
            return (T) objeto;
        }
    }

    /**
     * Verifica qual é o número primo mais próximo do número informado
     *
     * @param numero - número informado
     * @param limiteAceito - limite da procura
     * @return melhor tamanho do array
     */
    private int procuraPrimo(int numero, int limiteAceito)
    {
        boolean isPrimo;
        limiteAceito = numero + limiteAceito;

        // Faz loop entre o número e o limite aceito
        for (int i = numero; i < limiteAceito; i++)
        {
            isPrimo = true;

            // Verifica se o número atual é primo
            for (int j = 2; j < i; j++)
            {
                // Se for primo, para o loop e seta a flag
                if (i % j == 0)
                {
                    isPrimo = false;
                    break;
                }
            }

            // Se a flag estiver ligada, retorna valor
            if (isPrimo)
            {
                return i;
            }
        }

        // Se não encontrou, retorna o próprio numero
        return numero;
    }
}


/*
Link: http://ava.furb.br/ava/resources/tela_view.php?ds_diretorio=3436627&nm_arquivo=AED____Trabalho____Mapa_de_dispersao.pdf
Ajuda: http://www.javamadesoeasy.com/2015/02/hashmap-custom-implementation-put-get.html

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
