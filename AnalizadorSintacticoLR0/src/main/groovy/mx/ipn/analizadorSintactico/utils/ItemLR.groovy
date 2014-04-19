package mx.ipn.analizadorSintactico.utils

import org.apache.log4j.*
import groovy.util.logging.*

@Log4j
class ItemLR{

	def itemsNoTerminales
	def arregloBooleanTerminales

	def ItemLR(){
		log.level = Level.DEBUG		
	}

	def elementos(def Gp){
		arregloBooleanTerminales = [:]
		this.itemsNoTerminales = Gp
				
		def I = []
		I.add(((Gp.iterator().next().value)[0])?.get(0))			
		
		cerradura(I)

	}

	def cerradura(def I){
		
		itemsNoTerminales.each{ key,value ->
			arregloBooleanTerminales[key] = false
		}

		def J = I		
		def listProdAux = []
		
		for(def i=0;i<J.size();i++){			
			if(!J.get(i).next.equals('ε')){
				if(!arregloBooleanTerminales[J.get(i).next]){
					arregloBooleanTerminales[J.get(i).next] = true					
					getProduccionesSiguiente(J.get(i).next).each{ prod->
						J.add(prod)
					}
				}
			}
		}	
		
		log.error "--------> ${J}"
		J
	}

	
	def getProduccionesSiguiente(def next){
		def listProducciones = []
		
		itemsNoTerminales.get(next).each{ itemList ->
			listProducciones.add(itemList.get(0))
		}

		listProducciones
	}
}