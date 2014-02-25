package mx.ipn.analizadorLexico.service;

import mx.ipn.analizadorLexico.domain.Estado;
import mx.ipn.analizadorLexico.utils.Thompson;
import mx.ipn.analizadorLexico.domain.AFN;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnalizadorLexicoService {

    public void getFinalAutomata(){
        ArrayList<String> regularExpressions = readFromFile();
        Thompson thompson = new Thompson();
        /*Lista que guarda todas las transiciones Epsilon*/
        ArrayList<Estado> estados = new ArrayList<Estado>();
        ArrayList<Estado> transicionesEpsilon = new ArrayList<Estado>();
        Map<Character,Object> transiciones = new HashMap<Character, Object>();


        AFN finalAFN = new AFN();

        ArrayList<AFN> afns = new ArrayList<AFN>();
        for(int i=0;i<regularExpressions.size();i++){
            afns.add(thompson.convertRE(regularExpressions.get(i)));
        }

        for(AFN afn: afns){
            /*Se agregan todos los estados del automata*/
            for(Estado e:afn.getAllEdosFromAutomata())
                estados.add(e);

            /*Se agregan los estados iniciales para hacer transiciones epsilon*/
            transicionesEpsilon.add(afn.getEstadoInicial());
        }

        transiciones.put('ε',transicionesEpsilon);
        finalAFN.getEstadoInicial().setTransiciones(transiciones);
        finalAFN.setEstados(estados);

        finalAFN.etiquetaEstados(finalAFN.getEstadoInicial(),1);

        for(Estado e: finalAFN.getAllEdosFromAutomata()){
            System.out.println("--> " + e.getId());
            finalAFN.printTransicionesEdo(e);
        }
    }

    /*Este método lee expresiones regulares de un archivo y las regresa*/
    public ArrayList<String> readFromFile(){

        ArrayList<String> lineas = new ArrayList<String>();

        try{
            DataInputStream entrada = new DataInputStream(AnalizadorLexicoService.class.getResourceAsStream("/mx/ipn/analizadorLexico/utils/Regex.txt"));
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String linea = "";
            while((linea=buffer.readLine()) !=null){
                lineas.add(linea.split("\t")[0]);
            }
            entrada.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            return lineas;
        }
    }


}
