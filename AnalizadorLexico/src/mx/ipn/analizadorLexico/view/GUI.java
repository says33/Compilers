package mx.ipn.analizadorLexico.view;

import mx.ipn.analizadorLexico.controller.AnalizadorLexicoController;
import mx.ipn.analizadorLexico.utils.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{

    private JTextArea jTextArea;
    private JPanel jpanelTop;
    private JPanel jpanel;
    private JPanel jpanel2;
    private JButton jButton;
    private JButton jButton2;
    private JTable jtable;
    private DefaultTableModel defaultTableModel;
    private JScrollPane jScrollPane;
    private Scanner scanner;
    private final String[] columnHeaders = {"Lexema","Token"};


    public GUI(){
        initializeComponents();
        this.scanner = new Scanner();
        AnalizadorLexicoController al = new AnalizadorLexicoController();
        //al.createTable();
        al.readTable(scanner);
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
        jButton.addActionListener(this);
        jTextArea = new JTextArea(5,20);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jpanel = new JPanel();
        jpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpanel.add(new JScrollPane(jTextArea));
        jpanel.add(jButton);
    }
    public void setTableData(){
        jpanel2 = new JPanel();
        jpanel2.setLayout(null);
        defaultTableModel = new DefaultTableModel(null,columnHeaders);
        jtable = new JTable(defaultTableModel);
        jScrollPane = new JScrollPane(jtable);
        jScrollPane.setBounds(0,0,400,100);
        jpanel2.add(jScrollPane);
    }

    public void actionPerformed(ActionEvent e){
        scanner.cleanScanner();
        ArrayList<ArrayList<String>> lexemas =scanner.tokenizaCadena(jTextArea.getText());

        Object[][] valores = new Object[lexemas.size()][lexemas.get(0).size()];

        for(int i=0;i<lexemas.size();i++){
            for(int j=0;j<lexemas.get(i).size();j++){
                valores[i][j] = lexemas.get(i).get(j);
            }
        }
        defaultTableModel.setDataVector(valores,columnHeaders);

    }
}