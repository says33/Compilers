package mx.ipn.analizadorLexico.view;

import mx.ipn.analizadorLexico.controller.AnalizadorLexicoController;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem1,menuItem2;

    public GUI(){
        initializeComponents();
        AnalizadorLexicoController al = new AnalizadorLexicoController();
        al.getAFN();
    }

    public void initializeComponents(){
        setTitle("Analizador Léxico");
        setSize(400,400);
        setLocation(300,200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menu = new JMenu("Archivo");
        menuBar.add(menu);
        menuItem1 = new JMenuItem("Abrir");
        menu.add(menuItem1);
    }

    public void actionPerformed(ActionEvent e){

    }

    public void paint(Graphics g){
    }

}
