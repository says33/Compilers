package mx.ipn.proyectoFinal.controller

import java.util.Properties
import mx.ipn.proyectoFinal.services.ParserRelacionalService

class ParserRelacionalController{

	def parserRelacionalService

	def ParserRelacionalController(){
		parserRelacionalService	= new ParserRelacionalService()
	}

	def createPropertiesFile(String user,String password,String dataBaseName){
		parserRelacionalService.createPropertiesFile(user,password,dataBaseName)		
	}

	def getTablesOfDataBase(){
		parserRelacionalService.getTablesFromDataBase()
	}

	def getColumnsFromTable(String tableName){
		parserRelacionalService.getColumnsFromTable(tableName)
	}
}