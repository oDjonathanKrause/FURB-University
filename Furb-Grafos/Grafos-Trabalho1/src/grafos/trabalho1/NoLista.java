
package grafos.trabalho1;

/**
 *
 * @author Carlos Henrique Stapait Junior, Djonathan Krause 
 */

public class NoLista {
    private int info;
    private NoLista proximoNo;

    public NoLista(int info) {
        this.setInfo(info);
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NoLista getProximoNo() {
        return proximoNo;
    }

    public void setProximoNo(NoLista proximoNo) {
        this.proximoNo = proximoNo;
    }

    
    
    
    
}

