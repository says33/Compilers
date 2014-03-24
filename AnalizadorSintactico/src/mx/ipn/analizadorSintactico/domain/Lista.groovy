package mx.ipn.analizadorSintactico.domain

/**
 * User: Gamaliel
 *
 */
class Lista {

    Nodo head

    def Lista(){

    }

    def agregaNodo(String lexema,Boolean esTerminal){
        Nodo aux
        aux = head

        if(!aux){
            head = createNodo(lexema,esTerminal)
        }
        else{
            while(aux.sig)
                aux = aux.sig

            aux.sig = createNodo(lexema,esTerminal)
        }
    }

    def createNodo(String lexema,Boolean esTerminal){
        Nodo nuevo = new Nodo()
        nuevo.simbolo = lexema
        nuevo.esTerminal = esTerminal
        nuevo.sig = null
        nuevo
    }
    /*Método para imprimir la lista*/
    def printList(){
        Nodo aux = head

        println('Head_'+aux.simbolo)

        while(aux.sig){
            aux = aux.sig
            println(aux.simbolo)
        }

    }

    def printList(def nodo){

        if(nodo.abajo)
            printList(nodo.abajo)

        def aux = nodo

        println(aux.simbolo)

        if(aux.sig)
            printSubList(aux.sig)

    }

    def printSubList(Nodo nodo){

        if(nodo.abajo){
            printSubList(nodo.abajo)
        }

        def aux = nodo

        print aux.simbolo

        while(aux.sig){
            aux = aux.sig
            print(" " + aux.simbolo)
        }
        println()
    }


}

