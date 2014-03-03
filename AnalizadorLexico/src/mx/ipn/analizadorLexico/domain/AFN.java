package mx.ipn.analizadorLexico.domain;

import java.util.*;

public class AFN {

    private ArrayList<Estado> estados;
    private ArrayList<Character> alfabeto;
    private Estado estadoInicial;

    public AFN() {
        alfabeto = new ArrayList<Character>();
        estados = new ArrayList<Estado>();
        estadoInicial = new Estado(false);
    }

    /*Se crea un automata con un sólo símbolo*/
    public void createWithASymbol(Character character) {
        /*Al alfabeto se le asigna el primer cáracter*/
        this.alfabeto.add(character);
        estados.add(new Estado(true));

        /*Transición*/
        Map<Character, Object> transiciones = new HashMap<Character, Object>();
        transiciones.put(this.alfabeto.get(0), estados.get(0));
        this.estadoInicial.setTransiciones(transiciones);
    }


    public void unir(AFN f2) {
        /*Se agregan los caracteres del f2*/
        agregarAlfabeto(f2.getAlfabeto());
        /*Se cambia el estado inicial del automata y se agregan transiciones epsilon*/
        cambiaInicial(f2.getAllEdosFromAutomata());
        cambiaFinal();
    }

    public void conc(AFN f2) {
        ArrayList<Estado> edosf2 = f2.getAllEdosFromAutomata();
        Map<Character,Object> transicionesAux = new Hashtable<Character, Object>();

        /*Se agregan los caracteres del f2*/
        agregarAlfabeto(f2.getAlfabeto());

        /*Si el automata con el que se unirá es de más de dos estados*/
        for(int i=2;i<edosf2.size();i++){
            this.estados.add(edosf2.get(i));
        }

        Iterator iterator = edosf2.get(0).getTransiciones().keySet().iterator();
        Character key = ' ';

        while (iterator.hasNext()){
            key = new Character(iterator.next().toString().charAt(0));
            Object e = edosf2.get(0).getTransiciones().get(key);
            transicionesAux.put(key,e);
        }

        this.estados.get(0).setEsEstadoAceptacion(false);
        this.estados.get(0).setTransiciones(transicionesAux);
        this.estados.add(0,edosf2.get(1));

    }

    public void cerrPos() {
        //System.out.println("CerraduraPos");
    }

    public void cerrEstr() {
        ArrayList<Estado> transicionesEpsilon = new ArrayList<Estado>();
        Map<Character,Object> transiciones = new HashMap<Character, Object>();

        Estado edoFinalAux = estados.get(0);
        estados.add(0,new Estado(true));
        /*Se agrega una transición epsilon
        * al estado inicial
         */
        transicionesEpsilon.add(estadoInicial);
        transicionesEpsilon.add(estados.get(0));
        transiciones.put('ε', transicionesEpsilon);

        edoFinalAux.setTransiciones(transiciones);

        /*El estado de final deja de ser final*/
        edoFinalAux.setEsEstadoAceptacion(false);

        Estado edoInicialAux = estadoInicial;
        estadoInicial = new Estado(false);
        transicionesEpsilon = new ArrayList<Estado>();
        transiciones=new HashMap<Character, Object>();

        /*Se agregan dos transiciones epsilon al estado inicial*/
        transicionesEpsilon.add(edoInicialAux);
        transicionesEpsilon.add(estados.get(0));
        transiciones.put('ε',transicionesEpsilon);
        estadoInicial.setTransiciones(transiciones);
        estados.add(edoInicialAux);
    }

