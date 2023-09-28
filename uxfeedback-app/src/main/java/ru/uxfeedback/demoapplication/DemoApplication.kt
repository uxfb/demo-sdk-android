package ru.uxfeedback.demoapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.uxfeedback.demoapplication.api.logs.LogManagerApi
import ru.uxfeedback.demoapplication.api.prefs.SharedPreferencesApi
import ru.uxfeedback.pub.sdk.UxFbFont
import ru.uxfeedback.pub.sdk.UxFbTheme
import ru.uxfeedback.pub.sdk.UxFeedback


import javax.inject.Inject

@HiltAndroidApp
class DemoApplication: Application(){

    @Inject
    lateinit var sharedPreferencesApi: SharedPreferencesApi

    @Inject
    lateinit var logManagerApi: LogManagerApi

    override fun onCreate() {
        super.onCreate()
        UxFeedback.setup(this, sharedPreferencesApi.appId, sharedPreferencesApi.getSettings(),logManagerApi.campaignListener, logManagerApi.logListener)
    }

}