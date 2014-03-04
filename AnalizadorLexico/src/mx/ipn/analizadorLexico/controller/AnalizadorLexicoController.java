package mx.ipn.analizadorLexico.controller;

import mx.ipn.analizadorLexico.domain.AFD;
import mx.ipn.analizadorLexico.domain.AFN;
import mx.ipn.analizadorLexico.service.AnalizadorLexicoService;
import mx.ipn.analizadorLexico.utils.AFDFile;
import mx.ipn.analizadorLexico.utils.AFNaAFD;

/**
 * Author: Gamaliel Jiménez
 * Date: 13/02/14
 * Time: 12:55 PM
 */
public class AnalizadorLexicoController {

    public AnalizadorLexicoController(){

    }

    public void getAFN(){
        AFNaAFD convertidor = new AFNaAFD();
        AFDFile afdFile = new AFDFile();
        AnalizadorLexicoService als = new AnalizadorLexicoService();

        AFN afn = als.getFinalAutomata();
        AFD afd = new AFD();
        convertidor.convierteAFNaAFD(afn,afd);
        afd.printTransiciones();
        afdFile.createFile(afd);
    }
}
