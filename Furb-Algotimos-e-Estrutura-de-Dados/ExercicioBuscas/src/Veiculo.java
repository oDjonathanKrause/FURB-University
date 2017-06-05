
/**
 *
 * @author Marcel
 */
public class Veiculo implements Comparable
{

    private String placa;
    private String modelo;
    private int ano;
    private String proprietario;

    public Veiculo(String placa, String modelo, int ano, String proprietario)
    {
        this.setPlaca(placa);
        this.setModelo(modelo);
        this.setAno(ano);
        this.setProprietario(proprietario);
    }

    public String toString()
    {
        return "Placa: " + this.getPlaca()
                + ", " + this.getModelo()
                + ", " + this.getAno()
                + ", " + this.getProprietario();
    }

    @Override
    public int compareTo(Object o)
    {
        return (this.getPlaca().compareTo(((Veiculo) o).getPlaca()));
    }

    public String getPlaca()
    {
        return placa;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public int getAno()
    {
        return ano;
    }

    public void setAno(int ano)
    {
        this.ano = ano;
    }

    public String getProprietario()
    {
        return proprietario;
    }

    public void setProprietario(String proprietario)
    {
        this.proprietario = proprietario;
    }

}
