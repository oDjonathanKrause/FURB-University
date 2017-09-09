package grafos.trabalho1;

import java.util.ArrayList;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause
 */
public class Vertice
{

    private String nome;
    private ArrayList<Vertice> vAdj;
    private int grau;
    private char cor;

    public Vertice(String nome)
    {
        this.nome = nome;
        vAdj = new ArrayList<Vertice>(3);
        grau = 0;
        cor = 'b';
    }

    public void addAdjacencia(Vertice vertice)
    {
        vAdj.add(vertice);
        if (vertice.equals(this))
        {
            grau++;
        }
        grau++;
    }

    public ArrayList<Vertice> getAdjacencia()
    {
        return vAdj;
    }

    public String getNome()
    {
        return nome;
    }

    public char getCor()
    {
        return cor;
    }

    public void setCor(char cor)
    {
        this.cor = cor;
    }

    public int getGrau()
    {
        return grau;
    }

    @Override
    public String toString()
    {
        return nome;
    }
}
