package mx.ipn.analizadorLexico.controller;
import mx.ipn.analizadorLexico.domain.AFD;
import mx.ipn.analizadorLexico.domain.AFN;
import mx.ipn.analizadorLexico.service.AnalizadorLexicoService;
import mx.ipn.analizadorLexico.utils.AFDFile;
import mx.ipn.analizadorLexico.utils.AFNaAFD;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;


/**
 * Author: Gamaliel Jiménez
 * Date: 13/02/14
 * Time: 12:55 PM
 */
public class AnalizadorLexicoController {

    public AnalizadorLexicoController(){

    }

    public void createTable(){
        AFDFile afdFile = new AFDFile();

        AnalizadorLexicoService als = new AnalizadorLexicoService();

        Map<Integer,Integer> tokensAFN = new HashMap<Integer,Integer>();

        /*Se leen los datos del archivo*/
        ArrayList<Object> dataFromFile = als.readFromFile();
        /*Arreglo que contiene todsas las expresiones regulares*/
        ArrayList<String> regularExpressions = (ArrayList<String>)dataFromFile.get(0);
        /*Arreglo que contiene el valor de todos los tokens de las expresiones regulares*/
        ArrayList<Integer> tokenValues = (ArrayList<Integer>)dataFromFile.get(1);

        /*Automata final*/
        AFN afn = als.getFinalAutomata(regularExpressions,tokensAFN,tokenValues);

        //printTokens(tokens);
        AFNaAFD afNaAFD = new AFNaAFD(tokensAFN);

        /*AFD para hacer la conversión de AFN a AFD*/
        AFD afd = new AFD();

        Map<Integer,Integer> tokensAFD = afNaAFD.convierteAFNaAFD(afn,afd);
        //afd.printTransiciones();
        afdFile.createFile(afd,tokensAFD);

        printTokens(tokensAFD);
    }

    public void printTokens(Map<Integer,Integer> map){
        Iterator it = map.keySet().iterator();
        Integer key;
        Integer value;

        while(it.hasNext()){
            key = Integer.parseInt(it.next().toString());
            value=map.get(key);
            System.out.println("Estado " + key + " Token " + value);
        }
    }



}
