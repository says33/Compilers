package mx.ipn.analizadorSintactico.utils;

import mx.ipn.analizadorSintactico.domain.AFD;
import mx.ipn.analizadorSintactico.domain.AFN;
import mx.ipn.analizadorSintactico.domain.Estado;
import mx.ipn.analizadorSintactico.domain.EstadoAFD;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 26/02/14
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class AFNaAFD {

    private Map<Integer,Integer> tokensAFN;
    private Map<Integer,Integer> tokensAFD;
    private Integer INICIO_ETIQUETA = 0;

    /*Se inyectan los tokens por constructor*/
    public AFNaAFD(Map<Integer,Integer> tokensAFN){
        this.tokensAFN = tokensAFN;
        tokensAFD = new Hashtable<Integer, Integer>();
    }

    public Map<Integer,Integer> convierteAFNaAFD(AFN afn,AFD afd){
        ArrayList<EstadoAFD> dEstados;
        ArrayList<Estado> subEdos;
        Map<Character,Object> dTran = new HashMap<Character,Object>();

        /*Se asigna el alfabeto del AFN al AFD*/
        afd.setAlfabeto(afn.getAlfabeto());

        EstadoAFD T = new EstadoAFD();
        subEdos = T.getSubEstados();
        subEdos.add(afn.getEstadoInicial());
        T.setSubEstados(subEdos);
        subEdos = new ArrayList<Estado>();
        subEdos = cerradurae(T.getSubEstados(),T);

        T.setSubEstados(subEdos);
        afd.setEstadoInicial(T);

        EstadoAFD aux;

        while((aux = afd.edosSinMarcar(INICIO_ETIQUETA)) != null){

            ArrayList<Estado> subEstadosAFD = new ArrayList<Estado>();

            for(Character c: afn.getAlfabeto()){
                EstadoAFD U = new EstadoAFD();
                subEstadosAFD = cerradurae(afd.irA(aux,c),U);
                U.setSubEstados(subEstadosAFD);

                if((afd.estadoEnAFD(U) == null)){
                    if(U.getSubEstados().size() > 0){
                        dEstados = afd.getEstados();
                        dEstados.add(U);
                        afd.setEstados(dEstados);
                    }
                }
                else
                    U = afd.estadoEnAFD(U);

                if(U.getSubEstados().size() > 0){
                    dTran = aux.getdTrans();
                    dTran.put(c,U);
                    aux.setdTrans(dTran);
                }
            }
            INICIO_ETIQUETA++;
        }

        for(EstadoAFD edoAFD:afd.getAllEdosOfAFD()){
            if(edoAFD.isEsEstadoAceptacion()){
                for(Estado e:edoAFD.getSubEstados()){
                    if(tokensAFN.get(e.getId()) != null){
                        tokensAFD.put(edoAFD.getId(),tokensAFN.get(e.getId()));
                        break;
                    }
                }
            }
        }

        return tokensAFD;
    }

    private ArrayList<Estado> cerradurae(ArrayList<Estado> T,EstadoAFD currentEdo){
        Stack<Estado> pila = new Stack<Estado>();
        /*Obtiene todos los subEstados que hay en el estado*/

        /*La cerradura devuelve un conjunto de objetos Estado
          que pertenecen a un estado del AFD*/
        ArrayList<Estado> auxEdos = new ArrayList<Estado>();
        ArrayList<Estado> e_cerradura = new ArrayList<Estado>();
        Estado item;

        /*Se llena la pila e inicializa e_cerradura*/
        for(Estado e: T){
            if(tokensAFN.get(e.getId()) != null){
                currentEdo.setEsEstadoAceptacion(true);
            }
            pila.push(e);
            e_cerradura.add(e);
        }

        while(!pila.isEmpty()){
            /*Se saca el EstadoAFD de la pila*/
            item = pila.pop();

            auxEdos = getEstadosFromTransicionEpsilon(item);

            for(Estado e : auxEdos){
                if(!e_cerradura.contains(e)){
                    /*Si hay un estado de aceptación*/
                    if(tokensAFN.get(e.getId()) != null){
                        currentEdo.setEsEstadoAceptacion(true);
                    }
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


    public ArrayList<Estado> getEstadosFromTransicionEpsilon(Estado q){
        ArrayList<Estado> edos = new ArrayList<Estado>();
        Object obj = q.getTransiciones().get('ε');

        if (obj instanceof Estado)
            edos.add((Estado) obj);
        else if (obj instanceof ArrayList)
            for (Estado edo : (ArrayList<Estado>) obj)
                edos.add(edo);

        return edos;
    }

    public ArrayList<Estado> getEstadosFromTransicion(Estado q,Character c){
        ArrayList<Estado> edos = new ArrayList<Estado>();

        Object obj = q.getTransiciones().get(c);

        if (obj instanceof Estado)
            edos.add((Estado) obj);
        else if (obj instanceof ArrayList)
            for (Estado edo : (ArrayList<Estado>) obj)
                edos.add(edo);

        return edos;
    }

    public ArrayList<Estado> getEstadosFromObject(EstadoAFD T){
        ArrayList<Estado> edos = new ArrayList<Estado>();
        for(Estado e:T.getSubEstados())
            edos.add(e);

        return edos;
    }

    public Map<Integer, Integer> getTokensAFD() {
        return tokensAFD;
    }

    public void setTokensAFD(Map<Integer, Integer> tokensAFD) {
        this.tokensAFD = tokensAFD;
    }
}
