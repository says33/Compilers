package mx.ipn.proyectoFinal.utils

import groovy.sql.Sql

class DataBase{

	def db = [url:'jdbc:mysql://localhost/DataBasePrueba',
			  user:'egjimenezg',
			  password:'egjimenezg',
			  driver:'com.mysql.jdbc.Driver']
    def sql
	
	def DataBase(){								
		 sql = Sql.newInstance(db.url,db.user,db.password,db.driver)
	}

	def query(){
		sql.eachRow("SELECT * FROM Cliente"){ row ->
			println row
		}
	}
}




