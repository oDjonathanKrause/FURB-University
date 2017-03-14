package ListaEncadeada;

/**
 *
 * @author djonathan.krause
 */
public class ElementoLista
{

    private String elemento;
    private ElementoLista proxElemento = null;

    /**
     * Construtor da classe
     *
     * @param elemento
     * @param proxElemento
     */
    public ElementoLista(String elemento, ElementoLista proxElemento)
    {
        this.setElemento(elemento);
        this.setProxElemento(proxElemento);
    }

    /**
     * Gets e sets da classe ElementoLista
     */
    public String getElemento()
    {
        return elemento;
    }

    public void setElemento(String elemento)
    {
        this.elemento = elemento;
    }

    public ElementoLista getProxElemento()
    {
        return proxElemento;
    }

    public void setProxElemento(ElementoLista proxElemento)
    {
        this.proxElemento = proxElemento;
    }
}
