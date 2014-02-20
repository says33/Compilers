package mx.ipn.analizadorLexico.utils;
import mx.ipn.analizadorLexico.domain.AFN;
import mx.ipn.analizadorLexico.domain.ClaseLexica;

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 11/02/14
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnalizadorLexico {

    /*Constructor*/
    public AnalizadorLexico(){}
    /*
    boolean E(AFN f1){
        if(T(f1))
            if(Ep(f1))
                return true;

        return false;
    }

    boolean Ep(AFN f1){
        int tok;
        AFN f2 = new AFN();
        tok = lexic.yylex();

        if(tok == ClaseLexica.OR){
            if(T(f2)){
                f1.unir(f1);
                if(Ep(f1))
                    return true;
            }
        }

        lexic.regresaToken();
        return true;
    }

    boolean T(AFN f1){
        if(C(f1))
            if(Tp(f1))
                return true;

        return false;
    }

    boolean Tp(AFN f1){
        int tok;
        tok = lexic.yylex();
        AFN f2 = new AFN();

        if(tok == ClaseLexica.CONC){
            if(C(Fn))
        }
    }*/
}
