package mx.ipn.analizadorSintactico.utils;

import mx.ipn.analizadorSintactico.domain.AFN;
import mx.ipn.analizadorSintactico.domain.ClaseLexica;

/**
 * User: Gamaliel
 * Date: 16/02/14
 * Time: 05:15 PM
 */
public class Thompson {

    public Thompson(){
    }

    public AFN convertRE(String regularExpression){
        AFN afn = new AFN();
        /*Se crea la clase léxica que define la expresión regular*/
        ClaseLexica cl = new ClaseLexica();
        cl.setExpresionRegular(regularExpression);

        DescensoRecursivoLexico descensoRecursivo = new DescensoRecursivoLexico(cl);
        descensoRecursivo.E(afn);

        return afn;
    }
}
