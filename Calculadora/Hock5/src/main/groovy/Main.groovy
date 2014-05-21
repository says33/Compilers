import mx.ipn.hock5.utils.Lexico
import mx.ipn.hock5.utils.parser

class Main{

    static main(def args){

	def lexico = null
	def parser = null

	try{
	    lexico = new Lexico(new StringReader("4.5 + 5"))
	    parser = new parser(lexico)
	    parser.parse()
    	}
	catch(Exception ex){
	    System.out.println(ex.toString())
   	}

	def gui = new GUI()
    }
}
