package mx.ipn.analizadorSintactico.controller

import org.apache.log4j.*
import groovy.util.logging.*
import mx.ipn.analizadorSintactico.domain.Nodo
import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.service.AnalizadorSintacticoService
import mx.ipn.analizadorSintactico.utils.ItemLR
//import mx.ipn.analizadorSintactico.utils.First
//import mx.ipn.analizadorSintactico.utils.Follow
/****************************
 * Author: Gamaliel Jiménez *
 * Date: 15-Abril-2014      *
 ****************************/

@Log4j
class AnalizadorSintacticoController {
    
    def analizadorSintacticoService

    def AnalizadorSintacticoController(){       
       log.level = Level.DEBUG
       analizadorSintacticoService = new AnalizadorSintacticoService()
    }    


    //Crea la lista de listas a partir del archivo de gramáticas seleccionado
    def crearLista(def file){
       def sb = new StringBuilder()
       
       analizadorSintacticoService.getProductionsFromFile(file).each{ production ->
           sb.append(production)
       }

       def gramatica = sb.toString()

       log.debug gramatica

       analizadorSintacticoService.crearAlfabeto(gramatica)
       def lista = analizadorSintacticoService.crearListaGramatica(gramatica)
       def mapaDeListas = [:]
       analizadorSintacticoService.crearMapaDeListas(mapaDeListas,lista)

       mapaDeListas
    }


    def crearItems(def mapaDeListas){
        def itemsNoTerminales = [:]

        mapaDeListas.each{ key,value ->            
            itemsNoTerminales[key] = getItemsFromNodo(value)            
        }

        itemsNoTerminales
    }

    def getItemsFromNodo(Nodo n){        
        def auxs = n.sig            
        def ladoIzq = n.simbolo+'→'        
        def listOfItems = []
        getItemsFromProductions(auxs,ladoIzq,listOfItems)
        listOfItems        
    }

    
    def getItemsFromProductions(Nodo n,def ladoIzq,def list){

        def indexPoint = 0
        def dataItem = [:]
        dataItem[indexPoint] = [prod:ladoIzq+getItem(n,indexPoint)]            
        dataItem[indexPoint].prev = 'ε'
        dataItem[indexPoint].next = n.simbolo
        dataItem[indexPoint].pointPosition = indexPoint
        dataItem[indexPoint].li = ladoIzq.split('→')[0]

        def aux = n.sig
        def prev = n
        
        while(aux){
            indexPoint++            
            dataItem[indexPoint] = [prod:ladoIzq+getItem(n,indexPoint)]            
            dataItem[indexPoint].prev = prev.simbolo
            dataItem[indexPoint].next = aux.simbolo
            dataItem[indexPoint].pointPosition = indexPoint
            dataItem[indexPoint].li = ladoIzq.split('→')[0]
            prev=aux
            aux = aux.sig            
        }

        indexPoint++
        dataItem[indexPoint] = [prod:ladoIzq+getItem(n,indexPoint)]
        dataItem[indexPoint].prev = prev.simbolo
        dataItem[indexPoint].next = 'ε'
        dataItem[indexPoint].pointPosition = indexPoint
        dataItem[indexPoint].li = ladoIzq.split('→')[0]

        list.add(dataItem)

        if(n.abajo)        
            getItemsFromProductions(n.abajo,ladoIzq,list)            

    }

    def getItem(Nodo n,Integer indexPoint){
        def itemProd=""
        def aux = n
        def counter = 1        

        if(!indexPoint)
            itemProd+="."

        itemProd+=aux.simbolo
        
        while(aux.sig){
            if(counter == indexPoint)
                itemProd+="."
            
            aux = aux.sig
            itemProd += aux.simbolo
            counter++
        }

        if(counter == indexPoint)
            itemProd+="."
                
        itemProd
    }

