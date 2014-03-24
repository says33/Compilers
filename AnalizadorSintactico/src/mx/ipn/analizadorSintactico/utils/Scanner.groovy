package mx.ipn.analizadorSintactico.utils

import mx.ipn.analizadorSintactico.domain.TokenScanner

/**
 * User: Gamaliel
 * Date: 19/03/14
 */
class Scanner {

    Integer counter = 0
    Integer current = -1
    /*Gramática proveniente del archivo que será escaneada*/
    String gramatica
    String lexema
    Integer ultimoTokenEncontrado

    def Scanner(def gramatica){
        this.gramatica = gramatica
    }


    def getToken(){
        current++
        counter++

        println("Current  "+ current)
        println("Counter " + counter)

        if(current < gramatica.length()){
            lexema = gramatica.substring(current,counter)
            return TokenScanner.tokensMap.get(String.valueOf(lexema.charAt(lexema.size()-1)))
        }

        return 0
    }

    def getLexema(){
        def lexema = gramatica.substring(current,counter)

        println("Lexema " + lexema)

        if(isTerminal(lexema)){

            while(isTerminal((lexema = gramatica.substring(current,++counter)).charAt(lexema.length()-1))){
                lexema = gramatica.substring(current,counter)
            }
            counter--
            lexema = gramatica.substring(current,counter)
            current = counter-1

            return lexema
        }

        if(getToken() == TokenScanner.SIMB){
            lexema = gramatica.substring(current,counter)
            if(lexema == '\''){
                lexema = gramatica.substring(current-1,counter)
                return lexema
            }

        }

        regresarToken()
        lexema = gramatica.substring(current,counter)
    }


    def isTerminal(){
        def c = gramatica.substring(current,counter)

        if(c>='A' && c<='Z')
            return false
        else
            return true
    }

    def isTerminal(def c){
        if(c>='a' && c<='z')
            return true
        return false
    }

    def regresarToken(){
        current--
        counter--
    }
}
