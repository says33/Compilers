package mx.ipn.analizadorSintactico.controller

import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.service.AnalizadorSintacticoService
import mx.ipn.analizadorSintactico.utils.First
import mx.ipn.analizadorSintactico.utils.Follow

/**
 * Author: Gamaliel Jiménez
 */
class AnalizadorSintacticoController {

    def analizadorSintacticoService

    def AnalizadorSintacticoController(){
        analizadorSintacticoService = new AnalizadorSintacticoService()
    }

    def crearListaGramaticas(){
        def stringBuilder = new StringBuilder()

        analizadorSintacticoService.linesFromFile().each{
            stringBuilder.append(it)
        }

        def gramaticas = stringBuilder.toString()

        /*Se crea el alfabeto verificando cada carácter del archivo*/
        analizadorSintacticoService.crearAlfabeto(gramaticas)

        /*Lista de listas*/
        def list = analizadorSintacticoService.createListFromProduction(gramaticas)

        def mapOfLists = [:]

        analizadorSintacticoService.createAMapOfLists(mapOfLists,list)

        mapOfLists

    }

    def calcularFirst(def mapOfLists){

        def first = new First(mapOfLists)

        /*Calculo de todos los first*/
        mapOfLists.each{
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

    def obtenerTerminales(def mapOfLists){
        def terminales = []

        mapOfLists.each{
            terminales.addAll(Lista.terminalesSub(it.value.sig))
        }

        terminales.unique()
    }


    def analizaCadena(String cadena,def first,def follow,def terminales){

        def datos = []
        def listData = []
        def auxString = ""

        def inputArray = getItemsFromCadena(cadena,terminales)
        inputArray.add('$')

        Stack<String> pila = new Stack<String>()
        pila.push('$')
        pila.push(first.iterator().next().key)

        listData.add('$')
        listData.add(cadena)
        listData.add("push (${first.iterator().next().key})")
        datos.add(listData)

        println(first)
        println(follow)

        def pointer = 0
        def a = inputArray.get(pointer)
        def X = pila.peek()


        while(!X.equals('$')){
            listData = []
            auxString = ""

            if(X.equals(a)){
                pila.elements().each{
                    auxString+=it
                }
                listData.add(auxString)
                listData.add(cadena)
                listData.add("pop (${pila.peek()})")

                pila.pop()
                cadena = cadena.substring(a.length(),cadena.length())
                pointer++;
                a = inputArray.get(pointer)
            }
            else if(first.get(X)?.get(a)){
                def items = analizadorSintacticoService.getItemsOfProd(first.get(X)?.get(a))

                pila.elements().each{
                    auxString+=it
                }
                listData.add(auxString)
                listData.add(cadena)
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
            else if(follow.get(X)?.get(a)){
                pila.elements().each{
                    auxString+=it
                }

                listData.add(auxString)
                listData.add(cadena)
                listData.add(follow.get(X)?.get(a))

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
        listData.add(cadena)
        listData.add("ACCEPTED")

        datos.add(listData)

        datos
    }

    def getItemsFromCadena(String cadena,def terminales){
        def list = []

        def subStr = ""
        def counter = 0
        while (counter<cadena.length()){
            if(isTerminal(subStr,terminales)){
                list.add(subStr)
                subStr = ""
            }
            else{
                subStr+=cadena.charAt(counter)
                counter++
            }
        }

        if(subStr)
            list.add(subStr)

        list
    }

    def isTerminal(String cadena,def terminales){
        if(terminales.contains(cadena))
            return true
        false
    }

}
