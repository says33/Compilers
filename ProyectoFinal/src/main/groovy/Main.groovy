import mx.ipn.proyectoFinal.view.GUI
import mx.ipn.proyectoFinal.utils.DataBase

class Main{
    static main(def args){
       def gui = new GUI()

       def dataBase = new DataBase()
       dataBase.query()

    }
}
