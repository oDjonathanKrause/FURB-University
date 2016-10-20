package Controle.generico;

/**
 *
 * @author Tamires
 */
public class Classes {

    private final int id;

    public Classes(int id) {
        this.id = id;
    }

    public String getClasse() 
    {
        String classe = "";
        switch (id) 
        {
            case 3:
            case 4:
            case 5:
            case 6: 
            {
                classe = "Identificador";
                break;
            }
            case 7: { //87
                classe = "constante int";
                break;
            }
            case 8: {  //87,87
                classe = "constante float";
                break;
            }
            case 9:    //"oi"
            {
                classe = "constante string";
                break;
            }

            case 10:  //and
            case 11:  //false
            case 12:  //if
            case 13:  //in
            case 14:  //isFalseDo
            case 15:  //isTrueDo
            case 16:  //main
            case 17:  //module
            case 18:  //not
            case 19: //or
            case 20:  //true
            case 21:  //out
            case 22:  //while
            {
                classe = "palavra reservada";
                break;
            }
            
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            {
                classe = "simbolo especial";
                break;
            }
                
            
        }
        return classe;
    }

}

