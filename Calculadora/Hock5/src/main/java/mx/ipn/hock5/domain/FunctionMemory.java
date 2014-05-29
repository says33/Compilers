package mx.ipn.hock5.domain;

import java.util.List;
import java.util.ArrayList;

public class FunctionMemory{
    private List<Command> history = new ArrayList<Command>();

    public List<Command> getHistory(){
 	return history;
    }
    public void store(Command cmd){
	this.history.add(cmd);
    }

    public void executeAll(){
        for(Command cmd:history)
	    cmd.execute();	
    }

    public void clearAll(){
	for(int i=0;i<history.size();)
	    history.remove(i);	
    }

}
