package grafos;

/**
 *
 * @author Djonathan
 */
public class Vertice
{
    private int indiceVertice;
    private String nomeVertice;

    public Vertice(int indiceVertice, String nomeVertice)
    {
        this.indiceVertice = indiceVertice;
        this.nomeVertice = nomeVertice;
    }
    
    // Gets e sets
    public int getIndiceVertice()
    {
        return indiceVertice;
    }

    public void setIndiceVertice(int indiceVertice)
    {
        this.indiceVertice = indiceVertice;
    }

    public String getNomeVertice()
    {
        return nomeVertice;
    }

    public void setNomeVertice(String nomeVertice)
    {
        this.nomeVertice = nomeVertice;
    }

    
}
