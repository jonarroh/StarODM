package Find

import Utils.DateDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoDatabase
import org.bson.Document
import java.lang.reflect.Type
import java.util.Date

/**
With this class we can find a document in a collection
 @param: MongoDatabase db
 @author: Jonarroh
 */
class Find(private val db:MongoDatabase){

    /**
    This function find all in a collection
    @param: String collection
    @return: Document
    */
    private fun findAll(collection: String): FindIterable<Document>{
        val collection = this.db.getCollection(collection)
        val documents = collection.find()
        return documents
    }

    /**
     * This function find all in a collection and return a string
     * @param: String collection
     * @return: String
     */
    fun findAllAsString(collection: String): String {
        val documents = findAll(collection)
        val sb = StringBuilder()

        for (document in documents) {
            sb.append(document.toJson()).append("\n")
        }
        return sb.toString()
    }

    /**
     * This function find a document in a collection
     * and return a List of T
     * @param: String collection
     * @param: Class<T> class
     * @return: List<T>
     */
    fun <T> findAllAsList(collection: String, classT: Class<T>): List<T> {
        val documents = findAll(collection)
        val gson = Gson()
        val list = mutableListOf<T>()
        for (document in documents) {
            //put the _id in id
            val id = document.get("_id").toString()
            document.remove("_id")
            document.append("id", id)
            val json = document.toJson()
            val t = gson.fromJson(json, classT)
            list.add(t)

        }
        return list
    }
}