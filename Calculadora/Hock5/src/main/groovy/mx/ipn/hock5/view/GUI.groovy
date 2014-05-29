import groovy.swing.SwingBuilder
import mx.ipn.hock5.controller.HockController;
import javax.swing.JTextArea
import static javax.swing.JFrame.EXIT_ON_CLOSE
import java.awt.*

class GUI{
	
	def hockController
	
	def GUI(){
	    hockController = new HockController()
	    initComponents()
	}

	def initComponents(){
	    def swing = new SwingBuilder()
	    swing.edt{
	        frame(title:'Hock5',size:[350,230],locationRelativeTo:null,
		      defaultCloseOperation:EXIT_ON_CLOSE,show:true){
		          borderLayout(vgap: 5)
		          panel(constraints: BorderLayout.CENTER, 
			        border:compoundBorder([emptyBorder(10),titledBorder('Hock5')])){
		     		tableLayout{
		    		    tr{
				        	td{
				    	    	label 'Cadena:'
				        	}
							td{
					    		textArea(id:'cadena', lineWrap:true,wrapStyleWord:true, columns:20, rows:4,editable:true)				   
							}
			 	    	}
				    	tr{
							td{
					    		    button text:'Analizar',actionPerformed:{												               resultado.text = ""	
							        cadena.text.split('\n').each{
								    if(it)
							                hockController.parseString(it+"\n",resultado)
							        }
					    		    }
							}
				    	}
				    	tr{
							td{
					    		label 'Resultado'
							}
				        	td{
					    		textArea(id:'resultado',lineWrap:true,wrapStyleWord:true,columns:20,rows:4,editable:false)  
							}
				    	}
			  	 	}
		     	}		
	        }
	    }
	}
}
