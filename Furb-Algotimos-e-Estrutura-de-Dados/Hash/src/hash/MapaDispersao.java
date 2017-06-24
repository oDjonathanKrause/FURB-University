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
    private int tamanhoTabela;

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
        this.tamanhoTabela = procuraPrimo(tamanho, 10);

        System.out.println("Melhor tamanho pra tabela: " + this.tamanhoTabela);
        
        // Cria a tabela com o melhor tamanho
        this.tabela = new ListaEncadeada[this.tamanhoTabela];
        
    }
    
    /**
     * Calcula o hash da chave passada como parametro compactando o resultado para um intervalo aceitável para ser armazenado na tabela
     * @return hash
     */
    private int calculaHash(K chave)
    {
        // Calcula hash como o retorno de hashCode() resto de divisão com o tamanho da tabela
        int hash = chave.hashCode() % this.tamanhoTabela;
        
        // Se o resultado for negativo, utiliza ele positivo
        if(hash < 0)
            hash = hash * -1;
        
        System.out.println("chave " + chave + " hash: " + hash);
        
        return hash;
    }
    
    
    /**
     * Armazenar o objeto T no mapa de dispersão de acordo com o valor da chave 
     *
     * @param chave - K valor da chave
     * @param objeto - T Objeto que será inserido
     */
    public void inserir(K chave, T objeto)
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
    public ListaEncadeada buscar(K chave)
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
            // Retorna lista da posição x
            return tabela[hash];
        }
    }
    
    /**
     * Remove do mapa de dispersão o objeto que possui a mesma chave de busca da chave fornecida como argumento. 
     * @param chave - Chave que será removida
     * @return objeto removido. Caso não localize, deve retornar null.
     */
    public T remover(K chave)
    {
        // Se a chave for nula, retorna. Erro.
        if(chave == null)
            return null;
        
        // Calcula o hash da chave passada por parametro
        int hash = calculaHash(chave);
        
        if(tabela[hash] == null)
            return null;
        else
        {
            // Pega o objeto da posição
            T objetoRemovido = (T) tabela[hash];
            
            // Limpa posição
            tabela[hash] = null;
            
            // Retorna objeto removido
            return objetoRemovido;
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
    
    /**
     * 
     * @param chave
     * @return 
     */
    public int verificaColisao(K chave)
    {
        // Calcula hash da chave
        int hash = calculaHash(chave);
        
        // Verifica se a posição esta vazia
        if(tabela[hash] == null)
            return 0;
        else // Se não estiver, retorna o tamanho da lista na posição, será o número de colisões
        {
            return tabela[hash].getTamanho();
        }
    }
    
    /**
     * @return Retorna lista encadeada da posição que mais tem colisões na tabela
     */
    public ListaEncadeada posicaoComMaisColisoes()
    {
        ListaEncadeada listaMaisColisoes = new ListaEncadeada();
        
        // Percorre tabela
        for(int i = 0; i < tamanhoTabela; i++)
        {
            // Se a posição não for nula
            if(tabela[i] != null)
            {
                // Se a quantidade de elementos (colisões) na posição for maior do que a maior já calculada
                if(tabela[i].getTamanho() > listaMaisColisoes.getTamanho())
                {
                    // Seta a posição/lista atual como a mais colidida
                    listaMaisColisoes = tabela[i];
                }
            }
        }
        
        System.out.println("A posição com mais colisões tem " + listaMaisColisoes.getTamanho() + " itens na lista");
        
        return listaMaisColisoes;
    }

}
