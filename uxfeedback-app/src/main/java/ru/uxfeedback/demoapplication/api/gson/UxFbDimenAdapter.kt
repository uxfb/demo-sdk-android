package ru.uxfeedback.demoapplication.api.gson

import com.google.gson.*
import ru.uxfeedback.pub.sdk.UxFbDimen
import java.lang.reflect.Type

class UxFbDimenAdapter: JsonSerializer<UxFbDimen>, JsonDeserializer<UxFbDimen> {
    override fun serialize(src: UxFbDimen, srcType: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.dpValue.toString())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): UxFbDimen {
        var dimenDp : Int = 0
        if (json.asString != null && json.asString.isNotEmpty()){
            try {
                dimenDp = json.asInt
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        return UxFbDimen.fromDp(dimenDp)
    }
}
