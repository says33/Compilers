import static groovyx.javafx.GroovyFX.start


start{
    def fileChooser = fileChooser(initialDirectory: ".", title: "FileChooser Demo"){
                          filter("images", extensions: ["jpg", "gif", "bmp"])
                      }

    stage(title: 'Analizador Léxico-Sintáctico', show: true) {
        scene(fill: GROOVYBLUE, width:1000,height:600) {
            borderPane(){
                top(){
                    menuBar{
                        menu(text: "Archivo"){
                            menuItem("Abrir",onAction: {println "Abrir Archivo"})
                        }
                    }
                }
                tabPane(){
                    tab(text:'Analizador Lexico'){
                        borderPane(){                            
                            gridPane(hgap: 5, vgap: 10, padding: 25, alignment: "center"){        
                            label("Name", hgrow: "never", row: 1, column: 0, textFill: white)
                            textField(promptText: "Your name", row: 1, column: 1)
                            } 
                        } 
                    }
                    tab(text:'Analizador Sintáctico'){

                    }
                }
            }        
        }        
    }
}