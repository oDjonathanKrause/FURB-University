package Controle.semantico;

import Controle.generico.Token;
import Controle.generico.Constants;
import Controle.sintatico.Tipo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Tamires, Djonathan Krause
 */
public class Semantico implements Constants {

    private StringBuilder codigoObjeto = new StringBuilder("");
    private Stack<Tipo> pilhaTipos = new Stack();
    private Token token;
    private String operadorRelacional;
    private ArrayList<String> listaId = new ArrayList();
    private HashMap<String, Integer> tabelaSimbolos = new HashMap();
    private String identificador;
    private boolean expressao1;
    private int ctLabel = 0;
    private String expressaoId;

    public void executeAction(int action, Token token) throws SemanticError {
        System.out.println("Ação #" + action + ", Token: " + token);

        try {
            this.token = token;
            Tipo tipo;

            switch (action) {
                case 1: {
                    acaoUm();
                    break;
                }
                case 2: {
                    acaoDois();
                    break;
                }
                case 3: {
                    acaoTres();
                    break;
                }
                case 4: {
                    acaoQuatro();
                    break;
                }
                case 5: {
                    codigoObjeto.append("\n ldc.i8 ");
                    codigoObjeto.append(token.getLexeme());
                    pilhaTipos.push(new Tipo(Tipo.cte_integer));
                    break;
                }
                case 6: {
                    codigoObjeto.append("\n ldc.r8 ");
                    codigoObjeto.append(token.getLexeme().replace(",", "."));
                    pilhaTipos.push(new Tipo(Tipo.cte_float));
                    break;
                }
                case 7: {
                    verificaTipo();
                    break;
                }
                case 8: {
                    acaoOito();
                    break;
                }
                case 9: {
                    acaoNove();
                    break;
                }
                case 10: {
                    operadorRelacional = token.getLexeme();
                    break;
                }
                case 11: {
                    tipo = pilhaTipos.pop();
                    codigoObjeto.append("\n call void [mscorlib]System.Console::Write(");
                    codigoObjeto.append(tipo.toString());
                    codigoObjeto.append(") ");
                    break;
                }
                case 12: {
                    acaoDoze();
                    break;
                }
                case 13: {
                    acaoTreze();
                    break;
                }
                case 14: {
                    tipo = pilhaTipos.pop();
                    if (tipo.getTipo() != Tipo.cte_bool) {
                        exibeMensagemErro(8, "", "", token);
                    }
                    pilhaTipos.push(new Tipo(Tipo.cte_bool));
                    expressao1 = true;
                    codigoObjeto.append("\n and ");
                    break;
                }
                case 15: {
                    tipo = pilhaTipos.pop();
                    if (tipo.getTipo() != Tipo.cte_bool) {
                        exibeMensagemErro(8, "", "", token);
                    }
                    pilhaTipos.push(new Tipo(Tipo.cte_bool));
                    expressao1 = true;
                    codigoObjeto.append("\n or ");
                    break;
                }
                case 16: {
                    pilhaTipos.push(new Tipo(Tipo.cte_bool));
                    codigoObjeto.append("\n ldc.i4.1 "); //true
                    break;
                }
                case 17: {
                    pilhaTipos.push(new Tipo(Tipo.cte_bool));
                    codigoObjeto.append("\n ldc.i4.0 "); //false
                    break;
                }
                case 18: {
                    acaoDezoito();
                    break;
                }
                case 19: {
                    codigoObjeto.append("\n ldstr ");
                    codigoObjeto.append(token.getLexeme());
                    pilhaTipos.add(new Tipo(Tipo.cte_string));
                    break;
                }
                case 21: {
                    listaId.add(token.getLexeme());
                    break;
                }
                case 22: {
                    acaoVinteDois();
                    break;
                }
                case 23: {
                    acaoVinteTres();
                    break;
                }
                case 24: {
                    acaoVinteQuatro();
                    break;
                }
                case 25: {
                    acaoVinteCinco();
                    break;
                }
                case 26: {
                    acaoVinteSeis();
                    break;
                }
                case 27: {
                    acaoVinteSete();
                    break;
                }
                case 28: {
                    acaoVinteOito();
                    break;
                }
                case 29: {
                    acaoVinteNove();
                    break;
                }
                case 30: {
                    acaoTrinta();
                    break;
                }
                case 31: {
                    acaoTrintaUm();
                    break;
                }
                case 32: {
                    acaoTrintaDois();
                    break;
                }
                case 33: {
                    acaoTrintaTres();
                    break;
                }
                case 34: {
                    acaoTrintaQuatro();
                    break;
                }
                case 35: {
                    acaoTritaCinco();
                    break;
                }
                case 36: {
                    acaoTrintaSeis();
                    break;
                }
                case 37: {
                    identificador = token.getLexeme();
                    break;
                }
            }
        } catch (SemanticError se) {
            throw se;
        } catch (Exception e) {
            throw new SemanticError("Ocorreu um erro ao executar o método executeAction da classe Semantico");
        }
    }

