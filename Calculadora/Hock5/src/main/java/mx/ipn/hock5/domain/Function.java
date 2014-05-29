package mx.ipn.hock5.domain;

public class Function{

    public void printFunction(Double e){
        System.out.println(e);
    }

    public void asignFunction(char v,Double e){
        int index = Character.getNumericValue(v)-Character.getNumericValue('a');
 	Memoria.memory[index] = new Double(e); 
    }

    public Boolean condFunction(Double e1,String symbol,Double e2){
       switch(symbol){
           case ">":
		    return e1 > e2;
	   case ">=":
		    return e1 >= e2;
	   case "<":
		    return e1 < e2;
	   case "<=":
		    return e1 <= e2;
	   case "==":
	   	    return e1 == e2;
       }
       return false;
    } 

    public void whileFunction(FunctionMemory fm){
        fm.getHistory().get(1).execute(); 
    }
}
