package mx.ipn.analizadorSintactico.service;

import mx.ipn.analizadorSintactico.domain.AFN;
import mx.ipn.analizadorSintactico.domain.Estado;
import mx.ipn.analizadorSintactico.utils.Thompson;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnalizadorLexicoService {

    public AFN getFinalAutomata(ArrayList<String> regularExpressions,Map<Integer,Integer> tokens,
                                ArrayList<Integer> tokenValues){

        Thompson thompson = new Thompson();

        AFN finalAFN = new AFN();

        ArrayList<AFN> afns = new ArrayList<AFN>();
        for(int i=0;i<regularExpressions.size();i++)
            afns.add(thompson.convertRE(regularExpressions.get(i)));

        uneAutomatas(afns,finalAFN);
        finalAFN.etiquetaEstados(finalAFN.getEstadoInicial(),1,tokens,tokenValues);

        /*
        //Impresión del AFN
        System.out.println("Impresion AFN");
        for(Estado e: finalAFN.getAllEdosFromAutomata()){
            System.out.println(e.getId());
            finalAFN.printTransicionesEdo(e);
        }
        System.out.println("\n");*/

        return finalAFN;

    }


    public ArrayList<Object> getDataFromGUI(ArrayList<JTextField> arrayOfRegex,ArrayList<JTextField> arrayOfTokens){
        ArrayList<Object> expresionesTokens = new ArrayList<Object>();
        ArrayList<String> lineas = new ArrayList<String>();
        ArrayList<Integer> tokens = new ArrayList<Integer>();

        for(int i=0;i<arrayOfRegex.size();i++){
            lineas.add(arrayOfRegex.get(i).getText());
            tokens.add(Integer.parseInt(arrayOfTokens.get(i).getText()));
        }

        expresionesTokens.add(lineas);
        expresionesTokens.add(tokens);

        return expresionesTokens;
    }
    /*Este método lee expresiones regulares de un archivo y las regresa*/
    public ArrayList<Object> readFromFile(){

        ArrayList<Object> expresionesTokens = new ArrayList<Object>();
        ArrayList<String> lineas = new ArrayList<String>();
        ArrayList<Integer> tokens = new ArrayList<Integer>();

        try{
            DataInputStream entrada = new DataInputStream(AnalizadorLexicoService.class.getResourceAsStream("/mx/ipn/analizadorLexico/utils/Regex.txt"));
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String linea = "";
            String[] subcadenas;
            while((linea=buffer.readLine()) !=null){
                subcadenas = linea.split("\t");
                lineas.add(subcadenas[0]);
                tokens.add(Integer.parseInt(subcadenas[1]));
            }
            entrada.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            expresionesTokens.add(lineas);
            expresionesTokens.add(tokens);
            return expresionesTokens;
        }
    }

    public void uneAutomatas(ArrayList<AFN> afns,AFN finalAFN){
        ArrayList<Estado> estados = new ArrayList<Estado>();
        ArrayList<Estado> transicionesEpsilon = new ArrayList<Estado>();
        Map<Character,Object> transiciones = new HashMap<Character, Object>();
        ArrayList<Character> alfabeto = new ArrayList<Character>();

        for(AFN afn: afns){
            /*Se agregan todos los estados del automata*/
            for(Estado e:afn.getAllEdosFromAutomata())
                estados.add(e);

            for(Character c: afn.getAlfabeto())
                if(!alfabeto.contains(c))
                    alfabeto.add(c);

            /*Se agregan los estados iniciales para hacer transiciones epsilon*/
            transicionesEpsilon.add(afn.getEstadoInicial());
        }
        transiciones.put('ε', transicionesEpsilon);
        finalAFN.getEstadoInicial().setTransiciones(transiciones);
        finalAFN.setEstados(estados);
        finalAFN.setAlfabeto(alfabeto);
    }

}
