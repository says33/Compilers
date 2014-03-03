package mx.ipn.analizadorLexico.domain;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 25/02/14
 * Time: 05:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class AFD {

    private ArrayList<Character> alfabeto;
    private EstadoAFD estadoInicial;
    private ArrayList<EstadoAFD> estados;
    private ArrayList<Integer> conjunto;

    public AFD(){
        estados = new ArrayList<EstadoAFD>();
        conjunto = new ArrayList<Integer>();
        alfabeto = new ArrayList<Character>();
    }

    public EstadoAFD getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(EstadoAFD estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public ArrayList<EstadoAFD> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<EstadoAFD> estados) {
        this.estados = estados;
    }

    public ArrayList<Integer> getConjunto() {
        return conjunto;
    }

    public void setConjunto(ArrayList<Integer> conjunto) {
        this.conjunto = conjunto;
    }

    /*Método utilizado para comprobar si hay estados sin marcar en el AFD*/
    public EstadoAFD edosSinMarcar(Integer id){

        if(this.estadoInicial.getId() == -1){
            /*Se marca el estado*/
            this.estadoInicial.setId(id);
            return this.estadoInicial;
        }

        for(int i=0;i<estados.size();i++)
            if(estados.get(i).getId() == -1){
                estados.get(i).setId(id);
                return this.estados.get(i);
            }

        return null;
    }

    public boolean estadoEnAFD(EstadoAFD q){

        if(compareArray(idEdos(estadoInicial),idEdos(q))){
            return true;
        }

        for(EstadoAFD e : estados){
            if(compareArray(idEdos(q),idEdos(e))){
                return true;
            }
        }

        return false;
    }

    public ArrayList<Integer> idEdos(EstadoAFD e){
        ArrayList<Integer> idEstados = new ArrayList<Integer>();
            for(Estado edo: e.getSubEstados())
                idEstados.add(edo.getId());
        return idEstados;
    }

    public boolean compareArray(ArrayList<Integer> a,ArrayList<Integer> b){
        if(a.size() != b.size())
            return false;

        for(int i=0;i<a.size();i++){
            if(a.get(i)  != b.get(i)){
                return false;
            }
        }

        return true;
    }


    public ArrayList<Estado> irA(EstadoAFD edoAFD,Character c){
        ArrayList<Estado> estados = new ArrayList<Estado>();

        for(Estado e: edoAFD.getSubEstados()){
            for(Estado e_in : getEstadosFromTransicion(e,c)){
                estados.add(e_in);
            }
        }

        return estados;
    }

    public ArrayList<Estado> getEstadosFromTransicion(Estado q,Character c){
        ArrayList<Estado> edos = new ArrayList<Estado>();

        Object obj = q.getTransiciones().get(c);

        if(obj instanceof Estado)
            edos.add((Estado)obj);
        else if(obj instanceof ArrayList)
            for(Estado e: (ArrayList<Estado>)obj){
                edos.add(e);
            }

        return edos;
    }

    public void printTransiciones(){
        /*
        for(EstadoAFD estadoAFD : estados)
          */




    }
}