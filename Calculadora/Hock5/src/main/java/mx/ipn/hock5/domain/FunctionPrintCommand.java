package mx.ipn.hock5.domain;

public class FunctionPrintCommand implements Command{
    private Function function;
    private Double value;

    public FunctionPrintCommand(Function function,Double value){
        this.function = function;
	this.value = value;
    }

    public void execute(){
        function.printFunction(value);
    }

}
