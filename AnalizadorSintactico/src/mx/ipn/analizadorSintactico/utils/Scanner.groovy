package mx.ipn.analizadorSintactico.utils

import mx.ipn.analizadorSintactico.domain.TokenScanner

/**
 * User: Gamaliel
 * Date: 19/03/14
 */
class Scanner {
    Integer current = 0
    Integer prev = 0
    String gramatica
    Integer ultimoTokenEncontrado

    def Scanner(def gramatica){
        this.gramatica = gramatica
    }


    def getToken(){
        current++

        if(current < gramatica.length()){
            println(gramatica.substring(prev,current))
            return TokenScanner.tokensMap(gramatica.substring(prev,current))
        }


        return 0
    }

    def getLexema(){
        gramatica.substring(prev,current)
        /*Prev y current son iguales*/
        prev = ++current
    }


    def isCharacter(def c){
        if(c>='a' && c<='z' || c>='A' && c<='Z')
            return true
        return false
    }

    def isDigit(def c){
        if(c>='0' && c <='9')
            return true
        else
            return false
    }
}
