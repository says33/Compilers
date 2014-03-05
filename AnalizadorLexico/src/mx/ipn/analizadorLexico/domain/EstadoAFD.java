package mx.ipn.analizadorLexico.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 */
public class EstadoAFD {
    private Integer id = -1;
    private boolean esEstadoAceptacion;
    private ArrayList<Estado> subEstados;
    private Map<Character,Object> dTrans;
    private String token;

    public EstadoAFD(){
        esEstadoAceptacion = false;
        subEstados = new ArrayList<Estado>();
        dTrans = new HashMap<Character, Object>();
    }

    public EstadoAFD(Integer id,boolean esEstadoAceptacion){
        this.id = id;
        this.esEstadoAceptacion = esEstadoAceptacion;
        subEstados = new ArrayList<Estado>();
        dTrans = new HashMap<Character, Object>();
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

    public ArrayList<Estado> getSubEstados() {
        return subEstados;
    }

    public void setSubEstados(ArrayList<Estado> subEstados) {
        this.subEstados = subEstados;
    }

    public Map<Character, Object> getdTrans() {
        return dTrans;
    }

    public void setdTrans(Map<Character, Object> dTrans) {
        this.dTrans = dTrans;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
