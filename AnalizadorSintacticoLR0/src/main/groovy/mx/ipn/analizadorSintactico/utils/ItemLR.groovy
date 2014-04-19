package mx.ipn.analizadorSintactico.utils

import org.apache.log4j.*
import groovy.util.logging.*

@Log4j
class ItemLR{

	def itemsNoTerminales
	def arregloBooleanTerminales
	def terminales

	def ItemLR(def terminales){
		this.terminales = terminales
		log.level = Level.DEBUG
	}

	def elementos(def Gp){		
		this.itemsNoTerminales = Gp
		def simboloGramatical = []
		simboloGramatical.addAll(itemsNoTerminales.keySet())
		simboloGramatical.remove(Gp.iterator().next().key)
		simboloGramatical.addAll(terminales)

		log.debug "Simbolos gramaticales " + simboloGramatical

		def I = [] 
		def C = []
		def ir_AListAux = []
		I.add(((Gp.iterator().next().value)[0])?.get(0))
		C.add(cerradura(I))

		for(def i=0;i<C.size();i++){
			simboloGramatical.each{
				ir_AListAux = ir_A(C[i],it)
				if(ir_AListAux)
					log.debug ir_AListAux
				else
					log.debug "Is empty"
			}			
		}	

	}

	def cerradura(def I){
		
		arregloBooleanTerminales = [:]

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
				
		J
	}

	
	def getProduccionesSiguiente(def next){
		def listProducciones = []
		
		itemsNoTerminales.get(next).each{ itemList ->
			listProducciones.add(itemList.get(0))
		}

		listProducciones
	}

	def ir_A(def I,def X){
						
		def ir_Alist = []

		I.each{ item ->
			if(item.next.equals(X)){				
				itemsNoTerminales.get(item.li).each{ listItem->					
					if(listItem[item.pointPosition+1].prev.equals(item.next))					
						ir_Alist.add(listItem[item.pointPosition+1])
				}				
			}
		}

		ir_Alist
	}


	def containsItems(def C,def ir_A){
		C.each{
			log.debug it
		}

	}

	def itemsIguales(){

	}
	
}