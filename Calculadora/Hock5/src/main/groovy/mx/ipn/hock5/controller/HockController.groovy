package mx.ipn.hock5.controller;

import mx.ipn.hock5.utils.Lexico
import mx.ipn.hock5.utils.parser
import mx.ipn.hock5.domain.FunctionMemory

class HockController{
	
	def functionMemory

	public HockController(){
	}
	
	def parseString(def text,def textArea){
		def analizadorLexico = null
		def parser = null
		functionMemory = new FunctionMemory()
		try{			
			analizadorLexico = new Lexico(new StringReader(text))
			parser = new parser(analizadorLexico)
			parser.setJTextArea(textArea)
			parser.setFunctionMemory(functionMemory)
			parser.parse()
		}
		catch(Exception ex){
			println ex.toString()
		}
	}     
     
}
