import org.apache.log4j.*
import groovy.util.logging.*
import mx.ipn.analizadorSintactico.view.GUI

class Main{

	public static void main(String[] args){
		BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%d{ABSOLUTE} %-5p [%c{1}] %m%n")))		
		def GUI = new GUI()                    
	}
}
                
    
