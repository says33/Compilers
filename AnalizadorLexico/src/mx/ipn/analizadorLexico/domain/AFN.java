package mx.ipn.analizadorLexico.domain;
import java.util.ArrayList;
import java.util.HashMap;
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
    private Estado estadoInicial;
    private ArrayList<Estado> estadosAceptacion;

    public AFN(){
        alfabeto = new ArrayList<Character>();
        estados = new ArrayList<Estado>();
        estadosAceptacion = new ArrayList<Estado>();
        estadoInicial = new Estado(false);
    }

    /*Se crea un automata con un sólo símbolo*/
    public void createWithASymbol(Character character){
        /*Al alfabeto se le asigna el primer cáracter*/
        this.alfabeto.add(character);
        this.estadosAceptacion.add(new Estado(true));
        /*Transición*/
        Map<Character,Estado> map = new HashMap<Character, Estado>();
        map.put(this.alfabeto.get(0),this.estadosAceptacion.get(0));

        this.estadoInicial.setTransiciones(map);

    }


    public void unir(AFN f2){
        Map<Character,Estado> nvasTransiciones = new HashMap<Character, Estado>();
        Map<Character,Estado> transicionAux = new HashMap<Character, Estado>();
        transicionAux = this.estadoInicial.getTransiciones();

        for(int i=0;i<f2.getAlfabeto().size();i++){
            this.alfabeto.add(f2.getAlfabeto().get(i));
        }


    }

    public void conc(AFN f2){
        //System.out.println("Concatenacion");
    }

    public void cerrPos(){
        //System.out.println("CerraduraPos");
    }

    public void cerrEstr(){
        //System.out.println("Estrella");
    }

    public void opc(){
        System.out.println("Opcional");
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

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

}