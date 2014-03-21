package mx.ipn.analizadorSintactico.controller

import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.service.AnalizadorSintacticoService
import mx.ipn.analizadorSintactico.utils.DescensoRecursivo

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * To change this template use File | Settings | File Templates.
 */
class AnalizadorSintacticoController {

    def analizadorSintacticoService

    def AnalizadorSintacticoController(){
        analizadorSintacticoService = new AnalizadorSintacticoService()
    }

    def crearListasGramaticas(){
        def gramaticas = analizadorSintacticoService.linesFromFile()

        gramaticas.each{
            def listOfLists = []
            analizadorSintacticoService.createListFromProduction(it)
        }
    }



}
