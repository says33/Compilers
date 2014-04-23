package mx.ipn.analizadorSintactico.view

import static groovyx.javafx.GroovyFX.start
import javafx.scene.control.Label
import groovyx.javafx.beans.FXBindable
import javafx.collections.ObservableList;
import mx.ipn.analizadorSintactico.controller.AnalizadorSintacticoController
import mx.ipn.analizadorSintactico.utils.Movimiento

class GUI{

	def analizadorSintacticoController
  def mapaDeListas
  def itemsNoTerminales
  def terminales = []
  def tablaLR0 = []
  def movimientos = []
  /*The constructor initialize the GUI of the application-
	  El constructor inicializa la Interfaz de Usuario de la aplicacion*/
	class Info{
     def listaPrueba = []
  }

  def GUI(){
		
    analizadorSintacticoController = new AnalizadorSintacticoController();
    

		start{
      def info = new Info()
      info.listaPrueba = [ new Movimiento(pila:"0",simbolos:""),
                           new Movimiento(pila:"0 5",simbolos:"num") ]

      final fileChooser = fileChooser(initialDirectory: ".", title: "Selecciona Gramatica"){
                            		filter("Gramaticas", extensions: ["*.gram"])
                        	}

           	stage(title: 'Analizador Léxico-Sintáctico', show: true,id:'stageId') {
           		
           		scene(fill: GROOVYBLUE, width:1000,height:600,id:'scene1'){
           			borderPane(){
           				top(){
                    		menuBar{
                        		menu(text: "Archivo"){
                            		menuItem("Abrir",onAction: {
                                    def file = fileChooser.showOpenDialog(primaryStage)
                                    if(file){
                                        mapaDeListas = analizadorSintacticoController.crearLista(file)
                                        itemsNoTerminales = analizadorSintacticoController.crearItems(mapaDeListas)
                                        terminales = analizadorSintacticoController.obtenerTerminales(mapaDeListas)
                                        tablaLR0 = analizadorSintacticoController.crearTablaLR0(itemsNoTerminales,terminales,mapaDeListas)                                        
                                        def terminalesFieldArray = []
                                        def tokensFieldArray = []

                                        bpane.setTop(gridPane(hgap: 5, vgap: 5, padding: 20, alignment: "center",id:'gPane'){
                                            terminales.size().times { i ->
                                               label(text:terminales[i],row:0,column:i)
                                               terminalesFieldArray.add(textField(promptText:terminales[i], row: 1, column: i))
                                               tokensFieldArray.add(textField(promptText:"Token " + i, row: 2, column: i))
                                            }

                                            button(text:"Generar Analizador Léxico",onAction:{
                                              terminalesFieldArray.each{
                                                println it.text
                                              }
                                            },style:"-fx-font: 16 arial; -fx-base: #2e64fe;",row:5,column:0,columnSpan:20)

                                        })                                                                                
                                    }
                                })
                        		}
                    		}
                		}
                		tabPane(){
                    		tab(text:'Analizador Lexico'){
                        		borderPane(id:'bpane'){
                            		
                        		} 
                    		}
                    		tab(text:'Analizador Sintáctico'){
                            borderPane(id:'bpaneSintactico'){
                                top(){
                                  gridPane(hgap: 5, vgap: 0, padding: 20, alignment: "center",id:'gPane'){
                                    cadena = textField(promptText:"Teclee la cadena",row:1,column:1,columnSpan:100,halignment:"right")
                                    button(text:"Analizar cadena",onAction:{
                                                                            if(tablaLR0)
                                                                                movimientos = analizadorSintacticoController.analizaCadena(tablaLR0,cadena.text,terminales)
                                                                                //tablaMovimientos.setItems(info.listaPrueba)
                                                                                bpaneSintactico.setCenter(
                                                                                    stackPane(padding: [20,50,90,50]) {  
                                                                                      tableView(selectionMode:"single",cellSelectionEnabled: true, editable: false, items: movimientos,id:'tablaMovimientos',maxWidth:752){
                                                                                          tableColumn(editable: true, property: "pila", text: "Pila", prefWidth: 150)
                                                                                          tableColumn(editable: true, property: "simbolos", text: "Símbolos", prefWidth: 150)
                                                                                          tableColumn(editable: true, property: "entrada", text: "Entrada", prefWidth: 300)
                                                                                          tableColumn(editable: true, property: "accion", text: "Acción", prefWidth: 150)
                                                                                      }
                                                                                    }
                                                                                )
                                                                                
                                                                           }
                                                                           ,row:1,column:101,style:"-fx-font: 16 arial; -fx-base: #2e64fe;") 

                                  }                            
                                }//Fin top
                                
                            }
                    		}

                		}

           			}

           		}


           	}
		}
	
	}//End of GUI
}