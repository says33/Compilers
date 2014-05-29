package mx.ipn.analizadorSintactico.domain

import org.apache.log4j.*
import groovy.util.logging.*

/******************************
 * Author: Gamaliel Jiménez   *
 * Date: 19-03-14             *
 ******************************/
@Log4j
class TokenScanner {
        
    def static final PUNTOCOMA = 500
    def static final FLECHA = 600
    def static final OR = 700
    def static final SIMB = 800
    /*Lista que guarda los carácteres de la gramática*/
    def static alfabeto = []

    def static tokensMap = ['→':TokenScanner.FLECHA,
                            ';':TokenScanner.PUNTOCOMA,
                            '|':TokenScanner.OR]


    def static addAlphabetToMap(){
        alfabeto.each {
            //log.debug it
            tokensMap.put(it,TokenScanner.SIMB)
        }
    }
}
