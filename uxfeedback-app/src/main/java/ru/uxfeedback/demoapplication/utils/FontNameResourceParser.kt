package ru.uxfeedback.demoapplication.utils

class FontNameResourceParser(private val fontNameResource: String) {

    val resourceType = "font"

    val isAssetsResource: Boolean
        get() = fontNameResource.contains("assets:/")

    val isFontResource: Boolean
        get() = fontNameResource.contains(":font/")

    val packageName: String
        get() = fontNameResource.substringBefore(":font/")

    val resourceName: String
        get() = fontNameResource.substringAfter(":font/")

    val assetsResourceName: String
        get() = fontNameResource.substringAfter("assets:/fonts/")

}