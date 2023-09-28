package ru.uxfeedback.demoapplication.api.logs

import ru.uxfeedback.demoapplication.api.logs.entities.LogRecord
import ru.uxfeedback.demoapplication.ui.fragments.listeners.ListenersFragment
import ru.uxfeedback.demoapplication.ui.fragments.listeners.ListenersFragment.BindingHolder
import ru.uxfeedback.pub.sdk.UxFbOnEventsListener
import ru.uxfeedback.pub.sdk.UxFbOnLogListener
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LogManagerApi @Inject constructor()  {

    private val logRecords = mutableListOf<LogRecord>()

    private var subscriber = WeakReference<BindingHolder>(null)

    val logList : List<LogRecord>
        get() = logRecords.toList()

    fun reset() {
        logRecords.clear()
        subscriber.get()?.onUpdate(logList)
    }

    val campaignListener = object : UxFbOnEventsListener {
        override fun uxFbNoCampaignToStart(eventName: String) {
            logRecords.add(LogRecord.obtainCampaign("uxFbNoCampaignToStart$eventName"))
            subscriber.get()?.onUpdate(logList)
        }

        override fun uxFbOnFieldsEvent(campaignId: Int, eventName: String, fieldValues: Map<String, Array<String>>) {
            logRecords.add(LogRecord.obtainCampaign("uxFbOnFieldsEvent$campaignId$eventName$fieldValues"))
            subscriber.get()?.onUpdate(logList)
        }

        override fun uxFbOnFinishCampaign(campaignId: Int, eventName: String) {
            logRecords.add(LogRecord.obtainCampaign("uxFbOnFinishCampaign$campaignId$eventName"))
            subscriber.get()?.onUpdate(logList)
        }

        override fun uxFbOnReady() {
            logRecords.add(LogRecord.obtainCampaign("uxFbOnReady"))
            subscriber.get()?.onUpdate(logList)
        }

        override fun uxFbOnStartCampaign(campaignId: Int, eventName: String) {
            logRecords.add(LogRecord.obtainCampaign("uxFbOnStartCampaign$campaignId$eventName"))
            subscriber.get()?.onUpdate(logList)
        }

        override fun uxFbOnTerminateCampaign(campaignId: Int, eventName: String, terminatedPage: Int, totalPages: Int) {
            logRecords.add(LogRecord.obtainCampaign("uxFbOnTerminateCampaign$campaignId$eventName$terminatedPage$totalPages"))
            subscriber.get()?.onUpdate(logList)
        }
    }

    val logListener = object : UxFbOnLogListener {
        override fun uxFbOnLog(message: String) {
            logRecords.add(LogRecord.obtainLog("uxFbOnLog$message"))
            subscriber.get()?.onUpdate(logList)
        }
    }

    fun subscribeUpdates(subscriber: BindingHolder){
        this@LogManagerApi.subscriber = WeakReference(subscriber)
    }

}