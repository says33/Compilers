package mx.ipn.analizadorSintactico.utils

import mx.ipn.analizadorSintactico.domain.Lista
import mx.ipn.analizadorSintactico.domain.Nodo
import mx.ipn.analizadorSintactico.domain.TokenScanner

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 18/03/14
 * Time: 07:08 PM
 * To change this template use File | Settings | File Templates.
 */
class DescensoRecursivo {

    def scanner

    def DescensoRecursivo(){
        this.scanner = new Scanner()
    }

    def G(Lista l){
        if(listaReglas(l))
            true
        false
    }

    def listaReglas(Lista l){
        def tok

        def laux = new Lista()

        if(regla(laux)){
            tok = scanner.getToken()
            if(tok==TokenScanner.PUNTOCOMA){
                //[a]Guardar Edo Scanner
                if(listaReglas(l)){
                    laux.head.abajo = l
                    l = laux
                    return true
                }
                //Regresar Edo del Scanner al Edo[a]
                return true
            }
        }

        return false
    }

    def regla(Lista l){
        int tok
        def laux = new Lista()

        if(Li(l)){
            tok = scanner.getToken()
            if(tok==TokenScanner.FLECHA){
                if(Ld(l)){
                  //  laux.head.sig = l
                   // l = laux
                   // return true
                }
            }
        }

        return false
    }


    def Li(Lista l){
        int tok
        tok = scanner.getToken()

        if(tok==TokenScanner.SIMB){
            l.agregaNodo(scanner.getLexema())
        }
        l.head = null
    }


    def Ld(Lista l){

    }
}
