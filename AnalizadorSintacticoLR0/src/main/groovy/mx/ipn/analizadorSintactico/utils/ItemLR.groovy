package mx.ipn.analizadorSintactico.utils

import org.apache.log4j.*
import groovy.util.logging.*
import mx.ipn.analizadorSintactico.domain.AutomataLR0

@Log4j
class ItemLR{

	def itemsNoTerminales
	def arregloBooleanTerminales
	def terminales

	def ItemLR(def terminales){
		this.terminales = terminales
		log.level = Level.DEBUG
	}	

	def cerradura(def I){
		
		def arregloBooleanNoTerminales = [:]

		itemsNoTerminales.each{ key,value ->
			arregloBooleanNoTerminales[key] = false
		}

		def J = I		
		def listProdAux = []
		
		for(def i=0;i<J.size();i++){			
			if(!J.get(i).next.equals('ε') || J.get(i).prev.equals('ε')){
				if(!arregloBooleanNoTerminales[J.get(i).next]){
					arregloBooleanNoTerminales[J.get(i).next] = true					
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

	def ir_AContains(def item,def ir_A){
	    
	    def t = ir_A.findAll{it.prod == item.prod}

	    if(t) return true

	    false
	}

	def ir_A(def I,def X){
						
		def ir_Alist = []
		
		/*
		def f = new File("/home/gamaliel/Escritorio/datos.log")		
		f.createNewFile()

		f<< "Originales\n\n"		
		I.each{
			f << "${it}\n"
		}
		f<< "FinOriginales\n\n"
		*/

		I.each{ item ->
			if(item.next.equals(X)){				
				itemsNoTerminales.get(item.li).each{ listItem->					
					if(listItem[item.pointPosition+1])
						if(listItem[item.pointPosition+1].prev.equals(item.next)){
							if(!ir_AContains(listItem[item.pointPosition+1],ir_Alist))
								ir_Alist.add(listItem[item.pointPosition+1])
						}								
				}		
			}
		}		
		
		/*
		f<< "Items Ir_A\n\n"
		ir_Alist.each{
			f << "${it}\n"
		}					
		f<< "Fin Items\n\n"
		*/
		
		cerradura(ir_Alist)		
	}

	def elementos(def Gp){		
		
		this.itemsNoTerminales = Gp
		def simboloGramatical = []

		simboloGramatical.addAll(itemsNoTerminales.keySet())
		simboloGramatical.remove(Gp.iterator().next().key)
		simboloGramatical += terminales

		log.debug "Simbolos gramaticales " + simboloGramatical
		
		//Automata que contendrá los estados
		def automataLR0 = new AutomataLR0()

		def I = [] 
		def C = []
		def ir_AListAux = []
		I.add(((Gp.iterator().next().value)[0])?.get(0))		
		C.add(cerradura(I))		
		automataLR0.addEdoToAutomata(C[0])
		
		def auxTransicion = [:]

		for(def i=0;i<C.size();i++){
			simboloGramatical.each{ X ->
				ir_AListAux = ir_A(C[i],X)	
				if(ir_AListAux){										
					def edoTransicion = containsItems(C,ir_AListAux)
					if(edoTransicion == -1){						
						C.add(ir_AListAux)
						automataLR0.addEdoToAutomata(ir_AListAux)
						auxTransicion[X] = automataLR0.estados[automataLR0.estados.size()-1]
					}
					else{
						auxTransicion[X] = automataLR0.estados[edoTransicion]
					}
				}
					
			}
			automataLR0.estados[i].transiciones = auxTransicion
			auxTransicion = [:]			
		}

		/*
		log.debug "\n"
		C.each{
			log.debug "C Items"
			it.each{
				log.debug "Prod" + it.prod
			}
			log.debug "-------"
		}
		log.debug "\n"
		*/

		//automataLR0.printAutomata()
		
		automataLR0
	}

	def containsItems(def C,def ir_A){
		for(def i=0;i<C.size();i++)
			if(itemsIguales(C.get(i),ir_A)){				
				return i
			}
		
		return -1	
	}

	def itemsIguales(def I,def ir_A){
		def min = I.size() < ir_A.size() ? I.size() : ir_A.size()
		
		for(def i=0;i<min;i++){			
			if(!ir_A[i].prod.equals(I[i].prod))
				return false
		}
		
		return true
	}

}