package ListaDuplamenteEncadeada;

import ListaEncadeada.ElementoLista;
import listas.InterfaceListas;

/**
 * @author djonathan.krause
 */
public class ListaDuplamenteEncadeada<T> implements InterfaceListas<T>
{
    private ElementoLista<T> primeiro;
    private ElementoLista<T> ultimo;
    private int qtdElementos = 0;

    @Override
    public void insere(T x)
    {
        ElementoLista novo = new ElementoLista();
        novo.setElemento(x);

        if (this.estaVazia())
        {
            primeiro = novo;
        } else
        {
            ultimo.setProxElemento(novo);
            novo.setElementoAnt(ultimo);
        }
        ultimo = novo;
        qtdElementos++;

    }

    @Override
    public boolean estaVazia()
    {
        return qtdElementos == 0;
    }

    @Override
    public void insere(T x, int p)
    {
        if (p >= 0 && p <= this.qtdElementos)
        {
            if (p == this.qtdElementos)
            {// ultima posi��o
                this.insere(x);
            } else
            {
                ElementoLista novo = new ElementoLista();
                novo.setElemento(x);
                ElementoLista elemE;
                if (p == 0)
                { // primeira posição
                    novo.setProxElemento(primeiro);
                    primeiro.setElementoAnt(novo);
                    primeiro = novo;
                } else
                { // posição intermediaria
                    elemE = this.consultaInterno(p - 1);
                    novo.setProxElemento(elemE.getProxElemento());
                    novo.setElementoAnt(elemE);
                    novo.getProxElemento().setElementoAnt(novo);
                    elemE.setProxElemento(novo);
                }
                qtdElementos++;
            }
        }
    }

    private ElementoLista<T> consultaInterno(int p)
    {
        if (p >= 0 && p < this.qtdElementos)
        {  // posi��o procurada � v�lida
            ElementoLista proximo = primeiro;

            for (int i = 0; i < p; i++)
            {
                proximo = proximo.getProxElemento();
            }
            return proximo;
        } else
        {
            return null;
        }

    }

    @Override
    public T retira(int p)
    {
        if (p == 0)
        {
            T retorno = this.primeiro.getElemento();
            this.primeiro = primeiro.getProxElemento();
            this.primeiro.setElementoAnt(null);
            qtdElementos--;
            return retorno;
        } else if (p > 0 && p < qtdElementos)
        {
            ElementoLista<T> elementoRemovido = this.consultaInterno(p);
            elementoRemovido.getElementoAnt().setProxElemento(elementoRemovido.getProxElemento());
            elementoRemovido.getProxElemento().setElementoAnt(elementoRemovido.getElementoAnt());
            return elementoRemovido.getElemento();
        } else if (p == qtdElementos)
        {
            T retorno = this.ultimo.getElemento();
            this.ultimo = this.ultimo.getElementoAnt();
            this.ultimo.setProxElemento(null);
            qtdElementos--;
            return retorno;
        }
        return null;
    }

    @Override
    public int localiza(T x)
    {
        ElementoLista<T> elementoLista = primeiro;
        int i = 0;
        while ((elementoLista != null))
        {
            if (elementoLista.getElemento().equals(x))
            {
                return i;
            }
            i++;
            elementoLista = elementoLista.getProxElemento();
        }

        return -1;
    }

    @Override
    public int getTamanho()
    {
        return qtdElementos;
    }

    @Override
    public String imprime()
    {
        String retorno = "[";
        ElementoLista proximo = primeiro;

        while (proximo != null)
        {
            retorno += proximo.getElemento()+ "; ";
            proximo = proximo.getProxElemento();
        }

        try
        {
            // para retirar a última vírgula e espaço
            retorno = retorno.substring(0, retorno.length() - 2);
            return retorno + "]";
        } catch (StringIndexOutOfBoundsException strExc)
        {
            return "[]";
        }
    }

    @Override
    public T consulta(int p)
    {
        return this.consultaInterno(p).getElemento();
    }

}