import Connection.Connection
import Find.Find
import Find.Peluche


fun main(args: Array<String>) {
 val connection = Connection("localhost", "27017", "tienda_peluche", false)
     val db = connection.connect()
     println(db.name)
     var find = Find(db)
    val listPeluche = find.findAllAsList("peluche", Peluche::class.java)
    listPeluche.forEach { println(it.toString()) }
}