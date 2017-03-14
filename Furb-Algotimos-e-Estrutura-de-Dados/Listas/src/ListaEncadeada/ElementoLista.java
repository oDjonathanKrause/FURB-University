package ListaEncadeada;

/**
 *
 * @author djonathan.krause
 * @param T - Tipo de dado genérico
 */
public class ElementoLista<T>
{

    private T elemento;
    private ElementoLista proxElemento = null;
    
    // Construtor vazio
    public ElementoLista() {}

    /**
     * Construtor da classe
     *
     * @param elemento
     * @param proxElemento
     */
    public ElementoLista(T elemento, ElementoLista proxElemento)
    {
        this.setElemento(elemento);
        this.setProxElemento(proxElemento);
    }

    /**
     * Gets e sets da classe ElementoLista
     */
    public T getElemento()
    {
        return elemento;
    }

    public void setElemento(T elemento)
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
