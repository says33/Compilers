package mx.ipn.analizadorLexico.domain;

/**
 * Created with IntelliJ IDEA.
 * User: gamaliel
 * Date: 19/02/14
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class ClaseLexica {
    /**/

    private String expresionRegular;

    /*
    private static Character[] SIMB = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
                                       'o','p','q','r','s','t','u','v','w','x','y','z',
                                       '0','1','2','3','4','5','6','7','8','9'};
    private static Character OR = '|';
    private static Character AND = '&';
    private static Character PAR_I = '(';
    private static Character PAR_D = ')';*/


    /*Expresion regular que será analizada*/
    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }
}
