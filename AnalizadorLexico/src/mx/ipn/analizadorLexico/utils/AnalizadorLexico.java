package mx.ipn.analizadorLexico.utils;
import mx.ipn.analizadorLexico.domain.AFN;
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

    boolean E(AFN afn){
        if(T(afn))
            if(Ep(afn))
                return true;
        return false;
    }

    boolean Ep(AFN afn){

        return true;
    }

    boolean T(AFN afn){
        if(F(afn))
            return true;
        else
            return false;
    }

    boolean Tp(){
        return true;
    }

    boolean F(AFN afn){
        return true;
    }
}
