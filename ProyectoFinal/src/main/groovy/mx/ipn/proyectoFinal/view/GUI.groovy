package mx.ipn.proyectoFinal.view
import groovy.swing.SwingBuilder
import static javax.swing.JFrame.EXIT_ON_CLOSE
import static java.awt.GridBagConstraints.*
import javax.swing.*
import javax.swing.table.DefaultTableModel
import java.awt.*
import groovy.beans.Bindable 
import java.beans.PropertyChangeListener
import mx.ipn.proyectoFinal.controller.ParserRelacionalController

class GUI{

	def parserRelacionalController

    def GUI(){		
		parserRelacionalController = new ParserRelacionalController()		
		initComponents();		
    }

    def initComponents(){
		def defaultInsets = [0,0,10,0]
		def swing = new SwingBuilder()
		swing.edt{	    	    	
	    	frame(title:'Proyecto Final',size:[800,600],locationRelativeTo:null,defaultCloseOperation:EXIT_ON_CLOSE,show:true){
	    		tabbedPane(){
	    			panel(constraints:BorderLayout.CENTER,
	    				  border: compoundBorder([emptyBorder(10), titledBorder('Datos para la conexión a la Base de Datos')]),
	    				  name:'Configuración',	    				  
	    				  background: java.awt.Color.WHITE){
		    				
		    				label(
								text:"Usuario",
								constraints: gbc(gridx:0,gridy:0,fill:HORIZONTAL,insets:defaultInsets)
							)
							textField(
 								id:'userTextField',
 								preferredSize:new Dimension(100,30), 								
 								constraints:gbc(gridx:1,gridy:0,gridwidth:REMAINDER,fill:HORIZONTAL,insets:defaultInsets)
   							)
   							label(								
								text:"Password",
								constraints: gbc(gridx:0,gridy:1,fill:HORIZONTAL,insets:defaultInsets)
							)
							passwordField(
								id:'passwordTextField',
 								preferredSize:new Dimension(100,30),
 								constraints:gbc(gridx:1,gridy:1,gridwidth:REMAINDER,fill:HORIZONTAL,insets:defaultInsets)
   							)
   							label(
								text:"Nombre de la base",
								constraints: gbc(gridx:0,gridy:2,fill:HORIZONTAL,insets:defaultInsets)
							)
							textField(
 								id:'dataBaseNameTextField',
 								preferredSize:new Dimension(100,30),
 								constraints:gbc(gridx:1,gridy:2,gridwidth:REMAINDER,fill:HORIZONTAL,insets:defaultInsets)
   							)						
							button(
								text:'Conectar',
								constraints:gbc(gridx:0,gridy:3,insets:[10,0,0,0],gridwidth:3,anchor:WEST),
								actionPerformed:{
									parserRelacionalController.createPropertiesFile(userTextField.text,
																					passwordTextField.text,
																					dataBaseNameTextField.text)
									def tables,columns
									if((tables = parserRelacionalController.getTablesOfDataBase())){										
										cboxTables.setModel(new DefaultComboBoxModel(tables as Object[]))
										cboxTables.enabled = true
										columns = parserRelacionalController.getColumnsFromTable(tables[0])
										cboxColumns.setModel(new DefaultComboBoxModel(columns as Object[]))
										cboxColumns.enabled = true
									}
								}
							)
	    			}
	    			panel(name:'Parser'){	    							
	    				vbox(){	    					
	    					tableLayout(){
	    						tr{									
									td{label text:'Tablas'}
									td{
										comboBox(id:'cboxTables',items:[],enabled:false,actionPerformed:{ e ->
											def columns = parserRelacionalController.getColumnsFromTable(e.getSource().selectedItem)
											cboxColumns.setModel(new DefaultComboBoxModel(columns as Object[]))
										})										
									}
									td{
										button text:'Agregar Tabla',actionPerformed:{
											query.append(cboxTables.selectedItem)
										}
									}
									td{label text:'Columnas'}
									td{
										comboBox(id:'cboxColumns',items:[],enabled:false)
									}
									td{
										button text:'Agregar Columna',actionPerformed:{ query.append(cboxColumns.selectedItem) }
									}
								}
	    					}

	    					tableLayout(){
	    						tr{
	    							td{button text:'π',actionPerformed:{ query.append("\u03C0") }}
	    							td{button text:'σ',actionPerformed:{ query.append("\u03C3") }}
	    							td{button text:'⋈',actionPerformed:{ query.append("\u22C8") }}
	    							td{button text:'Х',actionPerformed:{ query.append("\u0425") }}
	    						}
	    						tr{
	    							td(colspan:8){
	    								scrollPane(constraints:CENTER, border:BorderFactory.createRaisedBevelBorder()) {
                        					textArea(id:'query',columns:50,rows:5,lineWrap:true,wrapStyleWord:true)
                    					}
	    							}
	    						}
	    						tr{
	    							td{
	    								button text:'Limpiar',actionPerformed:{ query.text='' }	
	    							}
	    							td{
	    								button text:'Ejecutar consulta',actionPerformed:{
	    									def queryString
	    									if((queryString = parserRelacionalController.getSQLString(query.text))){
	    										sqlQuery.text = queryString
	    										def datos = parserRelacionalController.executeSQLQuery(queryString)
	    										
	    										String[] columnNames = datos.columns.toArray()   									        										
	    										
        										
        										

	    										def model = new DefaultTableModel(datos.data as Object[][],columnNames)
	    										dataTable.setModel(model)

	    									}
	    								}
	    							}
	    						}
	    						tr{
	    							td(colspan:8){
	    								scrollPane(constraints:CENTER, border:BorderFactory.createRaisedBevelBorder()) {
                        					textArea(id:'sqlQuery',columns:50,rows:5,lineWrap:true,wrapStyleWord:true)
                    					}
	    							}	
	    						}
	    						tr{
	    							td{}
	    							td(colspan:8){
	    								scrollPane(preferredSize:[600,300]) {
	    									table(id:'dataTable'){
												    									                        					
	    									}
	    								}
	    							}
	    						}
	    					}
	    				}

	    			}
	    		}			
	    	}	    	
		}
    }

}

