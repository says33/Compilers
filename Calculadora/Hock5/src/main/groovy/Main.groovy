import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL
import javax.swing.JFrame
import javax.swing.JOptionPane
import mx.ipn.hock5.utils.Lexer
import mx.ipn.hock5.utils.Token
import static mx.ipn.hock5.utils.Token.*

def sb = new SwingBuilder()

sb.frame(title:'Interfaz',
	 location:[400,500],
	 size:[300,300],
	 show:true,
	 defaultCloseOperation:JFrame.EXIT_ON_CLOSE){
    gridLayout(columns:2, rows:4)
    label('Palabra')
    textField(id:'word')

    button(text:'Show',actionPerformed:{
   	probarLexer(word.text)
    })
}

def probarLexer(String cadena){
    def file = new File("/Users/gamaliel/Desktop/Fichero.txt")
    file.createNewFile() 
    try{
        file.write(cadena)
    }
    catch(FileNotFoundException ex){
        println("No existe");
    }
    def reader = new BufferedReader(new FileReader("/Users/gamaliel/Desktop/Fichero.txt"))
    def lexer = new Lexer(reader)
    def finalString = ""

    while(true){
        Token token = lexer.yylex()
	if(token == null){
	    finalString+="EOF"
	    println(finalString)
	    return;
	}
	switch(token){
	    case ERROR:
		finalString+="Error, simbolo no reconocido \n"
		break;
	    case ID: case INT:
		finalString+="TOKEN: ${token} ${lexer.lexeme} \n"
		break;
	    default:
		finalString+="TOKEN ${token} \n"
	}
    }	
}
