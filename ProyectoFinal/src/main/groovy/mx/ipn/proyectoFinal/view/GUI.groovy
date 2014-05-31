package mx.ipn.proyectoFinal.view
import groovy.swing.SwingBuilder
import static javax.swing.JFrame.EXIT_ON_CLOSE
import java.awt.*

class GUI{

    def GUI(){
		initComponents();
    }

    def initComponents(){
		def swing = new SwingBuilder()
		swing.edt{	    	    	
	    	frame(title:'Proyecto Final',size:[800,600],locationRelativeTo:null,defaultCloseOperation:EXIT_ON_CLOSE,show:true){
	    		tabbedPane(){
	    			panel(name:'Configuración'){

	    			}
	    			panel(name:'Parser'){
	    				vbox{
	    					tableLayout{
	    						tr{
	    							td{ button text:'π',actionPerformed:{}}
	    							td{button text:'σ',actionPerformed:{}}
									td{button text:'⋈',actionPerformed:{}}
									td{button text:'∪',actionPerformed:{}}
	    						}

	    					}
	    				}

	    			}
	    		}

			/*
				borderLayout(vgap: 5)
				panel(constraints: BorderLayout.CENTER,border:compoundBorder([emptyBorder(10),titledBorder('Algebra Relacional')])){
				    tableLayout{
					    tr{
							td{
								
					    	}
					    	
							
					    	
						}
						tr{
					    	td(colspan:20){
					    		textArea(id:'stringToParse',lineWrap:true,wrapStyleWord:true,columns:40,rows:4,editable:true)
					    	}
						}
				  	}    		          	
				}
			*/
	    	}	    	
		}
    }

}

