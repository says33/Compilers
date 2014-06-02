package mx.ipn.analizadorSintactico.utils;

import mx.ipn.analizadorSintactico.domain.AFD;
import mx.ipn.analizadorSintactico.domain.EstadoAFD;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gamaliel
 * Date: 03/03/14
 * Time: 06:05
 * To change this template use File | Settings | File Templates.
 */
public class AFDFile {

    public AFDFile(){}

    public void createFile(AFD afd,Map<Integer,Integer> tokensAFD){
        /*File f = new File("~/Desktop/estados.txt");*/
        Integer size = afd.getAllEdosOfAFD().size();
        Integer i;
        ArrayList<ArrayList<String>> tablaAFD = new ArrayList<ArrayList<String>>();
        /*ArrayList donde se guaran los caracteres*/
        ArrayList<String> edosId = new ArrayList<String>();
        ArrayList<String> mapFilas = new ArrayList<String>();
        ArrayList<String> ascciCode = new ArrayList<String>();
        ArrayList<String> mapColumnas = new ArrayList<String>();

        /*Se llena la tabla con arreglos de String y se agregan todos los ids a la columna 1*/
        for(i=0;i<size;i++){
            edosId.add(afd.getAllEdosOfAFD().get(i).getId().toString());
            mapFilas.add(afd.getAllEdosOfAFD().get(i).getId().toString());
            tablaAFD.add(new ArrayList<String>());
        }

        //Se guarda todo el código ASCCI en una fila
        for(int j=0;j<128;j++){
            ascciCode.add(j,new Character((char)(j)).toString());
            mapColumnas.add(new Integer(j).toString());
        }

        for(i=0;i<size;i++)
            for(Integer j=0;j<128;j++)
                tablaAFD.get(i).add("-1");

        /*Se llenan todos los estados en la matriz*/
        i = 0;
        for(EstadoAFD edoAFD:afd.getAllEdosOfAFD()){
            Iterator it = edoAFD.getdTrans().keySet().iterator();
            Character key =' ';
            while (it.hasNext()){
                key = it.next().toString().charAt(0);
                System.out.println("key " + (int)key.charValue());
                System.out.println(((EstadoAFD)edoAFD.getdTrans().get(key)).getId().toString());
                tablaAFD.get(i).set((int)key.charValue(),((EstadoAFD)edoAFD.getdTrans().get(key)).getId().toString());

            }

            i++;
        }

        /*
        for(int l=0;l<tablaAFD.size();l++){
            for(int k=0;k<tablaAFD.get(l).size();k++){
                System.out.print(tablaAFD.get(l).get(k) + '\t');
            }
            System.out.println("");
        } */
        reducirFilas(tablaAFD,mapFilas,edosId);

        /*
        System.out.println("");

        for(int l=0;l<tablaAFD.size();l++){
            for(int k=0;k<tablaAFD.get(l).size();k++){
                System.out.print(tablaAFD.get(l).get(k) + '\t');
            }
            System.out.println("");
        } */

        reducirColumnas(tablaAFD,mapColumnas,ascciCode);


        for(int l=0;l<tablaAFD.size();l++){
            for(int k=0;k<tablaAFD.get(l).size();k++){
                System.out.print(tablaAFD.get(l).get(k) + '\t');
            }
            System.out.println("");
        }

        try{
            File afnTableFile = new File("//users//gamaliel//Desktop//AFNTabla.bin");
            FileOutputStream fo = new FileOutputStream(afnTableFile);
            ObjectOutputStream out = new ObjectOutputStream(fo);

            out.writeObject(tokensAFD);
            out.writeObject(mapFilas);
            out.writeObject(mapColumnas);
            out.writeObject(tablaAFD);

            fo.close();
            out.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void reducirFilas(ArrayList<ArrayList<String>> tabla,ArrayList<String> mapeoFilas,ArrayList<String> estados){
        ArrayList<String> edosAux = new ArrayList<String>();

        //mapeoFilas.set(j,new Integer(i).toString());
        for(int i=0;i<tabla.size()-1;i++){
            for(int j=i+1;j<tabla.size();j++){
                if(listEquals(tabla.get(i),tabla.get(j))){
                    mapeoFilas.set(Integer.parseInt(estados.get(j)),new Integer(i).toString());
                    estados.remove(j);
                    //mapa.put(Integer.parseInt(tabla.get(j).get(0)),Integer.parseInt(tabla.get(i).get(0)));
                    tabla.remove(j);
                    j--;
                }
                else{
                    mapeoFilas.set(Integer.parseInt(estados.get(j)),new Integer(j).toString());
                }
            }
        }
        /*
        Iterator it = mapa.keySet().iterator();
        while(it.hasNext()){
            Integer i = Integer.parseInt(it.next().toString());
            Integer val = mapa.get(i);
            System.out.println("i " + i+ " Val " + val);
        }*/
    }

    public void reducirColumnas(ArrayList<ArrayList<String>> tabla,ArrayList<String> mapeoColumnas,ArrayList<String> ascii){
        ArrayList<Integer> currentColumn = new ArrayList<Integer>();
        ArrayList<Integer> nextColumn = new ArrayList<Integer>();

        for(int i=0;i<(tabla.get(0).size()-1);i++){
            for(int l=i+1;l<(tabla.get(0).size());l++){
                for(int j=0;j<tabla.size();j++){
                    currentColumn.add(Integer.parseInt(tabla.get(j).get(i)));
                    nextColumn.add(Integer.parseInt(tabla.get(j).get(l)));
                }

                if(columnEquals(currentColumn,nextColumn)){
                    mapeoColumnas.set((int)(ascii.get(l).charAt(0)),new Integer(i).toString());
                    ascii.remove(l);

                    for(int k=0;k<tabla.size();k++)
                        tabla.get(k).remove(l);
                    l--;
                }
                else{
                    mapeoColumnas.set((int)(ascii.get(l).charAt(0)),new Integer(l).toString());
                }

                currentColumn.clear();
                nextColumn.clear();
            }

        }
    }

    public boolean columnEquals(ArrayList<Integer> c1, ArrayList<Integer> c2){
        for(int i=0;i<c1.size();i++){
            if(c1.get(i) != c2.get(i))
                return false;
        }
        return true;
    }

    public boolean listEquals(ArrayList<String> l1,ArrayList<String> l2){
        for(Integer i=1;i<l1.size();i++){
            if(!l1.get(i).equals(l2.get(i)))
                return false;
        }
        return true;
    }

}