    public void opc() {
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

    public ArrayList<Estado> getAllEdosFromAutomata() {
        ArrayList<Estado> edos = new ArrayList<Estado>();

        edos.add(estadoInicial);

        for (Estado e : estados)
            edos.add(e);

        return edos;
    }

    public void cambiaInicial(ArrayList<Estado> edos) {
        Map<Character, Object> transiciones = new HashMap<Character, Object>();
        ArrayList<Estado> edosTransicionEpsilon = new ArrayList<Estado>();

        if (estadoInicial.getTransiciones().get('ε') == null) {
            Estado edoInicialAux = new Estado(false);
            edoInicialAux.setTransiciones(estadoInicial.getTransiciones());


            /*El nuevo estado inicial se agrega al Array de estados*/
            this.estados.add(edoInicialAux);

            /*El nuevo estado también se agrega a un arreglo de estados de la transición epsilon*/
            edosTransicionEpsilon.add(edoInicialAux);
            transiciones.put('ε', edosTransicionEpsilon);
            estadoInicial.setTransiciones(transiciones);

            edosTransicionEpsilon = (ArrayList<Estado>) estadoInicial.getTransiciones().get('ε');
            edosTransicionEpsilon.add(edos.get(0));
            transiciones = estadoInicial.getTransiciones();
            transiciones.put('ε', edosTransicionEpsilon);
            estadoInicial.setTransiciones(transiciones);

            /*Unión de un automata*/
            for (Estado e : edos) {
                this.estados.add(e);
            }
        } else {
            transiciones = estadoInicial.getTransiciones();
            edosTransicionEpsilon = (ArrayList<Estado>)transiciones.get('ε');
            edosTransicionEpsilon.add(edos.get(0));
            transiciones.put('ε', edosTransicionEpsilon);
            estadoInicial.setTransiciones(transiciones);
            /*Unión de un automata*/
            for (Estado e : edos) {
                this.estados.add(e);
            }
        }

    }

    public void cambiaFinal() {
        Map<Character, Object> transiciones = new HashMap<Character, Object>();
        ArrayList<Estado> edosTransicionEpsilon = new ArrayList<Estado>();

        if (estados.get(1).getTransiciones().get('ε') == null) {
            Estado nvoEdoFinal = new Estado(true);
            edosTransicionEpsilon.add(nvoEdoFinal);
            transiciones.put('ε', edosTransicionEpsilon);
            /*Agrega la transición del estado final al nuevo estado Final*/
            estados.get(0).setEsEstadoAceptacion(false);
            estados.get(0).setTransiciones(transiciones);
            estados.add(0, nvoEdoFinal);

            /*El estado final del segundo automata también lo liga mediante
              una transición epsilon al estado final*/
            edosTransicionEpsilon = new ArrayList<Estado>();
            edosTransicionEpsilon.add(estados.get(0));
            transiciones = estados.get(estados.size() - 1).getTransiciones();
            transiciones.put('ε', edosTransicionEpsilon);
            /*Ya no es de aceptación*/
            estados.get(estados.size() - 1).setEsEstadoAceptacion(false);
            estados.get(estados.size() - 1).setTransiciones(transiciones);
        } else {
            transiciones = estados.get(estados.size() - 1).getTransiciones();
            /*Se obtienen las transiciones epsilon anteriores*/
            if ((ArrayList<Estado>) transiciones.get('ε') != null)
                edosTransicionEpsilon = (ArrayList<Estado>) transiciones.get('ε');

            /*Se agrega el estado final a las transiciones epsilon*/
            edosTransicionEpsilon.add(this.estados.get(0));
            transiciones.put('ε', edosTransicionEpsilon);
            estados.get(estados.size() - 1).setEsEstadoAceptacion(false);
            estados.get(estados.size() - 1).setTransiciones(transiciones);
        }
    }


    public int etiquetaEstados(Estado ini, Integer id) {

        if (ini.getId() != -1) {
            return id;
        } else {
            ini.setId(id);
            ++id;
            Iterator it = ini.getTransiciones().keySet().iterator();
            Character key = ' ';

            while (it.hasNext()) {
                key = new Character(it.next().toString().charAt(0));
                Object e = ini.getTransiciones().get(key);

                if (e instanceof Estado)
                    id = etiquetaEstados((Estado) e, id);
                else if (e instanceof ArrayList){
                    for (Estado edo : (ArrayList<Estado>) e)
                        id = etiquetaEstados((Estado) edo, id);
                }
            }
        }
        return id;
    }

    public void cleanAutomata(Estado ini){

        if (ini.getId() == -1) {
            return;
        } else {
            ini.setId(-1);
            Iterator it = ini.getTransiciones().keySet().iterator();
            Character key = ' ';
            while (it.hasNext()) {
                key = new Character(it.next().toString().charAt(0));
                Object e = ini.getTransiciones().get(key);
                if (e instanceof Estado)
                    cleanAutomata((Estado) e);
                else if (e instanceof ArrayList){
                    for (Estado edo : (ArrayList<Estado>) e){
                        cleanAutomata((Estado) edo);
                    }
                }

            }
        }
    }

    public void printTransicionesEdo(Estado q) {
        Iterator iterator = q.getTransiciones().keySet().iterator();
        Character key = ' ';
        String value;

        while (iterator.hasNext()) {
            key = new Character(iterator.next().toString().charAt(0));
            value = "";
            Object e = q.getTransiciones().get(key);

            if (e instanceof Estado)
                value += ((Estado) e).isEsEstadoAceptacion();
            else if (e instanceof ArrayList) {
                for (Estado edo : (ArrayList<Estado>)e){
                    if (value.equals(""))
                        value += edo.isEsEstadoAceptacion();
                    else
                        value += "-" + edo.isEsEstadoAceptacion();
                }
            }
            System.out.println("Key " + key + " Value:" + value);
        }
    }

    public void agregarAlfabeto(ArrayList<Character> a){
        for(Character c: a)
            if(!alfabeto.contains(c))
                alfabeto.add(c);
    }
}