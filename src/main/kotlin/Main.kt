import Connection.Connection
import Find.Find

fun main(args: Array<String>) {
 val connection = Connection("localhost", "27017", "neptuno", false)
     val db = connection.connect()
     println(db.name)
     var find = Find(db)
    var documents = find.findAllAsString("cliente")
    println(documents)
}