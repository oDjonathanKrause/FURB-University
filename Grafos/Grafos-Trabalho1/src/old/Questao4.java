/*
    4. (1,5) Dada a matriz de adjacência do grafo de ordem n, você deve implementar um programa
    que responda as seguintes perguntas:
    a. Qual é o tipo do grafo (dirigido ou não, simples ou multigrafo, regular, completo, nulo ou bipartido)
    nome do método: tipoDoGrafo
    parâmetro de entrada: matriz de adjacência
    retorno: String contendo o tipo do grafo
    b. Quantas arestas esse grafo possui? Liste o conjunto de arestas.
    nome do método: arestasDoGrafo
    parâmetro de entrada: matriz de adjacência
    retorno: String com a quantidade e o conjunto de arestas
    c. Qual é o grau de cada vértice. Liste a sequência de graus
    nome do método: grausDoVertice
    parâmetro de entrada: matriz de adjacência
    retorno: String identificando o grau de cada vértice e por fim, a sequência de graus 
 */
package old;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause 
 */
public class Questao4
{     
        
    /**
     * Verifica o tipo do grafo (dirigido ou não, simples ou multigrafo, regular, completo, nulo ou bipartido)
     * @param matrizAdjacencia
     * @return String com o tipo do grafo

     * Multirafo = pelo meno um laço ou aresta paralela
     * Regular = Se todos os vértices tem o mesmo grau
     * Completo = Quando todos os vértices se relacionam (se é completo, é regular) (K)
     * Nulo = Grafo sem arestas (N)
     * Bipartido = Origem e destino da aresta tem que estar em conjuntos diferentes. Ímpares não são bipartidos.
     */
    public String tipoDoGrafo(int[][] matrizAdjacencia)
    {
        String retorno = "O grafo é: ";
        boolean isDirigidoFlag = isDirigido(matrizAdjacencia);

        retorno += isDirigido(matrizAdjacencia) ?  "\nDirigido;" : "\nNão dirigido;";
        retorno += isMultigrafo(matrizAdjacencia, isDirigidoFlag) ?  "\nMultigrafo;" : "\nSimples;";
        retorno += isRegular(matrizAdjacencia) ?  "\nRegular;" : "\nNão regular;";
        retorno += isCompleto(matrizAdjacencia, isDirigidoFlag) ?  "\nCompleto;" : "\nNão completo;";
        retorno += isNulo(matrizAdjacencia, isDirigidoFlag) ?  "\nNulo;" : "\nNão nulo;";
        retorno += isBipartido(matrizAdjacencia) ?  "\nBipartido;" : "\nNão bipartido;";
        
        return retorno;
    }
    
