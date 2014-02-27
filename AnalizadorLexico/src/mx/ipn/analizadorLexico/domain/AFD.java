package mx.ipn.analizadorLexico.domain;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 25/02/14
 * Time: 05:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class AFD {
    private Estado edoInicial;
    private ArrayList<Estado> estados;
    private ArrayList<Integer> matriz;

    public AFD(){};

    public Estado getEdoInicial() {
        return edoInicial;
    }

    public void setEdoInicial(Estado edoInicial) {
        this.edoInicial = edoInicial;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public ArrayList<Integer> getMatriz() {
        return matriz;
    }

    public void setMatriz(ArrayList<Integer> matriz) {
        this.matriz = matriz;
    }

    /*Método utilizado para comprobar si hay estados sin marcar en el AFD*/
    public boolean edosSinMarcar(){
         if(this.edoInicial.getId() != -1)
            return false;
        for(int i=0;i<estados.size();i++)
            if(estados.get(i).getId() == -1)
                return true;
        return false;
    }
}