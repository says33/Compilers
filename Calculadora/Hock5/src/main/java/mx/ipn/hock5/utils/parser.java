
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Thu May 29 12:52:05 CDT 2014
//----------------------------------------------------

package mx.ipn.hock5.utils;

import mx.ipn.hock5.domain.*;
import java_cup.runtime.*;
import javax.swing.JTextArea;
import java.util.ArrayList;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Thu May 29 12:52:05 CDT 2014
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\030\000\002\002\004\000\002\002\003\000\002\003" +
    "\002\000\002\003\005\000\002\003\005\000\002\005\005" +
    "\000\002\006\004\000\002\006\005\000\002\006\005\000" +
    "\002\010\007\000\002\010\007\000\002\011\003\000\002" +
    "\007\002\000\002\007\004\000\002\007\004\000\002\007" +
    "\004\000\002\004\003\000\002\004\005\000\002\004\005" +
    "\000\002\004\005\000\002\004\005\000\002\004\005\000" +
    "\002\004\004\000\002\004\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\057\000\014\002\uffff\004\uffff\005\uffff\017\uffff\030" +
    "\uffff\001\002\000\014\002\000\004\010\005\014\017\012" +
    "\030\007\001\002\000\004\002\006\001\002\000\004\002" +
    "\001\001\002\000\004\031\056\001\002\000\012\022\022" +
    "\025\023\030\020\033\021\001\002\000\004\034\060\001" +
    "\002\000\022\004\ufff5\005\ufff5\016\ufff5\017\ufff5\022\ufff5" +
    "\025\ufff5\030\ufff5\033\ufff5\001\002\000\004\034\047\001" +
    "\002\000\004\025\ufff6\001\002\000\004\025\017\001\002" +
    "\000\010\004\010\005\014\017\012\001\002\000\012\022" +
    "\022\025\023\030\020\033\021\001\002\000\040\004\ufff1" +
    "\005\ufff1\010\ufff1\011\ufff1\016\ufff1\017\ufff1\021\ufff1\022" +
    "\ufff1\023\ufff1\024\ufff1\025\ufff1\026\ufff1\030\ufff1\033\ufff1" +
    "\034\ufff1\001\002\000\040\004\uffea\005\uffea\010\uffea\011" +
    "\uffea\016\uffea\017\uffea\021\uffea\022\uffea\023\uffea\024\uffea" +
    "\025\uffea\026\uffea\030\uffea\033\uffea\034\uffea\001\002\000" +
    "\012\022\022\025\023\030\020\033\021\001\002\000\012" +
    "\022\022\025\023\030\020\033\021\001\002\000\016\010" +
    "\025\011\030\021\026\022\027\023\032\024\031\001\002" +
    "\000\012\022\022\025\023\030\020\033\021\001\002\000" +
    "\012\022\022\025\023\030\020\033\021\001\002\000\012" +
    "\022\022\025\023\030\020\033\021\001\002\000\012\022" +
    "\022\025\023\030\020\033\021\001\002\000\012\022\022" +
    "\025\023\030\020\033\021\001\002\000\012\022\022\025" +
    "\023\030\020\033\021\001\002\000\040\004\uffee\005\uffee" +
    "\010\uffee\011\uffee\016\uffee\017\uffee\021\uffee\022\uffee\023" +
    "\uffee\024\uffee\025\uffee\026\uffee\030\uffee\033\uffee\034\uffee" +
    "\001\002\000\040\004\uffed\005\uffed\010\uffed\011\uffed\016" +
    "\uffed\017\uffed\021\uffed\022\uffed\023\uffed\024\uffed\025\uffed" +
    "\026\uffed\030\uffed\033\uffed\034\uffed\001\002\000\014\021" +
    "\026\022\027\023\032\024\031\026\036\001\002\000\010" +
    "\004\ufff7\005\ufff7\017\ufff7\001\002\000\040\004\uffef\005" +
    "\uffef\010\uffef\011\uffef\016\uffef\017\uffef\021\uffef\022\uffef" +
    "\023\032\024\031\025\uffef\026\uffef\030\uffef\033\uffef\034" +
    "\uffef\001\002\000\040\004\ufff0\005\ufff0\010\ufff0\011\ufff0" +
    "\016\ufff0\017\ufff0\021\ufff0\022\ufff0\023\032\024\031\025" +
    "\ufff0\026\ufff0\030\ufff0\033\ufff0\034\ufff0\001\002\000\014" +
    "\021\026\022\027\023\032\024\031\026\042\001\002\000" +
    "\010\004\ufff8\005\ufff8\017\ufff8\001\002\000\014\021\026" +
    "\022\027\023\032\024\031\026\044\001\002\000\040\004" +
    "\uffec\005\uffec\010\uffec\011\uffec\016\uffec\017\uffec\021\uffec" +
    "\022\uffec\023\uffec\024\uffec\025\uffec\026\uffec\030\uffec\033" +
    "\uffec\034\uffec\001\002\000\040\004\uffeb\005\uffeb\010\uffeb" +
    "\011\uffeb\016\uffeb\017\uffeb\021\uffeb\022\uffeb\023\uffeb\024" +
    "\uffeb\025\uffeb\026\uffeb\030\uffeb\033\uffeb\034\uffeb\001\002" +
    "\000\024\004\ufffa\005\ufffa\016\ufffa\017\ufffa\022\ufffa\025" +
    "\ufffa\030\ufffa\033\ufffa\034\ufffa\001\002\000\014\002\ufffd" +
    "\004\ufffd\005\ufffd\017\ufffd\030\ufffd\001\002\000\022\004" +
    "\010\005\014\016\051\017\012\022\022\025\023\030\054" +
    "\033\021\001\002\000\024\004\ufff9\005\ufff9\016\ufff9\017" +
    "\ufff9\022\ufff9\025\ufff9\030\ufff9\033\ufff9\034\ufff9\001\002" +
    "\000\022\004\ufff3\005\ufff3\016\ufff3\017\ufff3\022\ufff3\025" +
    "\ufff3\030\ufff3\033\ufff3\001\002\000\022\004\ufff4\005\ufff4" +
    "\016\ufff4\017\ufff4\022\ufff4\025\ufff4\030\ufff4\033\ufff4\001" +
    "\002\000\032\004\ufff1\005\ufff1\016\ufff1\017\ufff1\021\ufff1" +
    "\022\ufff1\023\ufff1\024\ufff1\025\ufff1\030\ufff1\031\056\033" +
    "\ufff1\001\002\000\030\004\ufff2\005\ufff2\016\ufff2\017\ufff2" +
    "\021\026\022\027\023\032\024\031\025\ufff2\030\ufff2\033" +
    "\ufff2\001\002\000\012\022\022\025\023\030\020\033\021" +
    "\001\002\000\032\004\ufffc\005\ufffc\016\ufffc\017\ufffc\021" +
    "\026\022\027\023\032\024\031\025\ufffc\030\ufffc\033\ufffc" +
    "\034\ufffc\001\002\000\014\002\ufffe\004\ufffe\005\ufffe\017" +
    "\ufffe\030\ufffe\001\002\000\032\004\ufffb\005\ufffb\016\ufffb" +
    "\017\ufffb\021\026\022\027\023\032\024\031\025\ufffb\030" +
    "\ufffb\033\ufffb\034\ufffb\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\057\000\006\002\004\003\003\001\001\000\010\005" +
    "\010\006\012\011\014\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\004\060\001\001\000" +
    "\002\001\001\000\004\007\047\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\010\015\001\001\000\006\006" +
    "\045\011\014\001\001\000\004\004\023\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\004\044\001\001\000" +
    "\004\004\042\001\001\000\002\001\001\000\004\004\040" +
    "\001\001\000\004\004\037\001\001\000\004\004\036\001" +
    "\001\000\004\004\034\001\001\000\004\004\033\001\001" +
    "\000\004\004\032\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\012\004\054\005\051\006" +
    "\052\011\014\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\004\056\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


 
   private JTextArea textArea;
   private FunctionMemory functionMemory;
   
   public void setJTextArea(JTextArea textArea){
       this.textArea = textArea;
   }

   public void setTextToTextArea(String texto){
       this.textArea.append(texto);
   }

   public void setFunctionMemory(FunctionMemory functionMemory){
       this.functionMemory = functionMemory;
   }

   public void saveFunctionInMemory(Command cmd){
       this.functionMemory.store(cmd);
   }

   public FunctionMemory getFunctionMemory(){
       return functionMemory;
   }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // expr ::= NUMBER 
            {
              Double RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Double n = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT=new Double(n); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // expr ::= MINUS expr 
            {
              Double RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Double e = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT= -1*(new Double(e)); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // expr ::= LPAREN expr RPAREN 
            {
              Double RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Double e = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT=e; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // expr ::= expr DIV expr 
            {
              Double RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Double e1 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Double e2 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT=new Double(e1) / new Double(e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // expr ::= expr TIMES expr 
            {
              Double RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Double e1 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Double e2 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT=new Double(e1) * new Double(e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // expr ::= expr MINUS expr 
            {
              Double RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Double e1 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Double e2 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT=new Double(e1) - new Double(e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // expr ::= expr PLUS expr 
            {
              Double RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Double e1 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Double e2 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT=new Double(e1) + new Double(e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // expr ::= VAR 
            {
              Double RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		char v = (char)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = Memoria.memory[(int)v - (int)('a')]; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // stmtlist ::= stmtlist expr 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmtlist",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // stmtlist ::= stmtlist asgn 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmtlist",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // stmtlist ::= stmtlist stmt 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmtlist",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // stmtlist ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmtlist",5, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // while ::= WHILE 
            {
              Object RESULT =null;
		
                    Function function = new Function();
	      	    Command whileFunction = new FunctionWhileCommand(function,parser.getFunctionMemory());
	       	    parser.saveFunctionInMemory(whileFunction);
		
              CUP$parser$result = parser.getSymbolFactory().newSymbol("while",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // cond ::= LPAREN expr GE expr RPAREN 
            {
              Object RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Double e1 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Double e2 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
	     Function function = new Function();
	 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("cond",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // cond ::= LPAREN expr GT expr RPAREN 
            {
              Object RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Double e1 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Double e2 = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		  Function function = new Function();
	     FunctionCondCommand functionCondCommand = new FunctionCondCommand(function);
	      
	     Command condFunction = functionCondCommand; 
	     parser.saveFunctionInMemory(condFunction);
         
              CUP$parser$result = parser.getSymbolFactory().newSymbol("cond",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // stmt ::= LKEY stmtlist RKEY 
            {
              Object RESULT =null;
		 System.out.println("Lista"); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // stmt ::= while cond stmt 
            {
              Object RESULT =null;
		
	      parser.getFunctionMemory().getHistory().get(0).execute();
	  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // stmt ::= PRINT expr 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Double e = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
             Function function = new Function();
	     Command printCommand = new FunctionPrintCommand(function,e); 
	     parser.saveFunctionInMemory(printCommand);
	     parser.setTextToTextArea(e.toString()+"\n");
	 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // asgn ::= VAR ASSIGN expr 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		char v = (char)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Double e = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
             Function function = new Function();
	     Command asignCommand = new FunctionAsignCommand(function,v,e);
	     parser.saveFunctionInMemory(asignCommand);
         
              CUP$parser$result = parser.getSymbolFactory().newSymbol("asgn",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // list ::= list stmt SALTOLINEA 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("list",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // list ::= list asgn SALTOLINEA 
            {
              Object RESULT =null;
		
	      parser.getFunctionMemory().executeAll();
	      parser.getFunctionMemory().clearAll();
	  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("list",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // list ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("list",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // prog ::= list 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("prog",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= prog EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}
