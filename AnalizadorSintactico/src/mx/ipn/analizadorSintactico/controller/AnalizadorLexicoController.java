package mx.ipn.analizadorSintactico.controller;

import mx.ipn.analizadorSintactico.domain.AFD;
import mx.ipn.analizadorSintactico.domain.AFN;
import mx.ipn.analizadorSintactico.domain.EstadoAFD;
import mx.ipn.analizadorSintactico.service.AnalizadorLexicoService;
import mx.ipn.analizadorSintactico.utils.AFDFile;
import mx.ipn.analizadorSintactico.utils.AFNaAFD;
import mx.ipn.analizadorSintactico.utils.Scanner;
import mx.ipn.analizadorSintactico.utils.ScannerLexico;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Author: Gamaliel Jiménez
 * Date: 13/02/14
 * Time: 12:55 PM
 */
public class AnalizadorLexicoController {

    public AnalizadorLexicoController(){

    }

    public void readTable(ScannerLexico scanner){
        try{
            File afnTableFile = new File("C:\\Users\\Gamaliel\\Documents\\ESCOM\\AFNTabla.bin");
            FileInputStream fis = new FileInputStream(afnTableFile);
            ObjectInputStream input = new ObjectInputStream(fis);

            scanner.setTokensEdos((Map<Integer, Integer>) input.readObject());
            scanner.setMapeoFilas((ArrayList<String>)input.readObject());
            scanner.setMapeoColumnas((ArrayList<String>)input.readObject());
            scanner.setTablaAFD((ArrayList<ArrayList<String>>)input.readObject());

            fis.close();
            input.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createTable(ArrayList<JTextField> arrayOfRegex,ArrayList<JTextField> arrayOfToken){
        AFDFile afdFile = new AFDFile();

        AnalizadorLexicoService als = new AnalizadorLexicoService();

        Map<Integer,Integer> tokensAFN = new HashMap<Integer,Integer>();

        /*Se leen los datos del archivo*/
        //ArrayList<Object> dataFromFile = als.readFromFile();
        ArrayList<Object> dataFromGUI = als.getDataFromGUI(arrayOfRegex,arrayOfToken);
        /*Arreglo que contiene todsas las expresiones regulares*/
        //ArrayList<String> regularExpressions = (ArrayList<String>)dataFromFile.get(0);
        ArrayList<String> regularExpressions = (ArrayList<String>)dataFromGUI.get(0);
        /*Arreglo que contiene el valor de todos los tokens de las expresiones regulares*/
        //ArrayList<Integer> tokenValues = (ArrayList<Integer>)dataFromFile.get(1);
        ArrayList<Integer> tokenValues = (ArrayList<Integer>)dataFromGUI.get(1);

        /*Automata final*/
        AFN afn = als.getFinalAutomata(regularExpressions,tokensAFN,tokenValues);

        //printTokens(tokens);
        AFNaAFD afNaAFD = new AFNaAFD(tokensAFN);

        /*AFD para hacer la conversión de AFN a AFD*/
        AFD afd = new AFD();

        Map<Integer,Integer> tokensAFD = afNaAFD.convierteAFNaAFD(afn,afd);

        afd.printTransiciones();
        afdFile.createFile(afd,tokensAFD);

        //printTokens(tokensAFD);
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

    public void printTransiciones(Map<Character,Object> map){
        Iterator it = map.keySet().iterator();
        Character key;
        Integer value;

        while(it.hasNext()){
            key = new Character(it.next().toString().charAt(0));
            value = ((EstadoAFD)map.get(key)).getId();
            System.out.println("Key " + key + " Edo " + value);
        }
    }

}
