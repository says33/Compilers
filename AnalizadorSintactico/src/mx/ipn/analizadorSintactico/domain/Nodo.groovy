package mx.ipn.analizadorSintactico.domain

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 18/03/14
 * To change this template use File | Settings | File Templates.
 */
class Nodo {
    String simbolo
    Boolean esTerminal
    Nodo sig = null
    Nodo abajo = null
}