    def crearAutomataLR(def itemsNoTerminales,def terminales){
        def itemLR = new ItemLR(terminales)        
        itemLR.elementos(itemsNoTerminales)
    }

    def obtenerTerminales(def mapOfLists){
        def terminales = []

        mapOfLists.each{
            terminales.addAll(Lista.terminalesSub(it.value.sig))
        }

        terminales.unique()
    }

    //def mapTerminalToken
    
    /*
    def AnalizadorSintacticoController(def mapTerminalToken){
        analizadorSintacticoService = new AnalizadorSintacticoService()
        this.mapTerminalToken = mapTerminalToken
    }*/

/*


    def calcularFirst(def mapOfLists){

        def first = new First(mapOfLists)
*/
        /*Calculo de todos los first*/
  /*      mapOfLists.each{
            first.getFirstOfNodo(it.value)
        }

        first
    }

    def calcularFollow(def first,mapOfLists){

        def mapOfFollow = [:]
        def noTerminales = analizadorSintacticoService.getNoTerminalesConDerivacionEpsilon(mapOfLists)
        def follow = new Follow(mapOfLists,first.mapOfFirst)

        noTerminales.each{ noTerminal ->
            def auxmap = [:]

            follow.getFollowOfNodo(mapOfLists.get(noTerminal)).each{
                auxmap.put(it,"ε")
            }

            mapOfFollow.(noTerminal.toString()) = auxmap
        }

        mapOfFollow
    }

    


    def analizaCadena(def first,def follow,def terminales,def lexemasAndTokens){

        def datos = []
        def listData = []
        def auxString = ""

        def inputArray = []
        def cadena = []
        def subcadena = ""

        lexemasAndTokens.each{
            cadena.add(it.get(0))
            inputArray.add(it.get(1))
        }


        subcadena = concat(cadena)

        Stack<String> pila = new Stack<String>()
        pila.push('$')
        pila.push(first.iterator().next().key)

        listData.add('$')
        listData.add("")
        listData.add("push (${first.iterator().next().key})")

        datos.add(listData)

        def pointer = 0
        def a = inputArray.get(pointer)
        def X = pila.peek()

        while(!X.equals('$')){
            listData = []
            auxString = ""

            if(X.equals(mapTerminalToken.get(a))){
                pila.elements().each{
                    auxString+=it
                }
                listData.add(auxString)
                listData.add(subcadena)
                listData.add("pop (${pila.peek()})")

                pila.pop()
                //cadena = cadena.substring(a.length(),cadena.length())
                pointer++;
                a = inputArray.get(pointer)

                subcadena = subcadena.substring(cadena.get(pointer-1).length(),subcadena.length())

            }
            else if(first.get(X)?.get(mapTerminalToken.get(a))){
                def items = analizadorSintacticoService.getItemsOfProd(first.get(X)?.get(mapTerminalToken.get(a)))

                pila.elements().each{
                    auxString+=it
                }
                listData.add(auxString)
                listData.add(subcadena)
                auxString = ""

                items.each{
                    auxString+=it
                }

                listData.add(auxString)

                items = items.reverse()

                pila.pop()

                items.each{
                    pila.push(it)
                }
            }
            else if(follow.get(X)?.get(mapTerminalToken.get(a))){
                pila.elements().each{
                    auxString+=it
                }

                listData.add(auxString)
                listData.add(subcadena)
                listData.add(follow.get(X)?.get(mapTerminalToken.get(a)))

                pila.pop()
            }
            else{
                return datos
            }

            datos.add(listData)

            X = pila.peek()
        }


        listData = []
        listData.add('$')
        listData.add("")
        listData.add("ACCEPTED")

        datos.add(listData)
        datos
    }


    def isTerminal(String cadena,def terminales){
        if(terminales.contains(cadena))
            return true
        false
    }

    def concat(inputArray){
        def cadena = new StringBuilder()

        inputArray.each{
            cadena.append(it)
        }

        cadena.toString()
    }
*/
}
