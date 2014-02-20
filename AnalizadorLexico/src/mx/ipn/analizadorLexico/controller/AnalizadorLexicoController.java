package mx.ipn.analizadorLexico.controller;

import mx.ipn.analizadorLexico.domain.AFN;
import mx.ipn.analizadorLexico.service.AnalizadorLexicoService;

/**
 * Author: Gamaliel Jiménez
 * Date: 13/02/14
 * Time: 12:55 PM
 */
public class AnalizadorLexicoController {

    public AnalizadorLexicoController(){

    }

    public void getAFN(){
        AnalizadorLexicoService als = new AnalizadorLexicoService();
        als.getFinalAutomata();
    }
}
