package mx.ipn.hock5.domain;

public class FunctionCondCommand implements Command{
    private Function function;
    private String symbol;
    private Double[] expr;
 
    public FunctionCondCommand(Function function){
	this.function = function;
	this.expr = new Double[2];
    } 

    public void execute(){
	function.condFunction(expr[0],symbol,expr[1]);
    }

    public void setExpresions(Double e1,String symbol,Double e2){
	this.expr[0] = e1;
	this.expr[1] = e2;
	this.symbol = symbol;
    }

    public Double[] getExpresions(){
	return expr;
    }
	
    public String getSymbol(){
        return symbol;
    }
}
