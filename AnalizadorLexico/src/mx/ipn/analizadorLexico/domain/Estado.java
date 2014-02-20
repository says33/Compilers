package mx.ipn.analizadorLexico.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 11/02/14
 * Time: 09:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Estado {

    private Integer id;
    private boolean esEstadoAceptacion;
    private Map<Character,Estado> transiciones;

    /*Constructor del estado sin parámetros*/
    public Estado(){}

    public Estado(boolean esEstadoAceptacion){
        transiciones = new HashMap<Character, Estado>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isEsEstadoAceptacion() {
        return esEstadoAceptacion;
    }

    public void setEsEstadoAceptacion(boolean esEstadoAceptacion) {
        this.esEstadoAceptacion = esEstadoAceptacion;
    }

    public Map<Character, Estado> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(Map<Character, Estado> transiciones) {
        this.transiciones = transiciones;
    }
}
