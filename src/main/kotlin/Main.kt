import Connection.Connection
import Find.Find
import Find.Peluche


fun main(args: Array<String>) {
 val connection = Connection("localhost", "27017", "tienda_peluche", false)
     val db = connection.connect()
     println(db.name)
     var find = Find(db)
    val listPeluche = find.findOneByIdAsDocument("peluche", "640b8d4e1381cc766f204b53")
    println(listPeluche)
}