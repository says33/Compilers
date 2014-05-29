package mx.ipn.hock5.domain;

public class FunctionWhileCommand implements Command{
    Function function;
    FunctionMemory mem;

    public FunctionWhileCommand(Function function,FunctionMemory mem){
        this.function = function;
        this.mem = mem;
    }

    public void execute(){
        function.whileFunction(this.mem);
    }
} 
