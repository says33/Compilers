package mx.ipn.analizadorLexico.service;

import mx.ipn.analizadorLexico.domain.AFN;
import mx.ipn.analizadorLexico.domain.Estado;

import java.util.ArrayList;
import java.util.Stack;
/**
 * User: Gamaliel
 * Date: 17/02/14
 * Time: 02:35 AM
 */
public class ThompsonService {

    public ThompsonService(){
    }

    public AFN operationToAFN(AFN afn,Character caracter,Stack<Character> stack,Integer id){

        if(!stack.isEmpty()){
            if(stack.peek()!=')'){
                switch (stack.peek()){
                    case '|':
                        afn = orOperation(afn,caracter,id);
                        break;
                    case '*':
                        System.out.println("");
                        break;

                }
            }
        }
        else{
            ArrayList<Character> alfabeto = new ArrayList<Character>();
            /*Si el automata no tiene alfabeto se crea el nuevo alfabeto y se asigna
              el carácter a leer */
            if(afn.getAlfabeto().size()==0){
                alfabeto.add(caracter);
                afn.setAlfabeto(alfabeto);
            }
            else{
                alfabeto = afn.getAlfabeto();
                afn.setAlfabeto(alfabeto);
            }
        }

        return afn;
    }

    public AFN orOperation(AFN afn,Character caracter,Integer id){
        Estado estadoInicial = new Estado(id,false);


        return afn;
    }
}