    public String getCodigoObjeto() {
        return codigoObjeto.toString();
    }

    private void verificaTipos(int empilha) throws SemanticError {
        Tipo tipo1 = pilhaTipos.pop();
        Tipo tipo2 = pilhaTipos.pop();
        if (tipo1.getTipo() == Tipo.cte_bool
                || tipo1.getTipo() == Tipo.cte_string
                || tipo2.getTipo() == Tipo.cte_bool
                || tipo2.getTipo() == Tipo.cte_string) {
            exibeMensagemErro(6, "", "", token);
        }
        if (empilha == 1
                || tipo1.getTipo() == Tipo.cte_float
                || tipo2.getTipo() == Tipo.cte_float) {
            Tipo tipo = new Tipo(Tipo.cte_float);
            pilhaTipos.push(tipo);
        } else if (empilha == 2
                || tipo1.getTipo() == Tipo.cte_integer
                || tipo2.getTipo() == Tipo.cte_integer) {
            Tipo tipo = new Tipo(Tipo.cte_integer);
            pilhaTipos.push(tipo);
        } else {
            exibeMensagemErro(7, tipo1.toString(), tipo2.toString(), token);
        }
    }

    private void verificaTipo() throws SemanticError {
        Tipo tipo = pilhaTipos.pop();
        if (tipo.getTipo() == Tipo.cte_float
                || tipo.getTipo() == Tipo.cte_integer) {
            pilhaTipos.push(tipo);
        } else {
            exibeMensagemErro(6, "", "", token);
        }
    }

    private int getTipoIdInt(String tipo) {
        int intTipo = -1;
        if ("int64".equals(tipo)) {
            intTipo = Tipo.cte_integer;
        } else if ("float64".equals(tipo)) {
            intTipo = Tipo.cte_float;
        } else if ("bool".equals(tipo)) {
            intTipo = Tipo.cte_bool;
        } else if ("string".equals(tipo)) {
            intTipo = Tipo.cte_string;
        }
        return intTipo;
    }

    private String getTipoId(String lexeme) {
        String tipo = "";
        if ("i_".equals(lexeme.substring(0, 2))) {
            tipo = "int64";
        } else if ("f_".equals(lexeme.substring(0, 2))) {
            tipo = "float64";
        } else if ("s_".equals(lexeme.substring(0, 2))) {
            tipo = "string";
        } else if ("b_".equals(lexeme.substring(0, 2))) {
            tipo = "bool";
        } else {
            tipo = lexeme;
        }
        return tipo;
    }

    private boolean existeId(String id) {
        return tabelaSimbolos.get(id) != null;
    }

    private String getClasseTipo(String tipo) {
        String classe = "";
        if ("int64".equals(tipo)) {
            classe = "Int64";
        } else if ("float64".equals(tipo)) {
            classe = "Double";
        } else if ("string".equals(tipo)) {
            classe = "String";
        } else if ("bool".equals(tipo)) {
            classe = "Boolean";
        }
        return classe;
    }

    private void acaoUm() throws SemanticError {
        verificaTipos(0);
        codigoObjeto.append("\n add ");
    }

    private void acaoDois() throws SemanticError {
        verificaTipos(0);
        codigoObjeto.append("\n sub ");
    }

    private void acaoTres() throws SemanticError {
        verificaTipos(0);
        codigoObjeto.append("\n mul ");
    }

    private void acaoQuatro() throws SemanticError {
        verificaTipos(1);
        if ("]".equals(token.getLexeme())) {
            codigoObjeto.append("\n ldelem int64 ");
            codigoObjeto.append("\n conv.r8 ");
        }
        codigoObjeto.append("\n div ");
    }

