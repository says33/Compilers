package mx.ipn.analizadorSintactico.utils
import mx.ipn.analizadorSintactico.domain.Nodo

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 19/03/14
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */

class First {

    def First(){

    }

    def getFirst(Nodo n){

        def simbNoTerminales = []

        Stack<Nodo> pilaNodos = new Stack<Nodo>()
        pilaNodos.add(n)

        while(!pilaNodos.isEmpty()){

        }
    }
}
