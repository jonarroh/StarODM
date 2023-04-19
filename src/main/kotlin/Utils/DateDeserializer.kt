package Utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        val jsonPrimitive = json?.asJsonPrimitive
        return if (jsonPrimitive != null && jsonPrimitive.isString) {
            // La propiedad fechaCreacion es una cadena.
            Date(jsonPrimitive.asString)
        } else {
            // La propiedad fechaCreacion es un objeto JSON (JsonPrimitive).
            val seconds = json?.asJsonObject?.getAsJsonPrimitive("\$date")?.asLong
            Date(seconds ?: 0L)
        }
    }
}
