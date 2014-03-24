package mx.ipn.analizadorSintactico.utils

import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.domain.Nodo
import mx.ipn.analizadorSintactico.domain.TokenScanner
import mx.ipn.analizadorSintactico.utils.Scanner

/*
 * User: Gamaliel
 * Date: 18/03/14
 */
class DescensoRecursivo {

    def scanner

    def DescensoRecursivo(mx.ipn.analizadorSintactico.utils.Scanner scanner){
        this.scanner = scanner
    }

    def G(Lista l){
        if(listaReglas(l))
            return true
        return false
    }

    def listaReglas(Lista l){
        def tok
        def laux = new Lista()

        if(regla(l)){
            tok = scanner.getToken()

            if(tok==TokenScanner.PUNTOCOMA){
                l.printList(l.head)
                if(listaReglas(laux)){
                    l.head.abajo = laux.head
                    l.printList(l.head)
                    println()
                    return true
                }
                return true
            }
        }

        return false
    }

    def regla(Lista l){
        def tok
        def laux = new Lista()

        if(Li(laux)){
            tok = scanner.getToken()
            if(tok==TokenScanner.FLECHA){
                if(listaLd(l)){
                    laux.head.sig = l.head
                    l.head = laux.head
                    return true
                }
                return false
            }

            scanner.regresarToken()
        }

        return false
    }

    def Li(Lista l){
        int tok
        tok = scanner.getToken()

        if(tok==TokenScanner.SIMB){
            def isTerminal = scanner.isTerminal()
            l.agregaNodo(scanner.getLexema(),isTerminal)
            return true
        }

        return false
    }



    def listaLd(Lista l){
        def tok

        if(Ld(l)){
            tok = scanner.getToken()
            if(tok==TokenScanner.OR){
                def laux = new Lista()

                if(listaLd(laux)){
                    l.head.abajo = laux.head
                    return true;
                }
            }
            scanner.regresarToken()
            return true;
        }
        return false;
    }

    def Ld(Lista l){
        if(listaSimb(l))
            return true;
        return false;
    }

    def listaSimb(Lista l){

        def tok = scanner.getToken()

        if(tok==TokenScanner.SIMB){
            def isTerminal = scanner.isTerminal()

            l.agregaNodo(scanner.getLexema(),isTerminal)
            listaSimb(l)
            return true
        }

        scanner.regresarToken()
        return false
    }
}
