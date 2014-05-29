package mx.ipn.analizadorSintactico.domain

import org.apache.log4j.*
import groovy.util.logging.*
/**
	Author: Gamaliel Jiménez
	Date: 20-Abril-2014
**/

@Log4j
class AutomataLR0{
	def estados
	def alfabeto	

	def AutomataLR0(){
		log.level = Level.DEBUG
		estados=[]
		alfabeto=[]		
	}

	def addEdoToAutomata(def I){
		def estado = new EstadoLR0()		
		
		estado.id = (estados.size() > 0 ? estados.size() : 0)
		
		estado.estadoItems = I
		estados.add(estado)
	}

	def printAutomata(){
		estados.each{ edo ->
			log.debug "Inicia impresión de transiciones de Edo"
			log.debug "Items del estado"
			edo.estadoItems.each{
				log.debug it
			}
			log.debug "Fin Items"

			edo.transiciones.each{ key,value ->
				log.debug "${key} "
				value.estadoItems.each{ item ->
					log.debug "\t ${item.prod}"
				}				
			}

			log.debug "Finaliza impresión de transiciones de Edo"
		}
	}
}