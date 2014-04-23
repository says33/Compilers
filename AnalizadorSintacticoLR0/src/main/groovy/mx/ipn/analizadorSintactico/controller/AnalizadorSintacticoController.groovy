package mx.ipn.analizadorSintactico.controller

import org.apache.log4j.*
import groovy.util.logging.*
import mx.ipn.analizadorSintactico.domain.Nodo
import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.service.AnalizadorSintacticoService
import mx.ipn.analizadorSintactico.utils.ItemLR
import mx.ipn.analizadorSintactico.utils.Movimiento
/****************************
 * Author: Gamaliel Jiménez *
 * Date: 15-Abril-2014      *
 ****************************/

@Log4j
class AnalizadorSintacticoController {
    
    def mapTerminalTokens
    def analizadorSintacticoService

    def AnalizadorSintacticoController(){       
       log.level = Level.DEBUG
       analizadorSintacticoService = new AnalizadorSintacticoService()
    }    

    def setMapTerminalTokens(def mapTerminalTokens){
        this.mapTerminalTokens = mapTerminalTokens
    }

    def getMapTerminalTokens(){
        return mapTerminalTokens
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

    def crearTablaLR0(def itemsNoTerminales,def terminales,def mapOfLists){
        def itemLR = new ItemLR(terminales)        
        //Creación del automata LR0 para construir la tabla
        def automataLR0 = itemLR.elementos(itemsNoTerminales)
        analizadorSintacticoService.crearTablaLR0(automataLR0,terminales,mapOfLists)
    }

    def obtenerTerminales(def mapOfLists){
        def terminales = []

        mapOfLists.each{
            terminales.addAll(Lista.terminalesSub(it.value.sig))
        }

        terminales.unique()
    }


    def analizaCadena(def tablaLR0,def cadena,def terminales,def lexemasAndTokens){
        
        def cadenaReal = []
        def tokensEntrada=[]
        def subcadena = ""

        lexemasAndTokens.each{
            cadenaReal += it[0]
            tokensEntrada += it[1]
        }

        subcadena = concat(cadenaReal)

        tablaLR0.each{
            log.debug it
        }

        tokensEntrada.each{
            log.debug it
        }

        mapTerminalTokens.each{
            log.debug it
        }

        def movimientos = []
        def auxStringPila = ""
        def auxStringSimbolo = ""
        def edos = []
        Stack<Integer> pila = new Stack<Integer>()
        Stack<String> pilaSimbolos = new Stack<String>()
        def index = 0
        //def w = getW(cadena,terminales) + '$'
        def a = tokensEntrada[index]
        
        tablaLR0.size().times{ i ->
            edos << i
        }

        pila.push(edos[0])
        
        
        def noAceptado = false

        while(1){
            if(tablaLR0[pila.peek()][mapTerminalTokens[a]] instanceof Integer){
                pila.elements().each{
                    auxStringPila+= "${it} "
                }
                
                pilaSimbolos.elements().each{
                    auxStringSimbolo+= "${it} "
                }
                
                movimientos.add(new Movimiento(pila:auxStringPila,simbolos:auxStringSimbolo,entrada:subcadena,accion:"desplazar"))
                auxStringPila = ""
                auxStringSimbolo = ""

                pila.push(tablaLR0[pila.peek()][mapTerminalTokens[a]])

                pilaSimbolos.push(mapTerminalTokens[a])
                
                
                //log.debug "Items desp --"  
                  //  pila.elements().each{
                    //    log.debug it
                    //}
                //log.debug "-------------"
                
                a = tokensEntrada[++index]
                subcadena = subcadena.substring(cadenaReal.get(index-1).length(),subcadena.length())

                log.debug "A " + a  
            }
            else if(tablaLR0[pila.peek()][mapTerminalTokens[a]].getClass() == LinkedHashMap){
                
                def noTerminal = tablaLR0[pila.peek()][mapTerminalTokens[a]].li
                def ladoDerecho = ""
                tablaLR0[pila.peek()][mapTerminalTokens[a]].ld.each{
                    ladoDerecho += it
                }
                
                pila.elements().each{
                    auxStringPila += "${it} "
                }
                
                pilaSimbolos.elements().each{
                    auxStringSimbolo+= "${it} "
                }

                movimientos.add(new Movimiento(pila:auxStringPila,simbolos:auxStringSimbolo,entrada:subcadena,accion:"reducir ${noTerminal}→${ladoDerecho}")) 
                auxStringPila = ""
                auxStringSimbolo = ""


                (tablaLR0[pila.peek()][mapTerminalTokens[a]].ld).each{
                    pila.pop()
                    pilaSimbolos.pop()
                }
                

                pilaSimbolos.push(noTerminal)
                pila.push(tablaLR0[pila.peek()][noTerminal])

                /*
                log.debug "Items reduccion " 
                    pila.elements().each{
                        log.debug it
                    }
                log.debug "Simbolos "
                    pilaSimbolos.elements().each{
                        log.debug it
                    }
                log.debug "----------------"*/
            }
            else if(tablaLR0[pila.peek()][mapTerminalTokens[a]] == 'ACEPTAR'){
                pila.elements().each{
                    auxStringPila += "${it} "
                }
                
                pilaSimbolos.elements().each{
                    auxStringSimbolo+= "${it} "
                }

                movimientos.add(new Movimiento(pila:auxStringPila,simbolos:auxStringSimbolo,entrada:"",accion:"ACEPTAR"))
                break
            }
            else{                                        
                noAceptado = true
                break                
            }
            
        }

        if(noAceptado)
            log.debug "La cadena no fue aceptada"

        movimientos
    }
    
    def getW(def cadena,def terminales){
        def wList = []
        def subcadena = ""
        for(def i=0;i<cadena.length();i++){
            subcadena += Character.toString(cadena.charAt(i))
            if(isTerminal(subcadena,terminales)){
                wList << subcadena
                subcadena = ""
            }
        }

        wList
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

}
