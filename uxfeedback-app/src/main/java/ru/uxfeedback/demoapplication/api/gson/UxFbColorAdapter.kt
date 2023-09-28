package ru.uxfeedback.demoapplication.api.gson

import android.graphics.Color
import androidx.annotation.ColorInt
import com.google.gson.*
import ru.uxfeedback.pub.sdk.UxFbColor
import java.lang.reflect.Type

class UxFbColorAdapter : JsonSerializer<UxFbColor>, JsonDeserializer<UxFbColor> {
    override fun serialize(src: UxFbColor, srcType: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.hexString)
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): UxFbColor {
        @ColorInt
        var colorInt : Int = Color.TRANSPARENT
        if (json.asString != null && json.asString.isNotEmpty()){
            try {
                colorInt = Color.parseColor(json.asString)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        return UxFbColor.fromInt(colorInt)
    }
}