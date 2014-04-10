package mx.ipn.analizadorSintactico.view

//import groovy.swing.SwingBuilder
import mx.ipn.analizadorSintactico.controller.AnalizadorSintacticoController
import javax.swing.JButton
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
        jTabbedPane = new JTabbedPane()
        jPanel1 = new JPanel()

        jPanel1.setSize(700,500)
        jPanel1.setLayout(new GridLayout(1,1))
        //jpanel.setBackground(Color.black)

        String[] columns = listaTerminales.toArray()

        Object[][] data = [
            ["ε","-","+","/","*","num","(",")"]
        ]

        jTable = new JTable(data,columns)
        jScrollPane = new JScrollPane(jTable)
        jScrollPane.setPreferredSize(new Dimension(400,400))
        jPanel1.add(jScrollPane)
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
                def analizadorController = new AnalizadorSintacticoController()
                def i=0,j=0
                def list = analizadorController.analizaCadena(jTextField.getText(),first,follow,listTerminales)
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
