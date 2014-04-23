package mx.ipn.analizadorSintactico.service

import org.apache.log4j.*
import groovy.util.logging.*
import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.domain.Nodo
import mx.ipn.analizadorSintactico.domain.TokenScanner
import mx.ipn.analizadorSintactico.utils.ScannerSintactico
import mx.ipn.analizadorSintactico.utils.DescensoRecursivo
import mx.ipn.analizadorSintactico.utils.First
import mx.ipn.analizadorSintactico.utils.Follow

/**
    Author: Gamaliel Jiménez
    Date: 20/Marzo/2014
 **/

@Log4j
class AnalizadorSintacticoService {

    def AnalizadorSintacticoService(){       
        log.level = Level.DEBUG      
    }    

    def getProductionsFromFile(def file){
        def productions=[]        
        productions = file.readLines()
        
        productions.each{
            log.debug it
        }

        productions
    }
    
    def crearAlfabeto(String gramatica){
        log.debug gramatica.length()
        
        gramatica.length().times{ i->
            if(!TokenScanner.tokensMap.get(String.valueOf(gramatica.charAt(i))))
                if(!TokenScanner.alfabeto.contains(String.valueOf(gramatica.charAt(i))))
                    TokenScanner.alfabeto.add(String.valueOf(gramatica.charAt(i)))
        }
        
        TokenScanner.addAlphabetToMap()
    }
    

    def crearListaGramatica(String gramatica){
        def lista = new Lista()
        def scanner = new ScannerSintactico(gramatica)
        def descensoRecursivo = new DescensoRecursivo(scanner)
        descensoRecursivo.G(lista)        
        aumentarGramatica(lista)        
        lista
    }

    def aumentarGramatica(def lista){
        def nGp = new Nodo()
        def nG = new Nodo()
        def Gp = lista.head.simbolo+'\''
        def G = lista.head.simbolo
        nGp.simbolo = Gp
        nG.simbolo = G
        nGp.sig = nG
        nGp.abajo = lista.head
        lista.head = nGp        
    }

    def crearMapaDeListas(def mapa,def lista){
        def aux = lista.head
        mapa.put(aux.simbolo,aux)

        while(aux.abajo) {
            aux = aux.abajo
            mapa.put(aux.simbolo,aux)
        }
    }

    def crearTablaLR0(def automataLR0,def terminales,def mapOfLists){        
        def tablaAccion = []
        def mapOfGramaticaNoAumentada = [:]

        mapOfGramaticaNoAumentada << (mapOfLists.findAll{it.key != mapOfLists.iterator().next().key})
        //Mapa que contiene el follow de cada elemento
        def terminalesAndNoTerminales =[]
        terminalesAndNoTerminales += terminales + '$' + mapOfGramaticaNoAumentada.keySet()

        terminalesAndNoTerminales.each{
            log.debug it
        }

        def follow = calcularFollow(null,mapOfGramaticaNoAumentada)


        automataLR0.estados.each{ edo ->
            def row = [:] 
            terminalesAndNoTerminales.each{ terminal ->
                row[terminal]='' 
            }
            tablaAccion << row
        }

        automataLR0.estados.each{ edo ->            
            def accionAndIr_AItems = accionAndIr_AElementos(edo.estadoItems,terminalesAndNoTerminales)
            def reducciones = reduccionElementos(edo.estadoItems,terminalesAndNoTerminales)
            
            if(aceptacion(mapOfLists.iterator().next().key,edo.estadoItems)){
                tablaAccion[edo.id]['$'] = 'ACEPTAR'
            }

            reducciones.each{ r -> 
                if(follow[r.li]){                    
                    follow[r.li].each{ term -> tablaAccion[edo.id][term] = r }
                }
            }

            accionAndIr_AItems.each{ term ->
                if(edo.transiciones[term])
                    tablaAccion[edo.id][term]= edo.transiciones[term].id
            }

        }

        tablaAccion
    }

    //Define la acción para el estado en la tabla
    def accionAndIr_AElementos(def itemsEstado,def terminalesAndNoTerminales){
        def elementosAccion = []

        terminalesAndNoTerminales.each{ terminal ->
            itemsEstado.findAll { it.next == terminal}.each{
                elementosAccion << it.next
            }
        }

        elementosAccion
    }

    def reduccionElementos(def itemsEstado,def terminalesAndNoTerminales){
        def reduccion = []

        itemsEstado.findAll{it.next == 'ε'}.each{
            def ladoDerecho = it.prod.split('→')[1]
            ladoDerecho = ladoDerecho[0..(ladoDerecho.length()-2)]
            
            def reduccionMap = [li:it.li,ld:getLista(ladoDerecho,terminalesAndNoTerminales)]
            reduccion << reduccionMap
        }

        reduccion
    }

    def aceptacion(def simboloAumentado,def itemsEstado){
        if(itemsEstado.findAll{(it.li == simboloAumentado && it.next == 'ε')})
            return true
        return false
    }

    def calcularFollow(def first,mapOfLists){

        def mapOfFollow = [:]
        def noTerminales = mapOfLists.keySet()

        def follow = new Follow(mapOfLists,null)        

        noTerminales.each{ noTerminal ->
            def auxmap = [:]
            mapOfFollow[noTerminal] = follow.getFollowOfNodo(mapOfLists[noTerminal])
        }

        mapOfFollow
    }

    def getLista(def ladoDerecho,def terminalesAndNoTerminales){
        def listaSimbolos = []
        def substring = ""
        ladoDerecho.length().times{ i->
            substring += ladoDerecho[i]
            if(esSimbolo(substring,terminalesAndNoTerminales)){
                listaSimbolos << substring
                substring = ""  
            }
        }

        listaSimbolos
    }

    def esSimbolo(def cadena,def terminalesAndNoTerminales){
        if(terminalesAndNoTerminales.contains(cadena))
            return true
        false
    }
    
}