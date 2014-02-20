package mx.ipn.analizadorLexico.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ClaseLexicaToken {

    private static final Character[] SIMB = {'0','1','2','3','4','5','6','7','8','9','0','.','a','b','c','d','e',
            'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
            'w','x','y','z'};

    private static final Character OR = '|';
    private static final Character CONC = '&';
    private static final Character PAR_I = '(';
    private static final Character PAR_D = ')';
    private static final Character CERR_POS = '+';
    private static final Character CERR_ESTRELLA = '*';
    private static final Character OPC = '?';
    public static final Map<Character,Integer> claseLexicaToken;

    static {
        Map<Character,Integer> aMap = new HashMap<Character, Integer>();

        /*Simbolos agregados al mapa para obtener el token directamente*/
        for(Integer i=0;i<SIMB.length;i++){
            aMap.put(SIMB[i],TokenCL.SIMB);
        }
        aMap.put(OR, TokenCL.OR);
        aMap.put(CONC,TokenCL.CONC);
        aMap.put(PAR_I,TokenCL.PAR_I);
        aMap.put(PAR_D,TokenCL.PAR_D);
        aMap.put(CERR_POS,TokenCL.CERR_POS);
        aMap.put(CERR_ESTRELLA,TokenCL.CERR_ESTR);
        aMap.put(OPC,TokenCL.OPC);
        claseLexicaToken = Collections.unmodifiableMap(aMap);
    }

}
