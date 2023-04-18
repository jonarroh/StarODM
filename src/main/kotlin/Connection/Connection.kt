package Connection

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
/**
 * This function crate a connection with a database
 * @param: String host
 * @param: String port
 * @param: String database
 * @param: boolean ssl
 * @author: Jonarroh
 */
class Connection(private val host: String,private val port: String, private val databaseName: String,private val ssl: Boolean) {
    private val uri : MongoClientURI = MongoClientURI("mongodb://$host:$port/?ssl=$ssl")
    private var mongoClient : MongoClient? = null
    private var db : MongoDatabase? = null

    /**
     * This function connect to a database
     * @return: MongoDatabase
     */
    fun connect():MongoDatabase{
        if(this.db == null){
            println("Connecting to ${this.uri} ")
            this.mongoClient = MongoClient(this.uri)
            this.db = this.mongoClient!!.getDatabase(this.databaseName)
        }
        return this.db!!
    }
    /**
     * This function close the connection with a database
     * @return: void
     */
    fun close(){
        this.mongoClient!!.close()
        this.mongoClient = null
        this.db = null
        println("Connection closed with ${this.uri}")
    }
}