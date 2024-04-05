package ru.uxfeedback.demoapplication.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.uxfeedback.demoapplication.api.gson.UxFbColorAdapter
import ru.uxfeedback.demoapplication.api.gson.UxFbDimenAdapter
import ru.uxfeedback.demoapplication.api.gson.UxFbFontAdapter
import ru.uxfeedback.demoapplication.ui.fragments.attributes.entities.AttributeRecord
import ru.uxfeedback.pub.sdk.UxFbColor
import ru.uxfeedback.pub.sdk.UxFbDimen
import ru.uxfeedback.pub.sdk.UxFbFont
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(UxFbColor::class.java, UxFbColorAdapter())
        .registerTypeAdapter(UxFbDimen::class.java, UxFbDimenAdapter())
        .registerTypeAdapter(UxFbFont::class.java, UxFbFontAdapter())
        .create()

    @Provides
    @Singleton
    fun provideAttributes() = mutableListOf<AttributeRecord>()

}