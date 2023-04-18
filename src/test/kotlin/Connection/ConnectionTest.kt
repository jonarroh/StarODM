package Connection


import com.mongodb.client.MongoDatabase
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ConnectionTest {
    private var connection: Connection? = null
    private var db: MongoDatabase? = null
    @BeforeAll
    fun setUp(){
        this.connection = Connection("localhost", "27017", "test", false)
        this.db = this.connection!!.connect()
    }

    @AfterAll
    fun tearDown(){
        this.connection!!.close()
    }

    @Test
    fun testConnection(){
        //check if the connection is established
        assertNotNull(this.db)
    }

    @Test
    fun testReturnName(){
        //check if the name of the database is correct
        assert(this.db!!.name == "test")
    }

    @Test
    fun testCloseConnection(){
        //check if the connection is closed
        this.connection!!.close()
        assertNull(this.connection)
        assertNull(this.db)
    }
}