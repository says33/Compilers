package mx.ipn.analizadorSintactico.utils

import org.apache.log4j.*
import groovy.util.logging.*
import mx.ipn.analizadorSintactico.domain.Nodo
/******************************
 * Author: Gamaliel Jiménez   *
 * Date: 19-03-14             *
 ******************************/
@Log4j
class Follow {

    def mapOfLists
    def mapOfFollow = [:]
    def mapOfFirst

    def Follow(def mapOfLists,def mapOfFirst){
        this.mapOfLists = mapOfLists
        this.mapOfFirst = mapOfFirst
        log.level = Level.DEBUG
    }

    def getFollowOfNodo(Nodo n){
        def lista = []
        def nextNodes

        if(mapOfFollow.get(n.simbolo))
            return mapOfFollow.get(n.simbolo)

        if(n.simbolo.equals(mapOfLists.iterator().next().value.simbolo))
            lista.add('$')

        nextNodes = findNextNodesinMap(n)

        if(nextNodes){
            nextNodes.each {
                if(!it.value.esTerminal){
                    lista.addAll(getItemsOfFirst(it.value.simbolo))
                    lista.removeAll('ε')
                        if(getItemsOfFirst(it.value.simbolo).contains('ε'))
                            lista.addAll(getFollowOfNodo(mapOfLists.get(it.value.simbolo)))
                }
                else
                    lista.add(it.value.simbolo)
            }
        }
        
        if(n.simbolo == 'F')
            log.debug "Extremo Producciones ${esExtremoProducciones(n.simbolo)}"
        
        lista.addAll(esExtremoProducciones(n.simbolo))

        lista
    }

    def findNextNodesinMap(Nodo n){
        def listOfNodes = [:]

        mapOfLists.each{
            next((it.value).sig,n.simbolo,listOfNodes)
        }

        listOfNodes
    }

    def next(def nodo,def simbNodo,def map){
        def aux = nodo
        def auxb = nodo

        while (auxb.abajo){
            auxb = auxb.abajo
            next(auxb,simbNodo,map)
        }

        if((aux.simbolo).equals(simbNodo))
            if(aux.sig)
                if(!map.get(aux.sig.simbolo))
                    map.put(aux.sig.simbolo,aux.sig)

        while(aux.sig){
            aux = aux.sig
            if((aux.simbolo).equals(simbNodo))
                if(aux.sig)
                    if(!map.get(aux.sig.simbolo))
                        map.put(aux.sig.simbolo,aux.sig)
        }

        map
    }


    def esExtremoProducciones(def simbNodo){
        def list = []

        mapOfLists.each{        
            if((ultimoNodoProduccion(it.value.sig).contains(simbNodo)) && !simbNodo.equals(it.value.simbolo))
                list.addAll(getFollowOfNodo(mapOfLists.get(it.value.simbolo)))
        }

        return list
    }

    def ultimoNodoProduccion(def nodo){
        def list = []        
        
        if(nodo.abajo){            
            list += ultimoNodoProduccion(nodo.abajo)
        }    

        def aux = nodo
        
        while(aux.sig)            
            aux = aux.sig
        
        list << aux.simbolo

        list
    }

    def getItemsOfFirst(def simbNodo){
        def list = []

        mapOfFirst.get(simbNodo).each {
            list.addAll(it.key)
        }

        list
    }


   def tieneDerivacionesEpsilon(def simbNodo){
        mapOfLists.get(simbNodo)
        true
   }

   def buscarEpsilon(Nodo nodo){
       while(nodo.abajo){
           buscarEpsilon(nodo.abajo)
       }

       def aux = nodo

       while(aux.sig)
           aux = aux.sig
   }

}
