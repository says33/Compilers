package mx.ipn.analizadorLexico.service;
import mx.ipn.analizadorLexico.domain.AFN;
import mx.ipn.analizadorLexico.domain.AFD;
import mx.ipn.analizadorLexico.domain.Estado;
import mx.ipn.analizadorLexico.domain.EstadoAFD;
import mx.ipn.analizadorLexico.utils.AFNaAFD;
import mx.ipn.analizadorLexico.utils.Thompson;
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

        AFN finalAFN = new AFN();
        AFD afd = new AFD();
        AFNaAFD convertidor = new AFNaAFD();

        ArrayList<AFN> afns = new ArrayList<AFN>();
        for(int i=0;i<regularExpressions.size();i++)
            afns.add(thompson.convertRE(regularExpressions.get(i)));

        uneAutomatas(afns,finalAFN);
        finalAFN.etiquetaEstados(finalAFN.getEstadoInicial(),1);

        /*
        for(Estado e: finalAFN.getAllEdosFromAutomata()){
            System.out.println(e.getId());
            finalAFN.printTransicionesEdo(e);
        }
        System.out.println("\n");
         */
        convertidor.convierteAFNaAFD(finalAFN,afd);

        for(Estado e:afd.getEstadoInicial().getSubEstados())
            System.out.println(e.getId());
        System.out.println("\n");
        for(EstadoAFD e: afd.getEstados()){
            for(Estado se: e.getSubEstados()){
                System.out.println(se.getId());
            }
            System.out.println("\n");
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
