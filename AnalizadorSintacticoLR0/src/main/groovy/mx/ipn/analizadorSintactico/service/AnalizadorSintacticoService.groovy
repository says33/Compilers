package mx.ipn.analizadorSintactico.service

import org.apache.log4j.*
import groovy.util.logging.*
import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.domain.Nodo
import mx.ipn.analizadorSintactico.domain.TokenScanner
import mx.ipn.analizadorSintactico.utils.ScannerSintactico
import mx.ipn.analizadorSintactico.utils.DescensoRecursivo

/**
    Author: Gamaliel Jiménez
    Date: 20/Marzo/2014
 **/

@Log4j
class AnalizadorSintacticoService {

    def AnalizadorSintacticoService(){       
           
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


/*
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


    def getItemsOfProd(String prod){
        def lista = []

        if(isLowerCase(prod.charAt(0))){
            lista.add(prod)
            return lista
        }

        for(int i=0;i<prod.size();i++){
            if(i < prod.size()-1){
                if(prod.charAt(i+1) == '\''){
                    lista.add(prod.substring(i,i+2))
                    i += 1
                }
                else
                    lista.add(new Character(prod.charAt(i)).toString())
            }
            else if(prod.charAt(i) != '\'')
                lista.add(new Character(prod.charAt(i)).toString())
        }

        lista
    }

    def isLowerCase(def c){
        if(c>='a' && c <='z')
            return true

        false
    }
    */
}