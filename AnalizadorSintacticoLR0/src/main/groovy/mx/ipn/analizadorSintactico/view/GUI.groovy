package mx.ipn.analizadorSintactico.view

import static groovyx.javafx.GroovyFX.start
import mx.ipn.analizadorSintactico.controller.AnalizadorSintacticoController

class GUI{

	def analizadorSintacticoController
  def mapaDeListas
  /*The constructor initialize the GUI of the application-
	  El constructor inicializa la Interfaz de Usuario de la aplicacion*/
	def GUI(){
		
    analizadorSintacticoController = new AnalizadorSintacticoController();

		start{
			final fileChooser = fileChooser(initialDirectory: ".", title: "Selecciona Gramatica"){
                            		filter("Gramaticas", extensions: ["*.gram"])
                        		}
           	stage(title: 'Analizador Léxico-Sintáctico', show: true) {
           		
           		scene(fill: GROOVYBLUE, width:1000,height:600) {
           			borderPane(){
           				top(){
                    		menuBar{
                        		menu(text: "Archivo"){
                            		menuItem("Abrir",onAction: {
                                    def file = fileChooser.showOpenDialog(primaryStage)
                                    if(file){
                                        mapaDeListas = analizadorSintacticoController.crearLista(file)
                                        analizadorSintacticoController.crearItems(mapaDeListas)
                                    }
                                })
                        		}
                    		}
                		}
                		tabPane(){
                    		tab(text:'Analizador Lexico'){
                        		borderPane(){                            
                            		gridPane(hgap: 5, vgap: 10, padding: 25, alignment: "center"){        
                            			//label("Name", hgrow: "never", row: 1, column: 0, textFill: white)
                            			//textField(promptText: "Your name", row: 1, column: 1)
                            		} 
                        		} 
                    		}
                    		tab(text:'Analizador Sintáctico'){

                    		}

                		}

           			}

           		}


           	}
		}
	
	}//End of GUI
}