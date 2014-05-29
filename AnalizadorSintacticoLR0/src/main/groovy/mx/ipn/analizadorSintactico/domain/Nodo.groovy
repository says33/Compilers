package mx.ipn.analizadorSintactico.domain
/**
 * User: Gamaliel
 * Date: 18/03/14
 */
class Nodo {
    String simbolo
    Boolean esTerminal
    Nodo sig = null
    Nodo abajo = null
}
