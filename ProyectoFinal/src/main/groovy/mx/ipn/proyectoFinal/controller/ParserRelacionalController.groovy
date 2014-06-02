package mx.ipn.proyectoFinal.controller

import java.util.Properties
import mx.ipn.proyectoFinal.services.ParserRelacionalService
import mx.ipn.proyectoFinal.utils.Lexer
import mx.ipn.proyectoFinal.utils.parser
import mx.ipn.proyectoFinal.domain.Query

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

	def parseString(def text){
		def lexer = null
		def parser = null
		try{
			def query = new Query()
			
			lexer = new Lexer(new StringReader(text))
		    parser = new parser(lexer)
		    parser.setQuery(query)
		    parser.parse()
		}
		catch(Exception ex){
			println ex.toString()
		}
	}
}