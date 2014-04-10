package mx.ipn.analizadorSintactico.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Gamaliel
 * Date: 11/02/14
 **/
public class Estado {

    private Integer id = -1;
    private boolean esEstadoAceptacion;
    private Map<Character,Object> transiciones;

    /*Constructor del estado sin parámetros*/
    public Estado(){}

    public Estado(Integer id,boolean esEstadoAceptacion){
        transiciones = new HashMap<Character,Object>();
        this.id = id;
        this.esEstadoAceptacion = esEstadoAceptacion;
    }

    public Estado(boolean esEstadoAceptacion){
        transiciones = new HashMap<Character,Object>();
        this.esEstadoAceptacion = esEstadoAceptacion;
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

    public Map<Character,Object> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(Map<Character,Object> transiciones) {
        this.transiciones = transiciones;
    }
}
