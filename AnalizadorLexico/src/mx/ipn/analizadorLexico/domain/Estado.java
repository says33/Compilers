package mx.ipn.analizadorLexico.domain;
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

    /*Constructor del estado sin parámetros*/
    public Estado(){}

    public Estado(Integer id,boolean esEstadoAceptacion){

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

}
