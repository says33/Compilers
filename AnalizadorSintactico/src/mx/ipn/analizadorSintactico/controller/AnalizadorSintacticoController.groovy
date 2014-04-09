package mx.ipn.analizadorSintactico.controller

import mx.ipn.analizadorSintactico.service.AnalizadorSintacticoService
import mx.ipn.analizadorSintactico.utils.First
import mx.ipn.analizadorSintactico.utils.Follow
import mx.ipn.analizadorSintactico.view.GUI

/**
 * Author: Gamaliel Jiménez
 */
class AnalizadorSintacticoController {

    def analizadorSintacticoService

    def AnalizadorSintacticoController(){
        analizadorSintacticoService = new AnalizadorSintacticoService()
    }

    def crearListasGramaticas(){
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

        def first = new First(mapOfLists)

        /*Calculo de todos los first*/
        mapOfLists.each{
            first.getFirstOfNodo(it.value)
        }


        def follow = new Follow(mapOfLists,first.mapOfFirst)

        println(follow.getFollowOfNodo(mapOfLists.get("T")))

        def gui = new GUI()
    }

}
