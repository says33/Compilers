package mx.ipn.analizadorSintactico.view

import mx.ipn.analizadorSintactico.controller.AnalizadorLexicoController
import mx.ipn.analizadorSintactico.controller.AnalizadorSintacticoController

//import groovy.swing.SwingBuilder
import mx.ipn.analizadorSintactico.utils.ScannerLexico
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTabbedPane
import javax.swing.JTextField
import javax.swing.table.DefaultTableModel
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JTable
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class GUI extends JFrame{

    def jTextFieldsRegEx
    def jTextFieldTokens
    def jLabelTerminales
    def buttonLexicalAnalyzer
    def scanner
    def jTable
    def jTable2
    def jTable3
    def defautlTableModel
    def defautlTableModel2
    def jScrollPane
    def jScrollPane2
    def jScrollPane3
    def jPanel1
    def jPanel2
    def jTabbedPane
    def jTextField
    def jButton
    def first
    def follow
    def simbInicial
    def listTerminales
    def mapTerminalToken=[:]


    def GUI(def listTerminales, def first, def follow){
        this.first = first
        this.follow = follow
        this.simbInicial = simbInicial
        this.listTerminales = listTerminales

        initComponents(listTerminales)
        initAnalizadorSintacticoComponents(listTerminales)
        setBounds(200,100,800,600)
        setTitle "Analizador LL1"
        setLayout(new BorderLayout())
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        add(jTabbedPane,BorderLayout.CENTER)
        setVisible true
    }

    def initComponents(def listaTerminales){
        def listWithOutEpsilon = []

        listWithOutEpsilon.addAll(listaTerminales)
        listWithOutEpsilon.remove('ε')

        jTextFieldsRegEx = []
        jTextFieldTokens = []
        jLabelTerminales = []
        buttonLexicalAnalyzer = new JButton("Crear")
        buttonLexicalAnalyzer.setBounds(20,100,100,30)
        buttonLexicalAnalyzer.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                scanner = new ScannerLexico()
                def i = 0
                def analizadorLexicoController = new AnalizadorLexicoController()
                analizadorLexicoController.createTable(jTextFieldsRegEx,jTextFieldTokens)
                jTextFieldTokens.each {
                    mapTerminalToken.put(it.getText(),listWithOutEpsilon[i])
                    i++
                }

                mapTerminalToken.put('0','$')

                analizadorLexicoController.readTable(scanner)
            }
        })

        def x = 20,xlabel = 30;
        def i=0,j=0

        listWithOutEpsilon.each{
            jTextFieldsRegEx.add(new JTextField())
            jTextFieldTokens.add(new JTextField())
            jLabelTerminales.add(new JLabel(it))
        }

        listWithOutEpsilon.each {
            jTextFieldsRegEx[i].setBounds(x,30,70,20)
            jTextFieldTokens[i].setBounds(x,60,70,20)
            jLabelTerminales[i].setBounds(xlabel,5,70,30)
            x+=100;
            xlabel += 100;
            i++
        }

        jTabbedPane = new JTabbedPane()
        jPanel1 = new JPanel()
        jPanel1.setSize(700,500)
        jPanel1.setLayout(null)
        //jpanel.setBackground(Color.black)

        String[] columns = listaTerminales.toArray()

        Object[][] data = [
            ["ε","-","+","/","*","num","(",")"]
        ]

        jTable = new JTable(data,columns)
        jScrollPane = new JScrollPane(jTable)
        jScrollPane.setPreferredSize(new Dimension(400,400))
        //jPanel1.add(jScrollPane)
        i = 0
        listWithOutEpsilon.each {
            jPanel1.add(jLabelTerminales[i])
            jPanel1.add(jTextFieldsRegEx[i])
            jPanel1.add(jTextFieldTokens[i])
            i++
        }

        jPanel1.add(buttonLexicalAnalyzer)
        jTabbedPane.addTab("AnalizadorLexico",jPanel1)

    }


    def initAnalizadorSintacticoComponents(def listaTerminales){

        String[] columns = listaTerminales.toArray()
        String[] columnsAnalisis = ['PILA','CADENA','ACCIÓN']
        defautlTableModel = new DefaultTableModel(null,columns)
        defautlTableModel2 = new DefaultTableModel(null,columnsAnalisis)
        //defautlTableModel2 = new DefaultTableModel(null,)

        jTextField = new JTextField()

        jTextField.setBounds(10,20,600,30)

        jButton = new JButton("Analizar");

        jButton.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                scanner.cleanScanner()
                def analizadorController = new AnalizadorSintacticoController(mapTerminalToken)
                def i=0,j=0


                def list = analizadorController.analizaCadena(first,follow,listTerminales,scanner.tokenizaCadena(jTextField.getText()))

                String[][] datos = new String[list.size()][list.get(0).size()]

                list.each { lista ->
                    j = 0
                    lista.each{ item ->
                        datos[i][j] = item
                        j++
                    }
                    i++
                }
                defautlTableModel2.setDataVector(datos,columnsAnalisis)
            }
        })

        jButton.setBounds(610,20,80,30)


        jTable2 = new JTable(defautlTableModel)
        jTable3 = new JTable(defautlTableModel2)

        jScrollPane2 = new JScrollPane(jTable2)
        jScrollPane3 = new JScrollPane(jTable3)
        jScrollPane3.setBounds(0,100,790,400)

        jPanel2 = new JPanel()
        jPanel2.setSize(700,500)
        jPanel2.setLayout(null)


        jPanel2.add(jTextField)
        jPanel2.add(jButton)
        jPanel2.add(jScrollPane3)
        //jPanel2.add(jScrollPane2)
        jTabbedPane.addTab("Análisis Sintáctico",jPanel2)
    }

    /*
        SwingBuilder.build {
            frame(title:"Analizador LL1",show:true,bounds:[200,100,600,600],
                defaultCloseOperation: EXIT_ON_CLOSE){
                gridLayout(cols:3,rows:3)
            }
    } */
}
