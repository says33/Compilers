package mx.ipn.analizadorSintactico.controller

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


}
