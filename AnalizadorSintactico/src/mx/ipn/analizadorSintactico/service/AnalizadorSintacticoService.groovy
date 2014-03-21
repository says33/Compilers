package mx.ipn.analizadorSintactico.service

import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.utils.DescensoRecursivo

/**
    Author: Gamaliel Jiménez
    Date: 20/Marzo/2014
 **/

class AnalizadorSintacticoService {

    /*This function will read the lines of the file*/
    def linesFromFile(){
        def list=[]
        def f = new File(getClass().getResource("/mx/ipn/analizadorSintactico/utils/Gramatica.txt").file)
        list = f.readLines()
    }

    /*This function will create a list of a Production*/
    def createListFromProduction(String production){
        def lista = new Lista()
        def descensoRecursivo = new DescensoRecursivo()
        descensoRecursivo.G(lista)
        lista
    }


}
