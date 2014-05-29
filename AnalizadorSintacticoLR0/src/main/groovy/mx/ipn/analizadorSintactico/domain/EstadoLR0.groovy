package mx.ipn.analizadorSintactico.domain

class EstadoLR0{
	Integer id
	//Conjunto de Items de cada estado
	def estadoItems = []
	//Mapa de transiciones
	def transiciones = [:]

}