package ru.uxfeedback.demoapplication.api.prefs

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.api.prefs.entities.ApiDedicatedUrl

import ru.uxfeedback.demoapplication.api.prefs.entities.apiUrls
import ru.uxfeedback.pub.sdk.UxFbSettings
import ru.uxfeedback.pub.sdk.UxFbTheme
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesApi @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
) {

    private val sharedPrefs by lazy { context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }

    val appId: String
        get() {
            val appIdItems = getAppIds()
            val pos = sharedPrefs.getInt(APPID_POSITION, 0)
            if (pos > (appIdItems.size -1)){
                return appIdItems[0]
            }
           return appIdItems[pos]
        }

    @SuppressLint("ApplySharedPref")
    fun pushAppId(appId: String){
        if (appId.isEmpty()){
            return
        }
        val currentList =  getAppIds().toMutableList()
        if (currentList.contains(appId)){
            sharedPrefs.edit().apply{
                putInt(APPID_POSITION,  currentList.indexOf(appId))
            }.commit()
        }else{
            currentList.add(appId)
            sharedPrefs.edit().apply{
                putStringSet(APPID_LIST, currentList.toSet())
            }.commit()
            sharedPrefs.getStringSet(APPID_LIST, null)?.indexOf(appId)?.let { pos ->
                sharedPrefs.edit().apply{
                    putInt(APPID_POSITION, pos)
                }.commit()
            }
        }
    }

    fun deleteAppId(pos: Int){
        val currentList =  getAppIds().toMutableList()
        if ((currentList.size - 1) < pos){
            return
        }
        currentList.removeAt(pos)
        sharedPrefs.edit().apply{
            putStringSet(APPID_LIST, currentList.toSet())
            putInt(APPID_POSITION, 0)
        }.apply()
    }

    fun getAppIds(): List<String>{
        return (sharedPrefs.getStringSet(APPID_LIST, null)?.takeIf { it.isNotEmpty() } ?: setOf("ckzxywct400003965yjfzyp5m")).toList()
    }


    val eventName: String
        get() {
            val eventNameItems = getEventNames()
            val pos = sharedPrefs.getInt(EVENT_NAME_POSITION, 0)
            if (pos > (eventNameItems.size -1)){
                return eventNameItems[0]
            }
            return eventNameItems[pos]
        }

    fun deleteEventName(pos: Int){
        val currentList = getEventNames().toMutableList()
        if ((currentList.size - 1) < pos){
            return
        }
        currentList.removeAt(pos)
        sharedPrefs.edit().apply{
            putStringSet(EVENT_NAME_LIST, currentList.toSet())
            putInt(EVENT_NAME_POSITION, 0)
        }.apply()
    }

    @SuppressLint("ApplySharedPref")
    fun pushEventName(eventName: String){
        if (eventName.isEmpty()){
            return
        }
        val currentList = getEventNames().toMutableList()
        if (currentList.contains(eventName)){
            sharedPrefs.edit().apply{
                putInt(EVENT_NAME_POSITION,  currentList.indexOf(eventName))
            }.commit()
        }else{
            currentList.add(eventName)
            sharedPrefs.edit().apply{
                putStringSet(EVENT_NAME_LIST, currentList.toSet())
            }.commit()
            sharedPrefs.getStringSet(EVENT_NAME_LIST, null)?.indexOf(eventName)?.let { pos ->
                sharedPrefs.edit().apply{
                    putInt(EVENT_NAME_POSITION, pos)
                }.commit()
            }
        }
    }

    fun getEventNames(): List<String>{
        return (sharedPrefs.getStringSet(EVENT_NAME_LIST, null)?.takeIf { it.isNotEmpty() } ?: setOf("TestEvent")).toList()
    }

    val themePos: Int
        get() {
            return sharedPrefs.getInt(THEME_POSITION, 0)
        }

    val theme: String
        get() {
            val themeItems = getThemes()
            val pos = sharedPrefs.getInt(THEME_POSITION, 0)
            return themeItems[pos]
        }

    @SuppressLint("ApplySharedPref")
    fun pushThemePos(pos: Int){
        sharedPrefs.edit().apply{
            putInt(THEME_POSITION, pos)
        }.commit()
    }

    fun getThemes(): List<String>{
        return listOf("UXFBLightTheme", "UXFBDarkTheme", "ResourcesUXFBLightTheme", "ResourcesUXFBDarkTheme", "Кастомная тема")
    }

    fun getCustomTheme(): UxFbTheme{
        return sharedPrefs.getString(CUSTOM_THEME, null)?.takeIf { it.isNotEmpty() }?.let { payload ->
            try {
                gson.fromJson(payload, UxFbTheme::class.java)
            } catch (e: Exception){
                null
            }
        } ?: UxFbTheme.fromStyle(R.style.UXFBLightTheme) }

    @SuppressLint("ApplySharedPref")
    fun pushCustomTheme(theme: UxFbTheme){
        sharedPrefs.edit().apply{
            putString(CUSTOM_THEME, gson.toJson(theme))
        }.commit()
    }

    fun getSettings(): UxFbSettings{
        return sharedPrefs.getString(SDK_SETTINGS, null)?.takeIf { it.isNotEmpty() }?.let { payload ->
            try {
                gson.fromJson(payload, UxFbSettings::class.java).apply {
                    apiUrlDedicated = apiUrl.url
                }
            } catch (e: Exception){
                null
            }
        } ?: UxFbSettings.getDefault().apply {
            apiUrlDedicated = apiUrl.url
        }
    }


    @SuppressLint("ApplySharedPref")
    fun pushSettings(settings: UxFbSettings){
        settings.apiUrlDedicated = ""
        sharedPrefs.edit().apply{
            putString(SDK_SETTINGS, gson.toJson(settings))
        }.commit()
    }


    val apiUrl: ApiDedicatedUrl
        get() {
            val apiUrlItems = getApiUrls()
            val pos = sharedPrefs.getInt(API_URL_POSITION, 0)
            return apiUrlItems[pos]
        }

    fun getApiUrls(): List<ApiDedicatedUrl>{
        return apiUrls()
    }

    @SuppressLint("ApplySharedPref")
    fun pushApiUrlPos(pos: Int){
        sharedPrefs.edit().apply{
            putInt(API_URL_POSITION, pos)
        }.commit()
    }

    companion object{
        private const val PREF_NAME = "UxDemoStorage"
        private const val APPID_LIST = "AppIdList"
        private const val APPID_POSITION = "AppIdPosition"
        private const val EVENT_NAME_LIST = "EventNameList"
        private const val EVENT_NAME_POSITION = "EventNamePosition"
        private const val THEME_POSITION = "ThemePosition"
        private const val CUSTOM_THEME = "CustomTheme"
        private const val SDK_SETTINGS = "SDKSettings"
        private const val API_URL_POSITION = "ApiUrlSettings"
    }

}