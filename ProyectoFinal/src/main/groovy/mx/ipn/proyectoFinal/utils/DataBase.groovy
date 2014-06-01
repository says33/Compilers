package mx.ipn.proyectoFinal.utils

import groovy.sql.Sql
import java.sql.ResultSet
import java.util.Properties

class DataBase{

    String url = 'jdbc:mysql://localhost/'
    String dataBaseName
    String user
    String password
    String driver = 'com.mysql.jdbc.Driver'			  						  
    def sql
	
    def DataBase(){								
		Properties prop = new Properties()		
		def classLoader = Thread.currentThread().getContextClassLoader()		
		InputStream inputStream = classLoader.getResourceAsStream("DB.properties")
		prop.load(inputStream)

		user = prop.dbuser
		password = prop.dbpassword
		dataBaseName = prop.database
		
		if(!inputStream)
			throw new FileNotFoundException("Propery file not found in the classpath")		 
    }

    def getTableNames(){
    	sql = Sql.newInstance(url+dataBaseName,user,password,driver)
        def tableNames = []

        def md = sql.connection.metaData
        
        def rs = md.getTables(null, null,"%",null);
        
        while(rs.next())
            tableNames << rs.getString(3)

        tableNames            
    }	

    def getColumnNamesFromTable(String tableName){
        sql = Sql.newInstance(url+dataBaseName,user,password,driver)
        def columnNames = []

        def md = sql.connection.metaData
        def rs = md.getColumns(null,null,tableName,"%")

        while(rs.next())
            columnNames << rs.getString("COLUMN_NAME")

        columnNames
    }

}