    private void acaoOito() throws SemanticError {
        verificaTipo();
        codigoObjeto.append("\n ldc.i8 -1 ");
        codigoObjeto.append("\n mul ");
    }

    private void acaoNove() throws SemanticError {
        Tipo tipo1 = pilhaTipos.pop();
        Tipo tipo2 = pilhaTipos.pop();
        if (tipo1.getTipo() == tipo2.getTipo()) {
            Tipo tipo = new Tipo(Tipo.cte_bool);
            pilhaTipos.push(tipo);
        } else {
            exibeMensagemErro(7, tipo1.toString(), tipo2.toString(), token);
        }
        switch (operadorRelacional) {
            case "==":
                codigoObjeto.append("\n ceq ");
                break;
            case "!=":
                codigoObjeto.append("\n ceq ");
                codigoObjeto.append("\n ldc.i4.1 ");
                codigoObjeto.append("\n xor ");
                break;
            case "<":
                codigoObjeto.append("\n clt ");
                break;
            case "<=":
                codigoObjeto.append("\n cgt ");
                codigoObjeto.append("\n ldc.i4.1");
                codigoObjeto.append("\n xor ");
                break;
            case ">":
                codigoObjeto.append("\n cgt ");
                break;
            case ">=":
                codigoObjeto.append("\n clt ");
                codigoObjeto.append("\n ldc.i4.1 ");
                codigoObjeto.append("\n xor ");
                break;
        }
    }

    /*
     @ação: 12
     @descrição: adiciona o main do programa.
     */
    private void acaoDoze() {
        codigoObjeto.append(".assembly extern mscorlib {} \n");
        codigoObjeto.append(".assembly codigo_objeto{} \n");
        codigoObjeto.append(".module codigo_objeto.exe \n");
        codigoObjeto.append(".class public _Principal { \n");
        codigoObjeto.append(".method static public void _principal() { \n");
        codigoObjeto.append(".entrypoint \n");
    }

    /*
     @ação: 13
     @descrição: final do programa.
     */
    private void acaoTreze() {
        codigoObjeto.append("\n ret \n} \n} ");
    }

    private void acaoDezoito() throws SemanticError {
        Tipo tipo = pilhaTipos.pop();
        if (tipo.getTipo() != Tipo.cte_bool) {
            exibeMensagemErro(9, "", "", token);
        }
        pilhaTipos.push(new Tipo(Tipo.cte_bool));
        codigoObjeto.append("\n ldc.i4.1 "); //not
        codigoObjeto.append("\n xor ");
    }

    private void acaoVinteDois() throws SemanticError {
        for (String id : listaId) {
            if (existeId(id)
                    && (tabelaSimbolos.get(id) == null
                    || tabelaSimbolos.get(id) == 0)) {
                exibeMensagemErro(5, id, "", token);
            }

            codigoObjeto.append("\n .locals( ");
            codigoObjeto.append(getTipoId(id));

            //verifica se não é um vetor
            Object tamanho = tabelaSimbolos.get(id);
            boolean vetor = false;
            if (tamanho == null) {
                tabelaSimbolos.put(id, 0);
            } else {
                codigoObjeto.append(" [ ] ");
                vetor = true;
            }
            codigoObjeto.append(" ");
            codigoObjeto.append(id);
            codigoObjeto.append(" ) ");

            if (vetor) {
                codigoObjeto.append("\n ldc.i8 ");
                codigoObjeto.append(tamanho);
                codigoObjeto.append("\n newarr [mscorlib]System.Int64\n stloc ");
                codigoObjeto.append(id);
            }
        }
        listaId.clear();
    }

    //Comando de leitura
    private void acaoVinteTres() throws SemanticError {
        int posId = 0;
        for (String id : listaId) {
            if (!existeId(id)) {
                exibeMensagemErro(1, id, "", token);
            }
            if (getTipoIdInt(getTipoId(id)) == Tipo.cte_bool) {
                exibeMensagemErro(8, id, "", token);
            }
            String tipo = getTipoId(id);
            String classe = getClasseTipo(tipo);
            //Vetor
            if (verificaIdRepetido(id, posId)) {
                if (tabelaSimbolos.get(id) != null
                        && tabelaSimbolos.get(id) > 0) {
                    codigoObjeto.append("\n stelem ");
                    codigoObjeto.append(getTipoId(id));
                } else {
                    codigoObjeto.append("\n call string [mscorlib]System.Console::ReadLine() ");
                    if (!"String".equals(classe)) {
                        codigoObjeto.append("\n call ");
                        codigoObjeto.append(tipo);
                        codigoObjeto.append("  [mscorlib]System.");
                        codigoObjeto.append(classe);
                        codigoObjeto.append("::Parse(string) ");
                        codigoObjeto.append("\n stloc ");
                        codigoObjeto.append(id);
                    }
                }
            }
            posId++;
        }
        listaId.clear();
    }

