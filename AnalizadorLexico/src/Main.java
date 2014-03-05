/**
 * Created with IntelliJ IDEA.
 * User: Gamaliel
 * Date: 11/02/14
 * Time: 09:55 PM
 * To change this template use File | Settings | File Templates.
 */
import mx.ipn.analizadorLexico.controller.AnalizadorLexicoController;
import mx.ipn.analizadorLexico.view.GUI;

import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        AnalizadorLexicoController al = new AnalizadorLexicoController();
        al.createTable();
        /*
        GUI ventana = new GUI();*/
    }
}
