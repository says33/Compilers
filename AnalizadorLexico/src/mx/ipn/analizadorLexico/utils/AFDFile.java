package mx.ipn.analizadorLexico.utils;
import mx.ipn.analizadorLexico.domain.AFD;
import mx.ipn.analizadorLexico.domain.EstadoAFD;

import java.io.*;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: gamaliel
 * Date: 03/03/14
 * Time: 06:05
 * To change this template use File | Settings | File Templates.
 */
public class AFDFile {

    public AFDFile(){}

    public void createFile(AFD afd){
        File f = new File("C:/Files/estados.txt");

        try{
            Integer size =afd.getAllEdosOfAFD().size();
            String[][] line = new String[size][129];
            Integer counter = 0;
            f.createNewFile();
            PrintWriter fo = new PrintWriter(new FileOutputStream(f));

            for(EstadoAFD a:afd.getAllEdosOfAFD()){
                Iterator it = a.getdTrans().keySet().iterator();
                Character key =' ';
                line[counter][0] = a.getId().toString()+"\t";
                while (it.hasNext()){
                    key = it.next().toString().charAt(0);
                    line[counter][(int)key.charValue()] = ((EstadoAFD)a.getdTrans().get(key)).getId().toString();
                }
                counter++;
            }

            for(int i=0;i<afd.getAllEdosOfAFD().size();i++)
                for(int j=0;j<129;j++)
                    if(line[i][j] == null)
                        line[i][j] = "-1";

            for(int i=0;i<afd.getAllEdosOfAFD().size();i++){
                for(int j=0;j<129;j++){
                    fo.print(line[i][j]+" ");
                }
                fo.println("");
                fo.println("");
            }


            fo.close();
        }
        catch(Exception e){

        }
    }

}