    /**
     * Verifica e lista a sequência de graus dos vértices do grafo
     * @param matrizAdjacencia
     * @param isDirigidoFlag
     * @return String com o grau de cada vétice e a sequência de graus
     */
    public String grausDoVertice(int[][] matrizAdjacencia, boolean isDirigidoFlag)
    {
        // Cria lista de vértices
        ArrayList<Integer> listaVertices = new ArrayList<>(matrizAdjacencia.length);
		
        // Mensagem de retorno
        StringBuilder retorno = new StringBuilder();
        int qtdGraus = 0;

        // Percorre matriz de adjacência
        if(isDirigidoFlag)
        {
            for (int linha = 0; linha < matrizAdjacencia.length; linha++) 
            {
                // Soma o valor da linhaXcoluna na qtd de graus
                for (int coluna = 0; coluna < matrizAdjacencia[linha].length; coluna++) 
                    qtdGraus += matrizAdjacencia[linha][coluna];
                
                // Add soma de vértices na lista
                listaVertices.add(qtdGraus);
                
                // Zera variável de graus do vétice
                qtdGraus = 0;
            }
        }
        // Se não for dirigido, percorre apenas uma diagonal da matriz de adjacência (considera a diagonal principal, por isso o coluna = linha no for)
        else 
        {
            for (int linha = 0; linha < matrizAdjacencia.length; linha++) 
            {
                // Soma o valor da linhaXcoluna na qtd de graus
                for (int coluna = linha; coluna < matrizAdjacencia[linha].length; coluna++) 
                {
                    // Se estiver na diagonal principal, conta o valor 2x (entrada e saída do vértice)
                    if (linha == coluna && matrizAdjacencia[linha][coluna] == 1)
                        qtdGraus++;
                    qtdGraus += matrizAdjacencia[linha][coluna];
                }
                
                // Add soma de vértices na lista
                listaVertices.add(qtdGraus);
                
                // Zera variável de graus do vétice
                qtdGraus = 0;
            }
        }

        // Ordena vértices
        Collections.sort(listaVertices);

        // Add vértices num string separando por espaços
        listaVertices.forEach(vertice -> retorno.append(vertice + " "));

        // Retorna lista em String
        return retorno.toString();
    }
    
    
    /**
     * Verifica se é um grafo simples ou multigrafo 
     * Onde um multrigrafo tem pelo menos um laço ou aresta paralela
     * @param matrizAdjacencia
     * @param isDirigidoFlag se o grafo é dirigido
     * @return true se for multigrafo
     */
    private boolean isMultigrafo(int[][] matrizAdjacencia, boolean isDirigidoFlag)
    {
        // Se for dirigido, percorre de maneira diferente
        if(isDirigidoFlag)
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
            {
                for(int coluna = 0; coluna < matrizAdjacencia[linha].length; coluna++)
                {
                    // Se tiver alguma aresta paralela, é multigrafo
                    if(matrizAdjacencia[linha][coluna] > 1)
                        return true;
                    // Se for uma diagonal e tiver algum relacionamento, é multrigrafo
                    if(linha == coluna && matrizAdjacencia[linha][coluna] > 0)
                        return true;
                }
            }  }
        // Se não for dirigido, percorre apenas uma diagonal da matriz de adjacência (considera a diagonal, por isso coluna = linha no for)
        else
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
            {
                for(int coluna = linha; coluna < matrizAdjacencia[linha].length; coluna++)
                {
                    // Se tiver alguma aresta paralela, é multigrafo
                    if(matrizAdjacencia[linha][coluna] > 1)
                        return true;
                    // Se for uma diagonal e tiver algum relacionamento, é multrigrafo
                    if(linha == coluna && matrizAdjacencia[linha][coluna] > 0)
                        return true;
                }
            }  
        }
        return false;
    }

    /**
     * Verifica se o grafo é regular, ou seja, se todos os vértices tem o mesmo grau.
     * @param matrizAdjacencia
     * @return true caso seja regular
     */
    private boolean isRegular(int[][] matrizAdjacencia)
    {
        int soma = 0, ref = 0;
	for (int i = 0; i < matrizAdjacencia.length; i++) 
        {
            ref = soma;
            soma = 0;
            
            for (int j = 0; j < matrizAdjacencia.length; j++) 
            {
                if (i == j)
                    if (matrizAdjacencia[i][j] == 1)
                        soma++;
                soma += matrizAdjacencia[i][j];
            }
            if (i > 0 && ref != soma)
		return false;
	}
        return true;
    }

    /**
     * Verifica se o grafo é completo.
     * Caso algum vértice tiver o valor 0, este vértice não tem relacionamento, então não é completo
     * @param matrizAdjacencia
     * @param isDirigidoFlag flag para determinar modo de percorrimento da matriz
     * @return true se for completo
     */
    private boolean isCompleto(int[][] matrizAdjacencia, boolean isDirigidoFlag)
    {
        // Se for dirigido, percorre a matriz inteira
        if(isDirigidoFlag)
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
                for(int coluna = 0; coluna < matrizAdjacencia[linha].length; coluna++)
                    // Se não tiver o relacionamento, não é completo
                    if(matrizAdjacencia[linha][coluna] == 0)
                        return false;
        }
        // Se não for dirigido, percorre apenas uma diagonal da matriz de adjacência (desconsidera a diagonal principal, por isso o coluna = linha + 1 no for)
        else
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
                for(int coluna = linha + 1; coluna < matrizAdjacencia[linha].length; coluna++)
                    // Se não tiver o relacionamento, não é completo
                    if(matrizAdjacencia[linha][coluna] == 0)
                        return false;
        }
        return true;
    }

    /**
     * Verifica se o grafo é nulo. 
     * Percorre a matriz de adjacência, se encontrar algum valor diferente de zero, não é nulo.
     * @param matrizAdjacencia
     * @param isDirigidoFlag flag para determinar moto de percorrimento da matriz
     * @return true se for nulo
     */
    private boolean isNulo(int[][] matrizAdjacencia, boolean isDirigidoFlag)
    {
        // Se for dirigido, percorre a matriz toda
        if(isDirigidoFlag)
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
                for(int coluna = 0; coluna < matrizAdjacencia[linha].length; coluna++)
                    // Se tiver algum relacionamento, não é nulo
                    if(matrizAdjacencia[linha][coluna] != 0)
                        return false;
        }
        // Se não for dirigido, percorre apenas uma diagonal da matriz de adjacência (desconsidera a diagonal principal, por isso o coluna = linha + 1 no for)
        else
        {
            for(int linha = 0; linha < matrizAdjacencia.length; linha++)
                for(int coluna = linha + 1; coluna < matrizAdjacencia[linha].length; coluna++)
                    // Se tiver algum relacionamento, não é nulo
                    if(matrizAdjacencia[linha][coluna] != 0)
                        return false;
        }
        return true;
    }
    
    /**
     * Verifica se um grafo é dirigido.
     * Se as diagonais opostas forem iguais, é dirigido.
     * Percorre e compara apenas uma diagonal da matriz de adjacência (desconsidera a diagonal principal, por isso o coluna = linha + 1 no for)
     * Se o valor linhaXcoluna for diferente do colunaXlinha, é dirigido
     * @param matrizAdjacencia
     * @return true se for dirigido
     */
    public boolean isDirigido(int[][] matrizAdjacencia)
    {
        for(int linha = 0; linha < matrizAdjacencia.length; linha++)
            for(int coluna = linha + 1; coluna < matrizAdjacencia[linha].length; coluna++)
                if(matrizAdjacencia[linha][coluna] != matrizAdjacencia[coluna][linha])
                    return true;
        return false;
    }
    
    /**
     * Verifica a quantidade de aresas do grafo
     * @param matrizAdjacencia
     * @return String com a quantidade e o conjunto de arestas do grafo
     */
    public String arestasDoGrafo(int[][] matrizAdjacencia)
    {
        // Verifica se a matriz é válida
        if (matrizAdjacencia == null || matrizAdjacencia.length != matrizAdjacencia[0].length || matrizAdjacencia.length == 0) 
            throw new IllegalArgumentException("Matriz Inválida");
        
        // Declara string de retorno
        String retorno = "E = {";
        
        int qtdArestas = 0;
        boolean aux = false;
        
        // Percorre a matriz
        for (int linha = 0; linha < matrizAdjacencia.length; linha++) 
        {
            for (int coluna = linha; coluna < matrizAdjacencia[linha].length; coluna++) 
            {
                if (matrizAdjacencia[linha][coluna] > 0) 
                {
                    if (aux) 
                        retorno += ", ";
                    
                    qtdArestas += matrizAdjacencia[linha][coluna];

                    // Verifica ligação das arestas
                    for (int k = 0; k < matrizAdjacencia[linha][coluna]; k++) 
                    {
                        retorno += "(" + linha + ", " + coluna + ")";
                        if (k + 1 != matrizAdjacencia[linha][coluna]) 
                            retorno += ", ";
                    }
                    aux = true;
                }
            }

            if (linha + 1 == matrizAdjacencia.length)
                retorno += "}";
        }
        
        return "Quantidade de arestas: " + qtdArestas + "\n" + retorno;        
    }
    
    /**
     * Verifica se o grafo pe bipartido, ou seja, se a origem e destino da aresta estão em conjuntos diferentes. 
     * @param matrizAdjacencia
     * @return true se for bipartido
     */
    private boolean isBipartido(int[][] matrizAdjacencia)
    {
        // Se for nulo, não é bipartido
        if(isNulo(matrizAdjacencia, isDirigido(matrizAdjacencia)))
            return false;
        
        // Cria lista de grupos
        Lista grupoA = new Lista();
        Lista grupoB = new Lista();
        
        
        for (int linha = 0; linha < matrizAdjacencia.length; linha++) 
        {
            for (int coluna = 0; coluna < matrizAdjacencia[linha].length; coluna++) 
            {
                if (matrizAdjacencia[linha][coluna] > 0) 
                {
                    if (linha == coluna) 
                        return false;
                    if (!isBipartidoAux(grupoA, grupoB, linha, coluna)) 
                        return false;
                }
            }
        }
        return true;
    }
    
    private static boolean isBipartidoAux(Lista grupoA, Lista grupoB, int i, int j) 
    {
        if (grupoA.buscarNo(i) == null && grupoB.buscarNo(i) == null && grupoA.buscarNo(j) == null && grupoB.buscarNo(j) == null) 
        {
            grupoA.inserirNo(i);
            grupoB.inserirNo(j);
            return true;
        }
        
        if (grupoA.buscarNo(i) != null) 
        {
            if (grupoA.buscarNo(j) != null) 
                return false;
            else 
            {
                if (grupoB.buscarNo(j) == null) 
                    grupoB.inserirNo(j);
                return true;
            }
        }
        
        if (grupoB.buscarNo(i) != null) 
        {
            if (grupoB.buscarNo(j) != null) 
                return false;
            else 
            {
                if (grupoA.buscarNo(j) == null) 
                    grupoA.inserirNo(j);
                return true;
            }
        }
        
        return false;
    }
    
    
}
