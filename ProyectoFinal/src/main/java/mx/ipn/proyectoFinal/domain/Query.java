package mx.ipn.proyectoFinal.domain;
import java.util.ArrayList;

public class Query{
	private ArrayList<String> listaCampos;
	private ArrayList<String> listaTablas;
	private ArrayList<String> listaCondiciones;
	private String finalQuery;

	public Query(){
		listaCampos = new ArrayList<String>();
		listaTablas = new ArrayList<String>();
		listaCondiciones = new ArrayList<String>();
	}

	public ArrayList<String> getListaCampos(){
		return listaCampos;
	}

	public void setListaCampos(ArrayList<String> listaCampos){
		this.listaCampos = listaCampos;
	}

	public ArrayList<String> getListaTablas(){
		return listaTablas;
	}

	public void setListaTablas(ArrayList<String> listaTablas){
		this.listaTablas = listaTablas;
	}

	public ArrayList<String> getListaCondiciones(){
		return listaCondiciones;
	}

	public void setListaCondiciones(ArrayList<String> listaCondiciones){
		this.listaCondiciones = listaCondiciones;
	}

	public String getFinalQuery(){
		return finalQuery;
	}

	public void setFinalQuery(String finalQuery){
		this.finalQuery = finalQuery;
	}
}