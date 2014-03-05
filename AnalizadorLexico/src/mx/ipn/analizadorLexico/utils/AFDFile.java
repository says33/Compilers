package mx.ipn.analizadorLexico.utils;
import mx.ipn.analizadorLexico.domain.AFD;
import mx.ipn.analizadorLexico.domain.EstadoAFD;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void createFile(AFD afd){
        /*File f = new File("~/Desktop/estados.txt");*/

        Integer size = afd.getAllEdosOfAFD().size();
        ArrayList<ArrayList<String>> tablaAFD = new ArrayList<ArrayList<String>>();
        Integer i;
        Map<Integer,Integer> equivalencias = new HashMap<Integer, Integer>();

        /*Se llena la tabla con arreglos de String*/
        for(i=0;i<size+1;i++)
            tablaAFD.add(new ArrayList<String>());


        for(Integer j=0;j<129;j++)
            tablaAFD.get(0).add(" ");

        for(i=1;i<size+1;i++)
            for(Integer j=0;j<129;j++)
                tablaAFD.get(i).add("-1");


        for(Character c: afd.getAlfabeto())
            tablaAFD.get(0).set((int)c.charValue()+1,c.toString());


        i = 1;
        for(EstadoAFD edoAFD:afd.getAllEdosOfAFD()){
            Iterator it = edoAFD.getdTrans().keySet().iterator();
            Character key =' ';
            tablaAFD.get(i).set(0,edoAFD.getId().toString());

            while (it.hasNext()){
                key = it.next().toString().charAt(0);
                tablaAFD.get(i).set((int)key.charValue()+1,((EstadoAFD)edoAFD.getdTrans().get(key)).getId().toString());
            }
            i++;
        }


        for(i=0;i<size+1;i++){
            for(Integer j=0;j<tablaAFD.get(i).size();j++)
                System.out.print(tablaAFD.get(i).get(j) + '\t');
            System.out.println("");
        }

        Integer[] minAndMax = encuentraMaxYMin(tablaAFD);
        Integer dif = minAndMax[1] - minAndMax[0];
        System.out.println("Min " + minAndMax[0]);
        Integer tam;

        for(i=0;i<size+1;i++){
            tam = tablaAFD.get(i).size();
            for(Integer j=1;j<tam;j++){
                if(j<minAndMax[0])
                    tablaAFD.get(i).remove(1);
                else if(j>minAndMax[1])
                    tablaAFD.get(i).remove(dif+2);
            }
        }

        for(i=0;i<size+1;i++){
            for(Integer j=0;j<tablaAFD.get(i).size();j++)
                System.out.print(tablaAFD.get(i).get(j) + '\t');
            System.out.println("");
        }


        reducirFilas(tablaAFD,equivalencias);


        for(i=0;i<tablaAFD.size();i++){
            for(Integer j=0;j<tablaAFD.get(i).size();j++)
                System.out.print(tablaAFD.get(i).get(j) + '\t');
            System.out.println("");
        }

    }


    public Integer[] encuentraMaxYMin(ArrayList<ArrayList<String>> tabla){
        Integer[] values = {-1,-1};

        for(Integer i=1;i<tabla.size();i++){
            for(Integer j=1;j<tabla.get(i).size();j++){
                if(!tabla.get(i).get(j).equals("-1")){
                    if(values[0] == -1)
                        values[0] = j;
                    else if(values[0]>j)
                        values[0] = j;

                    break;
                }
            }
        }

        for(Integer i=1;i<tabla.size();i++){
            for(Integer j=values[0];j<tabla.get(i).size()-1;j++){
                if(!tabla.get(i).get(j).equals("-1")){
                    if(j>values[1]){
                        values[1] = j;
                    }
                }
            }
        }

        return values;
    }


    public void reducirFilas(ArrayList<ArrayList<String>> tabla,Map<Integer,Integer> mapa){

        for(int i=1;i<tabla.size();i++){
            for(int j=i+1;j<tabla.size();j++){
                if(listEquals(tabla.get(i),tabla.get(j))){
                   //if(mapa.get(Integer.parseInt(tabla.get(j).get(0))) == null){
                   mapa.put(Integer.parseInt(tabla.get(j).get(0)),Integer.parseInt(tabla.get(i).get(0)));
                   tabla.remove(j);
                   j--;
                }
            }
        }

        Iterator it = mapa.keySet().iterator();

        while(it.hasNext()){
            Integer i = Integer.parseInt(it.next().toString());
            Integer val = mapa.get(i);
            System.out.println("i " + i+ " Val " + val);
        }
    }


    public boolean listEquals(ArrayList<String> l1,ArrayList<String> l2){
        for(Integer i=1;i<l1.size();i++){
            if(!l1.get(i).equals(l2.get(i)))
                return false;
        }

        return true;
    }
}
