package Find

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoDatabase
import org.bson.Document

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
    fun findAll(collection: String): FindIterable<Document>{
        val collection = this.db.getCollection(collection)
        val documents = collection.find()
        return documents
    }
    fun findAllAsString(collection: String): String {
        val collection = this.db.getCollection(collection)
        val documents = collection.find()
        val sb = StringBuilder()

        for (document in documents) {
            sb.append(document.toJson()).append("\n")
        }

        return sb.toString()
    }

}