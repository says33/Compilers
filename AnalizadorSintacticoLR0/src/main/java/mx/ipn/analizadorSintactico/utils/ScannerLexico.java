package mx.ipn.analizadorSintactico.utils;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gamaliel
 * Date: 10/04/14
 * Time: 05:59
 * To change this template use File | Settings | File Templates.
 */
public class ScannerLexico {
    private Map<Integer,Integer> tokensEdos;
    private ArrayList<String> mapeoFilas;
    private ArrayList<String> mapeoColumnas;
    private ArrayList<ArrayList<String>> tablaAFD;
    private Integer edo_actual = 0;
    private Integer edo_aceptacion_ant = -1;
    private Integer prevPosition=0;
    private Integer currentPosition = 0;
    private String currentToken="";

    public ScannerLexico(){}

    public ScannerLexico(String cadena){}

    public ArrayList<ArrayList<String>> tokenizaCadena(String cadena){

        ArrayList<ArrayList<String>> tokensAndLexemas = new ArrayList<ArrayList<String>>();

        /*
        for(int k=0;k<tablaAFD.size();k++){
            for(int l=0;l<tablaAFD.get(k).size();l++){
                System.out.print(tablaAFD.get(k).get(l)+" ");
            }
            System.out.println("");
        } */

        Integer i = 0;

        while (i<cadena.length()){

            if(!tablaAFD.get(new Integer(mapeoFilas.get(edo_actual))).get(new Integer(mapeoColumnas.get((int) cadena.charAt(i)))).equals("-1")){

                edo_aceptacion_ant = edo_actual;
                edo_actual = new Integer(tablaAFD.get(new Integer(mapeoFilas.get(edo_actual))).get(new Integer(mapeoColumnas.get((int)cadena.charAt(i)))));
                i++;

                if(tokensEdos.get(edo_actual) != null){
                    currentPosition = i;
                    currentToken = tokensEdos.get(edo_actual).toString();
                }
            }
            else{
                /*Ningún estado de aceptación se encontro*/
                if(edo_aceptacion_ant == -1){
                    currentToken = "ERROR";
                    edo_actual = 0;
                    i++;
                    currentPosition=i;
                    tokensAndLexemas.add(new ArrayList<String>());
                    tokensAndLexemas.get(tokensAndLexemas.size()-1).add(getLexema(prevPosition,currentPosition,cadena));
                    tokensAndLexemas.get(tokensAndLexemas.size()-1).add(currentToken);
                }
                else{
                    tokensAndLexemas.add(new ArrayList<String>());
                    tokensAndLexemas.get(tokensAndLexemas.size()-1).add(getLexema(prevPosition,currentPosition,cadena));
                    tokensAndLexemas.get(tokensAndLexemas.size()-1).add(currentToken);

                    edo_aceptacion_ant = -1;
                    edo_actual = 0;
                    i = currentPosition;
                    prevPosition = i;
                }
                prevPosition=i;
                currentPosition=i;

            }

        }
        if(currentPosition != prevPosition){
            tokensAndLexemas.add(new ArrayList<String>());
            tokensAndLexemas.get(tokensAndLexemas.size()-1).add(getLexema(prevPosition,currentPosition,cadena));
            tokensAndLexemas.get(tokensAndLexemas.size()-1).add(currentToken);
        }

        tokensAndLexemas.add(new ArrayList<String>());
        tokensAndLexemas.get(tokensAndLexemas.size()-1).add("$");
        tokensAndLexemas.get(tokensAndLexemas.size()-1).add(new Integer(0).toString());

        return tokensAndLexemas;
    }

    public String getLexema(int prevPosition,int currentPosition,String cadena){
        return cadena.substring(prevPosition,currentPosition);
    }

    public Map<Integer, Integer> getTokensEdos() {
        return tokensEdos;
    }

    public void setTokensEdos(Map<Integer, Integer> tokensEdos) {
        this.tokensEdos = tokensEdos;
    }

    public ArrayList<String> getMapeoFilas() {
        return mapeoFilas;
    }

    public void setMapeoFilas(ArrayList<String> mapeoFilas) {
        this.mapeoFilas = mapeoFilas;
    }

    public ArrayList<String> getMapeoColumnas() {
        return mapeoColumnas;
    }

    public void setMapeoColumnas(ArrayList<String> mapeoColumnas) {
        this.mapeoColumnas = mapeoColumnas;
    }

    public ArrayList<ArrayList<String>> getTablaAFD() {
        return tablaAFD;
    }

    public void setTablaAFD(ArrayList<ArrayList<String>> tablaAFD) {
        this.tablaAFD = tablaAFD;
    }

    public void cleanScanner() {
        this.edo_actual = 0;
        this.edo_aceptacion_ant = -1;
        this.prevPosition=0;
        this.currentPosition = 0;
        this.currentToken="";
    }
}
