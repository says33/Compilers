package mx.ipn.analizadorLexico.domain;

import java.util.HashMap;
import java.util.Map;

public class ClaseLexicaToken {

    private Map<Object,Integer> claseLexicaToken;
    private static final Character[] SIMB = {'0','1','2','3','4','5','6','7','8','9','0','.','a','b','c','d','e',
                                             'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
                                             'w','x','y','z'};
    private static final Character OR = '|';
    private static final Character AND = '&';
    private static final Character PAR_I = '(';
    private static final Character PAR_D = ')';
    private static final Character CERR_POS = '+';
    private static final Character CERR_ESTRELLA = '*';
    private static final Character OPC = '?';

    ClaseLexicaToken(){
        initializeMapWithValues();
    }

    public void initializeMapWithValues(){
        claseLexicaToken = new HashMap<Object, Integer>();
        claseLexicaToken.put(SIMB,TokenCL.SIMB);
        claseLexicaToken.put(OR,TokenCL.OR);
        claseLexicaToken.put(AND,TokenCL.AND);
        claseLexicaToken.put(PAR_I,TokenCL.PAR_I);
        claseLexicaToken.put(PAR_D,TokenCL.PAR_D);
        claseLexicaToken.put(CERR_POS,TokenCL.CERR_POS);
        claseLexicaToken.put(CERR_ESTRELLA,TokenCL.CERR_ESTR);
        claseLexicaToken.put(OPC,TokenCL.OPC);
    }

    public Map<Object, Integer> getClaseLexicaToken() {
        return claseLexicaToken;
    }

    public void setClaseLexicaToken(Map<Object, Integer> claseLexicaToken) {
        this.claseLexicaToken = claseLexicaToken;
    }
}
