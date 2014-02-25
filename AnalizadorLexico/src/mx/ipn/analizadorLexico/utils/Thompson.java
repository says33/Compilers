package mx.ipn.analizadorLexico.utils;
import mx.ipn.analizadorLexico.domain.AFN;
import mx.ipn.analizadorLexico.domain.ClaseLexica;
import mx.ipn.analizadorLexico.domain.Estado;

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

        DescensoRecursivo descensoRecursivo = new DescensoRecursivo(cl);
        descensoRecursivo.E(afn);

        return afn;
    }
}
