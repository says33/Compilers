package mx.ipn.analizadorLexico.view;

import mx.ipn.analizadorLexico.controller.AnalizadorLexicoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{

    private JTextField jtextField;
    private JPanel jpanelTop;
    private JPanel jpanel;
    private JPanel jpanel2;
    private JButton jButton;
    private JButton jButton2;
    private JTable jtable;
    private JScrollPane jScrollPane;

    public GUI(){
        initializeComponents();
        AnalizadorLexicoController al = new AnalizadorLexicoController();
        al.createTable();
    }

    public void initializeComponents(){

        setTitle("Analizador Léxico");
        setItemsToFistPane();
        setTableData();
        jpanelTop = new JPanel();
        jpanelTop.setLayout(new BorderLayout());
        jpanelTop.add(jpanel, BorderLayout.NORTH);
        jpanelTop.add(jpanel2, BorderLayout.CENTER);
        add(jpanelTop);
        setLocation(400,300);
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void setItemsToFistPane(){
        jButton = new JButton("Analizar");
        jtextField = new JTextField(12);
        jpanel = new JPanel();
        jpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpanel.add(jtextField);
        jpanel.add(jButton);
    }
    public void setTableData(){
        jpanel2 = new JPanel();
        jpanel2.setLayout(null);
        String[] columnNames = {"Lexema","Token"};
        DefaultTableModel model = new DefaultTableModel(null,columnNames);
        jtable = new JTable(model);
        jScrollPane = new JScrollPane(jtable);
        jScrollPane.setBounds(0,0,400,100);
        jpanel2.add(jScrollPane);
    }

    public void actionPerformed(ActionEvent e){

    }
}