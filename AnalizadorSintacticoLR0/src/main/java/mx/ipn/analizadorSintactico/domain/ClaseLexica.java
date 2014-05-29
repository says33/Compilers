package mx.ipn.analizadorSintactico.domain;

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
            if(isDigit(currentChar) || isLetter(currentChar))
                return TokenCL.SIMB;
            else if(currentChar == '\\'){
                position++;
                return TokenCL.SIMB;
            }

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

    public boolean isDigit(Character c){
        if(c>='0'&&c<='9')
            return true;
        else
            return false;
    }

    public boolean isLetter(Character c){
        if(c>='a'&&c<='z' || c>='A'&&c<='Z')
            return true;
        else
            return false;
    }

}
