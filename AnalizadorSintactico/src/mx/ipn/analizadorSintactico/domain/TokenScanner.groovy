package mx.ipn.analizadorSintactico.domain

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 19/03/14
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
class TokenScanner {
    def static final PUNTOCOMA = 500
    def static final FLECHA = 600
    def static final OR = 700
    def static final SIMB = 800

    def static tokensMap = ["→":TokenScanner.FLECHA,
                            ";":TokenScanner.PUNTOCOMA,
                            "|":TokenScanner.OR]

}
