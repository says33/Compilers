package mx.ipn.analizadorLexico.service;

import mx.ipn.analizadorLexico.utils.Thompson;
import mx.ipn.analizadorLexico.domain.AFN;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AnalizadorLexicoService {

    public void getFinalAutomata(){
        ArrayList<String> regularExpressions = readFromFile();
        Thompson thompson = new Thompson();
        ArrayList<AFN> afns = new ArrayList<AFN>();

        for(int i=0;i<regularExpressions.size();i++){
            afns.add(thompson.convertRE(regularExpressions.get(i)));
        }
    }

    /*This method will read from a file the regular expresions and will return them*/
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
