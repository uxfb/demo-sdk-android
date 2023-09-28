package ru.uxfeedback.demoapplication.api.prefs.entities

data class ApiDedicatedUrl(val name:String, val url: String)

fun apiUrls() = listOf(
    ApiDedicatedUrl("По умолчанию", "")
)


