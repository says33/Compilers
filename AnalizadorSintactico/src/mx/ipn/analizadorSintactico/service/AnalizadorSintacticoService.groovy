package mx.ipn.analizadorSintactico.service

import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.domain.Nodo
import mx.ipn.analizadorSintactico.domain.TokenScanner
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

    def crearAlfabeto(String gramaticas){
        for(def i=0;i<gramaticas.length();i++)
            if(!TokenScanner.tokensMap.get(String.valueOf(gramaticas.charAt(i))))
                TokenScanner.alfabeto.add(String.valueOf(gramaticas.charAt(i)))

        TokenScanner.addAlphabetToMap()
    }

    /*This function will create a list of a Production*/
    def createListFromProduction(String production){
        def lista = new Lista()
        def scanner = new mx.ipn.analizadorSintactico.utils.Scanner(production)
        def descensoRecursivo = new DescensoRecursivo(scanner)
        descensoRecursivo.G(lista)
        lista
    }


    def createAMapOfLists(def map,def list){
        def aux = list.head

        map.put(aux.simbolo,aux)

        while (aux.abajo){
            aux = aux.abajo
            map.put(aux.simbolo,aux)
        }
    }

    def getNoTerminalesConDerivacionEpsilon(def map){

        def list = []

        map.each{
            if(tieneDerivacionesEpsilon(it.value.sig))
                list.add(it.value.simbolo)
        }

        list
    }

    def tieneDerivacionesEpsilon(Nodo n){

        if(n.abajo)
            if(tieneDerivacionesEpsilon(n.abajo))
                return true

        if(n.simbolo.equals("ε"))
            return true

        def aux = n.sig

        while(aux){
            if(aux.simbolo.equals("ε"))
                return true

            aux = aux.sig
        }

        false
    }

}