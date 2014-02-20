package mx.ipn.analizadorLexico.domain;
import java.util.ArrayList;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 11/02/14
 * Time: 09:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class AFN {

    private ArrayList<Estado> estados;
    private ArrayList<Character> alfabeto;
    private Map<Character,Estado> transiciones;
    private Estado estadoInicial;
    private ArrayList<Estado> estadosFinales;

    public AFN(){
    }

    public ArrayList<Character> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(ArrayList<Character> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public Map<Character, Estado> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(Map<Character, Estado> transiciones) {
        this.transiciones = transiciones;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public ArrayList<Estado> getEstadosFinales() {
        return estadosFinales;
    }

    public void setEstadosFinales(ArrayList<Estado> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }
}