DROP DATABASE IF EXISTS DataBasePrueba;

CREATE DATABASE DataBasePrueba;

USE DataBasePrueba;

CREATE TABLE Cliente(
	idCliente INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(40) NOT NULL,
	apellido VARCHAR(40) NOT NULL,
	email VARCHAR(40) NOT NULL,
	fechNac DATE NOT NULL
) ENGINE=InnoDB;

INSERT INTO Cliente(nombre,apellido,email,fechNac) VALUES('Gamaliel','Jimenez','egjimenezg@gmail.com','98-08-22');

CREATE TABLE Orden(
	idOrden INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idCliente INTEGER NOT NULL,
	fechOrd DATE NOT NULL,
	FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente)
);
