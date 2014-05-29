package mx.ipn.hock5.domain;

public class FunctionAsignCommand implements Command{
    private Function function;
    private char index;
    private Double expresion;

    public FunctionAsignCommand(Function function,char index,Double expresion){
        this.function = function;
	this.expresion = expresion;
	this.index = index;
    }

    public void execute(){
        function.asignFunction(index,expresion); 
    }
}
