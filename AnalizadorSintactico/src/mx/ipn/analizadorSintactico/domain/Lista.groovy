package mx.ipn.analizadorSintactico.domain

/**
 * User: Gamaliel
 *
 */
class Lista {

    Nodo head

    def Lista(){
    }

    def agregaNodo(String lexema){
        def nvoNodo = new Nodo()
        nvoNodo.simbolo = lexema
        nvoNodo.sig = null
        nvoNodo.abajo = null

        lastNode().sig = nvoNodo
    }

    def lastNode(){
        Nodo aux = head

        while(aux.sig!= null)
            aux = aux.sig

        aux
    }

    /*Método para imprimir la lista*/
    def printList(){
        Nodo aux = head

        while(aux.sig != null){
            println aux?.simbolo
            aux = aux?.sig
        }

    }

}

