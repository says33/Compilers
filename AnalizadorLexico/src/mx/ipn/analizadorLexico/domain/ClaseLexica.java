package mx.ipn.analizadorLexico.domain;

/**
 * Created with IntelliJ IDEA.
 * User: gamaliel
 * Date: 19/02/14
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class ClaseLexica {

    private ClaseLexicaToken claseLexicaToken;
    private String expresionRegular;
    private Integer position;

    public ClaseLexica(){
        position = -1;
    }

    public Integer yylex(){
        this.position++;
        if(position<expresionRegular.length()){
            Character currentChar = new Character(expresionRegular.charAt(position));
            return ClaseLexicaToken.claseLexicaToken.get(currentChar);
        }
        return 0;
    }

    public void regresarToken(){
        this.position--;
    }

    public Character getLexema(){
        return new Character(expresionRegular.charAt(position));
    }

    /*Expresion regular que será analizada*/
    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
