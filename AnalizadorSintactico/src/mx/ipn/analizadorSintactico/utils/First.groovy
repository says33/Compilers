package mx.ipn.analizadorSintactico.utils
import mx.ipn.analizadorSintactico.domain.Nodo
/*
 * Author: Eduardo Gamaliel Jiménez García
 */
class First {

    def mapOfLists
    def mapOfFirst = [:]

    def First(def mapOfLists){
        this.mapOfLists  = mapOfLists
    }

    public void getFirstOfNodo(Nodo n){
        def aux = n.sig
        def auxb = aux.abajo
        def listaAux = []
        def lista = []
        def mapa = [:]

        lista.addAll(getFirst(aux,listaAux))

        while (listaAux.contains('ε')){
            listaAux = []
            if((aux = aux.sig))
                lista.addAll(getFirst(aux,listaAux))
        }

        lista.unique()
        mapa.put(n.sig.simbolo,lista)

        while(auxb){
            lista = []
            listaAux = []

            lista.addAll(getFirst(auxb,listaAux))

            while(listaAux.contains('ε')){
                listaAux = []
                if((aux = auxb.sig))
                    lista.addAll(getFirst(aux,listaAux))
            }

            mapa.put(auxb.simbolo,lista)
            auxb = auxb.abajo
        }

        mapOfFirst.(n.simbolo) = mapa

    }

    def getFirst(Nodo n,def lista){
        if(!n.esTerminal)
            getFirstOfNodo((mapOfLists.get(n.simbolo)),lista)
        else{
            if(!lista.contains(n.simbolo))
                lista.add(n.simbolo)
            return lista
        }
    }

    def getFirstOfNodo(Nodo n, def lista){
        def aux = n.sig
        def auxb = aux.abajo

        getFirst(aux,lista)

        while(auxb){
            getFirst(auxb,lista)
            auxb = auxb.abajo
        }

        return lista
    }

    def getItemsOfFirst(def simbNodo){
        def list = []

        mapOfFirst.get(simbNodo).each {
            list.addAll(it.value)
        }

        list
    }

}
