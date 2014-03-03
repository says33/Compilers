package mx.ipn.analizadorLexico.utils;

import mx.ipn.analizadorLexico.domain.AFD;
import mx.ipn.analizadorLexico.domain.AFN;
import mx.ipn.analizadorLexico.domain.Estado;
import mx.ipn.analizadorLexico.domain.EstadoAFD;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 26/02/14
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class AFNaAFD {

    public AFNaAFD(){

    }

    public void convierteAFNaAFD(AFN afn,AFD afd){
        ArrayList<EstadoAFD> dEstados;
        ArrayList<Estado> subEdos;
        Map<Character,Object> dTran = new HashMap<Character,Object>();

        EstadoAFD T = new EstadoAFD(false);
        subEdos = T.getSubEstados();
        subEdos.add(afn.getEstadoInicial());
        T.setSubEstados(subEdos);
        subEdos = new ArrayList<Estado>();
        subEdos = cerradurae(T.getSubEstados(),'ε');

        T.setSubEstados(subEdos);
        afd.setEstadoInicial(T);

        EstadoAFD aux;
        Integer id = new Integer(1);

        while((aux = afd.edosSinMarcar(id)) != null){

            ArrayList<Estado> subEstadosAFD = new ArrayList<Estado>();

            for(Character c: afn.getAlfabeto()){
                EstadoAFD U = new EstadoAFD(false);
                subEstadosAFD = cerradurae(afd.irA(aux,c),c);
                U.setSubEstados(subEstadosAFD);

                if(!afd.estadoEnAFD(U) && U.getSubEstados().size() > 0){
                    dEstados = afd.getEstados();
                    dEstados.add(U);
                    afd.setEstados(dEstados);
                }

                dTran = aux.getdTrans();
                dTran.put(c,U);
                aux.setdTrans(dTran);
            }
            id++;
        }

    }

    private ArrayList<Estado> cerradurae(ArrayList<Estado> T,Character c){
        Stack<Estado> pila = new Stack<Estado>();
        /*Obtiene todos los subEstados que hay en el estado*/

        /*La cerradura devuelve un conjunto de objetos Estado
          que pertenecen a un estado del AFD*/
        ArrayList<Estado> auxEdos = new ArrayList<Estado>();
        ArrayList<Estado> e_cerradura = new ArrayList<Estado>();
        Estado item;

        /*Se llena la pila e inicializa e_cerradura*/
        for(Estado e: T){
            pila.push(e);
            e_cerradura.add(e);
        }

        while(!pila.isEmpty()){
            /*Se saca el EstadoAFD de la pila*/
            item = pila.pop();
            auxEdos = getEstadosFromTransicion(item,c);

            for(Estado e : auxEdos){
                if(!e_cerradura.contains(e)){
                    e_cerradura.add(e);
                    pila.push(e);
                }
            }
        }

        return e_cerradura;
    }

    public ArrayList<Estado> getEstadosFromTransicion(Estado q){
        ArrayList<Estado> edos = new ArrayList<Estado>();
        Map<Character,Object> mapa;
        mapa = q.getTransiciones();
        Iterator it = mapa.keySet().iterator();
        Character key = ' ';

        while (it.hasNext()) {
            key = new Character(it.next().toString().charAt(0));
            Object e = q.getTransiciones().get(key);

            if (e instanceof Estado)
                edos.add((Estado) e);
            else if (e instanceof ArrayList)
                for (Estado edo : (ArrayList<Estado>) e)
                    edos.add(edo);
        }

        return edos;
    }

    public ArrayList<Estado> getEstadosFromTransicion(Estado q,Character c){
        ArrayList<Estado> edos = new ArrayList<Estado>();
        Object obj = q.getTransiciones().get('ε');

        if (obj instanceof Estado)
            edos.add((Estado) obj);
        else if (obj instanceof ArrayList)
            for (Estado edo : (ArrayList<Estado>) obj)
                edos.add(edo);

        if(c != 'ε'){
            obj = q.getTransiciones().get(c);
            if (obj instanceof Estado)
                edos.add((Estado) obj);
            else if (obj instanceof ArrayList)
                for (Estado edo : (ArrayList<Estado>) obj)
                    edos.add(edo);
        }

        return edos;
    }

    public ArrayList<Estado> getEstadosFromObject(EstadoAFD T){
        ArrayList<Estado> edos = new ArrayList<Estado>();
        for(Estado e:T.getSubEstados())
            edos.add(e);

        return edos;
    }
}