    private boolean verificaIdRepetido(String id, int position) {
        int count = 0;
        int posPrimeiro = -1;
        int i = 0;
        for (String identificador : listaId) {
            if (id.equals(identificador)) {
                count++;
                if (posPrimeiro == -1) {
                    posPrimeiro = i;
                }
            }
            i++;
        }
        if (count > 1) {
            if (position != posPrimeiro) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private void acaoVinteQuatro() throws SemanticError {
        String id = token.getLexeme();
        if (!"]".equals(id)) {
            if ((tabelaSimbolos.get(expressaoId) == null
                    || tabelaSimbolos.get(expressaoId) == 0)
                    && !existeId(id)) {
                exibeMensagemErro(1, id, "", token);
            } else if (tabelaSimbolos.get(expressaoId) != null
                    && tabelaSimbolos.get(expressaoId) > 0) {
                codigoObjeto.append("\n stelem ");
                codigoObjeto.append(getTipoId(expressaoId));
                Tipo tipo = new Tipo(getTipoIdInt(getTipoId(expressaoId)));
                pilhaTipos.push(tipo);
                expressaoId = "";
            } else {
                Tipo tipo = new Tipo(getTipoIdInt(getTipoId(id)));
                pilhaTipos.push(tipo);
                if (tabelaSimbolos.get(id) == null
                        || tabelaSimbolos.get(id) == 0) {
                    codigoObjeto.append("\n ldloc ");
                    codigoObjeto.append(id);
                }
            }
        }
    }

    /*
     @ação: Atribuição
     */
    private void acaoVinteCinco() throws SemanticError {
        String id = identificador;
        if (!existeId(id)) {
            exibeMensagemErro(1, id, "", token);
        }
        int tipoId = getTipoIdInt(getTipoId(id));
        Tipo tipoExp = pilhaTipos.pop();
        if ((tipoId == Tipo.cte_integer && tipoExp.getTipo() != Tipo.cte_integer)
                || (tipoId == Tipo.cte_bool && tipoExp.getTipo() != Tipo.cte_bool)
                || (tipoId == Tipo.cte_float && tipoExp.getTipo() != Tipo.cte_float)
                || (tipoId == Tipo.cte_string && tipoExp.getTipo() != Tipo.cte_string)) {
            exibeMensagemErro(2, id, "", token);
        }
        if (tabelaSimbolos.get(id) == null
                || tabelaSimbolos.get(id) == 0) {
            codigoObjeto.append("\n stloc ");
            codigoObjeto.append(id);
        } else {
            codigoObjeto.append("\n stelem ");
            codigoObjeto.append(getTipoId(id));
        }
    }

    private void exibeMensagemErro(int tipo, String param1, String param2, Token token) throws SemanticError {
        switch (tipo) {
            case 1: {
                throw new SemanticError(param1 + " não declarado", token.getPosition(), token.getLinha());
            }
            case 2: {
                throw new SemanticError(param1 + " não aceita este tipo", token.getPosition(), token.getLinha());
            }
            case 3: {
                throw new SemanticError("Operação somente permitida com números inteiros", token.getPosition(), token.getLinha());
            }
            case 4: {
                throw new SemanticError("O tamanho do array precisa ser maior que 0 ", token.getPosition(), token.getLinha());
            }
            case 5: {
                throw new SemanticError(param1 + " já declarado", token.getPosition(), token.getLinha());
            }
            case 6: {
                throw new SemanticError("Operação válida apenas para tipos numéricos", token.getPosition(), token.getLinha());
            }
            case 7: {
                throw new SemanticError(param1 + " e " + param2 + " possuem tipos incompatíveis", token.getPosition(), token.getLinha());
            }
            case 8: {
                throw new SemanticError("Não é permitido tipo lógico", token.getPosition(), token.getLinha());
            }
            case 9: {
                throw new SemanticError("Somente é permitido tipo lógico", token.getPosition(), token.getLinha());
            }
        }
    }

    private void acaoVinteSeis() throws SemanticError {
        Integer tamVetor = 0;
        try {
            tamVetor = Integer.parseInt(token.getLexeme());
        } catch (Exception e) {
            exibeMensagemErro(3, "", "", token);
        }
        if (tamVetor < 1) {
            exibeMensagemErro(4, "", "", token);
        }
        // pega o ultimo id adicionado
        String id = listaId.get(listaId.size() - 1);
        tabelaSimbolos.put(id, tamVetor);

    }

    private void acaoVinteOito() {
        if ("isTrueDo".equals(token.getLexeme())) {
            codigoObjeto.append("\n brfalse ");
            ctLabel++;
            codigoObjeto.append("l" + ctLabel);
        } else {
            codigoObjeto.append("\n brfalse ");
            ctLabel++;
            codigoObjeto.append("l" + ctLabel);
            ctLabel++;
            codigoObjeto.append("\n br ");
            codigoObjeto.append("l" + ctLabel);
            codigoObjeto.append("\n");
            codigoObjeto.append("l" + (ctLabel - 1));
            codigoObjeto.append(":");
        }
    }

    private void acaoVinteNove() {
        codigoObjeto.append("\n");
        codigoObjeto.append("l" + ctLabel);
        codigoObjeto.append(":");
    }

    private void acaoTrintaUm() {
        ctLabel++;
        codigoObjeto.append("\n");
        codigoObjeto.append("l" + ctLabel);
        codigoObjeto.append(":");
    }

    private void acaoTrintaDois() {
        if ("isTrueDo".equals(token.getLexeme())) {
            codigoObjeto.append("\n brfalse ");
            ctLabel++;
            codigoObjeto.append("l" + ctLabel);
        } else {
            codigoObjeto.append("\n brfalse ");
            ctLabel++;
            codigoObjeto.append("l" + ctLabel);
            codigoObjeto.append("\n br ");
            ctLabel++;
            codigoObjeto.append("l" + ctLabel);
            codigoObjeto.append("\n");
            codigoObjeto.append("l" + (ctLabel - 1));
            codigoObjeto.append(":");
        }
    }

    private void acaoTrintaTres() {
        codigoObjeto.append("\n br ");
        codigoObjeto.append("l" + (ctLabel - 1));
        codigoObjeto.append("\n");
        codigoObjeto.append("l" + ctLabel);
        codigoObjeto.append(":");
    }

    // verifica se o tipo é lógico
    private void acaoTrintaQuatro() throws SemanticError {
        Tipo tipoExp = pilhaTipos.pop();
        if (tipoExp.getTipo() != Tipo.cte_bool) {
            exibeMensagemErro(8, "", "", token);
        }
    }

    // verifica se é diferente de tipo lógico
    private void acaoTritaCinco() throws SemanticError {
        if (getTipoIdInt(getTipoId(token.getLexeme())) == Tipo.cte_bool) {
            exibeMensagemErro(8, "", "", token);
        }
    }

    private void acaoTrintaSeis() throws SemanticError {
        if (expressao1) {
            Tipo tipo1 = pilhaTipos.pop();
            Tipo tipo2 = pilhaTipos.pop();
            verificaTipoExpressao(tipo1, tipo2);
        }
    }

    private void verificaTipoExpressao(Tipo tipo1, Tipo tipo2) throws SemanticError {
        int tp1 = tipo1.getTipo();
        int tp2 = tipo2.getTipo();
        if ((tp1 == Tipo.cte_integer
                && tp2 == Tipo.cte_integer)
                || (tp1 == Tipo.cte_float
                && tp2 == Tipo.cte_float)
                || (tp1 == Tipo.cte_string
                && tp2 == Tipo.cte_string)
                || (tp1 == Tipo.cte_bool
                && tp2 == Tipo.cte_bool)) {
            pilhaTipos.push(tipo1);
        } else {
            exibeMensagemErro(7, tipo1.toString(), tipo2.toString(), token);
        }
    }

    private void acaoVinteSete() throws SemanticError {
        codigoObjeto.append("\n ldloc ");
        codigoObjeto.append(token.getLexeme());
    }

    private void acaoTrinta() {
        expressaoId = token.getLexeme();
        codigoObjeto.append("\n ldloc ");
        codigoObjeto.append(expressaoId);
    }

}
