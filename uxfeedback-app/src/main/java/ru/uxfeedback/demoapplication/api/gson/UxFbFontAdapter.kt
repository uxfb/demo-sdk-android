package ru.uxfeedback.demoapplication.api.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import ru.uxfeedback.pub.sdk.UxFbFont
import java.lang.reflect.Type

class UxFbFontAdapter  :JsonDeserializer<UxFbFont> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): UxFbFont {
        val fontName = json.asJsonObject?.get("fontFamily")?.asString ?: ""
        val italic = json.asJsonObject?.get("italic")?.asBoolean ?: false
        val size = json.asJsonObject?.get("size")?.asInt ?: 14
        val weight = json.asJsonObject?.get("weight")?.asInt ?: UxFbFont.NORMAL
        return UxFbFont.fromName(fontName, weight, size, italic)
    }

}