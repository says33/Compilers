package mx.ipn.proyectoFinal.services

import mx.ipn.proyectoFinal.utils.DataBase

class ParserRelacionalService{
	
	def createPropertiesFile(String user,String password,String dataBaseName){		
		
		if(user && password && dataBaseName){
			def properties = new Properties()
			def testProperties = new Properties()

			def classLoader = Thread.currentThread().getContextClassLoader()
			def output = new FileOutputStream(classLoader.getResource('DB.properties').getPath())

			properties.setProperty("dbuser",user)
			properties.setProperty("dbpassword",password)
			properties.setProperty("database",dataBaseName)
			properties.store(output,null)			
			
		}
	}	

	def getTablesFromDataBase(){
		def db = new DataBase()
		db.getTableNames()		
	}

	def getColumnsFromTable(String tableName){
		def db = new DataBase()
		db.getColumnNamesFromTable(tableName)
	}

	def getDataFromDataBase(String sqlQuery){
		def db = new DataBase()
		db.query(sqlQuery);
	}
}