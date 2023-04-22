package Find


import com.google.gson.Gson
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.bson.BsonBinaryReader
import org.bson.Document
import org.bson.codecs.DecoderContext
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider


/**
With this class we can find a document in a collection
 @param: MongoDatabase db
 @author: Jonarroh
 */
class Find(private val db:MongoDatabase) {

    /**
    This function find all in a collection
    @param: String collection
    @return: Document
     */
    private fun findAll(collection: String): FindIterable<Document> {
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

    /**
     * This function findOne in a collection
     * @param: String collection
     * @param: String id like "640b8d4e1381cc766f204b53"
     * @return: Document
     */
     fun findOneByIdAsDocument(collection: String, id: String): Document {
        val collection = this.db.getCollection(collection)
        val objectId = org.bson.types.ObjectId(id)
        val document = collection.find(Document("_id", objectId)).first()
        return document
        }
    /**
     * This function findOne in a collection
     * @param: String collection
     * @param: String id
     * @return: String
     */
    fun findOneByIdAsString(collection: String, id: String): String {
        val document = findOneByIdAsDocument(collection, id)
        return document.toJson()
    }

    /**
     * This function findOne in a collection
     * and return a T
     * @param: String collection
     * @param: String id
     * @param: Class<T> class
     * @return: T
     */
    fun <T> findOneByIdAsList(collection: String, id: String, classT: Class<T>): T {
        val document = findOneByIdAsDocument(collection, id)
        val gson = Gson()
        if (document != null) {
            //put the _id in id
            val id = document.get("_id").toString()
            document.remove("_id")
            document.append("id", id)
            val json = document.toJson()
            val t = gson.fromJson(json, classT)
            return t
        }
        return classT.newInstance()
    }
}