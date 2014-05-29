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
	    frame(title:'Proyecto Final',size:[600,400],locationRelativeTo:null,defaultCloseOperation:EXIT_ON_CLOSE,show:true){
		borderLayout(vgap: 5)
		panel(constraints: BorderLayout.CENTER,
		      border:compoundBorder([emptyBorder(10),titledBorder('Algebra Relacional')])){
		          tableLayout{
				tr{
				    td{
				    	button text:'π',actionPerformed:{
					    stringToParse.append('π')
					}
				    }
				    td{
					button text:'σ',actionPerformed:{
					    stringToParse.append('σ')
					}
				    }
				    td{
					button text:'⋈',actionPerformed:{
					    stringToParse.append('⋈')
					}
				    }
				    td{
					button text:'∪',actionPerformed:{
					    stringToParse.append('∪')
					}
				    }
				}
				tr{
				    td(colspan:20){
				    	textArea(id:'stringToParse',lineWrap:true,wrapStyleWord:true,columns:40,rows:4,editable:true)
				    }
				}
			  }    		          	
		      }
	    }
	}
    }
}

